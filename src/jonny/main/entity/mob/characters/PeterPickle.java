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

public class PeterPickle extends Mob {

	//basic stats
	private int animTimer;
	public static boolean activated = false;
	public static boolean sequence1 = true;
	public static boolean sequence2 = false;

	public static int delayTimer = 0;

	public PeterPickle(int x, int y) {

		this.x = x;
		this.y = y;

		sprite = Sprite.peter_pickle1;

		//speech optional trigger collision 
		xTrans = -20;
		yTrans = -20;
		cWidth = 40;
		cHeight = 40;

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
						Player.sequenceNum = 24;
						Player.sequence = true;
						activated = true;

					}

					//play sequence is enter pressed
					if (Game.keys.enter && sequence2) {
						Player.sequenceNum = 25;
						Player.sequence = true;
						activated = true;

					}

				}
			}

		}

	}
	public void render(Screen screen) {
		if (animTimer % 120 > 60) {
			sprite = Sprite.peter_pickle1;
		} else {
			sprite = Sprite.peter_pickle2;
		}

		screen.renderObject((int) x - 20, (int) y - 20, sprite, this);
	}
}
