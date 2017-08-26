package jonny.main.levels.tiles.core;

import jonny.main.graphics.Sprite;
import jonny.main.levels.tiles.Tile;

public class WaterTile extends Tile {

	public WaterTile(Sprite sprite) {
		super(sprite);
	}

	public boolean isWater() {
		return true;
	}

}
