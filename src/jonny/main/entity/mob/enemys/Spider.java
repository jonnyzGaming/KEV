package jonny.main.entity.mob.enemys;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.sound.Sound;

public class Spider extends Enemy {

	//enemy attributes not in super class.
	private boolean movingRight = true;
	private double healthStripRatio;

	private boolean hasRange = false;
	private int xStartRange;
	private int xEndRange;

	private int animTimer = 0;

	//boosting speed vars.
	private boolean speedBoost;
	private int speedBoostTimer;
	private final int speedBoostTime = 30;
	private int speedBoostIntervalTimer;
	private final int speedBoostIntervalTime = 80;

	private final int boostSpeed;
	private final int normalSpeed;

	public Spider(int xSpawn, int ySpawn, int direction, boolean dropsMoney) {

		//general setup
		x = xSpawn;
		y = ySpawn;
		dir = direction;

		isEnemy = true;
		health = 15;
		healthStripRatio = health / 50;
		moveSpeeds = 1;
		normalSpeed = 1;
		boostSpeed = 2;
		damage = 3;
		hitFadeMax = 120;

		this.dropsMoney = dropsMoney;
		speedBoostTimer = 0;
		speedBoostIntervalTimer = 0;

		if (dir == 0) movingRight = true;
		if (dir == 1) movingRight = false;

		//collision area
		cWidth = 21;
		cHeight = 13;
		xTrans = -11;
		yTrans = -7;

	}

	public Spider(int xSpawn, int ySpawn, int direction, boolean moneyDrop, int xStartRange, int xEndRange, boolean dropsMoney) {

		//general setup
		x = xSpawn;
		y = ySpawn;
		dir = direction;

		isEnemy = true;
		health = 15;
		healthStripRatio = health / 50;
		moveSpeeds = 1;
		normalSpeed = 1;
		boostSpeed = 2;
		damage = 3;
		hitFadeMax = 120;

		this.xStartRange = xStartRange;
		this.xEndRange = xEndRange;
		hasRange = true;

		this.dropsMoney = dropsMoney;
		speedBoostTimer = 0;
		speedBoostIntervalTimer = 0;

		if (dir == 0) movingRight = true;
		if (dir == 1) movingRight = false;

		//collision area
		cWidth = 21;
		cHeight = 13;
		xTrans = -11;
		yTrans = -7;

	}

	public void update() {

		animTimer++;
		if (animTimer > 500000) animTimer = 0;

		flipDirection();

		//every interval have a little speed boost for some time.
		if (!speedBoost) speedBoostIntervalTimer++;

		if (speedBoostIntervalTimer == speedBoostIntervalTime) {
			speedBoostIntervalTimer = 0;
			speedBoost = true;
		}

		if (speedBoost) {
			moveSpeeds = boostSpeed;

			speedBoostTimer++;
			if (speedBoostTimer == speedBoostTime) {
				speedBoostTimer = 0;
				speedBoost = false;
				moveSpeeds = normalSpeed;
			}
		}

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
			Sound.spiderDeath.play(false);
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
		//first off have a random chance to flip direction, cause were a sneaky bugger.
		//then can handle normal collsion afterwards
		if (Game.rand.nextInt(100) == 50) {
			if (movingRight) movingRight = false;
			else movingRight = true;
		}

		if (collision(boostSpeed, 0) || objectCollision(boostSpeed, 0) || doorCollision(boostSpeed, 0)) {
			movingRight = false;
			return;
		}

		if (collision(-boostSpeed, 0) || objectCollision(-boostSpeed, 0) || doorCollision(-boostSpeed, 0)) {
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
	}

	public void render(Screen screen) {

		//animation
		if (animTimer % 30 > 15) {
			sprite = Sprite.spider1;
		} else {
			sprite = Sprite.spider2;
		}

		screen.renderObject((int) x - 11, (int) y - 11, sprite, this);

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
