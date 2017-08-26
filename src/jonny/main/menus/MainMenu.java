package jonny.main.menus;

import java.awt.Color;
import java.awt.Graphics;

import jonny.main.Game;
import jonny.main.Game.GameState;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Animations;
import jonny.main.graphics.Screen;
import jonny.main.input.KeyBoard;
import jonny.main.sound.Sound;

public class MainMenu extends Menu {

	//general menu properties (below used in Game render method instead)
	//public static BackGround Menubackground = BackGround.main_background;

	//items array and selected int.
	private String[] items = { "Singleplayer", "Options", "Help", "Quit" };
	private int selected = 0;
	private boolean GameHasBeenStarted;

	public MainMenu(KeyBoard input, Screen screen) {
		super(input, screen);

		GameHasBeenStarted = false;

		timerReset = 10;
	}

	public void update() {

		if (!Sound.menuMusic.isPlaying) {
			Sound.menuMusic.play(true);
			Sound.menuMusic.isPlaying = true;
		}

		if (timer > 0) timer--;

		//key input for option changes
		if (input.down && timer == 0) {
			Sound.menuClick.play(false);
			timer = timerReset;
			selected++;
		}
		if (input.up && timer == 0) {
			Sound.menuClick.play(false);
			timer = timerReset;
			selected--;
		}

		//update responses based on options selected
		//if singlePlayer option selected
		if (selected == 0 && timer == 0) {

			if (GameHasBeenStarted && !Game.gameComplete) items[0] = "> Continue <";
			else if (Game.gameComplete) items[0] = "> Game complete <";
			else items[0] = "> SinglePlayer <";

			if (input.enter && !Game.gameComplete) {

				Game.gs = GameState.SINGLE;
				//create first sequence if its the first start of the game.
				if (!GameHasBeenStarted) {
					Game.FADE_IN_OUT = true;
					Animations.FadeInOut = true;

					Player.sequenceNum = 0;
					Player.sequence = true;
				}

				Sound.menuMusic.stop();
				Sound.menuMusic.isPlaying = false;

				GameHasBeenStarted = true;
			}
		} else {
			if (GameHasBeenStarted && !Game.gameComplete) items[0] = "Continue";
			else if (Game.gameComplete) items[0] = "Game complete";
			else items[0] = "SinglePlayer";
		}

		//if controls is selected
		if (selected == 1 && timer == 0) {
			items[1] = "> Controls <";

			if (input.enter) {
				timer = timerReset;
				Game.menu = Menu.controls;

			}
		} else {
			items[1] = "Controls";
		}

		//if the credits option is selected
		if (selected == 2 && timer == 0) {
			items[2] = "> Credits <";
			if (input.enter) {
				timer = timerReset;
				Game.menu = Menu.credits;

			}
		} else {
			items[2] = "Credits";
		}

		//if exit option is selected
		if (selected == 3 && timer == 0) {
			items[3] = "> Exit <";

			if (input.enter) {
				System.exit(0);
			}
		} else {
			items[3] = "Exit";
		}

		/////TEMP CODE///
		//if (selected == 4 && timer == 0) {
		//	items[4] = "> tempcomp <";
		//	if (input.enter) {
		//		timer = timerReset;
		//		Game.menu = Menu.completion_menu;
		//
		//	}
		//} else {
		//	items[4] = "tempcomp";
		//}

		//enter option is always selected
		if (selected < 0) selected = 0;
		if (selected > 3) selected = 3; //was 3
	}

	public void render(Graphics g) {

		//draw main title
		g.setColor(Color.ORANGE);
		g.setFont(titleFont);
		g.drawString("K.E.V", (Game.getWindowWidth() / 2) - Game.getWindowWidth() / 17, 50);

		//draw options
		g.setColor(Color.white);
		g.setFont(bodyFont);
		for (int i = 0; i < items.length; i++) {//375
			//seperate by 40 pixels, center the strings
			g.drawString(items[i], Game.getWindowWidth() / 2 - (items[i].length() / 2) * 10 - 10, 200 + i * 40);
		}

	}

	public void setSelected(int sel) {
		selected = sel;
	}
}
