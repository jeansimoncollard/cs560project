package Clues;

import Characters.MainCharacter;
import GameState.GameStateMaster;

public class ClueDetector {
	public void Detect(GameStateMaster gameStateMaster, MainCharacter character, int xCoordinate, int yCoordinate) {
		if (character.XPosition == xCoordinate && character.YPosition == yCoordinate) {
			gameStateMaster.GameState++;
		}
	}
}
