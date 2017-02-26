package Objects;

import java.util.List;
import org.newdawn.slick.tiled.TiledMap;
import java.util.Random;

import Entities.ObjectEntity;

public class ObjectGenerater {

	private final String PATH_COIN_IMAGE = "dependencies/objects/retrocoin.png";

	public void GenerateCoinsRandomly(List<ObjectEntity> objectsList, int characterX, int characterY, TiledMap map) {

		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(30);

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

			objectsList.add(new ObjectEntity(objectX, objectY, Enums.ObjectType.Coin, PATH_COIN_IMAGE));
		}

	}

	private Boolean objectCollidesWithTerrain(int objectX, int objectY, TiledMap map) {
		int foregroundLayerIndex = map.getLayerIndex("noCollision");
		int overheadLayerIndex1 = map.getLayerIndex("characterOverhead1");
		int overheadLayerIndex2 = map.getLayerIndex("roofsCharacterOverhead");
		
		if(objectY >= map.getHeight()){
			return true;
		}
		if(objectY < 0){
			return true;
		}
		
		if(objectX >= map.getWidth()){
			return true;
		}
		if(objectX < 0){
			return true;
		}
		
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
