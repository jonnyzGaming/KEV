package jonny.main.entity.projectiles;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.Particle.Particle;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;

public class GooBall extends Projectile {

	public Sprite sprite;

	public GooBall(int x, int y, double dir) {
		super(x, y, dir);

		speed = 2.3;
		range = 400;
		damage = 3;

		cWidth = 16;
		cHeight = 16;
		xTrans = -8;
		yTrans = -8;

		sprite = Sprite.goo_ball;

		//find the future x and y values
		nx = speed * Math.cos(dir);
		ny = speed * Math.sin(dir);

	}
	public void update() {
		move();

		//detect collision with the player
		Rectangle colBox = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

		if (entityCollision(Game.player, colBox)) {

			removed = true;
			Player.health -= damage;
			//create particle effect
			Particle p = new Particle((int) x, (int) y, 7, 5, Sprite.goo_ball_particle);

		}

	}
	private void move() {

		if (collisionTile((int) nx, (int) ny) || objectCollision((int) nx, (int) ny)) {
			removed = true;

			//create particle effect
			Particle p = new Particle((int) x, (int) y, 7, 5, Sprite.goo_ball_particle);

			return;
		} else {
			x += nx;
			y += ny;

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
