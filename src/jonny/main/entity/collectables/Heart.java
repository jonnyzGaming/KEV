package jonny.main.entity.collectables;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Mob;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;

public class Heart extends Mob {

	//heart properties
	public int value;
	public int timer = -60;

	public Heart(int x, int y, int value, Sprite sprite) {

		this.x = x;
		this.y = y;
		this.value = value;
		this.sprite = sprite;

		cWidth = 10;
		cHeight = 10;
		xTrans = -5;
		yTrans = -5;

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

				if (Player.health <= 9 && !Player.ARMOR_UPGRADE1) {
					Player.health += value;
				} else if (!Player.ARMOR_UPGRADE1) {
					Player.health = 10;
				}

				if (Player.health <= 14 && Player.ARMOR_UPGRADE1 && !Player.ARMOR_UPGRADE2) {
					Player.health += value;
				} else if (Player.ARMOR_UPGRADE1 && !Player.ARMOR_UPGRADE2) {
					Player.health = 15;
				}

				if (Player.health <= 19 && Player.ARMOR_UPGRADE1 && Player.ARMOR_UPGRADE2) {
					Player.health += value;
				} else if (Player.ARMOR_UPGRADE1 && Player.ARMOR_UPGRADE2) {
					Player.health = 20;
				}

				//play sound extra

			}
		}

		gravity(3);

	}

	public void render(Screen screen) {

		screen.renderObject((int) x + xTrans, (int) y + yTrans, sprite, this);

	}
}
