package NPC;

import java.util.Random;

import Entities.ObjectEntity;
import Objects.NonPlayableCharacter;
import StartMain.ImageResources;
import StartMain.MapResources;

/**
 * An NPC that does nothing. Load it's image here or in
 * ImageResources which is the suggested route.
 * 
 * @author Greg
 *
 */
public class ThingOne extends NonPlayableCharacter {
	private static final String name = "ThingOne";
	private int totalCalls;
	private int initCallNum;
	
	public ThingOne(int x, int y, int maxX, int maxY) {
		super(ThingOne.name, ImageResources.THING_ONE_SS, x, y, maxX, maxY, ObjectEntity.NORMAL_INTERACT);
		this.totalCalls = 0;
		this.initCallNum = 120;
	}
	
	public void interact() {
		if (this.totalCalls < this.initCallNum) {
			this.totalCalls++;
			return;
		} else {
			this.totalCalls = 0;
		}
		
		Random r = new Random();
		int moveVar = r.nextInt(101)+1;
		int rand_moveDirection = moveVar%4;
		int currRow = 0;
		int currFrame = 0;
		
		switch(rand_moveDirection) {
			case 0:  // Move Down
				if (this.ObjectY != this.getyMax() && 
					MapResources.checkCollision(this.ObjectX, this.ObjectY+1)){
					this.ObjectY++;
					currFrame = (this.ObjectY%4);
					currRow = 0;
				}
				break;
			case 1: // Move Left
				if (this.ObjectX != 0 &&
					MapResources.checkCollision(this.ObjectX-1, this.ObjectY)){
					this.ObjectX--;
					currFrame = (this.ObjectX%4);
					currRow = 2;
				}
				break;
			case 2: // Move Right
				if (this.ObjectX != this.getxMax() &&
					MapResources.checkCollision(this.ObjectX+1, this.ObjectY)){
					this.ObjectX++;
					currFrame = (this.ObjectX%4);
					currRow = 4;
				}
				break;
			case 3: // Move Up
				if (this.ObjectY != 0 &&
					MapResources.checkCollision(this.ObjectX, this.ObjectY-1)){
					this.ObjectY--;
					currFrame = (this.ObjectY%4);
					currRow = 6;
				}
				break;
			default:
				System.err.println("Random move direction is incorrect.");
				break;
		}
		
		this.img = this.getSs().getSprite(currFrame, currRow);
		this.img1 = this.getSs().getSprite(currFrame, currRow+1);
	}
}
