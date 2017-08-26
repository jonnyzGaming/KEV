package jonny.main.entity.mob.enemys;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.levels.Level;
import jonny.main.sound.Sound;

//a friendly
public class CabbageGuard extends Enemy {

	//general properties

	public boolean movingRight = true;
	private double healthStripRatio;

	private int animTimer = 0;

	//random movement variables if needed
	public boolean randomized = false;
	public int moveTimer = 70;
	public int moveTimerMax = 70;

	public int stopTimer = 0;
	public int stopTimerMax = 20;

	//fighting variables
	public int hitTimer = 0;
	public int hitTimerMax = 40;

	//extra that set when creating the enemys seperately
	public boolean isMoving = false;
	public boolean combatActivated = false;
	public boolean doesActivateUponSight = false;
	public boolean fightingPot = false;

	public CabbageGuard(int xSpawn, int ySpawn, int direction, boolean combatActivated, int id) {

		//general setup
		x = xSpawn;
		y = ySpawn;
		dir = direction;

		isEnemy = true;
		health = 20;
		healthStripRatio = health / 50;
		moveSpeeds = 1.2;
		damage = 1.6; //when hit with sword
		hitFadeMax = 120;

		isCabbage = true;

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
		xTrans = -17;
		yTrans = -14;
		cWidth = 32;
		cHeight = 26;

		this.combatActivated = combatActivated;
		this.enemyID = id;

		//needs a sprite
		sprite = Sprite.cabbage_guard1;
	}

	public void update() {

		animTimer++;
		if (animTimer > 500000) animTimer = 0;

		//activating on site.
		if (doesActivateUponSight) {
			if (onScreen) {
				combatActivated = true;
				isMoving = true;
				doesActivateUponSight = false;
			}
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

			//check for any potato collisions when on screen.
			if (onScreen || hasSeenPlayer) {
				Rectangle box = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

				//damage the potato if colliding with ones
				for (int i = 0; i < Level.enemies.size(); i++) {

					//if we are colliding with a potato then start dealing damage and go into fighing.
					if (Level.enemies.get(i).isPotato && entityCollision(Level.enemies.get(i), box)) {
						fightingPot = true;
						isMoving = false;
						//deal damage periodically
						if (hitTimer <= 0) {
							Level.enemies.get(i).hit(damage);
							hitTimer = hitTimerMax;
						}
						break;

					} else {
						fightingPot = false;
					}

				}
			}

			//remove if is dead
			if (health <= 0) {
				remove();
				Sound.cabbageDeath.play(false);
			}

			if (recentlyHit) {
				hitFadeTimer = hitFadeMax;
				recentlyHit = false;
			}

			if (hitFadeTimer > 0) hitFadeTimer--;

		}

		//gravity
		gravity(3);

	}
	public void flipDirection() {

		//if on the screen
		if (onScreen) {
			hasSeenPlayer = true;
		}

		//for collision priority
		if (collision(2, 0) || objectCollision(2, 0) || doorCollision(2, 0)) {
			movingRight = false;
			return;
		}

		if (collision(-2, 0) || objectCollision(-2, 0) || doorCollision(-2, 0)) {
			movingRight = true;
			return;
		}

		//move towards potato if on screen with player.
		if (!fightingPot) {
			for (int i = 0; i < Level.enemies.size(); i++) {
				//move towards the first potato we can find thats on screen
				if (Level.enemies.get(i).isPotato && Level.enemies.get(i).hasSeenPlayer) {
					if (Level.enemies.get(i).x > x && Math.abs(y - Level.enemies.get(i).y) < 25) movingRight = true;
					if (Level.enemies.get(i).x <= x && Math.abs(y - Level.enemies.get(i).y) < 25) movingRight = false;
					isMoving = true;
					return;
				}
			}
		}

		//move randomly (favour towards a potato though)
		if (Game.rand.nextInt(50) == 49 && !fightingPot) {
			//amount of time to move
			if (movingRight) {
				movingRight = false;
			} else {
				movingRight = true;
			}
			isMoving = true;

			if (Game.rand.nextInt(3) == 1) isMoving = false;

		}

	}
	public void remove() {
		removed = true;
	}

	public void render(Screen screen) {

		//animation
		//standing still when not moving
		if (!isMoving) {
			if (animTimer % 60 > 30) {
				sprite = Sprite.cabbage_guard1;
			} else {
				sprite = Sprite.cabbage_guard2;
			}

		}
		//moving right 
		else if (isMoving && movingRight) {
			if (animTimer % 30 > 15) {
				sprite = Sprite.cabbage_guard_right1;
			} else {
				sprite = Sprite.cabbage_guard_right2;
			}

		}

		//moving left 
		else if (isMoving && !movingRight) {
			if (animTimer % 30 > 15) {
				sprite = Sprite.cabbage_guard_left1;
			} else {
				sprite = Sprite.cabbage_guard_left2;
			}

		}

		//attacking right
		if (!isMoving && combatActivated && movingRight && fightingPot) {
			if (animTimer % 20 > 10) {
				sprite = Sprite.cabbage_guard_attack_right1;
			} else {
				sprite = Sprite.cabbage_guard_attack_right2;
			}

		}

		//attacking left
		if (!isMoving && combatActivated && !movingRight && fightingPot) {
			if (animTimer % 30 > 15) {
				sprite = Sprite.cabbage_guard_attack_left1;
			} else {
				sprite = Sprite.cabbage_guard_attack_left2;
			}
		}

		screen.renderObject((int) x - 20, (int) y - 20, sprite, this);

		if (hitFadeTimer > 0) {

			screen.renderAccurateSprite((int) x - 24, (int) y - 20, Sprite.health_bar, this);
			//render the 50 health strips
			for (int i = 1; i <= 50; i++) {
				//render the strips based on the overall health
				if (i * healthStripRatio <= health) {
					screen.renderAccurateSprite((int) x - 24 + i, (int) y - 19, Sprite.health_strip, this);
				}
			}
		}
	}

}
