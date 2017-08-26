package jonny.main.levels.tiles.core;

import jonny.main.graphics.Sprite;
import jonny.main.levels.tiles.Tile;

public class CliffDirtTile extends Tile {

	public CliffDirtTile(Sprite sprite) {
		super(sprite);
	}

	public boolean solid() {
		return true;
	}

}
