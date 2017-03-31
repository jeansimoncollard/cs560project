package NPC;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import Characters.MainCharacter;
import Entities.ObjectEntity;
import GameState.GameStateMaster;
import Objects.NonPlayableCharacter;
import StartMain.ImageResources;
import StartMain.StringResources;

/**
 * An NPC that displays a little bit of text if a character presses 'n' when
 * they are nearby.
 * 
 * @author Greg
 *
 */
public class Nelly extends NonPlayableCharacter {
	private static final String name = "Harry"; // Name of the npc.
	private boolean addedStrings; // Tells whether or not strings were added to
									// textPages.

	/**
	 * Constructor for this NPC, requires standard things like position and max
	 * dimensions.
	 * 
	 * @param x
	 * @param y
	 * @param maxX
	 * @param maxY
	 */
	public Nelly(int x, int y, int maxX, int maxY) {
		super(Nelly.name, ImageResources.THING_ONE_SS, x, y, maxX, maxY, ObjectEntity.COMPLEX_INTERACT);
		this.textPages = new ArrayList<ArrayList<String>>();
		this.textPages.add(new ArrayList<String>());
		this.textPages.add(new ArrayList<String>());
		this.textPages.add(new ArrayList<String>());
		this.addedStrings = false;
	}

	/**
	 * This NPC interacts with the character and the character can press 'n' to
	 * have the NPC speak. 'n' can be changed to something else but it would
	 * need to be changed from 'space' to another key within TextBox.
	 * 
	 * Very simple interaction here. If character is nearby and wants to talk,
	 * pop open a text box and say hello.
	 */
	public void interact(MainCharacter mc, GameStateMaster gm, GameContainer gc) {
		if (!addedStrings) {
			if (StringResources.messages != null) {
				this.textPages.get(0).add(StringResources.messages.getString("thing2page1line1"));
				this.textPages.get(0).add(StringResources.messages.getString("thing2page1line2"));
				this.textPages.get(0).add(StringResources.messages.getString("thing2page1line3"));
				this.textPages.get(1).add(StringResources.messages.getString("thing2page1line4"));
				this.textPages.get(1).add(StringResources.messages.getString("thing2page1line5"));
				this.textPages.get(1).add(StringResources.messages.getString("thing2page1line6"));
				this.textPages.get(1).add(StringResources.messages.getString("thing2page1line7"));
				this.textPages.get(1).add(StringResources.messages.getString("thing2page1line8"));
				this.textPages.get(2).add(StringResources.messages.getString("thing2page1line9"));
				this.textPages.get(2).add(StringResources.messages.getString("thing2page1line10"));

				this.addedStrings = true;
			}
		}
		//If the character is near, he will talk to him
		if (isCharacterNear(mc)) {
			this.displayTextBox();
		}
	}
}
