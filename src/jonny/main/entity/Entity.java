package jonny.main.entity;

import jonny.main.graphics.Screen;
import jonny.main.levels.Level;

public abstract class Entity {

	//all entitys properties
	public double x, y;
	protected boolean removed = false;
	protected boolean solid = false;
	public Level level;

	//extra stuff as needed
	public boolean isCheckpoint = false;

	public void update() {
	}

	public void render(Screen screen) {
	}

	public boolean isRemoved() {
		return removed;
	}

	protected void remove() {
		removed = true;
	}

	public boolean isSolid() {
		return solid;
	}

	public void init(Level level) {
		this.level = level;
	}
}
