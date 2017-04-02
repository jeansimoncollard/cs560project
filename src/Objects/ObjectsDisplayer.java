package Objects;

import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Entities.ObjectEntity;
import StartMain.MapResources;

/**
 * This class takes care of displaying the different objects in
 * the objectsList. It displays NPCs, coins, and clues so far.
 *
 */

public class ObjectsDisplayer {

	/**
	 * Old display objects method that is currently 
	 * not in use and replaced in favor of the optimized
	 * version below. Left here in case it could be useful
	 * for simpler processing if objectsList is ever split.
	 * @param objectsList
	 * @param characterX
	 * @param characterY
	 * @param gc
	 * @throws SlickException
	 */
	public void DisplayObjects(List<ObjectEntity> objectsList, int characterX, int characterY, GameContainer gc) throws SlickException {
		for(Iterator<ObjectEntity> i = objectsList.iterator(); i.hasNext(); ) {
			ObjectEntity object = i.next();
			Image image = new Image(object.PathToImage);
			image.draw((object.ObjectX - characterX) * 32+gc.getWidth() / 2, (object.ObjectY - characterY) * 32+gc.getHeight() / 2, 32, 32);
		}	
	}
	
	/**
	 * Optimized display method for objects in the objectsList
	 * containing objects enumerated in Enums.ObjectType. If img1
	 * if null it means that either this is not a sprite, or it is
	 * a simple image to display like a Coin.
	 * 
	 * @param objectsList
	 * @param characterX
	 * @param characterY
	 * @param gc
	 * @throws SlickException
	 */
	public void displayObjects_opt(List<ObjectEntity> objectsList, int characterX, int characterY, GameContainer gc) throws SlickException  {
		for (ObjectEntity object : objectsList) {
			if (object.img != null) {
				if (object.img1 == null) { // Display Coin-like object.
					object.img.draw((object.ObjectX - characterX) * 32+gc.getWidth() / 2, (object.ObjectY - characterY) * 32+gc.getHeight() / 2, 32, 32);
				} else{	// Display NPC or two-cell image. 
					if (MapResources.checkOverhead(object.ObjectX, object.ObjectY)) { // Check the overhead for how to draw the two-cell image.
						object.img.draw((object.ObjectX - characterX) * 32+gc.getWidth() / 2, ((object.ObjectY - characterY) * 32+gc.getHeight() / 2)-32, 32, 32);
						object.img1.draw((object.ObjectX - characterX) * 32+gc.getWidth() / 2, (object.ObjectY - characterY) * 32+gc.getHeight() / 2, 32, 32);
					} else {	// Only draw the top if there is something in the way.
						object.img.draw((object.ObjectX - characterX) * 32+gc.getWidth() / 2, ((object.ObjectY - characterY) * 32+gc.getHeight() / 2)-32, 32, 32);
					}
				}
			}
		}
	}

}
