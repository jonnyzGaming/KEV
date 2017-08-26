package jonny.main.levels.tiles.core;

import jonny.main.graphics.Sprite;
import jonny.main.levels.tiles.Tile;

public class SpikeTile extends Tile {

	public boolean imminentDeath = true;

	public SpikeTile(Sprite sprite) {
		super(sprite);

	}

	public boolean isDeath() {
		return true;
	}

}
