package jonny.main.entity.Objects;

import jonny.main.entity.mob.Mob;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;

public class Solid_Object extends Mob {

	public Solid_Object(int x, int y, Sprite sprite, int cWidth, int cHeight) {

		this.x = x;
		this.y = y;
		this.sprite = sprite;

		solid = true;

		xTrans = -(cWidth / 2);
		yTrans = -(cHeight / 2);
		this.cWidth = cWidth;
		this.cHeight = cHeight;

	}

	public Solid_Object(int x, int y, Sprite sprite) {

		this.x = x;
		this.y = y;
		this.sprite = sprite;

		solid = true;

		int cWidth = sprite.width;
		int cHeight = sprite.height;

		xTrans = -(cWidth / 2);
		yTrans = -(cHeight / 2);
		this.cWidth = cWidth;
		this.cHeight = cHeight;

	}

	public void update() {
	}

	public void render(Screen screen) {

		if (sprite.accurateSprite) {
			screen.renderAccurateSprite((int) x + xTrans, (int) y + yTrans, sprite, this);
		} else {
			screen.renderObject((int) x + xTrans, (int) y + yTrans, sprite, this);
		}
	}
}
