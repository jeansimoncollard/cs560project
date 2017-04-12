package Entities;

import org.newdawn.slick.BigImage;
import org.newdawn.slick.GameContainer;

import Characters.MainCharacter;
import GameState.GameStateMaster;

/**
 * All objects should inherit from this class for easier
 * access to complex functionality. They will be displayed
 * and have interactions between the game master and the
 * main character. The interaction flag is used to determine
 * how complex the object is, if it is set to 0, it has no
 * interactions.
 * 
 */
public class ObjectEntity {
	public int ObjectX;								// X position of the object
	public int ObjectY;								// Y position of the object
	public Enums.ObjectType objectType;				// The object type, i.e. NPC, Coin, etc.
	public String PathToImage;						// Path to the image for non-deferred loading of images
	public BigImage img;								// Image of the object, or upper cell of a two-cell object.
	public BigImage img1;								// Bottom part of a two-cell object image (null if not defined).
	public int interactType;						// The interaction type to be performed.
	public static final int NORMAL_INTERACT = 1;	// A normal interaction that doesn't interact with the character, etc.
	public static final int COMPLEX_INTERACT = 2;	// Complex interaction to change states, and have character-NPC interactions.

	/**
	 * Default constructor.
	 */
	public ObjectEntity() {
		this.interactType = 0;
	}

	/**
	 * Constructor used for initializing an object
	 * with non-deferred loading. Currently not in use.
	 * @param objectX
	 * @param objectY
	 * @param objectType
	 * @param pathToImage
	 */
	public ObjectEntity(int objectX, int objectY, Enums.ObjectType objectType, String pathToImage) {
		ObjectX = objectX;
		ObjectY = objectY;
		this.objectType = objectType;
		PathToImage = pathToImage;
		this.img = null;
		this.interactType = 0;
	}
	
	/**
	 * Deferred loading constructor for the objects
	 * that will be displayed on the map. Initialize it's
	 * image in the ImageResources object.
	 * @param objectX
	 * @param objectY
	 * @param objectType
	 * @param img
	 */
	public ObjectEntity(int objectX, int objectY, Enums.ObjectType objectType, BigImage img) {
		ObjectX = objectX;
		ObjectY = objectY;
		this.objectType = objectType;
		this.PathToImage = "";
		this.img = img;
		this.interactType = 0;
	}
	
	
	/**
	 * Use to give an interaction to the object, it
	 * allows the interaction type to be changed from the default,
	 * which is nothing.
	 * @param objectX
	 * @param objectY
	 * @param objectType
	 * @param pathToImage
	 * @param interactType
	 */
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
