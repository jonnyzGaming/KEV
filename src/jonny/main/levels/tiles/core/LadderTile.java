package jonny.main.levels.tiles.core;

import jonny.main.graphics.Sprite;
import jonny.main.levels.tiles.Tile;

public class LadderTile extends Tile {

	public LadderTile(Sprite sprite) {
		super(sprite);

	}
	public boolean isClimbable() {
		return true;
	}

}
