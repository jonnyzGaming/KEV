package jonny.main.graphics;

public class Sprite {

	//general sprite properties
	public int size;
	public int xPix, yPix;
	public int[] pixels;
	public SpriteSheet sheet;
	public int width, height;
	public boolean accurateSprite = false;

	//static backgrounds
	public static Sprite sky_background = new Sprite(275, 172, 0, 0, SpriteSheet.sky_background);
	public static Sprite mountain_background = new Sprite(275, 172, 0, 0, SpriteSheet.mountain_background);
	public static Sprite green_blob = new Sprite(275, 172, 0, 0, SpriteSheet.green_blob);
	public static Sprite night = new Sprite(275, 172, 0, 0, SpriteSheet.night);

	//static sprites here(possible arrays)
	//core tile sprites
	public static Sprite voidie = new Sprite(16, 0);
	public static Sprite water_top = new Sprite(16, 0, 0, SpriteSheet.core_tiles);
	public static Sprite water_normal = new Sprite(16, 0, 1, SpriteSheet.core_tiles);
	public static Sprite grass_dirt = new Sprite(16, 1, 0, SpriteSheet.core_tiles);
	public static Sprite sky = new Sprite(16, 2, 0, SpriteSheet.core_tiles);
	public static Sprite dirt = new Sprite(16, 3, 0, SpriteSheet.core_tiles);
	public static Sprite hidden_dirt = new Sprite(16, 4, 4, SpriteSheet.core_tiles);
	public static Sprite hidden_dirt_right = new Sprite(16, 9, 0, SpriteSheet.core_tiles);
	public static Sprite forest_hidden_dirt = new Sprite(16, 6, 8, SpriteSheet.core_tiles);

	public static Sprite flower = new Sprite(16, 0, 2, SpriteSheet.core_tiles);
	public static Sprite flower_red = new Sprite(16, 5, 3, SpriteSheet.core_tiles);
	public static Sprite stone = new Sprite(16, 1, 1, SpriteSheet.core_tiles);
	public static Sprite cliff_dirt = new Sprite(16, 2, 1, SpriteSheet.core_tiles);
	public static Sprite hidden_cliff_dirt = new Sprite(16, 5, 4, SpriteSheet.core_tiles);

	public static Sprite sky_grass = new Sprite(16, 3, 1, SpriteSheet.core_tiles);
	public static Sprite sky_grass_dirt = new Sprite(16, 3, 4, SpriteSheet.core_tiles);
	public static Sprite sand = new Sprite(16, 3, 3, SpriteSheet.core_tiles);
	public static Sprite spike = new Sprite(16, 2, 2, SpriteSheet.core_tiles);
	public static Sprite spike_dirt = new Sprite(16, 1, 4, SpriteSheet.core_tiles);
	public static Sprite spike_stone = new Sprite(16, 2, 4, SpriteSheet.core_tiles);
	public static Sprite trunk = new Sprite(16, 5, 0, SpriteSheet.core_tiles);
	public static Sprite tree_leaves = new Sprite(16, 4, 1, SpriteSheet.core_tiles);
	public static Sprite cave_back_dirt = new Sprite(16, 3, 2, SpriteSheet.core_tiles);
	public static Sprite ladder = new Sprite(16, 4, 0, SpriteSheet.core_tiles);
	public static Sprite ladder_cave_dirt = new Sprite(16, 5, 1, SpriteSheet.core_tiles);
	public static Sprite ladder_water = new Sprite(16, 9, 1, SpriteSheet.core_tiles);
	public static Sprite seed_dirt = new Sprite(16, 0, 3, SpriteSheet.core_tiles);
	public static Sprite long_grass = new Sprite(16, 1, 2, SpriteSheet.core_tiles);
	public static Sprite dirt_wall = new Sprite(16, 4, 3, SpriteSheet.core_tiles);
	public static Sprite blue_wallpaper = new Sprite(16, 6, 3, SpriteSheet.core_tiles);
	public static Sprite light_green_wallpaper = new Sprite(16, 7, 3, SpriteSheet.core_tiles);
	public static Sprite red_wallpaper = new Sprite(16, 6, 4, SpriteSheet.core_tiles);
	public static Sprite orange_wallpaper = new Sprite(16, 7, 4, SpriteSheet.core_tiles);

	public static Sprite water_spike = new Sprite(16, 0, 5, SpriteSheet.core_tiles);
	public static Sprite water_coral = new Sprite(16, 1, 5, SpriteSheet.core_tiles);
	public static Sprite water_grass = new Sprite(16, 2, 5, SpriteSheet.core_tiles);
	public static Sprite water_rock = new Sprite(16, 3, 5, SpriteSheet.core_tiles);
	public static Sprite sewer_water_spike = new Sprite(16, 5, 6, SpriteSheet.core_tiles);
	public static Sprite sewer_water_coral = new Sprite(16, 3, 6, SpriteSheet.core_tiles);
	public static Sprite sewer_water_grass = new Sprite(16, 4, 6, SpriteSheet.core_tiles);

	//mush bar ones + house ones
	public static Sprite mushroom_base = new Sprite(16, 4, 2, SpriteSheet.core_tiles);
	public static Sprite mushroom_top = new Sprite(16, 5, 2, SpriteSheet.core_tiles);
	public static Sprite bar_apple = new Sprite(16, 7, 1, SpriteSheet.core_tiles);
	public static Sprite bar_potato = new Sprite(16, 6, 2, SpriteSheet.core_tiles);
	public static Sprite bar_floor = new Sprite(16, 6, 1, SpriteSheet.core_tiles);
	public static Sprite bar_shelf = new Sprite(16, 7, 0, SpriteSheet.core_tiles);
	public static Sprite bar_desk = new Sprite(16, 6, 0, SpriteSheet.core_tiles);
	public static Sprite bar_background = new Sprite(16, 7, 2, SpriteSheet.core_tiles);
	//mr mush house ones
	public static Sprite weed_wallpaper = new Sprite(16, 1, 3, SpriteSheet.core_tiles);
	public static Sprite red_carpet_floor = new Sprite(16, 2, 3, SpriteSheet.core_tiles);
	public static Sprite brown_wallpaper = new Sprite(16, 0, 4, SpriteSheet.core_tiles);

	//village ones
	public static Sprite straw_hut = new Sprite(16, 4, 5, SpriteSheet.core_tiles);
	public static Sprite grey = new Sprite(16, 5, 5, SpriteSheet.core_tiles);
	public static Sprite mud_hut = new Sprite(16, 6, 5, SpriteSheet.core_tiles);
	public static Sprite hut_wallpaper = new Sprite(16, 7, 5, SpriteSheet.core_tiles);
	public static Sprite grass = new Sprite(16, 8, 0, SpriteSheet.core_tiles);
	public static Sprite shop_desk = new Sprite(16, 8, 1, SpriteSheet.core_tiles);
	public static Sprite beatroot_shop = new Sprite(16, 8, 2, SpriteSheet.core_tiles);
	public static Sprite onion_shop = new Sprite(16, 8, 3, SpriteSheet.core_tiles);
	public static Sprite gun_shelf = new Sprite(16, 8, 4, SpriteSheet.core_tiles);
	public static Sprite sewer_water_top = new Sprite(16, 0, 6, SpriteSheet.core_tiles);
	public static Sprite sewer_water = new Sprite(16, 1, 6, SpriteSheet.core_tiles);
	public static Sprite sewer_pipe = new Sprite(16, 2, 6, SpriteSheet.core_tiles);
	public static Sprite sewer_wall = new Sprite(16, 3, 6, SpriteSheet.core_tiles);
	public static Sprite yellow_back = new Sprite(16, 6, 6, SpriteSheet.core_tiles);
	public static Sprite red_back = new Sprite(16, 7, 6, SpriteSheet.core_tiles);
	public static Sprite brown_back = new Sprite(16, 8, 6, SpriteSheet.core_tiles);
	public static Sprite fire1 = new Sprite(16, 1, 7, SpriteSheet.core_tiles);
	public static Sprite fire2 = new Sprite(16, 2, 7, SpriteSheet.core_tiles);
	public static Sprite yellow_pepper_face = new Sprite(16, 3, 7, SpriteSheet.core_tiles);
	public static Sprite green_cucumber_face = new Sprite(16, 4, 7, SpriteSheet.core_tiles);
	public static Sprite red_pepper_face = new Sprite(16, 5, 7, SpriteSheet.core_tiles);
	public static Sprite bank_box = new Sprite(16, 6, 7, SpriteSheet.core_tiles);
	public static Sprite bars = new Sprite(16, 7, 7, SpriteSheet.core_tiles);
	public static Sprite rock_dirt = new Sprite(16, 8, 7, SpriteSheet.core_tiles);
	public static Sprite tower_brick = new Sprite(16, 0, 8, SpriteSheet.core_tiles);

	//forest ones  
	public static Sprite forest_weed = new Sprite(16, 1, 8, SpriteSheet.core_tiles);
	public static Sprite forest_dirt = new Sprite(16, 2, 8, SpriteSheet.core_tiles);
	public static Sprite forest_grass = new Sprite(16, 3, 8, SpriteSheet.core_tiles);
	public static Sprite forest_flower = new Sprite(16, 4, 8, SpriteSheet.core_tiles);
	public static Sprite dirt_bars = new Sprite(16, 5, 8, SpriteSheet.core_tiles);
	public static Sprite blood_floor = new Sprite(16, 7, 8, SpriteSheet.core_tiles);
	public static Sprite blood_floor_dirt = new Sprite(16, 8, 8, SpriteSheet.core_tiles);

	//character sprites here
	//Kev player sprites
	public static Sprite kev_forward = new Sprite(24, 0, 0, SpriteSheet.KEV_sheet);
	public static Sprite kev_forward2 = new Sprite(24, 0, 1, SpriteSheet.KEV_sheet);

	public static Sprite kev_right = new Sprite(24, 1, 0, SpriteSheet.KEV_sheet);
	public static Sprite kev_right2 = new Sprite(24, 1, 1, SpriteSheet.KEV_sheet);

	public static Sprite kev_left = new Sprite(24, 2, 0, SpriteSheet.KEV_sheet);
	public static Sprite kev_left2 = new Sprite(24, 2, 1, SpriteSheet.KEV_sheet);

	public static Sprite kev_jump_left = new Sprite(24, 3, 0, SpriteSheet.KEV_sheet);
	public static Sprite kev_jump_right = new Sprite(24, 3, 1, SpriteSheet.KEV_sheet);

	public static Sprite kev_fall_left = new Sprite(24, 4, 0, SpriteSheet.KEV_sheet);
	public static Sprite kev_fall_right = new Sprite(24, 4, 1, SpriteSheet.KEV_sheet);

	public static Sprite kev_ladder1 = new Sprite(24, 5, 0, SpriteSheet.KEV_sheet);
	public static Sprite kev_ladder2 = new Sprite(24, 5, 1, SpriteSheet.KEV_sheet);

	//with potato gun
	public static Sprite kev_forward_pot = new Sprite(24, 0, 2, SpriteSheet.KEV_sheet);
	public static Sprite kev_forward2_pot = new Sprite(24, 0, 3, SpriteSheet.KEV_sheet);

	public static Sprite kev_right_pot = new Sprite(24, 1, 2, SpriteSheet.KEV_sheet);
	public static Sprite kev_right2_pot = new Sprite(24, 1, 3, SpriteSheet.KEV_sheet);

	public static Sprite kev_left_pot = new Sprite(24, 2, 2, SpriteSheet.KEV_sheet);
	public static Sprite kev_left2_pot = new Sprite(24, 2, 3, SpriteSheet.KEV_sheet);

	public static Sprite kev_jump_left_pot = new Sprite(24, 3, 2, SpriteSheet.KEV_sheet);
	public static Sprite kev_jump_right_pot = new Sprite(24, 3, 3, SpriteSheet.KEV_sheet);

	public static Sprite kev_fall_left_pot = new Sprite(24, 4, 2, SpriteSheet.KEV_sheet);
	public static Sprite kev_fall_right_pot = new Sprite(24, 4, 3, SpriteSheet.KEV_sheet);

	public static Sprite kev_ladder1_pot = new Sprite(24, 5, 2, SpriteSheet.KEV_sheet);
	public static Sprite kev_ladder2_pot = new Sprite(24, 5, 3, SpriteSheet.KEV_sheet);

	public static Sprite kev_right_shooting_pot = new Sprite(24, 1, 4, SpriteSheet.KEV_sheet);
	public static Sprite kev_left_shooting_pot = new Sprite(24, 2, 4, SpriteSheet.KEV_sheet);

	//all kev sprites duped with top hats on
	public static Sprite kev_forward_hat = new Sprite(24, 6, 0, SpriteSheet.KEV_sheet);
	public static Sprite kev_forward2_hat = new Sprite(24, 6, 1, SpriteSheet.KEV_sheet);

	public static Sprite kev_right_hat = new Sprite(24, 7, 0, SpriteSheet.KEV_sheet);
	public static Sprite kev_right2_hat = new Sprite(24, 7, 1, SpriteSheet.KEV_sheet);

	public static Sprite kev_left_hat = new Sprite(24, 8, 0, SpriteSheet.KEV_sheet);
	public static Sprite kev_left2_hat = new Sprite(24, 8, 1, SpriteSheet.KEV_sheet);

	public static Sprite kev_jump_left_hat = new Sprite(24, 9, 0, SpriteSheet.KEV_sheet);
	public static Sprite kev_jump_right_hat = new Sprite(24, 9, 1, SpriteSheet.KEV_sheet);

	public static Sprite kev_fall_left_hat = new Sprite(24, 10, 0, SpriteSheet.KEV_sheet);
	public static Sprite kev_fall_right_hat = new Sprite(24, 10, 1, SpriteSheet.KEV_sheet);

	public static Sprite kev_ladder1_hat = new Sprite(24, 11, 0, SpriteSheet.KEV_sheet);
	public static Sprite kev_ladder2_hat = new Sprite(24, 11, 1, SpriteSheet.KEV_sheet);

	//with potato gun
	public static Sprite kev_forward_pot_hat = new Sprite(24, 6, 2, SpriteSheet.KEV_sheet);
	public static Sprite kev_forward2_pot_hat = new Sprite(24, 6, 3, SpriteSheet.KEV_sheet);

	public static Sprite kev_right_pot_hat = new Sprite(24, 7, 2, SpriteSheet.KEV_sheet);
	public static Sprite kev_right2_pot_hat = new Sprite(24, 7, 3, SpriteSheet.KEV_sheet);

	public static Sprite kev_left_pot_hat = new Sprite(24, 8, 2, SpriteSheet.KEV_sheet);
	public static Sprite kev_left2_pot_hat = new Sprite(24, 8, 3, SpriteSheet.KEV_sheet);

	public static Sprite kev_jump_left_pot_hat = new Sprite(24, 9, 2, SpriteSheet.KEV_sheet);
	public static Sprite kev_jump_right_pot_hat = new Sprite(24, 9, 3, SpriteSheet.KEV_sheet);

	public static Sprite kev_fall_left_pot_hat = new Sprite(24, 10, 2, SpriteSheet.KEV_sheet);
	public static Sprite kev_fall_right_pot_hat = new Sprite(24, 10, 3, SpriteSheet.KEV_sheet);

	public static Sprite kev_ladder1_pot_hat = new Sprite(24, 11, 2, SpriteSheet.KEV_sheet);
	public static Sprite kev_ladder2_pot_hat = new Sprite(24, 11, 3, SpriteSheet.KEV_sheet);

	public static Sprite kev_right_shooting_pot_hat = new Sprite(24, 7, 4, SpriteSheet.KEV_sheet);
	public static Sprite kev_left_shooting_pot_hat = new Sprite(24, 8, 4, SpriteSheet.KEV_sheet);

	//all kev sprites with shotgun(normal)
	public static Sprite kev_forward_shotgun = new Sprite(24, 0, 5, SpriteSheet.KEV_sheet);
	public static Sprite kev_forward2_shotgun = new Sprite(24, 0, 6, SpriteSheet.KEV_sheet);

	public static Sprite kev_right_shotgun = new Sprite(24, 1, 5, SpriteSheet.KEV_sheet);
	public static Sprite kev_right2_shotgun = new Sprite(24, 1, 6, SpriteSheet.KEV_sheet);

	public static Sprite kev_left_shotgun = new Sprite(24, 2, 5, SpriteSheet.KEV_sheet);
	public static Sprite kev_left2_shotgun = new Sprite(24, 2, 6, SpriteSheet.KEV_sheet);

	public static Sprite kev_jump_left_shotgun = new Sprite(24, 3, 5, SpriteSheet.KEV_sheet);
	public static Sprite kev_jump_right_shotgun = new Sprite(24, 3, 6, SpriteSheet.KEV_sheet);

	public static Sprite kev_fall_left_shotgun = new Sprite(24, 4, 5, SpriteSheet.KEV_sheet);
	public static Sprite kev_fall_right_shotgun = new Sprite(24, 4, 6, SpriteSheet.KEV_sheet);

	public static Sprite kev_ladder1_shotgun = new Sprite(24, 5, 5, SpriteSheet.KEV_sheet);
	public static Sprite kev_ladder2_shotgun = new Sprite(24, 5, 6, SpriteSheet.KEV_sheet);

	public static Sprite kev_right_shooting_shotgun = new Sprite(24, 1, 7, SpriteSheet.KEV_sheet);
	public static Sprite kev_left_shooting_shotgun = new Sprite(24, 2, 7, SpriteSheet.KEV_sheet);

	//with top hat on(shotgun)
	public static Sprite kev_forward_shotgun_hat = new Sprite(24, 6, 5, SpriteSheet.KEV_sheet);
	public static Sprite kev_forward2_shotgun_hat = new Sprite(24, 6, 6, SpriteSheet.KEV_sheet);

	public static Sprite kev_right_shotgun_hat = new Sprite(24, 7, 5, SpriteSheet.KEV_sheet);
	public static Sprite kev_right2_shotgun_hat = new Sprite(24, 7, 6, SpriteSheet.KEV_sheet);

	public static Sprite kev_left_shotgun_hat = new Sprite(24, 8, 5, SpriteSheet.KEV_sheet);
	public static Sprite kev_left2_shotgun_hat = new Sprite(24, 8, 6, SpriteSheet.KEV_sheet);

	public static Sprite kev_jump_left_shotgun_hat = new Sprite(24, 9, 5, SpriteSheet.KEV_sheet);
	public static Sprite kev_jump_right_shotgun_hat = new Sprite(24, 9, 6, SpriteSheet.KEV_sheet);

	public static Sprite kev_fall_left_shotgun_hat = new Sprite(24, 10, 5, SpriteSheet.KEV_sheet);
	public static Sprite kev_fall_right_shotgun_hat = new Sprite(24, 10, 6, SpriteSheet.KEV_sheet);

	public static Sprite kev_ladder1_shotgun_hat = new Sprite(24, 11, 5, SpriteSheet.KEV_sheet);
	public static Sprite kev_ladder2_shotgun_hat = new Sprite(24, 11, 6, SpriteSheet.KEV_sheet);

	public static Sprite kev_right_shooting_shotgun_hat = new Sprite(24, 7, 7, SpriteSheet.KEV_sheet);
	public static Sprite kev_left_shooting_shotgun_hat = new Sprite(24, 8, 7, SpriteSheet.KEV_sheet);

	//larry leak sprites
	public static Sprite larry_leak_forward = new Sprite(40, 0, 0, SpriteSheet.larry_leak_sheet);
	public static Sprite larry_leak_forward2 = new Sprite(40, 0, 1, SpriteSheet.larry_leak_sheet);

	public static Sprite larry_leak_right1 = new Sprite(40, 1, 0, SpriteSheet.larry_leak_sheet);
	public static Sprite larry_leak_right2 = new Sprite(40, 1, 1, SpriteSheet.larry_leak_sheet);

	public static Sprite larry_leak_left1 = new Sprite(40, 2, 0, SpriteSheet.larry_leak_sheet);
	public static Sprite larry_leak_left2 = new Sprite(40, 2, 1, SpriteSheet.larry_leak_sheet);

	public static Sprite larry_leak_jump_left = new Sprite(40, 3, 0, SpriteSheet.larry_leak_sheet);
	public static Sprite larry_leak_jump_right = new Sprite(40, 3, 1, SpriteSheet.larry_leak_sheet);

	public static Sprite larry_leak_fall_left = new Sprite(40, 4, 0, SpriteSheet.larry_leak_sheet);
	public static Sprite larry_leak_fall_right = new Sprite(40, 4, 1, SpriteSheet.larry_leak_sheet);

	public static Sprite larry_leak_ladder1 = new Sprite(40, 5, 0, SpriteSheet.larry_leak_sheet);
	public static Sprite larry_leak_ladder2 = new Sprite(40, 5, 1, SpriteSheet.larry_leak_sheet);

	//mr mash sprites
	public static Sprite mr_mash1 = new Sprite(30, 0, 0, SpriteSheet.mr_mash_sheet);
	public static Sprite mr_mash2 = new Sprite(30, 0, 1, SpriteSheet.mr_mash_sheet);

	//giant mash boss sprites
	public static Sprite giant_mash1 = new Sprite(150, 0, 0, SpriteSheet.giant_mash_sheet);
	public static Sprite giant_mash2 = new Sprite(150, 0, 1, SpriteSheet.giant_mash_sheet);

	//mr mushroom sprites
	public static Sprite mr_mushroom1 = new Sprite(30, 0, 0, SpriteSheet.mr_mushroom_sheet);
	public static Sprite mr_mushroom2 = new Sprite(30, 0, 1, SpriteSheet.mr_mushroom_sheet);

	//roy sprites
	public static Sprite roy1 = new Sprite(30, 0, 0, SpriteSheet.roy_shop_sheet);
	public static Sprite roy2 = new Sprite(30, 0, 1, SpriteSheet.roy_shop_sheet);

	//cave crawler sprites
	public static Sprite cave_crawler_1 = new Sprite(30, 0, 0, SpriteSheet.cave_crawler_sheet);
	public static Sprite cave_crawler_2 = new Sprite(30, 0, 1, SpriteSheet.cave_crawler_sheet);

	//cave man/woman sprites
	public static Sprite cave_man1 = new Sprite(23, 38, 0, 0, SpriteSheet.cave_man_sheet);
	public static Sprite cave_man2 = new Sprite(23, 38, 0, 38, SpriteSheet.cave_man_sheet);
	public static Sprite cave_woman = new Sprite(23, 38, 0, 0, SpriteSheet.cave_woman_sheet);

	//black eye pee sprites
	public static Sprite black_eye_pee = new Sprite(30, 0, 0, SpriteSheet.black_eye_pees_sheet);
	public static Sprite black_eye_pee2 = new Sprite(30, 0, 1, SpriteSheet.black_eye_pees_sheet);

	//black eye pee dad sprites
	public static Sprite black_eye_pee_dad = new Sprite(54, 54, 30, 0, SpriteSheet.black_eye_pees_sheet);
	public static Sprite black_eye_pee_dad2 = new Sprite(54, 54, 30, 54, SpriteSheet.black_eye_pees_sheet);

	//sprouty steve sprites
	public static Sprite sprout1 = new Sprite(30, 0, 0, SpriteSheet.sprout_sheet);
	public static Sprite sprout2 = new Sprite(30, 0, 1, SpriteSheet.sprout_sheet);

	//kevs wife
	public static Sprite wife1 = new Sprite(24, 0, 0, SpriteSheet.wife_sheet);
	public static Sprite wife2 = new Sprite(24, 0, 1, SpriteSheet.wife_sheet);

	//barry beetroot
	public static Sprite barry_beetroot = new Sprite(30, 0, 0, SpriteSheet.barry_beetroot_sheet);
	public static Sprite barry_beetroot2 = new Sprite(30, 0, 1, SpriteSheet.barry_beetroot_sheet);

	//slime sprites
	public static Sprite slime1 = new Sprite(40, 30, 0, 0, SpriteSheet.slime_sheet);
	public static Sprite slime2 = new Sprite(40, 30, 0, 30, SpriteSheet.slime_sheet);

	//sewer boss sprites
	public static Sprite sewer_boss1 = new Sprite(276, 102, 0, 0, SpriteSheet.sewer_boss_sheet);
	public static Sprite sewer_boss2 = new Sprite(276, 102, 0, 102, SpriteSheet.sewer_boss_sheet);

	//peter pickle sprites
	public static Sprite peter_pickle1 = new Sprite(40, 0, 0, SpriteSheet.peter_pickle_sheet);
	public static Sprite peter_pickle2 = new Sprite(40, 0, 1, SpriteSheet.peter_pickle_sheet);

	//billy brocoli
	public static Sprite billy_brocoli1 = new Sprite(40, 0, 0, SpriteSheet.billy_brocoli_sheet);
	public static Sprite billy_brocoli2 = new Sprite(40, 0, 1, SpriteSheet.billy_brocoli_sheet);

	//cabbage guard sprites + extra cabbages
	public static Sprite cabbage_guard1 = new Sprite(40, 0, 0, SpriteSheet.cabbage_guard_sheet);
	public static Sprite cabbage_guard2 = new Sprite(40, 0, 1, SpriteSheet.cabbage_guard_sheet);
	public static Sprite cabbage_guard_left1 = new Sprite(40, 1, 0, SpriteSheet.cabbage_guard_sheet);
	public static Sprite cabbage_guard_left2 = new Sprite(40, 1, 1, SpriteSheet.cabbage_guard_sheet);
	public static Sprite cabbage_guard_right1 = new Sprite(40, 2, 0, SpriteSheet.cabbage_guard_sheet);
	public static Sprite cabbage_guard_right2 = new Sprite(40, 2, 1, SpriteSheet.cabbage_guard_sheet);
	public static Sprite cabbage_guard_attack_left1 = new Sprite(40, 3, 0, SpriteSheet.cabbage_guard_sheet);
	public static Sprite cabbage_guard_attack_left2 = new Sprite(40, 3, 1, SpriteSheet.cabbage_guard_sheet);
	public static Sprite cabbage_guard_attack_right1 = new Sprite(40, 4, 0, SpriteSheet.cabbage_guard_sheet);
	public static Sprite cabbage_guard_attack_right2 = new Sprite(40, 4, 1, SpriteSheet.cabbage_guard_sheet);

	public static Sprite cabbage_male1 = new Sprite(32, 0, 0, SpriteSheet.cabbage_male_sheet);
	public static Sprite cabbage_male2 = new Sprite(32, 0, 1, SpriteSheet.cabbage_male_sheet);
	public static Sprite cabbage_female1 = new Sprite(32, 0, 0, SpriteSheet.cabbage_female_sheet);
	public static Sprite cabbage_female2 = new Sprite(32, 0, 1, SpriteSheet.cabbage_female_sheet);
	public static Sprite cabbage_kid1 = new Sprite(17, 0, 0, SpriteSheet.cabbage_kid_sheet);
	public static Sprite cabbage_kid2 = new Sprite(17, 0, 1, SpriteSheet.cabbage_kid_sheet);
	public static Sprite cabbage_kid_kite_still1 = new Sprite(27, 0, 0, SpriteSheet.cabbage_kid_kite_sheet);
	public static Sprite cabbage_kid_kite_still2 = new Sprite(27, 0, 1, SpriteSheet.cabbage_kid_kite_sheet);
	public static Sprite cabbage_kid_kite_left1 = new Sprite(27, 1, 0, SpriteSheet.cabbage_kid_kite_sheet);
	public static Sprite cabbage_kid_kite_left2 = new Sprite(27, 1, 1, SpriteSheet.cabbage_kid_kite_sheet);
	public static Sprite cabbage_kid_kite_right1 = new Sprite(27, 2, 0, SpriteSheet.cabbage_kid_kite_sheet);
	public static Sprite cabbage_kid_kite_right2 = new Sprite(27, 2, 1, SpriteSheet.cabbage_kid_kite_sheet);
	public static Sprite cabbage_king1 = new Sprite(40, 0, 0, SpriteSheet.cabbage_king_sheet);
	public static Sprite cabbage_king2 = new Sprite(40, 0, 1, SpriteSheet.cabbage_king_sheet);
	public static Sprite teen_cabbage1 = new Sprite(30, 0, 0, SpriteSheet.teen_cabbage);
	public static Sprite teen_cabbage2 = new Sprite(30, 0, 1, SpriteSheet.teen_cabbage);
	public static Sprite crying_cabbage_kid1 = new Sprite(17, 0, 0, SpriteSheet.crying_kid_cabbage);
	public static Sprite crying_cabbage_kid2 = new Sprite(17, 0, 1, SpriteSheet.crying_kid_cabbage);
	public static Sprite crying_cabbage_kid_lollipop1 = new Sprite(17, 0, 2, SpriteSheet.crying_kid_cabbage);
	public static Sprite crying_cabbage_kid_lollipop2 = new Sprite(17, 0, 3, SpriteSheet.crying_kid_cabbage);
	public static Sprite cabbage_chef1 = new Sprite(30, 40, 0, 0, SpriteSheet.cabbage_chef_sheet);
	public static Sprite cabbage_chef2 = new Sprite(30, 40, 0, 40, SpriteSheet.cabbage_chef_sheet);
	public static Sprite crazy_kid_still1 = new Sprite(20, 0, 0, SpriteSheet.crazy_kids_sheet);
	public static Sprite crazy_kid_still2 = new Sprite(20, 0, 1, SpriteSheet.crazy_kids_sheet);
	public static Sprite crazy_kid_left1 = new Sprite(20, 1, 0, SpriteSheet.crazy_kids_sheet);
	public static Sprite crazy_kid_left2 = new Sprite(20, 1, 1, SpriteSheet.crazy_kids_sheet);
	public static Sprite crazy_kid_right1 = new Sprite(20, 2, 0, SpriteSheet.crazy_kids_sheet);
	public static Sprite crazy_kid_right2 = new Sprite(20, 2, 1, SpriteSheet.crazy_kids_sheet);
	public static Sprite cabbageBanker1 = new Sprite(30, 0, 0, SpriteSheet.cabbageBanker);
	public static Sprite cabbageBanker2 = new Sprite(30, 0, 1, SpriteSheet.cabbageBanker);
	public static Sprite highCabbage1 = new Sprite(40, 0, 0, SpriteSheet.high_cabbage_sheet);
	public static Sprite highCabbage2 = new Sprite(40, 0, 1, SpriteSheet.high_cabbage_sheet);
	public static Sprite sniperCabbage1 = new Sprite(40, 0, 0, SpriteSheet.cabbage_sniper_sheet);
	public static Sprite sniperCabbage2 = new Sprite(40, 0, 1, SpriteSheet.cabbage_sniper_sheet);

	//cabbage queen
	public static Sprite cabbage_queen_concerned = new Sprite(30, 0, 0, SpriteSheet.cabbage_queen_sheet);
	public static Sprite cabbage_queen = new Sprite(30, 0, 1, SpriteSheet.cabbage_queen_sheet);
	public static Sprite cabbage_queen_crying1 = new Sprite(30, 0, 2, SpriteSheet.cabbage_queen_sheet);
	public static Sprite cabbage_queen_crying2 = new Sprite(30, 0, 3, SpriteSheet.cabbage_queen_sheet);

	//cabbage nerd
	public static Sprite cabbage_nerd1 = new Sprite(30, 0, 0, SpriteSheet.cabbage_nerd_sheet);
	public static Sprite cabbage_nerd2 = new Sprite(30, 0, 1, SpriteSheet.cabbage_nerd_sheet);
	public static Sprite cabbage_nerd3 = new Sprite(30, 0, 2, SpriteSheet.cabbage_nerd_sheet);
	public static Sprite cabbage_nerd4 = new Sprite(30, 0, 3, SpriteSheet.cabbage_nerd_sheet);
	public static Sprite cabbage_nerd5 = new Sprite(30, 0, 4, SpriteSheet.cabbage_nerd_sheet);
	public static Sprite cabbage_nerd6 = new Sprite(30, 0, 5, SpriteSheet.cabbage_nerd_sheet);

	//crazy woman cabbage
	public static Sprite crazy_woman_still1 = new Sprite(30, 0, 0, SpriteSheet.crazy_woman_sheet);
	public static Sprite crazy_woman_still2 = new Sprite(30, 0, 1, SpriteSheet.crazy_woman_sheet);
	public static Sprite crazy_woman_left1 = new Sprite(30, 1, 0, SpriteSheet.crazy_woman_sheet);
	public static Sprite crazy_woman_left2 = new Sprite(30, 1, 1, SpriteSheet.crazy_woman_sheet);
	public static Sprite crazy_woman_right1 = new Sprite(30, 2, 0, SpriteSheet.crazy_woman_sheet);
	public static Sprite crazy_woman_right2 = new Sprite(30, 2, 1, SpriteSheet.crazy_woman_sheet);

	//crazy male cabbage
	public static Sprite crazy_male_still1 = new Sprite(30, 0, 0, SpriteSheet.crazy_male_sheet);
	public static Sprite crazy_male_still2 = new Sprite(30, 0, 1, SpriteSheet.crazy_male_sheet);
	public static Sprite crazy_male_left1 = new Sprite(30, 1, 0, SpriteSheet.crazy_male_sheet);
	public static Sprite crazy_male_left2 = new Sprite(30, 1, 1, SpriteSheet.crazy_male_sheet);
	public static Sprite crazy_male_right1 = new Sprite(30, 2, 0, SpriteSheet.crazy_male_sheet);
	public static Sprite crazy_male_right2 = new Sprite(30, 2, 1, SpriteSheet.crazy_male_sheet);

	//potato rebel sprites
	public static Sprite potato_rebel_still1 = new Sprite(35, 0, 0, SpriteSheet.potato_rebel_sheet);
	public static Sprite potato_rebel_still2 = new Sprite(35, 0, 1, SpriteSheet.potato_rebel_sheet);
	public static Sprite potato_rebel_left1 = new Sprite(35, 1, 0, SpriteSheet.potato_rebel_sheet);
	public static Sprite potato_rebel_left2 = new Sprite(35, 1, 1, SpriteSheet.potato_rebel_sheet);
	public static Sprite potato_rebel_right1 = new Sprite(35, 2, 0, SpriteSheet.potato_rebel_sheet);
	public static Sprite potato_rebel_right2 = new Sprite(35, 2, 1, SpriteSheet.potato_rebel_sheet);
	public static Sprite potato_rebel_attack_left1 = new Sprite(35, 3, 0, SpriteSheet.potato_rebel_sheet);
	public static Sprite potato_rebel_attack_left2 = new Sprite(35, 3, 1, SpriteSheet.potato_rebel_sheet);
	public static Sprite potato_rebel_attack_right1 = new Sprite(35, 4, 0, SpriteSheet.potato_rebel_sheet);
	public static Sprite potato_rebel_attack_right2 = new Sprite(35, 4, 1, SpriteSheet.potato_rebel_sheet);

	//red rebel sprites
	public static Sprite reb_rebel1 = new Sprite(30, 0, 0, SpriteSheet.red_rebel_sheet);
	public static Sprite reb_rebel2 = new Sprite(30, 0, 1, SpriteSheet.red_rebel_sheet);
	public static Sprite reb_rebel_shooting = new Sprite(30, 0, 2, SpriteSheet.red_rebel_sheet);

	//the wall blobs
	public static Sprite blob_right1 = new Sprite(30, 0, 0, SpriteSheet.blob_sheet);
	public static Sprite blob_right2 = new Sprite(30, 0, 1, SpriteSheet.blob_sheet);
	public static Sprite blob_left1 = new Sprite(30, 1, 0, SpriteSheet.blob_sheet);
	public static Sprite blob_left2 = new Sprite(30, 1, 1, SpriteSheet.blob_sheet);
	public static Sprite blob_down1 = new Sprite(30, 2, 0, SpriteSheet.blob_sheet);
	public static Sprite blob_down2 = new Sprite(30, 2, 1, SpriteSheet.blob_sheet);

	//sea slug sprites
	public static Sprite seaSlug_left1_blue = new Sprite(30, 0, 0, SpriteSheet.sea_slug_sheet);
	public static Sprite seaSlug_left2_blue = new Sprite(30, 0, 1, SpriteSheet.sea_slug_sheet);
	public static Sprite seaSlug_right1_blue = new Sprite(30, 1, 0, SpriteSheet.sea_slug_sheet);
	public static Sprite seaSlug_right2_blue = new Sprite(30, 1, 1, SpriteSheet.sea_slug_sheet);

	public static Sprite seaSlug_left1_red = new Sprite(30, 2, 0, SpriteSheet.sea_slug_sheet);
	public static Sprite seaSlug_left2_red = new Sprite(30, 2, 1, SpriteSheet.sea_slug_sheet);
	public static Sprite seaSlug_right1_red = new Sprite(30, 3, 0, SpriteSheet.sea_slug_sheet);
	public static Sprite seaSlug_right2_red = new Sprite(30, 3, 1, SpriteSheet.sea_slug_sheet);

	//spider sprites
	public static Sprite spider1 = new Sprite(21, 0, 0, SpriteSheet.spider_sheet);
	public static Sprite spider2 = new Sprite(21, 0, 1, SpriteSheet.spider_sheet);

	//human sprites
	public static Sprite human_left1 = new Sprite(40, 0, 0, SpriteSheet.human_sheet);
	public static Sprite human_left2 = new Sprite(40, 0, 1, SpriteSheet.human_sheet);
	public static Sprite human_right1 = new Sprite(40, 1, 0, SpriteSheet.human_sheet);
	public static Sprite human_right2 = new Sprite(40, 1, 1, SpriteSheet.human_sheet);

	//caged human sprites
	public static Sprite human1 = new Sprite(34, 0, 0, SpriteSheet.caged_human_sheet);
	public static Sprite human2 = new Sprite(34, 0, 1, SpriteSheet.caged_human_sheet);

	//evil potato boss sprites
	public static Sprite evil_potato1 = new Sprite(45, 0, 0, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato2 = new Sprite(45, 0, 1, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato_left1 = new Sprite(45, 1, 0, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato_left2 = new Sprite(45, 1, 1, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato_right1 = new Sprite(45, 2, 0, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato_right2 = new Sprite(45, 2, 1, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato_jump_right = new Sprite(45, 3, 0, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato_fall_right = new Sprite(45, 3, 1, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato_jump_left = new Sprite(45, 4, 0, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato_fall_left = new Sprite(45, 4, 0, SpriteSheet.evil_potato_sheet);

	public static Sprite evil_potato_stop_charge = new Sprite(45, 0, 2, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato_stop_shoot_left = new Sprite(45, 1, 2, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato_stop_shoot_right = new Sprite(45, 2, 2, SpriteSheet.evil_potato_sheet);

	public static Sprite evil_potato1_charge = new Sprite(45, 0, 4, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato2_charge = new Sprite(45, 0, 5, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato_left1_charge = new Sprite(45, 1, 4, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato_left2_charge = new Sprite(45, 1, 5, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato_right1_charge = new Sprite(45, 2, 4, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato_right2_charge = new Sprite(45, 2, 5, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato_death1 = new Sprite(45, 0, 6, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato_death2 = new Sprite(45, 0, 7, SpriteSheet.evil_potato_sheet);
	public static Sprite evil_potato_death3 = new Sprite(45, 1, 6, SpriteSheet.evil_potato_sheet);

	//kevs kids ones
	public static Sprite kid1_still1 = new Sprite(15, 0, 0, SpriteSheet.kids_sheet);
	public static Sprite kid1_still2 = new Sprite(15, 0, 1, SpriteSheet.kids_sheet);
	public static Sprite kid2_still1 = new Sprite(15, 1, 0, SpriteSheet.kids_sheet);
	public static Sprite kid2_still2 = new Sprite(15, 1, 1, SpriteSheet.kids_sheet);
	public static Sprite kid1_left1 = new Sprite(15, 2, 0, SpriteSheet.kids_sheet);
	public static Sprite kid1_left2 = new Sprite(15, 2, 1, SpriteSheet.kids_sheet);
	public static Sprite kid2_left1 = new Sprite(15, 3, 0, SpriteSheet.kids_sheet);
	public static Sprite kid2_left2 = new Sprite(15, 3, 1, SpriteSheet.kids_sheet);

	//mini egg/ mini potato sprites
	public static Sprite bomb1 = new Sprite(15, 0, 0, SpriteSheet.bomb_sheet);
	public static Sprite bomb2 = new Sprite(15, 1, 0, SpriteSheet.bomb_sheet);
	public static Sprite bomb3 = new Sprite(15, 2, 0, SpriteSheet.bomb_sheet);
	public static Sprite bomb4 = new Sprite(15, 3, 0, SpriteSheet.bomb_sheet);
	public static Sprite bomb5 = new Sprite(15, 4, 0, SpriteSheet.bomb_sheet);

	//projectiles here
	public static Sprite carrot_projectile = new Sprite(16, 0, 0, SpriteSheet.projectiles_sheet);
	public static Sprite mash_ball = new Sprite(16, 1, 0, SpriteSheet.projectiles_sheet);
	public static Sprite goo_ball = new Sprite(16, 2, 0, SpriteSheet.projectiles_sheet);
	public static Sprite potoato_peice = new Sprite(16, 3, 0, SpriteSheet.projectiles_sheet);
	public static Sprite rotten_goo_ball = new Sprite(16, 4, 0, SpriteSheet.projectiles_sheet);
	public static Sprite pee_pellet = new Sprite(16, 5, 0, SpriteSheet.projectiles_sheet);
	public static Sprite red_lazer = new Sprite(16, 0, 1, SpriteSheet.projectiles_sheet);
	public static Sprite lazer_bullet = new Sprite(16, 1, 1, SpriteSheet.projectiles_sheet);

	//interface sprites here
	public static Sprite heart = new Sprite(10, 0, 0, SpriteSheet.interface_sheet);
	public static Sprite drowningGauge = new Sprite(10, 1, 0, SpriteSheet.interface_sheet);
	public static Sprite deathDrowningGauge = new Sprite(10, 2, 0, SpriteSheet.interface_sheet);
	public static Sprite carrot_icon = new Sprite(15, 15, 0, 10, SpriteSheet.interface_sheet);
	public static Sprite potato_gun_icon = new Sprite(15, 15, 15, 10, SpriteSheet.interface_sheet);
	public static Sprite shotgun_icon = new Sprite(15, 15, 15, 25, SpriteSheet.interface_sheet);
	public static Sprite green_box = new Sprite(15, 15, 0, 25, SpriteSheet.interface_sheet);
	public static Sprite armor_heart = new Sprite(10, 3, 0, SpriteSheet.interface_sheet);
	public static Sprite armor1 = new Sprite(30, 30, 40, 0, SpriteSheet.interface_sheet);
	public static Sprite armor2 = new Sprite(30, 30, 70, 0, SpriteSheet.interface_sheet);

	//collectables here
	public static Sprite coin = new Sprite(10, 0, 0, SpriteSheet.collectables_sheet);
	public static Sprite goldCoin = new Sprite(10, 1, 0, SpriteSheet.collectables_sheet);
	public static Sprite mushroomKey = new Sprite(14, 14, 0, 10, SpriteSheet.collectables_sheet);
	public static Sprite metalBossKey = new Sprite(14, 14, 0, 24, SpriteSheet.collectables_sheet);
	public static Sprite cake = new Sprite(22, 12, 20, 0, SpriteSheet.collectables_sheet);

	//extra sprites here
	public static Sprite text_box = new Sprite(275, 35, 0, 82, SpriteSheet.extra_sheet);
	public static Sprite checkpoint_not = new Sprite(40, 0, 0, SpriteSheet.extra_sheet);
	public static Sprite checkpoint_yes = new Sprite(40, 1, 0, SpriteSheet.extra_sheet);
	public static Sprite wooden_door = new Sprite(16, 32, 0, 40, SpriteSheet.extra_sheet);
	public static Sprite wooden_door_shut = new Sprite(3, 32, 17, 40, SpriteSheet.extra_sheet);
	public static Sprite iron_door = new Sprite(15, 32, 117, 16, SpriteSheet.extra_sheet);
	public static Sprite iron_door_shut = new Sprite(3, 32, 132, 16, SpriteSheet.extra_sheet);
	public static Sprite iron_cage = new Sprite(5, 32, 135, 16, SpriteSheet.extra_sheet);
	public static Sprite bar_sign = new Sprite(48, 16, 80, 0, SpriteSheet.extra_sheet);
	public static Sprite red_bed = new Sprite(41, 23, 20, 40, SpriteSheet.extra_sheet);
	public static Sprite wife_portrait = new Sprite(37, 37, 80, 16, SpriteSheet.extra_sheet);
	public static Sprite wardrobe = new Sprite(20, 35, 61, 40, SpriteSheet.extra_sheet);
	public static Sprite tv = new Sprite(23, 25, 81, 53, SpriteSheet.extra_sheet);
	public static Sprite drawer = new Sprite(31, 19, 20, 63, SpriteSheet.extra_sheet);

	public static Sprite fridge = new Sprite(26, 47, 140, 6, SpriteSheet.extra_sheet);
	public static Sprite cooking_stove = new Sprite(45, 24, 148, 53, SpriteSheet.extra_sheet);
	public static Sprite cooking_desk = new Sprite(44, 24, 104, 54, SpriteSheet.extra_sheet);

	public static Sprite larry_photo = new Sprite(43, 38, 167, 14, SpriteSheet.extra_sheet);

	public static Sprite health_bar = new Sprite(52, 6, 128, 0, SpriteSheet.extra_sheet);//note theres 50 health strips for this bar available
	public static Sprite health_strip = new Sprite(1, 4, 180, 0, SpriteSheet.extra_sheet);

	//kevs house ones
	public static Sprite place_sign = new Sprite(47, 7, 109, 117, SpriteSheet.extra_sheet);
	public static Sprite family_photo = new Sprite(52, 48, 0, 117, SpriteSheet.extra_sheet);
	public static Sprite light = new Sprite(14, 28, 52, 117, SpriteSheet.extra_sheet);
	public static Sprite sofa = new Sprite(20, 24, 66, 117, SpriteSheet.extra_sheet);
	public static Sprite cooking_tv = new Sprite(23, 25, 86, 117, SpriteSheet.extra_sheet);
	public static Sprite double_bed_lower = new Sprite(61, 22, 52, 176, SpriteSheet.extra_sheet);
	public static Sprite double_bed_stalk = new Sprite(2, 18, 111, 158, SpriteSheet.extra_sheet);
	public static Sprite double_bed_top = new Sprite(61, 13, 52, 145, SpriteSheet.extra_sheet);
	public static Sprite double_bed_ladder = new Sprite(7, 53, 113, 145, SpriteSheet.extra_sheet);
	public static Sprite red_teddy = new Sprite(9, 13, 109, 124, SpriteSheet.extra_sheet);
	public static Sprite green_thing = new Sprite(9, 8, 118, 124, SpriteSheet.extra_sheet);
	public static Sprite kids_photo = new Sprite(48, 35, 0, 165, SpriteSheet.extra_sheet);
	public static Sprite target = new Sprite(26, 32, 0, 200, SpriteSheet.extra_sheet);
	public static Sprite master_bed = new Sprite(66, 27, 127, 124, SpriteSheet.extra_sheet);
	public static Sprite double_drawer = new Sprite(41, 30, 26, 200, SpriteSheet.extra_sheet);
	public static Sprite honeymoon_photo = new Sprite(52, 43, 67, 198, SpriteSheet.extra_sheet);

	//village ones
	public static Sprite village_door_closed = new Sprite(7, 48, 120, 151, SpriteSheet.extra_sheet);
	public static Sprite village_door_open = new Sprite(32, 48, 128, 151, SpriteSheet.extra_sheet);
	public static Sprite cabbage_sign = new Sprite(109, 92, 0, 242, SpriteSheet.extra_sheet);
	public static Sprite tree = new Sprite(77, 110, 109, 241, SpriteSheet.extra_sheet);
	public static Sprite tree2 = new Sprite(76, 110, 186, 241, SpriteSheet.extra_sheet);
	public static Sprite bush = new Sprite(57, 19, 119, 199, SpriteSheet.extra_sheet);
	public static Sprite water_fountain = new Sprite(228, 96, 0, 351, SpriteSheet.extra_sheet);
	public static Sprite swimming_sign = new Sprite(89, 47, 193, 117, SpriteSheet.extra_sheet);
	public static Sprite shop_text = new Sprite(23, 7, 119, 218, SpriteSheet.extra_sheet);
	public static Sprite sewer_sign = new Sprite(70, 44, 193, 164, SpriteSheet.extra_sheet);
	public static Sprite hq_sign = new Sprite(100, 24, 285, 0, SpriteSheet.extra_sheet);
	public static Sprite king_photo = new Sprite(75, 63, 210, 0, SpriteSheet.extra_sheet);
	public static Sprite torch = new Sprite(18, 38, 285, 24, SpriteSheet.extra_sheet);
	public static Sprite library_sign = new Sprite(40, 7, 303, 24, SpriteSheet.extra_sheet);
	public static Sprite table_set = new Sprite(106, 38, 345, 24, SpriteSheet.extra_sheet);
	public static Sprite bookcase = new Sprite(42, 61, 303, 31, SpriteSheet.extra_sheet);
	public static Sprite kitchen_unit = new Sprite(218, 46, 282, 92, SpriteSheet.extra_sheet);
	public static Sprite bedroom_unit = new Sprite(199, 42, 263, 187, SpriteSheet.extra_sheet);
	public static Sprite bed_transparent_box = new Sprite(66, 27, 355, 150, SpriteSheet.extra_sheet);//YOU LAZY FOOL :[
	public static Sprite queen_photo = new Sprite(73, 49, 282, 138, SpriteSheet.extra_sheet);
	public static Sprite lobby_sign = new Sprite(27, 8, 345, 62, SpriteSheet.extra_sheet);
	public static Sprite guard_photo = new Sprite(77, 55, 262, 230, SpriteSheet.extra_sheet);
	public static Sprite king_statue = new Sprite(78, 109, 262, 285, SpriteSheet.extra_sheet);
	public static Sprite burning_cabbage1 = new Sprite(107, 116, 0, 447, SpriteSheet.extra_sheet);
	public static Sprite burning_cabbage2 = new Sprite(107, 116, 108, 447, SpriteSheet.extra_sheet);
	public static Sprite bank_sign = new Sprite(82, 7, 373, 63, SpriteSheet.extra_sheet);
	public static Sprite chest_closed = new Sprite(31, 18, 346, 234, SpriteSheet.extra_sheet);
	public static Sprite chest_opened = new Sprite(31, 25, 379, 231, SpriteSheet.extra_sheet);
	public static Sprite rocks = new Sprite(41, 29, 437, 150, SpriteSheet.extra_sheet);
	public static Sprite tools = new Sprite(10, 29, 424, 150, SpriteSheet.extra_sheet);
	public static Sprite sniper = new Sprite(42, 8, 411, 231, SpriteSheet.extra_sheet);

	//forest ones
	public static Sprite forest_sign = new Sprite(109, 93, 387, 373, SpriteSheet.extra_sheet);
	public static Sprite old_tree1 = new Sprite(79, 113, 340, 258, SpriteSheet.extra_sheet);
	public static Sprite old_tree2 = new Sprite(81, 114, 419, 254, SpriteSheet.extra_sheet);
	public static Sprite forest_bush = new Sprite(42, 27, 342, 373, SpriteSheet.extra_sheet);
	public static Sprite tent = new Sprite(98, 45, 230, 395, SpriteSheet.extra_sheet);
	public static Sprite camp_fire1 = new Sprite(107, 53, 230, 441, SpriteSheet.extra_sheet);
	public static Sprite camp_fire2 = new Sprite(107, 53, 230, 496, SpriteSheet.extra_sheet);
	public static Sprite skeli = new Sprite(23, 28, 330, 401, SpriteSheet.extra_sheet);
	public static Sprite bowl = new Sprite(19, 9, 355, 401, SpriteSheet.extra_sheet);
	public static Sprite cage1 = new Sprite(201, 81, 9, 566, SpriteSheet.extra_sheet);
	public static Sprite cage2 = new Sprite(201, 81, 9, 651, SpriteSheet.extra_sheet);
	public static Sprite cageRelease1 = new Sprite(215, 81, 226, 566, SpriteSheet.extra_sheet);
	public static Sprite cageRelease2 = new Sprite(215, 81, 226, 651, SpriteSheet.extra_sheet);

	//menu items sprites
	public static Sprite potato_gun = new Sprite(30, 0, 0, SpriteSheet.weapons_sheet);
	public static Sprite shotgun = new Sprite(42, 23, 0, 30, SpriteSheet.weapons_sheet);

	//particle sprites
	public static Sprite carrot_particle = new Sprite(2, 0xffFF550C);
	public static Sprite goo_ball_particle = new Sprite(2, 0xffABE076);
	public static Sprite mash_ball_particle = new Sprite(2, 0xffD8E2B1);
	public static Sprite shotgun_particle = new Sprite(2, 0xff1EA800);
	public static Sprite Potato_particle = new Sprite(2, 0xff91581F);
	public static Sprite rotton_goo_ball_particle = new Sprite(2, 0xff55BE00);
	public static Sprite lazer_bullet_particle = new Sprite(3, 0xff383838);
	public static Sprite lazer_bullet_hit_particle = new Sprite(3, 0xffFF0000);
	public static Sprite bomb_particle = new Sprite(2, 0xffF5B218);

	//main constructor for a sprite from spritesheet
	public Sprite(int size, int xTile, int yTile, SpriteSheet sheet) {
		this.size = size;
		this.sheet = sheet;

		//find the pixel locations
		xPix = xTile * size;
		yPix = yTile * size;

		pixels = new int[size * size];

		//get the sprite from the sheet
		extractSprite();
	}

	//for spritesheets with sprites of many diff sizes
	public Sprite(int width, int height, int xPix, int yPix, SpriteSheet sheet) {
		this.width = width;
		this.height = height;
		this.sheet = sheet;
		this.xPix = xPix;
		this.yPix = yPix;

		accurateSprite = true;

		pixels = new int[width * height];

		extractAccurateSprite();

	}

	//sets up a basic tile with one color
	public Sprite(int size, int color) {
		this.size = size;
		pixels = new int[size * size];
		setColor(color);
	}

	private void setColor(int color) {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++)
				pixels[x + y * size] = color;
		}
	}

	private void extractSprite() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				pixels[x + y * size] = sheet.pixels[(x + xPix) + (y + yPix) * sheet.getWidth()];
			}
		}
	}

	private void extractAccurateSprite() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + xPix) + (y + yPix) * sheet.getWidth()];
			}
		}
	}

}
