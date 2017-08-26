package jonny.main.entity.collectables;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Mob;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.levels.Level;
import jonny.main.sound.Sound;

public class Checkpoint extends Mob {

	//checkpoint features
	public boolean activated;

	//static checkpoints for each level here;

	public Checkpoint(int x, int y, boolean activated) {
		isCheckpoint = true;
		this.x = x;
		this.y = y;
		this.activated = activated;

		cWidth = 30;
		cHeight = 36;
		xTrans = -17;
		yTrans = -17;

		//initial sprite
		sprite = Sprite.checkpoint_not;

	}

	public void update() {

		if (onScreen) {
			Rectangle box = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

			//activate the checkpoint and change player spawn location
			if (entityCollision(Game.player, box) && !activated) {

				//deactivate all other checkpoints
				for (int i = 0; i < Level.entitys.size(); i++) {

					if (Level.entitys.get(i).isCheckpoint) {
						Checkpoint c = (Checkpoint) Level.entitys.get(i);
						c.activated = false;
					}
				}

				//activate this checkpoint
				activated = true;
				Player.xSpawn = (int) x;
				Player.ySpawn = (int) y;

				//possible sound play here
				Sound.dinDing.play(false);

			}
		}
	}
	public void render(Screen screen) {

		if (activated) {
			sprite = Sprite.checkpoint_yes;
		} else {
			sprite = Sprite.checkpoint_not;
		}

		screen.renderObject((int) x - 20, (int) y - 20, sprite, this);

	}
}
