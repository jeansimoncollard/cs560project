package NPC;

import java.util.Random;

import org.newdawn.slick.BigImage;

import Objects.NonPlayableCharacter;
import StartMain.ImageResources;
import StartMain.MapResources;

/**
 * An NPC that does nothing but run/walk around randomly. 
 * It's image and sprite sheet are loaded in ImageResources.
 * 
 * @author Greg
 *
 */
public class RandomMovementNPC extends NonPlayableCharacter {
	private static final String name = "ThingOne";
	private int totalCalls;			// Number of interactions
	private int initCallNum;		// Number of interactions to hit before an action occurs
	
	/**
	 * Constructor, calls NonPlayableCharacter's
	 * constructor for most of the work.
	 * @param x
	 * @param y
	 * @param maxX
	 * @param maxY
	 */
	public RandomMovementNPC(int x, int y, int maxX, int maxY, int interactType) {
		super(RandomMovementNPC.name, ImageResources.THING_ONE_SS, x, y, maxX, maxY, interactType);
		this.totalCalls = 0;
		this.initCallNum = 120;
	}
	
	/**
	 * This NPC simply runs around pointlessly.
	 * It has absolutely no other actions.
	 */
	public void interact() {
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
		
		// Set the new image based on the random movement made above.
		this.img = (BigImage) this.getSs().getSprite(currFrame, currRow);
		this.img1 = (BigImage) this.getSs().getSprite(currFrame, currRow+1);
	}
}
