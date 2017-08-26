package jonny.main.entity.mob;

import java.awt.Color;
import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.Entity;
import jonny.main.entity.Objects.Door;
import jonny.main.graphics.Animations;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.input.KeyBoard;
import jonny.main.levels.BasicLevel;

public abstract class Mob extends Entity {

	//general mob properties
	public int dir = 0;
	protected double moveSpeed;
	protected boolean moving = false;
	protected boolean normalJumping = false;
	protected boolean doubleJumping = false;
	protected boolean falling = false;
	protected Sprite sprite;
	public boolean onScreen = false;

	//is player check for collision issue
	public boolean isEnemy = false;
	public boolean isPlayer = false;
	public boolean isLarry = false;

	//collision properties
	protected int cWidth;
	protected int cHeight;
	protected int xTrans; //translation values from the middle point
	protected int yTrans;

	//THE DEATH TILE OF DOOM CHECK + fire
	protected boolean deathTile = false;
	protected boolean fireTile = false;

	//mainly just for the player
	public void move(int xa, int ya) {

		//dir 0StandStill(auto),1right,2not there :0,3left: 4jumpright, 5 jumpleft, 6 fallright, 7 fallleft
		//can only move left or right(or jump)
		if (xa > 0 || (isPlayer && Player.CURRENTLY_SHOOTING && Player.shootRight && dir == 0)) dir = 1;
		if (xa < 0 || (isPlayer && Player.CURRENTLY_SHOOTING && !Player.shootRight) && dir == 0) dir = 3;
		if (ya > 0) dir = 0;
		if (ya < 0) dir = 0;
		if (xa > 0 && ya < 0) dir = 4;
		if (xa < 0 && ya < 0) dir = 5;

		if (falling) {
			if (xa > 0) dir = 6;
			if (xa < 0) dir = 7;
		}

		if (!collision(xa, 0) && !objectCollision(xa, 0)) {
			x += xa;
		}

		if (!collision(0, ya) && !objectCollision(0, ya)) {
			y += ya;
		}

	}

	//detect future collision with tiles (xf is future location)( % for y, / for x)
	public boolean collision(int xf, int yf) {
		boolean solid = false;

		//4 point collision area:
		for (int c = 0; c < 4; c++) {
			int xt = (int) Math.round(((x + xf) + c % 2 * cWidth + xTrans)) >> Screen.TILE_SHIFT;
			int yt = (int) Math.round(((y + yf) + c / 2 * cHeight + yTrans)) >> Screen.TILE_SHIFT;

			if (level.getTile(xt, yt).solid() == true) solid = true;

			//extra precision for entitys of the larger nature(height > 16)
			//all this code wiLL take the orginal collision box and minimize it down to 10 or whatever
			//inner boxes cutting only the y down in this case for the extra precision(will find if 
			//a small tile is say,inside a large sprite.)
			if (cWidth > 16 || cHeight > 16) {
				//loop 10 times cutting into tenths.
				for (double innerCircles = 0.1; innerCircles < 1; innerCircles += 0.1) {

					//check changing only the height (see kev log notes)
					int cHeightT = (int) (cHeight * innerCircles);
					int yt2 = (int) Math.round(((y + yf) + c / 2 * cHeightT + yTrans)) >> Screen.TILE_SHIFT;
					if (level.getTile(xt, yt2).solid() == true) solid = true;

					//check changing only the 
					int cWidthT = (int) (cWidth * innerCircles);
					int xt2 = (int) Math.round(((x + xf) + c % 2 * cWidth + xTrans)) >> Screen.TILE_SHIFT;
					if (level.getTile(xt, yt).solid() == true) solid = true;

				}
			}

			//for mash/enemies that should change dir at spike etc
			if (isEnemy) {
				if (level.getTile(xt, yt).isDeath() == true) solid = true;
			}

			//only do water stuff for player
			if (isPlayer) {
				//extra stuff for water for player.
				int currentX = (int) Math.round(((x) + c % 2 * cWidth + xTrans)) >> Screen.TILE_SHIFT;
				int currentY = (int) Math.round(((y) + c / 2 * cHeight + yTrans)) >> Screen.TILE_SHIFT;

				//check if were colliding with water to change the gravity for player
				if (level.getTile(currentX, currentY).isWater() == true) {
					Player.inWater = true;

					if (level.getTile(currentX, currentY).sprite == Sprite.water_top || level.getTile(currentX, currentY).sprite == Sprite.sewer_water_top) {
						Player.canWaterJump = true;
					} else {
						Player.canWaterJump = false;
					}

				} else {
					Player.inWater = false;
					Player.canWaterJump = false;
				}

				//check for death tiles
				if (level.getTile(currentX, currentY).isDeath() == true) deathTile = true;

				//check for death tiles
				if (level.getTile(currentX, currentY).isFire() == true) {
					fireTile = true;
				} else {
					fireTile = false;
				}

				//check if we are on a ladder tile
				if (level.getTile(currentX, currentY).isClimbable() == true || (ladderCollision(0, 1) && KeyBoard.down)) {
					Player.onLadder = true;
					normalJumping = false;
				} else {
					Player.onLadder = false;
				}

			}
		}

		return solid;
	}

	//extra collision method with option to input the box
	public boolean collision(int xf, int yf, int cWidth, int cHeight, int xTrans, int yTrans) {
		boolean solid = false;

		//4 point collision area:
		for (int c = 0; c < 4; c++) {
			int xt = (int) Math.round(((x + xf) + c % 2 * cWidth + xTrans)) >> Screen.TILE_SHIFT;
			;
			int yt = (int) Math.round(((y + yf) + c / 2 * cHeight + yTrans)) >> Screen.TILE_SHIFT;

			if (level.getTile(xt, yt).solid() == true) solid = true;
		}

		return solid;

	}

	public boolean ladderCollision(int xf, int yf) {
		boolean ladder = false;

		//4 point collision area:
		for (int c = 0; c < 4; c++) {
			int xt = (int) Math.round(((x + xf) + c % 2 * cWidth + xTrans)) >> Screen.TILE_SHIFT;
			;
			int yt = (int) Math.round(((y + yf) + c / 2 * cHeight + yTrans)) >> Screen.TILE_SHIFT;

			if (level.getTile(xt, yt).isClimbable() == true) ladder = true;
		}

		return ladder;

	}

	//detect future collision with all SOLID game objects
	public boolean objectCollision(int xf, int yf) {
		boolean collision = false;

		Rectangle colBox = new Rectangle((int) x + xf + xTrans, (int) y + yf + yTrans, cWidth, cHeight);

		//not future box to check we arent colliding with are selfs.
		Rectangle colBox2 = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

		for (int i = 0; i < level.gameSolidObjects.size(); i++) {
			Mob m = level.gameSolidObjects.get(i);

			if (m.isSolid()) {
				Rectangle mobBox = m.getCollisionRec();
				if (colBox.intersects(mobBox) && !colBox2.equals(mobBox)) {
					collision = true;

					//extra stuff after detecting collision
					if (isPlayer || isLarry) {
						/////extra here////
						///doors////
						if (m.isDoor()) {
							Door d = (Door) m;
							if (d.key != null && !d.key.taken) {
								BasicLevel.text = d.key.keyName + " required";
								BasicLevel.xPos = 10;
								BasicLevel.yPos = 500;
								BasicLevel.col = Color.black;

								Game.DISPLAY_PLAIN = true;
								Game.displayLevel = true;
								Animations.displayText = true;
								Animations.displayTextTime = 120;
								Animations.displayTextTimer = 0;

							}
						}
						////doors////
						/////extra here////
					}

				}

			}

		}

		return collision;

	}

	//detect future collision with all SOLID game objects
	public boolean doorCollision(int xf, int yf) {
		boolean collision = false;

		Rectangle colBox = new Rectangle((int) x + xf + xTrans, (int) y + yf + yTrans, cWidth, cHeight);

		//not future box to check we arent colliding with are selfs.
		Rectangle colBox2 = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

		for (int i = 0; i < level.gameSolidObjects.size(); i++) {
			Mob m = level.gameSolidObjects.get(i);

			if (m.isDoor()) {
				Door d = (Door) m;
				if (!d.opened) {

					Rectangle mobBox = m.getCollisionRec();
					if (colBox.intersects(mobBox) && !colBox2.equals(mobBox)) {
						collision = true;
					}
				}

			}
		}

		return collision;
	}

	//extra object collision method with option to input the box
	public boolean objectCollision(int xf, int yf, int cWidth, int cHeight, int xTrans, int yTrans) {
		boolean collision = false;

		Rectangle colBox = new Rectangle((int) x + xf + xTrans, (int) y + yf + yTrans, cWidth, cHeight);

		//not future box to check we arent colliding with are selfs.
		Rectangle colBox2 = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

		for (int i = 0; i < level.gameSolidObjects.size(); i++) {
			Mob m = level.gameSolidObjects.get(i);

			if (m.isSolid()) {
				Rectangle mobBox = m.getCollisionRec();
				if (colBox.intersects(mobBox) && !colBox2.equals(mobBox)) {
					collision = true;
				}
			}
		}
		return collision;
	}

	//collision for any entitys.
	public boolean entityCollision(Mob m, Rectangle box) {

		boolean collided = false;

		if (m.getCollisionRec().intersects(box)) {
			collided = true;
		}

		return collided;

	}

	public Rectangle getCollisionRec() {

		Rectangle colBox = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

		return colBox;
	}

	//applies gravity given the amount you want to apply (needs to be 3 really to work)
	public void gravity(double yStrength) {

		//loops collisions checking from the given strength first (eg check in 3, if cant then try 2, if cant then try 1 etc so stop buggyness)
		for (double i = yStrength; i > 0; i--) {
			if (!collision(0, (int) Math.ceil(i)) && !objectCollision(0, (int) Math.ceil(i))) {
				y += i;
				break;
			}
		}

	}

	//IN USE
	//allows the option of whether to round the gravity or not for player gravity
	public void gravity(double yStrength, boolean shouldRound) {
		if (shouldRound) {
			for (int i = (int) yStrength; i > 0; i--) {
				if (!collision(0, (int) Math.ceil(i)) && !objectCollision(0, (int) Math.ceil(i)) && !ladderCollision(0, (int) Math.ceil(i))) {
					y += i;
					break;
				}
			}
		} else {
			for (double i = yStrength; i > 0; i--) {
				if (!collision(0, (int) Math.ceil(i)) && !objectCollision(0, (int) Math.ceil(i)) && !ladderCollision(0, (int) Math.ceil(i))) {
					y += i;
					break;
				}
			}
		}

	}

	//extra checks as needed
	public boolean isDoor() {
		return false;
	}

}
