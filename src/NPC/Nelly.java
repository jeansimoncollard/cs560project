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
 * This NPC represents Nelly, when the character walks near her. She gives the quest to find the marker.
 *
 */
public class Nelly extends NonPlayableCharacter {
	private static final String name = "Nelly"; // Name of the npc.
	private boolean addedStrings; // Tells whether or not strings were added to
								  // textPages.
	private int numInteracts;
	
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
		super(Nelly.name, ImageResources.NELLY_SS, x, y, maxX, maxY, ObjectEntity.COMPLEX_INTERACT);
		this.textPages = new ArrayList<ArrayList<String>>();
		this.textPages.add(new ArrayList<String>());
		this.textPages.add(new ArrayList<String>());
		this.textPages.add(new ArrayList<String>());
		this.addedStrings = false;
		this.numInteracts = 0;
	}

	/**
	 * 
	 * Very simple interaction here. If character is nearby,
	 * pop open a text box and say hello.
	 */
	public void interact(MainCharacter mc, GameStateMaster gm, GameContainer gc) {
		if (!addedStrings) {
			if (StringResources.messages != null) {
				this.textPages.get(0).add(StringResources.messages.getString("nellypage1line1"));
				this.textPages.get(0).add(StringResources.messages.getString("nellypage1line2"));
				this.textPages.get(0).add(StringResources.messages.getString("nellypage1line3"));
				this.textPages.get(1).add(StringResources.messages.getString("nellypage1line4"));
				this.textPages.get(1).add(StringResources.messages.getString("nellypage1line5"));
				this.textPages.get(1).add(StringResources.messages.getString("nellypage1line6"));
				this.textPages.get(1).add(StringResources.messages.getString("nellypage1line7"));
				this.textPages.get(2).add(StringResources.messages.getString("nellypage1line8"));
				this.textPages.get(2).add(StringResources.messages.getString("nellypage1line9"));
				this.textPages.get(2).add(StringResources.messages.getString("nellypage1line10"));
				this.textPages.get(2).add(StringResources.messages.getString("nellypage1line11"));
				this.addedStrings = true;
			}
		}
		// If the character is near, she will talk to him automatically
		// the first time.
		if (isCharacterNear(mc)) {
			if (gc.getInput().isKeyPressed(Input.KEY_N)) {
				this.displayTextBox();
			} else if (this.numInteracts == 0){ 
				// If this is the first time there is an interaction
				// display the textbox letting them know how to open it.
				this.displayTextBox();
				if(gm.GameState==0){
					gm.GameState++;
				}
				this.numInteracts++;
			}
		}
	}
}
