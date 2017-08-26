package jonny.main.entity.mob.characters;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Mob;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;

public class Mr_mushroom extends Mob {

	private int animTimer;
	public boolean activated = false;

	public Mr_mushroom(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;

		xTrans = -15;
		yTrans = -15;

		cWidth = 150;
		cHeight = 100;

	}

	public void update() {
		animTimer++;
		if (animTimer == 75000) animTimer = 0;

		if (onScreen) {
			Rectangle box = new Rectangle((int) x - (cWidth / 2), (int) y - (cHeight / 2), cWidth, cHeight);

			if (!activated) {
				if (entityCollision(Game.player, box)) {
					Player.sequenceNum = 1;
					Player.sequence = true;
					activated = true;
				}
			}

		}

	}
	public void render(Screen screen) {

		if (animTimer % 60 > 30) {
			sprite = Sprite.mr_mushroom1;
		} else {
			sprite = Sprite.mr_mushroom2;
		}

		screen.renderObject((int) x + xTrans, (int) y + yTrans, sprite, this);
	}
}
