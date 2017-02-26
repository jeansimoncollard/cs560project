package Objects;

import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.Image;

import Characters.MainCharacter;
import Entities.ObjectEntity;

public class ObjectsPickuper {

	public void PickupObjects(MainCharacter character, List<ObjectEntity> objectsList) {
		for (Iterator<ObjectEntity> i = objectsList.iterator(); i.hasNext();) {
			ObjectEntity object = i.next();

			if (object.ObjectX == character.XPosition && object.ObjectY == character.YPosition) {
				character.CoinCount++;
				i.remove();
			}

		}

	}

}
