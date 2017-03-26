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

public class StatDisplayer {
	private final String _MENU_OVERLAY_STATS_BG = "dependencies/UI_photos/coins_stats_bg.png";
	private Image stats_bg;
	private TrueTypeFont ttf;

	public StatDisplayer() {
		try {
			this.stats_bg = new Image(_MENU_OVERLAY_STATS_BG);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.ttf = null;
	}
	
	public void DisplayCoins(MainCharacter character, Graphics g, GameContainer gc) {
		float maxX = (float)(0.25*(gc.getWidth()));
		float maxY = (float)(0.15*(gc.getHeight()));
		
		g.drawImage(this.stats_bg, 0, 0, maxX, maxY, 0, 0, 312, 192);
		if (this.ttf == null) {
			FontResources fontr = FontResources.getInstance();
			this.ttf = fontr.initialize_font("Papyrus", Font.PLAIN, 20);
		}
		
		if (StringResources.messages != null){
			ttf.drawString(20, 20, StringResources.messages.getString("coinCount") + MainCharacter.CoinCount, Color.black);
			ttf.drawString(20, 65, StringResources.messages.getString("name") + MainCharacter.Name, Color.black);
		}
	}

}
