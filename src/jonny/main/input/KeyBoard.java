package jonny.main.input;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import jonny.main.entity.mob.Player;

public class KeyBoard implements KeyListener, FocusListener {

	public static boolean[] keys = new boolean[KeyEvent.KEY_LAST];

	public static boolean focused = false;
	//all keys used
	public static boolean up;
	public static boolean down;
	public static boolean left;
	public static boolean right;
	public static boolean space;
	public static boolean enter;
	public static boolean backspace;

	public static boolean one;
	public static boolean two;
	public static boolean three;
	public static boolean four;

	public static boolean G; //god mode

	//triggered variables
	public static boolean triggeredJump = false; //set to true in player jump code and false here
	public static boolean triggeredGod = false;

	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];

		one = keys[KeyEvent.VK_1];
		two = keys[KeyEvent.VK_2];
		three = keys[KeyEvent.VK_3];
		four = keys[KeyEvent.VK_4];

		space = keys[KeyEvent.VK_SPACE];
		backspace = keys[KeyEvent.VK_BACK_SPACE];
		enter = keys[KeyEvent.VK_ENTER];

		G = keys[KeyEvent.VK_G];
	}

	public void keyPressed(KeyEvent k) {
		if (!Player.movementDisabled) {

			if (k.getKeyCode() > 0 && k.getKeyCode() < KeyEvent.KEY_LAST) keys[k.getKeyCode()] = true;
		}
	}

	public void keyReleased(KeyEvent k) {
		if (!Player.movementDisabled) {

			if (k.getKeyCode() > 0 && k.getKeyCode() < KeyEvent.KEY_LAST) keys[k.getKeyCode()] = false;

			if (k.getKeyCode() == KeyEvent.VK_UP) {
				triggeredJump = false;
			} else if (k.getKeyCode() == KeyEvent.VK_W) {
				triggeredJump = false;
			}

			if (k.getKeyCode() == KeyEvent.VK_G) {
				triggeredGod = false;
			}

		}

	}

	public void keyTyped(KeyEvent k) {
	}

	public void focusGained(FocusEvent f) {
		focused = true;

	}

	public void focusLost(FocusEvent f) {
		focused = false;

		for (int i = 0; i < keys.length; i++) {
			keys[i] = false;
		}

	}

}
