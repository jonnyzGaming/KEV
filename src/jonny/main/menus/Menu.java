package jonny.main.menus;

import java.awt.Font;
import java.awt.Graphics;

import jonny.main.Game;
import jonny.main.graphics.Screen;
import jonny.main.input.KeyBoard;

public abstract class Menu {

	//general menu properties
	protected static KeyBoard input;
	public Screen screen;
	protected Font titleFont = new Font("DialogInput", 1, 35);
	protected Font bodyFont = new Font("DialogInput", 1, 20);

	public int timer = 10;
	public int timerReset;

	//all static menus here
	public static Menu main = new MainMenu(Game.keys, Game.screen);
	public static Menu credits = new CreditsMenu(Game.keys, Game.screen);
	public static Menu controls = new ControlsMenu(Game.keys, Game.screen);
	public static Menu shop_menu = new ShopMenu(Game.keys, Game.screen);
	public static Menu completion_menu = new GameCompleteMenu(Game.keys, Game.screen);

	public Menu(KeyBoard input, Screen screen) {
		this.input = input;
		this.screen = screen;
	}

	public void update() {

	}

	public void render(Graphics g) {

	}

	//only used for shop
	public void renderBackground(Graphics g) {

	}

	public void renderSprites(Screen screen) {

	}
}
