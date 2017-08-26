package jonny.main.graphics;

import java.util.Random;

import jonny.main.entity.mob.Mob;
import jonny.main.menus.BackGround;

public class Screen {

	//screen properties
	public static final int TILE_SIZE = 16;
	public static final int TILE_SHIFT = 4;
	public static int width, height;
	public int[] pixels;

	//map offset values
	public static int xOffset, yOffset;

	//extra properties for testing
	public Random rand = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[width * height];
	}

	//sets all the screen pixels the background color(sky).
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xff309EFF;
		}
	}

	//for general tiles etc
	public void renderObject(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;

		for (int y = 0; y < sprite.size; y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.size; x++) {
				int xa = x + xp;
				if (xa < -sprite.size || xa >= width || ya < 0 || ya >= height) break; //only render the tiles in bounds of screen

				if (xa < 0) xa = 0; //ensure sprites are rendering smoothly at screen bounds

				int col = sprite.pixels[x + y * sprite.size];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;

			}
		}
	}

	///////ON SCREEN NEED RENDERING METHODS////////////////
	//for rendering objects that need a check to see if there on screen or not(eg enemies)
	public void renderObject(int xp, int yp, Sprite sprite, Mob e) {
		xp -= xOffset;
		yp -= yOffset;

		for (int y = 0; y < sprite.size; y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.size; x++) {
				int xa = x + xp;
				if (xa < -sprite.size || xa >= width || ya < 0 || ya >= height) {
					e.onScreen = false;
					break; //only render in bounds of screen
				}

				if (xa < 0) xa = 0; //ensure sprites are rendering smoothly at screen bounds
				e.onScreen = true;

				int col = sprite.pixels[x + y * sprite.size];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;

			}
		}
	}

	public void renderAccurateSprite(int xp, int yp, Sprite sprite, Mob e) {
		xp -= xOffset;
		yp -= yOffset;

		for (int y = 0; y < sprite.height; y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.width; x++) {
				int xa = x + xp;
				if (xa < -sprite.width || xa >= width || ya < 0 || ya >= height) {
					e.onScreen = false;
					break; //only render in bounds of screen
				}

				if (xa < 0) xa = 0; //ensure sprites are rendering smoothly at screen bounds
				e.onScreen = true;

				int col = sprite.pixels[x + y * sprite.width];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;

			}
		}

	}
	///////ON SCREEN NEED RENDERING METHODS////////////////

	//renders sprites that are not offsetted according to the maps position(fixed loc)
	public void renderBasicSprite(int xp, int yp, Sprite sprite) {

		for (int y = 0; y < sprite.size; y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.size; x++) {
				int xa = x + xp;

				if (xa < -sprite.width || xa >= width || ya < 0 || ya >= height) {
					break; //only render in bounds of screen
				}

				if (xa < 0) xa = 0; //ensure sprites are rendering smoothly at screen bounds

				int col = sprite.pixels[x + y * sprite.size];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}

		}
	}

	public void renderBasicAccurateSprite(int xp, int yp, Sprite sprite) {
		for (int y = 0; y < sprite.height; y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.width; x++) {
				int xa = x + xp;

				if (xa < -sprite.width || xa >= width || ya < 0 || ya >= height) {
					break; //only render in bounds of screen
				}

				if (xa < 0) xa = 0; //ensure sprites are rendering smoothly at screen bounds

				int col = sprite.pixels[x + y * sprite.width];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;

			}
		}

	}

	//must be 256 by 172 for best(note this is for fixed background, not offsetted at all)
	public void renderBackground(int[] pixels, BackGround back) {

		for (int y = 0; y < back.h; y++) {
			for (int x = 0; x < back.w; x++) {

				int col = pixels[x + y * back.w];
				if (col != 0xffff00ff) {
					this.pixels[x + y * width] = pixels[x + y * back.w];
				}
			}
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

}
