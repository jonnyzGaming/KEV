package jonny.main.entity.Objects;

import java.awt.Rectangle;

import jonny.main.Game;
import jonny.main.entity.mob.Player;
import jonny.main.graphics.Sprite;

public class movingPlatform extends Solid_Object {

	//NOTE TO SELF, DONT MESS WITH PLAT UPDOWN CODE ITS TO ANNOYING
	//general variables
	private double moveSpeed; //needs to be less than 1 as is now : - |
	private int moveLength; //from the middle eg end------length-------end
	private int halfMoveLength;

	private int moveDelayLength; //length it stops at each end.
	private int moveDelayTimer = 0;
	private int direction; //0 left,1 right,2up,3 down
	public boolean notMoving;

	//set these booleans when creating the object
	public boolean leftRight = false;
	private int leftEnd;
	private int rightEnd;

	public boolean upDown = false;
	private int upEnd;
	private int bottomEnd;

	public boolean sequence = false;

	public int[] sequenceDir = new int[100];

	//for basic left right or up down platforms
	public movingPlatform(int x, int y, Sprite sprite, int cWidth, int cHeight, int moveLength, int moveDelayLength, double moveSpeed, int initialDir) {
		super(x, y, sprite, cWidth, cHeight);

		this.moveSpeed = moveSpeed;
		this.moveLength = moveLength;
		halfMoveLength = moveLength / 2;

		//for left right platforms
		leftEnd = (int) x - halfMoveLength;
		rightEnd = (int) x + halfMoveLength;

		//for up down platforms
		upEnd = (int) y - halfMoveLength;
		bottomEnd = (int) y + halfMoveLength;

		this.moveDelayLength = moveDelayLength;
		direction = initialDir;

		solid = true;
	}

	public void update() {

		shouldReturn = false;
		move();

		if (shouldReturn) return;

		//consider collision with the player(eg move the player if he is on the platform/ at side
		if (onScreen) {

			//all increased by one for player collision
			Rectangle colRec = new Rectangle((int) x + xTrans - 1, (int) y + yTrans - 3, cWidth + 1, cHeight + 3);
			Rectangle colRec2 = new Rectangle((int) x + xTrans - 1, (int) y + yTrans - 3, cWidth + 1, cHeight + 3);
			Rectangle RealColRec = new Rectangle((int) x + xTrans, (int) y + yTrans, cWidth, cHeight);

			//all types of collision affecting the player
			if (leftRight && direction == 0 && entityCollision(Game.player, colRec)) {
				Player.onMovingTile = true;
				if (!notMoving && !Game.player.collision((int) -moveSpeed, 0)) Game.player.x -= moveSpeed;
			}

			if (leftRight && direction == 1 && entityCollision(Game.player, colRec)) {
				Player.onMovingTile = true;
				if (!notMoving && !Game.player.collision((int) moveSpeed, 0)) Game.player.x += moveSpeed;
			}

			if (upDown && direction == 3 && entityCollision(Game.player, colRec)) {
				Player.onMovingTile = true;
				if (!notMoving && !Game.player.collision(0, (int) -moveSpeed)) {
					Game.player.y -= moveSpeed;
				}
				//if we are on the tile and not moving then put the y of player to the top
				if (notMoving) Game.player.y -= 1;
			}

			if (upDown && direction == 4 && entityCollision(Game.player, colRec2)) {
				Player.onMovingTile = true;

			}

			//if the player has been crushed by the moving tile then they die
			if (entityCollision(Game.player, RealColRec)) {

				Game.player.health = 0;

			}

		}

	}

	public boolean shouldReturn = false;
	public void move() {

		//basic left right platform movement
		//moving left
		if (direction == 0 && leftRight) {

			if ((int) x - (int) moveSpeed != leftEnd && !notMoving) {
				x -= moveSpeed;
			}
			else {
				moveDelayTimer++;
				notMoving = true;
				if (moveDelayTimer == moveDelayLength) {
					direction = 1;
					moveDelayTimer = 0;
					notMoving = false;
					shouldReturn = true;
					return;
				}
			}

		}

		//moving right
		if (direction == 1 && leftRight) {

			if ((int) x + (int) moveSpeed != rightEnd && !notMoving) {
				x += moveSpeed;
			}
			else {
				moveDelayTimer++;
				notMoving = true;
				if (moveDelayTimer == moveDelayLength) {
					direction = 0;
					moveDelayTimer = 0;
					notMoving = false;
					shouldReturn = true;
					return;
				}
			}

		}

		//moving up
		if (direction == 3 && upDown) {

			if ((int) y - (int) moveSpeed != upEnd && !notMoving) {
				y -= moveSpeed;
			}
			else {
				moveDelayTimer++;
				notMoving = true;

				if (moveDelayTimer == moveDelayLength) {
					direction = 4;
					moveDelayTimer = 0;
					notMoving = false;
					shouldReturn = true;
					return;
				}
			}

		}

		//moving down
		if (direction == 4 && upDown) {

			if ((int) y + (int) moveSpeed != bottomEnd && !notMoving) {
				y += moveSpeed;
			}
			else {
				moveDelayTimer++;
				notMoving = true;
				if (moveDelayTimer == moveDelayLength) {
					direction = 3;
					moveDelayTimer = 0;
					notMoving = false;
					shouldReturn = true;
					return;
				}
			}

		}

	}
}
