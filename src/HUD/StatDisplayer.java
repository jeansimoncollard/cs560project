package HUD;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import Characters.MainCharacter;
import StartMain.FontResources;
import StartMain.StringResources;

/**
 * Displays statistics about the character in the upper
 * left corner. Currently, it hows coins and the character's
 * name.
 * 
 * @author Gregory
 *
 */
public class StatDisplayer {
	// Path to the image.
	private final String _MENU_OVERLAY_STATS_BG = "dependencies/UI_photos/coins_stats_bg.png";
	
	// Background image.
	private Image stats_bg;
	
	// Font resources.
	private TrueTypeFont ttf;

	/**
	 * Constructor that initializes the image.
	 */
	public StatDisplayer() {
		try {
			this.stats_bg = new Image(_MENU_OVERLAY_STATS_BG);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.ttf = null;
	}
	
	/**
	 * Displays coins and character name in the upper left corner. It can be modified
	 * to display more also since there is a lot of room left on the actual image.
	 * @param character
	 * @param g
	 * @param gc
	 */
	public void DisplayCoins(MainCharacter character, Graphics g, GameContainer gc) {
		// Get approximate length that should be displayed.
		float maxX = (float)(0.25*(gc.getWidth()));
		float maxY = (float)(0.15*(gc.getHeight()));
		
		// Draw the background.
		g.drawImage(this.stats_bg, 0, 0, maxX, maxY, 0, 0, 312, 192);
		
		// Get the font resources if needed.
		if (this.ttf == null) {
			FontResources fontr = FontResources.getInstance();
			this.ttf = fontr.initialize_font("Papyrus", Font.PLAIN, 20);
		}
		
		// If the strings were initialized, print the statistics.
		if (StringResources.messages != null){
			ttf.drawString(20, 20, StringResources.messages.getString("coinCount") + character.getCoinCount(), Color.black);
			ttf.drawString(20, 65, StringResources.messages.getString("name") + character.getName(), Color.black);
		}
	}

}
