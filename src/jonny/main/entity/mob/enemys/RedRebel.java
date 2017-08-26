package jonny.main.entity.mob.enemys;

import jonny.main.Game;
import jonny.main.entity.mob.characters.CabbageKing;
import jonny.main.entity.projectiles.LazerPiece;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.sound.Sound;

public class RedRebel extends Enemy {

	//rebel variables
	private double healthStripRatio;
	private int animTimer = 0;

	//shooting variables
	public int shootWait = 120;
	public int shootWaitTimer = shootWait;

	public boolean shooting = false;
	private static int shootingTime = 20;
	private int shootTimer = shootingTime;

	public RedRebel(int x, int y, boolean dropsMoney) {
		this.x = x;
		this.y = y;

		isEnemy = true;
		health = 20;
		healthStripRatio = health / 50;
		hitFadeMax = 120;
		this.dropsMoney = dropsMoney;

		//collision area
		cWidth = 24;
		cHeight = 25;
		xTrans = -12;
		yTrans = -13;

	}

	public void update() {

		animTimer++;
		if (animTimer > 10000) animTimer = 0;

		//decrement when not shooting
		if (shootWaitTimer > 0 && !shooting) {
			shootWaitTimer--;
			shooting = false;
		}

		//initiate shooting variables
		if (shootWaitTimer == 0 && onScreen) {
			shooting = true;
			shootWaitTimer = shootWait;
			shootTimer = shootingTime;
		}

		//fire for period of time.
		if (shooting) {
			if (shootTimer > 0) {
				if (shootTimer == shootingTime) Sound.lazer.play(false);
				fireLazer();
				shootTimer--;
			} else {
				shooting = false;
			}
		}

		//hit timer stuff
		if (recentlyHit) {
			hitFadeTimer = hitFadeMax;
			recentlyHit = false;

		}
		if (hitFadeTimer > 0) hitFadeTimer--;

		//death
		if (health <= 0) {
			dropBasicHealth((int) x, (int) y);
			if (dropsMoney) dropMoney((int) x + 4, (int) y);
			remove();

			CabbageKing.VillageMobsKilled++;
		}

		gravity(3);

	}

	public void fireLazer() {

		//use trig to find the angle to fire at.
		double aja = Game.player.x - x;
		double opp = Game.player.y - y;
		double angle = Math.atan2(opp, aja);

		LazerPiece piece = new LazerPiece((int) x, (int) y - 5, angle);
		piece.init(Game.level);
		level.add(piece);

	}

	public void render(Screen screen) {

		if (animTimer % 60 > 30) {
			sprite = Sprite.reb_rebel1;
		} else {
			sprite = Sprite.reb_rebel2;
		}

		if (shooting) sprite = Sprite.reb_rebel_shooting;

		//render health bar
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

		screen.renderObject((int) x - 15, (int) y - 15, sprite, this);
	}
}
