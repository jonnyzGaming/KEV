package jonny.main.levels;

import java.awt.Color;

import jonny.main.entity.Objects.Background_Object;
import jonny.main.entity.Objects.BossCage;
import jonny.main.entity.Objects.Door;
import jonny.main.entity.Objects.Solid_Object;
import jonny.main.entity.Objects.movingPlatform;
import jonny.main.entity.collectables.Checkpoint;
import jonny.main.entity.collectables.Chest;
import jonny.main.entity.collectables.Coin;
import jonny.main.entity.collectables.Key;
import jonny.main.entity.mob.Player;
import jonny.main.entity.mob.characters.BarryBeetroot;
import jonny.main.entity.mob.characters.BillyBrocoli;
import jonny.main.entity.mob.characters.BlackEyeDad;
import jonny.main.entity.mob.characters.Cabbage;
import jonny.main.entity.mob.characters.CabbageBanker;
import jonny.main.entity.mob.characters.CabbageFemale;
import jonny.main.entity.mob.characters.CabbageKidKite;
import jonny.main.entity.mob.characters.CabbageKing;
import jonny.main.entity.mob.characters.CabbageMale;
import jonny.main.entity.mob.characters.CabbageNerd;
import jonny.main.entity.mob.characters.CabbageQueen;
import jonny.main.entity.mob.characters.CabbageSniper;
import jonny.main.entity.mob.characters.Caveman;
import jonny.main.entity.mob.characters.Cavewoman;
import jonny.main.entity.mob.characters.Character;
import jonny.main.entity.mob.characters.CrazyCabbage;
import jonny.main.entity.mob.characters.CryingCabbagekid;
import jonny.main.entity.mob.characters.HighCabbage;
import jonny.main.entity.mob.characters.HumanCaged;
import jonny.main.entity.mob.characters.LarryLeak;
import jonny.main.entity.mob.characters.Mr_mushroom;
import jonny.main.entity.mob.characters.PeterPickle;
import jonny.main.entity.mob.characters.Roy;
import jonny.main.entity.mob.characters.Sprout;
import jonny.main.entity.mob.characters.TeenCabbage;
import jonny.main.entity.mob.characters.cabbageChef;
import jonny.main.entity.mob.characters.cabbageKid;
import jonny.main.entity.mob.characters.kevsWife;
import jonny.main.entity.mob.enemys.BlackEyePee;
import jonny.main.entity.mob.enemys.Blob;
import jonny.main.entity.mob.enemys.CabbageGuard;
import jonny.main.entity.mob.enemys.CaveCrawler;
import jonny.main.entity.mob.enemys.EvilPotato;
import jonny.main.entity.mob.enemys.Human;
import jonny.main.entity.mob.enemys.MashBoss;
import jonny.main.entity.mob.enemys.Mr_mash;
import jonny.main.entity.mob.enemys.PotatoRebel;
import jonny.main.entity.mob.enemys.RedRebel;
import jonny.main.entity.mob.enemys.SewerBoss;
import jonny.main.entity.mob.enemys.Slime;
import jonny.main.entity.mob.enemys.Spider;
import jonny.main.entity.mob.enemys.seaSlug;
import jonny.main.graphics.Sprite;

/*
 * The main level in the game
 */
public class BasicLevel extends Level {

	//basic level properties

	//coins in the game tally
	public static int totalCoins = 0;

	public static int cavemanReward = 15;
	public static int larryReward = 20;
	public static int sproutReward = 5;
	public static int barryReward = 30;
	public static int kingReward = 30;
	public static int chefReward = 20;
	public static int teenCabbageReward = 5;

	//animation properties (text box ones are in player)
	public static String text;
	public static int xPos, yPos;
	public static int r, g, b, a;
	public static Color col;

	public BasicLevel(String path, Player player) {
		super(path, player);

		loadLevelAssets();

	}

	/*
	 * Loads in all entities and objects used in the level and locates them
	 * Sets the players starting position within the level and any opening level text.
	 */
	public void loadLevelAssets() {
		clear();

		//load initial level animation
		BasicLevel.text = "Level 1: 'A bad day'";
		BasicLevel.xPos = 280;
		BasicLevel.yPos = 160;
		BasicLevel.r = 30;
		BasicLevel.g = 30;
		BasicLevel.b = 30;
		BasicLevel.a = 1;

		//set the spawn point the player for the start of the level
		player.setLocation(250, 2060);
		player.setSpawnPoint(250, 2060);

		//player.setLocation(2000, 2100);
		//player.setSpawnPoint(2000, 2100);
		//player.setLocation(2500, 1500);
		//player.setSpawnPoint(2500, 1500);
		//player.setLocation(2870, 2420);
		//player.setSpawnPoint(2870, 2420);
		//player.setLocation(5000, 3212);
		//player.setSpawnPoint(5000, 3212);
		//player.setLocation(9300, 1450);
		//player.setSpawnPoint(9300, 1450);
		//player.setLocation(12860, 1420);
		//player.setSpawnPoint(12860, 1420);
		//player.setLocation(16046, 1606);
		//player.setSpawnPoint(16046, 1606);

		//load checkpoints
		Checkpoint c1 = new Checkpoint(2047, 2093, false);
		c1.init(this);
		add(c1);

		Checkpoint c2 = new Checkpoint(2678, 2412, false);
		c2.init(this);
		add(c2);

		Checkpoint c3 = new Checkpoint(3836, 3212, false);
		c3.init(this);
		add(c3);

		Checkpoint c4 = new Checkpoint(2324, 1341, false);
		c4.init(this);
		add(c4);

		Checkpoint c5 = new Checkpoint(8072, 1677, false);
		c5.init(this);
		add(c5);

		Checkpoint c6 = new Checkpoint(8847, 2124, false);
		c6.init(this);
		add(c6);

		Checkpoint c7 = new Checkpoint(8101, 2444, false);
		c7.init(this);
		add(c7);

		Checkpoint c8 = new Checkpoint(9406, 1677, false);
		c8.init(this);
		add(c8);

		Checkpoint c9 = new Checkpoint(13577, 1405, false);
		c9.init(this);
		add(c9);

		Checkpoint c10 = new Checkpoint(16575, 1612, false);
		c10.init(this);
		add(c10);

		//load coins
		//add rewards to tally
		totalCoins += cavemanReward;
		totalCoins += larryReward;
		totalCoins += sproutReward;
		totalCoins += barryReward;
		totalCoins += chefReward;
		totalCoins += kingReward;
		totalCoins += teenCabbageReward;

		Coin Coin1 = new Coin(855, 2085, 1, Sprite.coin);
		Coin1.init(this);
		add(Coin1);

		Coin Coin2 = new Coin(873, 2085, 1, Sprite.coin);
		Coin2.init(this);
		add(Coin2);

		Coin Coin3 = new Coin(1297, 2392, 1, Sprite.coin); //mr mush den ones
		Coin3.init(this);
		add(Coin3);

		Coin Coin4 = new Coin(1307, 2392, 1, Sprite.coin);
		Coin4.init(this);
		add(Coin4);

		Coin Coin5 = new Coin(1480, 2407, 1, Sprite.coin);
		Coin5.init(this);
		add(Coin5);

		Coin Coin6 = new Coin(1491, 2407, 1, Sprite.coin);
		Coin6.init(this);
		add(Coin6);

		Coin Coin7 = new Coin(1502, 2407, 1, Sprite.coin);
		Coin7.init(this);
		add(Coin7);

		Coin goldenCoin1 = new Coin(1376, 2403, 5, Sprite.goldCoin); //mr mash den end
		goldenCoin1.init(this);
		add(goldenCoin1);

		Coin Coin8 = new Coin(1465, 1882, 1, Sprite.coin); //ones next to mush key
		Coin8.init(this);
		add(Coin8);

		Coin Coin9 = new Coin(1480, 1882, 1, Sprite.coin);
		Coin9.init(this);
		add(Coin9);

		Coin Coin10 = new Coin(1511, 1882, 1, Sprite.coin);
		Coin10.init(this);
		add(Coin10);

		Coin Coin11 = new Coin(1527, 1882, 1, Sprite.coin); //ones next to mush key end
		Coin11.init(this);
		add(Coin11);

		Coin Coin12 = new Coin(2467, 2297, 1, Sprite.coin); //ones in mr mash cave start
		Coin12.init(this);
		add(Coin12);

		Coin Coin13 = new Coin(2477, 2297, 1, Sprite.coin);
		Coin13.init(this);
		add(Coin13);

		Coin Coin14 = new Coin(2487, 2297, 1, Sprite.coin);
		Coin14.init(this);
		add(Coin14);

		//ones at end of mash cave(moving plat bit)
		Coin Coin15 = new Coin(4305, 3335, 5, Sprite.goldCoin);
		Coin15.init(this);
		add(Coin15);

		Coin Coin16 = new Coin(5217, 3211, 1, Sprite.coin);
		Coin16.init(this);
		add(Coin16);

		Coin Coin17 = new Coin(5230, 3211, 1, Sprite.coin);
		Coin17.init(this);
		add(Coin17);

		Coin Coin18 = new Coin(5243, 3211, 1, Sprite.coin);
		Coin18.init(this);
		add(Coin18);
		;

		Coin Coin19 = new Coin(5217, 3223, 1, Sprite.coin);
		Coin19.init(this);
		add(Coin19);

		Coin Coin20 = new Coin(5230, 3223, 1, Sprite.coin);
		Coin20.init(this);
		add(Coin20);

		Coin Coin21 = new Coin(5243, 3223, 1, Sprite.coin);
		Coin21.init(this);
		add(Coin21);

		Coin Coin22 = new Coin(4860, 3117, 1, Sprite.coin);
		Coin22.init(this);
		add(Coin22);

		Coin Coin23 = new Coin(4860, 3104, 1, Sprite.coin);
		Coin23.init(this);
		add(Coin23);

		Coin Coin24 = new Coin(4860, 3094, 1, Sprite.coin);
		Coin24.init(this);
		add(Coin24);

		Coin Coin25 = new Coin(4023, 3271, 1, Sprite.coin);
		Coin25.init(this);
		add(Coin25);

		Coin Coin26 = new Coin(4038, 3271, 1, Sprite.coin);
		Coin26.init(this);
		add(Coin26);

		//top of cave ones
		Coin Coin27 = new Coin(2906, 2743, 1, Sprite.coin);
		Coin27.init(this);
		add(Coin27);

		Coin Coin28 = new Coin(2919, 2743, 1, Sprite.coin);
		Coin28.init(this);
		add(Coin28);

		Coin Coin29 = new Coin(3384, 2615, 1, Sprite.coin);
		Coin29.init(this);
		add(Coin29);

		Coin Coin30 = new Coin(3408, 2615, 1, Sprite.coin);
		Coin30.init(this);
		add(Coin30);

		Coin Coin31 = new Coin(3432, 2615, 1, Sprite.coin);
		Coin31.init(this);
		add(Coin31);

		Coin Coin32 = new Coin(3384, 2600, 1, Sprite.coin);
		Coin32.init(this);
		add(Coin32);

		Coin Coin33 = new Coin(3408, 2600, 1, Sprite.coin);
		Coin33.init(this);
		add(Coin33);

		Coin Coin34 = new Coin(3432, 2600, 1, Sprite.coin);
		Coin34.init(this);
		add(Coin34);

		Coin Coin35 = new Coin(3384, 2585, 1, Sprite.coin);
		Coin35.init(this);
		add(Coin35);

		Coin Coin36 = new Coin(3408, 2585, 1, Sprite.coin);
		Coin36.init(this);
		add(Coin36);

		Coin Coin37 = new Coin(3432, 2585, 1, Sprite.coin);
		Coin37.init(this);
		add(Coin37);

		Coin Coin38 = new Coin(3333, 2914, 1, Sprite.coin);
		Coin38.init(this);
		add(Coin38);

		Coin Coin39 = new Coin(3309, 2922, 1, Sprite.coin);
		Coin39.init(this);
		add(Coin39);

		Coin Coin40 = new Coin(3357, 2922, 1, Sprite.coin);
		Coin40.init(this);
		add(Coin40);

		Coin Coin41 = new Coin(3768, 2854, 1, Sprite.coin);
		Coin41.init(this);
		add(Coin41);

		Coin Coin42 = new Coin(3782, 2854, 1, Sprite.coin);
		Coin42.init(this);
		add(Coin42);

		Coin Coin43 = new Coin(3944, 2712, 1, Sprite.coin);
		Coin43.init(this);
		add(Coin43);

		Coin Coin44 = new Coin(4007, 2712, 1, Sprite.coin);
		Coin44.init(this);
		add(Coin44);

		Coin Coin45 = new Coin(3975, 2728, 5, Sprite.goldCoin);
		Coin45.init(this);
		add(Coin45);

		Coin Coin46 = new Coin(4071, 2808, 1, Sprite.coin);
		Coin46.init(this);
		add(Coin46);

		Coin Coin47 = new Coin(4085, 2808, 1, Sprite.coin);
		Coin47.init(this);
		add(Coin47);

		Coin Coin48 = new Coin(4359, 2727, 1, Sprite.coin);
		Coin48.init(this);
		add(Coin48);

		Coin Coin49 = new Coin(4383, 2727, 1, Sprite.coin);
		Coin49.init(this);
		add(Coin49);

		Coin Coin50 = new Coin(4407, 2727, 1, Sprite.coin);
		Coin50.init(this);
		add(Coin50);

		Coin Coin51 = new Coin(4359, 2712, 1, Sprite.coin);
		Coin51.init(this);
		add(Coin51);

		Coin Coin52 = new Coin(4383, 2712, 1, Sprite.coin);
		Coin52.init(this);
		add(Coin52);

		Coin Coin53 = new Coin(4407, 2712, 1, Sprite.coin);
		Coin53.init(this);
		add(Coin53);

		Coin Coin54 = new Coin(4359, 2697, 1, Sprite.coin);
		Coin54.init(this);
		add(Coin54);

		Coin Coin55 = new Coin(4383, 2697, 1, Sprite.coin);
		Coin55.init(this);
		add(Coin55);

		Coin Coin56 = new Coin(4407, 2697, 1, Sprite.coin);
		Coin56.init(this);
		add(Coin56);

		//end of cave in key room ones
		Coin Coin57 = new Coin(5625, 3016, 1, Sprite.coin);
		Coin57.init(this);
		add(Coin57);

		Coin Coin58 = new Coin(5658, 2984, 1, Sprite.coin);
		Coin58.init(this);
		add(Coin58);

		Coin Coin59 = new Coin(5672, 2984, 1, Sprite.coin);
		Coin59.init(this);
		add(Coin59);

		Coin Coin60 = new Coin(5706, 3016, 1, Sprite.coin);
		Coin60.init(this);
		add(Coin60);

		//in cavewomans house
		Coin Coin61 = new Coin(3117, 3319, 1, Sprite.coin);
		Coin61.init(this);
		add(Coin61);

		Coin Coin63 = new Coin(3089, 3319, 1, Sprite.coin);
		Coin63.init(this);
		add(Coin63);

		Coin Coin64 = new Coin(3103, 3319, 1, Sprite.coin);
		Coin64.init(this);
		add(Coin64);

		//sky route coins
		Coin Coin62 = new Coin(2233, 1306, 1, Sprite.coin);
		Coin62.init(this);
		add(Coin62);

		Coin Coin65 = new Coin(2219, 1306, 1, Sprite.coin);
		Coin65.init(this);
		add(Coin65);

		Coin Coin66 = new Coin(2945, 1399, 1, Sprite.coin);
		Coin66.init(this);
		add(Coin66);

		Coin Coin67 = new Coin(2955, 1399, 1, Sprite.coin);
		Coin67.init(this);
		add(Coin67);

		Coin Coin68 = new Coin(2965, 1399, 1, Sprite.coin);
		Coin68.init(this);
		add(Coin68);

		Coin Coin69 = new Coin(3224, 775, 1, Sprite.coin);
		Coin69.init(this);
		add(Coin69);

		Coin Coin70 = new Coin(3236, 775, 1, Sprite.coin);
		Coin70.init(this);
		add(Coin70);

		Coin Coin71 = new Coin(3248, 775, 1, Sprite.coin);
		Coin71.init(this);
		add(Coin71);

		Coin Coin72 = new Coin(3060, 1256, 1, Sprite.coin);
		Coin72.init(this);
		add(Coin72);

		Coin Coin73 = new Coin(3073, 1256, 1, Sprite.coin);
		Coin73.init(this);
		add(Coin73);

		Coin Coin74 = new Coin(3086, 1256, 1, Sprite.coin);
		Coin74.init(this);
		add(Coin74);

		Coin goldCoin75 = new Coin(3499, 690, 5, Sprite.goldCoin);
		goldCoin75.init(this);
		add(goldCoin75);

		Coin Coin76 = new Coin(3274, 1320, 1, Sprite.coin);
		Coin76.init(this);
		add(Coin76);

		Coin Coin77 = new Coin(3284, 1320, 1, Sprite.coin);
		Coin77.init(this);
		add(Coin77);

		Coin Coin78 = new Coin(3294, 1320, 1, Sprite.coin);
		Coin78.init(this);
		add(Coin78);

		//sky in secret den bit
		Coin Coin79 = new Coin(2441, 1092, 1, Sprite.coin);
		Coin79.init(this);
		add(Coin79);

		Coin Coin80 = new Coin(2465, 1092, 1, Sprite.coin);
		Coin80.init(this);
		add(Coin80);

		Coin Coin81 = new Coin(2489, 1092, 1, Sprite.coin);
		Coin81.init(this);
		add(Coin81);

		Coin Coin82 = new Coin(2441, 1077, 1, Sprite.coin);
		Coin82.init(this);
		add(Coin82);

		Coin Coin83 = new Coin(2465, 1077, 1, Sprite.coin);
		Coin83.init(this);
		add(Coin83);

		Coin Coin84 = new Coin(2489, 1077, 1, Sprite.coin);
		Coin84.init(this);
		add(Coin84);

		Coin Coin85 = new Coin(2441, 1062, 1, Sprite.coin);
		Coin85.init(this);
		add(Coin85);

		Coin Coin86 = new Coin(2465, 1062, 1, Sprite.coin);
		Coin86.init(this);
		add(Coin86);

		Coin Coin87 = new Coin(2489, 1062, 1, Sprite.coin);
		Coin87.init(this);
		add(Coin87);

		//start ones + moving plat bit
		Coin Coin88 = new Coin(2353, 1867, 1, Sprite.coin);
		Coin88.init(this);
		add(Coin88);

		Coin Coin89 = new Coin(2363, 1867, 1, Sprite.coin);
		Coin89.init(this);
		add(Coin89);

		Coin Coin90 = new Coin(2373, 1867, 1, Sprite.coin);
		Coin90.init(this);
		add(Coin90);

		Coin Coin91 = new Coin(1895, 1626, 1, Sprite.coin);
		Coin91.init(this);
		add(Coin91);

		Coin Coin92 = new Coin(1905, 1626, 1, Sprite.coin);
		Coin92.init(this);
		add(Coin92);

		Coin Coin93 = new Coin(1895, 1370, 1, Sprite.coin);
		Coin93.init(this);
		add(Coin93);

		Coin Coin94 = new Coin(1905, 1370, 1, Sprite.coin);
		Coin94.init(this);
		add(Coin94);

		//ones in hidden black eye cave
		Coin Coin95 = new Coin(1395, 983, 1, Sprite.coin);
		Coin95.init(this);
		add(Coin95);

		Coin Coin96 = new Coin(1405, 983, 5, Sprite.goldCoin);
		Coin96.init(this);
		add(Coin96);

		Coin Coin97 = new Coin(1415, 983, 1, Sprite.coin);
		Coin97.init(this);
		add(Coin97);

		//falling from ladder bit ones
		Coin Coin98 = new Coin(1737, 1611, 1, Sprite.coin);
		Coin98.init(this);
		add(Coin98);

		Coin Coin99 = new Coin(1737, 1645, 1, Sprite.coin);
		Coin99.init(this);
		add(Coin99);

		Coin Coin100 = new Coin(1737, 1679, 1, Sprite.coin);
		Coin100.init(this);
		add(Coin100);

		//water route bit
		Coin Coin101 = new Coin(3088, 1818, 1, Sprite.coin);
		Coin101.init(this);
		add(Coin101);

		Coin Coin102 = new Coin(3088, 1833, 1, Sprite.coin);
		Coin102.init(this);
		add(Coin102);

		Coin Coin103 = new Coin(3088, 1848, 1, Sprite.coin);
		Coin103.init(this);
		add(Coin103);

		Coin Coin104 = new Coin(3088, 1863, 1, Sprite.coin);
		Coin104.init(this);
		add(Coin104);

		Coin Coin105 = new Coin(3088, 188, 1, Sprite.coin);
		Coin105.init(this);
		add(Coin105);

		Coin Coin106 = new Coin(3081, 2037, 1, Sprite.coin);
		Coin106.init(this);
		add(Coin106);

		Coin Coin107 = new Coin(3093, 2037, 1, Sprite.coin);
		Coin107.init(this);
		add(Coin107);

		Coin Coin108 = new Coin(2686, 2170, 5, Sprite.goldCoin);
		Coin108.init(this);
		add(Coin108);

		Coin Coin109 = new Coin(3230, 2136, 1, Sprite.coin);
		Coin109.init(this);
		add(Coin109);

		Coin Coin110 = new Coin(3241, 2136, 1, Sprite.coin);
		Coin110.init(this);
		add(Coin110);

		Coin Coin111 = new Coin(3841, 2111, 1, Sprite.coin);
		Coin111.init(this);
		add(Coin111);

		Coin Coin112 = new Coin(3852, 2111, 1, Sprite.coin);
		Coin112.init(this);
		add(Coin112);

		Coin Coin113 = new Coin(3841, 2123, 1, Sprite.coin);
		Coin113.init(this);
		add(Coin113);

		Coin Coin114 = new Coin(3852, 2123, 1, Sprite.coin);
		Coin114.init(this);
		add(Coin114);

		//ones in kevs house
		Coin Coin115 = new Coin(4642, 1731, 1, Sprite.coin);
		Coin115.init(this);
		add(Coin115);

		Coin Coin116 = new Coin(4654, 1731, 1, Sprite.coin);
		Coin116.init(this);
		add(Coin116);

		Coin Coin117 = new Coin(4666, 1731, 1, Sprite.coin);
		Coin117.init(this);
		add(Coin117);

		Coin Coin118 = new Coin(4034, 1726, 1, Sprite.coin);
		Coin118.init(this);
		add(Coin118);

		Coin Coin119 = new Coin(4045, 1726, 1, Sprite.coin);
		Coin119.init(this);
		add(Coin119);

		Coin Coin120 = new Coin(4056, 1726, 1, Sprite.coin);
		Coin120.init(this);
		add(Coin120);

		Coin Coin121 = new Coin(4795, 1544, 1, Sprite.coin);
		Coin121.init(this);
		add(Coin121);

		Coin Coin122 = new Coin(4808, 1544, 1, Sprite.coin);
		Coin122.init(this);
		add(Coin122);

		Coin Coin123 = new Coin(4818, 1544, 1, Sprite.coin);
		Coin123.init(this);
		add(Coin123);

		Coin goldChimney = new Coin(4279, 1400, 5, Sprite.goldCoin);
		goldChimney.init(this);
		add(goldChimney);

		//carrots in the village
		Coin Coin124 = new Coin(6262, 1593, 1, Sprite.coin);
		Coin124.init(this);
		add(Coin124);

		Coin Coin125 = new Coin(6272, 1593, 1, Sprite.coin);
		Coin125.init(this);
		add(Coin125);

		Coin Coin126 = new Coin(6282, 1593, 1, Sprite.coin);
		Coin126.init(this);
		add(Coin126);

		Coin Coin127 = new Coin(6910, 2088, 1, Sprite.coin);
		Coin127.init(this);
		add(Coin127);

		Coin Coin128 = new Coin(6920, 2088, 1, Sprite.coin);
		Coin128.init(this);
		add(Coin128);

		Coin Coin129 = new Coin(6930, 2088, 1, Sprite.coin);
		Coin129.init(this);
		add(Coin129);

		Coin Coin130 = new Coin(6940, 2088, 1, Sprite.coin);
		Coin130.init(this);
		add(Coin130);

		//hidden cave in village
		Coin Coin131 = new Coin(7328, 1955, 1, Sprite.coin);
		Coin131.init(this);
		add(Coin131);

		Coin Coin132 = new Coin(7352, 1955, 1, Sprite.coin);
		Coin132.init(this);
		add(Coin132);

		Coin Coin133 = new Coin(7376, 1955, 1, Sprite.coin);
		Coin133.init(this);
		add(Coin133);

		Coin Coin134 = new Coin(7328, 1970, 1, Sprite.coin);
		Coin134.init(this);
		add(Coin134);

		Coin Coin135 = new Coin(7352, 1970, 1, Sprite.coin);
		Coin135.init(this);
		add(Coin135);

		Coin Coin136 = new Coin(7376, 1970, 1, Sprite.coin);
		Coin136.init(this);
		add(Coin136);

		Coin Coin137 = new Coin(7328, 1985, 1, Sprite.coin);
		Coin137.init(this);
		add(Coin137);

		Coin Coin138 = new Coin(7352, 1985, 1, Sprite.coin);
		Coin138.init(this);
		add(Coin138);

		Coin Coin139 = new Coin(7376, 1985, 1, Sprite.coin);
		Coin139.init(this);
		add(Coin139);

		//sewers carrots
		Coin Coin140 = new Coin(8980, 1880, 1, Sprite.coin);
		Coin140.init(this);
		add(Coin140);

		Coin Coin141 = new Coin(8990, 1880, 1, Sprite.coin);
		Coin141.init(this);
		add(Coin141);

		Coin Coin142 = new Coin(9000, 1880, 1, Sprite.coin);
		Coin142.init(this);
		add(Coin142);

		Coin Coin143 = new Coin(8473, 2200, 1, Sprite.coin);
		Coin143.init(this);
		add(Coin143);

		Coin Coin144 = new Coin(8485, 2200, 1, Sprite.coin);
		Coin144.init(this);
		add(Coin144);

		Coin Coin145 = new Coin(7843, 2088, 1, Sprite.coin);
		Coin145.init(this);
		add(Coin145);

		Coin Coin146 = new Coin(7853, 2088, 1, Sprite.coin);
		Coin146.init(this);
		add(Coin146);

		Coin Coin147 = new Coin(7863, 2088, 1, Sprite.coin);
		Coin147.init(this);
		add(Coin147);

		Coin Coin148 = new Coin(7938, 2040, 1, Sprite.coin);
		Coin148.init(this);
		add(Coin148);

		Coin Coin149 = new Coin(7948, 2040, 1, Sprite.coin);
		Coin149.init(this);
		add(Coin149);

		//hidden bit
		Coin Coin151 = new Coin(9343, 2385, 1, Sprite.coin);
		Coin151.init(this);
		add(Coin151);

		Coin Coin152 = new Coin(9367, 2385, 1, Sprite.coin);
		Coin152.init(this);
		add(Coin152);

		Coin Coin153 = new Coin(9391, 2385, 1, Sprite.coin);
		Coin153.init(this);
		add(Coin153);

		Coin Coin154 = new Coin(9343, 2400, 1, Sprite.coin);
		Coin154.init(this);
		add(Coin154);

		Coin Coin155 = new Coin(9367, 2400, 1, Sprite.coin);
		Coin155.init(this);
		add(Coin155);

		Coin Coin156 = new Coin(9391, 2400, 1, Sprite.coin);
		Coin156.init(this);
		add(Coin156);

		Coin Coin157 = new Coin(9343, 2415, 1, Sprite.coin);
		Coin157.init(this);
		add(Coin157);

		Coin Coin158 = new Coin(9367, 2415, 1, Sprite.coin);
		Coin158.init(this);
		add(Coin158);

		Coin Coin159 = new Coin(9391, 2415, 1, Sprite.coin);
		Coin159.init(this);
		add(Coin159);

		Coin goldenCoin160 = new Coin(8337, 2788, 5, Sprite.goldCoin);
		goldenCoin160.init(this);
		add(goldenCoin160);

		Coin Coin160 = new Coin(8485, 2440, 1, Sprite.coin);
		Coin160.init(this);
		add(Coin160);

		Coin Coin161 = new Coin(8495, 2440, 1, Sprite.coin);
		Coin161.init(this);
		add(Coin161);

		Coin Coin162 = new Coin(8505, 2440, 1, Sprite.coin);
		Coin162.init(this);
		add(Coin162);

		Coin Coin163 = new Coin(9458, 2792, 1, Sprite.coin);
		Coin163.init(this);
		add(Coin163);

		Coin Coin164 = new Coin(9468, 2792, 1, Sprite.coin);
		Coin164.init(this);
		add(Coin164);

		Coin Coin165 = new Coin(9600, 2792, 1, Sprite.coin);
		Coin165.init(this);
		add(Coin165);

		Coin Coin166 = new Coin(9610, 2792, 1, Sprite.coin);
		Coin166.init(this);
		add(Coin166);

		Coin Coin167 = new Coin(9765, 2710, 1, Sprite.coin);
		Coin167.init(this);
		add(Coin167);

		Coin Coin168 = new Coin(9775, 2710, 1, Sprite.coin);
		Coin168.init(this);
		add(Coin168);

		Coin Coin169 = new Coin(9785, 2710, 1, Sprite.coin);
		Coin169.init(this);
		add(Coin169);

		//ones in rest of village
		Coin Coin170 = new Coin(7883, 1529, 1, Sprite.coin);
		Coin170.init(this);
		add(Coin170);

		Coin Coin171 = new Coin(7893, 1529, 1, Sprite.coin);
		Coin171.init(this);
		add(Coin171);

		Coin Coin172 = new Coin(8522, 1687, 1, Sprite.coin);
		Coin172.init(this);
		add(Coin172);

		Coin Coin173 = new Coin(8532, 1687, 1, Sprite.coin);
		Coin173.init(this);
		add(Coin173);

		Coin Coin174 = new Coin(8542, 1687, 1, Sprite.coin);
		Coin174.init(this);
		add(Coin174);

		Coin Coin175 = new Coin(9172, 1438, 1, Sprite.coin);
		Coin175.init(this);
		add(Coin175);

		Coin Coin176 = new Coin(9182, 1438, 1, Sprite.coin);
		Coin176.init(this);
		add(Coin176);

		Coin Coin177 = new Coin(9192, 1438, 1, Sprite.coin);
		Coin177.init(this);
		add(Coin177);

		//library hidden bit
		Coin Coin178 = new Coin(9468, 774, 1, Sprite.coin);
		Coin178.init(this);
		add(Coin178);

		Coin Coin179 = new Coin(9492, 774, 1, Sprite.coin);
		Coin179.init(this);
		add(Coin179);

		Coin Coin180 = new Coin(9516, 774, 1, Sprite.coin);
		Coin180.init(this);
		add(Coin180);

		Coin Coin181 = new Coin(9468, 789, 1, Sprite.coin);
		Coin181.init(this);
		add(Coin181);

		Coin Coin182 = new Coin(9492, 789, 1, Sprite.coin);
		Coin182.init(this);
		add(Coin182);

		Coin Coin183 = new Coin(9516, 789, 1, Sprite.coin);
		Coin183.init(this);
		add(Coin183);

		Coin Coin184 = new Coin(9468, 804, 1, Sprite.coin);
		Coin184.init(this);
		add(Coin184);

		Coin Coin185 = new Coin(9492, 804, 1, Sprite.coin);
		Coin185.init(this);
		add(Coin185);

		Coin Coin186 = new Coin(9516, 804, 1, Sprite.coin);
		Coin186.init(this);
		add(Coin186);

		//extra to right in village
		Coin Coin187 = new Coin(10015, 1529, 1, Sprite.coin);
		Coin187.init(this);
		add(Coin187);

		Coin Coin188 = new Coin(10025, 1529, 1, Sprite.coin);
		Coin188.init(this);
		add(Coin188);

		Coin Coin189 = new Coin(10035, 1529, 1, Sprite.coin);
		Coin189.init(this);
		add(Coin189);

		Coin Coin190 = new Coin(11207, 1930, 1, Sprite.coin);
		Coin190.init(this);
		add(Coin190);

		Coin Coin191 = new Coin(11243, 1930, 1, Sprite.coin);
		Coin191.init(this);
		add(Coin191);

		Coin Coin192 = new Coin(11225, 1912, 1, Sprite.coin);
		Coin192.init(this);
		add(Coin192);

		Coin Coin193 = new Coin(11225, 1948, 1, Sprite.coin);
		Coin193.init(this);
		add(Coin193);

		Coin goldenCoin194 = new Coin(11441, 1415, 5, Sprite.goldCoin);
		goldenCoin194.init(this);
		add(goldenCoin194);
		totalCoins += 5;

		Coin Coin194 = new Coin(11976, 1545, 1, Sprite.coin);
		Coin194.init(this);
		add(Coin194);

		Coin Coin195 = new Coin(12006, 1545, 1, Sprite.coin);
		Coin195.init(this);
		add(Coin195);

		Coin Coin196 = new Coin(12796, 1880, 1, Sprite.coin);
		Coin196.init(this);
		add(Coin196);

		Coin Coin197 = new Coin(12806, 1880, 1, Sprite.coin);
		Coin197.init(this);
		add(Coin197);

		Coin Coin198 = new Coin(12816, 1880, 1, Sprite.coin);
		Coin198.init(this);
		add(Coin198);

		//hidden area above high cabbage
		Coin goldCoinSpike = new Coin(12952, 1185, 5, Sprite.goldCoin);
		goldCoinSpike.init(this);
		add(goldCoinSpike);
		totalCoins += 5;

		Coin Coin199 = new Coin(12834, 1234, 1, Sprite.coin);
		Coin199.init(this);
		add(Coin199);

		Coin Coin200 = new Coin(12811, 1219, 1, Sprite.coin);
		Coin200.init(this);
		add(Coin200);

		Coin Coin201 = new Coin(12788, 1234, 1, Sprite.coin);
		Coin201.init(this);
		add(Coin201);

		//at top of tower 9 block
		Coin Coin202 = new Coin(12980, 993, 1, Sprite.coin);
		Coin202.init(this);
		add(Coin202);

		Coin Coin203 = new Coin(13004, 993, 1, Sprite.coin);
		Coin203.init(this);
		add(Coin203);

		Coin Coin204 = new Coin(13028, 993, 1, Sprite.coin);
		Coin204.init(this);
		add(Coin204);

		Coin Coin205 = new Coin(12980, 1008, 1, Sprite.coin);
		Coin205.init(this);
		add(Coin205);

		Coin Coin206 = new Coin(13004, 1008, 1, Sprite.coin);
		Coin206.init(this);
		add(Coin206);

		Coin Coin207 = new Coin(13028, 1008, 1, Sprite.coin);
		Coin207.init(this);
		add(Coin207);

		Coin Coin208 = new Coin(12980, 1023, 1, Sprite.coin);
		Coin208.init(this);
		add(Coin208);

		Coin Coin209 = new Coin(13004, 1023, 1, Sprite.coin);
		Coin209.init(this);
		add(Coin209);

		Coin Coin210 = new Coin(13028, 1023, 1, Sprite.coin);
		Coin210.init(this);
		add(Coin210);

		//in forest ones
		Coin Coin211 = new Coin(13398, 1415, 1, Sprite.coin);
		Coin211.init(this);
		add(Coin211);

		Coin Coin212 = new Coin(13408, 1415, 1, Sprite.coin);
		Coin212.init(this);
		add(Coin212);

		Coin Coin213 = new Coin(13418, 1415, 1, Sprite.coin);
		Coin213.init(this);
		add(Coin213);

		Coin Coin214 = new Coin(14230, 1655, 1, Sprite.coin);
		Coin214.init(this);
		add(Coin214);

		Coin Coin215 = new Coin(14240, 1655, 1, Sprite.coin);
		Coin215.init(this);
		add(Coin215);

		Coin Coin216 = new Coin(14250, 1655, 1, Sprite.coin);
		Coin216.init(this);
		add(Coin216);

		//9 pack human cave hidden.
		Coin Coin218 = new Coin(14782, 1622, 1, Sprite.coin);
		Coin218.init(this);
		add(Coin218);

		Coin Coin219 = new Coin(14806, 1622, 1, Sprite.coin);
		Coin219.init(this);
		add(Coin219);

		Coin Coin220 = new Coin(14830, 1622, 1, Sprite.coin);
		Coin220.init(this);
		add(Coin220);

		Coin Coin221 = new Coin(14782, 1637, 1, Sprite.coin);
		Coin221.init(this);
		add(Coin221);

		Coin Coin222 = new Coin(14806, 1637, 1, Sprite.coin);
		Coin222.init(this);
		add(Coin222);

		Coin Coin223 = new Coin(14830, 1637, 1, Sprite.coin);
		Coin223.init(this);
		add(Coin223);

		Coin Coin224 = new Coin(14782, 1652, 1, Sprite.coin);
		Coin224.init(this);
		add(Coin224);

		Coin Coin225 = new Coin(14806, 1652, 1, Sprite.coin);
		Coin225.init(this);
		add(Coin225);

		Coin Coin226 = new Coin(14830, 1652, 1, Sprite.coin);
		Coin226.init(this);
		add(Coin226);

		//load all extra sprite graphics
		//larrys house objects
		Background_Object larry_photo = new Background_Object(3500, 718, Sprite.larry_photo, 43, 38);
		larry_photo.init(this);
		add(larry_photo);

		Background_Object wardrobe2 = new Background_Object(3400, 766, Sprite.wardrobe, 20, 35);
		wardrobe2.init(this);
		add(wardrobe2);

		Background_Object cookingDesk3 = new Background_Object(3540, 773, Sprite.cooking_desk, 44, 24);
		cookingDesk3.init(this);
		add(cookingDesk3);

		Background_Object cookingStove2 = new Background_Object(3583, 773, Sprite.cooking_stove, 43, 24);
		cookingStove2.init(this);
		add(cookingStove2);

		Background_Object cookingDesk4 = new Background_Object(3626, 773, Sprite.cooking_desk, 44, 24);
		cookingDesk4.init(this);
		add(cookingDesk4);

		//load extra sprites for cavewoman house
		Background_Object cookingDesk = new Background_Object(2928, 3316, Sprite.cooking_desk, 44, 24);
		cookingDesk.init(this);
		add(cookingDesk);

		Background_Object cookingStove = new Background_Object(2971, 3316, Sprite.cooking_stove, 43, 24);
		cookingStove.init(this);
		add(cookingStove);

		Background_Object cookingDesk2 = new Background_Object(3014, 3316, Sprite.cooking_desk, 44, 24);
		cookingDesk2.init(this);
		add(cookingDesk2);

		Background_Object fridge = new Background_Object(2893, 3304, Sprite.fridge, 26, 47);
		fridge.init(this);
		add(fridge);

		//kevs house objects
		//living room
		Background_Object kev_sign = new Background_Object(4055, 1812, Sprite.place_sign, 47, 7);
		kev_sign.init(this);
		add(kev_sign);

		Solid_Object kev_tv = new Solid_Object(4127, 1875, Sprite.cooking_tv, 23, 25);
		kev_tv.init(this);
		gameSolidObjects.add(kev_tv);
		add(kev_tv);

		Background_Object sofa = new Background_Object(4189, 1876, Sprite.sofa, 20, 24);
		sofa.init(this);
		add(sofa);

		Background_Object lamp1 = new Background_Object(4179, 1806, Sprite.light, 14, 28);
		lamp1.init(this);
		add(lamp1);

		Background_Object family_photo = new Background_Object(4250, 1825, Sprite.family_photo, 52, 48);
		family_photo.init(this);
		add(family_photo);
		//kitchen 2
		Background_Object lamp2 = new Background_Object(4566, 1806, Sprite.light, 14, 28);
		lamp2.init(this);
		add(lamp2);

		Background_Object cookingDesk5 = new Background_Object(4580, 1876, Sprite.cooking_desk, 44, 24);
		cookingDesk5.init(this);
		add(cookingDesk5);

		Background_Object cookingStove3 = new Background_Object(4623, 1876, Sprite.cooking_stove, 43, 24);
		cookingStove3.init(this);
		add(cookingStove3);

		Background_Object cookingDesk6 = new Background_Object(4666, 1876, Sprite.cooking_desk, 44, 24);
		cookingDesk6.init(this);
		add(cookingDesk6);

		Background_Object fridge3 = new Background_Object(4545, 1864, Sprite.fridge, 26, 47);
		fridge3.init(this);
		add(fridge3);

		//kevs room
		Background_Object lamp3 = new Background_Object(4184, 1662, Sprite.light, 14, 28);
		lamp3.init(this);
		add(lamp3);

		Solid_Object master_bed = new Solid_Object(4049, 1746, Sprite.master_bed, 66, 27);
		master_bed.init(this);
		gameSolidObjects.add(master_bed);
		add(master_bed);

		Background_Object wardrobe3 = new Background_Object(4326, 1742, Sprite.wardrobe, 20, 35);
		wardrobe3.init(this);
		add(wardrobe3);

		Background_Object double_drawer = new Background_Object(4200, 1744, Sprite.double_drawer, 41, 30);
		double_drawer.init(this);
		add(double_drawer);

		Background_Object honeymoon_photo = new Background_Object(4100, 1680, Sprite.honeymoon_photo, 52, 43);
		honeymoon_photo.init(this);
		add(honeymoon_photo);

		//kids room
		Background_Object lamp4 = new Background_Object(4566, 1662, Sprite.light, 14, 28);
		lamp4.init(this);
		add(lamp4);

		Background_Object double_bed_ladder = new Background_Object(4623, 1733, Sprite.double_bed_ladder, 7, 53);
		double_bed_ladder.init(this);
		add(double_bed_ladder);

		Solid_Object double_bed_lower = new Solid_Object(4657, 1749, Sprite.double_bed_lower, 61, 23);
		double_bed_lower.init(this);
		gameSolidObjects.add(double_bed_lower);
		add(double_bed_lower);

		Solid_Object double_bed_stalk = new Solid_Object(4687, 1729, Sprite.double_bed_stalk, 2, 18);
		double_bed_stalk.init(this);
		gameSolidObjects.add(double_bed_stalk);
		add(double_bed_stalk);

		Solid_Object double_bed_higher = new Solid_Object(4657, 1714, Sprite.double_bed_top, 61, 13);
		double_bed_higher.init(this);
		gameSolidObjects.add(double_bed_higher);
		add(double_bed_higher);

		Background_Object red_teddy = new Background_Object(4683, 1701, Sprite.red_teddy, 9, 13);
		red_teddy.init(this);
		add(red_teddy);

		Background_Object green_thing = new Background_Object(4681, 1734, Sprite.green_thing, 9, 8);
		green_thing.init(this);
		add(green_thing);

		Background_Object kids_photo = new Background_Object(4500, 1691, Sprite.kids_photo, 48, 35);
		kids_photo.init(this);
		add(kids_photo);

		Background_Object target = new Background_Object(4450, 1744, Sprite.target, 26, 32);
		target.init(this);
		add(target);

		//all village sprites
		Background_Object village_sign = new Background_Object(5516, 1764, Sprite.cabbage_sign, 109, 92);
		village_sign.init(this);
		add(village_sign);

		Background_Object bush1 = new Background_Object(5733, 1750, Sprite.bush, 57, 19);
		bush1.init(this);
		add(bush1);

		Background_Object bush2 = new Background_Object(6157, 1750, Sprite.bush, 57, 19);
		bush2.init(this);
		add(bush2);

		Background_Object bush3 = new Background_Object(9540, 1687, Sprite.bush, 57, 19);
		bush3.init(this);
		add(bush3);

		Background_Object tree1 = new Background_Object(5835, 1705, Sprite.tree, 77, 110);
		tree1.init(this);
		add(tree1);

		Background_Object tree2 = new Background_Object(6620, 1707, Sprite.tree, 77, 110);
		tree2.init(this);
		add(tree2);

		Background_Object tree3 = new Background_Object(7034, 1707, Sprite.tree2, 76, 110);
		tree3.init(this);
		add(tree3);

		Background_Object tree4 = new Background_Object(8155, 1642, Sprite.tree2, 76, 110);
		tree4.init(this);
		add(tree4);

		Background_Object swimming_sign = new Background_Object(6449, 1736, Sprite.swimming_sign, 89, 47);
		swimming_sign.init(this);
		add(swimming_sign);

		Background_Object fountain = new Background_Object(7500, 1648, Sprite.water_fountain, 228, 96);
		fountain.init(this);
		add(fountain);

		Background_Object shop_text = new Background_Object(7890, 1624, Sprite.shop_text, 23, 7);
		shop_text.init(this);
		add(shop_text);

		Background_Object sewer_sign = new Background_Object(8262, 1674, Sprite.sewer_sign, 70, 44);
		sewer_sign.init(this);
		add(sewer_sign);

		//hq ones
		Background_Object hq_sign = new Background_Object(8524, 1640, Sprite.hq_sign, 100, 24);
		hq_sign.init(this);
		add(hq_sign);

		Background_Object king_photo = new Background_Object(8705, 1620, Sprite.king_photo, 75, 63);
		king_photo.init(this);
		add(king_photo);

		Background_Object torch = new Background_Object(8600, 1662, Sprite.torch, 18, 38);
		torch.init(this);
		add(torch);

		Background_Object torch2 = new Background_Object(8785, 1662, Sprite.torch, 18, 38);
		torch2.init(this);
		add(torch2);

		Background_Object torch3 = new Background_Object(8926, 1662, Sprite.torch, 18, 38);
		torch3.init(this);
		add(torch3);

		Background_Object torch4 = new Background_Object(9267, 1662, Sprite.torch, 18, 38);
		torch4.init(this);
		add(torch4);

		Background_Object library_sign = new Background_Object(9129, 1000, Sprite.library_sign);
		library_sign.init(this);
		add(library_sign);

		Background_Object bookcase1 = new Background_Object(9232, 1009, Sprite.bookcase);
		bookcase1.init(this);
		add(bookcase1);

		Background_Object bookcase2 = new Background_Object(9280, 1009, Sprite.bookcase);
		bookcase2.init(this);
		add(bookcase2);

		Background_Object bookcase3 = new Background_Object(9470, 1009, Sprite.bookcase);
		bookcase3.init(this);
		add(bookcase3);

		Background_Object bookcase4 = new Background_Object(9518, 1009, Sprite.bookcase);
		bookcase4.init(this);
		add(bookcase4);

		Background_Object bookcase5 = new Background_Object(9566, 1009, Sprite.bookcase);
		bookcase5.init(this);
		add(bookcase5);

		Background_Object table_set = new Background_Object(9384, 1022, Sprite.table_set);
		table_set.init(this);
		add(table_set);

		Background_Object kitchen_unit = new Background_Object(8605, 1449, Sprite.kitchen_unit);
		kitchen_unit.init(this);
		add(kitchen_unit);

		Background_Object bedroom_unit = new Background_Object(9116, 1451, Sprite.bedroom_unit);
		bedroom_unit.init(this);
		add(bedroom_unit);

		Solid_Object bed_trans_box = new Solid_Object(9182, 1457, Sprite.bed_transparent_box, 66, 27);
		bed_trans_box.init(this);
		gameSolidObjects.add(bed_trans_box);
		add(bed_trans_box);

		Background_Object queen_photo = new Background_Object(9083, 1397, Sprite.queen_photo);
		queen_photo.init(this);
		add(queen_photo);

		Background_Object lobby_sign = new Background_Object(8676, 1000, Sprite.lobby_sign);
		lobby_sign.init(this);
		add(lobby_sign);

		Background_Object guard_photo = new Background_Object(8870, 974, Sprite.guard_photo);
		guard_photo.init(this);
		add(guard_photo);

		Background_Object tree5 = new Background_Object(9500, 1642, Sprite.tree2, 76, 110);
		tree5.init(this);
		add(tree5);

		Background_Object tree6 = new Background_Object(9842, 1642, Sprite.tree, 76, 110);
		tree6.init(this);
		add(tree6);

		Background_Object king_stat = new Background_Object(10250, 1642, Sprite.king_statue);
		king_stat.init(this);
		add(king_stat);

		Background_Object burning_cabbage = new Background_Object(10540, 1608, Sprite.burning_cabbage1,
				Sprite.burning_cabbage2);
		burning_cabbage.init(this);
		add(burning_cabbage);

		Background_Object bush4 = new Background_Object(10680, 1687, Sprite.bush, 57, 19);
		bush4.init(this);
		add(bush4);

		Background_Object tree7 = new Background_Object(11000, 1642, Sprite.tree, 76, 110);
		tree7.init(this);
		add(tree7);

		Background_Object bank_sign = new Background_Object(11450, 1582, Sprite.bank_sign);
		bank_sign.init(this);
		add(bank_sign);

		Background_Object bush5 = new Background_Object(11855, 1686, Sprite.bush, 57, 19);
		bush5.init(this);
		add(bush5);

		Background_Object tree8 = new Background_Object(12163, 1642, Sprite.tree2, 76, 110);
		tree8.init(this);
		add(tree8);

		Background_Object tree9 = new Background_Object(12605, 1642, Sprite.tree2, 76, 110);
		tree9.init(this);
		add(tree9);

		Solid_Object rocks = new Solid_Object(12872, 1873, Sprite.rocks);
		rocks.init(this);
		gameSolidObjects.add(rocks);
		add(rocks);

		Background_Object tools = new Background_Object(12847, 1873, Sprite.tools);
		tools.init(this);
		add(tools);

		Solid_Object sniper = new Solid_Object(13197, 1020, Sprite.sniper);
		sniper.init(this);
		gameSolidObjects.add(sniper);
		add(sniper);

		//forest ones
		Background_Object old_tree1 = new Background_Object(13365, 1369, Sprite.old_tree1);
		old_tree1.init(this);
		add(old_tree1);

		Background_Object old_tree2 = new Background_Object(13450, 1369, Sprite.old_tree2);
		old_tree2.init(this);
		add(old_tree2);

		Background_Object old_tree3 = new Background_Object(13680, 1369, Sprite.old_tree1);
		old_tree3.init(this);
		add(old_tree3);

		Background_Object old_tree4 = new Background_Object(13765, 1369, Sprite.old_tree2);
		old_tree4.init(this);
		add(old_tree4);

		Background_Object old_tree5 = new Background_Object(13850, 1369, Sprite.old_tree1);
		old_tree5.init(this);
		add(old_tree5);

		Background_Object forest_bush1 = new Background_Object(13912, 1411, Sprite.forest_bush);
		forest_bush1.init(this);
		add(forest_bush1);

		Background_Object tent1 = new Background_Object(14110, 1401, Sprite.tent);
		tent1.init(this);
		add(tent1);

		Background_Object tent2 = new Background_Object(14440, 1401, Sprite.tent);
		tent2.init(this);
		add(tent2);

		Background_Object forest_sign = new Background_Object(13219, 1410, Sprite.forest_sign);
		forest_sign.init(this);
		add(forest_sign);

		Background_Object camp_fire = new Background_Object(14284, 1399, Sprite.camp_fire1, Sprite.camp_fire2);
		camp_fire.init(this);
		add(camp_fire);

		Background_Object old_tree6 = new Background_Object(14553, 1369, Sprite.old_tree1);
		old_tree6.init(this);
		add(old_tree6);

		Background_Object old_tree7 = new Background_Object(14638, 1369, Sprite.old_tree2);
		old_tree7.init(this);
		add(old_tree7);

		Background_Object old_tree8 = new Background_Object(14723, 1369, Sprite.old_tree1);
		old_tree8.init(this);
		add(old_tree8);

		Background_Object skeli = new Background_Object(14060, 1650, Sprite.skeli);
		skeli.init(this);
		add(skeli);

		Background_Object bowl = new Background_Object(14096, 1659, Sprite.bowl);
		bowl.init(this);
		add(bowl);

		Background_Object old_tree9 = new Background_Object(14843, 1321, Sprite.old_tree2);
		old_tree9.init(this);
		add(old_tree9);

		Background_Object old_tree10 = new Background_Object(14928, 1321, Sprite.old_tree1);
		old_tree10.init(this);
		add(old_tree10);

		Background_Object forest_bush2 = new Background_Object(14980, 1363, Sprite.forest_bush);
		forest_bush2.init(this);
		add(forest_bush2);

		Background_Object kids_cage = new Background_Object(15450, 1336, Sprite.cage1);
		kids_cage.init(this);
		add(kids_cage);
		addToChangeableBackgroundObjectsArray(kids_cage);

		//load keys + extra collectables before doors
		Key mushroomKey = new Key(1497, 1863, Sprite.mushroomKey, "Mushroom key");
		mushroomKey.init(this);
		add(mushroomKey);

		Key metalBossKey = new Key(5869, 3076, Sprite.metalBossKey, "Metal boss key");
		metalBossKey.init(this);
		add(metalBossKey);

		Key bankKey = new Key(11225, 1930, Sprite.metalBossKey, "Bank key");
		bankKey.init(this);
		add(bankKey);

		Chest chest = new Chest(12049, 1959);
		chest.init(this);
		add(chest);

		//load doors(and cages)
		Door mrMushroomsDoor1 = new Door(1170, 2416, Sprite.wooden_door, Sprite.wooden_door_shut, mushroomKey);
		mrMushroomsDoor1.init(this);
		mrMushroomsDoor1.openFromRight = true;
		gameSolidObjects.add(mrMushroomsDoor1);
		add(mrMushroomsDoor1);

		Door mrMushroomsDoor2 = new Door(1586, 2416, Sprite.wooden_door, Sprite.wooden_door_shut, mushroomKey);
		mrMushroomsDoor2.init(this);
		mrMushroomsDoor2.openFromRight = true;
		gameSolidObjects.add(mrMushroomsDoor2);
		add(mrMushroomsDoor2);

		Door barDoor1 = new Door(1090, 2096, Sprite.wooden_door, Sprite.wooden_door_shut);
		barDoor1.init(this);
		barDoor1.openFromRight = true;
		gameSolidObjects.add(barDoor1);
		add(barDoor1);

		Door barDoor2 = new Door(1890, 2096, Sprite.wooden_door, Sprite.wooden_door_shut);
		barDoor2.init(this);
		barDoor2.openFromRight = true;
		gameSolidObjects.add(barDoor2);
		add(barDoor2);

		Door mashBossDoor = new Door(2882, 2416, Sprite.iron_door, Sprite.iron_door_shut, metalBossKey);
		mashBossDoor.init(this);
		mashBossDoor.openFromRight = true;
		gameSolidObjects.add(mashBossDoor);
		add(mashBossDoor);

		BossCage cage = new BossCage(2941, 2384, Sprite.iron_cage, 5, 32, true);
		cage.init(this);
		gameSolidObjects.add(cage);
		add(cage);

		Door cavewomandoor = new Door(3183, 3312, Sprite.wooden_door, Sprite.wooden_door_shut);
		cavewomandoor.init(this);
		cavewomandoor.openFromLeft = true;
		gameSolidObjects.add(cavewomandoor);
		add(cavewomandoor);

		Door larrysDoor = new Door(3330, 768, Sprite.wooden_door, Sprite.wooden_door_shut);
		larrysDoor.init(this);
		larrysDoor.openFromRight = true;
		larrysDoor.opened = true;
		gameSolidObjects.add(larrysDoor);
		add(larrysDoor);

		Door kevsDoor = new Door(4002, 1872, Sprite.wooden_door, Sprite.wooden_door_shut);
		kevsDoor.init(this);
		kevsDoor.openFromRight = true;
		gameSolidObjects.add(kevsDoor);
		add(kevsDoor);

		Door villageDoor = new Door(5268, 1784, Sprite.village_door_open, Sprite.village_door_closed);
		villageDoor.init(this);
		villageDoor.openFromRight = true;
		villageDoor.isNormalDoor = false;
		villageDoor.isBigVillageDoor = true;
		gameSolidObjects.add(villageDoor);
		add(villageDoor);

		Door villageDoor1 = new Door(5922, 1744, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor1.init(this);
		villageDoor1.openFromRight = true;
		gameSolidObjects.add(villageDoor1);
		add(villageDoor1);

		Door villageDoor2 = new Door(6127, 1744, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor2.init(this);
		villageDoor2.openFromLeft = true;
		gameSolidObjects.add(villageDoor2);
		add(villageDoor2);

		Door villageDoor3 = new Door(6194, 1744, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor3.init(this);
		villageDoor3.openFromRight = true;
		gameSolidObjects.add(villageDoor3);
		add(villageDoor3);

		Door villageDoor4 = new Door(6351, 1744, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor4.init(this);
		villageDoor4.openFromLeft = true;
		gameSolidObjects.add(villageDoor4);
		add(villageDoor4);

		Door villageDoor5 = new Door(6690, 1744, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor5.init(this);
		villageDoor5.openFromRight = true;
		gameSolidObjects.add(villageDoor5);
		add(villageDoor5);

		Door villageDoor6 = new Door(6895, 1744, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor6.init(this);
		villageDoor6.openFromLeft = true;
		gameSolidObjects.add(villageDoor6);
		add(villageDoor6);

		Door villageDoor7 = new Door(7762, 1680, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor7.init(this);
		villageDoor7.openFromRight = true;
		gameSolidObjects.add(villageDoor7);
		add(villageDoor7);

		Door villageDoor8 = new Door(8015, 1680, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor8.init(this);
		villageDoor8.openFromLeft = true;
		gameSolidObjects.add(villageDoor8);
		add(villageDoor8);

		Door villageDoor9 = new Door(8450, 1680, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor9.init(this);
		villageDoor9.openFromRight = true;
		gameSolidObjects.add(villageDoor9);
		add(villageDoor9);

		Door villageDoor10 = new Door(9314, 1680, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor10.init(this);
		villageDoor10.openFromRight = true;
		gameSolidObjects.add(villageDoor10);
		add(villageDoor10);

		//deleted 11 here

		Door villageDoor12 = new Door(8815, 1456, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor12.init(this);
		villageDoor12.openFromLeft = true;
		gameSolidObjects.add(villageDoor12);
		add(villageDoor12);

		Door villageDoor13 = new Door(8898, 1456, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor13.init(this);
		villageDoor13.openFromRight = true;
		gameSolidObjects.add(villageDoor13);
		add(villageDoor13);

		Door villageDoor14 = new Door(9090, 1024, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor14.init(this);
		villageDoor14.openFromRight = true;
		gameSolidObjects.add(villageDoor14);
		add(villageDoor14);

		Door villageDoor15 = new Door(9602, 1680, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor15.init(this);
		villageDoor15.openFromRight = true;
		gameSolidObjects.add(villageDoor15);
		add(villageDoor15);

		Door villageDoor16 = new Door(9759, 1680, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor16.init(this);
		villageDoor16.openFromLeft = true;
		gameSolidObjects.add(villageDoor16);
		add(villageDoor16);

		Door villageDoor17 = new Door(9922, 1680, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor17.init(this);
		villageDoor17.openFromRight = true;
		gameSolidObjects.add(villageDoor15);
		add(villageDoor17);

		Door villageDoor18 = new Door(10127, 1680, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor18.init(this);
		villageDoor18.openFromLeft = true;
		gameSolidObjects.add(villageDoor18);
		add(villageDoor18);

		Door villageDoor19 = new Door(10770, 1680, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor19.init(this);
		villageDoor19.opened = true;
		villageDoor19.openFromRight = true;
		gameSolidObjects.add(villageDoor19);
		add(villageDoor19);

		Door villageDoor20 = new Door(10914, 1680, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor20.init(this);
		villageDoor20.opened = true;
		villageDoor20.openFromLeft = true;
		gameSolidObjects.add(villageDoor20);
		add(villageDoor20);

		Door villageDoor21 = new Door(11202, 1632, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor21.init(this);
		villageDoor21.openFromRight = true;
		gameSolidObjects.add(villageDoor21);
		add(villageDoor21);

		Door villageDoor22 = new Door(11695, 1632, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor22.init(this);
		villageDoor22.openFromLeft = true;
		gameSolidObjects.add(villageDoor22);
		add(villageDoor22);

		Door bankDoor = new Door(11938, 1952, Sprite.iron_door, Sprite.iron_door_shut, bankKey);
		bankDoor.init(this);
		bankDoor.openFromRight = true;
		gameSolidObjects.add(bankDoor);
		add(bankDoor);

		Door villageDoor23 = new Door(11906, 1680, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor23.init(this);
		villageDoor23.openFromRight = true;
		villageDoor23.opened = true;
		gameSolidObjects.add(villageDoor23);
		add(villageDoor23);

		Door villageDoor24 = new Door(12066, 1680, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor24.init(this);
		villageDoor24.openFromLeft = true;
		villageDoor24.opened = true;
		gameSolidObjects.add(villageDoor24);
		add(villageDoor24);

		Door villageDoor25 = new Door(12258, 1680, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor25.init(this);
		villageDoor25.openFromRight = true;
		villageDoor25.opened = true;
		gameSolidObjects.add(villageDoor25);
		add(villageDoor25);

		Door villageDoor26 = new Door(12402, 1680, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor26.init(this);
		villageDoor26.openFromLeft = true;
		villageDoor26.opened = true;
		gameSolidObjects.add(villageDoor26);
		add(villageDoor26);

		Door villageBigDoor2 = new Door(12964, 1432, Sprite.village_door_open, Sprite.village_door_closed);
		villageBigDoor2.init(this);
		villageBigDoor2.openFromRight = true;
		villageBigDoor2.isNormalDoor = false;
		villageBigDoor2.isBigVillageDoor2 = true;
		gameSolidObjects.add(villageBigDoor2);
		add(villageBigDoor2);

		Door villageDoor27 = new Door(12914, 1008, Sprite.wooden_door, Sprite.wooden_door_shut);
		villageDoor27.init(this);
		villageDoor27.openFromRight = true;
		gameSolidObjects.add(villageDoor27);
		add(villageDoor27);

		BossCage potCage = new BossCage(16401, 1584, Sprite.iron_cage, 5, 32, false);
		potCage.init(this);
		gameSolidObjects.add(potCage);
		add(potCage);

		//load all enemies
		Mr_mash mash1 = new Mr_mash(2320, 2416, 0, false);
		mash1.init(this);
		enemies.add(mash1);
		add(mash1);

		Mr_mash mash2 = new Mr_mash(2672, 2400, 0, false);
		mash2.init(this);
		enemies.add(mash2);
		add(mash2);

		Mr_mash mash3 = new Mr_mash(2976, 2400, 0, true);
		mash3.init(this);
		enemies.add(mash3);
		add(mash3);

		Mr_mash mash4 = new Mr_mash(2791, 2789, 0, 2639, 2922, false);
		mash4.init(this);
		enemies.add(mash4);
		add(mash4);

		Mr_mash mash5 = new Mr_mash(3891, 2948, 0, 3582, 4104, true);
		mash5.init(this);
		enemies.add(mash5);
		add(mash5);

		Mr_mash mash6 = new Mr_mash(4229, 2739, 0, 4140, 4293, false);
		mash6.init(this);
		enemies.add(mash6);
		add(mash6);

		Mr_mash mash7 = new Mr_mash(5534, 3104, 1, 5210, 5648, false);
		mash7.init(this);
		enemies.add(mash7);
		add(mash7);

		MashBoss mashBoss = new MashBoss(3300, 2360);
		mashBoss.init(this);
		enemies.add(mashBoss);
		add(mashBoss);

		CaveCrawler caveCrawler1 = new CaveCrawler(3558, 3316, 0);
		caveCrawler1.init(this);
		enemies.add(caveCrawler1);
		add(caveCrawler1);

		CaveCrawler caveCrawler2 = new CaveCrawler(3348, 3311, 0);
		caveCrawler2.init(this);
		enemies.add(caveCrawler2);
		add(caveCrawler2);

		CaveCrawler caveCrawler3 = new CaveCrawler(5816, 3103, 0);
		caveCrawler3.init(this);
		enemies.add(caveCrawler3);
		add(caveCrawler3);

		BlackEyePee blackEye1 = new BlackEyePee(2561, 800, 0, 2409, 4000, true);
		blackEye1.init(this);
		enemies.add(blackEye1);
		add(blackEye1);

		BlackEyePee blackEye2 = new BlackEyePee(2000, 1050, 0, false);
		blackEye2.init(this);
		enemies.add(blackEye2);
		add(blackEye2);

		BlackEyePee blackEye3 = new BlackEyePee(1700, 1050, 1, true);
		blackEye3.init(this);
		enemies.add(blackEye3);
		add(blackEye3);

		BlackEyePee blackEye4 = new BlackEyePee(2450, 1050, 0, false);
		blackEye4.init(this);
		enemies.add(blackEye4);
		add(blackEye4);

		BlackEyePee blackEye5 = new BlackEyePee(3000, 800, 0, 2409, 4000, true);
		blackEye5.init(this);
		enemies.add(blackEye5);
		add(blackEye5);

		seaSlug seaSlug1 = new seaSlug(2839, 2123, 0, "red", true);
		seaSlug1.init(this);
		enemies.add(seaSlug1);
		add(seaSlug1);

		seaSlug seaSlug2 = new seaSlug(2988, 2123, 1, "blue", false);
		seaSlug2.init(this);
		enemies.add(seaSlug2);
		add(seaSlug2);

		seaSlug seaSlug3 = new seaSlug(3184, 2123, 0, "red", true);
		seaSlug3.init(this);
		enemies.add(seaSlug3);
		add(seaSlug3);

		Spider spider1 = new Spider(4167, 1595, 0, true);
		spider1.init(this);
		enemies.add(spider1);
		add(spider1);

		Spider spider2 = new Spider(4405, 1595, 1, false);
		spider2.init(this);
		enemies.add(spider2);
		add(spider2);

		Spider spider3 = new Spider(4607, 1595, 0, true);
		spider3.init(this);
		enemies.add(spider3);
		add(spider3);

		Slime slime1 = new Slime(8908, 1916, 1, true);
		slime1.init(this);
		enemies.add(slime1);
		add(slime1);

		Slime slime2 = new Slime(8938, 2125, 0, false);
		slime2.init(this);
		enemies.add(slime2);
		add(slime2);

		CaveCrawler caveCrawler4 = new CaveCrawler(7869, 2157, 0);
		caveCrawler4.init(this);
		enemies.add(caveCrawler4);
		add(caveCrawler4);

		CaveCrawler caveCrawler5 = new CaveCrawler(8038, 2160, 1);
		caveCrawler5.init(this);
		enemies.add(caveCrawler5);
		add(caveCrawler5);

		CaveCrawler caveCrawler6 = new CaveCrawler(8857, 2445, 1);
		caveCrawler6.init(this);
		enemies.add(caveCrawler6);
		add(caveCrawler6);

		Slime slime3 = new Slime(7974, 2157, 0, false);
		slime3.init(this);
		enemies.add(slime3);
		add(slime3);

		Slime slime4 = new Slime(8762, 2444, 0, false);
		slime4.init(this);
		enemies.add(slime4);
		add(slime4);

		Slime slime5 = new Slime(8052, 2444, 0, true);
		slime5.init(this);
		enemies.add(slime5);
		add(slime5);

		Blob blob1 = new Blob(8011, 1983, Sprite.blob_down1, true);
		blob1.init(this);
		enemies.add(blob1);
		add(blob1);

		Blob blob2 = new Blob(8184, 2319, Sprite.blob_down1, true);
		blob2.init(this);
		enemies.add(blob2);
		add(blob2);

		Blob blob3 = new Blob(9441, 2351, Sprite.blob_left1, false);
		blob3.init(this);
		enemies.add(blob3);
		add(blob3);

		Blob blob4 = new Blob(9295, 2799, Sprite.blob_right1, false);
		blob4.init(this);
		enemies.add(blob4);
		add(blob4);

		Blob blob5 = new Blob(9857, 2847, Sprite.blob_left1, true);
		blob5.init(this);
		enemies.add(blob5);
		add(blob5);

		SewerBoss sewerBoss = new SewerBoss(9515, 2877);
		sewerBoss.init(this);
		enemies.add(sewerBoss);
		add(sewerBoss);
		totalCoins += 7;

		Spider spider4 = new Spider(9383, 796, 0, false);
		spider4.init(this);
		enemies.add(spider4);
		add(spider4);

		Spider spider5 = new Spider(9458, 796, 1, true);
		spider5.init(this);
		enemies.add(spider5);
		add(spider5);
		totalCoins += 1;

		Spider spider6 = new Spider(9516, 796, 0, false);
		spider6.init(this);
		enemies.add(spider6);
		add(spider6);
		//village mob enemys (and friendly cabbages)
		//potato rebels with swords in middle by fire
		///this initial add one is for the rebel we add after first encounter,
		//the sneaky one that spawns after text seq end.
		CabbageKing.VillageMobsNeeded++;

		PotatoRebel rebel1 = new PotatoRebel(10420, 1000, 0, false, 1);
		rebel1.init(this);
		enemies.add(rebel1);
		add(rebel1);
		CabbageKing.VillageMobsNeeded++;

		PotatoRebel rebel2 = new PotatoRebel(10480, 1100, 0, false, 1);
		rebel2.init(this);
		enemies.add(rebel2);
		add(rebel2);
		CabbageKing.VillageMobsNeeded++;

		PotatoRebel rebel3 = new PotatoRebel(10530, 1100, 0, false, 1);
		rebel3.init(this);
		enemies.add(rebel3);
		add(rebel3);
		CabbageKing.VillageMobsNeeded++;

		PotatoRebel rebel4 = new PotatoRebel(11000, 1100, 0, false, 2);
		rebel4.init(this);
		rebel4.doesActivateUponSight = true;
		enemies.add(rebel4);
		add(rebel4);
		CabbageKing.VillageMobsNeeded++;

		PotatoRebel rebel5 = new PotatoRebel(10864, 1670, 0, false, 2);
		rebel5.init(this);
		rebel5.doesActivateUponSight = true;
		enemies.add(rebel5);
		add(rebel5);
		CabbageKing.VillageMobsNeeded++;

		CabbageGuard guard1 = new CabbageGuard(10683, 1670, 0, false, -2);
		guard1.init(this);
		guard1.doesActivateUponSight = true;
		enemies.add(guard1);
		add(guard1);

		CabbageGuard guard2 = new CabbageGuard(11297, 1628, 0, false, -2);
		guard2.init(this);
		guard2.doesActivateUponSight = true;
		enemies.add(guard2);
		add(guard2);

		PotatoRebel rebel6 = new PotatoRebel(11437, 1627, 0, false, 2);
		rebel6.init(this);
		rebel6.doesActivateUponSight = true;
		enemies.add(rebel6);
		add(rebel6);
		CabbageKing.VillageMobsNeeded++;

		PotatoRebel rebel7 = new PotatoRebel(11610, 1627, 0, false, 2);
		rebel7.init(this);
		rebel7.doesActivateUponSight = true;
		enemies.add(rebel7);
		add(rebel7);
		CabbageKing.VillageMobsNeeded++;

		PotatoRebel rebel8 = new PotatoRebel(11614, 1945, 0, false, 2);
		rebel8.init(this);
		rebel8.doesActivateUponSight = true;
		enemies.add(rebel8);
		add(rebel8);
		CabbageKing.VillageMobsNeeded++;

		PotatoRebel rebel9 = new PotatoRebel(11750, 1920, 0, false, 2);
		rebel9.init(this);
		rebel9.doesActivateUponSight = true;
		enemies.add(rebel9);
		add(rebel9);
		CabbageKing.VillageMobsNeeded++;

		CabbageGuard guard3 = new CabbageGuard(11858, 1672, 0, false, -2);
		guard3.init(this);
		guard3.doesActivateUponSight = true;
		enemies.add(guard3);
		add(guard3);

		PotatoRebel rebel10 = new PotatoRebel(12133, 1672, 0, false, 2);
		rebel10.init(this);
		rebel10.doesActivateUponSight = true;
		enemies.add(rebel10);
		add(rebel10);
		CabbageKing.VillageMobsNeeded++;

		PotatoRebel rebel11 = new PotatoRebel(12330, 1672, 0, false, 2);
		rebel11.init(this);
		rebel11.doesActivateUponSight = true;
		enemies.add(rebel11);
		add(rebel11);
		CabbageKing.VillageMobsNeeded++;

		PotatoRebel rebel12 = new PotatoRebel(12791, 1858, 0, false, 2);
		rebel12.init(this);
		rebel12.doesActivateUponSight = true;
		enemies.add(rebel12);
		add(rebel12);
		CabbageKing.VillageMobsNeeded++;

		PotatoRebel rebel13 = new PotatoRebel(12675, 1676, 0, false, 2);
		rebel13.init(this);
		rebel13.doesActivateUponSight = true;
		enemies.add(rebel13);
		add(rebel13);
		CabbageKing.VillageMobsNeeded++;

		RedRebel redRebel1 = new RedRebel(10760, 1600, true);
		redRebel1.init(this);
		enemies.add(redRebel1);
		add(redRebel1);
		CabbageKing.VillageMobsNeeded++;

		RedRebel redRebel2 = new RedRebel(10935, 1600, false);
		redRebel2.init(this);
		enemies.add(redRebel2);
		add(redRebel2);
		CabbageKing.VillageMobsNeeded++;

		RedRebel redRebel3 = new RedRebel(12230, 1600, true);
		redRebel3.init(this);
		enemies.add(redRebel3);
		add(redRebel3);
		CabbageKing.VillageMobsNeeded++;

		RedRebel redRebel4 = new RedRebel(12440, 1600, false);
		redRebel4.init(this);
		enemies.add(redRebel4);
		add(redRebel4);
		CabbageKing.VillageMobsNeeded++;

		//forest ones
		Human human1 = new Human(13860, 1407, 1, 13780, 13975, true);
		human1.init(this);
		enemies.add(human1);
		add(human1);

		Human human2 = new Human(14060, 1407, 0, 13985, 14140, false);
		human2.init(this);
		enemies.add(human2);
		add(human2);

		PotatoRebel rebel15 = new PotatoRebel(14265, 1400, 0, false, 2);
		rebel15.init(this);
		rebel15.doesActivateUponSight = true;
		enemies.add(rebel15);
		add(rebel15);

		PotatoRebel rebel16 = new PotatoRebel(14350, 1400, 0, false, 2);
		rebel16.init(this);
		rebel16.doesActivateUponSight = true;
		enemies.add(rebel16);
		add(rebel16);

		PotatoRebel rebel17 = new PotatoRebel(14467, 1400, 0, false, 2);
		rebel17.init(this);
		rebel17.doesActivateUponSight = true;
		enemies.add(rebel17);
		add(rebel17);

		Human human3 = new Human(14600, 1407, 1, 14534, 14672, true);
		human3.init(this);
		enemies.add(human3);
		add(human3);

		//EVIL POTATO BOSS//
		EvilPotato evilpot = new EvilPotato(15330, 1300);
		//EvilPotato evilpot = new EvilPotato(16135, 1609); //temp for combat testing
		evilpot.init(this);
		enemies.add(evilpot);
		add(evilpot);

		//load all npcs(eg roy,mr_mush,cave man)
		Mr_mushroom mr_mushroom = new Mr_mushroom(1650, 2100, Sprite.mr_mushroom1);
		mr_mushroom.init(this);
		add(mr_mushroom);

		Roy roy = new Roy(2100, 2098, Sprite.roy1);
		roy.init(this);
		add(roy);

		Roy village_roy = new Roy(7927, 1682, Sprite.roy1);
		village_roy.init(this);
		add(village_roy);

		Roy end_roy = new Roy(16542, 1617, Sprite.roy1);
		end_roy.init(this);
		add(end_roy);

		Caveman caveman = new Caveman(4435, 2733, Sprite.cave_man1);
		caveman.init(this);
		add(caveman);

		Cavewoman cavewoman = new Cavewoman(2976, 3309, Sprite.cave_woman);
		cavewoman.init(this);
		add(cavewoman);

		LarryLeak larry = new LarryLeak(2513, 1400, Sprite.larry_leak_forward);
		larry.init(this);
		add(larry);

		Sprout sproutySteve = new Sprout(2540, 1092, Sprite.sprout1);
		sproutySteve.init(this);
		add(sproutySteve);

		BlackEyeDad blackEyeDad = new BlackEyeDad(1240, 965, Sprite.black_eye_pee_dad);
		blackEyeDad.init(this);
		add(blackEyeDad);

		kevsWife wife = new kevsWife(4600, 1879);
		wife.init(this);
		add(wife);

		Cabbage guard = new Cabbage(5209, 1795);
		guard.init(this);
		add(guard);

		CabbageMale cabbage_male1 = new CabbageMale(6270, 1746);
		cabbage_male1.init(this);
		add(cabbage_male1);

		CabbageFemale cabbage_female1 = new CabbageFemale(6010, 1746);
		cabbage_female1.init(this);
		add(cabbage_female1);

		cabbageKid cabbage_kid = new cabbageKid(6737, 1993);
		cabbage_kid.init(this);
		add(cabbage_kid);

		CabbageKidKite cabbage_kid_kite = new CabbageKidKite(7400, 1670, 2);
		cabbage_kid_kite.init(this);
		add(cabbage_kid_kite);

		Character cabbage_guard_village = new Character(6820, 1747, Sprite.cabbage_guard1, Sprite.cabbage_guard2, -20,
				-20);
		cabbage_guard_village.init(this);
		add(cabbage_guard_village);

		BarryBeetroot barry = new BarryBeetroot(8413, 1923);
		barry.init(this);
		add(barry);

		PeterPickle peterPickle = new PeterPickle(7610, 2428);
		peterPickle.init(this);
		add(peterPickle);

		BillyBrocoli billy = new BillyBrocoli(10312, 2908);
		billy.init(this);
		add(billy);

		CryingCabbagekid cryingKid = new CryingCabbagekid(8630, 1688);
		cryingKid.init(this);
		add(cryingKid);

		TeenCabbage teenCabbage = new TeenCabbage(8698, 1684);
		teenCabbage.init(this);
		add(teenCabbage);

		CabbageKing king = new CabbageKing(9094, 1631);
		king.init(this);
		add(king);

		cabbageChef crazyChef = new cabbageChef(8570, 1456);
		crazyChef.init(this);
		add(crazyChef);

		CrazyCabbage crazyKid1 = new CrazyCabbage(8720, 1420, 1, "crazyKid");
		crazyKid1.init(this);
		add(crazyKid1);

		CrazyCabbage crazyKid2 = new CrazyCabbage(8680, 1420, 2, "crazyKid");
		crazyKid2.init(this);
		add(crazyKid2);

		CrazyCabbage crazyKid3 = new CrazyCabbage(8780, 1420, 1, "crazyKid");
		crazyKid3.init(this);
		add(crazyKid3);

		Character cabbage_guard_village2 = new Character(8958, 1682, Sprite.cabbage_guard1, Sprite.cabbage_guard2, -20,
				-20);
		cabbage_guard_village2.init(this);
		add(cabbage_guard_village2);

		Character cabbage_guard_village3 = new Character(9213, 1682, Sprite.cabbage_guard1, Sprite.cabbage_guard2, -20,
				-20);
		cabbage_guard_village3.init(this);
		add(cabbage_guard_village3);

		CabbageQueen queen = new CabbageQueen(8991, 1459);
		queen.init(this);
		add(queen);

		CabbageNerd nerd = new CabbageNerd(9311, 1026);
		nerd.init(this);
		add(nerd);

		Character nerd2 = new Character(9507, 1026, Sprite.cabbage_nerd3, Sprite.cabbage_nerd4, -15, -15);
		nerd2.init(this);
		add(nerd2);

		Character nerd3 = new Character(9550, 1026, Sprite.cabbage_nerd5, Sprite.cabbage_nerd6, -15, -15);
		nerd3.init(this);
		add(nerd3);

		Character cabbage_guard_village4 = new Character(8771, 1027, Sprite.cabbage_guard1, Sprite.cabbage_guard2, -20,
				-20);
		cabbage_guard_village4.init(this);
		add(cabbage_guard_village4);

		Character cabbage_guard_village5 = new Character(8962, 1027, Sprite.cabbage_guard1, Sprite.cabbage_guard2, -20,
				-20);
		cabbage_guard_village5.init(this);
		add(cabbage_guard_village5);

		CrazyCabbage crazyKid4 = new CrazyCabbage(9731, 1679, 1, "crazyKid");
		crazyKid4.init(this);
		add(crazyKid4);

		CrazyCabbage crazyWoman1 = new CrazyCabbage(10090, 1665, 1, "crazyWoman", 80, 60);
		crazyWoman1.init(this);
		add(crazyWoman1);

		CabbageBanker banker = new CabbageBanker(11985, 1954);
		banker.init(this);
		add(banker);

		CrazyCabbage crazyMale1 = new CrazyCabbage(12030, 1678, 1, "crazyMale", 60, 60);
		crazyMale1.init(this);
		add(crazyMale1);

		CrazyCabbage crazyKid5 = new CrazyCabbage(12380, 1678, 1, "crazyKid", 60, 60);
		crazyKid5.init(this);
		add(crazyKid5);

		HighCabbage highCab = new HighCabbage(12895, 1436);
		highCab.init(this);
		add(highCab);

		CabbageSniper sniper1 = new CabbageSniper(13107, 1020);
		sniper1.init(this);
		add(sniper1);

		Roy roy3 = new Roy(13615, 1410, Sprite.roy1);
		roy3.init(this);
		add(roy3);

		HumanCaged slave = new HumanCaged(14135, 1647);
		slave.init(this);
		add(slave);

		//load all moving platform objects
		//ones in the cave
		movingPlatform platform1 = new movingPlatform(4260, 3240, Sprite.sky_grass, 16, 16, 160, 60, 1, 0);
		platform1.init(this);
		platform1.leftRight = true;
		gameSolidObjects.add(platform1);
		add(platform1);

		movingPlatform platform2 = new movingPlatform(4276, 3240, Sprite.sky_grass, 16, 16, 160, 60, 1, 0);
		platform2.init(this);
		platform2.leftRight = true;
		gameSolidObjects.add(platform2);
		add(platform2);

		movingPlatform platform3 = new movingPlatform(4724, 3136, Sprite.sky_grass, 16, 16, 220, 60, 1, 0);
		platform3.init(this);
		platform3.leftRight = true;
		gameSolidObjects.add(platform3);
		add(platform3);

		movingPlatform platform4 = new movingPlatform(4740, 3136, Sprite.sky_grass, 16, 16, 220, 60, 1, 0);
		platform4.init(this);
		platform4.leftRight = true;
		gameSolidObjects.add(platform4);
		add(platform4);

		movingPlatform platform5 = new movingPlatform(4980, 3136, Sprite.sky_grass, 16, 16, 220, 60, 1, 1);
		platform5.init(this);
		platform5.leftRight = true;
		gameSolidObjects.add(platform5);
		add(platform5);

		movingPlatform platform6 = new movingPlatform(4996, 3136, Sprite.sky_grass, 16, 16, 220, 60, 1, 1);
		platform6.init(this);
		platform6.leftRight = true;
		gameSolidObjects.add(platform6);
		add(platform6);

		movingPlatform platform7 = new movingPlatform(5176, 3184, Sprite.sky_grass, 16, 16, 114, 60, 1, 3);
		platform7.init(this);
		platform7.upDown = true;
		gameSolidObjects.add(platform7);
		add(platform7);

		movingPlatform platform8 = new movingPlatform(5192, 3184, Sprite.sky_grass, 16, 16, 114, 60, 1, 3);
		platform8.init(this);
		platform8.upDown = true;
		gameSolidObjects.add(platform8);
		add(platform8);

		//ones on sky route
		movingPlatform skyPlatform1 = new movingPlatform(1944, 1577, Sprite.sky_grass, 16, 16, 66, 20, 1, 0);
		skyPlatform1.init(this);
		skyPlatform1.leftRight = true;
		gameSolidObjects.add(skyPlatform1);
		add(skyPlatform1);

		movingPlatform skyPlatform2 = new movingPlatform(1928, 1577, Sprite.sky_grass, 16, 16, 66, 20, 1, 0);
		skyPlatform2.init(this);
		skyPlatform2.leftRight = true;
		gameSolidObjects.add(skyPlatform2);
		add(skyPlatform2);

		movingPlatform skyPlatform3 = new movingPlatform(1944, 1512, Sprite.sky_grass, 16, 16, 66, 20, 1, 1);
		skyPlatform3.init(this);
		skyPlatform3.leftRight = true;
		gameSolidObjects.add(skyPlatform3);
		add(skyPlatform3);

		movingPlatform skyPlatform4 = new movingPlatform(1928, 1512, Sprite.sky_grass, 16, 16, 66, 20, 1, 1);
		skyPlatform4.init(this);
		skyPlatform4.leftRight = true;
		gameSolidObjects.add(skyPlatform4);
		add(skyPlatform4);

		movingPlatform skyPlatform5 = new movingPlatform(1944, 1448, Sprite.sky_grass, 16, 16, 66, 20, 1, 0);
		skyPlatform5.init(this);
		skyPlatform5.leftRight = true;
		gameSolidObjects.add(skyPlatform5);
		add(skyPlatform5);

		movingPlatform skyPlatform6 = new movingPlatform(1928, 1448, Sprite.sky_grass, 16, 16, 66, 20, 1, 0);
		skyPlatform6.init(this);
		skyPlatform6.leftRight = true;
		gameSolidObjects.add(skyPlatform6);
		add(skyPlatform6);

		//ones over mini water section
		movingPlatform waterPlatform = new movingPlatform(2919, 1880, Sprite.sky_grass, 16, 16, 288, 20, 1, 0);
		waterPlatform.init(this);
		waterPlatform.leftRight = true;
		gameSolidObjects.add(waterPlatform);
		add(waterPlatform);

		movingPlatform waterPlatform2 = new movingPlatform(2903, 1880, Sprite.sky_grass, 16, 16, 288, 20, 1, 0);
		waterPlatform2.init(this);
		waterPlatform2.leftRight = true;
		gameSolidObjects.add(waterPlatform2);
		add(waterPlatform2);

		movingPlatform waterPlatform3 = new movingPlatform(3273, 1880, Sprite.sky_grass, 16, 16, 288, 20, 1, 1);
		waterPlatform3.init(this);
		waterPlatform3.leftRight = true;
		gameSolidObjects.add(waterPlatform3);
		add(waterPlatform3);

		movingPlatform waterPlatform4 = new movingPlatform(3257, 1880, Sprite.sky_grass, 16, 16, 288, 20, 1, 1);
		waterPlatform4.init(this);
		waterPlatform4.leftRight = true;
		gameSolidObjects.add(waterPlatform4);
		add(waterPlatform4);

		//ones in cabb village
		movingPlatform platform9 = new movingPlatform(8496, 2392, Sprite.sky_grass_dirt, 16, 16, 306, 60, 1, 1);
		platform9.init(this);
		platform9.leftRight = true;
		gameSolidObjects.add(platform9);
		add(platform9);

		movingPlatform platform10 = new movingPlatform(8512, 2392, Sprite.sky_grass_dirt, 16, 16, 306, 60, 1, 1);
		platform10.init(this);
		platform10.leftRight = true;
		gameSolidObjects.add(platform10);
		add(platform10);

		movingPlatform platform11 = new movingPlatform(12712, 1160, Sprite.sky_grass, 16, 16, 259, 60, 1, 3);
		platform11.init(this);
		platform11.upDown = true;
		gameSolidObjects.add(platform11);
		add(platform11);

		movingPlatform platform12 = new movingPlatform(12728, 1160, Sprite.sky_grass, 16, 16, 259, 60, 1, 3);
		platform12.init(this);
		platform12.upDown = true;
		gameSolidObjects.add(platform12);
		add(platform12);

		//load extra background sprites
		Background_Object bar_sign = new Background_Object(1488, 2050, Sprite.bar_sign, 48, 16);
		bar_sign.init(this);
		add(bar_sign);

		Background_Object wife_portrait = new Background_Object(1350, 2370, Sprite.wife_portrait, 37, 37);
		wife_portrait.init(this);
		add(wife_portrait);

		Solid_Object redBed = new Solid_Object(1424, 2428, Sprite.red_bed, 41, 23);
		redBed.init(this);
		gameSolidObjects.add(redBed);
		add(redBed);

		Solid_Object wardrobe = new Solid_Object(1300, 2416, Sprite.wardrobe, 20, 35);
		wardrobe.init(this);
		gameSolidObjects.add(wardrobe);
		add(wardrobe);

		Solid_Object tv = new Solid_Object(1375, 2423, Sprite.tv, 23, 25);
		tv.init(this);
		gameSolidObjects.add(tv);
		add(tv);

		Solid_Object drawer = new Solid_Object(1490, 2423, Sprite.drawer, 31, 19);
		drawer.init(this);
		gameSolidObjects.add(drawer);
		add(drawer);

	}
}
