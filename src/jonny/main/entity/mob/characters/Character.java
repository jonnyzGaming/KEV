package jonny.main.entity.mob.characters;

import jonny.main.entity.mob.Mob;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;

//used to create non talkable characters
public class Character extends Mob {
	//basic stats
	private int animTimer;
	public static boolean activated = false;

	public Sprite originSprite;
	public Sprite nextSprite;

	public static int delayTimer = 0;

	public Character(int x, int y, Sprite originSprite, Sprite nextSprite, int xTrans, int yTrans) {

		this.x = x;
		this.y = y;
		this.sprite = originSprite;
		this.nextSprite = nextSprite;
		this.originSprite = originSprite;

		//speech optional trigger collision 
		this.xTrans = xTrans;
		this.yTrans = yTrans;

	}

	public void update() {
		animTimer++;
		if (animTimer == 75000) animTimer = 0;

		if (delayTimer > 0) delayTimer--;

	}
	public void render(Screen screen) {
		if (animTimer % 120 > 60) {
			sprite = originSprite;
		} else {
			sprite = nextSprite;
		}

		screen.renderObject((int) x + xTrans, (int) y + yTrans, sprite, this);
	}
}
