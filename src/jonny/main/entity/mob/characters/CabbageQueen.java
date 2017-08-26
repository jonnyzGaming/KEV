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

public class CabbageQueen extends Mob {

	//basic stats
	private int animTimer;
	public static boolean activated = false;

	public static boolean sequence1 = true;
	public static boolean sequence2 = false;
	public static boolean sequence3 = false;
	public static boolean sequence4 = false;

	public static boolean crying = false;
	public static boolean happy = false;

	public static int delayTimer = 0;

	public CabbageQueen(int x, int y) {

		this.x = x;
		this.y = y;

		sprite = Sprite.cabbage_queen;

		//speech optional trigger collision 
		xTrans = -15;
		yTrans = -15;
		cWidth = 30;
		cHeight = 30;

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
						Player.sequenceNum = 38;
						Player.sequence = true;
						activated = true;
					}

					if (Game.keys.enter && sequence2) {
						Player.sequenceNum = 39;
						Player.sequence = true;
						activated = true;
					}

					if (Game.keys.enter && sequence3) {
						Player.sequenceNum = 40;
						Player.sequence = true;
						activated = true;
					}

					if (Game.keys.enter && sequence4) {
						Player.sequenceNum = 41;
						Player.sequence = true;
						activated = true;
					}

				}
			}

		}

	}
	public void render(Screen screen) {

		sprite = Sprite.cabbage_queen_concerned;

		if (happy) sprite = Sprite.cabbage_queen;

		if (crying) {
			if (animTimer % 60 > 30) {
				sprite = Sprite.cabbage_queen_crying1;
			} else {
				sprite = Sprite.cabbage_queen_crying2;
			}
		}

		screen.renderObject((int) x - 15, (int) y - 15, sprite, this);
	}

}
