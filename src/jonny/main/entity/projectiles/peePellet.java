package jonny.main.entity.projectiles;

import java.awt.Rectangle;

import jonny.main.entity.Particle.Particle;
import jonny.main.entity.mob.enemys.Enemy;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;

public class peePellet extends Projectile {
	//decreases damage by about 50% every 1 second
	public static final double DAMAGE_DECREASE_RATE = 0.99;

	public peePellet(int x, int y, double dir) {
		super(x, y, dir);
		sprite = Sprite.pee_pellet;
		speed = 6;
		range = 280;
		damage = 3.5;
		//damage = 200;

		//collision variables
		cWidth = 2;
		cHeight = 2;
		xTrans = -1;
		yTrans = 0;

		//find the future x and y values
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
					Particle p = new Particle((int) x, (int) y, 4, 2, Sprite.shotgun_particle);

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

		if (collisionTile((int) nx, (int) ny) || objectCollision((int) nx, (int) ny)) {
			removed = true;
			return;
		} else {
			x += nx;
			y += ny;
			damage = damage * DAMAGE_DECREASE_RATE;
		}

		if (distance() > range) {
			removed = true;
		}

	}

	private int distance() {
		int dist = 0;

		//phythagorus therom here to find distance after figuring out oa and b
		int a = (int) y - yOrigin;
		int b = (int) x - xOrigin;
		dist = (int) Math.sqrt(Math.abs((a * a) + (b * b)));

		return dist;
	}

	public void render(Screen screen) {
		screen.renderObject((int) x - 8, (int) y - 8, sprite);
	}
}
