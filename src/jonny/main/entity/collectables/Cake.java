package jonny.main.entity.collectables;

import java.awt.Color;
import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Mob;
import jonny.main.entity.mob.characters.cabbageChef;
import jonny.main.graphics.Animations;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.levels.BasicLevel;
import jonny.main.sound.Sound;

public class Cake extends Mob {

	//key properties
	public boolean taken = false;
	public int timer = -60;

	public Cake(int x, int y) {
		this.x = x;
		this.y = y;

		sprite = Sprite.cake;

		cWidth = 22;
		cHeight = 12;
		xTrans = -11;
		yTrans = -6;
	}

	public void update() {
		//y animation stuff
		if (!taken) {
			timer++;
			if (timer == 0) {
				y += 1;
			}
			if (timer == 40) {
				y -= 1;
				timer = -40;
			}
		}

		if (onScreen && !taken) {
			Rectangle box = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

			if (entityCollision(Game.player, box)) {
				taken = true;

				//change cabbage chef sequence to obtained cake one.
				cabbageChef.sequence3 = true;
				cabbageChef.sequence2 = false;
				cabbageChef.sequence1 = false;

				//play a basic text animation 
				BasicLevel.text = "Cake obtained!";
				BasicLevel.xPos = 10;
				BasicLevel.yPos = 500;
				BasicLevel.col = Color.black;

				Game.DISPLAY_PLAIN = true;
				Game.displayLevel = true;
				Animations.displayTextTime = 180;
				Animations.displayText = true;
				Animations.displayTextTimer = 0;

				//play sound extra
				Sound.cakePickup.play(false);

			}
		}

	}
	public void render(Screen screen) {

		if (!taken) {
			if (sprite.accurateSprite) {
				screen.renderAccurateSprite((int) x + xTrans, (int) y + yTrans, sprite, this);
			} else {
				screen.renderObject((int) x + xTrans, (int) y + yTrans, sprite, this);
			}
		}
	}

}
