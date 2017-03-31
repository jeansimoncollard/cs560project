package Objects;

import java.util.List;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TiledMap;

import Entities.ObjectEntity;
import GameState.GameStateMaster;
import StartMain.ImageResources;

/**
 * This class is used to generate objects
 * under certain conditions. For example, either
 * randomly or conditionally such a based on the current
 * state of the game.
 * 
 */

public class ObjectGenerater {
	// Path to the images used.
	private final String PATH_COIN_IMAGE = "dependencies/objects/retrocoin.png";
	private final String PATH_CLUE_IMAGE = "dependencies/objects/clue.png";

	/**
	 * Generate coins on the ground randomly, inserted
	 * into objectList so that they are displayed.
	 * @param objectsList
	 * @param characterX
	 * @param characterY
	 * @param map
	 */
	public void GenerateCoinsRandomly(List<ObjectEntity> objectsList, int characterX, int characterY, TiledMap map) {
		// Create a new coin.
		ObjectEntity coinObject = createObject(characterX, characterY, map, PATH_COIN_IMAGE, ImageResources.COIN, 50, Enums.ObjectType.Coin);
		
		// If it was correctly created, add it to the list.
		if (coinObject != null) {
			objectsList.add(coinObject);
		}
	}

	/**
	 * Generate a clue randomly and display
	 * it by adding it to the objectList.
	 * @param objectsList
	 * @param characterX
	 * @param characterY
	 * @param map
	 * @param gameStateMaster
	 */
	public void GenerateClueRandomly(List<ObjectEntity> objectsList, int characterX, int characterY, TiledMap map,
			GameStateMaster gameStateMaster) {
		// Create the clue.
		ObjectEntity clueObject = createObject(characterX, characterY, map, PATH_CLUE_IMAGE, ImageResources.CLUE, 15, Enums.ObjectType.Clue);

		// If it was correctly created, add it to the list
		// the state is changed so that there is only one clue
		// drop/
		if (clueObject != null) {
			objectsList.add(clueObject);
			gameStateMaster.GameState++;
		}
	}

	/**
	 * Create object at a random position according to probability
	 */
	private ObjectEntity createObject(int characterX, int characterY, TiledMap map, String pathToImage, Image img,
			int probabilityToCreate, Enums.ObjectType type) {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(probabilityToCreate);

		// Don't generate an object every time this is called, only one time out
		// of x
		if (randomInt == 1) {
			// Generate
			int objectX;
			int objectY;

			do {
				objectX = characterX + randomGenerator.nextInt(30) - 15;
				objectY = characterY + randomGenerator.nextInt(20) - 10;
			} while (objectCollidesWithTerrain(objectX, objectY, map));

			return new ObjectEntity(objectX, objectY, type, img);
		}
		return null;
	}

	/**
	 * Checks to see if the object collides with the map.
	 * @param objectX
	 * @param objectY
	 * @param map
	 * @return
	 */
	private Boolean objectCollidesWithTerrain(int objectX, int objectY, TiledMap map) {
		// Get layer indices
		int foregroundLayerIndex = map.getLayerIndex("noCollision"); 			// Walkable
		int overheadLayerIndex1 = map.getLayerIndex("characterOverhead1");		// Overhead layer
		int overheadLayerIndex2 = map.getLayerIndex("roofsCharacterOverhead");	// Overhead layer 2

		// Check bounds
		if (objectY >= map.getHeight()) {
			return true;
		}
		if (objectY < 0) {
			return true;
		}

		if (objectX >= map.getWidth()) {
			return true;
		}
		if (objectX < 0) {
			return true;
		}

		// Check for walkable and overhead.
		Boolean isTileWalkable = map.getTileId(objectX, objectY, foregroundLayerIndex) != 0;
		Boolean doesTileHaveOverhead = map.getTileId(objectX, objectY, overheadLayerIndex1) != 0
				|| map.getTileId(objectX, objectY, overheadLayerIndex2) != 0;

		// Will not spawn under roof or something similar
		if (!isTileWalkable) {
			return true;
		}
		// Will not spawn on unreachable terrain
		if (doesTileHaveOverhead) {
			return true;
		}

		return false;
	}

}
