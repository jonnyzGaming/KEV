package jonny.main.entity.mob.characters;

import java.awt.Color;
import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Mob;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Animations;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.input.KeyBoard;
import jonny.main.levels.BasicLevel;

public class CabbageKing extends Mob {

	//basic stats
	private int animTimer;
	public static boolean activated = false;

	public static int seqXtrans;
	public static int seqYtrans;
	public static int seqCWidth;
	public static int seqCHeight;

	//boolean to determine number of mobs killed
	public static boolean complete = false;
	public static int VillageMobsNeeded = 0; //this is set on level setup
	public static int VillageMobsKilled = 0;

	//extra
	public static boolean startWalking = false;
	public static boolean completeWalking = false;

	public static int walkTimer = 0;
	public static int walkTimerMax = 74;

	public static boolean sequence1 = true;
	public static boolean sequence2 = false;
	public static boolean sequence3 = false;

	public static int delayTimer = 0;

	public CabbageKing(int x, int y) {

		this.x = x;
		this.y = y;

		cWidth = 40;
		cHeight = 40;

		//for initial sequence
		seqXtrans = -270;
		seqYtrans = -1;
		seqCWidth = 200;
		seqCHeight = 200;

	}

	public void update() {
		animTimer++;
		if (animTimer == 75000) animTimer = 0;

		if (delayTimer > 0) delayTimer--;

		//if all village mobs have been killed then set return sequence
		if (VillageMobsKilled == VillageMobsNeeded && !complete) {
			sequence1 = false;
			sequence2 = false;
			sequence3 = true;
			activated = false;

			//activate the cabbage queens sequence also
			CabbageQueen.activated = false;
			CabbageQueen.delayTimer = 50;
			CabbageQueen.sequence1 = false;
			CabbageQueen.sequence2 = false;
			CabbageQueen.sequence3 = true;

			complete = true;

		}

		//for initial sequence
		Rectangle seqbox = new Rectangle((int) x + seqXtrans, (int) y + seqYtrans, seqCWidth, seqCHeight);
		if (!startWalking && entityCollision(Game.player, seqbox)) {

			Player.movementDisabled = true;
			startWalking = true;

		}

		//walk, jump and then activated the sequence.
		if (startWalking && !completeWalking) {
			if (walkTimer < walkTimerMax) {
				walkTimer++;
				KeyBoard.right = true;

				if (walkTimer == 40) {
					Player.forceJump = true;
				}
			} else {

				//clear out keys array to solve bug
				for (int i = 0; i < KeyBoard.keys.length; i++) {
					KeyBoard.keys[i] = false;
				}
				Player.movementDisabled = false;
				Player.sequenceNum = 33;
				Player.sequence = true;
				activated = true;
				completeWalking = true;
			}
		}

		//for the rest of them
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

					if (Game.keys.enter && sequence2) {
						Player.sequenceNum = 34;
						Player.sequence = true;
						activated = true;

					}

					if (Game.keys.enter && sequence3) {
						Player.sequenceNum = 48;
						Player.sequence = true;
						activated = true;

					}

				}
			}

		}

	}
	public void render(Screen screen) {
		if (animTimer % 180 > 90) {
			sprite = Sprite.cabbage_king1;
		} else {
			sprite = Sprite.cabbage_king2;
		}

		screen.renderObject((int) x - 20, (int) y - 20, sprite, this);
	}
}
