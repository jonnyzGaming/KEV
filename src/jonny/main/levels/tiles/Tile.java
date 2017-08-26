package jonny.main.levels.tiles;

import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.levels.tiles.core.BackgroundTile;
import jonny.main.levels.tiles.core.CliffDirtTile;
import jonny.main.levels.tiles.core.DirtTile;
import jonny.main.levels.tiles.core.FireTile;
import jonny.main.levels.tiles.core.FlowerDirtTile;
import jonny.main.levels.tiles.core.GrassDirtTile;
import jonny.main.levels.tiles.core.HiddenTile;
import jonny.main.levels.tiles.core.LadderTile;
import jonny.main.levels.tiles.core.LongGrassTile;
import jonny.main.levels.tiles.core.SandTile;
import jonny.main.levels.tiles.core.SeedGrassTile;
import jonny.main.levels.tiles.core.SkyGrassTile;
import jonny.main.levels.tiles.core.SkyTile;
import jonny.main.levels.tiles.core.SolidTile;
import jonny.main.levels.tiles.core.SpikeTile;
import jonny.main.levels.tiles.core.TreeTile;
import jonny.main.levels.tiles.core.TrunkTile;
import jonny.main.levels.tiles.core.WaterTile;
import jonny.main.levels.tiles.core.caveDirtTile;
import jonny.main.levels.tiles.voider.VoidTile;

public abstract class Tile {

	//main basic tile properties
	public Sprite sprite;
	public Sprite sprite2; //if animated
	public Sprite sprite1; //if animated
	public boolean isAnimated = false;

	//all tiles here
	//core tile sheet1
	public static Tile VOID = new VoidTile(Sprite.voidie);
	public static Tile WATER_TOP = new WaterTile(Sprite.water_top);
	public static Tile WATER_NORMAL = new WaterTile(Sprite.water_normal);
	public static Tile DIRT = new DirtTile(Sprite.dirt);
	public static Tile HIDDEN_DIRT_SOLID = new DirtTile(Sprite.dirt);
	public static Tile HIDDEN__DIRT = new HiddenTile(Sprite.hidden_dirt);
	public static Tile HIDDEN_DIRT_RIGHT = new HiddenTile(Sprite.hidden_dirt_right);
	public static Tile NON_SOLID__DIRT = new HiddenTile(Sprite.dirt);
	public static Tile GRASS_DIRT = new GrassDirtTile(Sprite.grass_dirt);
	public static Tile SKY = new SkyTile(Sprite.sky);
	public static Tile CLIFF_DIRT = new CliffDirtTile(Sprite.cliff_dirt);
	public static Tile HIDDEN_CLIFF_DIRT = new HiddenTile(Sprite.hidden_cliff_dirt);
	public static Tile FLOWER = new FlowerDirtTile(Sprite.flower);
	public static Tile FLOWER_RED = new FlowerDirtTile(Sprite.flower_red);
	public static Tile SKY_GRASS = new SkyGrassTile(Sprite.sky_grass);
	public static Tile SKY_GRASS_DIRT = new SkyGrassTile(Sprite.sky_grass_dirt);

	public static Tile SEED_GRASS = new SeedGrassTile(Sprite.seed_dirt);
	public static Tile LONG_GRASS = new LongGrassTile(Sprite.long_grass);
	public static Tile SAND = new SandTile(Sprite.sand);
	public static Tile SPIKE = new SpikeTile(Sprite.spike);
	public static Tile SPIKE_DIRT = new SpikeTile(Sprite.spike_dirt);
	public static Tile SPIKE_STONE = new SpikeTile(Sprite.spike_stone);
	public static Tile LADDER = new LadderTile(Sprite.ladder);
	public static Tile LADDER_CAVE_DIRT = new LadderTile(Sprite.ladder_cave_dirt);
	public static Tile LADDER_WATER = new LadderTile(Sprite.ladder_water);
	public static Tile TRUNK = new TrunkTile(Sprite.trunk);
	public static Tile TREE_LEAVES = new TreeTile(Sprite.tree_leaves);
	public static Tile CAVE_DIRT = new caveDirtTile(Sprite.cave_back_dirt);
	public static Tile DIRT_WALL = new SolidTile(Sprite.dirt_wall);
	public static Tile STONE_BACKGROUND = new BackgroundTile(Sprite.stone);

	public static Tile WATER_SPIKE = new SpikeTile(Sprite.water_spike);
	public static Tile WATER_CORAL = new WaterTile(Sprite.water_coral);
	public static Tile WATER_GRASS = new WaterTile(Sprite.water_grass);
	public static Tile WATER_ROCK = new WaterTile(Sprite.water_rock);

	//mush bar tiles
	public static Tile MUSHROOM_BASE = new SolidTile(Sprite.mushroom_base);
	public static Tile MUSHROOM_TOP = new SolidTile(Sprite.mushroom_top);
	public static Tile BAR_APPLE = new BackgroundTile(Sprite.bar_apple);
	public static Tile BAR_POTATO = new BackgroundTile(Sprite.bar_potato);
	public static Tile BAR_FLOOR = new SolidTile(Sprite.bar_floor);
	public static Tile BAR_SHELF = new BackgroundTile(Sprite.bar_shelf);
	public static Tile BAR_DESK = new BackgroundTile(Sprite.bar_desk);
	public static Tile BAR_BACKGROUND = new BackgroundTile(Sprite.bar_background);
	public static Tile WEED_WALLPAPER = new BackgroundTile(Sprite.weed_wallpaper);
	public static Tile RED_CARPET_FLOOR = new SolidTile(Sprite.red_carpet_floor);
	public static Tile BROWN_WALLPAPER = new BackgroundTile(Sprite.brown_wallpaper);

	public static Tile BLUE_WALLPAPER = new BackgroundTile(Sprite.blue_wallpaper);
	public static Tile LIGHT_GREEN_WALLPAPER = new BackgroundTile(Sprite.light_green_wallpaper);
	public static Tile RED_WALLPAPER = new BackgroundTile(Sprite.red_wallpaper);
	public static Tile ORANGE_WALLPAPER = new BackgroundTile(Sprite.orange_wallpaper);

	//village ones
	public static Tile STRAW = new SolidTile(Sprite.straw_hut);
	public static Tile GREY_WALL = new SolidTile(Sprite.grey);
	public static Tile MUD_HUT = new SolidTile(Sprite.mud_hut);
	public static Tile HUT_WALLPAPER = new BackgroundTile(Sprite.hut_wallpaper);
	public static Tile GRASS = new BackgroundTile(Sprite.grass);
	public static Tile SHOP_DESK = new BackgroundTile(Sprite.shop_desk);
	public static Tile BEATROOT = new BackgroundTile(Sprite.beatroot_shop);
	public static Tile ONION = new BackgroundTile(Sprite.onion_shop);
	public static Tile GUN_SHELF = new BackgroundTile(Sprite.gun_shelf);
	public static Tile SEWER_WATER_TOP = new WaterTile(Sprite.sewer_water_top);
	public static Tile SEWER_WATER_NORMAL = new WaterTile(Sprite.sewer_water);
	public static Tile SEWER_PIPE = new HiddenTile(Sprite.sewer_pipe);
	public static Tile SEWER_WALL = new BackgroundTile(Sprite.sewer_wall);
	public static Tile SEWER_WATER_SPIKE = new SpikeTile(Sprite.sewer_water_spike);
	public static Tile SEWER_WATER_CORAL = new WaterTile(Sprite.sewer_water_coral);
	public static Tile SEWER_WATER_GRASS = new WaterTile(Sprite.sewer_water_grass);
	public static Tile YELLOW_BACK = new BackgroundTile(Sprite.yellow_back);
	public static Tile RED_BACK = new BackgroundTile(Sprite.red_back);
	public static Tile BROWN_BACK = new BackgroundTile(Sprite.brown_back);
	public static Tile FIRE = new FireTile(Sprite.fire1, Sprite.fire2);
	public static Tile YELLOW_PEP_FACE = new BackgroundTile(Sprite.yellow_pepper_face);
	public static Tile GREEN_CUC_FACE = new BackgroundTile(Sprite.green_cucumber_face);
	public static Tile RED_PEP_FACE = new BackgroundTile(Sprite.red_pepper_face);
	public static Tile BANK_BOX = new BackgroundTile(Sprite.bank_box);
	public static Tile BARS = new SolidTile(Sprite.bars);
	public static Tile ROCK_DIRT = new SolidTile(Sprite.rock_dirt);
	public static Tile TOWER_BRICK = new SolidTile(Sprite.tower_brick);

	//forest ones
	public static Tile FOREST_WEED = new BackgroundTile(Sprite.forest_weed);
	public static Tile FOREST_DIRT = new SolidTile(Sprite.forest_dirt);
	public static Tile FOREST_GRASS = new SolidTile(Sprite.forest_grass);
	public static Tile FOREST_FLOWER = new BackgroundTile(Sprite.forest_flower);
	public static Tile DIRT_BARS = new SolidTile(Sprite.dirt_bars);
	public static Tile FOREST_DIRT_HIDDEN = new HiddenTile(Sprite.forest_hidden_dirt);
	public static Tile FOREST_DIRT_HIDDEN_SOLID = new DirtTile(Sprite.forest_dirt);
	public static Tile FOREST_NON_SOLID_HIDDEN = new HiddenTile(Sprite.forest_dirt);
	public static Tile BLOOD_FLOOR = new SolidTile(Sprite.blood_floor);
	public static Tile BLOOD_FLOOR_DIRT = new SolidTile(Sprite.blood_floor_dirt);

	//tile colors
	//core_tile_sheet1
	public static final int water_col = 0xff0094FF;
	public static final int water_col2 = 0xff00BEFF;

	public static final int dirt_grass_col = 0xff267F00;
	public static final int dirt_col = 0xff915004;
	public static final int hidden_dirt_solid_col = 0xff8E612D;
	public static final int hidden_dirt_col = 0xff9E5604;
	public static final int hidden_dirt_right_col = 0xff845B02;
	public static final int non_solid_dirt_col = 0xffAF5D05;
	public static final int sky_col = 0xff0026FF;
	public static final int cliff_dirt_col = 0xff5E3608;
	public static final int hidden_cliff_dirt_col = 0xff3F2405;
	public static final int flower_col = 0xffFFE332;
	public static final int flower_red_col = 0xffFF3D4A;
	public static final int sky_grass_col = 0xff45823E;
	public static final int sky_grass_dirt_col = 0xff3C7035;
	public static final int seed_grass_col = 0xff472F01;
	public static final int long_grass_col = 0xff62FF2D;
	public static final int sand_col = 0xffEABA17;
	public static final int spike_col = 0xffC0C0C0;
	public static final int spike_dirt_col = 0xffBFB3B3;
	public static final int spike_stone_col = 0xffA0A0A0;
	public static final int ladder_col = 0xffD5823E;
	public static final int trunk_col = 0xff472603;
	public static final int tree_leaves_col = 0xff2FFF14;
	public static final int cave_dirt_col = 0xff840303;
	public static final int ladder_cave_dirt_col = 0xffD56D08;
	public static final int dirt_wall_col = 0xff510202;
	public static final int stone_background_col = 0xff606060;

	public static final int water_spike_col = 0xff9191B7;
	public static final int water_coral_col = 0xffB3E12B;
	public static final int water_grass_col = 0xff55FF7C;
	public static final int water_rock_col = 0xff38A591;
	public static final int ladder_water_col = 0xffFFDCA5;

	//mush bar colors
	public static final int mushroom_base_col = 0xffFFF177;
	public static final int mushroom_top_col = 0xff00FF21;
	public static final int bar_apple_col = 0xffA1FF84;
	public static final int bar_potato_col = 0xffFF9719;
	public static final int bar_floor_col = 0xff7F6A00;
	public static final int bar_shelf_col = 0xffCD3E00;
	public static final int bar_desk_col = 0xff7F3E00;
	public static final int bar_background_col = 0xff520400;
	public static final int weed_wallpaper_col = 0xff266900;
	public static final int red_carpet_col = 0xffFF0571;
	public static final int brown_wallpaper_col = 0xff514545;

	public static final int blue_wallpaper_col = 0xff6D6DFF;
	public static final int light_green_wallpaper_col = 0xff95FF68;
	public static final int red_wallpaper_col = 0xffBC0051;
	public static final int orange_wallpaper_col = 0xffE55B00;

	//village ones
	public static final int straw_col = 0xffFFC300;
	public static final int greyish_col = 0xffCCCCCC;
	public static final int mud_hut_col = 0xffA58866;
	public static final int hut_wallpaper_col = 0xff6B5842;
	public static final int grass_col = 0xff38BC00;
	public static final int shop_desk_col = 0xff8E4500;
	public static final int beatroot_col = 0xff7600AD;
	public static final int onion_col = 0xffB59600;
	public static final int gun_shelf_col = 0xff7A5250;
	public static final int sewer_water_top_col = 0xff41AD41;
	public static final int sewer_water_col = 0xff6EAD6E;
	public static final int sewer_pipe_col = 0xff99AE99;
	public static final int sewer_wall_col = 0xff628762;
	public static final int sewer_water_spike_col = 0xffD7E5D7;
	public static final int sewer_water_coral_col = 0xffB6C458;
	public static final int sewer_water_grass_col = 0xff56C458;
	public static final int yellow_back_col = 0xffFFE875;
	public static final int red_back_col = 0xffFF1433;
	public static final int brown_back_col = 0xff7C5252;
	public static final int fire_col = 0xffD18C00;
	public static final int yellow_pep_col = 0xffB78900;
	public static final int red_pep_col = 0xffB70000;
	public static final int green_cuc_col = 0xff195400;
	public static final int bank_box_col = 0xff43513D;
	public static final int bars_col = 0xffC6C6C6;
	public static final int rock_dirt_col = 0xff898989;
	public static final int tower_brick_col = 0xff3D3D3D;

	//forest ones
	public static final int forest_weed_col = 0xff616900;
	public static final int forest_grass_col = 0xff527448;
	public static final int forest_dirt_col = 0xff824500;
	public static final int forest_flower_col = 0xffC1AA26;
	public static final int dirt_bars_col = 0xffAAC2C6;
	public static final int forest_dirt_hidden_col = 0xffCEA93B;
	public static final int forest_dirt_hidden_solid_col = 0xff8C8437;
	public static final int forest_dirt_hidden_normal_col = 0xffAD774C;
	public static final int blood_floor_col = 0xff602020;
	public static final int blood_floor_dirt_col = 0xff7C2A2A;

	//basic constructor
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	//for animated sprites.
	public Tile(Sprite sprite, Sprite sprite2) {
		this.sprite = sprite;
		this.sprite1 = sprite;
		this.sprite2 = sprite2;
		isAnimated = true;

	}

	public void render(int x, int y, Screen screen) {
		screen.renderObject(x << Screen.TILE_SHIFT, y << Screen.TILE_SHIFT, sprite);
	}

	public void update() {

	}

	public boolean solid() {
		return false;
	}

	public boolean breakable() {
		return false;
	}

	public boolean isWater() {
		return false;
	}

	public boolean isDeath() {
		return false;
	}

	public boolean isClimbable() {
		return false;
	}

	public boolean isHidden() {
		return false;
	}

	public boolean isFire() {
		return false;
	}

}
