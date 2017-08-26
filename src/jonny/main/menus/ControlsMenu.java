package jonny.main.menus;

import java.awt.Color;
import java.awt.Graphics;

import jonny.main.Game;
import jonny.main.graphics.Screen;
import jonny.main.input.KeyBoard;

public class ControlsMenu extends Menu {

	private String option = "> Back <";

	public ControlsMenu(KeyBoard input, Screen screen) {
		super(input, screen);

		timerReset = 10;
	}

	public void update() {
		if (timer > 0) timer--;

		if (input.enter && timer == 0) {
			timer = timerReset;
			Game.menu = Menu.main;
			((MainMenu) Menu.main).setSelected(1);

		}
	}

	public void render(Graphics g) {

		//draw main title
		g.setColor(Color.ORANGE);
		g.setFont(titleFont);
		g.drawString("K.E.V", (Game.getWindowWidth() / 2) - Game.getWindowWidth() / 17, 50);

		//draw options and text
		g.setColor(Color.white);
		g.setFont(bodyFont);
		g.drawString(option, 10, 500);

		g.setColor(Color.DARK_GRAY);
		g.drawString("-WASD or arrow keys to move.", 10, 230);
		g.drawString("-Space to shoot.", 10, 260);
		g.drawString("-Enter to interact with stuff", 10, 290);
		g.drawString("-Number keys to switch weapons", 10, 320);

	}
}
