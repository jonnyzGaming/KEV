package jonny.main.entity.Objects;

import jonny.main.Game;
import jonny.main.graphics.Sprite;

public class BossCage extends Solid_Object {

	//cage variables
	public int initialX, initialY; //to keep a track of the starting loc

	//will only need to be one caged door at any point in the game (only 2) so make static for conveniance.
	public static boolean cageClosed = false;
	public static boolean cageOpened = true;
	public static boolean activated = false;

	//initially set for the mash boss
	public boolean isMashCage;
	public boolean isPotatoCage;

	//to determine which boss in currently in control of the static vars.

	public BossCage(int x, int y, Sprite sprite, int cWidth, int cHeight, boolean mashOrPot) {
		super(x, y, sprite, cWidth, cHeight);
		initialY = y;
		initialX = x;

		if (mashOrPot) isMashCage = true;
		else isPotatoCage = true;

	}

	public void update() {

		if (cageOpened) {
			moveToOpen();
		}

		if (cageClosed) {
			moveToClosed();
		}

		//specific range for the mash boss
		if (isMashCage) {
			if (!activated && Game.player.x > 3015 && Game.player.y > 2260 && Game.player.y < 2436) {
				cageClosed = true;
				cageOpened = false;
				activated = true; //this is deactivated upon death
			}
		}

		if (isPotatoCage) {
			if (!activated && Game.player.x < 16320 && Game.player.x > 15000) {
				cageClosed = true;
				cageOpened = false;
				activated = true; //this is deactivated upon death
			}
		}

	}

	public void moveToClosed() {
		if (y != initialY + 32) {
			y += 1;
		}

	}

	public void moveToOpen() {
		if (y != initialY) {
			y -= 1;
		}

	}

}
