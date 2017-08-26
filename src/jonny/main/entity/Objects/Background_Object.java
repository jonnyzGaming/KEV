package jonny.main.entity.Objects;

import jonny.main.entity.mob.Mob;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;

public class Background_Object extends Mob {

	//stats for multy sprite objects
	private boolean multipleSprites = false;
	public Sprite sprite1;
	public Sprite sprite2;
	public int animTimer = 0;

	public Background_Object(int x, int y, Sprite sprite, int width, int height) {

		this.x = x;
		this.y = y;
		this.sprite = sprite;

		solid = false;

		cWidth = width;
		cHeight = height;

	}

	//optimized best version, other is keep only cause to lazy to change all.
	public Background_Object(int x, int y, Sprite sprite) {

		this.x = x;
		this.y = y;
		this.sprite = sprite;

		solid = false;

		if (sprite.accurateSprite) {
			cWidth = sprite.width;
			cHeight = sprite.height;
		} else {
			cWidth = sprite.size;
			cHeight = sprite.size;
		}

	}

	public Background_Object(int x, int y, Sprite sprite1, Sprite sprite2) {

		this.x = x;
		this.y = y;
		this.sprite = sprite1;

		this.sprite1 = sprite1;
		this.sprite2 = sprite2;

		multipleSprites = true;

		solid = false;

		if (sprite.accurateSprite) {
			cWidth = sprite.width;
			cHeight = sprite.height;
		} else {
			cWidth = sprite.size;
			cHeight = sprite.size;
		}

	}

	public void update() {

		if (multipleSprites) {

			animTimer++;
			if (animTimer > 10000) animTimer = 0;

			if (animTimer % 50 > 25) {
				sprite = sprite1;
			} else {
				sprite = sprite2;
			}

		}
	}

	public void render(Screen screen) {

		if (sprite.accurateSprite) {
			screen.renderAccurateSprite((int) x - (cWidth / 2), (int) y - (cHeight / 2), sprite, this);
		} else {
			screen.renderObject((int) x - (cWidth / 2), (int) y - (cHeight / 2), sprite, this);
		}
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite newsprite) {
		sprite = newsprite;
	}

}
