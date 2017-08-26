package jonny.main.sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

	//properties
	public String path;
	private AudioClip clip;

	//needed for certain clips
	public boolean isPlaying = false;

	//static sounds here
	public static final Sound menuClick = new Sound("/sound/menuClick.wav");
	public static final Sound menuMusic = new Sound("/sound/menuMusic.wav");
	public static final Sound menuCompleteMusic = new Sound("/sound/goofyMenuMusic.wav");

	public static final Sound carrotCrunch = new Sound("/sound/carrotCrunch2.wav");
	public static final Sound gooFire = new Sound("/sound/gooFire.wav");
	public static final Sound fiff = new Sound("/sound/fiff.wav");
	public static final Sound mashDeath = new Sound("/sound/mashDeath.wav");
	public static final Sound keyPickup = new Sound("/sound/keyPickup.wav");
	public static final Sound mashTalking = new Sound("/sound/mashTalking.wav");
	public static final Sound pwut = new Sound("/sound/pwut.wav");
	public static final Sound doorOpen = new Sound("/sound/doorOpen.wav");
	public static final Sound dinDing = new Sound("/sound/dinDing.wav");
	public static final Sound squish = new Sound("/sound/squish.wav");
	public static final Sound ArmorPurchase = new Sound("/sound/purchase.wav");
	public static final Sound mashBossDeath = new Sound("/sound/mashBossDeath.wav");
	public static final Sound crawlerDeath = new Sound("/sound/crawlerDeath.wav");
	public static final Sound pleb = new Sound("/sound/pleb.wav");
	public static final Sound drowning = new Sound("/sound/drowning.wav");
	public static final Sound shopDing = new Sound("/sound/shopDing.wav");
	public static final Sound blouk = new Sound("/sound/blouk.wav");
	public static final Sound slimeDeath = new Sound("/sound/slimeDeath.wav");
	public static final Sound sewerDeath = new Sound("/sound/sewerDeath.wav");
	public static final Sound shotgun = new Sound("/sound/shotgun.wav");
	public static final Sound slugDeath = new Sound("/sound/slugDeath.wav");
	public static final Sound spiderDeath = new Sound("/sound/spiderDeath.wav");
	public static final Sound cakePickup = new Sound("/sound/cakePickup.wav");
	public static final Sound potatoDeath = new Sound("/sound/potatoDeath.wav");
	public static final Sound cabbageDeath = new Sound("/sound/cabbageDeath.wav");
	public static final Sound lazer = new Sound("/sound/lazer.wav");
	public static final Sound humanDeath = new Sound("/sound/humanDeath.wav");
	public static final Sound bomb = new Sound("/sound/bomb.wav");
	public static final Sound explosion = new Sound("/sound/explosion.wav");
	public static final Sound peelingFace = new Sound("/sound/peelingFace.wav");
	public static final Sound blackEyeDeath = new Sound("/sound/blackEyeDeath.wav");
	public static final Sound monsterRoar = new Sound("/sound/monsterRoar.wav");

	//these is for the main sound thoughout the game, switched according to level update code.
	public static enum MAIN_TRACK {
		NORMAL, CAVE, BOSS, END_SCENE, VILLAGE_CHAOS
	}

	public static MAIN_TRACK track = MAIN_TRACK.NORMAL;

	public static Sound music = new Sound("/sound/Slow_Ska_Game_Loop.wav"); //may change throughout game.
	public static Sound caveMusic = new Sound("/sound/caveMusic.wav"); //may change throughout game.
	public static Sound bossMusic = new Sound("/sound/Slow_Ska_Game_Loop.wav"); //may change throughout game.
	public static Sound villageChaosMusic = new Sound("/sound/Slow_Ska_Game_Loop.wav"); //may change throughout game.

	//currently unused
	public static final Sound chew = new Sound("/sound/chew.wav");

	public Sound(String path) {
		this.path = path;
		loadPath(path);
	}

	public void loadPath(String path) {
		clip = Applet.newAudioClip(getClass().getResource(path));

	}

	//option to play once or loop the clip continuosly
	public void play(boolean loop) {
		if (loop) {
			clip.loop();
		} else {
			clip.play();
		}
	}

	public void stop() {
		clip.stop();
	}

}
