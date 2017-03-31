package Clues;

import Characters.MainCharacter;
import GameState.GameStateMaster;

/**
 * This class detects whether or not the character is nearby to
 * initiate a state change in the game. This is for the second clue
 * after the clue is found on the ground. It simply checks to see
 * if the characters position is within the bounds and then initiates
 * the change.
 *
 */

public class AreaClueDetector {

	public void Detect(GameStateMaster gameStateMaster, MainCharacter character, int minX, int maxX, int minY, int maxY) {
		if (character.XPosition > minX && character.XPosition < maxX) // If it is
																	// near the
																	// bush
		{
			if (character.YPosition < maxY && character.YPosition > minY) {
				gameStateMaster.GameState++;
			}
		}
	}
}
