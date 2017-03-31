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
 * An NPC that does nothing. Load it's image here or in
 * ImageResources which is the suggested route.
 * 
 * @author Greg
 *
 */
public class ThingTwo extends NonPlayableCharacter {
	private static final String name = "ThingTwo";
	private ArrayList<ArrayList<String>> textPages;
	private boolean addedStrings;
	
	public ThingTwo(int x, int y, int maxX, int maxY) {
		super(ThingTwo.name, ImageResources.THING_ONE_SS, x, y, maxX, maxY, ObjectEntity.COMPLEX_INTERACT);
		this.textPages = new ArrayList<ArrayList<String>>();
		this.textPages.add(new ArrayList<String>());
		this.addedStrings = false;
	}
	
	public void interact(MainCharacter mc, GameStateMaster gm, GameContainer gc) {
		if (!addedStrings) {
			if (StringResources.messages != null) {
				this.textPages.get(0).add(StringResources.messages.getString("thing2page1line1"));
				this.textPages.get(0).add(StringResources.messages.getString("thing2page1line2"));
				this.addedStrings = true;
			}
		}
		
		if (isCharacterNear(mc)) {
			if (gc.getInput().isKeyPressed(Input.KEY_N)) {
				this.displayTextBox();
			}
		}
	}
}
