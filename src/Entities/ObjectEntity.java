package Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

import Characters.MainCharacter;
import GameState.GameStateMaster;

public class ObjectEntity {
	public int ObjectX;
	public int ObjectY;
	public Enums.ObjectType objectType;
	public String PathToImage;
	public Image img;
	public Image img1;
	public int interactType;
	public static final int NORMAL_INTERACT = 1;
	public static final int COMPLEX_INTERACT = 2;

	public ObjectEntity() {
		this.interactType = 0;
	}

	public ObjectEntity(int objectX, int objectY, Enums.ObjectType objectType, String pathToImage) {
		ObjectX = objectX;
		ObjectY = objectY;
		this.objectType = objectType;
		PathToImage = pathToImage;
		this.img = null;
		this.interactType = 0;
	}
	
	public ObjectEntity(int objectX, int objectY, Enums.ObjectType objectType, Image img) {
		ObjectX = objectX;
		ObjectY = objectY;
		this.objectType = objectType;
		this.PathToImage = "";
		this.img = img;
		this.interactType = 0;
	}
	
	public ObjectEntity(int objectX, int objectY, Enums.ObjectType objectType, String pathToImage, int interactType) {
		ObjectX = objectX;
		ObjectY = objectY;
		this.objectType = objectType;
		PathToImage = pathToImage;
		this.interactType = interactType;
	}
	
	/**
	 * Override to get simple interaction per render without having to
	 * deal with the character.
	 */
	public void interact() { }
	
	/**
	 * Override to get more complex interaction per render allowing
	 * the game state to be modified along with the MainCharacter.
	 */
	public void interact(MainCharacter mc, GameStateMaster gm, GameContainer gc) { }
}
