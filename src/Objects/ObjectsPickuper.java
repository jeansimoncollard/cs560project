package Objects;

import java.util.Iterator;
import java.util.List;

import Characters.MainCharacter;
import Entities.ObjectEntity;
import Enums.ObjectType;
import GameState.GameStateMaster;

/**
 *  This class handles which objects can be picked up. Currently,
 *  only clues and coins have that capability and it is triggered
 *  when a character walks over the object in question. It is used
 *  in the ObjectHandler.
 */
public class ObjectsPickuper {

	/**
	 * Picks up the objects and updates accordingly.
	 * @param character
	 * @param objectsList
	 * @param gameStateMaster
	 */
	public void PickupObjects(MainCharacter character, List<ObjectEntity> objectsList, GameStateMaster gameStateMaster) {
		for (Iterator<ObjectEntity> i = objectsList.iterator(); i.hasNext();) {
			ObjectEntity object = i.next();
			
			// If were interacting with something that can be picked up.
			if (object.objectType != ObjectType.NPC) {
				// If we are over the item
				if (object.ObjectX == character.XPosition && object.ObjectY == character.YPosition) {
					// Perform that items specific action
					if (object.objectType == ObjectType.Coin) { // If we found a coin, add 1 coin to the character.
						character.setCoinCount(character.getCoinCount() + character.getCoinWorth());
					} else if (object.objectType == ObjectType.Clue) { // If we found a clue, change the game state.	
						gameStateMaster.GameState++;
					}
					// Remove the object from the list to prevent it
					// from displaying.
					i.remove();
				}
			}

		}

	}

}
