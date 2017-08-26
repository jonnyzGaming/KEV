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

public class kevsWife extends Mob {

	//basic stats
	private int animTimer;
	public static boolean activated = false;

	public static boolean sequence1 = true;
	public static boolean sequence2 = false;

	public static int delayTimer = 0;

	public kevsWife(int x, int y) {

		this.x = x;
		this.y = y;

		//speech optional trigger collision 
		xTrans = -15;
		yTrans = -12;
		cWidth = 28;
		cHeight = 23;

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

					//play sequence is enter pressed
					if (Game.keys.enter && sequence1) {
						Player.sequenceNum = 14;
						Player.sequence = true;
						activated = true;

					}

					if (Game.keys.enter && sequence2) {
						Player.sequenceNum = 15;
						Player.sequence = true;
						activated = true;

					}

				}
			}

		}

	}
	public void render(Screen screen) {
		if (animTimer % 30 > 15) {
			sprite = Sprite.wife1;
		} else {
			sprite = Sprite.wife2;
		}

		screen.renderObject((int) x - 12, (int) y - 12, sprite, this);
	}

}
