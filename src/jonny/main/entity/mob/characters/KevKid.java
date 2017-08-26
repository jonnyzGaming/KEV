package jonny.main.entity.mob.characters;

import jonny.main.entity.mob.Mob;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;

public class KevKid extends Mob {

	//basic stats
	private int animTimer;
	public boolean activated = false;

	public static boolean getMoving = false; //since kids move as same time.
	private boolean isKid1; //to determine if kid1 or kid2 for sprites.

	public int moveTimer = 0;
	public int moveTimerMax = 300;

	public int stopTimer = 0;
	public int stopTimerMax = 60;

	public int currentDir;

	public KevKid(int x, int y, boolean isKid1) {

		this.x = x;
		this.y = y;

		this.isKid1 = isKid1;

		moveSpeed = 1;

		//speech optional trigger collision 
		xTrans = -7;
		yTrans = -7;
		cWidth = 13;
		cHeight = 13;

	}

	public void update() {
		animTimer++;
		if (animTimer == 75000) animTimer = 0;

		//when moving state is set(after text seq) then move for a while
		if (getMoving) {
			move();
			moveTimer++;

			if (moveTimer == moveTimerMax) getMoving = false;

		}
		gravity(3);
	}

	public void move() {

		//move left for a period of time when we need to.
		x -= moveSpeed;

	}

	public void render(Screen screen) {

		//standstill
		if (animTimer % 50 > 25) {

			if (isKid1) sprite = Sprite.kid1_still1;
			else sprite = Sprite.kid2_still1;
		} else {

			if (isKid1) sprite = Sprite.kid1_still2;
			else sprite = Sprite.kid2_still2;
		}

		//left(if moving)
		if (getMoving) {
			if (animTimer % 30 > 15) {

				if (isKid1) sprite = Sprite.kid1_left1;
				else sprite = Sprite.kid2_left1;
			} else {
				if (isKid1) sprite = Sprite.kid2_left2;
				else sprite = Sprite.kid2_left2;
			}
		}

		screen.renderObject((int) x - 7, (int) y - 7, sprite, this);
	}

}
