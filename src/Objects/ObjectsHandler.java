package Objects;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import Characters.MainCharacter;
import Entities.ObjectEntity;
import Enums.ObjectType;
import GameState.GameStateMaster;
import StartMain.BigTiledMap;

/**
 * This class handles everything that objects do.
 * Displays them, interacts with them, generates them,
 * etc..
 *
 */

public class ObjectsHandler {
	// List of objects currently on the map. 
	private List<ObjectEntity> _objectsList;
	
	// The object generator which creates simple
	// objects and inserts them into the _objectList.
	private ObjectGenerater _objectGenerator;
	
	// Displays objects in the _objectList.
	private ObjectsDisplayer _objectDisplayer;
	
	// Picks up objects in the _objecList.
	private ObjectsPickuper _objectsPickuper;

	// Previous character position.
	private int oldCharacterXPosition;
	private int oldCharacterYPosition;

	/**
	 * Default constructor which initializes all
	 * the objects needed to be handled.
	 */
	public ObjectsHandler() {
		_objectsList = new ArrayList<ObjectEntity>();
		_objectGenerator = new ObjectGenerater();
		_objectDisplayer = new ObjectsDisplayer();
		_objectsPickuper = new ObjectsPickuper();
	}

	/**
	 * Handles all the object interactions and processes
	 * like displaying, generating, interacting.
	 * @param character
	 * @param map
	 * @param gc
	 * @param gameStateMaster
	 * @throws SlickException
	 */
	public void HandleObjects(MainCharacter character, BigTiledMap map, GameContainer gc, GameStateMaster gameStateMaster) throws SlickException {

		int characterX = character.XPosition;
		int characterY = character.YPosition;

		// If the character moved, generate objects on the floor,
		// pick them up if the character is on them, and finally update the old positions.
		if (oldCharacterXPosition != characterX || oldCharacterYPosition != characterY) {
			createObjects(characterX, characterY, map);
			pickupObjects(character, _objectsList, gameStateMaster);
			oldCharacterXPosition = characterX;
			oldCharacterYPosition = characterY;
		}
		
		// Interact with the objects (NPCs).
		interactObjects(character, _objectsList, gameStateMaster, gc);
		
		// Display the objects on the map.
		displayObjects(characterX, characterY, gc);
	}

	/**
	 * Generate the clue randomly on the ground
	 * when the character moves.
	 * @param characterX
	 * @param characterY
	 * @param map
	 * @param gameStateMaster
	 * @throws SlickException
	 */
	public void GenerateClueItem(int characterX, int characterY, BigTiledMap map, GameStateMaster gameStateMaster)
			throws SlickException {

		_objectGenerator.GenerateClueRandomly(_objectsList, characterX, characterY, map, gameStateMaster);
	}

	/**
	 * Create objects randomly when the character moves.
	 * @param characterX
	 * @param characterY
	 * @param map
	 */
	private void createObjects(int characterX, int characterY, BigTiledMap map) {
		_objectGenerator.GenerateCoinsRandomly(_objectsList, characterX, characterY, map);
	}
	
	/**
	 * Display the objects on the map.
	 * @param characterX
	 * @param characterY
	 * @param gc
	 * @throws SlickException
	 */
	private void displayObjects(int characterX, int characterY, GameContainer gc) throws SlickException {
		_objectDisplayer.displayObjects_opt(_objectsList, characterX, characterY, gc);
	}

	/**
	 * Pick up the objects if the character is above it.
	 * @param character
	 * @param objectsList
	 * @param gameStateMaster
	 */
	private void pickupObjects(MainCharacter character, List<ObjectEntity> objectsList,
			GameStateMaster gameStateMaster) {
		_objectsPickuper.PickupObjects(character, objectsList, gameStateMaster);
	}
	
	/**
	 * Interact with the objects in either a normal or
	 * complex manner.
	 * @param character
	 * @param objectsList
	 * @param gameStateMaster
	 * @param gc
	 */
	private void interactObjects(MainCharacter character, List<ObjectEntity> objectsList,
			GameStateMaster gameStateMaster, GameContainer gc) {
		for (ObjectEntity i : objectsList) {
			if (i.interactType == ObjectEntity.NORMAL_INTERACT) {
				i.interact();
			} else if (i.interactType == ObjectEntity.COMPLEX_INTERACT) { // Perform a complex interact
				i.interact(character, gameStateMaster, gc);
			}
		}
	}
	
	/**
	 * Add an object to the objectList if it's
	 * type is defined in the enumeration ObjectType.
	 * @param o
	 * @return
	 */
	public boolean addObject(ObjectEntity o) {
		System.out.println("Here start");
		for (ObjectType i : ObjectType.values()) {
			if (o.objectType == i) {
				this._objectsList.add(o);
				return true;
			}
		}
		System.err.println("ERROR: Invalid object being added to object list for ObjectsHandler.");
		return false;
	}
}
