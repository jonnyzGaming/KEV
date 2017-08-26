package jonny.main.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import jonny.main.Game;
import jonny.main.Game.GameState;
import jonny.main.entity.mob.Player;
import jonny.main.entity.mob.Player.weapon;
import jonny.main.entity.mob.characters.Roy;
import jonny.main.graphics.Screen;
import jonny.main.graphics.Sprite;
import jonny.main.input.KeyBoard;
import jonny.main.input.Mouse;
import jonny.main.sound.Sound;

public class ShopMenu extends Menu {
	//background here start (1st tab)
	public static BackGround background = BackGround.shop_background1;

	public static final Color highterColor = new Color(0xff8486FF);
	public static final Color redColor = new Color(0xffFA436B);
	public static final Color purpleColor = new Color(0xff69045B);
	public static final Color greenColor = new Color(0xff3EED4C);
	public static final Color blackColor = new Color(0xff272927);

	public static enum Tab {
		GENERAL, WEAPONS, CLOTHING
	}

	public static Tab tabSelected = Tab.GENERAL;

	//extra general properties
	public static int itemNum = 1;
	public static int bobTimer = 0;
	public static final int bobTimerMax = 50;

	public ShopMenu(KeyBoard input, Screen screen) {
		super(input, screen);

		timerReset = 10;
		timer = -1;
	}

	public void update() {

		//for all sprites
		bobTimer++;
		if (bobTimer >= 70000) bobTimer = 0;

		//close button
		int x = Mouse.getX();
		int y = Mouse.getY();
		if ((x >= 750 && x <= 780 && y >= 15 && y <= 45) || input.backspace) {
			Game.gs = GameState.SINGLE;
			Roy.inMenu = false;
			background = BackGround.shop_background1;
			tabSelected = Tab.GENERAL;
			timer = -1;

		}

		//for when the menu is first opened
		if (timer == -1) {
			timer = 60;
		}

		if (timer > 0) timer--;

		//item selecting
		if (timer == 0) {
			if (input.down && (itemNum >= 1 && itemNum < 4)) {
				itemNum++;
				timer = timerReset;
			}

			if (input.up && (itemNum > 1 && itemNum <= 4)) {
				itemNum--;
				timer = timerReset;
			}
		}

		//select different tabs
		//general tab
		if (tabSelected == Tab.GENERAL) {

			if (timer == 0) {

				//general tab updating
				//unlock double jump 
				if (itemNum == 1 && !Player.DOUBLE_JUMP_UNLOCKED && Player.coins >= Player.double_jump_price && input.enter) {
					Player.DOUBLE_JUMP_UNLOCKED = true;
					Player.coins -= Player.double_jump_price;
					timer = timerReset;
					//play sound
					Sound.shopDing.play(false);

				}

				//tab switching
				if (input.right) {
					background = BackGround.shop_background2;
					tabSelected = Tab.WEAPONS;
					timer = timerReset;
					itemNum = 1;
				}
			}
		}

		//weapons tab
		if (tabSelected == Tab.WEAPONS) {

			if (timer == 0) {

				//general tab updating
				//purchase potato gun
				if (itemNum == 1 && !Player.POTATO_GUN_UNLOCKED && Player.coins >= Player.potato_gun_price && input.enter) {
					Player.POTATO_GUN_UNLOCKED = true;
					Player.WEAPON_SELECTED = weapon.POTATO_GUN;
					Player.coins -= Player.potato_gun_price;
					timer = timerReset;
					//play sound
					Sound.shopDing.play(false);

				}
				//purchase shotgun
				if (itemNum == 2 && !Player.SHOTGUN_UNLOCKED && Player.coins >= Player.shotgun_price && input.enter) {
					Player.SHOTGUN_UNLOCKED = true;
					Player.WEAPON_SELECTED = weapon.SHOTGUN;
					Player.coins -= Player.shotgun_price;
					timer = timerReset;
					//play sound
					Sound.shopDing.play(false);

				}

				//tab switching start//
				if (input.left) {
					background = BackGround.shop_background1;
					tabSelected = Tab.GENERAL;
					timer = timerReset;
					itemNum = 1;
				}

				if (input.right) {
					background = BackGround.shop_background3;
					tabSelected = Tab.CLOTHING;
					timer = timerReset;
					itemNum = 1;
				}
			}
			//tab switching end//

		}

		//clothing tab
		if (tabSelected == Tab.CLOTHING) {

			if (timer == 0) {

				if (itemNum == 1 && !Player.TOP_HAT_UNLOCKED && Player.coins >= Player.top_hat_price && input.enter) {
					Player.TOP_HAT_UNLOCKED = true;
					Player.coins -= Player.top_hat_price;
					timer = timerReset;
					//play sound
					Sound.shopDing.play(false);

				}

				if (itemNum == 2 && !Player.ARMOR_UPGRADE1 && Player.coins >= Player.armor1_price && input.enter) {
					Player.ARMOR_UPGRADE1 = true;
					Player.coins -= Player.armor1_price;
					timer = timerReset;
					Player.health = 15;
					//play sound
					Sound.ArmorPurchase.play(false);
				}

				if (itemNum == 3 && Player.ARMOR_UPGRADE1 && !Player.ARMOR_UPGRADE2 && Player.coins >= Player.armor2_price && input.enter) {
					Player.ARMOR_UPGRADE2 = true;
					Player.coins -= Player.armor2_price;
					timer = timerReset;
					Player.health = 20;
					//play sound
					Sound.ArmorPurchase.play(false);
				}

				//tab switching start//
				if (input.left) {
					background = BackGround.shop_background2;
					tabSelected = Tab.WEAPONS;
					timer = timerReset;
				}
			}
			//tab switching end//

		}

		//exit button

	}

	public void render(Graphics g) {
		//render highlighting selected option
		if (itemNum == 1) {
			g.setColor(highterColor);
			g.drawRect(73, 100, 678, 87);

		}
		if (itemNum == 2) {
			g.setColor(highterColor);
			g.drawRect(73, 187, 678, 87);

		}
		if (itemNum == 3) {
			g.setColor(highterColor);
			g.drawRect(73, 274, 678, 87);

		}
		if (itemNum == 4) {
			g.setColor(highterColor);
			g.drawRect(73, 361, 678, 87);

		}

		if (tabSelected == Tab.GENERAL) {

			g.setColor(Color.WHITE);
			g.setFont(new Font("Veranda", Font.PLAIN, 20));
			g.drawString("General", 80, 85);

			g.setColor(Color.BLACK);
			g.drawString("Weapons", 165, 85);
			g.drawString("Clothing", 265, 85);

			//render all items and text
			if (Player.coins < Player.double_jump_price && !Player.DOUBLE_JUMP_UNLOCKED) {
				g.setColor(redColor);
				g.drawString("Total cost: " + Player.double_jump_price + " carrots", 180, 128);
			} else if (Player.coins >= Player.double_jump_price && !Player.DOUBLE_JUMP_UNLOCKED) {
				g.setColor(purpleColor);
				g.drawString("Total cost: " + Player.double_jump_price + " carrots", 180, 128);
			} else if (Player.DOUBLE_JUMP_UNLOCKED) {
				g.setColor(greenColor);
				g.drawString("double jump unlocked!", 180, 128);
			}

			g.setColor(blackColor);
			g.drawString("Double jump. Very usefull for getting to high places. To use just", 180, 150);
			g.drawString("hit up when your at the peak of your jump!", 180, 170);
		}

		if (tabSelected == Tab.WEAPONS) {

			g.setColor(Color.WHITE);
			g.setFont(new Font("Veranda", Font.PLAIN, 20));
			g.drawString("Weapons", 165, 85);

			g.setColor(Color.BLACK);
			g.drawString("General", 80, 85);
			g.drawString("Clothing", 265, 85);

			//render info
			//potato gun
			if (Player.coins < Player.potato_gun_price && !Player.POTATO_GUN_UNLOCKED) {
				g.setColor(redColor);
				g.drawString("Total cost: " + Player.potato_gun_price + " carrots", 180, 128);
			} else if (Player.coins >= Player.potato_gun_price && !Player.POTATO_GUN_UNLOCKED) {
				g.setColor(purpleColor);
				g.drawString("Total cost: " + Player.potato_gun_price + " carrots", 180, 128);
			} else if (Player.POTATO_GUN_UNLOCKED) {
				g.setColor(greenColor);
				g.drawString("weapon unlocked!", 180, 128);
			}

			g.setColor(blackColor);
			g.drawString("Potato gun, fires small fragments of potatos at a high fire rate,", 180, 150);
			g.drawString("medium damage.", 180, 170);

			//shotgun
			if (Player.coins < Player.shotgun_price && !Player.SHOTGUN_UNLOCKED) {
				g.setColor(redColor);
				g.drawString("Total cost: " + Player.shotgun_price + " carrots", 180, 218);
			} else if (Player.coins >= Player.shotgun_price && !Player.SHOTGUN_UNLOCKED) {
				g.setColor(purpleColor);
				g.drawString("Total cost: " + Player.shotgun_price + " carrots", 180, 218);
			} else if (Player.SHOTGUN_UNLOCKED) {
				g.setColor(greenColor);
				g.drawString("weapon unlocked!", 180, 218);
			}

			g.setColor(blackColor);
			g.drawString("Shotgun, fires a triple burst of deadly frozon pees. High damage", 180, 240);
			g.drawString("at close range but slow fire rate", 180, 260);

		}

		if (tabSelected == Tab.CLOTHING) {

			g.setColor(Color.WHITE);
			g.setFont(new Font("Veranda", Font.PLAIN, 20));

			g.setColor(Color.BLACK);
			g.drawString("General", 80, 85);
			g.drawString("Weapons", 165, 85);
			g.drawString("Clothing", 265, 85);

			//render all items and text
			//top hat
			if (Player.coins < Player.top_hat_price && !Player.TOP_HAT_UNLOCKED) {
				g.setColor(redColor);
				g.drawString("Total cost: " + Player.top_hat_price + " carrots", 180, 128);
			} else if (Player.coins >= Player.top_hat_price && !Player.TOP_HAT_UNLOCKED) {
				g.setColor(purpleColor);
				g.drawString("Total cost: " + Player.top_hat_price + " carrots", 180, 128);
			} else if (Player.TOP_HAT_UNLOCKED) {
				g.setColor(greenColor);
				g.drawString("Top hat unlocked!", 180, 128);
			}

			g.setColor(blackColor);
			g.drawString("Top hat. The best hat on the market!", 180, 150);

			//armor 1
			if (Player.coins < Player.armor1_price && !Player.ARMOR_UPGRADE1) {
				g.setColor(redColor);
				g.drawString("Total cost: " + Player.armor1_price + " carrots", 180, 218);
			} else if (Player.coins >= Player.armor1_price && !Player.ARMOR_UPGRADE1) {
				g.setColor(purpleColor);
				g.drawString("Total cost: " + Player.armor1_price + " carrots", 180, 218);
			} else if (Player.ARMOR_UPGRADE1) {
				g.setColor(greenColor);
				g.drawString("Thick skin 1 unlocked!", 180, 218);
			}

			g.setColor(blackColor);
			g.drawString("Thick skin. Provides you with extra resistance from all those", 180, 240);
			g.drawString("pesky vegatables!", 180, 260);

			//armor 1
			if (Player.coins < Player.armor2_price && !Player.ARMOR_UPGRADE2) {
				g.setColor(redColor);
				g.drawString("Total cost: " + Player.armor2_price + " carrots", 180, 308);
			} else if (Player.coins >= Player.armor2_price && !Player.ARMOR_UPGRADE2) {
				g.setColor(purpleColor);
				g.drawString("Total cost: " + Player.armor2_price + " carrots", 180, 308);
			} else if (Player.ARMOR_UPGRADE2) {
				g.setColor(greenColor);
				g.drawString("Thick skin 2 unlocked!", 180, 308);
			}

			g.setColor(blackColor);
			g.drawString("Thick skin V2. Provides even more resistance! Requires thick", 180, 330);
			g.drawString("skin V1 first.", 180, 350);

		}

	}

	//render all the sprites that display the items
	public void renderSprites(Screen screen) {

		if (tabSelected == Tab.GENERAL) {
			if (bobTimer % 80 >= 40) {
				screen.renderBasicSprite(30, 32, Sprite.kev_jump_right);
			} else {
				screen.renderBasicSprite(30, 33, Sprite.kev_jump_right);
			}

		}

		if (tabSelected == Tab.WEAPONS) {
			if (bobTimer % 80 >= 40) {
				screen.renderBasicSprite(30, 31, Sprite.potato_gun);
				screen.renderBasicAccurateSprite(18, 64, Sprite.shotgun);

			} else {
				screen.renderBasicSprite(30, 32, Sprite.potato_gun);
				screen.renderBasicAccurateSprite(18, 65, Sprite.shotgun);
			}

		}

		if (tabSelected == Tab.CLOTHING) {
			if (bobTimer % 80 >= 40) {
				screen.renderBasicSprite(30, 32, Sprite.kev_forward_hat);
				screen.renderBasicAccurateSprite(26, 60, Sprite.armor1);
				screen.renderBasicAccurateSprite(26, 90, Sprite.armor2);

			} else {
				screen.renderBasicSprite(30, 33, Sprite.kev_forward_hat);
				screen.renderBasicAccurateSprite(26, 61, Sprite.armor1);
				screen.renderBasicAccurateSprite(26, 91, Sprite.armor2);
			}

		}

	}

	public void renderBackground(Graphics g) {
		background.render(screen);

	}

}
