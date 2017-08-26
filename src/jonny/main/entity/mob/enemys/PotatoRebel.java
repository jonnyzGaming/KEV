package jonny.main.entity.mob.enemys;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Player;
import jonny.main.entity.mob.characters.CabbageKing;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.levels.Level;
import jonny.main.sound.Sound;

public class PotatoRebel extends Enemy {

	//enemy attributes not in super class.
	public boolean movingRight = true;
	private double healthStripRatio;

	private boolean hasRange = false;
	private int xStartRange;
	private int xEndRange;

	private int animTimer = 0;

	//fighting variables
	public int hitTimer = 0;
	public int hitTimerMax = 40;

	//extra that set when creating the enemys seperately
	public boolean isMoving = false;
	public boolean combatActivated = false;
	public boolean doesActivateUponSight = false;
	public boolean fightingCabbage = false;

	//sequence variables
	public static boolean sequence1 = false;
	public static int xStart1 = 10380;
	public static int yStart1 = 1561;
	public static int cWidth1 = 200;
	public static int cHeight1 = 150;

	public PotatoRebel(int xSpawn, int ySpawn, int direction, boolean combatActivated, int id) {

		//general setup
		x = xSpawn;
		y = ySpawn;
		dir = direction;

		isEnemy = true;
		health = 25;
		healthStripRatio = health / 50;
		moveSpeeds = 1.5;
		damage = 3; //when hit with sword(only 2 for cabbage)
		hitFadeMax = 120;

		isPotato = true;

		if (dir == 0) isMoving = false;
		if (dir == 1) {
			movingRight = true;
			isMoving = true;
		}
		if (dir == 2) {
			movingRight = false;
			isMoving = true;
		}

		//collision area
		cWidth = 25;
		cHeight = 30;
		xTrans = -13;
		yTrans = -17;

		this.combatActivated = combatActivated;
		this.enemyID = id;

		//needs a sprite
		sprite = Sprite.potato_rebel_still1;
	}

	public void update() {

		animTimer++;
		if (animTimer > 500000) animTimer = 0;

		//if the potato intiates combat upon sight then this is called to do it.
		if (doesActivateUponSight) {
			if (onScreen) {
				combatActivated = true;
				isMoving = true;
				doesActivateUponSight = false;
			}
		}

		//collision box for the first sequence by the fire.
		//NOTE:combat is activated for the selected potatos by their id in text box player code.
		Rectangle seq1box = new Rectangle(xStart1, yStart1, cWidth1, cHeight1);
		if (!sequence1 && entityCollision(Game.player, seq1box)) {
			Player.sequenceNum = 43;
			Player.sequence = true;
			sequence1 = true;
		}

		if (combatActivated) {
			flipDirection();

			if (isMoving) {
				//movement
				if (movingRight) {
					x += moveSpeeds;
				} else {
					x -= moveSpeeds;
				}
			}

			if (hitTimer > 0) hitTimer--;

			//check for player collision if were rendering on the screen.
			if (onScreen && !fightingCabbage) {
				Rectangle box = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

				//damage the player periodically is colliding with him
				if (entityCollision(Game.player, box)) {

					isMoving = false;
					//deal damage periodically
					if (hitTimer <= 0) {
						Player.health -= damage;

						hitTimer = hitTimerMax;
					}

				} else {
					isMoving = true;
				}

			}

			//for attacking other cabbages
			Rectangle box = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

			//damage the potato if colliding with ones
			for (int i = 0; i < Level.enemies.size(); i++) {

				//if we are colliding with a potato then start dealing damage and go into fighing.
				if (Level.enemies.get(i).isCabbage && entityCollision(Level.enemies.get(i), box)) {
					fightingCabbage = true;
					isMoving = false;
					//deal damage periodically
					if (hitTimer <= 0) {
						Level.enemies.get(i).hit(2);
						hitTimer = hitTimerMax;
					}
					break;
				} else {
					fightingCabbage = false;
				}
			}

			//remove if is dead
			if (health <= 0) {

				remove();
				int dir = Game.rand.nextInt(2);

				//spawn a mash in the rebels place
				Mr_mash mash = new Mr_mash((int) x, (int) y, dir, true);
				mash.enemyID = -1;
				mash.init(level);
				Level.enemies.add(mash);
				Level.add(mash);

				//increment number of mobs killed
				CabbageKing.VillageMobsKilled++;

				Sound.potatoDeath.play(false);
			}

			if (recentlyHit) {
				hitFadeTimer = hitFadeMax;
				recentlyHit = false;
			}

			if (hitFadeTimer > 0) hitFadeTimer--;

			//if not cb activate yet then make sure health is full all time
		} else {
			health = 25;
		}

		//gravity
		gravity(3);

	}

	public void flipDirection() {

		//if on the screen or of particular id in range of player go towards the players position
		if ((onScreen && !fightingCabbage) || (enemyID == 1 && Math.abs(Game.player.x - x) > 550 && !fightingCabbage)) {
			hasSeenPlayer = true;
			if (Game.player.x > x) movingRight = true;
			if (Game.player.x < x) movingRight = false;
		}

		if (collision(2, 0) || objectCollision(2, 0) || doorCollision(2, 0)) {
			movingRight = false;
			return;
		}

		if (collision(-2, 0) || objectCollision(-2, 0) || doorCollision(-2, 0)) {
			movingRight = true;
			return;
		}

		//to stop them moving through each other after theyve come in contact with the player
		if (movingRight && hasSeenPlayer && EnemyCollision(4, 0, this) && (!collision(1, 0) && !objectCollision(1, 0) && !doorCollision(1, 0))) {
			movingRight = false;
			return;
		}

		if (!movingRight && hasSeenPlayer && EnemyCollision(-4, 0, this) && (!collision(-1, 0) && !objectCollision(-1, 0) && !doorCollision(-1, 0))) {
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

		//animation
		//standing still when not moving and not activated.
		if (!isMoving && !combatActivated) {
			if (animTimer % 60 > 30) {
				sprite = Sprite.potato_rebel_still1;
			} else {
				sprite = Sprite.potato_rebel_still2;
			}

		}
		//moving right 
		else if (isMoving && movingRight) {
			if (animTimer % 30 > 15) {
				sprite = Sprite.potato_rebel_right1;
			} else {
				sprite = Sprite.potato_rebel_right2;
			}

		}

		//moving left 
		else if (isMoving && !movingRight) {
			if (animTimer % 30 > 15) {
				sprite = Sprite.potato_rebel_left1;
			} else {
				sprite = Sprite.potato_rebel_left2;
			}

		}
		//attacking right
		if (!isMoving && combatActivated && movingRight && fightingCabbage) {
			if (animTimer % 20 > 10) {
				sprite = Sprite.potato_rebel_attack_right1;
			} else {
				sprite = Sprite.potato_rebel_attack_right2;
			}

		}

		//attacking left
		if (!isMoving && combatActivated && !movingRight && fightingCabbage) {
			if (animTimer % 30 > 15) {
				sprite = Sprite.potato_rebel_attack_left1;
			} else {
				sprite = Sprite.potato_rebel_attack_left2;
			}
		}

		screen.renderObject((int) x - 17, (int) y - 17, sprite, this);

		if (hitFadeTimer > 0) {

			screen.renderAccurateSprite((int) x - 24, (int) y - 25, Sprite.health_bar, this);
			//render the 50 health strips
			for (int i = 1; i <= 50; i++) {
				//render the strips based on the overall health
				if (i * healthStripRatio <= health) {
					screen.renderAccurateSprite((int) x - 24 + i, (int) y - 24, Sprite.health_strip, this);
				}
			}
		}
	}

}
