package Clues;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

import StartMain.FontResources;
import StartMain.StringResources;

public class ClueDisplayer {

	private Boolean _renderClueToogle;
	private Image _currentClueImage;
	private String _clueText;
	private TrueTypeFont _ttf;

	public ClueDisplayer() {
		_renderClueToogle = false;
		FontResources fontr = FontResources.getInstance();
		fontr.initialize_font();
		_ttf = fontr.GetFontWithSize(20);

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

	public void setClue(Image image, String clueText) {
		_currentClueImage = image;
		_clueText = clueText;
	}

	private void displayCurrentClue(GameContainer gc, Graphics g) {

		if (_currentClueImage != null) {
			g.drawImage(_currentClueImage, (gc.getWidth() / 2) - (_currentClueImage.getWidth() / 2),
					(gc.getHeight() / 2) - (_currentClueImage.getHeight() / 2));

			// ttf.drawstring doesn't implement newlines, so that's a quick fix
			// I found.
			String clueString = StringResources.messages.getString(_clueText);
			String[] lines = clueString.split("\n");

			for (int i = 0; i < lines.length; i++) {
				int width = (int) _ttf.getWidth(lines[i]);

				_ttf.drawString((gc.getWidth() / 2 - (width / 2) + 4),
						(gc.getHeight() / 2) + (_currentClueImage.getHeight() / 9) + i * 20, lines[i], Color.black);
			}

			int width = (int) _ttf.getWidth(StringResources.messages.getString("cToClose"));

			_ttf.drawString((gc.getWidth() / 2 - (width / 2) + 4),
					(gc.getHeight() / 2) + (_currentClueImage.getHeight() / 4),
					StringResources.messages.getString("cToClose"), Color.black);
		}
	}
}
