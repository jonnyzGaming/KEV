package jonny.main.entity.mob.enemys;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Player;
import jonny.main.entity.projectiles.GooBall;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.sound.Sound;

public class CaveCrawler extends Enemy {

	//enemy attributes not in super class.
	private boolean movingRight = true;
	private double healthStripRatio;

	private int shootTimer = 0;
	private static int shootingTime;

	private int animTimer = 0;

	public CaveCrawler(int xSpawn, int ySpawn, int direction) {

		//general setup
		x = xSpawn;
		y = ySpawn;
		dir = direction;

		isEnemy = true;
		health = 20;
		healthStripRatio = health / 50;
		moveSpeeds = 0.6;
		damage = 3; //when touched
		hitFadeMax = 120;

		shootingTime = 80;

		//(note cave crawlers always drop money hence no need that code used for other enemys)

		if (dir == 0) movingRight = true;
		if (dir == 1) movingRight = false;

		//collision area
		cWidth = 26;
		cHeight = 17;
		xTrans = -13;
		yTrans = -9;

		//needs a sprite
		sprite = Sprite.cave_crawler_1;
	}

	public void update() {

		animTimer++;
		if (animTimer > 500000) animTimer = 0;

		flipDirection();

		//movement
		if (movingRight) {
			x += moveSpeeds;
		} else {
			x -= moveSpeeds;
		}

		//check for player collision if were rendering on the screen.
		if (onScreen) {
			Rectangle box = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

			shootTimer++;
			if (shootTimer >= shootingTime) {
				shootTimer = 0;
				fireGoo();
			}

			//damage the player and set to flinching(so cant be damaged constantly)
			if (entityCollision(Game.player, box)) {

				//activate flinching
				if (!Player.flinching) {
					Player.health -= damage;
					Player.flinchActivated = 1;
				}

			}

		}

		//remove if mash is dead
		if (health <= 0) {
			Sound.crawlerDeath.play(false);
			dropBasicHealth((int) x, (int) y);
			dropMoney((int) x + 8, (int) y);
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

	}

	public void remove() {
		removed = true;
	}

	public void render(Screen screen) {

		//animation for mash
		if (animTimer % 120 > 60) {
			sprite = Sprite.cave_crawler_1;
		} else {
			sprite = Sprite.cave_crawler_2;
		}

		screen.renderObject((int) x - 15, (int) y - 15, sprite, this);

		if (hitFadeTimer > 0) {

			screen.renderAccurateSprite((int) x - 24, (int) y - 14, Sprite.health_bar, this);
			//render the 50 health strips
			for (int i = 1; i <= 50; i++) {
				//render the strips based on the overall health
				if (i * healthStripRatio <= health) {
					screen.renderAccurateSprite((int) x - 24 + i, (int) y - 13, Sprite.health_strip, this);
				}
			}
		}
	}

	public void fireGoo() {
		//convert to atan2 grid co-ords for the line(inputting the cartesian co-ords), and then find it using the function
		double xp = Game.player.x - x;
		double yp = Game.player.y - y;
		double angle = Math.atan2(yp, xp);

		GooBall ball = new GooBall((int) x, (int) y, angle);
		ball.init(Game.level);
		level.add(ball);

		Sound.gooFire.play(false);
	}

	//getters and setters.
	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}
}
