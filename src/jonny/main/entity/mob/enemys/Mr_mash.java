package jonny.main.entity.mob.enemys;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.sound.Sound;

public class Mr_mash extends Enemy {

	//enemy attributes not in super class.
	private boolean movingRight = true;
	private static final int damageTimerStart = 40;
	private int damageTimer = 0;
	private double healthStripRatio;

	private boolean hasRange = false;
	private int xStartRange;
	private int xEndRange;

	private int animTimer = 0;

	//NOTE DAMAGE SET IN PLAYER CODE SEARCH FOR MASH UNTIL FIND THE GOOD STUFF(I know not good code right)

	public Mr_mash(int xSpawn, int ySpawn, int direction, boolean dropsMoney) {

		//general setup
		x = xSpawn;
		y = ySpawn;
		dir = direction;

		isEnemy = true;
		health = 7;
		healthStripRatio = health / 50;
		moveSpeeds = 0.51;
		damage = 2; //per 2 seconds.
		hitFadeMax = 120;
		this.dropsMoney = dropsMoney;

		if (dir == 0) movingRight = true;
		if (dir == 1) movingRight = false;

		//collision area
		cWidth = 28;
		cHeight = 15;
		xTrans = -14;
		yTrans = -7;

		//needs a sprite
		sprite = Sprite.mr_mash1;
	}

	public Mr_mash(int xSpawn, int ySpawn, int direction, int xStartRange, int xEndRange, boolean dropsMoney) {

		//general setup
		x = xSpawn;
		y = ySpawn;
		dir = direction;
		this.xStartRange = xStartRange;
		this.xEndRange = xEndRange;
		hasRange = true;

		isEnemy = true;
		health = 7;
		healthStripRatio = health / 50;
		moveSpeeds = 0.51;
		hitFadeMax = 120;
		this.dropsMoney = dropsMoney;

		if (dir == 0) movingRight = true;
		if (dir == 1) movingRight = false;

		//collision area
		cWidth = 28;
		cHeight = 15;
		xTrans = -14;
		yTrans = -7;

		//needs a sprite
		sprite = Sprite.mr_mash1;

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
		//NOTE TO SELF, MAY BE BUG IF MANY MASH ON SCREEN AT ONCE
		if (onScreen) {
			Rectangle box = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

			if (entityCollision(Game.player, box)) {
				Game.player.inMash = true;
				Player.normalMovement = false;
			}

		}

		//remove if mash is dead
		if (health <= 0) {
			Sound.mashDeath.play(false);
			dropBasicHealth((int) x, (int) y);

			if (dropsMoney) dropMoney((int) x + 4, (int) y);

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
		Game.player.inMash = false;
		Player.normalMovement = true;

	}

	public void render(Screen screen) {

		//animation for mash
		if (animTimer % 30 > 15) {
			sprite = Sprite.mr_mash1;
		} else {
			sprite = Sprite.mr_mash2;
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

	//getters and setters.
	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

}
