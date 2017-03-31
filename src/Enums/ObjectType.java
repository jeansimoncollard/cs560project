package Enums;

/**
 * This enumeration is used to denote what
 * types of objects are in the game so that we
 * can distinguish between differing interactions
 * like picking up objects, moving objects, 
 * game-state changing objects, etc..
 *
 */

public enum ObjectType {
	Coin, // The coin that is displayed on the maps
	Clue, // All clues use this type.
	NPC	  // All NPCs use this type.
}
