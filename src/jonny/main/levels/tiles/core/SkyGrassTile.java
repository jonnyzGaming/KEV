package jonny.main.levels.tiles.core;

import jonny.main.graphics.Sprite;
import jonny.main.levels.tiles.Tile;

public class SkyGrassTile extends Tile {

	public SkyGrassTile(Sprite sprite) {
		super(sprite);
	}

	public boolean solid() {
		return true;
	}

}
