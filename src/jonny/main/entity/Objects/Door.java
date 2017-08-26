package jonny.main.entity.Objects;

import java.awt.Color;
import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.collectables.Key;
import jonny.main.entity.mob.Mob;
import jonny.main.graphics.Animations;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.levels.BasicLevel;
import jonny.main.sound.Sound;

public class Door extends Mob {

	//door stats
	public boolean opened = false;
	public Key key;
	public Sprite doorOpened;

	public boolean isNormalDoor = true;
	public boolean isBigVillageDoor = false;
	public boolean isBigVillageDoor2 = false;

	public boolean setBigDoorStats = false;
	public static boolean bigOpened = false;

	public boolean openFromRight = false;
	public boolean openFromLeft = false;

	public Door(int x, int y, Sprite doorOpened, Sprite doorClosed, Key key) {
		this.x = x;
		this.y = y;
		this.sprite = doorClosed;
		this.doorOpened = doorOpened;

		this.key = key;

		solid = true;

		if (isNormalDoor) {
			cWidth = 3;
			cHeight = 31;
			xTrans = -2;
			yTrans = -16;
		}
	}

	//for doors that open without a key
	public Door(int x, int y, Sprite doorOpened, Sprite doorClosed) {

		this.x = x;
		this.y = y;
		this.sprite = doorClosed;
		this.doorOpened = doorOpened;

		solid = false;

		if (isNormalDoor) {
			cWidth = 3;
			cHeight = 31;
			xTrans = -2;
			yTrans = -16;
		}
	}

	public void update() {
		if ((isBigVillageDoor || isBigVillageDoor2) && !setBigDoorStats) {
			cWidth = 7;
			cHeight = 48;
			xTrans = -4;
			yTrans = -24;
			solid = true;
			setBigDoorStats = true;
		}

		if (onScreen && (!opened)) {
			Rectangle box = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

			if (entityCollision(Game.player, box) && isNormalDoor) {
				opened = true;
				//play sound extra
				Sound.doorOpen.play(false);

				if (openFromRight && isNormalDoor) x += 6;
				if (openFromLeft && isNormalDoor) x -= 7;

				if (isNormalDoor) {
					cWidth = 16;
					cHeight = 32;
					xTrans = -8;
					yTrans = -16;
				}

				if (key != null) {
					BasicLevel.text = "Door unlocked!";
					BasicLevel.xPos = 10;
					BasicLevel.yPos = 500;
					BasicLevel.col = Color.green;

					Game.DISPLAY_PLAIN = true;
					Game.displayLevel = true;
					Animations.displayTextTime = 120;
					Animations.displayText = true;
					Animations.displayTextTimer = 0;

				}

			}

			//for the village doors
			//first door
			if (openFromRight && isBigVillageDoor && bigOpened) {
				//x += 17;
				sprite = doorOpened;
				solid = false;
				bigOpened = false;

				opened = true;
			}
			if (openFromLeft && isBigVillageDoor && bigOpened) {
				//x -= 17;
				sprite = doorOpened;
				solid = false;
				bigOpened = false;
				opened = true;
			}

			//duplicate code opening for second door
			if (openFromRight && isBigVillageDoor2 && bigOpened) {
				//x += 17;
				sprite = doorOpened;
				solid = false;
				bigOpened = false;

				opened = true;
			}
			if (openFromLeft && isBigVillageDoor2 && bigOpened) {
				//x -= 17;
				sprite = doorOpened;
				solid = false;
				bigOpened = false;
				opened = true;
			}
		}

		if (key != null) {
			if (key.taken) {
				solid = false;
			}
		}

	}
	public void render(Screen screen) {

		//change the sprite here,put another one in the constructor
		if (opened) {
			sprite = doorOpened;

		}

		if (sprite.accurateSprite) {
			screen.renderAccurateSprite((int) x + xTrans, (int) y + yTrans, sprite, this);
		} else {
			screen.renderObject((int) x + xTrans, (int) y + yTrans, sprite, this);
		}

	}

	public boolean isDoor() {
		return true;
	}
}
