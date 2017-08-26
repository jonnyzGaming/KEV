package jonny.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import jonny.main.entity.mob.Player;
import jonny.main.entity.mob.characters.CabbageKing;
import jonny.main.entity.mob.enemys.EvilPotato;
import jonny.main.graphics.Animations;
import jonny.main.graphics.Screen;
import jonny.main.input.KeyBoard;
import jonny.main.input.Mouse;
import jonny.main.levels.BasicLevel;
import jonny.main.levels.Level;
import jonny.main.menus.BackGround;
import jonny.main.menus.Menu;

/*
 * Main game class
 */
public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	// game properties
	public static int WIDTH = 275;
	public static int HEIGHT = 172;
	public static int SCALE = 3;
	public static Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);

	public static String gameTitle = "KEV: kill-every-vegatable";

	// game engine variables
	private Thread gameThread;
	public JFrame frame;
	private boolean running = false;

	// random gen
	public static Random rand = new Random();

	// graphics variables
	public static BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	// class objects
	public static Screen screen;
	public static KeyBoard keys;
	public static Mouse mouse;
	public static Player player;
	// public Level level;
	public static Level level;
	public Animations anim;

	// menus
	public static BackGround background;
	public static Menu menu;

	// game states
	public static enum GameState {
		MENU, SINGLE, SHOP_MENU
	}

	public static GameState gs = GameState.MENU; // INITIAL

	// seperate Anim booleans etc
	public static boolean FADE_IN_OUT = false;
	public static boolean FADE_IN_OUT_WHOLE = false;
	public static boolean FADE_OUT_TRANSITION = false;
	public static boolean DISPLAY_PLAIN = false;
	public static boolean displayLevel = false;

	public static int r = 0, g = 0, b = 0, a = 1; //for the whole animation.

	//custom camera control variables
	public static boolean controllCamera = false; //default follows player
	public static int customX;
	public static int customY;

	//extra nice game stats.
	public static int gameCompletionTime;
	public static boolean gameComplete = false; //needed to disable continue after game completion.

	// the main game constructor
	public Game() {
		// canvas properties
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);

		// add the frame and screen
		frame = new JFrame(gameTitle);
		screen = new Screen(WIDTH, HEIGHT);

		// add input classes to canvas
		keys = new KeyBoard();
		addKeyListener(keys);
		mouse = new Mouse();
		addMouseListener(mouse);
		addFocusListener(keys);

		//init player and level
		player = new Player(keys);
		level = new BasicLevel("/Levels/level_1.png", player);
		player.init(level);

		anim = new Animations();

		// initial menu
		background = BackGround.main_background;
		menu = Menu.main;

	}

	//creates a new thread and passes in this runnable class as is arguments, meaning this classes run() overrides the threads run()
	public synchronized void start() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// main game loop
	public void run() {
		long lastTime = System.nanoTime();
		long secondTimer = System.currentTimeMillis();
		final double tickRate = 60.0;
		final double fpsLimitRate = 120;
		final double nanoSecs = 1000000000.0 / tickRate; //(1/60 in seconds)
		double delta = 0; //difference in time

		int updates = 0;
		int frames = 0;

		requestFocus();
		while (running) {

			long now = System.nanoTime();
			delta += (now - lastTime) / nanoSecs;
			lastTime = now;

			while (delta > 0) {
				update();
				delta--;
				updates++;

			}

			//cap at 180 fps by only allowing 3 fps per tick
			if (updates * 3 != frames && KeyBoard.focused) {
				render();
				frames++;
			} else if (!KeyBoard.focused) {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			/*
			 * if (KeyBoard.focused) {
			 * render();
			 * frames++;
			 * } else {
			 * try {
			 * Thread.sleep(5);
			 * } catch (InterruptedException e) {
			 * e.printStackTrace();
			 * }
			 * }
			 */

			// display stats every second
			if (System.currentTimeMillis() - secondTimer > 1000) {
				secondTimer += 1000; // increment second counter

				frame.setTitle(gameTitle + "| ups: " + updates + "| fps: " + frames);
				//log("ups: " + updates + " fps: " + frames);

				// extra usefull debugging stuff put here.
				// log("health: " + Player.health);

				//System.out.println(System.getProperties().getProperty("os.name"));
				//log("" + BasicLevel.totalCoins);
				//log("Total coin count:" + Player.totalCoinCount);
				//log("Money free: " + (BasicLevel.totalCoins - Player.total_in_shop));
				//log("Player x: " + player.x + ", Player y: " + player.y);
				updates = 0;
				frames = 0;
			}
		}
	}

	// update the game
	public void update() {

		keys.update();
		if (keys.focused) {
			if (gs == GameState.MENU) {
				menu.update();
			}

			if (gs == GameState.SINGLE) {
				player.update();
				int xScroll = (int) player.x - screen.width / 2;
				int yScroll = (int) player.y - screen.height / 2;
				level.update(xScroll, yScroll);
				anim.update();

				gameCompletionTime++;
			}

			if (gs == GameState.SHOP_MENU) {
				int xScroll = (int) player.x - screen.width / 2;
				int yScroll = (int) player.y - screen.height / 2;
				level.update(xScroll, yScroll);
				menu.update();

				gameCompletionTime++;
			}
		}

	}

	// render all the games graphics
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();

		// if in menu game state render the background to START
		if (gs == GameState.MENU) {
			background.render(screen);
		}

		// if game state is single then update player and render the active
		// level.
		if (gs == GameState.SINGLE || gs == GameState.SHOP_MENU) {
			// render the level based on players position in it(centered)
			int xScroll = (int) player.x - screen.width / 2;
			int yScroll = (int) player.y - screen.height / 2;

			// if final boss seq then roll camera for meet seq
			if (EvilPotato.startWalking1 || EvilPotato.startWalking2) {
				xScroll = (int) player.x - screen.width / 2 + EvilPotato.cameraRollTimer;
				yScroll = (int) player.y - screen.height / 2;
			}

			//if want a static screen then dont follow the player but go of two other x, y
			if (controllCamera) {
				xScroll = customX;
				yScroll = customY;
			}

			level.render(xScroll, yScroll, screen);
			player.render(screen);
			level.renderHidden(xScroll, yScroll, screen);
		}

		// loads the main pixel array with content.
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		if (gs == GameState.MENU) {
			menu.render(g);
		}

		if (gs == GameState.SINGLE || gs == GameState.SHOP_MENU) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Veranda", Font.PLAIN, 25));
			g.drawString("Deaths: " + Player.deaths, 686, 30);

			// health + coin bar
			g.drawString("Health: ", 10, 30);

			if (Player.ARMOR_UPGRADE1) {
				g.drawString("Armor: ", 10, 59);
				g.drawString("Money     : " + Player.coins, 10, 88);
			}

			if (!Player.ARMOR_UPGRADE1) g.drawString("Money     : " + Player.coins, 10, 59);

			// mob timer
			if (Player.villageAttackStarted && !CabbageKing.complete) {
				if (Player.ARMOR_UPGRADE1) g.drawString("Mobs left: " + (CabbageKing.VillageMobsNeeded
						- CabbageKing.VillageMobsKilled), 10, 117);
				else {
					g.drawString("Mobs left: " + (CabbageKing.VillageMobsNeeded - CabbageKing.VillageMobsKilled), 10,
							88);
				}

			}

			// locator mouse click tool(for testing in god mode)
			if (Player.GodMode) {
				int xScroll = (int) player.x - screen.width / 2;
				int yScroll = (int) player.y - screen.height / 2;
				int x = xScroll + (mouse.getX() / 3); // need to divide by 3 become of screen scale
				int y = yScroll + (mouse.getY() / 3);
				g.drawString("co-ords:(" + x + " , " + y + ")", mouse.getX(), mouse.getY());
			}

			// can only play one animation at once like this (change later by creating more gs2s)
			if (FADE_IN_OUT) {
				anim.renderFade(g, true);
			}
			if (DISPLAY_PLAIN) {
				anim.renderBasicText(g, displayLevel);
			}
			//only for potato boss.
			if (FADE_IN_OUT_WHOLE) {
				anim.renderFadeWhole(g);
			}
			if (FADE_OUT_TRANSITION) {
				anim.renderFadeWhole(g);
			}
		}

		if (gs == GameState.SHOP_MENU) {
			menu.renderBackground(g);
			menu.renderSprites(screen);
			// loads the main pixel array with content.
			for (int i = 0; i < pixels.length; i++) {
				pixels[i] = screen.pixels[i];
			}
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

			menu.render(g);

		}

		g.dispose();
		bs.show();

	}

	public static void main(String[] args) {
		Game game = new Game();
		// frame properties
		game.frame.setResizable(false);
		game.frame.add(game, BorderLayout.CENTER);
		game.frame.pack(); // must pack first after adding.
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		// start the games thread
		game.start();

	}

	// extra helpfull methods
	public static int getWindowWidth() {
		return WIDTH * SCALE;
	}

	public static int getWindowHeight() {
		return WIDTH * SCALE;
	}

	public static void log(String message) {
		System.out.println(message);
	}

}
