import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageResources {
	public static Image SHOP_BG;
	public static Image SHOP_SPEED_BTN;
	public static Image SHOP_Q1_BTN;
	public static Image SHOP_Q2_BTN;
	
	public void loadReources() {
		try {
			SHOP_BG = new Image("dependencies/UI_photos/shop_menu_bg.png");
			SHOP_SPEED_BTN = new Image("dependencies/UI_photos/speed_button_50.png");
			SHOP_Q1_BTN = new Image("dependencies/UI_photos/questions_button1_150.png");
			SHOP_Q2_BTN = new Image("dependencies/UI_photos/questions_button1_150.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
