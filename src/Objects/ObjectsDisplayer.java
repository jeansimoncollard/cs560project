package Objects;

import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Entities.ObjectEntity;
import StartMain.MapResources;

public class ObjectsDisplayer {

	public void DisplayObjects(List<ObjectEntity> objectsList, int characterX, int characterY, GameContainer gc) throws SlickException {
		for(Iterator<ObjectEntity> i = objectsList.iterator(); i.hasNext(); ) {
			ObjectEntity object = i.next();
			Image image = new Image(object.PathToImage);
			image.draw((object.ObjectX - characterX) * 32+gc.getWidth() / 2, (object.ObjectY - characterY) * 32+gc.getHeight() / 2, 32, 32);
		}	
	}
	
	public void displayObjects_opt(List<ObjectEntity> objectsList, int characterX, int characterY, GameContainer gc) throws SlickException  {
		for (ObjectEntity object : objectsList) {
			if (object.img1 == null) {
				object.img.draw((object.ObjectX - characterX) * 32+gc.getWidth() / 2, (object.ObjectY - characterY) * 32+gc.getHeight() / 2, 32, 32);
			} else{
				if (MapResources.checkOverhead(object.ObjectX, object.ObjectY)) {
					object.img.draw((object.ObjectX - characterX) * 32+gc.getWidth() / 2, ((object.ObjectY - characterY) * 32+gc.getHeight() / 2)-32, 32, 32);
					object.img1.draw((object.ObjectX - characterX) * 32+gc.getWidth() / 2, (object.ObjectY - characterY) * 32+gc.getHeight() / 2, 32, 32);
				} else {
					object.img.draw((object.ObjectX - characterX) * 32+gc.getWidth() / 2, ((object.ObjectY - characterY) * 32+gc.getHeight() / 2)-32, 32, 32);
				}
			}
		}
	}
}
