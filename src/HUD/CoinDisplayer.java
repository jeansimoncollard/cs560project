package HUD;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import Characters.MainCharacter;

public class CoinDisplayer {

	public void DisplayCoins(MainCharacter character, Graphics g, GameContainer gc) {
		g.drawString("Coins: "+character.CoinCount,  gc.getWidth()-100, 20);
	}

}
