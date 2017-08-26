package jonny.main.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import jonny.main.Game;
import jonny.main.entity.mob.Player;
import jonny.main.levels.BasicLevel;

public class Animations {

	//Animation activation variables
	//fade in/out
	public static boolean FadeInOut = false;
	public static int fadeTimerMax = 510;
	public int fadeTimer = fadeTimerMax--;

	//display text
	public static boolean displayText = false;
	public static int displayTextTime = 60;
	public static int displayTextTimer = 0;

	//entire screen fade/in out
	public static boolean FadeInOutWholeScreen = false;
	public static int fadeTimerMaxWhole = 510;
	public int fadeTimerWhole = fadeTimerMaxWhole--;

	public static boolean FadeOutTransition = false;
	public static int fadeTimerMaxTrans = 254;
	public int fadeTimerTrans = fadeTimerMaxWhole--;

	//should set a to around 1 to start
	public void FadeInOutText(Graphics grap, String s, int x, int y, int r, int g, int b, int a) {

		Color c = new Color(r, g, b, a);
		grap.setColor(c);
		grap.setFont(new Font("Veranda", Font.PLAIN, 25));
		grap.drawString(s, x, y);
	}

	//used to fade the entire screen by a color.
	public void FadeInOutEntireScreen(Graphics grap, int r, int g, int b, int a) {
		grap.setColor(new Color(r, g, b, a));
		grap.fillRect(0, 0, Game.getWindowWidth(), Game.getWindowHeight());
	}

	//will only display while its being constantly called by an entity
	public void displayPlainText(Graphics grap, String s, int x, int y, Color c) {

		grap.setColor(c);
		grap.setFont(new Font("Veranda", Font.PLAIN, 23)); //defualt for windows

		//COMMENTED OUT FOR APPLET
		//if (System.getProperties().getProperty("os.name").equals("Linux")) grap.setFont(new Font("Veranda", Font.PLAIN, 21)); //correct for linux

		int i = 0;
		for (String line : s.split("\n")) {

			if (i == 0) {
				grap.drawString(line, x, y);
			} else {
				grap.drawString(line, x, y += grap.getFontMetrics().getHeight());
			}
			i++;
		}
	}

	public void update() {

		//////fade in out animation//////
		if (FadeInOut) {
			fadeTimer = 0;
			FadeInOut = false;
			Game.a = 0;
		}

		if (fadeTimer == fadeTimerMax) {

			Game.FADE_IN_OUT = false;
			fadeTimer = fadeTimerMax--;
		}

		if (fadeTimer >= 0) {
			if (fadeTimer < 254) {
				BasicLevel.a++;
			}

			if (fadeTimer >= 255) {
				BasicLevel.a--;
			}
			fadeTimer++;
		}

		//////fade in out animation//////

		//fade in out whole screen/////
		if (Game.FADE_IN_OUT_WHOLE) {
			if (FadeInOutWholeScreen) {
				fadeTimerWhole = 0;
				FadeInOutWholeScreen = false;
			}

			if (fadeTimerWhole == fadeTimerMaxWhole) {

				Game.FADE_IN_OUT_WHOLE = false;
				fadeTimerWhole = 0;
				Game.a = 1;
				return;
			}

			if (fadeTimerWhole >= 0) {
				if (fadeTimerWhole < 254) {
					if (Game.a < 255) Game.a++;
				}

				if (fadeTimerWhole >= 255) {
					if (Game.a > 0) Game.a--;
				}
				fadeTimerWhole++;
			}
		}

		if (Game.FADE_OUT_TRANSITION) {
			if (FadeOutTransition) {
				fadeTimerTrans = 0;
				FadeOutTransition = false;
			}

			if (fadeTimerTrans == fadeTimerMaxTrans) {

				Game.FADE_OUT_TRANSITION = false;
				fadeTimerTrans = 0;
				Game.a = 1;
				return;
			}

			if (fadeTimerTrans >= 0) {
				if (fadeTimerTrans < 254) {
					if (Game.a < 255) Game.a += 2;
				}
				fadeTimerTrans++;
			}
		}

		//fade in out whole screen/////

		/////display plain text///////
		if (displayText) {
			if (displayTextTimer < displayTextTime) {
				displayTextTimer++;
			} else {
				displayTextTimer = 0;
				displayText = false;
				Game.DISPLAY_PLAIN = false;
			}
		}
		/////display plain text///////

	}

	public void renderFade(Graphics grah, boolean level) {

		if (fadeTimer >= 0) {
			if (level = true) {
				FadeInOutText(grah, BasicLevel.text, BasicLevel.xPos, BasicLevel.yPos, BasicLevel.r, BasicLevel.g, BasicLevel.b, BasicLevel.a);
			}
		}

		//////fade in out animation//////

	}

	//only works for the final potato boss death
	public void renderFadeWhole(Graphics grah) {
		FadeInOutEntireScreen(grah, Game.r, Game.g, Game.b, Game.a);
	}

	public void renderBasicText(Graphics grah, boolean level) {
		/////display plain text///////
		if (level) {
			displayPlainText(grah, BasicLevel.text, BasicLevel.xPos, BasicLevel.yPos, BasicLevel.col);
		} else if (!level) {
			displayPlainText(grah, Player.text, Player.xPos, Player.yPos, Player.col);
		}

		/////display plain text///////
	}

}
