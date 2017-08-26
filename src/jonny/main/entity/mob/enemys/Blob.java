package jonny.main.entity.mob.enemys;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Player;
import jonny.main.entity.projectiles.RottenGooBall;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.sound.Sound;

public class Blob extends Enemy {

	//enemy attributes not in super class.
	private double healthStripRatio;

	private int shootTimer = 0;
	private static int shootingTime;

	private int animTimer = 0;

	public Blob(int xSpawn, int ySpawn, Sprite sprite, boolean dropsMoney) {

		//general setup
		x = xSpawn;
		y = ySpawn;

		this.sprite = sprite;

		isEnemy = true;
		health = 12;
		healthStripRatio = health / 50;
		damage = 1; //for flinching thing
		hitFadeMax = 120;
		this.dropsMoney = dropsMoney;

		shootingTime = 80;

		//collision area
		cWidth = 30;
		cHeight = 30;
		xTrans = -15;
		yTrans = -15;

	}

	public void update() {

		animTimer++;
		if (animTimer > 500000) animTimer = 0;

		//check for player collision if were rendering on the screen.
		if (onScreen) {
			Rectangle box = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

			shootTimer++;
			if (shootTimer >= shootingTime) {
				shootTimer = 0;
				fireRottenGoo();
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
			Sound.blouk.play(false);
			dropBasicHealth((int) x, (int) y);

			if (dropsMoney) dropMoney((int) x + 8, (int) y);

			remove();
		}

		if (recentlyHit) {
			hitFadeTimer = hitFadeMax;
			recentlyHit = false;
		}

		if (hitFadeTimer > 0) hitFadeTimer--;

	}

	public void remove() {
		removed = true;
	}

	public void render(Screen screen) {

		//animation for the 3 blob types
		if (sprite == Sprite.blob_right1 || sprite == Sprite.blob_right2) {
			if (animTimer % 60 > 30) {
				sprite = Sprite.blob_right1;
			} else {
				sprite = Sprite.blob_right2;
			}
		}

		if (sprite == Sprite.blob_left1 || sprite == Sprite.blob_left2) {
			if (animTimer % 60 > 30) {
				sprite = Sprite.blob_left1;
			} else {
				sprite = Sprite.blob_left2;
			}
		}

		if (sprite == Sprite.blob_down1 || sprite == Sprite.blob_down2) {
			if (animTimer % 60 > 30) {
				sprite = Sprite.blob_down1;
			} else {
				sprite = Sprite.blob_down2;
			}
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

	public void fireRottenGoo() {
		//convert to atan2 grid co-ords for the line(inputting the cartesian co-ords), and then find it using the function
		double xp = Game.player.x - x;
		double yp = Game.player.y - y;
		double angle = Math.atan2(yp, xp);

		RottenGooBall ball = new RottenGooBall((int) x, (int) y, angle);
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
