package jonny.main.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

	// x and y mouse position variables
	private static int mouseX = -1;
	private static int mouseY = -1;

	// getters and setters
	public static int getX() {
		return mouseX;
	}

	public static int getY() {
		return mouseY;
	}

	public void mouseDragged(MouseEvent m) {

	}

	public void mouseMoved(MouseEvent m) {

	}

	public void mouseClicked(MouseEvent e) {
		// use these and comment pressed out for co-ord feature
		//mouseX = e.getX();
		//mouseY = e.getY();

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

	}

	public void mouseReleased(MouseEvent e) {
		mouseX = -1;
		mouseY = -1;
	}

}
