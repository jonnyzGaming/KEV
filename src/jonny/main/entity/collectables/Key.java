package jonny.main.entity.collectables;

import java.awt.Color;
import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Mob;
import jonny.main.graphics.Animations;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.levels.BasicLevel;
import jonny.main.sound.Sound;

public class Key extends Mob {

	//keys defined in level setup(note to self)

	//key properties
	public String keyName;
	public boolean taken = false;
	public int timer = -60;

	public Key(int x, int y, Sprite sprite, String keyName) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.keyName = keyName;

		cWidth = 13;
		cHeight = 13;
		xTrans = -6;
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

				//play a basic text animation 
				BasicLevel.text = keyName + " Obtained!";
				BasicLevel.xPos = 10;
				BasicLevel.yPos = 500;
				BasicLevel.col = Color.black;

				Game.DISPLAY_PLAIN = true;
				Game.displayLevel = true;
				Animations.displayTextTime = 180;
				Animations.displayText = true;
				Animations.displayTextTimer = 0;

				//play sound extra
				Sound.keyPickup.play(false);

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
