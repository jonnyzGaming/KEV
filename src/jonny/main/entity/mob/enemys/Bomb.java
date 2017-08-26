package jonny.main.entity.mob.enemys;

import jonny.main.Game;
import jonny.main.entity.Particle.Particle;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.levels.Level;
import jonny.main.sound.Sound;

//a low health egg dropped by the potato boss during his second phase, will hatch into a mini potato
//ninja after 5 seconds.
public class Bomb extends Enemy {

	//a referance to the boss the bomb belongs to to use non static stuff.
	private EvilPotato potato;

	private int explodeTime;
	private int explodeTimer = 0;
	private double healthStripRatio;
	private int animTimer = 0;

	public Bomb(int x, int y, EvilPotato potato) {
		this.x = x;
		this.y = y;
		this.potato = potato;

		health = 16;
		isEnemy = true;
		isBomb = true;
		healthStripRatio = health / 50;
		hitFadeMax = 120;
		explodeTime = 300; //5 secs
		damage = 6;

		cWidth = 15;
		cHeight = 15;
		xTrans = -7;
		yTrans = -9;

	}

	public void update() {

		explodeTimer++;
		animTimer++;

		if (explodeTimer >= explodeTime) {

			Sound.bomb.play(false);

			explode();
			remove();
		}

		//death
		if (health <= 0 || EvilPotato.healthCopy <= 0) {
			dropBasicHealth((int) x, (int) y);
			dropBasicHealth((int) x - 5, (int) y);

			remove();
		}

		if (recentlyHit) {
			hitFadeTimer = hitFadeMax;
			recentlyHit = false;
		}

		if (hitFadeTimer > 0) hitFadeTimer--;

		gravity(3);

	}

	public void render(Screen screen) {

		//cover the changing bomb animations per sec
		if (explodeTimer < 60) sprite = Sprite.bomb1;
		if (explodeTimer == 60) sprite = Sprite.bomb2;
		if (explodeTimer == 120) sprite = Sprite.bomb3;
		if (explodeTimer == 180) sprite = Sprite.bomb4;
		if (explodeTimer == 240) sprite = Sprite.bomb5;

		if (sprite == null) sprite = Sprite.bomb5; //just in case.

		//cover health bar
		if (hitFadeTimer > 0) {

			screen.renderAccurateSprite((int) x - 25, (int) y - 16, Sprite.health_bar, this);
			//render the 50 health strips
			for (int i = 1; i <= 50; i++) {
				//render the strips based on the overall health
				if (i * healthStripRatio <= health) {
					screen.renderAccurateSprite((int) x - 25 + i, (int) y - 15, Sprite.health_strip, this);
				}
			}
		}

		screen.renderObject((int) x + xTrans, (int) y + yTrans, sprite);
	}

	//particle effect explosion that deals damage in a radius to the player and potato boss.
	private void explode() {

		//radius for the explosion
		int radius = 100;

		//set of the particle explosion effect.
		Particle explosion = new Particle((int) x, (int) y, radius, 100, Sprite.bomb_particle);

		//check for the player
		double xLength = x - Game.player.x;
		double yLength = y - Game.player.y;
		double dist = (int) Math.sqrt(Math.abs((xLength * xLength) + (yLength * yLength)));

		if (dist <= radius) Player.health -= damage;

		//check for the boss.
		xLength = x - EvilPotato.xPos;
		yLength = y - EvilPotato.yPos;
		dist = (int) Math.sqrt(Math.abs((xLength * xLength) + (yLength * yLength)));

		if (dist <= radius) potato.hit(damage * 2); //more damage for potato

	}

	public void remove() {
		removed = true;
	}

	//used to remove every bomb in the game.
	public static void removeAll() {
		for (int i = 0; i < Level.enemies.size(); i++) {
			if (Level.enemies.get(i).isBomb) {
				Level.enemies.get(i).remove();
			}
		}
	}

}
