package jonny.main.entity.mob.characters;

import jonny.main.entity.mob.Mob;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;

public class CabbageKidKite extends Mob {

	//basic stats
	private int animTimer;
	public boolean activated = false;

	public int delayTimer = 0;

	public boolean moving = false;

	public int moveTimer = 120;
	public int moveTimerMax = 120;

	public int stopTimer = 0;
	public int stopTimerMax = 60;

	public int currentDir;

	public CabbageKidKite(int x, int y, int dir) {

		this.x = x;
		this.y = y;

		sprite = Sprite.cabbage_kid_kite_still1;

		moveSpeed = 1.5;

		this.dir = dir;
		currentDir = dir;
		moving = true;

		//speech optional trigger collision 
		xTrans = -4;
		yTrans = -4;
		cWidth = 20;
		cHeight = 15;

	}

	public void update() {
		animTimer++;
		if (animTimer == 75000) animTimer = 0;

		if (delayTimer > 0) delayTimer--;

		move();
		gravity(3);
	}

	public void move() {

		//move side to side with the kite incrementally
		if (moveTimer > 0 && moving) {
			dir = currentDir;
			if (dir == 1) {
				x -= moveSpeed;
			}

			if (dir == 2) {
				x += moveSpeed;
			}

			moveTimer--;
			return;
		}

		if (moveTimer <= 0 && stopTimer == 0) {
			dir = 0;
			stopTimer = stopTimerMax;
			moving = false;
			return;
		}

		if (stopTimer > 0 && !moving) stopTimer--;

		if (stopTimer == 0 && !moving) {
			moveTimer = moveTimerMax;
			moving = true;
			if (currentDir == 1) {
				currentDir = 2;
				return;
			}
			if (currentDir == 2) {
				currentDir = 1;
				return;
			}

		}

	}
	public void render(Screen screen) {

		//standstill
		if (dir == 0) {
			if (animTimer % 60 > 30) {
				sprite = Sprite.cabbage_kid_kite_still1;
			} else {
				sprite = Sprite.cabbage_kid_kite_still2;
			}
		}

		//left
		if (dir == 1) {
			if (animTimer % 30 > 15) {
				sprite = Sprite.cabbage_kid_kite_left1;
			} else {
				sprite = Sprite.cabbage_kid_kite_left2;
			}
		}

		//right
		if (dir == 2) {
			if (animTimer % 30 > 15) {
				sprite = Sprite.cabbage_kid_kite_right1;
			} else {
				sprite = Sprite.cabbage_kid_kite_right2;
			}
		}

		screen.renderObject((int) x - 14, (int) y - 14, sprite, this);
	}

}
