package jonny.main.entity.mob.enemys;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.sound.Sound;

public class seaSlug extends Enemy {

	//enemy attributes not in super class.
	public String color;

	private boolean movingRight = true;
	private double healthStripRatio;

	private boolean hasRange = false;
	private int xStartRange;
	private int xEndRange;

	private int animTimer = 0;

	public seaSlug(int xSpawn, int ySpawn, int direction, String color, boolean dropsMoney) {

		//general setup
		x = xSpawn;
		y = ySpawn;
		dir = direction;

		isEnemy = true;
		health = 15;
		healthStripRatio = health / 50;
		moveSpeeds = 0.6;
		damage = 4;
		hitFadeMax = 120;

		this.dropsMoney = dropsMoney;
		this.color = color;

		if (dir == 0) movingRight = true;
		if (dir == 1) movingRight = false;

		//collision area
		cWidth = 27;
		cHeight = 15;
		xTrans = -15;
		yTrans = -2;

		//needs a sprite
		if (color.equals("red")) sprite = Sprite.seaSlug_right1_red;
		if (color.equals("blue")) sprite = Sprite.seaSlug_right1_blue;

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

			if (entityCollision(Game.player, box)) {
				//activate flinching
				if (!Player.flinching) {
					Player.health -= damage;
					Player.flinchActivated = 1;
				}
			}

		}

		//remove if is dead
		if (health <= 0) {
			Sound.slugDeath.play(false);
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

	}

	public void remove() {
		removed = true;
	}

	public void render(Screen screen) {

		//animation
		if (color.equals("red")) {

			if (movingRight) {
				if (animTimer % 30 > 15) {
					sprite = Sprite.seaSlug_right1_red;
				} else {
					sprite = Sprite.seaSlug_right2_red;
				}
			}

			if (!movingRight) {
				if (animTimer % 30 > 15) {
					sprite = Sprite.seaSlug_left1_red;
				} else {
					sprite = Sprite.seaSlug_left2_red;
				}
			}

		}

		if (color.equals("blue")) {

			if (movingRight) {
				if (animTimer % 30 > 15) {
					sprite = Sprite.seaSlug_right1_blue;
				} else {
					sprite = Sprite.seaSlug_right2_blue;
				}
			}

			if (!movingRight) {
				if (animTimer % 30 > 15) {
					sprite = Sprite.seaSlug_left1_blue;
				} else {
					sprite = Sprite.seaSlug_left2_blue;
				}
			}

		}

		screen.renderObject((int) x - 15, (int) y - 15, sprite, this);

		if (hitFadeTimer > 0) {

			screen.renderAccurateSprite((int) x - 23, (int) y - 8, Sprite.health_bar, this);
			//render the 50 health strips
			for (int i = 1; i <= 50; i++) {
				//render the strips based on the overall health
				if (i * healthStripRatio <= health) {
					screen.renderAccurateSprite((int) x - 23 + i, (int) y - 7, Sprite.health_strip, this);
				}
			}
		}
	}
}
