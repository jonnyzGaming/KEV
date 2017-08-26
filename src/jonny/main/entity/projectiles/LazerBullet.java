package jonny.main.entity.projectiles;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.Particle.Particle;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;

public class LazerBullet extends Projectile {

	public Sprite sprite;
	int particleDispurseTimer = 0;

	public LazerBullet(int x, int y, double dir) {
		super(x, y, dir);

		speed = 5;
		range = 600;
		damage = 5;

		cWidth = 14;
		cHeight = 10;
		xTrans = -8;
		yTrans = -5;

		sprite = Sprite.lazer_bullet;

		//find the future x and y values
		nx = speed * Math.cos(dir);
		ny = speed * Math.sin(dir);

	}

	public void update() {
		move();

		//update the particle effect as it goes along.
		particleDispurseTimer++;
		if (particleDispurseTimer % 30 > 15) createTrailingParticles(x, y);

		//detect collision with the player
		Rectangle colBox = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

		if (entityCollision(Game.player, colBox)) {

			removed = true;
			Player.health -= damage;
			//create particle effect for hitting
			Particle p = new Particle((int) x, (int) y, 7, 5, Sprite.lazer_bullet_hit_particle);

		}

	}

	//creates a trail of lazer particles for the high speed bullet.
	private void createTrailingParticles(double x, double y) {
		Particle p = new Particle((int) x - 5, (int) y, 30, 3, Sprite.lazer_bullet_particle);

	}

	private void move() {

		if (collisionTile((int) nx, (int) ny) || objectCollision((int) nx, (int) ny)) {
			removed = true;

			//create particle effect
			Particle p = new Particle((int) x, (int) y, 7, 5, Sprite.lazer_bullet_hit_particle);

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
