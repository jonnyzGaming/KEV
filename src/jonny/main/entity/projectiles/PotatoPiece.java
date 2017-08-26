package jonny.main.entity.projectiles;

import java.awt.Rectangle;

import jonny.main.entity.Particle.Particle;
import jonny.main.entity.mob.enemys.Enemy;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;

public class PotatoPiece extends Projectile {

	public PotatoPiece(int x, int y, int dir) {
		super(x, y, dir);
		sprite = Sprite.potoato_peice;
		speed = 7;
		range = 280;
		damage = 1;

		//collision variables
		cWidth = 4;
		cHeight = 2;
		xTrans = -1;
		yTrans = 0;

		nx = speed * Math.cos(dir);
		ny = speed * Math.sin(dir);

	}

	public void update() {
		move();

		//detect collision with all enemies.
		Rectangle box = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

		//if there are enemies loop them all
		if (level.enemies.size() > 0) {

			for (int i = 0; i < level.enemies.size(); i++) {

				Enemy e = level.enemies.get(i);

				if (e != null && box.intersects(e.getCollisionRec()) && e.enemyID != -2) {
					e.hit(damage);

					//create particle effect
					Particle p = new Particle((int) x, (int) y, 4, 3, Sprite.Potato_particle);
					removed = true;
					return;
				}
			}
		}

		if (distance() > range) {
			removed = true;
		}

	}

	private void move() {

		if (dir > 0) {
			if (collisionTile((int) speed, 0) || objectCollision((int) speed, 0)) {
				removed = true;
				//create particle effect
				Particle p = new Particle((int) x, (int) y, 3, 3, Sprite.Potato_particle);
				return;
			} else {
				x += speed;
				return;
			}
		}

		if (dir < 0) {
			if (collisionTile((int) -speed, 0) || objectCollision((int) -speed, 0)) {
				removed = true;
				//create particle effect
				Particle p = new Particle((int) x, (int) y, 3, 2, Sprite.Potato_particle);
				return;
			} else {
				x -= speed;
				return;
			}
		}

	}
	private int distance() {
		int dist = 0;

		if (dir > 0) dist = (int) x - xOrigin;
		if (dir < 0) dist = xOrigin - (int) x;

		return dist;
	}

	public void render(Screen screen) {
		screen.renderObject((int) x - 8, (int) y - 8, sprite);
	}

}
