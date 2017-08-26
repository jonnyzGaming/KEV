package jonny.main.entity.mob.enemys;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Player;
import jonny.main.entity.projectiles.RottenGooBall;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.sound.Sound;

public class SewerBoss extends Enemy {

	//enemy attributes not in super class.
	private double healthStripRatio;

	private int shootTimer1 = 0;
	private static int shootingTime;
	private int shootTimer2 = 0;
	private int shootTimer3 = 0;
	private int shootTimer4 = 0;
	private int shootTimer5 = 0;
	private int shootTimer6 = 0;

	private int animTimer = 0;

	//shooting ints
	private int shootX1;
	private int shootY1;
	private int shootX2;
	private int shootY2;
	private int shootX3;
	private int shootY3;
	private int shootX4;
	private int shootY4;
	private int shootX5;
	private int shootY5;
	private int shootX6;
	private int shootY6;

	Rectangle BossRoomRec;

	public SewerBoss(int xSpawn, int ySpawn) {

		//general setup
		x = xSpawn;
		y = ySpawn;

		isEnemy = true;
		health = 100;
		healthStripRatio = health / 50;
		damage = 1;
		hitFadeMax = 120;

		shootingTime = 120;

		BossRoomRec = new Rectangle((int) x - 400, (int) y - 230, 1000, 400);

		shootX1 = (int) (x - 118);
		shootY1 = (int) (y + 40);
		shootX2 = (int) (x - 85);
		shootY2 = (int) (y);
		shootX3 = (int) (x - 40);
		shootY3 = (int) (y - 27);
		shootX4 = (int) (x + 20);
		shootY4 = (int) (y - 40);
		shootX5 = (int) (x + 87);
		shootY5 = (int) (y - 15);
		shootX6 = (int) (x + 130);
		shootY6 = (int) (y + 40);

		//collision area
		cWidth = 189;
		cHeight = 90;
		xTrans = -81;
		yTrans = -39;

	}
	public void update() {

		animTimer++;
		if (animTimer > 500000) animTimer = 0;

		//check for player collision if were rendering on the screen.
		Rectangle box = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

		if (entityCollision(Game.player, box)) {
			Game.player.inMash = true;
			Game.player.inSlime = true;
			Player.normalMovement = false;
		}

		//if the player is in the boss room
		if (entityCollision(Game.player, BossRoomRec)) {
			//possibilty to fire from each of the allocated firing points
			if (Game.player.x + (Game.screen.width / 2) > shootX1 && Game.player.x - (Game.screen.width / 2) < shootX1) {
				shootTimer1++;
				if (shootTimer1 >= shootingTime) {
					shootTimer1 = 0;
					fireRottenGoo(shootX1, shootY1);
				}
			}

			if (Game.player.x + (Game.screen.width / 2) > shootX2 && Game.player.x - (Game.screen.width / 2) < shootX2) {
				shootTimer2++;
				if (shootTimer2 >= shootingTime) {
					shootTimer2 = 0;
					fireRottenGoo(shootX2, shootY2);
				}
			}

			if (Game.player.x + (Game.screen.width / 2) > shootX3 && Game.player.x - (Game.screen.width / 2) < shootX3) {
				shootTimer3++;
				if (shootTimer3 >= shootingTime) {
					shootTimer3 = 0;
					fireRottenGoo(shootX3, shootY3);
				}
			}

			if (Game.player.x + (Game.screen.width / 2) > shootX4 && Game.player.x - (Game.screen.width / 2) < shootX4) {
				shootTimer4++;
				if (shootTimer4 >= shootingTime) {
					shootTimer4 = 0;
					fireRottenGoo(shootX4, shootY4);
				}
			}

			if (Game.player.x + (Game.screen.width / 2) > shootX5 && Game.player.x - (Game.screen.width / 2) < shootX5) {
				shootTimer5++;
				if (shootTimer5 >= shootingTime) {
					shootTimer5 = 0;
					fireRottenGoo(shootX5, shootY5);
				}
			}

			if (Game.player.x + (Game.screen.width / 2) > shootX6 && Game.player.x - (Game.screen.width / 2) < shootX6) {
				shootTimer6++;
				if (shootTimer6 >= shootingTime) {
					shootTimer6 = 0;
					fireRottenGoo(shootX6, shootY6);
				}
			}

		}

		//remove if dead
		if (health <= 0) {
			Sound.sewerDeath.play(false);
			dropBasicHealth((int) x, (int) y);
			dropBasicHealth((int) x + 4, (int) y);
			dropBasicHealth((int) x + 11, (int) y);
			dropBasicHealth((int) x + 18, (int) y);
			dropBasicHealth((int) x + 25, (int) y);
			dropBasicHealth((int) x + 32, (int) y);
			dropBasicHealth((int) x + 39, (int) y);
			dropMoney((int) x + 13, (int) y + 20);
			dropGoldMoney((int) x + 20, (int) y + 20);
			dropMoney((int) x + 27, (int) y + 20);

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

		//animation
		if (animTimer % 60 > 30) {
			sprite = Sprite.sewer_boss1;
		} else {
			sprite = Sprite.sewer_boss2;
		}

		screen.renderAccurateSprite((int) x - 138, (int) y - 51, sprite, this);

		screen.renderAccurateSprite((int) x - 5, (int) y - 54, Sprite.health_bar, this);

		//render the 50 health strips
		for (int i = 1; i <= 50; i++) {

			//render the strips based on the overall health
			if (i * healthStripRatio <= health) {
				screen.renderAccurateSprite((int) x - 5 + i, (int) y - 53, Sprite.health_strip, this);
			}
		}
	}

	public void fireRottenGoo(int x, int y) {
		//convert to atan2 grid co-ords for the line(inputting the cartesian co-ords), and then find it using the function
		double xp = Game.player.x - x;
		double yp = Game.player.y - y;
		double angle = Math.atan2(yp, xp);

		RottenGooBall ball = new RottenGooBall(x, y, angle);
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
