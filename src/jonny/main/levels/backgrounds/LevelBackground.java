package jonny.main.levels.backgrounds;

import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;

public class LevelBackground {

	//background properties
	public static double xM = 0;
	public static double mountainMoveSpeed = 0.00; //doesent move

	public static double xB = 0;
	public static double blobMoveSpeed = 0.06;

	public static void renderMountain(Screen screen) {

		if (xM > 275) xM = 0;
		if (xM < 0) xM = 275;

		screen.renderBasicAccurateSprite((int) xM, 0, Sprite.mountain_background);
		screen.renderBasicAccurateSprite((int) xM - 275, 0, Sprite.mountain_background);
		screen.renderBasicAccurateSprite((int) xM + 275, 0, Sprite.mountain_background);
	}

	public static void renderBlob(Screen screen) {

		if (xB > 275) xB = 0;
		if (xB < 0) xB = 275;

		screen.renderBasicAccurateSprite((int) xB, 0, Sprite.green_blob);
		screen.renderBasicAccurateSprite((int) xB - 275, 0, Sprite.green_blob);
		screen.renderBasicAccurateSprite((int) xB + 275, 0, Sprite.green_blob);
	}
}
