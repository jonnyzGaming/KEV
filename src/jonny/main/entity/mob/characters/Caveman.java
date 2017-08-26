package jonny.main.entity.mob.characters;

import java.awt.Color;
import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Mob;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Animations;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.levels.BasicLevel;

public class Caveman extends Mob {

	//basic stats
	private int animTimer;
	public static boolean activated = false;

	public static boolean sequence1 = true;
	public static boolean sequence2 = false;
	public static boolean sequence3 = false;

	public static int delayTimer = 0;

	public Caveman(int x, int y, Sprite sprite) {

		this.x = x;
		this.y = y;
		this.sprite = sprite;

		//speech optional trigger collision 
		xTrans = -12;
		yTrans = -19;
		cWidth = 23;
		cHeight = 38;

	}

	public void update() {
		animTimer++;
		if (animTimer == 75000) animTimer = 0;

		if (delayTimer > 0) delayTimer--;

		if (onScreen) {
			Rectangle box = new Rectangle((int) x - (cWidth / 2), (int) y - (cHeight / 2), cWidth, cHeight);

			if (!activated && delayTimer == 0) {
				if (entityCollision(Game.player, box)) {

					//basic rollover animation
					BasicLevel.text = "Enter to interect";
					BasicLevel.xPos = 340;
					BasicLevel.yPos = 320;
					BasicLevel.col = Color.black;

					Game.DISPLAY_PLAIN = true;
					Game.displayLevel = true;
					Animations.displayTextTime = 5;
					Animations.displayText = true;
					Animations.displayTextTimer = 0;

					//play sequence if enter pressed
					if (Game.keys.enter && sequence1) {
						Player.sequenceNum = 5;
						Player.sequence = true;
						activated = true; //only tempory until sequence ends

					}

					if (Game.keys.enter && sequence2) {
						Player.sequenceNum = 6;
						Player.sequence = true;
						activated = true; //only tempory until sequence ends

					}

					if (Game.keys.enter && sequence3) {
						Player.sequenceNum = 7;
						Player.sequence = true;
						activated = true; //only tempory until sequence ends

					}

				}
			}

		}

	}
	public void render(Screen screen) {

		if (animTimer % 60 > 30) {
			sprite = Sprite.cave_man1;
		} else {
			sprite = Sprite.cave_man2;
		}

		screen.renderAccurateSprite((int) x + xTrans, (int) y + yTrans, sprite, this);
	}
}
