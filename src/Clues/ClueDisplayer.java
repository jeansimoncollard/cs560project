package Clues;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

import StartMain.FontResources;
import StartMain.StringResources;

/**
 * Display the current clue that the character
 * has made it to. 
 *
 */
public class ClueDisplayer {

	private Boolean _renderClueToogle;	// Toggles whether to display the clue.
	private Image _currentClueImage;	// The image of the current clue to display.
	private String _clueText;			// The text to display within the clue.
	private TrueTypeFont _ttf;			// The font to use for the text.

	/**
	 * Default constructor, initializes
	 * the font.
	 */
	public ClueDisplayer() {
		_renderClueToogle = false;
		FontResources fontr = FontResources.getInstance();
		fontr.initialize_font();
		_ttf = fontr.GetFontWithSize(20);

	}

	/**
	 * Toggles if the clue should be displayed.
	 */
	public void DisplayCurrentClue() {
		_renderClueToogle = true;

	}

	/**
	 * Display the current clue if the C key
	 * is pressed.
	 * @param gc
	 * @param g
	 */
	public void RenderClue(GameContainer gc, Graphics g) {
		if (gc.getInput().isKeyPressed(Input.KEY_C)) {
			_renderClueToogle = !_renderClueToogle;
		}

		if (_renderClueToogle) {
			displayCurrentClue(gc, g);
		}
	}

	/**
	 * Set the image and text to a new or different clue.
	 * @param image
	 * @param clueText
	 */
	public void setClue(Image image, String clueText) {
		_currentClueImage = image;
		_clueText = clueText;
	}

	/**
	 * Display the clue.
	 * @param gc
	 * @param g
	 */
	private void displayCurrentClue(GameContainer gc, Graphics g) {
		// If the image has been initialized.
		if (_currentClueImage != null) {
			// Draw the clue image
			g.drawImage(_currentClueImage, (gc.getWidth() / 2) - (_currentClueImage.getWidth() / 2),
					(gc.getHeight() / 2) - (_currentClueImage.getHeight() / 2));
			
			// Draw the text within the clue.
			
			// ttf.drawstring doesn't implement newlines, so this is a quick
			// fix that works around this issue.
			String clueString = StringResources.messages.getString(_clueText);
			String[] lines = clueString.split("\n");

			// Draw each line.
			for (int i = 0; i < lines.length; i++) {
				int width = (int) _ttf.getWidth(lines[i]);

				_ttf.drawString((gc.getWidth() / 2 - (width / 2) + 4),
						(gc.getHeight() / 2) + (_currentClueImage.getHeight() / 9) + i * 20, lines[i], Color.black);
			}

			// Tell the user theyh can close the clue with the C button.
			int width = (int) _ttf.getWidth(StringResources.messages.getString("cToClose"));

			_ttf.drawString((gc.getWidth() / 2 - (width / 2) + 4),
					(gc.getHeight() / 2) + (_currentClueImage.getHeight() / 4),
					StringResources.messages.getString("cToClose"), Color.black);
		}
	}
}
