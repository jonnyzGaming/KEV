package jonny.main.entity.mob;

import java.awt.Color;

import jonny.main.Game;
import jonny.main.entity.Objects.BossCage;
import jonny.main.entity.Objects.Door;
import jonny.main.entity.mob.characters.BarryBeetroot;
import jonny.main.entity.mob.characters.BillyBrocoli;
import jonny.main.entity.mob.characters.Cabbage;
import jonny.main.entity.mob.characters.CabbageKing;
import jonny.main.entity.mob.characters.CabbageQueen;
import jonny.main.entity.mob.characters.CabbageSniper;
import jonny.main.entity.mob.characters.Caveman;
import jonny.main.entity.mob.characters.Cavewoman;
import jonny.main.entity.mob.characters.CryingCabbagekid;
import jonny.main.entity.mob.characters.HighCabbage;
import jonny.main.entity.mob.characters.HumanCaged;
import jonny.main.entity.mob.characters.LarryLeak;
import jonny.main.entity.mob.characters.PeterPickle;
import jonny.main.entity.mob.characters.TeenCabbage;
import jonny.main.entity.mob.characters.cabbageChef;
import jonny.main.entity.mob.characters.kevsWife;
import jonny.main.entity.mob.enemys.Bomb;
import jonny.main.entity.mob.enemys.EvilPotato;
import jonny.main.entity.mob.enemys.MashBoss;
import jonny.main.entity.mob.enemys.PotatoRebel;
import jonny.main.entity.projectiles.CarrotProjectile;
import jonny.main.entity.projectiles.PotatoPiece;
import jonny.main.entity.projectiles.Projectile;
import jonny.main.entity.projectiles.peePellet;
import jonny.main.graphics.Animations;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.input.KeyBoard;
import jonny.main.levels.BasicLevel;
import jonny.main.levels.Level;
import jonny.main.levels.backgrounds.LevelBackground;
import jonny.main.sound.Sound;

public class Player extends Mob {

	// general extra player properties
	private KeyBoard input;
	private int animTimer;
	public static boolean sequence = false;
	public static int sequenceNum = -1; // rogue to start
	public int sequenceStep = 0;
	public static final int textBoxDelayMax = 30;
	public int textBoxTimer = 0;
	public Sprite faceBoxSprite;

	// ultimate developer god mode boolean of doom
	public static boolean GodMode = false;
	public static boolean GodModeEnabled = true;

	// anim text box properties
	public static String text;
	public static String title;
	public static int xPos, yPos;
	public static Color col;

	// jumping variables
	private int jumpDelay = 2;
	private static final int JUMP_TIMER_START = 100; // jump for over a second
	private int jumpTimer = 0;
	private static final double JUMP_SPEED_START = 10; // 12 is good
	private double jumpSpeed;
	private double jumpDecline = 0.96; // falls every update by a percentage

	public static boolean forceJump = false;

	// double jump extra
	private static final int DOUBLE_TIMER_START = 40;
	private int doubleJumpTimer = 0;
	private static final int DOUBLE_JUMP_SPEED_START = 8;
	private double doubleJumpSpeed;
	private double doubleJumpDecline = 0.96;
	public static boolean canDoubleJump = false;
	// what height can you doublejump from
	private int yAvailable;

	// tiles statuses
	public static boolean inWater = false;
	public static boolean canWaterJump = false;
	public static boolean inDeathWater = false;
	public static final int WATER_TIMER = 900; // 15 32 SECONDS IN WATER ALLOWED
	public static double waterTimer = 0;
	public static double waterTimerDeath = 0;
	public static boolean hiddenTileCol = false;

	public static boolean onMovingTile = false;

	public static boolean onLadder = false;
	public static final double ladderSpeed = 2;

	// shooting variable
	public static boolean shootRight = false;

	// gravity variables(should increase exponentially according to time)
	public static double Normal_Gravity_Speed = 3;
	public static final int Water_Gravity_Speed = 1;

	public static final double gravityIncreaseSpeedStart = 0.01666;
	public static final double rateOfIncreaseSpeedStart = 1.02;

	public static double gravityIncreaseSpeed = 0.01666; // 1 per sec
	public static double rateOfIncreaseSpeed = 1.02;

	public static boolean shouldRound = false; // set in mob gravity code

	// weapon selected.
	public static enum weapon {
		CARROT_GUN, POTATO_GUN, SHOTGUN;
	}

	public static weapon WEAPON_SELECTED = weapon.CARROT_GUN;

	// attributes for weapons
	public static boolean CURRENTLY_SHOOTING = false;

	// carrot gun is 6dmg per sec(calculated from rates+ damage in their
	// classes)
	private static final int carrot_rate_max = 10;
	private static int carrot_rate = 10;

	// potato gun is 8dmg per sec
	private static final int potato_rate_max = 8;
	private static int potato_rate = 8;

	// potato gun is about 10 dmg per shot(at full on close range)
	private static final int shotgun_rate_max = 60;
	private static int shotgun_rate = 60;

	// shop prices
	// shop unlock variables(make sure all false to start)
	public static boolean DOUBLE_JUMP_UNLOCKED = false;
	public static boolean POTATO_GUN_UNLOCKED = false;
	public static boolean SHOTGUN_UNLOCKED = false;
	public static boolean TOP_HAT_UNLOCKED = false;
	public static boolean ARMOR_UPGRADE1 = false;
	public static boolean ARMOR_UPGRADE2 = false;

	public static boolean CABBAGE_BOMB_UNLOCKED = false;
	public static boolean PEA_SHOOTER_UNLOCKED = false;
	public static boolean BEATROOT_SHOTGUN_UNLOCKED = false;

	// prices
	public static final int double_jump_price = 15;
	public static final int potato_gun_price = 75;
	public static final int shotgun_price = 150;
	public static final int top_hat_price = 20;
	public static final int armor1_price = 50;
	public static final int armor2_price = 75;

	public static final int total_in_shop = double_jump_price + potato_gun_price + shotgun_price + top_hat_price
			+ armor1_price + armor2_price;

	public static final int cabbage_bomb_price = 50;
	public static final int pea_shooter_price = 100; // have toxic poisen on
													 // them
	public static final int beatroot_shotgun_price = 600;

	// death counter
	public static int deaths = 0;
	public static boolean vunerable = true; //disbales ability to die if false

	// coins + health + armor
	public static double health = 10;
	public static int coins = 0;
	public static int totalCoinCount = 0;

	// enemy collision variables
	public static boolean inMash = false;
	public static boolean inSlime = false;
	public static double inMashSpeed;

	public static boolean flinching;
	public static int flinchActivated = 0;
	public static int flinchTimer = 0;
	public static final int flinchTime = 120;

	// movement variables
	public static boolean normalMovement = true;
	public static double normalMovementSpeed;

	public static boolean drunkMovement = false;
	public static double drunkMovementSpeed;

	public static boolean movementDisabled = false; // used in keyboard class

	// extra variables for text boxes and other stuff
	int random = 0;
	public static boolean gotMashBossTokens = false;
	public static boolean gotLarryTokens = false;
	public static boolean villageAttackStarted = false;

	public static int xSpawn, ySpawn;

	public Player(KeyBoard input) {
		this.input = input;
		sprite = Sprite.kev_forward; // initial sprite

		x = 0;
		y = 0;

		// set the speeds (currently must be integers)
		normalMovementSpeed = 3;
		moveSpeed = normalMovementSpeed; // should set to drunk at start then
										 // change after asprin bought

		drunkMovementSpeed = 2;
		inMashSpeed = 1;

		// collision variables
		cWidth = 7;
		cHeight = 13;
		xTrans = -4;
		yTrans = -5;

		isPlayer = true;

	}

	boolean levelChange = false;

	public void update() {

		// possible level change code for later
		/*
		 * if (levelChange == false && x > 700) { Game.level = new
		 * BasicLevel("/Levels/test_level.png", 0); this.init(Game.level);
		 * 
		 * x = Player.xSpawn; y = Player.ySpawn; levelChange = true; return; }
		 */

		// System.out.println(moveSpeed);

		// reset basic stuff
		// if (!CURRENTLY_SHOOTING)
		dir = 0;
		int xa = 0, ya = 0;
		deathTile = false;

		animTimer++;
		if (animTimer == 75000) animTimer = 0;

		// developer god mode(cancels everything else out)
		if (GodModeEnabled) {
			if (GodMode) {
				if (input.left) x -= 8;
				if (input.right) x += 8;
				if (input.up) y -= 8;
				if (input.down) y += 8;

				if (input.G && !input.triggeredGod) {
					input.triggeredGod = true;
					GodMode = false;
				}

				return;
			}
			if (input.G & !input.triggeredGod) {
				GodMode = true;
				input.triggeredGod = true;
			}
		}

		if (!sequence) {// sequence bracket start///////////////////////

			// detect move input normally
			if (input.left && !inWater) {
				xa -= moveSpeed;
				LevelBackground.xM -= LevelBackground.mountainMoveSpeed;
				LevelBackground.xB -= LevelBackground.blobMoveSpeed;

				shootRight = false;
			}
			if (input.right && !inWater) {
				xa += moveSpeed;
				LevelBackground.xM += LevelBackground.mountainMoveSpeed;
				LevelBackground.xB += LevelBackground.blobMoveSpeed;
				shootRight = true;
			}
			// for mash
			if (input.up && inMash && !normalJumping) {
				y -= 3;
			}

			// for water
			if (input.up && inWater == true) ya -= Water_Gravity_Speed * 2;
			if (input.left && inWater == true) {
				xa -= Water_Gravity_Speed * 2;
				shootRight = false;
			}
			if (input.right && inWater == true) {
				xa += Water_Gravity_Speed * 2;
				shootRight = true;
			}

			// for ladders
			if (input.up && onLadder) {
				ya -= ladderSpeed;
			}
			if (input.down && onLadder) {
				ya += ladderSpeed;
			}

			// /////////FIRING///////////////////
			// detect fire input
			if (input.space) {
				if (WEAPON_SELECTED == weapon.CARROT_GUN && carrot_rate == 0) {
					carrot_rate = carrot_rate_max;
					int dir = 0;
					if (shootRight) dir = 1;
					if (!shootRight) dir = -1;
					Projectile p = new CarrotProjectile((int) x, (int) y, dir);
					p.init(level);
					level.add(p);
					Sound.fiff.play(false);
				}

				if (WEAPON_SELECTED == weapon.POTATO_GUN && potato_rate == 0) {
					potato_rate = potato_rate_max;
					int dir = 0;
					if (shootRight) dir = 1;
					if (!shootRight) dir = -1;
					Projectile p = new PotatoPiece((int) x, (int) y, dir);
					p.init(level);
					level.add(p);
					Sound.pwut.play(false);
				}

				if (WEAPON_SELECTED == weapon.SHOTGUN && shotgun_rate == 0) {
					shotgun_rate = shotgun_rate_max;
					double dir = 0;
					if (shootRight) dir = 0;
					if (!shootRight) dir = Math.toRadians(180);

					Projectile p = new peePellet((int) x + 5, (int) y, dir);
					p.init(level);
					level.add(p);

					Projectile p2 = new peePellet((int) x + 5, (int) y, dir + Math.toRadians(4));
					p2.init(level);
					level.add(p2);

					Projectile p3 = new peePellet((int) x + 5, (int) y, dir + Math.toRadians(-4));
					p3.init(level);
					level.add(p3);

					Sound.shotgun.play(false);
				}
			}

			// decrement rate of fire for weapons
			if (WEAPON_SELECTED == weapon.CARROT_GUN) {
				if (carrot_rate > 0) {
					carrot_rate--;
				}
			}

			if (WEAPON_SELECTED == weapon.POTATO_GUN) {
				if (potato_rate > 0) {
					CURRENTLY_SHOOTING = true;
					potato_rate--;
				} else {
					CURRENTLY_SHOOTING = false;
				}
			}

			if (WEAPON_SELECTED == weapon.SHOTGUN) {
				if (shotgun_rate > 0) {
					shotgun_rate--;
					CURRENTLY_SHOOTING = true;
				} else {
					CURRENTLY_SHOOTING = false;
				}
			}

			// /////////FIRING///////////////////

			// ////number keys to switch weapons/////

			if (input.one) {
				Player.WEAPON_SELECTED = weapon.CARROT_GUN;
			}

			if (input.two && POTATO_GUN_UNLOCKED) {
				Player.WEAPON_SELECTED = weapon.POTATO_GUN;
			}

			if (input.three && SHOTGUN_UNLOCKED) {
				Player.WEAPON_SELECTED = weapon.SHOTGUN;
			}

			// ////number keys to switch weapons/////

			// ///////JUMPING////////
			// check for jumping if we can and start the jump timer(if were
			// colliding with a solid tile or object)
			// normal jump
			int amount = 1;
			if (onMovingTile) amount = 3; // to be safe
			if (input.up && !input.triggeredJump && (collision(0, amount) || objectCollision(0, amount))
					&& jumpDelay == 0 && !doubleJumping && !inWater && !onLadder || (forceJump)) {
				normalJumping = true;
				jumpSpeed = JUMP_SPEED_START;
				jumpTimer = JUMP_TIMER_START;
				input.triggeredJump = true;

				forceJump = false;

			}
			// water jump
			else if (input.up && canWaterJump == true) {
				normalJumping = true;
				jumpSpeed = JUMP_SPEED_START - 2;
				jumpTimer = JUMP_TIMER_START;
			}

			// mash boss jump
			else if (input.up && inMash && !input.triggeredJump && (!MashBoss.jumpRec.intersects(getCollisionRec()))
					&& !doubleJumping && !inWater && !onLadder) {
				normalJumping = true;
				jumpSpeed = JUMP_SPEED_START - 2;
				jumpTimer = JUMP_TIMER_START;
				input.triggeredJump = true;
			}

			// start delay if where colliding with floor
			if ((collision(0, amount) || objectCollision(0, amount)) && jumpDelay > 0) {
				jumpDelay--;
			} else {
				jumpDelay = 1;
			}

			// main jumping operations
			if (jumpTimer > 0) {

				// for double jump if its unlocked
				if (jumpTimer == 69 && DOUBLE_JUMP_UNLOCKED) {
					canDoubleJump = true;
					yAvailable = (int) y + 70;
				}

				jumpTimer--;

			} else {
				normalJumping = false;
			}

			if (normalJumping && !onLadder) {
				jumpSpeed = jumpSpeed * jumpDecline;
				ya -= jumpSpeed;

			}

			// double jump extra
			if (doubleJumpTimer > 0) {
				doubleJumping = true;
				doubleJumpTimer--;
			} else {
				doubleJumping = false;
			}

			if (doubleJumping && !onLadder && !inMash) {
				doubleJumpSpeed = doubleJumpSpeed * doubleJumpDecline;
				ya -= doubleJumpSpeed;

			}

			// ///////JUMPING////////

			// //////WATER STATUS/////

			if (!inWater) {
				waterTimer = 0;
				inDeathWater = false;
				waterTimerDeath = 0;
			}

			// start the timer if in water
			if (inWater && !inDeathWater && waterTimer == 0) {
				waterTimer = WATER_TIMER;
			}

			if (inWater && waterTimer > 0) {
				waterTimer--;
			}

			if (inWater && waterTimer == 0) {
				inDeathWater = true;
			}

			if (inDeathWater && waterTimerDeath == 0) {
				waterTimerDeath = WATER_TIMER / 2; // 16 secs till death
			}

			if (inDeathWater) {
				waterTimerDeath--;
				if (!Sound.drowning.isPlaying) {
					Sound.drowning.isPlaying = true;
					Sound.drowning.play(true);
				}
			} else {
				Sound.drowning.isPlaying = false;
				Sound.drowning.stop();
			}

			// damage the player once every second in death water
			if (inWater) {
				if (inDeathWater && waterTimerDeath / 45 == 10) health -= 1;
				if (inDeathWater && waterTimerDeath / 45 == 9) health -= 1;
				if (inDeathWater && waterTimerDeath / 45 == 8) health -= 1;
				if (inDeathWater && waterTimerDeath / 45 == 7) health -= 1;
				if (inDeathWater && waterTimerDeath / 45 == 6) health -= 1;
				if (inDeathWater && waterTimerDeath / 45 == 5) health -= 1;
				if (inDeathWater && waterTimerDeath / 45 == 4) health -= 1;
				if (inDeathWater && waterTimerDeath / 45 == 3) health -= 1;
				if (inDeathWater && waterTimerDeath / 45 == 2) health -= 1;
				if (inDeathWater && waterTimerDeath / 45 == 1) health -= 1;
				if (inDeathWater && waterTimerDeath / 45 == 0) health -= 1;
			}
			// //////WATER STATUS/////

			// ///////MOVING//////////

		} // sequence bracket end//////////////////////////////////////
		int yTemp = (int) y;

		// if we can move then move
		if (xa != 0 || ya != 0) {
			move(xa, ya);
		}

		// ///////MOVING///////////

		// ////GRAVITY/////////
		// gravity is applied at ALL times (even when jumping)(but not when on
		// floor)
		if (falling && !onMovingTile) {

			gravityIncreaseSpeed = gravityIncreaseSpeed * rateOfIncreaseSpeed;

			if (Normal_Gravity_Speed <= 6) // limit max increase to 6
				Normal_Gravity_Speed += gravityIncreaseSpeed;

		} else {
			Normal_Gravity_Speed = 3;
			gravityIncreaseSpeed = gravityIncreaseSpeedStart;
			rateOfIncreaseSpeed = rateOfIncreaseSpeedStart;
			shouldRound = true;
		}

		// if were near the floor or moving plat then we shouldnt be dealing
		// with doubles due to buggyness
		if (collision(0, 0, cWidth + 6, cHeight + 6, xTrans - 3, yTrans - 3) || objectCollision(0, 0, cWidth + 6,
				cHeight + 6, xTrans - 3, yTrans - 3)) {
			shouldRound = true;
			y = Math.floor(y);
		} else {
			shouldRound = false;
		}

		if (!inWater && !onLadder) {
			if (inMash) {
				gravity(1);
			} else {
				gravity(Normal_Gravity_Speed, shouldRound);
			}
		} else if (!onLadder) {
			gravity(Water_Gravity_Speed);
		}
		// ////GRAVITY/////

		// //////FALLING AND DOUBLE JUMP/////////
		int yTemp2 = (int) y;
		if (yTemp < yTemp2) {
			falling = true;

			// double jump only if falling in range
			if (input.up & !input.triggeredJump && canDoubleJump == true && y < yAvailable && !collision(0, 1)
					&& !inMash) {
				doubleJumping = true;
				normalJumping = false;
				doubleJumpSpeed = DOUBLE_JUMP_SPEED_START;
				doubleJumpTimer = DOUBLE_TIMER_START;
			}
		} else {
			falling = false;
			canDoubleJump = false;
		}
		// /////FALLING AND DOUBLE JUMP//////////

		// /////DEATH///////////

		//if killed then update death stats.(can die by death tile, 0 health, and being inside a tile.
		if (deathTile || (health <= 0 && vunerable) || (collision(0, 0) && !onLadder)) {

			// reset mash boss stats on death if we died to him
			if (!MashBoss.deathSequenceActivated) {
				MashBoss.combatActivated = false;
				MashBoss.spawnActivated = true;

				if (MashBoss.sequence1Activated) MashBoss.returnSequenceActivated = true;

				BossCage.activated = false;
				BossCage.cageClosed = false;
				BossCage.cageOpened = true;
			}

			//reset for potato boss aswell
			if (EvilPotato.isAtPotatoBoss) {
				BossCage.activated = false;
				BossCage.cageClosed = false;
				BossCage.cageOpened = true;

				//reset the evil potato.
				EvilPotato.stage1 = true;
				EvilPotato.stage2 = false;
				EvilPotato.stage3 = false;
				EvilPotato.stage4 = false;
				EvilPotato.combatActivated = false;
				EvilPotato.waitingForPlayerReturn = true;
				EvilPotato.shootingStill = false;
				EvilPotato.shootingStillTimer = 0;
				EvilPotato.layingBomb = false;
				EvilPotato.layingBombTimer = 0;
				EvilPotato.chargeAttack = false;
				EvilPotato.readyToChargeTimer = 0;
				EvilPotato.chargeTimer = 0;

				//remove all the current bombs that may have been dropped by the boss.
				Bomb.removeAll();
			}

			//core death stuff
			death(xSpawn, ySpawn);
			health = 10;
			if (deathTile) Sound.squish.play(false);
			if (ARMOR_UPGRADE1) health = 15;
			if (ARMOR_UPGRADE2) health = 20;
		}

		// /////DEATH///////////

		// ////Enemy/moveSpeed states//////

		if (normalMovement) {
			moveSpeed = normalMovementSpeed;
		}

		if (drunkMovement) {
			moveSpeed = drunkMovementSpeed;
		}

		if (inMash) {
			moveSpeed = inMashSpeed;
			if (inMash && !inSlime) {
				health -= 0.05;
			} else {
				health -= 0.08;
			}

			inMash = false;
			inSlime = false;
			normalMovement = true;
		}

		// if the player is in a fire tile then damage him alot.
		if (fireTile) {
			health -= 0.06;
		}

		// flinching
		// start by activating the flinch(can only be activated once before
		// reset
		if (flinchActivated == 1) {
			flinching = true;
			flinchTimer = 0;
			flinchActivated = 2;
		}

		if (flinching) {
			flinchTimer++;

			if (flinchTimer >= flinchTime) {
				flinchTimer = 0;
				flinching = false;
				flinchActivated = 0;
			}
		}

		// ///Enemy/moveSpeed states///////

		// ///update the text box animations
		// timer
		if (textBoxTimer < textBoxDelayMax) {
			textBoxTimer++;
		}

		// //////////////RESETS//////////////
		Player.onMovingTile = false; // reset(leave this as is its painfully
									 // annoying)
									 // //////////////RESETS//////////////
	}

	public void render(Screen screen) {

		// ///player animations////
		if (dir == 0) {
			if (animTimer % 30 > 15) {
				if (WEAPON_SELECTED == weapon.CARROT_GUN) {
					sprite = Sprite.kev_forward;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_forward_hat;
				}
				if (WEAPON_SELECTED == weapon.POTATO_GUN) {
					if (CURRENTLY_SHOOTING && shootRight) {
						sprite = Sprite.kev_right_shooting_pot;
						if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_right_shooting_pot_hat;

					} else if (CURRENTLY_SHOOTING && !shootRight) {
						sprite = Sprite.kev_left_shooting_pot;
						if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_left_shooting_pot_hat;
					} else {
						sprite = Sprite.kev_forward_pot;
						if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_forward_pot_hat;
					}

				}

				if (WEAPON_SELECTED == weapon.SHOTGUN) {
					if (CURRENTLY_SHOOTING && shootRight) {
						sprite = Sprite.kev_right_shooting_shotgun;
						if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_right_shooting_shotgun_hat;

					} else if (CURRENTLY_SHOOTING && !shootRight) {
						sprite = Sprite.kev_left_shooting_shotgun;
						if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_left_shooting_shotgun_hat;
					} else {
						sprite = Sprite.kev_forward_shotgun;
						if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_forward_shotgun_hat;
					}

				}

			} else {
				if (WEAPON_SELECTED == weapon.CARROT_GUN) {
					sprite = Sprite.kev_forward2;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_forward2_hat;
				}
				if (WEAPON_SELECTED == weapon.POTATO_GUN) {
					if (CURRENTLY_SHOOTING && shootRight) {
						sprite = Sprite.kev_right_shooting_pot;
						if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_right_shooting_pot_hat;
					} else if (CURRENTLY_SHOOTING && !shootRight) {
						sprite = Sprite.kev_left_shooting_pot;
						if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_left_shooting_pot_hat;
					} else {
						sprite = Sprite.kev_forward2_pot;
						if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_forward2_pot_hat;
					}
				}

				if (WEAPON_SELECTED == weapon.SHOTGUN) {
					if (CURRENTLY_SHOOTING && shootRight) {
						sprite = Sprite.kev_right_shooting_shotgun;
						if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_right_shooting_shotgun_hat;
					} else if (CURRENTLY_SHOOTING && !shootRight) {
						sprite = Sprite.kev_left_shooting_shotgun;
						if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_left_shooting_shotgun_hat;
					} else {
						sprite = Sprite.kev_forward2_shotgun;
						if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_forward2_shotgun_hat;
					}
				}

			}
		}

		if (dir == 1) {
			if (animTimer % 30 > 15) {
				if (WEAPON_SELECTED == weapon.CARROT_GUN) {
					sprite = Sprite.kev_right;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_right_hat;
				}
				if (WEAPON_SELECTED == weapon.POTATO_GUN) {
					sprite = Sprite.kev_right_pot;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_right_pot_hat;
				}

				if (WEAPON_SELECTED == weapon.SHOTGUN) {
					sprite = Sprite.kev_right_shotgun;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_right_shotgun_hat;
				}
			} else {
				if (WEAPON_SELECTED == weapon.CARROT_GUN) {
					sprite = Sprite.kev_right2;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_right2_hat;
				}
				if (WEAPON_SELECTED == weapon.POTATO_GUN) {
					sprite = Sprite.kev_right2_pot;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_right2_pot_hat;
				}
				if (WEAPON_SELECTED == weapon.SHOTGUN) {
					sprite = Sprite.kev_right2_shotgun;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_right2_shotgun_hat;
				}

			}
		}

		if (dir == 3) {
			if (animTimer % 30 > 15) {
				if (WEAPON_SELECTED == weapon.CARROT_GUN) {
					sprite = Sprite.kev_left;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_left_hat;
				}
				if (WEAPON_SELECTED == weapon.POTATO_GUN) {
					sprite = Sprite.kev_left_pot;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_left_pot_hat;
				}

				if (WEAPON_SELECTED == weapon.SHOTGUN) {
					sprite = Sprite.kev_left_shotgun;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_left_shotgun_hat;
				}
			} else {
				if (WEAPON_SELECTED == weapon.CARROT_GUN) {
					sprite = Sprite.kev_left2;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_left2_hat;
				}
				if (WEAPON_SELECTED == weapon.POTATO_GUN) {
					sprite = Sprite.kev_left2_pot;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_left2_pot_hat;
				}
				if (WEAPON_SELECTED == weapon.SHOTGUN) {
					sprite = Sprite.kev_left2_shotgun;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_left2_shotgun_hat;
				}

			}
		}

		if (dir == 4) {
			if (WEAPON_SELECTED == weapon.CARROT_GUN) {
				sprite = Sprite.kev_jump_right;
				if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_jump_right_hat;
			}
			if (WEAPON_SELECTED == weapon.POTATO_GUN) {
				sprite = Sprite.kev_jump_right_pot;
				if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_jump_right_pot_hat;
			}
			if (WEAPON_SELECTED == weapon.SHOTGUN) {
				sprite = Sprite.kev_jump_right_shotgun;
				if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_jump_right_shotgun_hat;
			}
		}

		if (dir == 5) {
			if (WEAPON_SELECTED == weapon.CARROT_GUN) {
				sprite = Sprite.kev_jump_left;
				if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_jump_left_hat;
			}
			if (WEAPON_SELECTED == weapon.POTATO_GUN) {
				sprite = Sprite.kev_jump_left_pot;
				if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_jump_left_pot_hat;
			}
			if (WEAPON_SELECTED == weapon.SHOTGUN) {
				sprite = Sprite.kev_jump_left_shotgun;
				if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_jump_left_shotgun_hat;
			}
		}

		if (dir == 6) {
			if (WEAPON_SELECTED == weapon.CARROT_GUN) {
				sprite = Sprite.kev_fall_right;
				if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_fall_right_hat;

			}
			if (WEAPON_SELECTED == weapon.POTATO_GUN) {
				sprite = Sprite.kev_fall_right_pot;
				if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_fall_right_pot_hat;

			}
			if (WEAPON_SELECTED == weapon.SHOTGUN) {
				sprite = Sprite.kev_fall_right_shotgun;
				if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_fall_right_shotgun_hat;

			}
		}

		if (dir == 7) {
			if (WEAPON_SELECTED == weapon.CARROT_GUN) {
				sprite = Sprite.kev_fall_left;
				if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_fall_left_hat;
			}
			if (WEAPON_SELECTED == weapon.POTATO_GUN) {
				sprite = Sprite.kev_fall_left_pot;
				if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_fall_left_pot_hat;
			}
			if (WEAPON_SELECTED == weapon.SHOTGUN) {
				sprite = Sprite.kev_fall_left_shotgun;
				if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_fall_left_shotgun_hat;
			}
		}

		if (onLadder) {
			if (animTimer % 40 > 20) {
				if (WEAPON_SELECTED == weapon.CARROT_GUN) {
					sprite = Sprite.kev_ladder1;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_ladder1_hat;
				}
				if (WEAPON_SELECTED == weapon.POTATO_GUN) {
					sprite = Sprite.kev_ladder1_pot;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_ladder1_pot_hat;
				}
				if (WEAPON_SELECTED == weapon.SHOTGUN) {
					sprite = Sprite.kev_ladder1_shotgun;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_ladder1_shotgun_hat;
				}
			} else {
				if (WEAPON_SELECTED == weapon.CARROT_GUN) {
					sprite = Sprite.kev_ladder2;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_ladder2_hat;
				}
				if (WEAPON_SELECTED == weapon.POTATO_GUN) {
					sprite = Sprite.kev_ladder2_pot;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_ladder2_pot_hat;
				}
				if (WEAPON_SELECTED == weapon.SHOTGUN) {
					sprite = Sprite.kev_ladder2_shotgun;
					if (TOP_HAT_UNLOCKED) sprite = Sprite.kev_ladder2_shotgun_hat;
				}
			}
		}

		// //player animation/////

		// /status bars////
		// health
		for (int i = 0; i < Math.ceil(health); i++) {
			if (i <= 9) screen.renderBasicSprite(30 + (i * 10), 1, Sprite.heart);
			if (i > 9) screen.renderBasicSprite(30 + ((i - 10) * 10), 11, Sprite.armor_heart);
		}

		// carrot icon
		if (!ARMOR_UPGRADE1) screen.renderBasicSprite(30, 12, Sprite.coin);
		if (ARMOR_UPGRADE1) screen.renderBasicSprite(30, 22, Sprite.coin);

		// drowning gauge
		if (inWater) {
			for (int i = 0; i < Math.ceil(waterTimer / 60 * 0.52) + 1; i++) {
				if (i != 0) {
					screen.renderBasicSprite(5 + (i * 10), 155, Sprite.drowningGauge);
				} else {
					screen.renderBasicSprite(5 + (i * 10), 155, Sprite.deathDrowningGauge);
				}

			}
		}

		// render all the weapon icons as their unlocked,
		screen.renderBasicAccurateSprite(255, 15, Sprite.carrot_icon);
		if (WEAPON_SELECTED == weapon.CARROT_GUN) screen.renderBasicAccurateSprite(255, 15, Sprite.green_box);

		if (POTATO_GUN_UNLOCKED) screen.renderBasicAccurateSprite(255, 33, Sprite.potato_gun_icon);
		if (WEAPON_SELECTED == weapon.POTATO_GUN) screen.renderBasicAccurateSprite(255, 33, Sprite.green_box);
		if (SHOTGUN_UNLOCKED) screen.renderBasicAccurateSprite(255, 51, Sprite.shotgun_icon);
		if (WEAPON_SELECTED == weapon.SHOTGUN) screen.renderBasicAccurateSprite(255, 51, Sprite.green_box);

		// //status bars////

		// when flinching render the player periodically
		if (flinching) {
			if (flinchTimer % 30 >= 15) {
				screen.renderObject((int) x - 12, (int) y - 12, sprite);
				return;
			} else {
				return;
			}
		}
		// render the player starting at top right corner
		screen.renderObject((int) x - 12, (int) y - 12, sprite);

		// ////////////////////////////////////////////////////////////////////
		// ///////////////////////////////////////////////////////////////
		// ////////////////////////////////////////////////////////////////////
		// ///////////////////////////////////////////////////////////////
		// ///////////////////TEXT BOX SEQUENCES////////////////

		// A starting null sequence.
		if (sequenceNum == -1 && sequence) {
		}

		// start of level 1 sequence
		if (sequenceNum == 0 && sequence) {

			if (sequenceStep == 0) {
				textBoxTimer = 0;

				BasicTextBox("Oh my, I must have gotten drunk last night, better go find the kids...", 22);

				sequenceStep = 1;

			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);
		}

		// mr mushroom sequence
		if (sequenceNum == 1 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Hello Kev, great party last night eh, wanna drink?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"No thanks Mr mushroom I'm good. Anyway, I forgot what happened last night,\nhave you seen the kids?",
						22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Yeah I saw them pass through with a potato chap earlier,he seemed\naggressive.", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Why didn't you stop him?!", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Drugs bro.", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("...", 22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;
			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// mash boss sequence
		if (sequenceNum == 2 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Hey, what are doing in my lair!? get out.", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"I'm trying to find out who took my kids, you seen a dodgy looking potato come\nthrough here?",
						22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I ain't telling you nuthin, prepare to be mashed!", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Bring it!", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				// activate combat sequence after finnishing and stop talking
				MashBoss.combatActivated = true;
				Sound.mashTalking.stop();
			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// mash boss return after death sequence
		if (sequenceNum == 3 && sequence) {

			int random = Game.rand.nextInt(5);
			// sequence steps
			if (sequenceStep == 0) {

				if (random == 0) {
					BasicTextBox("Back for more!? Good, I'm rather peckish at this hour.", 22);
				} else if (random == 1) {
					BasicTextBox("Come back for another round!? Ill drown you in my own body fat!", 22);
				} else if (random == 2) {
					BasicTextBox("Come back to die!? Don't worry, I'll let my kids feast on your corpse!", 22);
				} else if (random == 3) {
					BasicTextBox("Fight like a man and stop re-spawning!? I'm having breakfast soon.", 22);
				} else if (random == 4) {
					BasicTextBox("We meet again! For the LAST TIME!!!:[]", 22);
				}

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				if (random == 0) {
					BasicTextBox("Well let me give you a slice of my fist!! Literally", 22);
				} else if (random == 1) {
					BasicTextBox("Good luck with that, ill just re-spawn anyway!", 22);
				} else if (random == 2) {
					BasicTextBox("THATS RATHER EXTREME SIR!", 22);
				} else if (random == 3) {
					BasicTextBox("I'm afraid breakfast is about to be SERVED!! Period.", 22);
				} else if (random == 4) {
					BasicTextBox("FOR SPARTAAAAA!", 22);
				}

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				// activate combat sequence after finnishing
				MashBoss.combatActivated = true;
				Sound.mashTalking.stop();
			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// mash boss seq2
		if (sequenceNum == 4 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("NOOOOOOOO!!!! Please don't kill me, I have a family.", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Tell me were the potato went and I might consider it!", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("He was heading towards the cabbage village, here take these cabbage tokens.\nPlease!",
						22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Received 5 cabbage tokens.", 22, Color.GREEN);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				MashBoss.combatActivated = false;
				Sound.mashTalking.stop();

				gotMashBossTokens = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cave MAN sequence 1
		if (sequenceNum == 5 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Have you seen my pickaxe?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("No, have you seen my kids?", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Huh, were am I?", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("...", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				Caveman.delayTimer = 50;
				Caveman.activated = false;
				Caveman.sequence1 = false;
				Caveman.sequence2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cave MAN sequence 2
		if (sequenceNum == 6 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("You got my pickaxe yet?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Not just yet.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				Caveman.delayTimer = 50;
				Caveman.activated = false;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cave MAN sequence 3
		if (sequenceNum == 7 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("You got my pickaxe yet?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Yes, here it is.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Thanks, take this.", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Received " + BasicLevel.cavemanReward + " carrots.", 22, Color.green);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				coins += BasicLevel.cavemanReward;
				totalCoinCount += BasicLevel.cavemanReward;

				// final cave man sequence
				Caveman.activated = true;
				Caveman.sequence1 = false;
				Caveman.sequence2 = false;
				Caveman.sequence3 = false;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cave WOMAN sequence 1
		if (sequenceNum == 8 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Why hello there, have you seen my husband?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Maybe.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Oh, well if you find him then give him this pickaxe, he's lost without it.", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Received pickaxe.", 22, Color.green);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				Cavewoman.activated = false;
				Cavewoman.delayTimer = 50;
				Cavewoman.sequence1 = false;
				Cavewoman.sequence2 = true;

				Caveman.sequence3 = true;
				Caveman.sequence2 = false;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cave WOMAN sequence 2
		if (sequenceNum == 9 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Fancy a rock cake?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("No thanks...", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				Cavewoman.activated = false;
				Cavewoman.delayTimer = 50;
				Cavewoman.sequence1 = false;
				Cavewoman.sequence2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// larry leak sequence 1
		if (sequenceNum == 10 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Oh hi Kev, what are you doing here?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Where's my money Larry, pay up.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Dude you gotta give me more time, I got bills to pay...", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("You've had enough time, I'm not waiting any longer!", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("You'll have to catch me first!", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				// activate running seq at end.
				LarryLeak.runningSeq = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// larry leak sequence 2
		if (sequenceNum == 11 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Larry! You've got no were to run, give up.", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Why you so fast Kev? Anyway, you got me. What do you want?", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I'm looking for the potato who stole my kids, you seen him?", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Not personally, but I heard he was heading towards cabbage village,\ntake these cabbage tokens.",
						22);
				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("And the money...", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I'll give you all I got, I guess I'm gonna have to sell the house. :[", 22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Received 5 cabbage key tokens and " + BasicLevel.larryReward + " carrots.", 22,
						Color.green);

				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				// give money etc
				coins += BasicLevel.larryReward;
				totalCoinCount += BasicLevel.larryReward;
				gotLarryTokens = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// unwanted sprout sequence
		if (sequenceNum == 12 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Hey sprout, what's your purpose in this game?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Huh? all my friends say I stink, will you be my friend?", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Well...ah...It seems I have to leave, bills to pay and such. Bye!", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("But I'll pay you!!", 22);
				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Oh, Why didn't you say so! Hows my new best friend doing today?", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Received " + BasicLevel.sproutReward + " carrots.", 22, Color.green);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				// give money etc
				coins += BasicLevel.sproutReward;
				totalCoinCount += BasicLevel.sproutReward;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// black eye pees dad sequence
		if (sequenceNum == 13 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Hello Kev, can you check on my kids? They said they were out playing in the\nyard.", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Ah. There just outside now!. I can guarantee they are completely safe\nand well (gulp).",
						22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"That's great Kev. Anyway, I seem to be missing my knife collection,if you find it\nbe sure to pop round would you.",
						22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Will do black eye. Anyway I better be off, I've got kid problems of my own.\nBye.", 22);
				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Fair-well young warrior, good luck on your adventures.", 22);
				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Thanks!", 22);
				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// kevs wife sequence
		if (sequenceNum == 14 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Kev! Where have you been, I've been worried sick. Do you know where\nthe kids are?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("There fine Susan, I'm on my way to get them just now. ", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("FINE? I'm sick of you getting drunk all the time, you've really done it this time.", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Jeez stop worrying woman, the kids can handle themselves.I didn't\ncombat train them for no reason...",
						22);
				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Well you better hope so for your sake! I'm not taking any more of your shit Kev,\nTHIS IS THE LAST TIME.  ",
						22);
				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("(gulp)Ok Susan, I'll be back in a jiffy!", 22);
				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("You better be!!!", 22);
				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				kevsWife.activated = false;
				kevsWife.delayTimer = 50;
				kevsWife.sequence1 = false;
				kevsWife.sequence2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// KEVS wife sequence 2
		if (sequenceNum == 15 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Did you find the kids yet?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Erm, not just yet.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("WELL GET A MOVE ON THEN!...", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				kevsWife.activated = false;
				kevsWife.delayTimer = 50;
				kevsWife.sequence1 = false;
				kevsWife.sequence2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage guard sequence
		if (sequenceNum == 16 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Hey cabbage, can you let me through into the village?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("All tourists must pay the fee, 10 cabbage tokens will do.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("What's stopping me from just killing you can taking the key?", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Oh there's erm...snipers, yes, there's snipers everywhere. You wouldn't\neven stand a chance.",
						22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Alright I believe you, better go get some tokens then.", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				Cabbage.activated = false;
				Cabbage.delayTimer = 50;
				Cabbage.sequence1 = false;
				Cabbage.sequence2 = true;
				Cabbage.sequence1ActivatedOnce = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage guard sequence 2
		if (sequenceNum == 17 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Oh, your back, Have you got the tokens?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Not just yet", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Well go find some then!", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				Cabbage.activated = false;
				Cabbage.delayTimer = 50;
				Cabbage.sequence1 = false;
				Cabbage.sequence2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage guard sequence 3
		if (sequenceNum == 18 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Oh, your back, Have you got the tokens?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Yes I got them right here. (Gives 10 cabbage tokens)", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Good, you can go on through.", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				Cabbage.activated = true;
				Cabbage.delayTimer = 50;
				Cabbage.sequence1 = false;
				Cabbage.sequence2 = false;
				Cabbage.sequence3 = false;
				Cabbage.sequence4 = false;
				Cabbage.finnishedAll = true;

				Door.bigOpened = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage guard sequence 4 (for if turns up with all tokens already)
		if (sequenceNum == 19 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Hey cabbage, can you let me through into the village?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("All tourists must pay the fee, 10 cabbage tokens will do.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("What's stopping me from just killing you can taking the key?", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Oh there's erm...snipers, yes, there's snipers everywhere. You \nwouldn't even stand a chance.",
						22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Well alright, here you go then. (Gives 10 cabbage tokens) ", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Good, you can go on through.", 22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				Cabbage.activated = true;
				Cabbage.delayTimer = 50;
				Cabbage.sequence1 = false;
				Cabbage.sequence2 = false;
				Cabbage.sequence3 = false;
				Cabbage.sequence4 = false;
				Cabbage.finnishedAll = true;

				Door.bigOpened = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage swimming kid sequence
		if (sequenceNum == 20 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Hello little one, are you going to go for a swim?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("What?? In there, no way.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Why not? Are you scared of water?", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Me? scared...? Nopes, I just don't want to get my leaves wet of course.", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Oh really? Then why are you stood next to a swimming pool?", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Enjoying the view obviously...I mean come on bro. Jeez. Dem waves like...wow.", 22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Well all right kid, make sure you don't fall in!", 22);

				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Thanks!", 22);

				sequenceStep = 8;
			}

			if (sequenceStep == 8 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage guy sequence
		if (sequenceNum == 21 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Hello fellow cabbage, why are you so happy today?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Hi random carrot, I'm happy because my son is going to be the next junior\nswimming champion!",
						22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Oh really, that's great!", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I know right, he's going to make me so proud.", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Well I hope he does sir. Oh and by the way, I think the chick next door likes\nyou.", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Julian? That chickabbage be crazy man, she tried to eat my hair once.\nNever again.", 22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"I think I can relate to that...Anyway, have you seen any potatoes\npass through here recently?",
						22);

				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I'm afraid I haven't, sorry. You can try and ask the king though!", 22);

				sequenceStep = 8;
			}

			if (sequenceStep == 8 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Will do. Thanks.", 22);

				sequenceStep = 9;
			}

			if (sequenceStep == 9 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage woman sequence
		if (sequenceNum == 22 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Hello, have you perhaps seen an evil potato pass through here recently?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"I don't think so, have you seen the guy next door? He hasn't spoken to\nme in like 2 days.",
						22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Oh, yes I've seen him. Anyway are you sure you haven't seen anyone\nsuspicious pass through? Its rather serious.",
						22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I wonder if he likes me, oh I miss him...Will you ask him out for me?", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("...(sigh)", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// peter pickle sequence 1
		if (sequenceNum == 24 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Hey, is your name billy?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("No I'm peter! Have you come to rescue me?!", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I'm afraid I'm here for someone else, sorry!", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("WHAT? YOU CANT DO THIS TO ME MAN, IT STINKS DOWN HERE!!", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Jeez, well what can you give me?", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Erm...all I have is a copy of 'onion rampage 4, the quest for tears'.Will\nthat do?", 22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I'm afraid not, I have the collectors edition at home.", 22);

				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("GOD DAMN IT!!!!!!!!!!!!...", 22);

				sequenceStep = 8;
			}

			if (sequenceStep == 8 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				PeterPickle.activated = false;
				PeterPickle.delayTimer = 50;
				PeterPickle.sequence1 = false;
				PeterPickle.sequence2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// peter pickle sequence 2
		if (sequenceNum == 25 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("PLEASEEE DONT GOOOOOOOOOOO!", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("No reward no rescue I'm afraid!", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				PeterPickle.activated = false;
				PeterPickle.sequence1 = false;
				PeterPickle.sequence2 = true;
				PeterPickle.delayTimer = 50;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// billy brocoli sequence 1
		if (sequenceNum == 26 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Hey man, did you kill all the monsters?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Maybe.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Oh, that sounds promising. Can you tell Barry ill be up in a bit.", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Why don't you just come with me now? I'm suppose to rescue you...", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Huh? Don't ask me, the developer is the one who made me unable to move.", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Alright, I guess I'll go tell Barry then...", 22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				BillyBrocoli.activated = false;
				BillyBrocoli.delayTimer = 50;
				BillyBrocoli.sequence1 = false;
				BillyBrocoli.sequence2 = true;

				BarryBeetroot.sequence3 = true;
				BarryBeetroot.sequence2 = false;
				BarryBeetroot.sequence1 = false;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// billy brocoli sequence 2
		if (sequenceNum == 27 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Hey, your still here. Erm...ye.", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I was just wondering what your doing down here?", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"I'm doing research for my new film, 'Vegan massacre 2: A new hope'.The script\nis really coming together, although we do still need to catch a few vegans\nbefore we start filming!",
						22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Very nice, I'll be sure to tell Roy to keep an eye out on it for me!", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				BillyBrocoli.activated = false;
				BillyBrocoli.delayTimer = 50;
				BillyBrocoli.sequence1 = false;
				BillyBrocoli.sequence2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// barry beetroot sequence 1
		if (sequenceNum == 23 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Hello there, what seems to be the problem?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Its my friend,(sniff),we went down into the sewers and I lost him. Could you\ngo and rescue him?",
						22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Well maybe...I'd need an incentive of course.", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("(sniff)Sure. How about 30 carrots?", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("That's a nice sum, what's his name?", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("His name is billy, (sniff), oh I hope he's okay.", 22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Well I'll go investigate!", 22);

				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				BarryBeetroot.activated = false;
				BarryBeetroot.sequence1 = false;
				BarryBeetroot.sequence2 = true;
				BarryBeetroot.delayTimer = 50;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// barry beetroot sequence 2
		if (sequenceNum == 28 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Did you find billy? (sniff)", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Not just yet. Hang in there billy boy.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				BarryBeetroot.activated = false;
				BarryBeetroot.sequence1 = false;
				BarryBeetroot.sequence2 = true;
				BarryBeetroot.delayTimer = 50;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// barry beetroot sequence 2
		if (sequenceNum == 29 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Did you find billy? (sniff)", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Yes! He said he was just finishing his research for the film.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("(sniff)Thank god, I'm so happy he's alive! I never did tell him how I truly felt...", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("That's great, know about that reward...", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Ah yes, here you go.", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Received " + BasicLevel.barryReward + " carrots.", 22, Color.green);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				coins += BasicLevel.barryReward;
				totalCoinCount += BasicLevel.barryReward;

				// finnish all
				BarryBeetroot.activated = true;
				BarryBeetroot.sequence1 = false;
				BarryBeetroot.sequence2 = false;
				BarryBeetroot.sequence3 = false;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// crying cabbage kid sequence
		if (sequenceNum == 30 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("WAAAAAAAAAAAAAAAaaaaaaaa!", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("You alright there kid?", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("No!", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("That's a shame.Here, take a lollipop.", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("-Gives lollipop-", 22, Color.green);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Thanks!", 22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				// finnish all
				CryingCabbagekid.activated = true;
				CryingCabbagekid.talkedTo = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// teen cabbage kid sequence 1
		if (sequenceNum == 31 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("You seen my friend man?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Nope, what does he look like?", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("He's a cabbage.", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Well thanks, that's really helpful. I'm totally going to find him now...", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Really? That's great!", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("...", 22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				// finnish all
				TeenCabbage.activated = false;
				TeenCabbage.sequence1 = false;
				TeenCabbage.delayTimer = 50;
				TeenCabbage.sequence2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// teen cabbage kid sequence 2
		if (sequenceNum == 32 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Did you find him?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Maybe, I've seen a lot of cabbages round here.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Oh thank god! Take this.", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("-Received " + BasicLevel.teenCabbageReward + " carrots-", 22, Color.green);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("...", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				coins += BasicLevel.teenCabbageReward;
				totalCoinCount += BasicLevel.teenCabbageReward;

				// finnish all
				TeenCabbage.activated = true;
				TeenCabbage.delayTimer = 50;
				TeenCabbage.sequence1 = false;
				TeenCabbage.sequence2 = false;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage king sequence 1
		if (sequenceNum == 33 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Hello there traveller, what are doing in this part of town?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I'm looking for the potato who took my kids, have you seen him come through\nhere?", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Well now you mention it, my guards did report seeing a dodgy looking potato\nwith 2 children pass by earlier.",
						22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("That's great! Where are they?", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"I have a big problem of my own right now, the town is under attack! If you\ncould help, I'll happily tell you were your kids are.",
						22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Well...alright. Why aren't you on the battlefield anyway?", 22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Oh, er...I have paperwork to attend to and such. Anyway, take out the mobs \nto the east and north west of here and return here after, ok?",
						22);

				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Sure, sounds like a piece of cake!", 22);

				sequenceStep = 8;
			}

			if (sequenceStep == 8 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("YOU HAVE CAKE? WHY WASENT I INFORMED OF THIS, HAND IT OVER.", 22);

				sequenceStep = 9;
			}

			if (sequenceStep == 9 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Its just an expression man...I don't really have cake.", 22);

				sequenceStep = 10;
			}

			if (sequenceStep == 10 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("WHAT? Cake is not a joking matter, were running short on that stuff...\nnow leave me be.",
						22);

				sequenceStep = 11;
			}

			if (sequenceStep == 11 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Okayyy...I'll be of then.", 22);

				sequenceStep = 12;
			}

			if (sequenceStep == 12 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				// finnish all
				CabbageKing.activated = false;
				CabbageKing.delayTimer = 50;
				CabbageKing.sequence1 = false;
				CabbageKing.sequence2 = true;

				villageAttackStarted = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage king sequence 2
		if (sequenceNum == 34 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Hey, did you kill the mobs yet?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"                                                      Option\n   1)Yes                                                                            2)No\n.                                        number keys to choose.",
						22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.one) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Yes...I definitely murdered those guys.", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"DONT LIE TO ME FOOL! I have spies everywhere, and my own personal\nKEV-TRACKER 9000 chip card.",
						22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Damn, why wasn't I given this?", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Its a pre-order exclusive...", 22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("GOD DAMN IT.I should have known...", 22);

				sequenceStep = 7;
			}

			if (sequenceStep == 2 && (input.two) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("No, not just yet.", 22);

				sequenceStep = 8;
			}

			if (sequenceStep == 8 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Well get on with it!", 22);

				sequenceStep = 9;
			}

			if (sequenceStep == 9 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Ok, I'll be back in a second!", 22);

				sequenceStep = 10;
			}

			if (sequenceStep == 10 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("TIMES UP!", 22);

				sequenceStep = 11;
			}

			if (sequenceStep == 11 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("(sigh)...", 22);

				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				// finnish all
				CabbageKing.activated = false;
				CabbageKing.delayTimer = 50;
				CabbageKing.sequence1 = false;
				CabbageKing.sequence2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage chef 1
		if (sequenceNum == 35 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("DO YOU HAVE ANY CAKE?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I'm afraid I don't, but I do have some muffins if you want.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("WHAT? ARE YOU INSANE?!!!! I REQUIRE CAKE OF PREMIUM\nEXCELLANCE, BRING ME THE CAKE.", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Alright well...I'll keep a look out for some. ", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"HAAAAAAAAAHAAAAAaaaAAA. CAKE!!!(COUGH)I BAKE THE\nCAKE WHICH I MAKE,BUT YOU CANT DO THE SHAKE!",
						22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("-walks away slowly-", 22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				// finnish all
				cabbageChef.activated = false;
				cabbageChef.delayTimer = 50;
				cabbageChef.sequence1 = false;
				cabbageChef.sequence2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage chef 2
		if (sequenceNum == 36 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("DO YOU HAVE THE CAKE?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Not just yet I'm afraid!", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("WHAT? WHEEERRRREEEE ISSS ITTTT!!!!!", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("...", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				// finnish all
				cabbageChef.activated = false;
				cabbageChef.delayTimer = 50;
				cabbageChef.sequence1 = false;
				cabbageChef.sequence2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage chef 3
		if (sequenceNum == 37 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("DO YOU HAVE THE CAKE?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I got it right here.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("OMG YOU ACTUALLY GOT IT, (calls king: WE DID IT, WE CAN SAVE THE\nCHILDREN!).", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("What's wrong with the children?", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("THEY'VE BEEN CAKE DEPRIVED FOR DAYS, DO YOU KNOW WHAT\nTHAT CAN DO AN INFANT CABBAGE?",
						22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I didn't know that cake was so important to you guys...", 22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"HAAAAHAAAAAAAaaa(eats cake)haaaa...Oh, that's better. I must thank you\nkind carrot for your services to the realm, here take this reward.",
						22);

				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Received " + BasicLevel.chefReward + " carrots.", 22, Color.green);

				sequenceStep = 8;
			}

			if (sequenceStep == 8 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				// finnish all
				cabbageChef.activated = true;
				cabbageChef.delayTimer = 50;
				cabbageChef.sequence1 = false;
				cabbageChef.sequence2 = false;
				cabbageChef.sequence3 = false;
				coins += BasicLevel.chefReward;
				totalCoinCount += BasicLevel.chefReward;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage queen 1
		if (sequenceNum == 38 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Hey, erm, I mean your majesty. What's up?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"(sniff)Hello fair traveller. Today is indeed a sad day, this village will never\nbe the same again...",
						22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Oh, well I'm sure the mobs wont take the village. I'm on my way to kill them\nright now!",
						22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Really? You are a brave soul, what's your name?", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("The names Kev, Kev the carrot.", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Well its nice to meet such a brave young man, Philip has grown so weak these\npast few months, I wish he would just take some action.",
						22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Don't worry, I'm sure he will come around...", 22);

				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Promise me Kev, if you make it out alive, that you will visit me again.", 22);

				sequenceStep = 8;
			}

			if (sequenceStep == 8 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Sure...I guess.", 22);

				sequenceStep = 9;
			}

			if (sequenceStep == 9 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Good, I'm sure we would have much to discuss...if you know what I mean.", 22);

				sequenceStep = 10;
			}

			if (sequenceStep == 10 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Hahahaha, of course, good one(confused face). I have to leave then. Bye!", 22);

				sequenceStep = 11;
			}

			if (sequenceStep == 11 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				// finnish all
				CabbageQueen.activated = false;
				CabbageQueen.delayTimer = 50;
				CabbageQueen.sequence1 = false;
				CabbageQueen.sequence2 = true;
				CabbageQueen.happy = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage queen 2
		if (sequenceNum == 39 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Why hello kev, did you rescue the village? ", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Not just yet...", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				// finnish all
				CabbageQueen.activated = false;
				CabbageQueen.delayTimer = 50;
				CabbageQueen.sequence1 = false;
				CabbageQueen.sequence2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage queen 3
		if (sequenceNum == 40 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Why hello kev, did you rescue the village? ", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Yes, I just finished killing all those guys.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Oh wonderful, your so strong, you really make my leaves wet, if you know\nwhat I mean.",
						22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Hahaaha(nervous laugh)...your so funny(must be water leak somewhere).", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Kiss me.", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Wow, miss I think you've got the wrong end of the stick here, I'm married.", 22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Please? Your so attractive.", 22);

				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"That may be the case, but I'm taken, and cross breading isn't my thing. So\ngoodbye your majesty.",
						22);

				sequenceStep = 8;
			}

			if (sequenceStep == 8 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Noooooooo(crys).", 22);

				sequenceStep = 9;
			}

			if (sequenceStep == 9 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				CabbageQueen.activated = false;
				CabbageQueen.delayTimer = 50;
				CabbageQueen.sequence1 = false;
				CabbageQueen.sequence2 = false;
				CabbageQueen.sequence3 = false;
				CabbageQueen.sequence4 = true;
				CabbageQueen.crying = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage queen 4
		if (sequenceNum == 41 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Please don't leave me Kev!", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Sorry, I have a score to settle with a very unfortunate potato.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				// finnish all
				CabbageQueen.activated = false;
				CabbageQueen.delayTimer = 50;
				CabbageQueen.sequence1 = false;
				CabbageQueen.sequence2 = false;
				CabbageQueen.sequence3 = false;
				CabbageQueen.sequence4 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage nerd sequence
		if (sequenceNum == 42 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Hey man, what you reading?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("The life and death of Justin cabbage, you a fan?", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Not really, but I do like the ending. Bread overdose, never would of guessed it.", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I know right, that bread is some good stuff, its really worth the dough.", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I'm more into granulated sugar myself. Anyway, what can you contribute to\nthe story?",
						22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("How about a free book?", 22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("No thanks...", 22);

				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// potato mob sequence 1
		if (sequenceNum == 43 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox(
						"Well well...look what we have here guys. Its a cute little carrot all lost and alone.\nWheres your mommy? Heheehe.(grunts)",
						22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"WHO ARE YOU CALLING CUTE BITCH. Where are you keeping my kids!?\nI've just about had enough of this shit. Don't make me ask twice.",
						22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Kids eh? Ye I know where they are. There Paul's newest pets, I'm sure he'll be\nglad to see your corpse!",
						22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("PETS!!!??Oh your gonna get it now...", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Lets get him boys!", 22);
				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				//create a new potato to sneak attack from behind.
				PotatoRebel rebel = new PotatoRebel(10165, 1678, 1, false, 1);
				rebel.init(level);
				Level.enemies.add(rebel);
				Level.add(rebel);

				// loop all enemies and activate ones with id 1
				for (int i = 0; i < level.enemies.size(); i++) {

					if (level.enemies.get(i).enemyID == 1) {
						PotatoRebel p = (PotatoRebel) level.enemies.get(i);
						p.combatActivated = true;
						p.isMoving = true;
						p.movingRight = false; // move all left to charge
					}
				}

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage banker sequence
		if (sequenceNum == 44 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Please don't kill me!", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Don't worry man, I'm a friendly.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Oh thank god. I was really worried for a moment there. Well now were back in\nbusiness,would you be interested in opening a cabbage card today?",
						22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("No thanks...I'm kinda busy right now.", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Are you sure? We offer a 10% cash growth scheme available to all new\ncustomers!", 22);
				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Ohh realllyyy...I don't care(slaps cabbage).", 22);
				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("OMG, what was that for?", 22);
				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Sorry but I'm under contract with Roy to slap anyone who tries to sell me\nthings...Bye!",
						22);
				sequenceStep = 8;
			}

			if (sequenceStep == 8 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// high cabbage sequence
		if (sequenceNum == 45 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Heeeey man, whatcha doin there?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Can you let me pass through this gate?", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Nawww,naw...hehe, only unicorns are allowed through! Hehee, and your too\nugly!", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Jeez...You do know the town is under attack right?", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Say whaaaaaattt, hehe, attack of the rainbow warrior!...That's awesome man.", 22);
				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("...(sigh)(gets fish from backpack).", 22);
				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Hey, hey! You should be an astro..(fish slap). Kev: GET YOUR SHIT\nTOGETHER MAN. I'VE GOT BUSINESS TO TAKE CARE OF!",
						22);
				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Ohhhhhhh.hehe, your so orange man, just like...erm..like a cloud!", 22);
				sequenceStep = 8;
			}

			if (sequenceStep == 8 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("ssssAhaaaaa. Will you just let me through before I kill you already.", 22);
				sequenceStep = 9;
			}

			if (sequenceStep == 9 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Naw, naw..You need permissions from the king..I mean cloud warrior for that\nman. You wanna joint?",
						22);
				sequenceStep = 10;
			}

			if (sequenceStep == 10 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Not right now. I guess I'll go see the king then.", 22);
				sequenceStep = 11;
			}

			if (sequenceStep == 11 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				HighCabbage.activated = false;
				HighCabbage.delayTimer = 50;
				HighCabbage.sequence1 = false;
				HighCabbage.sequence2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// high cabbage sequence 2
		if (sequenceNum == 46 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Heyyyyy its you again! Orange man.", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Will you let me through now?", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Nawwww man...You don't got the goods, the slip, the tango mango, the chicken.\nYou get me?",
						22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I would kill you, but I've got a reward to collect. I'll be back soon cabbage.", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Hehe, you should like, grow a face within your face! Oooooo inception.", 22);
				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("...(sigh)", 22);
				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				HighCabbage.activated = false;
				HighCabbage.delayTimer = 50;
				HighCabbage.sequence1 = false;
				HighCabbage.sequence2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// high cabbage sequence 3
		if (sequenceNum == 47 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Orange man! What's up my acidic friend.", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I talked to the king, let me through cabbage.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I just got the emails, hehe, your such a unicorn man. I'll open da door for ya.", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Good. And you better give me some of that when I get back, I'll need it.", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Surreeeee, we could be like..erm..cloud buddys man!", 22);
				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("-Door unlocked-", 22, Color.green);
				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				HighCabbage.activated = true;
				HighCabbage.delayTimer = 50;
				HighCabbage.sequence1 = false;
				HighCabbage.sequence2 = false;
				HighCabbage.sequence3 = false;

				// open the door
				Door.bigOpened = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// KING CABBAGE RETURN SEQUENCE
		if (sequenceNum == 48 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Did you kill the mobs yet?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Yep, I just unleashed the beast on those idiots.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Good job traveller! You have saved this village from certain ruin, how may I\nrepay you for your services?",
						22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Tell me where my kids are...and give me money.", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Ah yes. They were last spotted leaving the village to the east of here.I\nwill email the guard to let you through. ",
						22);
				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("You have email here!? Damn it Roy...", 22);
				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Indeed we do. My guards salvaged some tech from a human raid a few months\nback, it works like a charm!",
						22);
				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Very nice. I'll have to get me some of that after I've taken care of business. I\nguess I'll be off then.",
						22);
				sequenceStep = 8;
			}

			if (sequenceStep == 8 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Fair well traveller. Should you ever return, I'll give you a cabbagehood for\nyour services here.",
						22);
				sequenceStep = 9;
			}

			if (sequenceStep == 9 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Oh...great. (omg he didn't even offer a statue).", 22);
				sequenceStep = 10;
			}

			if (sequenceStep == 10 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("-Received " + BasicLevel.kingReward + " carrots-", 22, Color.green);
				sequenceStep = 11;
			}

			if (sequenceStep == 11 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				coins += BasicLevel.kingReward;
				totalCoinCount += BasicLevel.kingReward;

				CabbageKing.activated = true;
				CabbageKing.delayTimer = 50;
				CabbageKing.sequence1 = false;
				CabbageKing.sequence2 = false;
				CabbageKing.sequence3 = false;

				// activate the guards final sequence
				HighCabbage.activated = false;
				HighCabbage.delayTimer = 50;
				HighCabbage.sequence1 = false;
				HighCabbage.sequence2 = false;
				HighCabbage.sequence3 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage sniper tower sequence
		if (sequenceNum == 49 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("How did you get up here carrot!?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Dude, there's a pathway. What are doing?", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I'm on lookout duty, how about you?", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Oh you know...just on a mission to find my kids. Another day in the life of Kev.", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Rough day huh, wanna here a joke?", 22);
				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("No.", 22);
				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("What is a cabbages favourite road?", 22);
				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("No idea...", 22);
				sequenceStep = 8;
			}

			if (sequenceStep == 8 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("The dual cabbage way! Haha. But seriously, those roads are like really smooth\nman.", 22);
				sequenceStep = 9;
			}

			if (sequenceStep == 9 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("ssssAhhhhh...Anyway, have you seen any potatoes up here recently?", 22);
				sequenceStep = 10;
			}

			if (sequenceStep == 10 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("I'm afraid that's confidential sir, sorry.", 22);
				sequenceStep = 11;
			}

			if (sequenceStep == 11 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("ARE YOU SERIOUS? Why you do me like this man?", 22);
				sequenceStep = 12;
			}

			if (sequenceStep == 12 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Its in the contract, take it up with the king...", 22);
				sequenceStep = 13;
			}

			if (sequenceStep == 13 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				CabbageSniper.activated = false;
				CabbageSniper.delayTimer = 50;
				CabbageSniper.sequence1 = false;
				CabbageSniper.sequence2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// cabbage sniper tower sequence 2
		if (sequenceNum == 50 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Hey, before I leave I've gotta ask. What's with the dance?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Huh? What's wrong with the dance?", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Nothing, its just a bit off.", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Off? I think you will find its because I'm fabulous! You jealous babe?", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Oh err...Never mind.", 22);
				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				CabbageSniper.activated = false;
				CabbageSniper.delayTimer = 50;
				CabbageSniper.sequence1 = false;
				CabbageSniper.sequence2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// caged human seq 1
		if (sequenceNum == 51 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Mmmhapahouhou , shewumbaheha cou?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("What? Stop your mumbling, have you seen my kids?", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Mmmwackawahwah! Shikeka cow cow east fumble boo boo.", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("East? Oh that's great, good luck with the whole starving to death thing.", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("UmbackaMumMum!", 22);
				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Hey man, you guys started it. Goodbye foul human.", 22);
				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Mouwwwwwuuuuuuuuuu!", 22);
				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				HumanCaged.activated = false;
				HumanCaged.delayTimer = 50;
				HumanCaged.sequence1 = false;
				HumanCaged.sequence2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// caged human seq 2
		if (sequenceNum == 52 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Shebacka pow pow tee!", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Hey, don't talk to me like that human! I'm your master now.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Omappa poo poo? Rambacka foo foo?", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Shit on the floor, and I'll give you some food when I've taken care of\nbusiness. You better behave if I bring you home!(A new slave would be nice)...",
						22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Owmappa! Owmappa bee gum gum!", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Good. I'll be back human.", 22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				HumanCaged.activated = true;
				HumanCaged.delayTimer = 50;
				HumanCaged.sequence1 = false;
				HumanCaged.sequence2 = false;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// Evil potato sequence 1
		if (sequenceNum == 53 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Kev! We meet at last, I've been expecting you.", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Ken!, Karl! Are you alright!? OMG if he hurt any one of you guys...", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Oh papa! We sorry we went for da ice creams.(sniff) He promised us\ntriple chocolate!",
						22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Ice cream!? Your one sick son of a human. Don't worry kids, we will be going\nhome soon!",
						22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Yayyyyy! Go papa(cheers).", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Enough! Do you have any idea of what I'm capable off? I'm going to make\nyou suffer Kev, and I'll make my new slaves watch!",
						22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"I'm gonna rip your face off you little shit. No one messes with my kids.\nLET THE PEELING COMMENCE!!",
						22);

				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Oh you gonna get it now mister, papa angry. He murder many people when\nangry! Right ken? (ken: yeeaa papa gonna do it!)",
						22);

				sequenceStep = 8;
			}

			if (sequenceStep == 8 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Bring it on! I'll be having carrot soup tonight, with your face as the topping!\nMwwaahahahaa.",
						22);

				sequenceStep = 9;
			}

			if (sequenceStep == 9 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				// start the second walk sequence into areana.
				Player.movementDisabled = true;
				EvilPotato.walkTimer = 0;
				EvilPotato.startWalking2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// Evil potato sequence 2
		if (sequenceNum == 54 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Muwaaahaaahaa. Your such an idiot Kev, prepare to die!", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Not today evil potato!", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				Player.movementDisabled = false;
				EvilPotato.combatActivated = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// Evil potato sequence 3
		if (sequenceNum == 55 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("You think you can hurt me!? I eat your kind for breakfast! EVEN ON\nSUNDAYS!", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("You cannibal! How dare you offend the holy peach of wisdom. You\nwill pay for that!", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Oh no Kev, I'll never pay. FREE CARROTS FOR EVERYONE! muwwahhwha\na(cough)haha.", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				EvilPotato.stage2 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// Evil potato sequence 4
		if (sequenceNum == 56 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Argghhh, is that all you got!? Your kids put up a better fight than this!\nPathetic.",
						22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("What's up? Feeling a bit mashy?", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"Mashy? You think I'm full of mash!? HA. I was injected with human DNA\nat a young age. I'm going to bleed all over your face! Mwwwahaha(choke)ha.",
						22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("...", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				EvilPotato.stage3 = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// Evil potato death return sequence
		if (sequenceNum == 57 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("What!? I just killed you minion. How dare you respawn in the other room!", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("Its only a matter of time potato...seriously.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox(
						"HA. You'll never defeat me Kev, I am invincible! MORE SOUP FOR THE\nFRIDGE! Mwwuhahahaa. ",
						22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				textBoxTimer = 0;

				BasicTextBox("...", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				EvilPotato.combatActivated = true;
				EvilPotato.waitingForPlayerReturn = false;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// Evil potato death text sequence
		if (sequenceNum == 58 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Muwahaha(choke), I'll kill you(choke)..rahsdfdfddg...", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {

				BasicTextBox("Its over potato. You lose.", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {

				BasicTextBox("You cant kill me!! This is only the beginning Kev...you watch.", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {

				BasicTextBox("Whatever. Now for the face peeling. Goodbye potato...", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {

				BasicTextBox("Noooooooo(choke)...", 22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				EvilPotato.combatActivated = false;
				EvilPotato.waitingForPlayerReturn = false;

				//setup the fade out to kids, with peeling sound half way inbetween.
				EvilPotato.kidsSeqActive = true;
				EvilPotato.kidFadeActivate = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// final kids last text sequence of epicness!
		if (sequenceNum == 59 && sequence) {

			// sequence steps
			if (sequenceStep == 0) {

				BasicTextBox("Papa! You made it! I knew that guy would gets what comin to him. Right\nken?", 22);

				sequenceStep = 1;
			}

			if (sequenceStep == 1 && (input.enter) && textBoxTimer == textBoxDelayMax) {

				BasicTextBox("(ken)Yheeeaaa boy, paps showed him whose boss! High five (->bam).", 22);

				sequenceStep = 2;
			}

			if (sequenceStep == 2 && (input.enter) && textBoxTimer == textBoxDelayMax) {

				BasicTextBox("Are we in trouble paps? That potato was really scary =[.", 22);

				sequenceStep = 3;
			}

			if (sequenceStep == 3 && (input.enter) && textBoxTimer == textBoxDelayMax) {

				BasicTextBox("Its ok kids, it wasn't your fault. Lets get back home.", 22);

				sequenceStep = 4;
			}

			if (sequenceStep == 4 && (input.enter) && textBoxTimer == textBoxDelayMax) {

				BasicTextBox(
						"Yaayyy!! Go papa, that potato was such a meanie man. Oh! Hey! Can we get\nice cream on the way back!? (0.0)",
						22);

				sequenceStep = 5;
			}

			if (sequenceStep == 5 && (input.enter) && textBoxTimer == textBoxDelayMax) {

				BasicTextBox("Sure! Only if you behave.", 22);

				sequenceStep = 6;
			}

			if (sequenceStep == 6 && (input.enter) && textBoxTimer == textBoxDelayMax) {

				BasicTextBox("(ken) Awwwww ye boy! Gonna get dem grain freezes man!", 22);

				sequenceStep = 7;
			}

			if (sequenceStep == 7 && (input.enter) && textBoxTimer == textBoxDelayMax) {

				BasicTextBox("Oh and kids, if your mother asks what happened, we went camping, ok?", 22);

				sequenceStep = 8;
			}

			if (sequenceStep == 8 && (input.enter) && textBoxTimer == textBoxDelayMax) {

				BasicTextBox("You got it papa! (ken)Dayyyuummm boy, got that camping down man, amirite?", 22);

				sequenceStep = 9;
			}

			if (sequenceStep == 9 && (input.enter) && textBoxTimer == textBoxDelayMax) {

				BasicTextBox("(karl)Yeea boy, we got it down, real time, you know what im sayin?", 22);

				sequenceStep = 10;
			}

			if (sequenceStep == 10 && (input.enter) && textBoxTimer == textBoxDelayMax) {

				BasicTextBox("Settle down kids. Anyway, we best be off now, your mothers gonna be crazy\nmad...", 22);

				sequenceStep = 11;
			}

			if (sequenceStep == 11 && (input.enter) && textBoxTimer == textBoxDelayMax) {

				BasicTextBox("Lead the way paps!!", 22);

				sequenceStep = 12;
			}

			if (sequenceStep == 12 && (input.enter) && textBoxTimer == textBoxDelayMax) {
				Game.DISPLAY_PLAIN = false;
				Animations.displayText = false;
				sequence = false;
				sequenceNum = -1;
				sequenceStep = 0;
				faceBoxSprite = null;

				EvilPotato.gameOverFinalSeq = true;

			}

			// rendering section
			if (faceBoxSprite != null) {
				screen.renderBasicAccurateSprite(5, 138, faceBoxSprite);
			}

			screen.renderBasicAccurateSprite(0, 138, Sprite.text_box);

		}

		// ///////////////////TEXT BOX SEQUENCES////////////////
		// ////////////////////////////////////////////////////////////////////
		// ///////////////////////////////////////////////////////////////
		// ////////////////////////////////////////////////////////////////////
		// ///////////////////////////////////////////////////////////////

	}

	/*
	 * Handles when the player dies.
	 */
	public void death(int xSpawn, int ySpawn) {
		x = xSpawn;
		y = ySpawn;
		deaths++;
	}

	/*
	 * Sets new location for the Player.
	 */
	public void setLocation(int xa, int ya) {
		x = xa;
		y = ya;
	}

	/*
	 * Sets players spawn point
	 */
	public void setSpawnPoint(int xa, int ya) {
		xSpawn = xa;
		ySpawn = ya;
	}

	// basic with black text
	public void BasicTextBox(String s, int x) {
		textBoxTimer = 0;
		xPos = x;
		yPos = 448;
		col = Color.BLACK;
		text = s;
		Game.DISPLAY_PLAIN = true;
		Game.displayLevel = false;
		Animations.displayText = true;
		Animations.displayTextTime = 10000;
	}

	// allows choice of colors.
	public void BasicTextBox(String s, int x, Color col) {
		textBoxTimer = 0;
		xPos = x;
		yPos = 448;
		this.col = col;
		text = s;
		Game.DISPLAY_PLAIN = true;
		Game.displayLevel = false;
		Animations.displayText = true;
		Animations.displayTextTime = 10000;
	}

}
