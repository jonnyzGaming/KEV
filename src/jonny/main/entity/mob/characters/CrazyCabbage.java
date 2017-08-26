package jonny.main.entity.mob.characters;

import jonny.main.entity.mob.Mob;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;

public class CrazyCabbage extends Mob {

	//basic stats
	private int animTimer;
	public boolean activated = false;

	public int delayTimer = 0;

	public boolean moving = false;

	public int moveTimer = 70;
	public int moveTimerMax = 70;

	public int stopTimer = 0;
	public int stopTimerMax = 20;

	public int currentDir;

	//string that indicated what type of crazy cabbage it is.
	//NAME OPTIONS:"crazyKid" "crazyWoman" "crazyGuy"
	public String name;

	//NOTE DIR MUST BE 1 OR 2 TO START
	public CrazyCabbage(int x, int y, int dir, String name) {

		this.x = x;
		this.y = y;

		moveSpeed = 1.5;

		this.name = name;

		this.dir = dir;
		currentDir = dir;
		moving = true;

		// collision 
		if (name.equals("crazyKid")) {
			xTrans = -10;
			yTrans = -10;
			cWidth = 20;
			cHeight = 18;
		}

		if (name.equals("crazyWoman")) {
			xTrans = -15;
			yTrans = -15;
			cWidth = 30;
			cHeight = 27;
		}

		if (name.equals("crazyMale")) {
			xTrans = -15;
			yTrans = -15;
			cWidth = 30;
			cHeight = 27;
		}

	}

	public CrazyCabbage(int x, int y, int dir, String name, int moveTimerMax, int stopTimerMax) {

		this.x = x;
		this.y = y;

		moveSpeed = 1.5;

		this.name = name;

		this.moveTimerMax = moveTimerMax;
		this.stopTimerMax = stopTimerMax;
		moveTimer = moveTimerMax;

		this.dir = dir;
		currentDir = dir;
		moving = true;

		//collision
		if (name.equals("crazyKid")) {
			xTrans = -10;
			yTrans = -10;
			cWidth = 20;
			cHeight = 18;
		}

		if (name.equals("crazyWoman")) {
			xTrans = -15;
			yTrans = -15;
			cWidth = 30;
			cHeight = 27;
		}

		if (name.equals("crazyMale")) {
			xTrans = -15;
			yTrans = -15;
			cWidth = 30;
			cHeight = 27;
		}

	}

	public void update() {
		animTimer++;
		if (animTimer == 75000) animTimer = 0;

		if (delayTimer > 0) delayTimer--;

		move();
		gravity(3);
	}

	public void move() {

		//move side to side incrementally
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
		if (name.equals("crazyKid")) {
			if (dir == 0) {
				if (animTimer % 60 > 30) {
					sprite = Sprite.crazy_kid_still1;
				} else {
					sprite = Sprite.crazy_kid_still2;
				}
			}

			//left
			if (dir == 1) {
				if (animTimer % 30 > 15) {
					sprite = Sprite.crazy_kid_left1;
				} else {
					sprite = Sprite.crazy_kid_left2;
				}
			}

			//right
			if (dir == 2) {
				if (animTimer % 30 > 15) {
					sprite = Sprite.crazy_kid_right1;
				} else {
					sprite = Sprite.crazy_kid_right2;
				}
			}

			screen.renderObject((int) x - 10, (int) y - 10, sprite, this);
		}

		//crazy woman
		if (name.equals("crazyWoman")) {
			if (dir == 0) {
				if (animTimer % 60 > 30) {
					sprite = Sprite.crazy_woman_still1;
				} else {
					sprite = Sprite.crazy_woman_still2;
				}
			}

			//left
			if (dir == 1) {
				if (animTimer % 30 > 15) {
					sprite = Sprite.crazy_woman_left1;
				} else {
					sprite = Sprite.crazy_woman_left2;
				}
			}

			//right
			if (dir == 2) {
				if (animTimer % 30 > 15) {
					sprite = Sprite.crazy_woman_right1;
				} else {
					sprite = Sprite.crazy_woman_right2;
				}
			}

			screen.renderObject((int) x - 15, (int) y - 15, sprite, this);
		}

		//crazy male
		if (name.equals("crazyMale")) {
			if (dir == 0) {
				if (animTimer % 60 > 30) {
					sprite = Sprite.crazy_male_still1;
				} else {
					sprite = Sprite.crazy_male_still2;
				}
			}

			//left
			if (dir == 1) {
				if (animTimer % 30 > 15) {
					sprite = Sprite.crazy_male_left1;
				} else {
					sprite = Sprite.crazy_male_left2;
				}
			}

			//right
			if (dir == 2) {
				if (animTimer % 30 > 15) {
					sprite = Sprite.crazy_male_right1;
				} else {
					sprite = Sprite.crazy_male_right2;
				}
			}

			screen.renderObject((int) x - 15, (int) y - 15, sprite, this);
		}
	}

}
