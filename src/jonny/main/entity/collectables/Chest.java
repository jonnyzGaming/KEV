package jonny.main.entity.collectables;

import java.awt.Color;
import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Mob;
import jonny.main.graphics.Animations;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.levels.BasicLevel;
import jonny.main.levels.Level;

public class Chest extends Mob {

	//basic stats
	private int animTimer;
	public static boolean activated = false;

	public static int delayTimer = 0;

	public Chest(int x, int y) {

		this.x = x;
		this.y = y;

		//speech optional trigger collision 
		xTrans = -20;
		yTrans = -10;
		cWidth = 30;
		cHeight = 20;

		sprite = Sprite.chest_closed;

	}
	public void update() {
		animTimer++;
		if (animTimer == 75000) animTimer = 0;

		if (delayTimer > 0) delayTimer--;

		if (onScreen && !activated) {
			Rectangle box = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

			if (delayTimer == 0) {
				if (entityCollision(Game.player, box)) {

					//basic rollover animation
					BasicLevel.text = "Enter to interect";
					BasicLevel.xPos = 340;
					BasicLevel.yPos = 320;
					BasicLevel.col = Color.black;

					Game.DISPLAY_PLAIN = true;
					Game.displayLevel = true;
					Animations.displayTextTime = 5;
					Animations.displayText = true;
					Animations.displayTextTimer = 0;

					//play sequence is enter pressed
					if (Game.keys.enter) {

						sprite = Sprite.chest_opened;

						//spawn a cake when the chest is opened
						Cake cake = new Cake((int) x, (int) y);
						cake.init(level);
						Level.add(cake);

						y -= 7;

						activated = true;
					}

				}
			}

		}

	}
	public void render(Screen screen) {

		screen.renderAccurateSprite((int) x - 16, (int) y - 9, sprite, this);
	}

}
