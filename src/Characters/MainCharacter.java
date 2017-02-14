package Characters;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MainCharacter {
	
	public int XPosition, YPosition;
	public Image Image;
	
	public MainCharacter(){
		XPosition = 0;
		YPosition = 0;
	}

	
	public MainCharacter(int xPosition, int yPosition, String imagePath) throws SlickException{
		XPosition = xPosition;
		YPosition = yPosition;
		Image = new Image(imagePath);
	}

}
