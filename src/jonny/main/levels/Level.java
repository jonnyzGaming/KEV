package jonny.main.levels;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import jonny.main.Game;
import jonny.main.entity.Entity;
import jonny.main.entity.Objects.Background_Object;
import jonny.main.entity.Particle.Particle;
import jonny.main.entity.mob.Mob;
import jonny.main.entity.mob.Player;
import jonny.main.entity.mob.enemys.Enemy;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.levels.backgrounds.LevelBackground;
import jonny.main.levels.tiles.Tile;
import jonny.main.sound.Sound;
import jonny.main.sound.Sound.MAIN_TRACK;

public abstract class Level {

	//level properties
	private String path;
	protected int width, height;
	protected int[] tiles; //stores pixels for the level (1 pixel is a tile)
	public static List<Entity> entitys = new ArrayList<Entity>();
	public static List<Enemy> enemies = new ArrayList<Enemy>();
	public static List<Mob> gameSolidObjects = new ArrayList<Mob>();
	public static List<Particle> particles = new ArrayList<Particle>();
	public static List<Background_Object> ChangeableBackgroundObjectsArray = new ArrayList<Background_Object>();

	//reference to the player in the level.
	public Player player;

	public Level(String path, Player player) {
		this.path = path;
		loadLevel(path);
		this.player = player;
	}

	//loads the levels image into tile pixel array
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];
			image.getRGB(0, 0, width, height, tiles, 0, width);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void add(Entity e) {
		entitys.add(e);
	}

	public static void remove(Entity e) {
		entitys.remove(e);
	}

	public void clear() {
		entitys.clear();
	}

	public void addToChangeableBackgroundObjectsArray(Background_Object ob) {
		ChangeableBackgroundObjectsArray.add(ob);

	}

	public void update(int xScroll, int yScroll) {

		//update other general level things not covered elsewhere
		//music
		if (xScroll > 13000 && yScroll > 1000) {
			Sound.track = MAIN_TRACK.CAVE;
		} else {
			Sound.track = MAIN_TRACK.NORMAL;
		}
		switch (Sound.track) {
			case NORMAL:
				if (!Sound.music.isPlaying) {
					Sound.music.play(true);
					Sound.music.isPlaying = true;
				}
				if (Sound.caveMusic.isPlaying) {
					Sound.caveMusic.stop();
					Sound.caveMusic.isPlaying = false;
				}
				break;
			case CAVE:
				if (!Sound.caveMusic.isPlaying) {
					Sound.caveMusic.play(true);
					Sound.caveMusic.isPlaying = true;
				}
				if (Sound.music.isPlaying) {
					Sound.music.stop();
					Sound.music.isPlaying = false;
				}
				break;
			case VILLAGE_CHAOS:
				if (!Sound.villageChaosMusic.isPlaying) {
					Sound.villageChaosMusic.play(true);
					Sound.villageChaosMusic.isPlaying = true;
				}
				break;
			case BOSS:
				if (!Sound.villageChaosMusic.isPlaying) {
					Sound.bossMusic.play(true);
					Sound.bossMusic.isPlaying = true;
				}
				break;

		}
		//if (xScroll > 9012 && xScroll < 11000 && yScroll > 2700 && yScroll < 4000) {
		//			if (Sound.monsterRoar.isPlaying == false) {
		//				Sound.monsterRoar.play(true);
		//				Sound.monsterRoar.isPlaying = true;
		//			}
		//		} else {
		//			if (Sound.monsterRoar.isPlaying == true) {
		//				Sound.monsterRoar.stop();
		//				Sound.monsterRoar.isPlaying = false;
		//			}
		//		}
		//Sound.music.stop();
		//Sound.music.isPlaying = false;
		//sewer boss roar on approach.

		//		if (xScroll > 9012 && xScroll < 11000 && yScroll > 2700 && yScroll < 4000) {
		//			if (Sound.monsterRoar.isPlaying == false) {
		//				Sound.monsterRoar.play(true);
		//				Sound.monsterRoar.isPlaying = true;
		//			}
		//		} else {
		//			if (Sound.monsterRoar.isPlaying == true) {
		//				Sound.monsterRoar.stop();
		//				Sound.monsterRoar.isPlaying = false;
		//			}
		//		}

		//update all entitys
		for (int i = 0; i < entitys.size(); i++) {
			Entity e = entitys.get(i);

			if (e.isRemoved() == false) {
				e.update();
			} else {
				entitys.remove(e);
				enemies.remove(e);
				gameSolidObjects.remove(e);
				particles.remove(e);
				i--;
			}
		}

		//get the corner pins of the screen(in tile precision)
		int x0 = xScroll >> Screen.TILE_SHIFT;
		int x1 = (xScroll + Screen.width + Screen.TILE_SIZE) >> Screen.TILE_SHIFT;
		int y0 = yScroll >> Screen.TILE_SHIFT;
		int y1 = (yScroll + Screen.height + Screen.TILE_SIZE) >> Screen.TILE_SHIFT;

		//for all the y tiles on screen loop all the x tiles.
		tile_loop:
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				//check for all animated tiles
				if (getTile(x, y).isAnimated) {
					getTile(x, y).update();
					break tile_loop; //becuase tiles are static(NOTE wont work if multiple diff types of animated tiles are on screen(eg fire and water on same screen)
				}
			}
		}

	}

	//render all tiles in the level etc
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);

		//render backgrounds
		if (Game.player.x > 13128 && Game.player.y > 1100) {
			screen.renderBasicAccurateSprite(0, 0, Sprite.night);
		} else {
			screen.renderBasicAccurateSprite(0, 0, Sprite.sky_background);
		}
		LevelBackground.renderMountain(screen);
		LevelBackground.renderBlob(screen);

		//get the corner pins of the screen(in tile precision)
		int x0 = xScroll >> Screen.TILE_SHIFT;
		int x1 = (xScroll + Screen.width + Screen.TILE_SIZE) >> Screen.TILE_SHIFT;
		int y0 = yScroll >> Screen.TILE_SHIFT;
		;
		int y1 = (yScroll + Screen.height + Screen.TILE_SIZE) >> Screen.TILE_SHIFT;
		;

		//for all the y tiles on screen loop all the x tiles.
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}

		//render all entitys
		for (int i = 0; i < entitys.size(); i++) {
			entitys.get(i).render(screen);
		}
	}

	public void renderHidden(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);

		//get the corner pins of the screen(in tile precision)
		int x0 = xScroll >> Screen.TILE_SHIFT;
		int x1 = (xScroll + screen.width + Screen.TILE_SIZE) >> Screen.TILE_SHIFT;
		int y0 = yScroll >> Screen.TILE_SHIFT;
		;
		int y1 = (yScroll + screen.height + Screen.TILE_SIZE) >> Screen.TILE_SHIFT;
		;

		//for all the y tiles on screen loop all the x tiles.
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				Tile t = getHiddenTile(x, y);
				if (t != Tile.VOID) {
					t.render(x, y, screen);
				}
			}
		}
	}

	public Tile getHiddenTile(int xp, int yp) {
		if (tiles[xp + yp * width] == Tile.hidden_dirt_col) return Tile.HIDDEN__DIRT;
		if (tiles[xp + yp * width] == Tile.hidden_dirt_right_col) return Tile.HIDDEN_DIRT_RIGHT;
		if (tiles[xp + yp * width] == Tile.non_solid_dirt_col) return Tile.NON_SOLID__DIRT;
		if (tiles[xp + yp * width] == Tile.hidden_dirt_solid_col) return Tile.HIDDEN_DIRT_SOLID; //this is repeated in normal for collision issues
		if (tiles[xp + yp * width] == Tile.sewer_pipe_col) return Tile.SEWER_PIPE; //this is repeated in normal for collision issues
		if (tiles[xp + yp * width] == Tile.forest_dirt_hidden_solid_col) return Tile.FOREST_DIRT_HIDDEN_SOLID; //this is repeated in normal for collision issues
		if (tiles[xp + yp * width] == Tile.forest_dirt_hidden_col) return Tile.FOREST_DIRT_HIDDEN;
		if (tiles[xp + yp * width] == Tile.forest_dirt_hidden_normal_col) return Tile.FOREST_NON_SOLID_HIDDEN;
		if (tiles[xp + yp * width] == Tile.hidden_cliff_dirt_col) return Tile.HIDDEN_CLIFF_DIRT;

		return Tile.VOID;
	}

	//returns basic tiles given the tiles position
	public Tile getTile(int xp, int yp) {
		if (xp < 0 || yp < 0 || xp >= width || yp >= height) return Tile.VOID;
		if (tiles[xp + yp * width] == Tile.water_col) return Tile.WATER_TOP;
		if (tiles[xp + yp * width] == Tile.water_col2) return Tile.WATER_NORMAL;
		if (tiles[xp + yp * width] == Tile.dirt_col) return Tile.DIRT;
		if (tiles[xp + yp * width] == Tile.hidden_dirt_solid_col) return Tile.HIDDEN_DIRT_SOLID;
		if (tiles[xp + yp * width] == Tile.dirt_grass_col) return Tile.GRASS_DIRT;
		if (tiles[xp + yp * width] == Tile.sky_col) return Tile.SKY;
		if (tiles[xp + yp * width] == Tile.cliff_dirt_col) return Tile.CLIFF_DIRT;
		if (tiles[xp + yp * width] == Tile.hidden_cliff_dirt_col) return Tile.HIDDEN_CLIFF_DIRT;
		if (tiles[xp + yp * width] == Tile.flower_col) return Tile.FLOWER;
		if (tiles[xp + yp * width] == Tile.flower_red_col) return Tile.FLOWER_RED;
		if (tiles[xp + yp * width] == Tile.sky_grass_col) return Tile.SKY_GRASS;
		if (tiles[xp + yp * width] == Tile.sky_grass_dirt_col) return Tile.SKY_GRASS_DIRT;
		if (tiles[xp + yp * width] == Tile.seed_grass_col) return Tile.SEED_GRASS;
		if (tiles[xp + yp * width] == Tile.long_grass_col) return Tile.LONG_GRASS;
		if (tiles[xp + yp * width] == Tile.sand_col) return Tile.SAND;
		if (tiles[xp + yp * width] == Tile.spike_col) return Tile.SPIKE;
		if (tiles[xp + yp * width] == Tile.spike_stone_col) return Tile.SPIKE_STONE;
		if (tiles[xp + yp * width] == Tile.spike_dirt_col) return Tile.SPIKE_DIRT;
		if (tiles[xp + yp * width] == Tile.ladder_col) return Tile.LADDER;
		if (tiles[xp + yp * width] == Tile.trunk_col) return Tile.TRUNK;
		if (tiles[xp + yp * width] == Tile.tree_leaves_col) return Tile.TREE_LEAVES;
		if (tiles[xp + yp * width] == Tile.cave_dirt_col) return Tile.CAVE_DIRT;
		if (tiles[xp + yp * width] == Tile.stone_background_col) return Tile.STONE_BACKGROUND;
		if (tiles[xp + yp * width] == Tile.ladder_cave_dirt_col) return Tile.LADDER_CAVE_DIRT;
		if (tiles[xp + yp * width] == Tile.mushroom_base_col) return Tile.MUSHROOM_BASE;
		if (tiles[xp + yp * width] == Tile.mushroom_top_col) return Tile.MUSHROOM_TOP;
		if (tiles[xp + yp * width] == Tile.bar_apple_col) return Tile.BAR_APPLE;
		if (tiles[xp + yp * width] == Tile.bar_background_col) return Tile.BAR_BACKGROUND;
		if (tiles[xp + yp * width] == Tile.bar_desk_col) return Tile.BAR_DESK;
		if (tiles[xp + yp * width] == Tile.bar_floor_col) return Tile.BAR_FLOOR;
		if (tiles[xp + yp * width] == Tile.bar_potato_col) return Tile.BAR_POTATO;
		if (tiles[xp + yp * width] == Tile.bar_shelf_col) return Tile.BAR_SHELF;
		if (tiles[xp + yp * width] == Tile.weed_wallpaper_col) return Tile.WEED_WALLPAPER;
		if (tiles[xp + yp * width] == Tile.red_carpet_col) return Tile.RED_CARPET_FLOOR;
		if (tiles[xp + yp * width] == Tile.brown_wallpaper_col) return Tile.BROWN_WALLPAPER;
		if (tiles[xp + yp * width] == Tile.dirt_wall_col) return Tile.DIRT_WALL;
		if (tiles[xp + yp * width] == Tile.blue_wallpaper_col) return Tile.BLUE_WALLPAPER;
		if (tiles[xp + yp * width] == Tile.light_green_wallpaper_col) return Tile.LIGHT_GREEN_WALLPAPER;
		if (tiles[xp + yp * width] == Tile.water_spike_col) return Tile.WATER_SPIKE;
		if (tiles[xp + yp * width] == Tile.water_coral_col) return Tile.WATER_CORAL;
		if (tiles[xp + yp * width] == Tile.water_grass_col) return Tile.WATER_GRASS;
		if (tiles[xp + yp * width] == Tile.water_rock_col) return Tile.WATER_ROCK;
		if (tiles[xp + yp * width] == Tile.red_wallpaper_col) return Tile.RED_WALLPAPER;
		if (tiles[xp + yp * width] == Tile.orange_wallpaper_col) return Tile.ORANGE_WALLPAPER;
		if (tiles[xp + yp * width] == Tile.straw_col) return Tile.STRAW;
		if (tiles[xp + yp * width] == Tile.greyish_col) return Tile.GREY_WALL;
		if (tiles[xp + yp * width] == Tile.mud_hut_col) return Tile.MUD_HUT;
		if (tiles[xp + yp * width] == Tile.hut_wallpaper_col) return Tile.HUT_WALLPAPER;
		if (tiles[xp + yp * width] == Tile.grass_col) return Tile.GRASS;
		if (tiles[xp + yp * width] == Tile.shop_desk_col) return Tile.SHOP_DESK;
		if (tiles[xp + yp * width] == Tile.beatroot_col) return Tile.BEATROOT;
		if (tiles[xp + yp * width] == Tile.onion_col) return Tile.ONION;
		if (tiles[xp + yp * width] == Tile.gun_shelf_col) return Tile.GUN_SHELF;
		if (tiles[xp + yp * width] == Tile.sewer_water_top_col) return Tile.SEWER_WATER_TOP;
		if (tiles[xp + yp * width] == Tile.sewer_water_col) return Tile.SEWER_WATER_NORMAL;
		if (tiles[xp + yp * width] == Tile.sewer_wall_col) return Tile.SEWER_WALL;
		if (tiles[xp + yp * width] == Tile.sewer_water_coral_col) return Tile.SEWER_WATER_CORAL;
		if (tiles[xp + yp * width] == Tile.sewer_water_grass_col) return Tile.SEWER_WATER_GRASS;
		if (tiles[xp + yp * width] == Tile.sewer_water_spike_col) return Tile.SEWER_WATER_SPIKE;
		if (tiles[xp + yp * width] == Tile.yellow_back_col) return Tile.YELLOW_BACK;
		if (tiles[xp + yp * width] == Tile.red_back_col) return Tile.RED_BACK;
		if (tiles[xp + yp * width] == Tile.brown_back_col) return Tile.BROWN_BACK;
		if (tiles[xp + yp * width] == Tile.fire_col) return Tile.FIRE;
		if (tiles[xp + yp * width] == Tile.yellow_pep_col) return Tile.YELLOW_PEP_FACE;
		if (tiles[xp + yp * width] == Tile.red_pep_col) return Tile.RED_PEP_FACE;
		if (tiles[xp + yp * width] == Tile.green_cuc_col) return Tile.GREEN_CUC_FACE;
		if (tiles[xp + yp * width] == Tile.bank_box_col) return Tile.BANK_BOX;
		if (tiles[xp + yp * width] == Tile.bars_col) return Tile.BARS;
		if (tiles[xp + yp * width] == Tile.rock_dirt_col) return Tile.ROCK_DIRT;
		if (tiles[xp + yp * width] == Tile.ladder_water_col) return Tile.LADDER_WATER;
		if (tiles[xp + yp * width] == Tile.tower_brick_col) return Tile.TOWER_BRICK;
		if (tiles[xp + yp * width] == Tile.forest_weed_col) return Tile.FOREST_WEED;
		if (tiles[xp + yp * width] == Tile.forest_grass_col) return Tile.FOREST_GRASS;
		if (tiles[xp + yp * width] == Tile.forest_dirt_col) return Tile.FOREST_DIRT;
		if (tiles[xp + yp * width] == Tile.forest_flower_col) return Tile.FOREST_FLOWER;
		if (tiles[xp + yp * width] == Tile.dirt_bars_col) return Tile.DIRT_BARS;
		if (tiles[xp + yp * width] == Tile.forest_dirt_hidden_solid_col) return Tile.FOREST_DIRT_HIDDEN_SOLID;
		if (tiles[xp + yp * width] == Tile.blood_floor_col) return Tile.BLOOD_FLOOR;
		if (tiles[xp + yp * width] == Tile.blood_floor_dirt_col) return Tile.BLOOD_FLOOR_DIRT;
		return Tile.VOID;
	}
}
