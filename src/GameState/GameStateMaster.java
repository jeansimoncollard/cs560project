package GameState;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import Characters.MainCharacter;
import Clues.ClueDisplayer;
import Clues.LibraryBushClueDetector;
import Objects.ObjectsHandler;

public class GameStateMaster {
	public int GameState;

	private ObjectsHandler _objectsHandler;
	private int oldCharacterXPosition;
	private int oldCharacterYPosition;
	private final String PATH_LIBRARYBUSHCLUE_IMAGE = "dependencies/clues/brushcluenotext.png";
	private final String PATH_HAMILTONNOISECLUE_IMAGE = "dependencies/clues/blankscroll2WithMagnifyingGlass.png";
	private ClueDisplayer _clueDisplayer;
	private LibraryBushClueDetector _libraryBushClueDetector;

	public GameStateMaster(ObjectsHandler objectsHandler, ClueDisplayer clueDisplayer) {
		GameState = 0;
		_objectsHandler = objectsHandler;
		_clueDisplayer = clueDisplayer;
		_libraryBushClueDetector = new LibraryBushClueDetector();
	}

	public void Update(MainCharacter character, TiledMap map, GameContainer gc, Graphics g) throws SlickException {
		switch (GameState) {
		case 0: // Generate clue on the floor
			int characterX = character.XPosition;
			int characterY = character.YPosition;

			if (oldCharacterXPosition != characterX || oldCharacterYPosition != characterY) {
				// Only execute the code when the character moved
				_objectsHandler.GenerateClueItem(character.XPosition, character.YPosition, map, this);
				oldCharacterXPosition = characterX;
				oldCharacterYPosition = characterY;
			}

			break;
		case 1: // Player pickups clue
			// The objectsPicuper will take care of advancing the gamestate when
			// the player pickups the clue
			break;
		case 2:// Clue is displayed
			_clueDisplayer.setClue(new Image(PATH_LIBRARYBUSHCLUE_IMAGE), "bushClue");
			_clueDisplayer.DisplayCurrentClue();
			GameState++;

			break;
		case 3:
			_libraryBushClueDetector.Detect(this, character); // Player walks
																// near bush
			break;
		case 4:
			_clueDisplayer.setClue(new Image(PATH_HAMILTONNOISECLUE_IMAGE), "noiseClue"); // New
																							// clue
																							// displayed
			_clueDisplayer.DisplayCurrentClue();
			GameState++;
			break;
		}
	}

}
