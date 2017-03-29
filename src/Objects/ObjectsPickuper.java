package Objects;

import java.util.Iterator;
import java.util.List;

import Characters.MainCharacter;
import Entities.ObjectEntity;
import Enums.ObjectType;
import GameState.GameStateMaster;

public class ObjectsPickuper {

	public void PickupObjects(MainCharacter character, List<ObjectEntity> objectsList, GameStateMaster gameStateMaster) {
		for (Iterator<ObjectEntity> i = objectsList.iterator(); i.hasNext();) {
			ObjectEntity object = i.next();
			
			// If were interacting with something that can be picked up.
			if (object.ObjectType == ObjectType.Clue || object.ObjectType == ObjectType.Clue) {
				if (object.ObjectX == character.XPosition && object.ObjectY == character.YPosition) {
	
					if (object.ObjectType == ObjectType.Coin) {
						character.setCoinCount(character.getCoinCount() + character.getCoinWorth());
					} else if (object.ObjectType == ObjectType.Clue) {				
						gameStateMaster.GameState++;
					}
	
					i.remove();
				}
			}

		}

	}

}
