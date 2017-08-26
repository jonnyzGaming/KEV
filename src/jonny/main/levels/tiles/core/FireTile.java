package jonny.main.levels.tiles.core;

import jonny.main.graphics.Sprite;
import jonny.main.levels.tiles.Tile;

public class FireTile extends Tile {

	public int animTimer = 0;

	public FireTile(Sprite sprite, Sprite sprite2) {
		super(sprite, sprite2);
	}

	public boolean isFire() {
		return true;
	}

	public void update() {

		animTimer++;
		if (animTimer > 10000) animTimer = 0;

		if (animTimer % 30 > 15) {
			sprite = sprite1;
		} else {
			sprite = sprite2;
		}

	}

}
