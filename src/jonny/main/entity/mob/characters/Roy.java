package jonny.main.entity.mob.characters;

import java.awt.Color;
import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.Game.GameState;
import jonny.main.entity.mob.Mob;
import jonny.main.graphics.Animations;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.levels.BasicLevel;
import jonny.main.menus.BackGround;
import jonny.main.menus.Menu;
import jonny.main.menus.ShopMenu;

public class Roy extends Mob {

	//other general variables
	public static boolean inMenu = false;
	private int animTimer = 0;

	public Roy(int x, int y, Sprite sprite) {

		this.x = x;
		this.y = y;
		this.sprite = sprite;

		cWidth = 30;
		cHeight = 30;
	}

	public void update() {

		animTimer++;
		if (animTimer > 10000) animTimer = 0;

		if (onScreen && !inMenu) {
			Rectangle box = new Rectangle((int) x - (cWidth / 2), (int) y - (cHeight / 2), cWidth, cHeight);

			if (entityCollision(Game.player, box)) {

				//play a basic text animation 
				BasicLevel.text = "Enter to interect";
				BasicLevel.xPos = 340;
				BasicLevel.yPos = 320;
				BasicLevel.col = Color.black;

				Game.DISPLAY_PLAIN = true;
				Game.displayLevel = true;
				Animations.displayTextTime = 5;
				Animations.displayText = true;
				Animations.displayTextTimer = 0;

				if (Game.keys.enter) {
					inMenu = true;
					Game.menu = Menu.shop_menu;
					ShopMenu.background = BackGround.shop_background1;
					Game.gs = GameState.SHOP_MENU;
				}
				//Game.menu = 

			}
		}

	}

	public void render(Screen screen) {

		if (animTimer % 120 > 60) {
			sprite = Sprite.roy1;
		} else {
			sprite = Sprite.roy2;
		}

		screen.renderObject((int) x - (cWidth / 2), (int) y - (cHeight / 2), sprite, this);
	}
}
