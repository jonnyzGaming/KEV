package jonny.main.entity.projectiles;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;

public class LazerPiece extends Projectile {

	public LazerPiece(int x, int y, double dir) {
		super(x, y, dir);

		speed = 2;
		range = 500;
		damage = 0.3;

		cWidth = 4;
		cHeight = 4;
		xTrans = -2;
		yTrans = -2;

		sprite = Sprite.red_lazer;

		//find the future x and y values
		nx = speed * Math.cos(dir);
		ny = speed * Math.sin(dir);

	}

	public void update() {
		move();

		//detect collision with the player
		Rectangle colBox = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

		if (entityCollision(Game.player, colBox)) {

			removed = true;
			Player.health -= damage;

		}

	}

	private void move() {

		if (collisionTile((int) nx, (int) ny) || objectCollision((int) nx, (int) ny)) {
			removed = true;
			return;
		} else {
			x += nx;
			y += ny;

		}

		if (distance() > range) {
			removed = true;
		}

	}

	private int distance() {
		int dist = 0;

		//phythagorus therom here to find distance after figuring out oa and b
		int a = (int) y - yOrigin;
		int b = (int) x - xOrigin;
		dist = (int) Math.sqrt(Math.abs((a * a) + (b * b)));

		return dist;
	}

	public void render(Screen screen) {

		screen.renderObject((int) x - 8, (int) y - 8, sprite);

	}

}
