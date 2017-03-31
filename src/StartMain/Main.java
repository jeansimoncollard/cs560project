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
import Entities.ObjectEntity;
import GameState.GameStateMaster;
import HUD.HelpMenu;
import HUD.MainMenu;
import HUD.MapMenu;
import HUD.PauseMenu;
import HUD.ShopMenu;
import HUD.StatDisplayer;
import HUD.TextBox;
import NPC.RandomMovementNPC;
import NPC.Layachi;
import NPC.Nelly;
import Objects.ObjectsHandler;

/**
 * The main class which runs the game & loads all the resources.
 * It handles all the interactions for displaying the game, it's objects
 * and menus.
 *
 */
public class Main extends BasicGame {

	/**
	 * All the variables need by the game.
	 */
    private TiledMap _map;
    private TiledMap _mapOver;
    private MainCharacter _mainCharacter;
    private ObjectsHandler _objectsHandler;
    private StatDisplayer _statDisplayer;
    private GameStateMaster _gameStateMaster;
    private ClueDisplayer _clueDisplayer;
    private PauseMenu _pauseMenu;
    private HelpMenu _helpMenu;
    private MapMenu _mapMenu;
    private MainMenu _mainMenu;
    private ShopMenu _shopMenu;
    private TextBox _textBox;
    private boolean _renderOverlay;

    /**
     * Path to the walkable map and overhead map.
     */
    private static final String MAP_PATH = "dependencies/map/mainbishopsmap.tmx";

    private static final String MAP_PATH_OVERHEAD = "dependencies/map/bishopsmap_overhead.tmx";

    /**
     * Default constructor.
     * @param gamename
     */
    public Main(String gamename) {
        super(gamename);
    }

    /**
     * This function is called once at the beginning of the
     * game and handles initializing all the variables and
     * setting up the deferred loading operations along
     * with displaying the loading screen.
     */
    @Override

    public void init(GameContainer gc) throws SlickException {
    	// Create the menu before deferred loading is enabled,
    	// and set it's view to the loading screen.
        _mainMenu = new MainMenu();
        _mainMenu.setLoading(true);
        
        // Enable deferred loading.
        LoadingList.setDeferredLoading(true);
        LoadingList.get().add(new DeferredResource() {
            /**
             * Anything initialized in this function will have it's
             * images and resources loaded before the game is started
             * during the loading screen.
             */
            public void load() throws IOException {
                //turn off deferred loading when we create the resource
                boolean old = LoadingList.isDeferredLoading();
                LoadingList.setDeferredLoading(false);
                try {
                    // create the resource
                    // loads immediately since deferred loading is OFF
                	ImageResources.loadReources();
                    _map = new TiledMap(MAP_PATH);
                    _mapOver = new TiledMap(MAP_PATH_OVERHEAD);
                    MapResources.loadResources(_map);
                    
                    // Find a better way to load up multiple NPCs.
                    RandomMovementNPC tOne = new RandomMovementNPC(125, 85, _map.getWidth(), _map.getHeight(),ObjectEntity.NORMAL_INTERACT);
                    Nelly tTwo = new Nelly(125, 85, _map.getWidth(), _map.getHeight());
                    NPC.Layachi layachi = new  NPC.Layachi(135, 90, _map.getWidth(), _map.getHeight(),ObjectEntity.COMPLEX_INTERACT);
                    
                    _mainCharacter = new MainCharacter(130, 85);
                    _statDisplayer = new StatDisplayer();
                    
                    _objectsHandler = new ObjectsHandler();
                    _objectsHandler.addObject(tOne);
                    _objectsHandler.addObject(tTwo);
                    _objectsHandler.addObject(layachi);
                    
                    _clueDisplayer = new ClueDisplayer();
                    _pauseMenu = new PauseMenu();
                    _helpMenu = new HelpMenu();
                    _mapMenu = new MapMenu();
                    _textBox = new TextBox();
                    
                    _shopMenu = new ShopMenu();
                    _shopMenu.set_character(_mainCharacter);
                    
                    _gameStateMaster = new GameStateMaster(_objectsHandler, _clueDisplayer);
                } catch (SlickException e) {
                    throw new IOException("Error loading image.");
                }
                //reset the loading back to what it was before
                LoadingList.setDeferredLoading(old);
            }
            
            /**
             * Returns the description of this loader.
             */
            public String getDescription() {
                return "Map, and character loader.";
            }
           
        });
        
        // Set minimum interval between update() calls
        gc.setMinimumLogicUpdateInterval(1);
        _renderOverlay = true;
    }

    /**
     * This function is called just before a render
     * which makes it an ideal place to move the character and
     * have all associated updating actions occur.
     */
    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        if (LoadingList.get().getRemainingResources() > 0) {  // If we are still loading.
        	// Render the loading screen.
        	this._renderOverlay = true;
        	_mainMenu.render(gc);
            System.out.println("Loading resource...");
            
            // Load the resource.
            DeferredResource nextResource = LoadingList.get().getNext(); 
            try {
				nextResource.load();
			} catch (IOException e) { // Check for error opening image.
				e.printStackTrace();
			}
        } else {											 // Otherwise, we a re no longer loading.
        	// Disable the loading screen to display the menu.
        	this._mainMenu.setLoading(false);
        	
            // loading is complete, do normal update here
            _mainCharacter.Move(_map, _renderOverlay, gc);     
        }
    }


    @Override
    /**
     * This function is called for every FPS, this is where to put code that is executed every frame.
     */
    public void render(GameContainer gc, Graphics g) throws SlickException {
        // Move the map to simulate that the character moves, but the character
        // actually stays static in middle of screen {
    	if (LoadingList.get().getRemainingResources() == 0) {
    		// Make sure the loading screen is off.
    		this._mainMenu.setLoading(false);
    		
    		// Draw the map with the character at the center
	        _map.render(0, 0, _mainCharacter.XPosition - gc.getWidth() / (2 * 32),
	                _mainCharacter.YPosition - gc.getHeight() / (2 * 32), 40, 60);
	        // Draw objects surrounding character.
	        _objectsHandler.HandleObjects(_mainCharacter, _map, gc, _gameStateMaster);
	        // Draw the character.
	        _mainCharacter.RenderCharacter(_map, gc);
	        // Draw the overhead map so that the character doesn't
	        // disappear when they move under overhead areas.
	        _mapOver.render(0, 0, _mainCharacter.XPosition - gc.getWidth() / (2 * 32),
	                _mainCharacter.YPosition - gc.getHeight() / (2 * 32), 40, 60);
	        
	        // Display the clues and the stats.
	        _clueDisplayer.RenderClue(gc, g);
	        _statDisplayer.DisplayCoins(_mainCharacter, g, gc);
	
	        // Update the game state.
	        _gameStateMaster.Update(_mainCharacter, _map, gc, g);
	
	        // Check if a menu should currently be rendered.
	        if (_pauseMenu.render(gc)) {
	            this._renderOverlay = true;
	        }
	        else if (_helpMenu.render(gc)) {
	            this._renderOverlay = true;
	        }
            else if (_mapMenu.render(gc)) {
                this._renderOverlay = true;
            }
	        else if (_mainMenu.render(gc)) {
	        	this._renderOverlay = true;
	        }
	        else if (_shopMenu.render(gc)) {
	        	this._renderOverlay = true;
	        }
	        else if (_textBox.render(gc)) {
	        	this._renderOverlay = true;
	        }
	        else {
	            this._renderOverlay = false;
	        }
    	} else {  // Otherwise, we are loading so display the loading screen.
    		this._mainMenu.setLoading(true);
    		_mainMenu.render(gc);
    		this._renderOverlay = true;
    	}
    	
    }

    /**
     * Starts the game by initializing this class
     * in the AppContainer. Set the size to a fixed number
     * and make it non-resizeable.
     * @param args
     * @throws SlickException
     */
    public static void main(String[] args) throws SlickException {
        AppGameContainer appgc;
        appgc = new AppGameContainer(new Main("Treasure Trail"));
        appgc.setDisplayMode(1280, 768, false);
        appgc.start();
    }
}
