package jonny.main.entity.collectables;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Mob;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.levels.BasicLevel;
import jonny.main.sound.Sound;

public class Coin extends Mob {

	//coin properties
	public int value;

	public int timer = -60;

	public Coin(int x, int y, int value, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.value = value;
		this.sprite = sprite;

		cWidth = 10;
		cHeight = 10;
		xTrans = -5;
		yTrans = -5;

		BasicLevel.totalCoins += value;

	}

	public void update() {

		//y animation stuff
		timer++;
		if (timer == 0) {
			y += 1;
		}
		if (timer == 40) {
			y -= 1;
			timer = -40;
		}

		if (onScreen) {
			Rectangle box = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

			if (entityCollision(Game.player, box)) {
				removed = true;
				Player.coins += value;
				Player.totalCoinCount += value;

				//play sound extra
				Sound.carrotCrunch.play(false);

			}
		}

	}

	public void render(Screen screen) {

		screen.renderObject((int) x - 5, (int) y - 5, sprite, this);
	}

}
