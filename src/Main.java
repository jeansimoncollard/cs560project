import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;
import org.lwjgl.input.Mouse;

import Characters.MainCharacter;
import Clues.ClueDisplayer;
import GameState.GameStateMaster;
import HUD.CoinDisplayer;
import Objects.ObjectsDisplayer;
import Objects.ObjectsHandler;

public class Main extends BasicGame {

	private TiledMap _map;
	private MainCharacter _mainCharacter;
	private ObjectsHandler _objectsHandler;
	private CoinDisplayer _coinDisplayer;
	private GameStateMaster _gameStateMaster;
	private ClueDisplayer _clueDisplayer;
	private Image _img;
	private boolean _renderOverlay = false;

	private final String MAP_PATH = "dependencies/map/bishopsmap.tmx";
	private final String OVERLAY_PATH = "dependencies/UI_photos/overlay.png";

	public Main(String gamename) {
		super(gamename);
	}

	@Override
	// This function is called once at the beginning when we start the game
	public void init(GameContainer gc) throws SlickException {
		_map = new TiledMap(MAP_PATH);
		_mainCharacter = new MainCharacter(130, 85);
		_img = new Image(OVERLAY_PATH);
		_coinDisplayer = new CoinDisplayer();
		_objectsHandler = new ObjectsHandler();
		_clueDisplayer = new ClueDisplayer();
		_gameStateMaster = new GameStateMaster(_objectsHandler, _clueDisplayer);
		// Set minimum interval between update() calls
		gc.setMinimumLogicUpdateInterval(5);

	}

	@Override
	// Update is called just before render
	public void update(GameContainer gc, int i) throws SlickException {
		_mainCharacter.Move(_map, _renderOverlay, gc);

		if (gc.getInput().isMousePressed(0) && Mouse.getX() < gc.getWidth() && Mouse.getY() < 136 && Mouse.getY() > 0
				&& Mouse.getX() > gc.getWidth() - 130) {
			_renderOverlay = !_renderOverlay;
		}
	}

	@Override
	// This function is called for every FPS
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// Move the map to simulate that the character moves, but the character
		// actually stays static in middle of screen {

		_map.render(0, 0, _mainCharacter.XPosition - gc.getWidth() / (2 * 32),
				_mainCharacter.YPosition - gc.getHeight() / (2 * 32), 40, 60

		);

		_mainCharacter.RenderCharacter(_map, gc);

		_objectsHandler.HandleObjects(_mainCharacter, _map, gc, _gameStateMaster);

		g.drawImage(new Image("dependencies/UI_photos/wood-plank.jpg"), gc.getWidth() - 130, gc.getHeight() - 135);

		_coinDisplayer.DisplayCoins(_mainCharacter, g, gc);

		_gameStateMaster.Update(_mainCharacter, _map, gc, g);

		_clueDisplayer.RenderClue(gc, g);

		if (_renderOverlay) {
			g.drawImage(_img, (gc.getWidth() / 2) - (_img.getWidth() / 2),
					(gc.getHeight() / 2) - (_img.getHeight() / 2));
		}
	}

	public static void main(String[] args) throws SlickException {

		AppGameContainer appgc;
		appgc = new AppGameContainer(new Main("Treasure Trail"));
		appgc.setDisplayMode(1280, 768, false);
		appgc.start();
	}
}
