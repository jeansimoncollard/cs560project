package GameState;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import Characters.MainCharacter;
import Clues.ClueDisplayer;
import Objects.ObjectsHandler;

public class GameStateMaster {
	public int GameState;

	private ObjectsHandler _objectsHandler;
	private int oldCharacterXPosition;
	private int oldCharacterYPosition;
	private final String PATH_LIBRARYBUSHCLUE_IMAGE = "dependencies/clues/clueBushLibrary.png";
	private ClueDisplayer _clueDisplayer;

	public GameStateMaster(ObjectsHandler objectsHandler, ClueDisplayer clueDisplayer) {
		GameState = 0;
		_objectsHandler = objectsHandler;
		_clueDisplayer = clueDisplayer;
	}

	public void Update(MainCharacter character, TiledMap map, GameContainer gc, Graphics g) throws SlickException {
		switch (GameState) {
		case 0:
			int characterX = character.XPosition;
			int characterY = character.YPosition;

			if (oldCharacterXPosition != characterX || oldCharacterYPosition != characterY) {
				// Only execute the code when the character moved
				_objectsHandler.GenerateClueItem(character.XPosition, character.YPosition, map, this);
				oldCharacterXPosition = characterX;
				oldCharacterYPosition = characterY;
			}

			break;
		case 1:
			// The objectsPicuper will take care of advancing the gamestate when
						// the player pickups the clue
			break;
		case 2:			
			_clueDisplayer.CurrentClueImage = new Image(PATH_LIBRARYBUSHCLUE_IMAGE);
			_clueDisplayer.DisplayCurrentClue();
			GameState++;

			break;
		}
	}

}
