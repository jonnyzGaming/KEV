package jonny.main.entity.mob.enemys;

import java.awt.Rectangle;
import java.util.Random;

import jonny.main.Game;
import jonny.main.entity.Objects.BossCage;
import jonny.main.entity.mob.Player;
import jonny.main.entity.projectiles.MashBall;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.sound.Sound;

public class MashBoss extends Enemy {

	//general stats
	public static boolean combatActivated = false;
	private int animTimer = 0;
	private int seqWidth = 50;
	private int seqHeight = 120;
	public static boolean sequence1Activated = false;
	public static boolean returnSequenceActivated = false;
	public static boolean deathSequenceActivated = false;
	private double healthStripRatio;
	private int shootLocX;
	private int shootLocY;
	public Random rand = new Random();
	public static Rectangle jumpRec;

	//combat variables
	public int shootingTimer;
	public int shootingTimeInterval;
	private boolean canMove1 = true;
	private int moveTimer = 0;
	private boolean playerMoving = false;
	private static final int moveTimerLength = 360; //starting move time
	private static final int moveTimerTowardsPlayerLength = 300; //starting move time
	private static final int moveTimerTowardsPlayerDuration = 240; //starting move time
	private int mashTimer = 0;
	private static final int mashTimerLength = 750;
	public static boolean spawnActivated = false;
	public static boolean dieing = false;

	//health ones
	private double halfHealth;
	private double quarterHealth;
	private double fullHealth;

	//determine the speed of the mash balls.
	private boolean isNormalBallSpeed;

	public MashBoss(int x, int y) {
		this.x = x;
		this.y = y;
		isEnemy = true;

		sprite = Sprite.giant_mash1;

		cWidth = 140;
		cHeight = 95;
		xTrans = -71;
		yTrans = -51;

		health = 150;
		fullHealth = health;
		halfHealth = health / 2;
		quarterHealth = halfHealth / 2;

		healthStripRatio = health / 50;

		moveSpeeds = 0.6;
		isNormalBallSpeed = true;

		shootingTimeInterval = 120;

	}

	public void update() {

		//just in case
		if (!combatActivated && !dieing) {
			health = 150;
		}

		animTimer++;
		if (animTimer > 10000) animTimer = 0;
		shootLocX = (int) x + 5;
		shootLocY = (int) y + 3;

		if (health <= 5 && !deathSequenceActivated) {
			Player.sequenceNum = 4;
			Player.sequence = true;
			combatActivated = false;
			dieing = true;
			deathSequenceActivated = true;
			Sound.mashTalking.play(true);

			BossCage.cageOpened = true;
			BossCage.cageClosed = false;
		}

		if (health <= 0) {
			remove();

			//play sound
			Sound.mashBossDeath.play(false);

			//drop money etc and unlock the door
			dropBasicHealth((int) x, (int) y);
			dropBasicHealth((int) x + 10, (int) y);
			dropBasicHealth((int) x + 20, (int) y);
			dropBasicHealth((int) x - 10, (int) y);
			dropBasicHealth((int) x - 20, (int) y);
			dropGoldMoney((int) x + 2, (int) y);

		}
		updateJumpRec();

		Rectangle seqBox = new Rectangle((int) x + xTrans - seqWidth, (int) y + yTrans, seqWidth, seqHeight);
		Rectangle colBox = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

		//sequence area collision
		if (entityCollision(Game.player, seqBox) && !sequence1Activated) {

			Player.sequenceNum = 2;
			Player.sequence = true;
			sequence1Activated = true;
			Sound.mashTalking.play(true);

		} else if (entityCollision(Game.player, seqBox) && returnSequenceActivated && Player.sequence == false) {

			Player.sequenceNum = 3;
			Player.sequence = true;
			returnSequenceActivated = false;
			Sound.mashTalking.play(true);

		}

		//general boss collision
		if (entityCollision(Game.player, colBox)) {
			Player.inMash = true;
			Player.normalMovement = false;
		}

		//covers all fighting characteristics
		if (combatActivated) {

			//firing
			if (shootingTimer < 10000) shootingTimer++;
			//fire mash balls randomly towards the players position
			if (shootingTimer == shootingTimeInterval) {

				//normal firing for above 3 quarter health
				if (health > quarterHealth * 3) {
					shootingTimeInterval = 120;
					fireMash();
					shootingTimer = 0;
				}

				//if at less than 3 quarter health can do the triple spray, and increased rate of fire.
				//rate of fire goes even higher then < 1 quarter health, and also the speed of the balls.
				if (health <= quarterHealth * 3) {
					shootingTimeInterval = 100;
					isNormalBallSpeed = true;

					//give higher rate of fire and speed to balls when quarter health.
					if (health <= quarterHealth) {
						shootingTimeInterval = 80;
						isNormalBallSpeed = false;
					}

					//60% percent change to triple spray
					if (rand.nextInt(10) >= 3) {
						tripleSpray();
						shootingTimer = 0;
					} else {
						fireMash();
						shootingTimer = 0;
					}

				}

			}

			//movement
			if (health <= 125) {

				moveTimer++;
				//long initial move sequence
				if (moveTimer <= moveTimerLength && canMove1) {

					//move left for initial sequence if we can
					if (canMove1) {
						move(0);
					}

					if (moveTimer == 360 && canMove1) {
						canMove1 = false;
						moveTimer = 0;
					}

					//initial movement end

				}

				//move towards the player every 8 seconds for a duration of 4 seconds
				if (!canMove1 && moveTimer == moveTimerTowardsPlayerLength && !playerMoving) {

					moveTimer = 0;
					playerMoving = true;
				}

				//moves the boss towards the player
				if (playerMoving) {

					if (moveTimer <= moveTimerTowardsPlayerDuration) {

						if (Game.player.x <= shootLocX) {
							move(0);
						} else {
							move(1);
						}

					} else {
						moveTimer = 0;
						playerMoving = false;
					}

				}

			}

			//minions spawning
			if (mashTimer < 10000) mashTimer++;
			if (mashTimer == mashTimerLength) {

				spawnMushroom();
				mashTimer = 0;
			}

		}

		gravity(3);

		//for if the player dies.
		if (spawnActivated) {
			x = 3300;
			y = 2360;
			spawnActivated = false;
		}

	}

	public void render(Screen screen) {

		if (animTimer % 30 > 15) {
			sprite = Sprite.giant_mash1;
		} else {
			sprite = Sprite.giant_mash2;
		}

		screen.renderObject((int) x - 75, (int) y - 75, sprite, this);
		screen.renderAccurateSprite((int) x - 24, (int) y - 54, Sprite.health_bar, this);

		//render the 50 health strips
		for (int i = 1; i <= 50; i++) {

			//render the strips based on the overall health
			if (i * healthStripRatio <= health) {
				screen.renderAccurateSprite((int) x - 24 + i, (int) y - 53, Sprite.health_strip, this);
			}
		}

	}

	//0 for left, 1 for right
	public void move(int dir) {

		if (dir == 0) {
			if (!collision(-1, 0)) {
				x -= moveSpeeds;
			}
		}

		if (dir == 1) {
			if (!collision(1, 0)) {
				x += moveSpeeds;
			}
		}

	}

	//fires the mash at the calculated angle
	public void fireMash() {

		//convert to atan2 grid co-ords for the line(inputting the cartesian co-ords), and then find it using the function
		double xp = Game.player.x - shootLocX;
		double yp = Game.player.y - shootLocY;
		double angle = Math.atan2(yp, xp);

		MashBall ball = new MashBall(shootLocX, shootLocY, angle, isNormalBallSpeed);
		ball.init(Game.level);
		level.add(ball);
	}

	public void tripleSpray() {

		//do straight tripleSpray
		//60% time do normal
		if (rand.nextInt(10) >= 4) {
			//shooting left
			if (Game.player.x <= shootLocX) {

				MashBall ball = new MashBall(shootLocX, shootLocY, Math.toRadians(180), isNormalBallSpeed);
				ball.init(Game.level);
				level.add(ball);

				MashBall ball2 = new MashBall(shootLocX, shootLocY, Math.toRadians(160), isNormalBallSpeed);
				ball2.init(Game.level);
				level.add(ball2);

				MashBall ball3 = new MashBall(shootLocX, shootLocY, Math.toRadians(-160), isNormalBallSpeed);
				ball3.init(Game.level);
				level.add(ball3);

			}
			//shooting right
			else {

				MashBall ball = new MashBall(shootLocX, shootLocY, Math.toRadians(0), isNormalBallSpeed);
				ball.init(Game.level);
				level.add(ball);

				MashBall ball2 = new MashBall(shootLocX, shootLocY, Math.toRadians(20), isNormalBallSpeed);
				ball2.init(Game.level);
				level.add(ball2);

				MashBall ball3 = new MashBall(shootLocX, shootLocY, Math.toRadians(-20), isNormalBallSpeed);
				ball3.init(Game.level);
				level.add(ball3);

			}
		}
		//triple spray directed at the player
		else {

			//find the angle to fire at for centre first
			double xp = Game.player.x - shootLocX;
			double yp = Game.player.y - shootLocY;
			double angle1 = Math.atan2(yp, xp);

			//find the angle above the centered to fire at
			double xp2 = xp;
			double yp2 = yp - 30;
			double angle2 = Math.atan2(yp2, xp2);

			//find the angle below centered to fire at
			double xp3 = xp;
			double yp3 = yp + 30;
			double angle3 = Math.atan2(yp3, xp3);

			if (Game.player.x <= shootLocX) {

				MashBall ball = new MashBall(shootLocX, shootLocY, angle1, isNormalBallSpeed);
				ball.init(Game.level);
				level.add(ball);

				MashBall ball2 = new MashBall(shootLocX, shootLocY, angle2, isNormalBallSpeed);
				ball2.init(Game.level);
				level.add(ball2);

				MashBall ball3 = new MashBall(shootLocX, shootLocY, angle3, isNormalBallSpeed);
				ball3.init(Game.level);
				level.add(ball3);

			}
			//shooting right
			else {

				MashBall ball = new MashBall(shootLocX, shootLocY, angle1, isNormalBallSpeed);
				ball.init(Game.level);
				level.add(ball);

				MashBall ball2 = new MashBall(shootLocX, shootLocY, angle2, isNormalBallSpeed);
				ball2.init(Game.level);
				level.add(ball2);

				MashBall ball3 = new MashBall(shootLocX, shootLocY, angle3, isNormalBallSpeed);
				ball3.init(Game.level);
				level.add(ball3);

			}

		}

	}

	//spawns a mr mushroom minion aimed towards the player.
	public void spawnMushroom() {

		if (Game.player.x <= shootLocX) {
			Mr_mash mash = new Mr_mash(shootLocX, shootLocY, 1, false);
			mash.init(level);
			level.enemies.add(mash);
			level.add(mash);
		} else {
			Mr_mash mash = new Mr_mash(shootLocX, shootLocY, 0, false);
			mash.init(level);
			level.enemies.add(mash);
			level.add(mash);

		}
	}

	public void updateJumpRec() {
		jumpRec = getCollisionRec();
		jumpRec.x += 15;
		jumpRec.y += 15;
		jumpRec.width -= 15;
		jumpRec.height -= 15;

	}

}
