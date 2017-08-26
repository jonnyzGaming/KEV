package jonny.main.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	//spriteSheet properties
	private String path;
	private final int width;
	private final int height;
	public int[] pixels;

	//static spriteSheets
	public static SpriteSheet core_tiles = new SpriteSheet("/textures/Tile_sheets/core_tile_sheet1.png", 160, 160);
	public static SpriteSheet projectiles_sheet = new SpriteSheet("/textures/Projectile_sheets/projectile_sheet.png", 160, 160);
	public static SpriteSheet KEV_sheet = new SpriteSheet("/textures/character_sheets/KEV2.png", 288, 216);
	public static SpriteSheet mr_mash_sheet = new SpriteSheet("/textures/character_sheets/mr_mash.png", 30, 90);
	public static SpriteSheet mr_mushroom_sheet = new SpriteSheet("/textures/character_sheets/mr_mushroom.png", 30, 60);
	public static SpriteSheet roy_shop_sheet = new SpriteSheet("/textures/character_sheets/roy_shop.png", 30, 60);
	public static SpriteSheet giant_mash_sheet = new SpriteSheet("/textures/character_sheets/giant_mash.png", 150, 300);
	public static SpriteSheet cave_crawler_sheet = new SpriteSheet("/textures/character_sheets/cave_crawler.png", 30, 60);
	public static SpriteSheet cave_man_sheet = new SpriteSheet("/textures/character_sheets/cave_man.png", 23, 76);
	public static SpriteSheet cave_woman_sheet = new SpriteSheet("/textures/character_sheets/cave_woman.png", 23, 38);
	public static SpriteSheet larry_leak_sheet = new SpriteSheet("/textures/character_sheets/larry_leak.png", 240, 80);
	public static SpriteSheet black_eye_pees_sheet = new SpriteSheet("/textures/character_sheets/black_eye_pee.png", 100, 120);
	public static SpriteSheet sprout_sheet = new SpriteSheet("/textures/character_sheets/sprout.png", 30, 60);
	public static SpriteSheet wife_sheet = new SpriteSheet("/textures/character_sheets/kevs_wife.png", 24, 48);
	public static SpriteSheet cabbage_guard_sheet = new SpriteSheet("/textures/character_sheets/cabbage_guard.png", 200, 80);
	public static SpriteSheet cabbage_male_sheet = new SpriteSheet("/textures/character_sheets/cabbage_male.png", 32, 64);
	public static SpriteSheet cabbage_female_sheet = new SpriteSheet("/textures/character_sheets/cabbage_woman.png", 32, 64);
	public static SpriteSheet cabbage_kid_sheet = new SpriteSheet("/textures/character_sheets/cabbage_kid.png", 17, 34);
	public static SpriteSheet cabbage_kid_kite_sheet = new SpriteSheet("/textures/character_sheets/cabbage_kid_kite.png", 81, 54);
	public static SpriteSheet barry_beetroot_sheet = new SpriteSheet("/textures/character_sheets/barry_beetroot.png", 30, 60);
	public static SpriteSheet slime_sheet = new SpriteSheet("/textures/character_sheets/slime.png", 40, 60);
	public static SpriteSheet blob_sheet = new SpriteSheet("/textures/character_sheets/blob.png", 90, 60);
	public static SpriteSheet sewer_boss_sheet = new SpriteSheet("/textures/character_sheets/sewer_boss.png", 276, 204);
	public static SpriteSheet peter_pickle_sheet = new SpriteSheet("/textures/character_sheets/peter_pickle.png", 40, 80);
	public static SpriteSheet billy_brocoli_sheet = new SpriteSheet("/textures/character_sheets/billy_brocoli.png", 40, 80);
	public static SpriteSheet cabbage_king_sheet = new SpriteSheet("/textures/character_sheets/cabbage_king.png", 40, 80);
	public static SpriteSheet teen_cabbage = new SpriteSheet("/textures/character_sheets/teen_cabbage_male.png", 30, 60);
	public static SpriteSheet crying_kid_cabbage = new SpriteSheet("/textures/character_sheets/crying kid.png", 17, 68);
	public static SpriteSheet sea_slug_sheet = new SpriteSheet("/textures/character_sheets/seaSlug.png", 120, 60);
	public static SpriteSheet spider_sheet = new SpriteSheet("/textures/character_sheets/spider.png", 21, 42);
	public static SpriteSheet cabbage_chef_sheet = new SpriteSheet("/textures/character_sheets/cabbage_chef.png", 30, 80);
	public static SpriteSheet crazy_kids_sheet = new SpriteSheet("/textures/character_sheets/crazy_kids.png", 60, 40);
	public static SpriteSheet cabbage_queen_sheet = new SpriteSheet("/textures/character_sheets/cabbage_queen.png", 30, 120);
	public static SpriteSheet cabbage_nerd_sheet = new SpriteSheet("/textures/character_sheets/cabbage_nerd.png", 30, 180);
	public static SpriteSheet potato_rebel_sheet = new SpriteSheet("/textures/character_sheets/potato_rebel.png", 175, 70);
	public static SpriteSheet crazy_woman_sheet = new SpriteSheet("/textures/character_sheets/crazyWoman.png", 90, 60);
	public static SpriteSheet cabbageBanker = new SpriteSheet("/textures/character_sheets/cabbageBanker.png", 30, 60);
	public static SpriteSheet crazy_male_sheet = new SpriteSheet("/textures/character_sheets/crazyMale.png", 90, 60);
	public static SpriteSheet high_cabbage_sheet = new SpriteSheet("/textures/character_sheets/highCabbage.png", 40, 80);
	public static SpriteSheet red_rebel_sheet = new SpriteSheet("/textures/character_sheets/redRebel.png", 30, 90);
	public static SpriteSheet cabbage_sniper_sheet = new SpriteSheet("/textures/character_sheets/cabbage_sniper.png", 40, 80);
	public static SpriteSheet human_sheet = new SpriteSheet("/textures/character_sheets/human.png", 80, 80);
	public static SpriteSheet caged_human_sheet = new SpriteSheet("/textures/character_sheets/CagedHuman.png", 34, 68);
	public static SpriteSheet evil_potato_sheet = new SpriteSheet("/textures/character_sheets/EvilPotato.png", 225, 360);
	public static SpriteSheet bomb_sheet = new SpriteSheet("/textures/character_sheets/bomb.png", 75, 15);
	public static SpriteSheet kids_sheet = new SpriteSheet("/textures/character_sheets/kevs_kids.png", 60, 30);

	public static SpriteSheet weapons_sheet = new SpriteSheet("/textures/Weapon_sheets/weapons_sheet.png", 100, 100);

	public static SpriteSheet interface_sheet = new SpriteSheet("/textures/Interface_sheets/main_sheet.png", 150, 40);
	public static SpriteSheet collectables_sheet = new SpriteSheet("/textures/Collectables_sheet/collectables_sheet.png", 50, 50);
	public static SpriteSheet extra_sheet = new SpriteSheet("/textures/Extra_sprites_sheet/extra_sheet.png", 500, 750);

	//static backgrounds
	public static SpriteSheet sky_background = new SpriteSheet("/textures/Backgrounds/sky.png", 275, 172);
	public static SpriteSheet mountain_background = new SpriteSheet("/textures/Backgrounds/mountains.png", 275, 172);
	public static SpriteSheet green_blob = new SpriteSheet("/textures/Backgrounds/green_blob.png", 275, 172);
	public static SpriteSheet night = new SpriteSheet("/textures/Backgrounds/night.png", 275, 172);

	//constructor taking the sheets path and width/height (in pixels)
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		this.width = width;
		this.height = height;

		pixels = new int[width * height];
		loadSheet();

		//for debugging
		//Game.log(path);
	}

	//loads the sheet from the specified path into pixel array.
	public void loadSheet() {
		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, getWidth());

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
