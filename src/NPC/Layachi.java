package NPC;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;

import Characters.MainCharacter;
import GameState.GameStateMaster;
import StartMain.StringResources;

public class Layachi extends RandomMovementNPC {
	private boolean addedStrings; // Tells whether or not strings were added to
	// textPages.

	/**
	 * Initialize character dialog
	 * 
	 * @param x
	 * @param y
	 * @param maxX
	 * @param maxY
	 */
	public Layachi(int x, int y, int maxX, int maxY, int interactType) {
		super(x, y, maxX, maxY, interactType);
		this.textPages = new ArrayList<ArrayList<String>>();
		this.textPages.add(new ArrayList<String>());
		this.textPages.add(new ArrayList<String>());

		this.addedStrings = false;

	}

	/**
	 * Detect if character is near and has finished treasure trail. If yes, give
	 * marker
	 */
	public void interact(MainCharacter mc, GameStateMaster gm, GameContainer gc) {
		if (!addedStrings) {
			if (StringResources.messages != null) {
				addedStrings = true;
				this.textPages.get(0).add(StringResources.messages.getString("layachiPage1Line1"));
				this.textPages.get(0).add(StringResources.messages.getString("layachiPage1Line2"));
				this.textPages.get(0).add(StringResources.messages.getString("layachiPage1Line3"));
				this.textPages.get(1).add(StringResources.messages.getString("layachiPage1Line4"));
				this.textPages.get(1).add(StringResources.messages.getString("layachiPage2Line1"));
				this.textPages.get(1).add(StringResources.messages.getString("layachiPage2Line2"));
				this.textPages.get(1).add(StringResources.messages.getString("layachiPage2Line3"));
			}
		}
		// If the character is near, he will talk to him
		if (isCharacterNear(mc)) {
			// if gamestate==22, character has found all clues.
			if (gm.GameState == 22) {
				this.displayTextBox();
			}
		}
	}

}
