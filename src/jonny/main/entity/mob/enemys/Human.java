package jonny.main.entity.mob.enemys;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.sound.Sound;

public class Human extends Enemy {

	//enemy attributes not in super class.
	private boolean movingRight = true;
	private double healthStripRatio;

	private boolean hasRange = false;
	private int xStartRange;
	private int xEndRange;

	private int animTimer = 0;

	private final double normalMoveSpeed;
	private final double slowedMoveSpeed;

	public Human(int xSpawn, int ySpawn, int direction, boolean dropsMoney) {

		//general setup
		x = xSpawn;
		y = ySpawn;
		dir = direction;

		isEnemy = true;
		health = 22;
		healthStripRatio = health / 50;
		moveSpeeds = 0.85;
		normalMoveSpeed = 0.85;
		slowedMoveSpeed = 0.6;
		damage = 3;
		hitFadeMax = 120;

		this.dropsMoney = dropsMoney;

		if (dir == 0) movingRight = true;
		if (dir == 1) movingRight = false;

		//collision area
		cWidth = 21;
		cHeight = 12;
		xTrans = -12;
		yTrans = -7;

	}

	public Human(int xSpawn, int ySpawn, int direction, int xStartRange, int xEndRange, boolean dropsMoney) {

		//general setup
		x = xSpawn;
		y = ySpawn;
		dir = direction;

		isEnemy = true;
		health = 22;
		healthStripRatio = health / 50;
		moveSpeeds = 0.85;
		normalMoveSpeed = 0.85;
		slowedMoveSpeed = 0.6;
		damage = 3;
		hitFadeMax = 120;

		this.xStartRange = xStartRange;
		this.xEndRange = xEndRange;
		hasRange = true;

		this.dropsMoney = dropsMoney;

		if (dir == 0) movingRight = true;
		if (dir == 1) movingRight = false;

		//collision area
		cWidth = 21;
		cHeight = 12;
		xTrans = -12;
		yTrans = -7;

	}

	public void update() {

		animTimer++;
		if (animTimer > 500000) animTimer = 0;

		moveSpeeds = normalMoveSpeed;

		//check for player collision if were rendering on the screen.
		if (onScreen) {
			Rectangle box = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

			if (entityCollision(Game.player, box)) {
				//activate flinching, initial hit
				if (!Player.flinching) {
					Player.health -= damage;
					Player.flinchActivated = 1;
				}

				//while player is still in the human it will have the same affect as mash, slowing not
				//only player but HUMAN AS WELL.
				Game.player.inMash = true;
				Player.normalMovement = false;

				//set moveSpeed to be slower.
				moveSpeeds = slowedMoveSpeed;
			}

		}

		flipDirection();

		//movement
		if (movingRight) {
			x += moveSpeeds;
		} else {
			x -= moveSpeeds;
		}

		//remove if is dead
		if (health <= 0) {
			Sound.humanDeath.play(false);
			dropBasicHealth((int) x, (int) y);

			if (dropsMoney) dropMoney((int) x, (int) y);
			remove();
		}

		if (recentlyHit) {
			hitFadeTimer = hitFadeMax;
			recentlyHit = false;

		}
		if (hitFadeTimer > 0) hitFadeTimer--;

		//gravity
		gravity(3);

	}

	public void flipDirection() {
		if (collision(1, 0) || objectCollision(1, 0) || doorCollision(1, 0)) {
			movingRight = false;
			return;
		}

		if (collision(-1, 0) || objectCollision(-1, 0) || doorCollision(-1, 0)) {
			movingRight = true;
		}

		if (hasRange) {
			if (x >= xEndRange) {
				movingRight = false;
				return;
			}

			if (x <= xStartRange) {
				movingRight = true;
				return;
			}
		}

	}

	public void remove() {
		removed = true;

		Game.player.inMash = false;
		Player.normalMovement = true;
	}

	public void render(Screen screen) {

		//animation
		//moving right 
		if (movingRight) {
			if (animTimer % 40 > 20) {
				sprite = Sprite.human_right1;
			} else {
				sprite = Sprite.human_right2;
			}

		}

		//moving left 
		if (!movingRight) {
			if (animTimer % 40 > 20) {
				sprite = Sprite.human_left1;
			} else {
				sprite = Sprite.human_left2;
			}

		}

		screen.renderObject((int) x - 20, (int) y - 20, sprite, this);

		if (hitFadeTimer > 0) {

			screen.renderAccurateSprite((int) x - 24, (int) y - 13, Sprite.health_bar, this);
			//render the 50 health strips
			for (int i = 1; i <= 50; i++) {
				//render the strips based on the overall health
				if (i * healthStripRatio <= health) {
					screen.renderAccurateSprite((int) x - 24 + i, (int) y - 12, Sprite.health_strip, this);
				}
			}
		}
	}
}
