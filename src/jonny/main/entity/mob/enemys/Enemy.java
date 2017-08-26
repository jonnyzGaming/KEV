package jonny.main.entity.mob.enemys;

import jonny.main.entity.collectables.Coin;
import jonny.main.entity.collectables.Heart;
import jonny.main.entity.mob.Mob;
import jonny.main.graphics.Sprite;
import jonny.main.levels.Level;

public abstract class Enemy extends Mob {

	//id(mainly for village mobs)
	//NOTE: -1 ids mean that they can be walked through by other enemys, 
	//-2 means its a friendly, used for projectiles for player(so cant shoot them)
	//1 is for potatos that are activated by the first sequecne
	//2 is for potatoes that are normal (not act by seq)but when seen.
	public int enemyID = 0;

	//extra stuff
	public boolean isPotato = false;
	public boolean isCabbage = false;
	public boolean isBomb = false;

	//general enemy properties
	protected double health;
	protected double damage;
	protected double moveSpeeds;
	protected boolean recentlyHit = false;
	protected int hitFadeMax;
	protected int hitFadeTimer = 0;

	//only used for village mobs really
	public boolean hasSeenPlayer = false;

	public boolean dropsMoney = false;

	//inflict the set damage on the enemy
	public void hit(double damage) {
		health -= damage;
		recentlyHit = true;
	}

	public void remove() {
		removed = true;
	}

	//avoid collision with all other enemies
	public boolean EnemyCollision(int x, int y, Enemy e) {

		boolean collision = false;

		//temp
		e.x += x;
		e.y += y;

		for (int i = 0; i < Level.enemies.size(); i++) {

			if (Level.enemies.get(i) != e && entityCollision(e, Level.enemies.get(i).getCollisionRec()) && Level.enemies.get(i).enemyID != -1 && Level.enemies.get(i).enemyID != -2) {
				collision = true;
			}

		}

		e.x -= x;
		e.y -= y;

		return collision;

	}

	public void dropBasicHealth(int x, int y) {

		Heart heart = new Heart(x, y, 1, Sprite.heart);
		heart.init(level);
		level.add(heart);

	}

	public void dropMoney(int x, int y) {

		Coin c = new Coin(x, y, 1, Sprite.coin);
		c.init(level);
		level.add(c);
	}

	public void dropGoldMoney(int x, int y) {

		Coin c = new Coin(x, y, 5, Sprite.goldCoin);
		c.init(level);
		level.add(c);
	}

}
