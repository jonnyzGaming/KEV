package jonny.main.entity.projectiles;

import java.awt.Rectangle;

import jonny.main.entity.Entity;
import jonny.main.entity.mob.Mob;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;

public abstract class Projectile extends Entity {

	//general projectile properties
	protected Sprite sprite;
	protected int xOrigin;
	protected int yOrigin;
	protected double dir;
	protected double speed;
	protected int range;
	protected double damage;
	protected int animTimer = 0;
	protected double nx, ny; //what will be added to x and y each update, based on the angle(dir)

	//collision properties
	protected int cWidth;
	protected int cHeight;
	protected int xTrans; //translation values from the middle point
	protected int yTrans;

	public Projectile(int x, int y, double dir) {
		this.x = x;
		this.y = y;
		this.xOrigin = x;
		this.yOrigin = y;
		this.dir = dir;

	}

	public void update() {

	}

	public void render(Screen screen) {
	}

	protected boolean collisionTile(int xf, int yf) {

		boolean collision = false;
		for (int c = 0; c < 4; c++) {
			int xPos = (int) Math.round(((x + xf) + c % 2 * cWidth + xTrans)) >> Screen.TILE_SHIFT;
			int yPos = (int) Math.round(((y + yf) + c / 2 * cHeight + yTrans)) >> Screen.TILE_SHIFT;
			if (level.getTile(xPos, yPos).solid() == true) collision = true;
			//level is null
		}

		return collision;
	}

	//detect future collision with all SOLID game objects
	public boolean objectCollision(int xf, int yf) {
		boolean collision = false;

		Rectangle colBox = new Rectangle((int) x + xf + xTrans, (int) y + yf + yTrans, cWidth, cHeight);

		for (int i = 0; i < level.gameSolidObjects.size(); i++) {
			Mob m = level.gameSolidObjects.get(i);

			if (m.isSolid() && !m.isEnemy) {
				if (colBox.intersects(m.getCollisionRec())) {
					collision = true;
				}
			}
		}

		return collision;
	}

	/////////////////////////COPYED FROM MOB CLASS, IM SO LAZY//////////
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

}
