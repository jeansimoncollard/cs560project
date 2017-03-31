package StartMain;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ImageResources {
	public static Image COIN;
	public static Image CLUE;
	public static Image THING_ONE;
	public static SpriteSheet THING_ONE_SS;
	
	public static void loadReources() {
		try {
			COIN = new Image("dependencies/objects/retrocoin.png");
			CLUE = new Image("dependencies/objects/clue.png");
			THING_ONE = new Image("dependencies/objects/thingone_npc.png");
			THING_ONE_SS = new SpriteSheet(THING_ONE, 32, 32);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
