package Objects;

import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Entities.ObjectEntity;

public class ObjectsDisplayer {

	public void DisplayObjects(List<ObjectEntity> objectsList, int characterX, int characterY, GameContainer gc) throws SlickException {
		for(Iterator<ObjectEntity> i = objectsList.iterator(); i.hasNext(); ) {
			ObjectEntity object = i.next();
			Image image = new Image(object.PathToImage);
			image.draw((object.ObjectX - characterX) * 32+gc.getWidth() / 2, (object.ObjectY - characterY) * 32+gc.getHeight() / 2, 32, 32);
		}	
	}
}
