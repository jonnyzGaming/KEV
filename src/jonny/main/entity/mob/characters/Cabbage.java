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

public class Cabbage extends Mob {

	//basic stats
	private int animTimer;
	public static boolean activated = false;

	public static boolean sequence1 = true;
	public static boolean sequence1ActivatedOnce = false;

	public static boolean sequence2 = false;
	public static boolean sequence3 = false;

	public static boolean sequence4 = false;

	public static boolean finnishedAll = false;

	public static int delayTimer = 0;

	public Cabbage(int x, int y) {

		this.x = x;
		this.y = y;

		//speech optional trigger collision 
		xTrans = -20;
		yTrans = -20;
		cWidth = 40;
		cHeight = 37;

	}

	public void update() {
		animTimer++;
		if (animTimer == 75000) animTimer = 0;

		if (delayTimer > 0) delayTimer--;

		//if has already talked to once then go to seq 3 if have tokens
		if (Player.gotMashBossTokens && Player.gotLarryTokens && sequence1ActivatedOnce) {
			sequence1 = false;
			sequence2 = false;
			sequence3 = true;
			sequence4 = false;
		}

		//if has tokens and not talked to before seq 4
		if (Player.gotMashBossTokens && Player.gotLarryTokens && !sequence1ActivatedOnce) {
			sequence1 = false;
			sequence2 = false;
			sequence3 = false;
			sequence4 = true;
		}

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
					if (Game.keys.enter && sequence1 && !finnishedAll) {
						Player.sequenceNum = 16;
						Player.sequence = true;
						activated = true;

					}

					if (Game.keys.enter && sequence2 && !finnishedAll) {
						Player.sequenceNum = 17;
						Player.sequence = true;
						activated = true;

					}

					if (Game.keys.enter && sequence3 && !finnishedAll) {
						Player.sequenceNum = 18;
						Player.sequence = true;
						activated = true;

					}

					if (Game.keys.enter && sequence4 && !finnishedAll) {
						Player.sequenceNum = 19;
						Player.sequence = true;
						activated = true;

					}

				}
			}

		}

	}
	public void render(Screen screen) {
		if (animTimer % 60 > 30) {
			sprite = Sprite.cabbage_guard1;
		} else {
			sprite = Sprite.cabbage_guard1;
		}

		screen.renderObject((int) x - 20, (int) y - 20, sprite, this);
	}

}
