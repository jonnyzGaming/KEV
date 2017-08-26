package jonny.main.menus;

import java.awt.Color;
import java.awt.Graphics;

import jonny.main.Game;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Screen;
import jonny.main.input.KeyBoard;
import jonny.main.levels.BasicLevel;
import jonny.main.sound.Sound;

public class GameCompleteMenu extends Menu {

	private String option = "> Back <";

	public GameCompleteMenu(KeyBoard input, Screen screen) {
		super(input, screen);

		timerReset = 10;
	}

	public void update() {
		if (timer > 0) timer--;

		//change to the correct background
		Game.background = BackGround.completed_background;

		//go back to main menu if entered
		if (input.enter && timer == 0) {
			timer = timerReset;
			Game.menu = Menu.main;
			((MainMenu) Menu.main).setSelected(2);
			Game.background = BackGround.main_background;

			Sound.menuCompleteMusic.stop();
			Sound.menuCompleteMusic.isPlaying = false;

		}
	}

	public void render(Graphics g) {

		//draw the back option
		g.setColor(Color.white);
		g.setFont(bodyFont);
		g.drawString(option, 10, 500);

		//draw the game stats in the stats box.(drawn from background)
		g.setColor(Color.DARK_GRAY);

		//start with completion time
		double completionSeconds = Game.gameCompletionTime / 60;
		double completionMinutes = Math.round((completionSeconds / 60.0) * 100.0) / 100.0;
		int roundedMinutes = (int) Math.floor(completionMinutes);
		int leftOverSeconds = (int) ((completionMinutes - roundedMinutes) * 60);

		g.drawString("-Completion time: " + roundedMinutes + " m, " + leftOverSeconds + " s.", 212, 220);
		g.drawString("-Carrots collected: " + Player.totalCoinCount + "/" + BasicLevel.totalCoins, 212, 250);
		g.drawString("-Deaths: " + Player.deaths, 212, 280);

	}
}
