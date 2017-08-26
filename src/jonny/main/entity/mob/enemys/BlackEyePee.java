package jonny.main.entity.mob.enemys;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.sound.Sound;

public class BlackEyePee extends Enemy {

	//enemy attributes not in super class.
	private boolean movingRight = true;
	private double healthStripRatio;

	private boolean hasRange = false;
	private int xStartRange;
	private int xEndRange;

	private boolean hasSeenPlayer = false;

	private int animTimer = 0;

	public BlackEyePee(int xSpawn, int ySpawn, int direction, boolean dropsMoney) {

		//general setup
		x = xSpawn;
		y = ySpawn;
		dir = direction;

		isEnemy = true;
		health = 15;
		healthStripRatio = health / 50;
		moveSpeeds = 1;
		damage = 4; //when touched
		hitFadeMax = 120;
		this.dropsMoney = dropsMoney;

		if (dir == 0) movingRight = true;
		if (dir == 1) movingRight = false;

		//collision area
		cWidth = 30;
		cHeight = 18;
		xTrans = -15;
		yTrans = -9;

		//needs a sprite
		sprite = Sprite.black_eye_pee;
	}

	public BlackEyePee(int xSpawn, int ySpawn, int direction, int xStartRange, int xEndRange, boolean dropsMoney) {

		//general setup
		x = xSpawn;
		y = ySpawn;
		dir = direction;

		isEnemy = true;
		health = 15;
		healthStripRatio = health / 50;
		moveSpeeds = 1;
		damage = 4; //when touched
		hitFadeMax = 120;
		this.dropsMoney = dropsMoney;

		hasRange = true;
		this.xStartRange = xStartRange;
		this.xEndRange = xEndRange;

		if (dir == 0) movingRight = true;
		if (dir == 1) movingRight = false;

		//collision area
		cWidth = 30;
		cHeight = 19;
		xTrans = -15;
		yTrans = -9;

		//needs a sprite
		sprite = Sprite.black_eye_pee;
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

			//damage the player and set to flinching(so cant be damaged constantly)
			if (entityCollision(Game.player, box)) {

				//deal damage and activate flinching
				if (!Player.flinching) {
					Player.health -= damage;
					Player.flinchActivated = 1;
				}

			}

		}

		//remove if mash is dead
		if (health <= 0) {
			dropBasicHealth((int) x, (int) y);
			if (dropsMoney) dropMoney((int) x + 8, (int) y);
			remove();
			Sound.blackEyeDeath.play(false);
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

		//if on the screen converge towards the players position
		if (onScreen) {
			hasSeenPlayer = true;
			if (Game.player.x > x) movingRight = true;
			if (Game.player.x < x) movingRight = false;
		}

		if (collision(1, 0) || objectCollision(1, 0) || doorCollision(1, 0)) {
			movingRight = false;
			return;
		}

		if (collision(-1, 0) || objectCollision(-1, 0) || doorCollision(-1, 0)) {
			movingRight = true;
			return;
		}

		//to stop them moving through each other after theyve come in contact with the player
		if (movingRight && hasSeenPlayer && EnemyCollision(3, 0, this) && (!collision(1, 0) && !objectCollision(1, 0) && !doorCollision(1, 0))) {
			movingRight = false;
			return;
		}

		if (!movingRight && hasSeenPlayer && EnemyCollision(-3, 0, this) && (!collision(-1, 0) && !objectCollision(-1, 0) && !doorCollision(-1, 0))) {
			movingRight = true;
			return;
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

		//animation for mash
		if (animTimer % 60 > 30) {
			sprite = Sprite.black_eye_pee;
		} else {
			sprite = Sprite.black_eye_pee2;
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
}
