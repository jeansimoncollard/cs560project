package Objects;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import Entities.ObjectEntity;

public class ObjectsHandler {

	private List<ObjectEntity> _objectsList;
	private ObjectGenerater _objectGenerator;
	private ObjectsDisplayer _objectDisplayer;

	private int oldCharacterXPosition;
	private int oldCharacterYPosition;

	public ObjectsHandler() {
		_objectsList = new ArrayList<ObjectEntity>();
		_objectGenerator = new ObjectGenerater();
		_objectDisplayer = new ObjectsDisplayer();
	}

	public void HandleObjects(int characterX, int characterY, TiledMap map, GameContainer gc) throws SlickException {
		if (oldCharacterXPosition != characterX || oldCharacterYPosition != characterY) {
			// Only execute the code when the character moved
			createObjects(characterX, characterY, map);
			oldCharacterXPosition = characterX;
			oldCharacterYPosition = characterY;
		}
		displayObjects(characterX, characterY, gc);
	}

	private void createObjects(int characterX, int characterY, TiledMap map) {
		_objectGenerator.GenerateCoinsRandomly(_objectsList, characterX, characterY, map);
	}

	private void displayObjects(int characterX, int characterY, GameContainer gc) throws SlickException {
		_objectDisplayer.DisplayObjects(_objectsList, characterX, characterY, gc);
	}
}
