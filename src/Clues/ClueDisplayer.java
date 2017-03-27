package Clues;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class ClueDisplayer {

	private Boolean _renderClueToogle;
	public Image CurrentClueImage;

	public ClueDisplayer() {
		_renderClueToogle = false;
	}

	public void DisplayCurrentClue() {
		_renderClueToogle = true;
	}

	public void RenderClue(GameContainer gc, Graphics g) {
		if (gc.getInput().isKeyPressed(Input.KEY_C)) {
			_renderClueToogle = !_renderClueToogle;
		}

		if (_renderClueToogle) {
			displayCurrentClue(gc, g);
		}
	}

	private void displayCurrentClue(GameContainer gc, Graphics g) {

		if (CurrentClueImage != null) {
			g.drawImage(CurrentClueImage, (gc.getWidth() / 2) - (CurrentClueImage.getWidth() / 2),
					(gc.getHeight() / 2) - (CurrentClueImage.getHeight() / 2));
			
			/** Remove comments to print a string on the clue.
			 *  Another thing that can be done here is to give the ClueDisplayer
			 *  another member variable that can hold the font. Making this code
			 *  only ever execute once.
			FontResources fontr = FontResources.getInstance();
			fontr.initialize_font();
			TrueTypeFont ttf = fontr.get_ttf();
			ttf.drawString(gc.getWidth()/2, gc.getHeight()/2, "Hello World!");
			**/
		}
	}
}
