package jonny.main.entity.mob.characters;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Mob;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;

public class LarryLeak extends Mob {

	//general larry leak properties, alot copied from player class
	private int animTimer;
	public boolean activated = false;

	//sequence box var
	public static int seqXtrans = -50;
	public static int seqYtrans = -100;
	public static int seqWidth = 300;
	public static int seqHeight = 300;

	public static boolean runningSeq = false;
	public static boolean text1 = false;
	public static boolean text2 = false;

	//jumping
	private int jumpDelay = 2;
	private static final int JUMP_TIMER_START = 100; //jump for over a second
	private int jumpTimer = 0;
	private static final double JUMP_SPEED_START = 10; //12 is good
	private double jumpSpeed;
	private double jumpDecline = 0.965; //falls every update by a percentage

	//ladder
	public static boolean onLadder = false;
	public static final double ladderSpeed = 2;

	//sequence variables
	public static boolean up;

	public static int timer = 0; //15 secs

	public LarryLeak(int x, int y, Sprite sprite) {

		dir = 0;
		this.x = x;
		this.y = y;
		this.sprite = sprite;

		moveSpeed = 2.5;

		isLarry = true;

		//collision variables
		xTrans = -9;
		yTrans = -12;
		cWidth = 13;
		cHeight = 26;
	}

	public void update() {
		//reset basic stuff
		dir = 0;
		int xa = 0, ya = 0;
		animTimer++;
		if (animTimer == 75000) animTimer = 0;

		//for the text sequences
		if (onScreen) {
			Rectangle box = new Rectangle((int) x + seqXtrans, (int) y + seqYtrans, seqWidth, seqHeight);

			if (!activated) {
				if (entityCollision(Game.player, box)) {
					if (!text1) {
						Player.sequenceNum = 10;
						Player.sequence = true;
						activated = true;
					}

					//if second seq not activated but first has been, then can do second
					if (!text2 && text1) {
						Player.sequenceNum = 11;
						Player.sequence = true;
						activated = true;
						text2 = true;
					}

				}
			}

		}

		//running sequence
		if (activated && runningSeq) {
			timer++;

			if (timer <= 190) {
				xa += moveSpeed;
			}
			if (timer == 190) up = true;

			if (timer > 190 && timer < 310) {
				xa += moveSpeed;
			}

			if (timer > 310 && timer < 350) {
				onLadder = true;
				//if (timer == 370) up = true;
			}

			if (timer > 350 && timer < 400) {
				onLadder = false;
				xa += moveSpeed;
			}

			if (timer > 400 && timer < 440) {
				onLadder = true;
			}

			if (timer > 440 && timer < 450) {
				onLadder = false;
				xa += moveSpeed;
			}

			if (timer >= 450 && timer < 550) {
				if (timer == 450) up = true;
				xa -= moveSpeed;

			}

			if (timer >= 550 && timer < 840) {
				if (collision(0, 1)) up = true;
				if (collision(-3, 0)) xa -= 2;
				if (collision(-2, 0)) xa -= 1;
				if (collision(-1, 0)) {
					xa -= 1;
				} else {
					xa -= moveSpeed;
				}
			}

			if (timer >= 840 && timer < 900) {
				onLadder = true;
			}

			if (timer >= 900 && timer < 1320) {
				onLadder = false;
				xa += moveSpeed;
				if (timer == 1080) up = true;
			}

			if (timer == 1360) {
				runningSeq = false;
				activated = false;
				text1 = true;
			}

		}

		/////COPIED FROM PLAYER STUFF////
		//only goes up
		if (onLadder) {
			ya -= ladderSpeed;
		}

		////////////jumping///////////////////
		if (up && (collision(0, 1) || objectCollision(0, 1)) && jumpDelay == 0 && !onLadder) {
			normalJumping = true;
			jumpSpeed = JUMP_SPEED_START;
			jumpTimer = JUMP_TIMER_START;
			up = false;

		}

		//start delay if where colliding with floor
		if ((collision(0, 1) || objectCollision(0, 1)) && jumpDelay > 0) {
			jumpDelay--;
		}
		else {
			jumpDelay = 1;
		}

		//main jumping operations
		if (jumpTimer > 0) {
			jumpTimer--;
		}
		else {
			normalJumping = false;
		}

		if (normalJumping && !onLadder) {
			jumpSpeed = jumpSpeed * jumpDecline;
			ya -= jumpSpeed;

		}
		////////////////jumping///////////////

		int yTemp = (int) y;

		//movement
		if (xa != 0 || ya != 0) {
			move(xa, ya);
		}

		//gravity
		if (!onLadder) gravity(3);

		int yTemp2 = (int) y;

		//falling check
		if (yTemp < yTemp2) {
			falling = true;
		} else {
			falling = false;
		}

		/////COPIED FROM PLAYER STUFF////

	}
	public void render(Screen screen) {

		//stand still
		if (dir == 0) {
			if (animTimer % 30 > 15) {
				sprite = Sprite.larry_leak_forward;
			} else {
				sprite = Sprite.larry_leak_forward2;
			}
		}

		if (dir == 1) {
			if (animTimer % 30 > 15) {
				sprite = Sprite.larry_leak_right1;
			} else {
				sprite = Sprite.larry_leak_right2;
			}
		}

		if (dir == 3) {
			if (animTimer % 30 > 15) {
				sprite = Sprite.larry_leak_left1;
			} else {
				sprite = Sprite.larry_leak_left2;
			}
		}

		if (dir == 4) {
			sprite = Sprite.larry_leak_jump_right;
		}
		if (dir == 5) {
			sprite = Sprite.larry_leak_jump_left;
		}

		if (dir == 6) {
			sprite = Sprite.larry_leak_fall_right;
		}

		if (dir == 7) {
			sprite = Sprite.larry_leak_fall_left;
		}

		if (onLadder) {
			if (animTimer % 40 > 20) {
				sprite = Sprite.larry_leak_ladder1;
			} else {
				sprite = Sprite.larry_leak_ladder2;
			}
		}

		screen.renderObject((int) x - 20, (int) y - 20, sprite, this);
	}

}
