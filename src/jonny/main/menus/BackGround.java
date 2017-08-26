package jonny.main.menus;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import jonny.main.graphics.Screen;

public class BackGround {

	public static BackGround main_background = new BackGround("/textures/menu_sheets/backgrounds/main_background.png");
	public static BackGround shop_background1 = new BackGround("/textures/menu_sheets/backgrounds/shop_background.png");
	public static BackGround shop_background2 = new BackGround("/textures/menu_sheets/backgrounds/shop_background_second_tab.png");
	public static BackGround shop_background3 = new BackGround("/textures/menu_sheets/backgrounds/shop_background_third_tab.png");
	public static BackGround completed_background = new BackGround("/textures/menu_sheets/backgrounds/game_complete_background.png");

	//general background properties
	private String path;
	public int[] pixels;
	public int w, h;

	public BackGround(String path) {
		this.path = path;

		loadImage();

	}

	//loads the image from the paths file
	public void loadImage() {
		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream(path));
			w = image.getWidth();
			h = image.getHeight();
			pixels = new int[w * h];
			image.getRGB(0, 0, w, h, pixels, 0, w);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void update() {
		/*
		 * shiftTimer++;
		 * 
		 * if (shiftTimer == 75) { xScroll++; if (xScroll == -275) xScroll = 0;
		 * shiftTimer = 0; }
		 */
	}

	public void render(Screen screen) {
		screen.renderBackground(pixels, this);
	}

}
