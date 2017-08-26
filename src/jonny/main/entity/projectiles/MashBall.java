package jonny.main.entity.projectiles;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.Particle.Particle;
import jonny.main.entity.mob.Player;
import jonny.main.entity.mob.enemys.MashBoss;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;

public class MashBall extends Projectile {

	//general mashball properties
	public Sprite sprite;

	public MashBall(int x, int y, double dir, boolean normalOrHyperSpeed) {
		super(x, y, dir);

		//the normal speed.
		if (normalOrHyperSpeed) speed = 2;
		else speed = 3; //hyper

		range = 400;
		damage = 3;

		cWidth = 16;
		cHeight = 16;
		xTrans = -8;
		yTrans = -8;

		sprite = Sprite.mash_ball;

		//find the future x and y values
		nx = speed * Math.cos(dir);
		ny = speed * Math.sin(dir);

	}

	public void update() {
		move();

		if (MashBoss.deathSequenceActivated) {
			removed = true;
		}

		//detect collision with the player
		Rectangle colBox = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

		if (entityCollision(Game.player, colBox)) {
			removed = true;
			Player.health -= damage;

			//create particle effect
			Particle p = new Particle((int) x, (int) y, 7, 8, Sprite.mash_ball_particle);
		}

	}

	private void move() {

		//ensure to only can fire with the boss rooms bounds(looks cooler not colliding with tiles)
		if (x > 2894 && x < 3426) {
			x += nx;
			y += ny;
		} else {
			removed = true;
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
