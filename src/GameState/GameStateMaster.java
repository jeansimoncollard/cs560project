package GameState;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import Characters.MainCharacter;
import Clues.ClueDetector;
import Clues.ClueDisplayer;
import Clues.AreaClueDetector;
import Objects.ObjectsHandler;

public class GameStateMaster {
	public int GameState;

	private ObjectsHandler _objectsHandler;
	private int oldCharacterXPosition;
	private int oldCharacterYPosition;
	private final String PATH_LIBRARYBUSHCLUE_IMAGE = "dependencies/clues/brushcluenotext.png";
	private final String PATH_BASICCLUE_IMAGE = "dependencies/clues/blankscroll2WithMagnifyingGlass.png";
	private ClueDisplayer _clueDisplayer;
	private AreaClueDetector _areaClueDetector;
	private ClueDetector _clueDetector;

	public GameStateMaster(ObjectsHandler objectsHandler, ClueDisplayer clueDisplayer) {
		GameState = 0;
		_objectsHandler = objectsHandler;
		_clueDisplayer = clueDisplayer;
		_areaClueDetector = new AreaClueDetector();
		_clueDetector = new ClueDetector();
	}

	public void Update(MainCharacter character, TiledMap map, GameContainer gc, Graphics g) throws SlickException {
		switch (GameState) {
		case 1: // Generate clue on the floor
			int characterX = character.XPosition;
			int characterY = character.YPosition;

			if (oldCharacterXPosition != characterX || oldCharacterYPosition != characterY) {
				// Only execute the code when the character moved
				_objectsHandler.GenerateClueItem(character.XPosition, character.YPosition, map, this);
				oldCharacterXPosition = characterX;
				oldCharacterYPosition = characterY;
			}

			break;
		case 2: // Player pickups clue
			// The objectsPicuper will take care of advancing the gamestate when
			// the player pickups the clue
			break;
		case 3:// Clue is displayed
			_clueDisplayer.setClue(new Image(PATH_LIBRARYBUSHCLUE_IMAGE), "bushClue");
			_clueDisplayer.DisplayCurrentClue();
			GameState++;

			break;
		case 4:
			_areaClueDetector.Detect(this, character, 130, 135, 63, 68); // Player
			// walks
			// near bush
			break;
		case 5:
			_clueDisplayer.setClue(new Image(PATH_BASICCLUE_IMAGE), "noiseClue"); // New
																					// clue
																					// displayed
			_clueDisplayer.DisplayCurrentClue();
			GameState++;
			break;
		case 6:
			// position of hamilton
			_areaClueDetector.Detect(this, character, 122, 152, 98, 109);

			break;
		case 7:
			_clueDisplayer.setClue(new Image(PATH_BASICCLUE_IMAGE), "hamiltonClue"); // New
																						// clue
																						// displayed
			_clueDisplayer.DisplayCurrentClue();
			GameState++;
			break;
		case 8:
			// position of gazebo
			_areaClueDetector.Detect(this, character, 153, 157, 59, 64);

			break;
		case 9:

			_clueDisplayer.setClue(new Image(PATH_BASICCLUE_IMAGE), "gazeboClue"); // New
																					// clue
																					// displayed
			_clueDisplayer.DisplayCurrentClue();
			GameState++;
			break;
		case 10:
			// position of nicolls
			_areaClueDetector.Detect(this, character, 117, 145, 123, 139);
			break;
		case 11:

			_clueDisplayer.setClue(new Image(PATH_BASICCLUE_IMAGE), "nicollsClue"); // New
																					// clue
																					// displayed
			_clueDisplayer.DisplayCurrentClue();
			GameState++;
			break;

		case 12:
			// Position of mcgreerClue hall
			_areaClueDetector.Detect(this, character, 169, 172, 78, 84);
			break;
		case 13:
			_clueDisplayer.setClue(new Image(PATH_BASICCLUE_IMAGE), "mcgreerClue"); // New
																					// clue
																					// displayed
			_clueDisplayer.DisplayCurrentClue();
			GameState++;
			break;
		case 14:
			// Position of church
			_areaClueDetector.Detect(this, character, 173, 175, 89, 97);
			break;
		case 15:
			_clueDisplayer.setClue(new Image(PATH_BASICCLUE_IMAGE), "churchClue"); // New
																					// clue
																					// displayed
			_clueDisplayer.DisplayCurrentClue();
			GameState++;
			break;
		case 16:
			// Position of forest
			_areaClueDetector.Detect(this, character, 165, 182, 107, 123);
			break;
		case 17:
			_clueDisplayer.setClue(new Image(PATH_BASICCLUE_IMAGE), "forestClue"); // New
																					// clue
																					// displayed
			_clueDisplayer.DisplayCurrentClue();
			GameState++;
			break;
		case 18:
			// Position of libaryparking
			_areaClueDetector.Detect(this, character, 141, 159, 38, 46);
			break;
		case 19:
			_clueDisplayer.setClue(new Image(PATH_BASICCLUE_IMAGE), "liblaryParkingClue"); // New
			// clue
			// displayed
			_clueDisplayer.DisplayCurrentClue();
			GameState++;
			break;
		case 20:
			// Position of south west parking
			_areaClueDetector.Detect(this, character, 74, 96, 123, 148);
			break;
		case 21:
			_clueDisplayer.setClue(new Image(PATH_BASICCLUE_IMAGE), "swParkingClue"); // New
																						// clue
																						// displayed
			_clueDisplayer.DisplayCurrentClue();
			GameState++;
			break;
		}
	}
}
