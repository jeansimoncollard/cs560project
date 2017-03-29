package Objects;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import Characters.MainCharacter;
import Entities.ObjectEntity;
import GameState.GameStateMaster;

public class ObjectsHandler {

	private List<ObjectEntity> _objectsList;
	private ObjectGenerater _objectGenerator;
	private ObjectsDisplayer _objectDisplayer;
	private ObjectsPickuper _objectsPickuper;

	private int oldCharacterXPosition;
	private int oldCharacterYPosition;

	public ObjectsHandler() {
		_objectsList = new ArrayList<ObjectEntity>();
		_objectGenerator = new ObjectGenerater();
		_objectDisplayer = new ObjectsDisplayer();
		_objectsPickuper = new ObjectsPickuper();
	}

	public void HandleObjects(MainCharacter character, TiledMap map, GameContainer gc, GameStateMaster gameStateMaster) throws SlickException {

		int characterX = character.XPosition;
		int characterY = character.YPosition;

		if (oldCharacterXPosition != characterX || oldCharacterYPosition != characterY) {
			// Only execute the code when the character moved
			createObjects(characterX, characterY, map);
			pickupObjects(character, _objectsList, gameStateMaster);
			interactObjects(character, _objectsList, gameStateMaster);
			oldCharacterXPosition = characterX;
			oldCharacterYPosition = characterY;
		}
		displayObjects(characterX, characterY, gc);
	}

	public void GenerateClueItem(int characterX, int characterY, TiledMap map, GameStateMaster gameStateMaster)
			throws SlickException {

		_objectGenerator.GenerateClueRandomly(_objectsList, characterX, characterY, map, gameStateMaster);
	}

	private void createObjects(int characterX, int characterY, TiledMap map) {
		_objectGenerator.GenerateCoinsRandomly(_objectsList, characterX, characterY, map);
	}

	private void displayObjects(int characterX, int characterY, GameContainer gc) throws SlickException {
		_objectDisplayer.DisplayObjects(_objectsList, characterX, characterY, gc);
	}

	private void pickupObjects(MainCharacter character, List<ObjectEntity> objectsList,
			GameStateMaster gameStateMaster) {
		_objectsPickuper.PickupObjects(character, objectsList, gameStateMaster);
	}
	
	private void interactObjects(MainCharacter character, List<ObjectEntity> objectsList,
			GameStateMaster gameStateMaster) {
		for (ObjectEntity i : objectsList) {
			if (i.interactType == ObjectEntity.NORMAL_INTERACT) {
				i.interact();
			} else if (i.interactType == ObjectEntity.COMPLEX_INTERACT) { // Perform a complex interact
				i.interact(character, gameStateMaster);
			}
		}
	}
}
