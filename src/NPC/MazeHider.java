package NPC;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import Characters.MainCharacter;
import GameState.GameStateMaster;
import Objects.NonPlayableCharacter;
import StartMain.ImageResources;
import StartMain.MapResources;
import StartMain.StringResources;

// Number of interactions
public class MazeHider extends NonPlayableCharacter {
	private static String name = "Harry";
	private int initCallNum;		// Number of interactions to hit before an action occurs
	private int totalCalls;
	private boolean addedStrings = false;
	private boolean initiated = false;
	
	// Set these to get different sprites from the sprite sheet.
	private int spriteColOffset = 0;
	private int spriteRowOffset = 0;
	private boolean given = false;
	
	/**
	 * Constructor, calls NonPlayableCharacter's
	 * constructor for most of the work.
	 * @param x
	 * @param y
	 * @param maxX
	 * @param maxY
	 */
	public MazeHider(int x, int y, int maxX, int maxY, int interactType) {
		super(MazeHider.name, ImageResources.MAZEHIDER_SS, x, y, maxX, maxY, interactType);
		this.totalCalls = 0;
		this.initCallNum = 60;
		this.img = null;
		this.textPages = new ArrayList<ArrayList<String>>();
		this.textPages.add(new ArrayList<String>());
		this.textPages.add(new ArrayList<String>());
	}
	
	public void initiatePostGame(boolean initiate) {
		if (this.initiated == false) {
			this.initiated = initiate;
		}
	}
	
	/**
	 * This NPC runs around the maze in the forest after
	 * the game is finished. It will open a dialogue and maybe send
	 * the character on another little quest.
	 */
	public void interact(MainCharacter mc, GameStateMaster gm, GameContainer gc) {
		if (!addedStrings) {
			if (StringResources.messages != null) {
				addedStrings = true;
				this.textPages.get(0).add(StringResources.messages.getString("mazehider1Page1Line1"));
				this.textPages.get(0).add(StringResources.messages.getString("mazehider1Page1Line2"));
				this.textPages.get(0).add(StringResources.messages.getString("mazehider1Page1Line3"));
				this.textPages.get(1).add(StringResources.messages.getString("mazehider1Page2Line1"));
				this.textPages.get(1).add(StringResources.messages.getString("mazehider1Page2Line2"));
				this.textPages.get(1).add(StringResources.messages.getString("mazehider1Page2Line3"));
			}
		}
		
		if (this.initiated) {
			// This needs to be done before the call to prevent flash.
			if (isCharacterNear(mc)) {
				// When the user talks to this NPC, give them 500 coins
				// on the first time.
				if (gc.getInput().isKeyPressed(Input.KEY_SPACE)){
					this.displayTextBox();
					if (!this.given) {
						mc.setCoinCount(mc.getCoinCount() + 500);
						this.given = true;
					}
				}
			}

			// This is used to prevent the NPC from updating every frame
			// and flying around at a billion miles per hour.
			if (this.totalCalls < this.initCallNum) {
				this.totalCalls++;
				return;
			} else {
				this.totalCalls = 0;
			}
			
			// Find a random direction to move in
			Random r = new Random();
			int moveVar = r.nextInt(101)+1;
			int rand_moveDirection = moveVar%5;
			int currFrame = 0;
			int currRow = 0;
			
			// Move a random direction
			switch(rand_moveDirection) {
				case 0:  // Move Down if we don't collide and we aren't at the limit.
					if (this.ObjectY != this.getyMax() && 
						MapResources.checkCollision(this.ObjectX, this.ObjectY+1)){
						this.ObjectY++;
						currFrame = (this.ObjectY%4);
						currRow = 0;
					}
					break;
				case 1: // Move Left if we don't collide and we aren't at the limit.
					if (this.ObjectX != 0 &&
						MapResources.checkCollision(this.ObjectX-1, this.ObjectY)){
						this.ObjectX--;
						currFrame = (this.ObjectX%4);
						currRow = 2;
					}
					break;
				case 2: // Move Right if we don't collide and we aren't at the limit.
					if (this.ObjectX != this.getxMax() &&
						MapResources.checkCollision(this.ObjectX+1, this.ObjectY)){
						this.ObjectX++;
						currFrame = (this.ObjectX%4);
						currRow = 4;
					}
					break;
				case 3: // Move Up if we don't collide and we aren't at the limit.
					if (this.ObjectY != 0 &&
						MapResources.checkCollision(this.ObjectX, this.ObjectY-1)){
						this.ObjectY--;
						currFrame = (this.ObjectY%4);
						currRow = 6;
					}
					break;
				case 4:	// Don't move and stay in one spot.
					return;
				default:
					System.err.println("Random move direction is incorrect.");
					break;
			}
			
			/**
			 * This is a 3 frame sprite sheet
			 * so deal with this here.
			 */
			if (currFrame == 2) {
				currFrame = 0;
			} else if (currFrame == 3) {
				currFrame = 2;
			}
			
			// Set the new image based on the random movement made above.
			this.img = this.getSs().getSprite(currFrame, currRow);
			this.img1 = this.getSs().getSprite(currFrame, currRow+1);
		} else {
			this.img = null;
		}
	}
}
