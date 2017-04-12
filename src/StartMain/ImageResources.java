package StartMain;
import org.newdawn.slick.BigImage;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Use this class to load images that are needed for multiple items.
 * It makes it easier to use them later on, and also makes the game
 * just a bit faster by deferring loading to the beginning of the
 * game during the main menu screen.
 * 
 * @author Gregory
 *
 */

public class ImageResources {
	public static BigImage COIN;				// Image for a coin
	public static BigImage CLUE;				// Image for a clue
	public static BigImage THING_ONE;			// Image for NPC ThingOne
	public static SpriteSheet THING_ONE_SS; // Image for NPC ThingTwo
	public static BigImage CONGRATS_SCREEN;
	public static BigImage CONGRATS_SCREEN_FR;
	public static SpriteSheet NELLY_SS;
	public static SpriteSheet MAZEHIDER_SS;
	
	/**
	 * Load up the needed images and sprite sheets.
	 */
	public static void loadReources() {
		try {
			COIN = new BigImage("dependencies/objects/retrocoin.png");
			CLUE = new BigImage("dependencies/objects/clue.png");
			THING_ONE = new BigImage("dependencies/objects/thingone_npc.png");
			THING_ONE_SS = new SpriteSheet(THING_ONE, 32, 32);
			NELLY_SS = new SpriteSheet(new BigImage("dependencies/objects/nelly_npc.png"), 32, 32);
			MAZEHIDER_SS = new SpriteSheet(new BigImage("dependencies/objects/mazehider_sprites.png"), 32, 32);
			CONGRATS_SCREEN = new BigImage("dependencies/UI_photos/congrats_screen_transparent.png");
			CONGRATS_SCREEN_FR = new BigImage("dependencies/UI_photos/congrats_screen_transparent_fr.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
