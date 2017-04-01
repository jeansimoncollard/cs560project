package Characters;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

/**
 * The class for the main character that will
 * be used by the user to move around in the game
 * and go through the adventure with the GameStateMaster.
 *
 */
public class MainCharacter {

    public int XPosition, YPosition;    // Position of the character.
    public int _frameCount;                // Used to determine how long buttons have been pressed.
    private int CoinCount;        // Number of coins the player has.
    private int CoinWorth;
    private int speed;            // Speed at which the character can move at (in frames per move).
    private String Name;            // Name of the character.

    // Image information. 
    private SpriteSheet ss;  			// Spritesheet for the character.
    private String SPRITE_SHEET_LOC;	// The location of the sprite sheet.
    private int currFrame;				// The current movement occuring.
    private int currRow;				// The current row or direction to move in.

    private boolean debug_mode;            // Debug mode variable.

    /**
     * Get the default name for the character and 
     * initialize the sprite sheet depending on the name.
     */
    private void get_default_name() {
        Random r = new Random();
        int nrand = r.nextInt(10) + 1;

        if (nrand < 5) {
            Name = "Alice";
            this.SPRITE_SHEET_LOC = "dependencies/characters/SpriteSheet.png";
        }
        else if (nrand < 9) {
            Name = "Bob";
            this.SPRITE_SHEET_LOC = "dependencies/characters/Charactervector.png";
        }
        else {
            Name = "Link";
            this.SPRITE_SHEET_LOC = "dependencies/characters/linkSpriteSheet.png";
        }
    }

    /**
     * Initialize the maincharacter with everything at 0
     * Default constructor for the character.
     * @throws SlickException
     */
    public MainCharacter() throws SlickException {
        XPosition = 0;
        YPosition = 0;
        _frameCount = 0;
        CoinCount = 0;
        CoinWorth = 1;
        debug_mode = false;
        get_default_name();
    }

    /**
     * Initialize the maincharacter to the given position
     * Constructor that initializes the character to
     * a certain position and a random spritesheet.
     * @param xPosition
     * @param yPosition
     * @throws SlickException
     */
    public MainCharacter(int xPosition, int yPosition) throws SlickException {
        XPosition = xPosition;
        YPosition = yPosition;
        speed = 15;
        CoinWorth = 1;
        debug_mode = false;
        get_default_name();
        ss = new SpriteSheet(new Image(SPRITE_SHEET_LOC), 32, 32);
    }

    /**
     * Getters and setters for the coin count, coin worth,
     * speed, and name.
     */
    public int getCoinCount() {
        return CoinCount;
    }

    /**
     * set coin
     * @return
     */
    public void setCoinCount(int coinCount) {
        CoinCount = coinCount;
    }

    /**
     * Get coin worth
     * @return
     */
    public int getCoinWorth() {
        return CoinWorth;
    }

    /**
     * set coin worth
     * @return
     */
    public void setCoinWorth(int coinWorth) {
        CoinWorth = coinWorth;
    }

    /**
     * Get speed
     * @return
     */
    public int getSpeed() {
        return speed;
    }    
    
    /**
     * set speed
     * @return
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * set name
     * @return
     */
    public void setName(String name) {
        this.Name = name;
    }

    /**
     * Get name
     * @return
     */
    public String getName() {
        return this.Name;
    }

    /**
     * move the character, updating the displayed sprite
     * This function handles the characters movements,
     * preventing out of bounds, and also accepts some
     * secret input.
     * @param map
     * @param isRenderOverlay
     * @param gc
     */
    public void Move(TiledMap map, boolean isRenderOverlay, GameContainer gc) {
    	// Check for collidables
    	int groundCollisionIndex = map.getLayerIndex("groundCollision");
    	
    	// Check for undefined tiles in the walkable layer.
        int foregroundLayerIndex = map.getLayerIndex("noCollision");	// Walkable layer

        // When nothing is pressed, reset the frameCount to
        // 0, else, move only when _frameCount%x = 0 (x can
        // be changed to change movement speed), decrease to increase speed.
        if (!gc.getInput().isKeyDown(Input.KEY_RIGHT) && !gc.getInput().isKeyDown(Input.KEY_LEFT)
                && !gc.getInput().isKeyDown(Input.KEY_UP) && !gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            _frameCount = 0;
            return;
        }

        // If there is no menu, text box, etc. currently being displayed, thee character can move.
        if (!isRenderOverlay) {
        	// Moving right.
            if (gc.getInput().isKeyDown(Input.KEY_RIGHT) && _frameCount % speed == 0) {
                // Doesn't go off map
                if (this.XPosition != map.getWidth() - 1) {
                    // Doesn't collide with foreground
                    if (map.getTileId(this.XPosition + 1, this.YPosition, foregroundLayerIndex) != 0 &&
                    		map.getTileId(this.XPosition + 1, this.YPosition, groundCollisionIndex) == 0) {
                        this.XPosition++;
                        // Process the position and returns frame to be rendered
                        this.currFrame = (this.XPosition%4);
                        this.currRow = 6;
                    }
                }
            }
            // Moving Left
            if (gc.getInput().isKeyDown(Input.KEY_LEFT) && _frameCount % speed == 0) {
                // Doesn't go off map
                if (this.XPosition != 0) {
                    // Doesn't collide with foreground
                    if (map.getTileId(this.XPosition - 1, this.YPosition, foregroundLayerIndex) != 0 &&
                    		map.getTileId(this.XPosition - 1, this.YPosition, groundCollisionIndex) == 0) {
                        this.XPosition--;
                        this.currFrame = (this.XPosition%4);
                        this.currRow = 4;
                    }
                }
            }
            // Moving up.
            if (gc.getInput().isKeyDown(Input.KEY_UP) && _frameCount % speed == 0) {
                // Doesn't go off map
                if (this.YPosition != 0) {
                    // Doesn't collide with foreground
                    if (map.getTileId(this.XPosition, this.YPosition - 1, foregroundLayerIndex) != 0 &&
                    		map.getTileId(this.XPosition, this.YPosition-1, groundCollisionIndex) == 0) {
                        this.YPosition--;
                        // Process the position and returns frame to be rendered
                        this.currFrame = (this.YPosition%4);
                        this.currRow = 2;
                    }
                }
            }
            // Moving down.
            if (gc.getInput().isKeyDown(Input.KEY_DOWN) && _frameCount % speed == 0) {
                if (this.YPosition != map.getHeight() - 1) {
                    // doesn't collide with foreground
                    if (map.getTileId(this.XPosition, this.YPosition + 1, foregroundLayerIndex) != 0 &&
                    		map.getTileId(this.XPosition, this.YPosition + 1, groundCollisionIndex) == 0) {
                        this.YPosition++;
                        // Process the position and returns frame to be rendered
                        this.currFrame = (this.YPosition%4);
                        this.currRow = 0;
                    }
                }
            }

            // Turn on debug mode using this.
            if (gc.getInput().isKeyDown(Input.KEY_D)) {
                if (!this.debug_mode) {
                    System.out.println("Debug mode on.");
                    this.debug_mode = true;
                } else {
                    System.out.println("Debug mode off.");
                    this.debug_mode = false;
                }
            }

            // This can be used after debug has been turned on to get more coins.
            // FYI, it is not easy to turn on debug mode accidentally without console
            // logging.
            if (gc.getInput().isKeyDown(Input.KEY_F) && this.debug_mode) {
                System.out.println("Free money!");
                CoinCount++;
            }

            _frameCount++;
        }
    }

    /**
     * Render the character with the right sprite at the middle of the screen.
     * Displays the character in the view.
     * @param map
     * @param gc
     */
    public void RenderCharacter(TiledMap map, GameContainer gc) {
        // When the character is on those layers, don't render it
        int overheadLayerIndex1 = map.getLayerIndex("characterOverhead1");
        int overheadLayerIndex2 = map.getLayerIndex("roofsCharacterOverhead");

        //To 2 below if statements are meant for character overhead. It renders the character only if it is not under a roof or something
        if (map.getTileId(this.XPosition, this.YPosition, overheadLayerIndex1) == 0
                && map.getTileId(this.XPosition, this.YPosition, overheadLayerIndex2) == 0) {
        	// Always render top if lower renders
        	this.ss.getSprite(this.currFrame, this.currRow + 1).draw(gc.getWidth() / 2 - 4, gc.getHeight() / 2, 32, 32); //lower
        	this.ss.getSprite(this.currFrame, this.currRow).draw(gc.getWidth() / 2 - 4, gc.getHeight() / 2 - 32, 32, 32); //upper
            return;
        }

        
        if (map.getTileId(this.XPosition, this.YPosition - 1, overheadLayerIndex1) == 0
                && map.getTileId(this.XPosition, this.YPosition - 1, overheadLayerIndex2) == 0) {
        	// Don't render the bottom, there is a conflict in layers.
        	this.ss.getSprite(this.currFrame, this.currRow).draw(gc.getWidth() / 2 - 4, gc.getHeight() / 2 - 32, 32, 32); //upper
        }
    }
}
