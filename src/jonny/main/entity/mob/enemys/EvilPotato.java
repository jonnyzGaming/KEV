package jonny.main.entity.mob.enemys;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import jonny.main.Game;
import jonny.main.Game.GameState;
import jonny.main.entity.Objects.BossCage;
import jonny.main.entity.mob.Player;
import jonny.main.entity.mob.characters.KevKid;
import jonny.main.entity.projectiles.LazerBullet;
import jonny.main.graphics.Animations;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.input.KeyBoard;
import jonny.main.levels.BasicLevel;
import jonny.main.levels.Level;
import jonny.main.menus.Menu;
import jonny.main.sound.Sound;

public class EvilPotato extends Enemy {

	//basic stats
	int xa = 0, ya = 0;
	public static double xPos, yPos; //use for bombs since x,y not static
	public static double healthCopy;
	private int animTimer;
	public static boolean activated = false;
	private double healthStripRatio;

	//For player death
	public static boolean isAtPotatoBoss = false;
	public static boolean waitingForPlayerReturn = false;

	//for potato death
	public static boolean dead = false;
	private boolean deadTextSeq = false;
	private boolean deadAfterShot = false;
	public static boolean kidsSeqActive = false;
	public static boolean kidFadeActivate = false;
	public static int finnishHimTimer = 0;
	public static int faceLeftAfterDeathTimer = 0;
	private boolean notAlreadyPlayedPeelingSound = true;

	//sequence variables
	public static int startX;
	public static boolean startWalking1;
	public static boolean finnishWalking1;
	public static int walkTimer = 0;
	public static int walkTimerMax = 92;

	public static boolean startWalking2;
	public static boolean finnishWalking2;
	public static int walkTimerMax2 = 270;

	public static int cameraRollTimer = 0;
	public static final int cameraRollTimerMax = 150;

	//jumping
	public boolean jump = false; //only can be activated at the selected jump points.
	private static final int JUMP_TIMER_START = 100; //jump for over a second
	private int jumpTimer = 0;
	private static final double JUMP_SPEED_START = 10; //12 is good
	private double jumpSpeed;
	private double jumpDecline = 0.96; //falls every update by a percentage

	//shooting variables
	//for the lazer.
	public static boolean shootingStill = false;
	public static int shootingStillTimer = 0; //counts the time until we want to start shooting
	private final int shootingStillTime = 70; //for the more powerfull lazer shot.
	private double shootStillAngle; //will either be 0 or 180 in radians(left or right)

	//rate of fire etc for moving time
	private int rateOfFire = 20;
	private int rateOfFireTimer = 20;

	//comabt variables
	public static boolean combatActivated = false;
	public static boolean stage1 = true;
	public static boolean stage2 = false;
	public static boolean stage3 = false;
	public static boolean stage4 = false;
	public static boolean deathSeq = false;

	public static boolean isAttackHappening = false;

	//extra froms of attach(eg eggLaying)
	public static boolean layingBomb = false;
	public static int layingBombTimer = 0;
	private final int layingBombTime = 25;

	public static boolean chargeAttack = false;
	private double normalMoveSpeed;
	private final double chargeSpeed;
	public static int readyToChargeTimer = 0;
	private final int readyToChargeTime = 100;
	public static int chargeTimer = 0;
	private final int chargeTime = 150;

	///////jumping points/ path finding figures
	//all boss variables.
	public static int jumpBottomY = 1614;
	public static int jumpBottomLeftX = 16080;
	public static int jumpBottomLeftX2 = 15934;
	public static int jumpBottomRightX = 16194;
	public static int jumpBottomRightX2 = 16342;

	public static int jumpMidY = 1550;
	public static int jumpMidLeftX = 16000;
	public static int jumpMidRightX = 16272;

	public boolean jumpBottom = false;
	public boolean jumpMid = false;

	public static int topY = 1502;
	public static int topYPlatRange = 1400; //basically a value just above the y for the top platform

	//these are variables which determine the time the boss should keep
	//going right or left after hes jumped(to get onto a platform).
	public static final int jumpingTime = 30;
	public static int jumpCount = 0;

	//these values are the end of the platforms
	public int midLeftPlaformLeftEnd = 15984;
	public int midLeftPlaformRightEnd = 16015;

	public int midRightPlaformLeftEnd = 16256;
	public int midRightPlaformRightEnd = 16289;

	public int topPlaformLeftEnd = 16063;
	public int topPlaformRightEnd = 16207;

	//some for the player
	public static int playerMidY = 1559;
	public static int playerTopY = 1511;

	//the middle on the fight area point
	public static int midX = 16138, midY = 1611;
	//////jumping/pathfinding end/////

	//health variables
	public static double TwoThirdHealth;
	public static double OneThirdHealth;

	//death variables
	public static boolean deathActive = false;

	//this activated after talking with kids at end, starts the final sequence transitioning end menu
	public static boolean gameOverFinalSeq = false;
	private int finalMoveTimer = 0; //used for moving kev/kids into credit screen.
	private boolean relocateToKids = false;

	//constructor
	public EvilPotato(int x, int y) {

		this.x = x;
		this.y = y;

		//set the start move seq for player meeting potato
		startX = 15000;

		//extra
		isEnemy = true;
		dir = 0;
		health = 400;
		healthStripRatio = health / 50;
		damage = 2; //when hit flinch normal
		hitFadeMax = 120;

		normalMoveSpeed = 2.5;
		moveSpeed = normalMoveSpeed;
		chargeSpeed = 3.8;

		//more health vars
		TwoThirdHealth = health * 0.666;
		OneThirdHealth = health * 0.333;

		//colision variables
		xTrans = -15;
		yTrans = -17;
		cWidth = 27;
		cHeight = 34;

	}

	//FOR TESTING
	//boolean test = false;

	public void update() {
		xa = 0;
		ya = 0;
		dir = 0;
		if (!combatActivated) health = 400;
		xPos = x;
		yPos = y;
		healthCopy = health;
		moveSpeed = normalMoveSpeed;

		animTimer++;
		if (animTimer == 10000) animTimer = 0;

		//hit fader stuff
		if (recentlyHit) {
			hitFadeTimer = hitFadeMax;
			recentlyHit = false;
		}

		if (hitFadeTimer > 0) hitFadeTimer--;

		//if the player died, check for his return before carrying on.
		if (waitingForPlayerReturn) {
			x = 16135;
			y = 1614;

			//check for return sequence
			if (Game.player.x < 16200 && Game.player.y > 1567) {
				Player.sequenceNum = 57;
				Player.sequence = true;
			}

			return;

		}

		//////////////////////POTATO DEATH////////////////
		//if the potato is dead, then we dont want to update anything other than this.
		if (dead) {

			//do everything for death at the peak of fade animation(when fully black)
			if (Game.a == 254) {
				//change both the player and potato positions.
				Game.player.x = 16182;
				Game.player.y = 1623;
				x = 16135;
				y = 1614;

				//play death interval sound.
				Sound.explosion.play(false);

				Player.sequenceNum = 58;
				Player.sequence = true;

				dead = false;
				deadTextSeq = true;
			}

			faceLeftAfterDeathTimer++;
			//need to face left before continue
			if (faceLeftAfterDeathTimer >= 251 && faceLeftAfterDeathTimer <= 252) {
				KeyBoard.left = true;
				KeyBoard.keys[KeyEvent.VK_LEFT] = true;
			}
			if (faceLeftAfterDeathTimer == 253) {
				for (int i = 0; i < KeyBoard.keys.length; i++) {
					KeyBoard.keys[i] = false;
				}
				Player.movementDisabled = false;
			}

			return;
		}
		//////////////////////POTATO DEATH////////////////

		//////////////////////////////KIDS SEQUENCE/END GAME///////////////////////
		//cover the kids sequence after death over.
		if (kidsSeqActive) {

			if (kidFadeActivate) {
				Game.FADE_IN_OUT_WHOLE = true;
				Animations.FadeInOutWholeScreen = true;
				Player.movementDisabled = true;
				kidFadeActivate = false;
			}
			finnishHimTimer++;
			//wait a bit before auto shooting the potato to mix with the fade.
			if (finnishHimTimer == 70) {
				KeyBoard.space = true;
				KeyBoard.keys[KeyEvent.VK_SPACE] = true;

			}
			if (finnishHimTimer == 71) {
				for (int i = 0; i < KeyBoard.keys.length; i++) {
					KeyBoard.keys[i] = false;
				}
				//change the potatos animation to completly dead here.
				deadAfterShot = true;
			}

			//play peeling sound just before mid fade
			if (Game.a == 220 && notAlreadyPlayedPeelingSound) {
				Sound.peelingFace.play(false);
				notAlreadyPlayedPeelingSound = false;
			}

			//if at mid fade going to the kids sequence.
			if (Game.a == 255 && !relocateToKids) {
				//activate the kids sequence.
				//first relocate the player
				Game.player.x = 15313;
				Game.player.y = 1367;

				relocateToKids = true;

				//create kid objects
				KevKid kid1 = new KevKid(15337, 1367, true);
				kid1.init(level);
				Level.add(kid1);

				KevKid kid2 = new KevKid(15355, 1367, false);
				kid2.init(level);
				Level.add(kid2);

				//change the kids cage object to have the new sprite
				for (int e = 0; e < Level.ChangeableBackgroundObjectsArray.size(); e++) {
					if (Level.ChangeableBackgroundObjectsArray.get(e).getSprite() == Sprite.cage1) {

						Level.ChangeableBackgroundObjectsArray.get(e).setSprite(Sprite.cageRelease1);

					}
				}

			}

			//at end of fade start the kids text boxes
			if (finnishHimTimer == 470) {
				//start the final kids sequence!
				Player.movementDisabled = false;
				Player.sequenceNum = 59;
				Player.sequence = true;
			}

			//when enabled, start the fade and movement of kev and his kids into distance. And 
			//switch to the final end menu state at the end of it!
			if (gameOverFinalSeq) {

				//disable player movement by keys and set camera to static fixed position.
				Player.movementDisabled = true;
				Game.controllCamera = true;
				Game.customX = 15176;
				Game.customY = 1281;

				KevKid.getMoving = true;
				finalMoveTimer++;
				//start moving kev and kids for set amount of time(short)
				if (finalMoveTimer <= 340) {
					Player.normalMovementSpeed = 1;
					KeyBoard.left = true;
					KeyBoard.keys[KeyEvent.VK_LEFT] = true;

					//if at a particular point here start the quick fade
					if (finalMoveTimer == 120) {
						Game.FADE_OUT_TRANSITION = true;
						Animations.FadeOutTransition = true;

					}

					//switch to new menu after the final fade.
					if (finalMoveTimer == 340) {
						Game.gs = GameState.MENU;
						Game.menu = Menu.completion_menu;

						Sound.menuCompleteMusic.play(true);
						Sound.menuCompleteMusic.isPlaying = true;

						BasicLevel.a = 0;

						Sound.music.stop();
						Sound.music.isPlaying = false;

						Game.gameComplete = true;

						//disable a bunch of stuff
						//disable some stuff we changed.
						kidsSeqActive = false;
						Player.movementDisabled = false;
						Game.controllCamera = false;
						Player.normalMovementSpeed = 3;
						Player.movementDisabled = false;
						gameOverFinalSeq = false;
						KevKid.getMoving = false;
						for (int i = 0; i < KeyBoard.keys.length; i++) {
							KeyBoard.keys[i] = false;
						}
						KeyBoard.enter = false;

					}

					//if at another point finnally change the game state and menu to credits.

				}

			}

			return;
		}
		//////////////////////////////KIDS SEQUENCE/END GAME///////////////////////

		//only for testing purposes(delete after finnished) 
		/////TESTING////
		/*
		 * if (!test) {
		 * isAtPotatoBoss = true;
		 * BossCage.cageClosed = true;
		 * BossCage.cageOpened = false;
		 * BossCage.activated = true;
		 * test = true;
		 * Player.xSpawn = 16575;
		 * Player.ySpawn = 1614;
		 * 
		 * }
		 */
		/////TESTING////

		/////FIRST SEQUENCE CODE START(COMMENT OUT FOR TESTING)////
		//@formatter:off
		
		  if (Game.player.x >= startX && !startWalking1){ 
			  isAtPotatoBoss = true; 
			  Player.movementDisabled = true; 
			  startWalking1 = true;
		  
    		  //change the spawn point to the new checkpoint in the cave
    		  Player.xSpawn = 16575; Player.ySpawn = 1614; //Also set the cage to closed. 
    		  BossCage.cageClosed = true; 
    		  BossCage.cageOpened = false;
    		  BossCage.activated = true;
		  }
		  
		  //start walking walkTimermax amount of time before ending by starting the potato sequence. 
		  if (startWalking1 && !finnishWalking1) { 
			  if(walkTimer < walkTimerMax) { 
				  walkTimer++; 
				  KeyBoard.right = true;
				  KeyBoard.keys[KeyEvent.VK_RIGHT] = true;
			  
        		  //roll the camera 
        		  if (cameraRollTimer <= cameraRollTimerMax){
        			  cameraRollTimer++; 
        		  } 
		  }else { 
			  //clear out keys array to solve bug 
			  for(int i = 0; i < KeyBoard.keys.length; i++) { 
				  KeyBoard.keys[i] = false; 
			  } 
			  Player.sequenceNum = 53; 
			  Player.sequence = true;
    		  finnishWalking1 = true; 
    		  Player.movementDisabled = false;
		    
		  	}
		  }
		   /////FIRST SEQUENCE CODE END////
		  
		  //SECOND SEQUENCE CODE START///// 
		  if (startWalking2 && !finnishWalking2) { 
			  if (walkTimer < walkTimerMax2){ 
				  walkTimer++;
        		  //move the player and the potato this time.
				  moveSpeed = Player.normalMovementSpeed;
			      xa += moveSpeed;
        		  KeyBoard.right = true; 
        		  KeyBoard.keys[KeyEvent.VK_RIGHT] = true;
		  
        		  //roll the camera back to normal 
                  if (cameraRollTimer > 0)cameraRollTimer--; 
			  }else { 
    			  //clear out keys array to solve bug 
    			  for(int i = 0; i < KeyBoard.keys.length; i++) { 
    				  KeyBoard.keys[i] = false; 
    			  }
    		  
        		  Player.movementDisabled = false; 
        		  Player.sequenceNum = 54;
        		  Player.sequence = true; 
        		  finnishWalking2 = true; 
        		  moveSpeed = normalMoveSpeed;
        		  
		  	  } 
          } 
		  /////SECOND SEQUENCE CODE END/////
		 
		//@formatter:on

		//////////JUMPING///////////////NOTE I REMOVED JUMP DELAY MABYE DONT NEED//
		//only jump when when on the floor and the jump boolean is activated by pathfinder.
		if (jump && (collision(0, 1) || objectCollision(0, 1))) {
			normalJumping = true;
			jumpSpeed = JUMP_SPEED_START;
			jumpTimer = JUMP_TIMER_START;
			jump = false;
		}

		//keep jumping for the amount of time we want, else stop jumping and gravity takes over.
		if (jumpTimer > 0) {
			jumpTimer--;
		} else {
			normalJumping = false;
		}

		//if jumping then affect y with exponential decline.(big to small speed).
		if (normalJumping) {
			jumpSpeed = jumpSpeed * jumpDecline;
			ya -= jumpSpeed;

		}

		//////////JUMPING///////////////

		//////////THE COMBAT STAGES////////////
		//combatActivated = true;
		if (combatActivated) {

			///////////STAGE 1/////////////(note alot of code repeated in other stages, bad bad)
			//stage 1 is procedural run and fire
			if (stage1) {

				//check if two third for conversion to stage 2.
				if (health <= TwoThirdHealth) {
					stage1 = false;
					Player.sequenceNum = 55;
					Player.sequence = true;
				}

				//first check if we are doing any other attacks, if we are not then we can have a
				//random chance to select one
				isAttackHappening = shootingStill;
				if (!isAttackHappening) {
					//first have a random chance for the lazer
					if (Game.rand.nextInt(180) == 179) {
						shootingStill = true;
					} else {
						//more attacks here
					}
				}

				//if we are doing the shooting still attack then do it and nothing else
				if (shootingStill) {
					shootingStillTimer++;

					//if were half way through before shooting then decide the angle to fire at
					if (shootingStillTimer == shootingStillTime / 2) {
						if (Game.player.x >= x) shootStillAngle = Math.toRadians(0);
						else shootStillAngle = Math.toRadians(180);
					}

					//if fully charged then shoot
					if (shootingStillTimer >= shootingStillTime) {
						shootLazerBullet(x + 5, y, shootStillAngle);
						shootingStillTimer = 0;
						shootingStill = false;
						shootStillAngle = 9001; //need to reset this to random value that isent 0 or 180
					}
					//else we can just chase the player as normal
				} else {
					chase((int) Game.player.x, (int) Game.player.y);
				}

				//always do stuff here.
				collisionDamage(3);

			}
			///////////STAGE 1/////////////	

			///////////STAGE 2/////////////	
			if (stage2) {
				//stage 2 adds the possibility to spawn a mini fast potato egg(spawns after 5 secs)

				//check if one third health conversion to stage 3.
				if (health <= OneThirdHealth) {
					Player.sequenceNum = 56;
					Player.sequence = true;
					stage1 = false;
					stage2 = false;
				}

				//first check if we are doing any other attacks, if we are not then we can have a
				//random chance to select one
				isAttackHappening = shootingStill || layingBomb;
				if (!isAttackHappening) {
					//first have a random chance for the lazer
					if (Game.rand.nextInt(260) == 249) {
						shootingStill = true;
					} else if (Game.rand.nextInt(200) == 150) {
						layingBomb = true;
					}
				}

				//if we are doing the shooting still attack then do it and nothing else
				if (shootingStill) {
					shootingStillTimer++;

					//if were half way through before shooting then decide the angle to fire at
					if (shootingStillTimer == shootingStillTime / 2) {
						if (Game.player.x >= x) shootStillAngle = Math.toRadians(0);
						else shootStillAngle = Math.toRadians(180);
					}

					//if fully charged then shoot
					if (shootingStillTimer >= shootingStillTime) {
						shootLazerBullet(x + 5, y, shootStillAngle);
						shootingStillTimer = 0;
						shootingStill = false;
						shootStillAngle = 9001; //need to reset this to random value that isent 0 or 180
					}
					//else we can just chase the player as normal
				} else if (layingBomb) {
					layingBombTimer++;

					if (layingBombTimer == layingBombTime) {

						Bomb boom = new Bomb((int) x, (int) y, this);
						boom.init(level);
						Level.enemies.add(boom);
						Level.add(boom);
						layingBomb = false;
						layingBombTimer = 0;

					}

				} else {
					chase((int) Game.player.x, (int) Game.player.y);
				}

				//always do stuff here.
				collisionDamage(3);

			}

			///////////STAGE 2/////////////	

			///////////STAGE 3 FINAL/////////////	
			if (stage3) {

				//stage 3 adds a new attack called charge blood rage 9000, to do.

				//check if dead. Start the whole death sequence if so.
				if (health <= 0) {
					Player.vunerable = false; //player cant die now.
					dead = true;
					Game.FADE_IN_OUT_WHOLE = true;
					Animations.FadeInOutWholeScreen = true;
					//Player.sequence = true;
					//Player.sequenceNum = -1; //just for now dont want text box yet.
					for (int i = 0; i < KeyBoard.keys.length; i++) {
						KeyBoard.keys[i] = false;
					}
					Player.movementDisabled = true;
					stage1 = false;
					stage2 = false;
					stage3 = false;
					return;
				}

				//first check if we are doing any other attacks, if we are not then we can have a
				//random chance to select one
				isAttackHappening = shootingStill || layingBomb || chargeAttack;
				if (!isAttackHappening) {
					//first have a random chance for the lazer
					if (Game.rand.nextInt(260) == 249) {
						shootingStill = true;
					} else if (Game.rand.nextInt(260) == 150) {
						layingBomb = true;
					} else if (Game.rand.nextInt(200) == 120) {
						chargeAttack = true;
					}
				}

				//if we are doing the shooting still attack then do it and nothing else
				if (shootingStill) {
					shootingStillTimer++;

					//if were half way through before shooting then decide the angle to fire at
					if (shootingStillTimer == shootingStillTime / 2) {
						if (Game.player.x >= x) shootStillAngle = Math.toRadians(0);
						else shootStillAngle = Math.toRadians(180);
					}

					//if fully charged then shoot
					if (shootingStillTimer >= shootingStillTime) {
						shootLazerBullet(x + 5, y, shootStillAngle);
						shootingStillTimer = 0;
						shootingStill = false;
						shootStillAngle = 9001; //need to reset this to random value that isent 0 or 180
					}
					//else we can just chase the player as normal
				} else if (layingBomb) {
					layingBombTimer++;

					if (layingBombTimer == layingBombTime) {

						Bomb bomb = new Bomb((int) x, (int) y, this);
						bomb.init(level);
						Level.enemies.add(bomb);
						Level.add(bomb);
						layingBomb = false;
						layingBombTimer = 0;

					}

				} else if (chargeAttack) {

					readyToChargeTimer++;

					//if we are ready to charge then we can do so.
					if (readyToChargeTimer >= readyToChargeTime) {

						chargeTimer++;

						if (chargeTimer >= chargeTime) {
							chargeAttack = false;
							chargeTimer = 0;
							readyToChargeTimer = 0;
						} else {
							moveSpeed = chargeSpeed;
							chase((int) Game.player.x, (int) Game.player.y);
						}

					}

				} else {
					chase((int) Game.player.x, (int) Game.player.y);
				}

				//always do stuff here.
				collisionDamage(3);
			}
			///////////STAGE 3 FINAL/////////////

		}

		//////////THE COMBAT STAGES////////////

		//////////MOVEMENT/////////////
		int yTemp = (int) y;

		if (xa != 0 || ya != 0) {
			move(xa, ya);
		}

		//gravity
		gravity(3);

		int yTemp2 = (int) y;

		//falling check
		if (yTemp < yTemp2) {
			falling = true;
		} else {
			falling = false;
		}
		//////////MOVEMENT/////////////

	}

	public void render(Screen screen) {

		//first cover all the standard movement animations
		//stand still
		if (dir == 0) {
			if (animTimer % 60 > 30) {
				sprite = Sprite.evil_potato1;
				if (chargeAttack) sprite = Sprite.evil_potato1_charge;
			} else {
				sprite = Sprite.evil_potato2;
				if (chargeAttack) sprite = Sprite.evil_potato2_charge;
			}
		}

		//move right
		if (dir == 1) {
			if (animTimer % 30 > 15) {
				sprite = Sprite.evil_potato_right1;
				if (chargeAttack) sprite = Sprite.evil_potato_right1_charge;
			} else {
				sprite = Sprite.evil_potato_right2;
				if (chargeAttack) sprite = Sprite.evil_potato_right2_charge;
			}
		}

		//move left
		if (dir == 2) {
			if (animTimer % 30 > 15) {
				sprite = Sprite.evil_potato_left1;
				if (chargeAttack) sprite = Sprite.evil_potato_left1_charge;
			} else {
				sprite = Sprite.evil_potato_left2;
				if (chargeAttack) sprite = Sprite.evil_potato_left2_charge;
			}
		}

		//fall right
		if (dir == 3) {
			sprite = Sprite.evil_potato_fall_right;
		}

		//fall left
		if (dir == 4) {
			sprite = Sprite.evil_potato_fall_left;
		}

		//now cover any special animations which will overwrite the default ones.
		//shooting still
		if (shootingStill) {
			//if in the first half of charging raise hand like a boss
			sprite = Sprite.evil_potato_stop_charge;

			//if we have now decided on what direction to fire in then change animation
			if (shootStillAngle == Math.toRadians(0)) sprite = Sprite.evil_potato_stop_shoot_right;
			else if (shootStillAngle == Math.toRadians(180)) sprite = Sprite.evil_potato_stop_shoot_left;

		}

		//overwirte all with death animations if potato is dead
		if (deadTextSeq) {
			if (animTimer % 80 > 40) {
				sprite = Sprite.evil_potato_death1;
			} else {
				sprite = Sprite.evil_potato_death2;
			}

			//after hes been shot change to fully dead.
			if (deadAfterShot) {
				sprite = Sprite.evil_potato_death3;
			}

			shootingStill = false; //dont want this after dead.

		}

		screen.renderObject((int) x - 23, (int) y - 23, sprite, this);

		//render the health bar
		if (hitFadeTimer > 0) {

			screen.renderAccurateSprite((int) x - 29, (int) y - 25, Sprite.health_bar, this);
			//render the 50 health strips
			for (int i = 1; i <= 50; i++) {
				//render the strips based on the overall health
				if (i * healthStripRatio <= health) {
					screen.renderAccurateSprite((int) x - 29 + i, (int) y - 24, Sprite.health_strip, this);
				}
			}
		}
	}

	//controlls movement of the potato and animation direction.
	public void move(int xa, int ya) {
		dir = 0;

		if (xa > 0) dir = 1;
		if (xa < 0) dir = 2;

		if (falling) {
			if (xa > 0) dir = 3;
			if (xa < 0) dir = 4;
		}

		//moving the x
		if (!collision(xa, 0) && !objectCollision(xa, 0)) {
			x += xa;
		}

		//moving the y
		if (!collision(0, ya) && !objectCollision(0, ya)) {
			y += ya;
		}

	}

	//method that checks if theres been a collision with the player and damages
	//him by the amount if so. Damage may change in rage mode.
	public void collisionDamage(int damage) {

		Rectangle box = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

		if (entityCollision(Game.player, box)) {
			//activate flinching
			if (!Player.flinching) {
				Player.health -= damage;
				Player.flinchActivated = 1;
			}
		}
	}

	//uses path finding algorithm to go the middle point(for attacks) UNUSED
	public void goToMiddleAttack(int midX, int midY) {

		//go to the middle by using path finding alg(instead of player use the
		//mid point.
		chase(midX, midY);

	}

	//shoots a high speed lazer bullet at the player. called after standing still for a bit.
	public void shootLazerBullet(double x, double y, double angle) {

		//add the bullet
		LazerBullet bullet = new LazerBullet((int) x, (int) y, angle);
		bullet.init(Game.level);
		level.add(bullet);

		//Sound.gooFire.play(false);
	}

	public void remove() {
		removed = true;
	}

	////////////////THE MESSY CHASE ALGORITHM! STAY AWAY//////////////
	//path finding algorithm used to chase the player around the fight area.
	//may be overwrited by other methods such as goToMiddle, stopAndShoot etc.
	public void chase(int playerX, int playerY) {

		///////////////START OF PLAYER BEING ON BOTTOM LEVEL////////////
		//cover the player on bottom level. Boss only needs to walk in this 
		//level, if the boss is on higher levels it will need to navigate 
		//to the bottom(cant just follow player will get stuck).

		//if player on bottom level
		if (playerY > playerMidY) {

			//if boss on mid level platform then follow the player
			//unless he is underneath a platform.
			if (y > topY && y <= jumpMidY) {
				//first determine which mid platform boss is on
				//if boss on the left mid
				if (x < midX) {
					//if the player is out of the range of the plat(not under it) 
					//then go towards him for smoothness.
					if (playerX < midLeftPlaformLeftEnd - 5 || playerX > midLeftPlaformRightEnd + 5) {
						if (x >= playerX) {
							xa -= moveSpeed;
						} else {
							xa += moveSpeed;
						}
					}
					//else fall down to nearest side.
					else if (Math.abs(x - midLeftPlaformLeftEnd) <= Math.abs(x - midLeftPlaformRightEnd)) {
						xa -= moveSpeed;
					} else {
						xa += moveSpeed;
					}
				}
				//else if boss on the right mid
				else
				//if the player is out of the range of the plat then go towards him.
				if (playerX < midRightPlaformLeftEnd - 5 || playerX > midRightPlaformRightEnd + 5) {
					if (x >= playerX) {
						xa -= moveSpeed;
					} else {
						xa += moveSpeed;
					}
				}
				//else fall down to nearest side.
				else if (Math.abs(x - midRightPlaformLeftEnd) <= Math.abs(x - midRightPlaformRightEnd)) {
					xa -= moveSpeed;
				} else {
					xa += moveSpeed;
				}
			}
			//if the boss on the top level then go down to nearest bottom
			else if (y > topYPlatRange && y <= topY) {
				//if boss on the top
				if (x >= topPlaformLeftEnd - 16 && x <= topPlaformRightEnd + 16) {
					//if closer to left go left or else go right
					if (Math.abs(x - topPlaformLeftEnd) <= Math.abs(x - topPlaformRightEnd)) {
						xa -= moveSpeed;
					} else {
						xa += moveSpeed;
					}
				}
			}
			//else if the boss is on the same bottom level then just follow him!
			else {
				//if player is too the right of the boss
				if (playerX >= x) xa += moveSpeed;
				if (playerX < x) xa -= moveSpeed;
			}
		}
		/////////////////END OF PLAYER BEING ON BOTTOM LEVEL///////////////

		//cover player on the mid level, boss may need to jump onto the mid 
		//level for this from jumping point.

		///////////START OF PLAYER BEING ON MID LEVEL/////////////////
		//if player is on one of the mid platforms (includes a y range AND
		//x range check to ensure player is on one of the platforms.
		//NOTE: THE +5 ETC ARE FOR EXTRA ACCURACY FROM BUGS.
		else if (playerY <= playerMidY && playerY > playerTopY && ((playerX >= midLeftPlaformLeftEnd - 5 && playerX <= midLeftPlaformRightEnd + 5) || (playerX >= midRightPlaformLeftEnd - 5 && playerX <= midRightPlaformRightEnd + 5))) {

			//if not currently jumping onto a platform then need to move left or right
			//from any level(this if cancells out movement if in middle of jump).
			//may be repeated for when player on top level aswell.
			if (jumpCount == 0) {

				//if boss is on the BOTTOM then need to jump up. 
				if (y > jumpMidY) {
					//boss should jump up to the platform the PLAYER is closest to

					//if player on left jump to left platform.
					if (playerX < midX) {
						//find the nearest left point from current position.
						//if were closest to the first point then jump there.
						if (Math.abs(x - jumpBottomLeftX) < Math.abs(x - jumpBottomLeftX2)) {
							//move left if boss is to the right of the first point.
							if (x >= jumpBottomLeftX) {
								xa -= moveSpeed;
								//if in the jump range then jump.
								if (Math.abs(x - jumpBottomLeftX) < 4 && y == jumpBottomY) {
									jump = true;
									jumpCount = jumpingTime;
									jumpBottom = true;
								}

							}//else move right to the jump point
							else {
								xa += moveSpeed;
								//if in the jump range then jump.
								if (Math.abs(x - jumpBottomLeftX) < 4 && y == jumpBottomY) {
									jump = true;
									jumpCount = jumpingTime;
									jumpBottom = true;
								}
							}
							//if we were closest to the second point then jump from that instead.
						} else {
							//move left if boss is to the right of the second point.
							if (x >= jumpBottomLeftX2) {
								xa -= moveSpeed;
								//if in the jump range then jump.
								if (Math.abs(x - jumpBottomLeftX2) < 4 && y == jumpBottomY) {
									jump = true;
									jumpCount = jumpingTime;
									jumpBottom = true;
								}

							}//else move right to the jump point
							else {
								xa += moveSpeed;
								//if in the jump range then jump.
								if (Math.abs(x - jumpBottomLeftX2) < 4 && y == jumpBottomY) {
									jump = true;
									jumpCount = jumpingTime;
									jumpBottom = true;
								}
							}
						}
						//else if player to RIGHT jump to right platform
					} else if (playerX >= midX) {
						////if were closest to the first point then jump from there.
						if (Math.abs(x - jumpBottomRightX) < Math.abs(x - jumpBottomRightX2)) {
							//move right if boss is to the left of the first point.
							if (x <= jumpBottomRightX) {
								xa += moveSpeed;
								//if in the jump range then jump.
								if (Math.abs(x - jumpBottomRightX) < 4 && y == jumpBottomY) {
									jump = true;
									jumpCount = jumpingTime;
									jumpBottom = true;
								}

							}//else move left to the jump point
							else {
								xa -= moveSpeed;
								//if in the jump range then jump.
								if (Math.abs(x - jumpBottomRightX) < 4 && y == jumpBottomY) {
									jump = true;
									jumpCount = jumpingTime;
									jumpBottom = true;
								}
							}
							//if we were closest to the second point then jump from that instead.
						} else {
							//move right if boss is to the left of the second point.
							if (x <= jumpBottomRightX2) {
								xa += moveSpeed;
								//if in the jump range then jump.
								if (Math.abs(x - jumpBottomRightX2) < 4 && y == jumpBottomY) {
									jump = true;
									jumpCount = jumpingTime;
									jumpBottom = true;
								}

							}//else move left to the jump point
							else {
								xa -= moveSpeed;
								//if in the jump range then jump.
								if (Math.abs(x - jumpBottomRightX2) < 4 && y == jumpBottomY) {
									jump = true;
									jumpCount = jumpingTime;
									jumpBottom = true;
								}
							}
						}
					}
					//////////end of boss being on BOTTOM.////////////////

					//else if the boss is on the mid level or top whilst on
					//one of the mid plats then go towards the player.
				} else if (y <= jumpMidY && y > topYPlatRange) {
					if (x >= playerX) {
						xa -= moveSpeed;
					} else {
						xa += moveSpeed;
					}

				}
			}
			//else if the player is just anywhere in the mid range and boss in
			//the top range we need to go to nearest edge.
		} else if (playerY <= playerMidY && playerY > playerTopY && y > topYPlatRange && y <= topY) {
			//if boss on the top
			if (x >= topPlaformLeftEnd - 10 && x <= topPlaformRightEnd + 14) {
				//if closer to left go left or else go right
				if (Math.abs(x - topPlaformLeftEnd) <= Math.abs(x - topPlaformRightEnd)) {
					xa -= moveSpeed;
				} else {
					xa += moveSpeed;
				}
			}
		}
		//////!!//////END OF PLAYER BEING ON MID LEVEL////////!!///////

		////////////START OF PLAYER BEING ON TOP LEVEL//////////////////
		//if the player is on the top level, then need to jump up
		//possibly multiple times to get to the top.
		else if (playerY > topYPlatRange && playerY <= playerTopY && ((playerX > topPlaformLeftEnd - 5) || (playerX < topPlaformRightEnd - 5))) {

			//if not already jumping
			if (jumpCount == 0) {

				//start of boss on bottom level.
				//if boss on the bottom level then jump to the nearest mid, and
				//then to the top in the next section of code(when boss on mid).
				if (y > jumpMidY) {
					//find the nearest mid jump point and go to it.(reuses prev code)
					//first calculate all the distances and find the smallest one.
					int distToLeftX1 = (int) Math.abs(x - jumpBottomLeftX);
					int distToLeftX2 = (int) Math.abs(x - jumpBottomLeftX2);
					int distToRightX1 = (int) Math.abs(x - jumpBottomRightX);
					int distToRightX2 = (int) Math.abs(x - jumpBottomRightX2);

					//if closest to leftX1 point
					if (distToLeftX1 <= distToLeftX2 && distToLeftX1 <= distToRightX1 && distToLeftX1 <= distToRightX2) {
						//move left if boss is to the right point
						if (x >= jumpBottomLeftX) {
							xa -= moveSpeed;
							//if in the jump range then jump.
							if (Math.abs(x - jumpBottomLeftX) < 4 && y == jumpBottomY) {
								jump = true;
								jumpCount = jumpingTime;
								jumpBottom = true;
							}

						}//else move right to the jump point
						else {
							xa += moveSpeed;
							//if in the jump range then jump.
							if (Math.abs(x - jumpBottomLeftX) < 4 && y == jumpBottomY) {
								jump = true;
								jumpCount = jumpingTime;
								jumpBottom = true;
							}
						}
						//else if were closest to the leftX2 point
					} else if (distToLeftX2 <= distToLeftX1 && distToLeftX2 <= distToRightX1 && distToLeftX2 <= distToRightX2) {
						//move left if boss is to the right point
						if (x >= jumpBottomLeftX2) {
							xa -= moveSpeed;
							//if in the jump range then jump.
							if (Math.abs(x - jumpBottomLeftX2) < 4 && y == jumpBottomY) {
								jump = true;
								jumpCount = jumpingTime;
								jumpBottom = true;
							}

						}//else move right to the jump point
						else {
							xa += moveSpeed;
							//if in the jump range then jump.
							if (Math.abs(x - jumpBottomLeftX2) < 4 && y == jumpBottomY) {
								jump = true;
								jumpCount = jumpingTime;
								jumpBottom = true;
							}
						}

						//else if were closest to the rightX1 point go there.
					} else if (distToRightX1 <= distToLeftX1 && distToRightX1 <= distToLeftX2 && distToRightX1 <= distToRightX2) {
						//move left if boss is to the right point
						if (x >= jumpBottomRightX) {
							xa -= moveSpeed;
							//if in the jump range then jump.
							if (Math.abs(x - jumpBottomRightX) < 4 && y == jumpBottomY) {
								jump = true;
								jumpCount = jumpingTime;
								jumpBottom = true;
							}

						}//else move right to the jump point
						else {
							xa += moveSpeed;
							//if in the jump range then jump.
							if (Math.abs(x - jumpBottomRightX) < 4 && y == jumpBottomY) {
								jump = true;
								jumpCount = jumpingTime;
								jumpBottom = true;
							}
						}
						//else if closest to the rightX2 point then go there!
					} else if (distToRightX2 <= distToLeftX1 && distToRightX2 <= distToLeftX2 && distToRightX2 <= distToRightX1) {
						//move left if boss is to the right point
						if (x >= jumpBottomRightX2) {
							xa -= moveSpeed;
							//if in the jump range then jump.
							if (Math.abs(x - jumpBottomRightX2) < 4 && y == jumpBottomY) {
								jump = true;
								jumpCount = jumpingTime;
								jumpBottom = true;
							}

						}//else move right to the jump point
						else {
							xa += moveSpeed;
							//if in the jump range then jump.
							if (Math.abs(x - jumpBottomRightX2) < 4 && y == jumpBottomY) {
								jump = true;
								jumpCount = jumpingTime;
								jumpBottom = true;
							}
						}

					}
					//////////////end of boss on bottom level//////////

					/////////////start of boss on mid level//////////
					//if the boss is on the mid level then we need to jump up
					//to the top level to chase the player, simple!
				} else if (y > topY && y <= jumpMidY) {
					//if boss on the left mid, move to the left mid 
					//jump point.
					if (x < midX) {
						//move left to jump point if boss to right of it.
						if (x >= jumpMidLeftX) {
							xa -= moveSpeed;
							//if in the jump range then jump.
							if (Math.abs(x - jumpMidLeftX) < 4 && y == jumpMidY) {
								jump = true;
								jumpCount = jumpingTime;
								jumpMid = true;
							}

						}//else move right to the jump point
						else {
							xa += moveSpeed;
							//if in the jump range then jump.
							if (Math.abs(x - jumpMidLeftX) < 4 && y == jumpMidY) {
								jump = true;
								jumpCount = jumpingTime;
								jumpMid = true;
							}
						}

						//else if on right platform then move to right point
					} else {
						//move left to jump point if boss to right of it.
						if (x >= jumpMidRightX) {
							xa -= moveSpeed;
							//if in the jump range then jump.
							if (Math.abs(x - jumpMidRightX) < 4 && y == jumpMidY) {
								jump = true;
								jumpCount = jumpingTime;
								jumpMid = true;
							}

						}//else move right to the jump point
						else {
							xa += moveSpeed;
							//if in the jump range then jump.
							if (Math.abs(x - jumpMidRightX) < 4 && y == jumpMidY) {
								jump = true;
								jumpCount = jumpingTime;
								jumpMid = true;
							}
						}
					}

				}
				//end of boss being on mid level

				//start of boss on top level
				//if the boss is on the top level aswell then simply 
				//follow the player.
				else if (y > topYPlatRange && y <= topY) {
					//follow the player.
					if (x >= playerX) {
						xa -= moveSpeed;
					} else {
						xa += moveSpeed;
					}
				}
				//end of boss on top level.

			}

			////////END OF PLAYER ON THE TOP LEVEL//////////////
			//DEFAULT
			//if at any point the player is in a grey area not covered by
			//this algorithm(eg in top left/right of fight area) then
			//to cover it just follow the player.
		} else {
			if (x >= playerX) {
				xa -= moveSpeed;
			} else {
				xa += moveSpeed;
			}
		}

		////////////END OF PLAYER BEING ON TOP LEVEL//////////////////

		//cover the jumping directions(6 possible)
		if (jumpCount > 0) {
			jumpCount--;
			//if we are currently jumping from bottom to mid then
			//set the directions appropriately.
			if (jumpBottom) {
				//first left
				if (x <= jumpBottomLeftX + 5 && x >= 15998) xa -= moveSpeed;
				//second left
				else if (x >= jumpBottomLeftX2 - 5 && x <= 15997) xa += moveSpeed;
				//first right
				else if (x >= jumpBottomLeftX - 5 && x <= 16272) xa += moveSpeed;
				//second right
				else if (x <= jumpBottomRightX2 + 5 && x >= 16273) xa -= moveSpeed;

				if (jumpCount == 0) jumpBottom = false;
			}

			else if (jumpMid) {
				if (x < midX) xa += moveSpeed;
				if (x > midX) xa -= moveSpeed;
				if (jumpCount == 0) jumpMid = false;
			}
		}//end of jumping directions
	}

}
