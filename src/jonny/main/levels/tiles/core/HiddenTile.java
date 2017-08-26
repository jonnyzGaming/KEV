package jonny.main.levels.tiles.core;

import jonny.main.graphics.Sprite;
import jonny.main.levels.tiles.Tile;

public class HiddenTile extends Tile {

	public HiddenTile(Sprite sprite) {
		super(sprite);
	}

	public boolean isHidden() {
		return true;
	}

}
