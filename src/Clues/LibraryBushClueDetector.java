package Clues;

import Characters.MainCharacter;
import GameState.GameStateMaster;

public class LibraryBushClueDetector {

	public void Detect(GameStateMaster gameStateMaster, MainCharacter character) {
		if (character.XPosition > 130 && character.YPosition < 135) // If it is
																	// near the
																	// bush
		{
			if (character.YPosition < 68 && character.YPosition > 63) {
				gameStateMaster.GameState++;
			}
		}
	}
}
