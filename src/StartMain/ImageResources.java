package StartMain;
import org.newdawn.slick.Image;
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
	public static Image COIN;				// Image for a coin
	public static Image CLUE;				// Image for a clue
	public static Image THING_ONE;			// Image for NPC ThingOne
	public static SpriteSheet THING_ONE_SS; // Image for NPC ThingTwo
	public static Image CONGRATS_SCREEN;
	public static Image CONGRATS_SCREEN_FR;
	
	/**
	 * Load up the needed images and sprite sheets.
	 */
	public static void loadReources() {
		try {
			COIN = new Image("dependencies/objects/retrocoin.png");
			CLUE = new Image("dependencies/objects/clue.png");
			THING_ONE = new Image("dependencies/objects/thingone_npc.png");
			THING_ONE_SS = new SpriteSheet(THING_ONE, 32, 32);
			CONGRATS_SCREEN = new Image("dependencies/UI_photos/congrats_screen_transparent.png");
			CONGRATS_SCREEN_FR = new Image("dependencies/UI_photos/congrats_screen_transparent_fr.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
