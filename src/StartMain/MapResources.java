package StartMain;

import org.newdawn.slick.tiled.TiledMap;

/**
 * This class contains the actual map and gives access to different
 * functionality of the map to the rest of the game.
 * 
 * Currently, it is needed to determine the index of the non-collidable
 * layer for NPCs without having to pass the map through all sorts of objects.
 * 
 * IMPORTANT: Do not allow the map to be accessed explicitly to keep it
 * 			  away from outside manipulation. Only provide getters, or setters
 * 			  to restrict this access as much as possible.
 * 
 * @author Greg
 * 
 */

public class MapResources {
	private static TiledMap map;
	private static int walkableLayer;
	private static int overheadLayerIndex1;
    private static int overheadLayerIndex2;
	
	public static void loadResources(TiledMap mapWalk) {
		if (map == null) {
			map = mapWalk;
			walkableLayer = map.getLayerIndex("noCollision");
			overheadLayerIndex1 = map.getLayerIndex("characterOverhead1");
			overheadLayerIndex2 = map.getLayerIndex("roofsCharacterOverhead");
		}
	}
	
	public static boolean checkCollision(int x, int y) {
		return map.getTileId(x, y, walkableLayer) != 0;
	}
	
	public static boolean checkOverhead(int x, int y) {
		return map.getTileId(x, y, overheadLayerIndex1) == 0
                && map.getTileId(x, y, overheadLayerIndex2) == 0;
	}
}
