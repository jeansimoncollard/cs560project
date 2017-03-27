package StartMain;




import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.tiled.TiledMap;

import Characters.MainCharacter;
import Clues.ClueDisplayer;
import GameState.GameStateMaster;
import HUD.HelpMenu;
import HUD.MainMenu;
import HUD.PauseMenu;
import HUD.ShopMenu;
import HUD.StatDisplayer;
import Objects.ObjectsHandler;

public class Main extends BasicGame {

    private TiledMap _map;
    private MainCharacter _mainCharacter;
    private ObjectsHandler _objectsHandler;
    private StatDisplayer _statDisplayer;
    private GameStateMaster _gameStateMaster;
    private ClueDisplayer _clueDisplayer;
    private PauseMenu _pauseMenu;
    private HelpMenu _helpMenu;
    private MainMenu _mainMenu;
    private ShopMenu _shopMenu;
    private boolean _renderOverlay;

    private static final String MAP_PATH = "dependencies/map/bishopsmap.tmx";

    public Main(String gamename) {
        super(gamename);
    }

    @Override
    // This function is called once at the beginning when we start the game
    public void init(GameContainer gc) throws SlickException {
        _mainMenu = new MainMenu();
        _mainMenu.setLoading(true);
        LoadingList.setDeferredLoading(true);
        LoadingList.get().add(new DeferredResource() {
            
            public void load() throws IOException {
                //turn off deferred loading when we create the resource
                boolean old = LoadingList.isDeferredLoading();
                LoadingList.setDeferredLoading(false);
                try {
                    //create the resource
                    //loads immediately since deferred loading is OFF
                    _map = new TiledMap(MAP_PATH);
                    _mainCharacter = new MainCharacter(130, 85);
                    _statDisplayer = new StatDisplayer();
                    _objectsHandler = new ObjectsHandler();
                    _clueDisplayer = new ClueDisplayer();
                    _pauseMenu = new PauseMenu();
                    _helpMenu = new HelpMenu();
                    _shopMenu = new ShopMenu();
                    _shopMenu.set_character(_mainCharacter);
                    _gameStateMaster = new GameStateMaster(_objectsHandler, _clueDisplayer);
                } catch (SlickException e) {
                    throw new IOException("Error loading image.");
                }
                //reset the loading back to what it was before
                LoadingList.setDeferredLoading(old);
            }

            public String getDescription() {
                return "Map, and character loader.";
            }
           
        });
        
        // Set minimum interval between update() calls
        gc.setMinimumLogicUpdateInterval(1);
        _renderOverlay = true;
    }

    @Override
    // Update is called just before render
    public void update(GameContainer gc, int i) throws SlickException {
        if (LoadingList.get().getRemainingResources() > 0) {
        	this._renderOverlay = true;
        	_mainMenu.render(gc);
            System.out.println("Loading resource...");
            DeferredResource nextResource = LoadingList.get().getNext(); 
            try {
				nextResource.load();
			} catch (IOException e) { // Check for error opening image.
				e.printStackTrace();
			}
        } else {
        	this._mainMenu.setLoading(false);
            // loading is complete, do normal update here
            _mainCharacter.Move(_map, _renderOverlay, gc);     
        }
    }

    @Override
    // This function is called for every FPS
    public void render(GameContainer gc, Graphics g) throws SlickException {
        // Move the map to simulate that the character moves, but the character
        // actually stays static in middle of screen {
    	if (LoadingList.get().getRemainingResources() == 0) {
    		this._mainMenu.setLoading(false);
	        _map.render(0, 0, _mainCharacter.XPosition - gc.getWidth() / (2 * 32),
	                _mainCharacter.YPosition - gc.getHeight() / (2 * 32), 40, 60
	
	        );
	
	        _mainCharacter.RenderCharacter(_map, gc);
	
	        _objectsHandler.HandleObjects(_mainCharacter, _map, gc, _gameStateMaster);
	
	        _statDisplayer.DisplayCoins(_mainCharacter, g, gc);
	
	        _gameStateMaster.Update(_mainCharacter, _map, gc, g);
	
	        _clueDisplayer.RenderClue(gc, g);
	
	        if (_pauseMenu.render(gc)) {
	            this._renderOverlay = true;
	        }
	        else if (_helpMenu.render(gc)) {
	            this._renderOverlay = true;
	        }
	        else if (_mainMenu.render(gc)) {
	        	this._renderOverlay = true;
	        }
	        else if (_shopMenu.render(gc)) {
	        	this._renderOverlay = true;
	        }
	        else {
	            this._renderOverlay = false;
	        }
    	} else {
    		this._mainMenu.setLoading(true);
    		_mainMenu.render(gc);
    		this._renderOverlay = true;
    	}
    	
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer appgc;
        appgc = new AppGameContainer(new Main("Treasure Trail"));
        appgc.setDisplayMode(1280, 768, false);
        appgc.start();
    }
}
