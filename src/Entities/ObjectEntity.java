package Entities;

import Characters.MainCharacter;
import GameState.GameStateMaster;

public class ObjectEntity {
	public int ObjectX;
	public int ObjectY;
	public Enums.ObjectType ObjectType;
	public String PathToImage;
	public int interactType;
	public static final int NORMAL_INTERACT = 1;
	public static final int COMPLEX_INTERACT = 2;

	public ObjectEntity() {
		this.interactType = 0;
	}

	public ObjectEntity(int objectX, int objectY, Enums.ObjectType objectType, String pathToImage) {
		ObjectX = objectX;
		ObjectY = objectY;
		ObjectType = objectType;
		PathToImage = pathToImage;
		this.interactType = 0;
	}
	
	public ObjectEntity(int objectX, int objectY, Enums.ObjectType objectType, String pathToImage, int interactType) {
		ObjectX = objectX;
		ObjectY = objectY;
		ObjectType = objectType;
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
	public void interact(MainCharacter mc, GameStateMaster gm) { }
}
