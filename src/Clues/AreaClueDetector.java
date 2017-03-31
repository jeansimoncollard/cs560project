package Clues;

import Characters.MainCharacter;
import GameState.GameStateMaster;

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
