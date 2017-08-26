package jonny.main.entity.Particle;

import jonny.main.Game;
import jonny.main.entity.Entity;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.levels.Level;

//this class spawns a number of pixels via normal distribution.
public class Particle extends Entity {

	//particle properties
	public Sprite sprite;
	private int life;

	public double xa = 0, ya = 0; //values to move on x/y axis every update

	//single particle constructor, sprites changed in other classes
	public Particle(int x, int y, int life, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.life = life;
		this.sprite = sprite;

		//create normal dist variables with standard dev 1.(70% chance to be within -1,1 range)
		//cut of values above outside that range, retrying for each if it is over the range.
		do {
			this.xa = Game.rand.nextGaussian();
		} while (xa < -1 || xa > 1);

		do {
			this.ya = Game.rand.nextGaussian();
		} while (ya < -1 || ya > 1);

	}

	//adds the seleced amount of pixels
	public Particle(int x, int y, int life, int amount, Sprite sprite) {

		//add an initial particle(by calling a constructor in a constructor(inception))
		this(x, y, life, sprite);
		Level.particles.add(this);
		Level.add(this);

		//add the rest if amount is larger
		for (int i = 0; i < amount - 1; i++) {
			Level.particles.add(this);
			Level.add(new Particle(x, y, life, sprite));
		}

	}

	public void update() {
		this.x += xa;
		this.y += ya;

		life--;
		if (life <= 0) {
			remove();
		}

	}

	public void render(Screen screen) {
		screen.renderObject((int) x, (int) y, sprite);
	}
}
