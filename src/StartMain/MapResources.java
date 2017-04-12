package StartMain;

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
	private static BigTiledMap map;				// The map to walk around.
	private static int walkableLayer;			// The layer of the map to walk in.
	private static int overheadLayerIndex1;		// The first overhead layer.
    private static int overheadLayerIndex2;		// The second overhead layer.
    private static int groundCollisionIndex;
	
    /**
     * Load a map into the resources.
     * @param mapWalk
     */
	public static void loadResources(BigTiledMap mapWalk) {
		if (map == null) {
			map = mapWalk;
			walkableLayer = map.getLayerIndex("noCollision");
			overheadLayerIndex1 = map.getLayerIndex("characterOverhead1");
			overheadLayerIndex2 = map.getLayerIndex("roofsCharacterOverhead");
			groundCollisionIndex = map.getLayerIndex("groundCollision");
		}
	}
	
	/**
	 * Check if a given position can be walked to by ensuring the
	 * tile is defined and there are no collidables.
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean checkCollision(int x, int y) {
		return map.getTileId(x, y, walkableLayer) != 0 &&
        		map.getTileId(x, y, groundCollisionIndex) == 0;
	}
	
	/**
	 * Check if a given position has an overhead in
	 * some form.
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean checkOverhead(int x, int y) {
		return map.getTileId(x, y, overheadLayerIndex1) == 0
                && map.getTileId(x, y, overheadLayerIndex2) == 0;
	}
}
