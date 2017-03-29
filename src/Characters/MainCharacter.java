package Characters;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

public class MainCharacter {

    public int XPosition, YPosition;    // Position of the character.
    public int _frameCount;                // Used to determine how long buttons have been pressed.
    private int CoinCount;        // Number of coins the player has.
    private int CoinWorth;
    private int speed;            // Speed at which the character can move at (in frames per move).
    private String Name;            // Name of the character.

    private SpriteSheet ss; 
    private String SPRITE_SHEET_LOC;
    private int currFrame;
    private int currRow;

    private boolean debug_mode;            // Debug mode variable.

    private void get_default_name() {
        Random r = new Random();
        int nrand = r.nextInt(7) + 1;
        if (nrand < 4) {
            Name = "Alice";
            this.SPRITE_SHEET_LOC = "dependencies/characters/SpriteSheet.png";
        } else {
            Name = "Bob";
            this.SPRITE_SHEET_LOC = "dependencies/characters/Charactervector.png";
        }
    }

    public MainCharacter() throws SlickException {
        XPosition = 0;
        YPosition = 0;
        _frameCount = 0;
        CoinCount = 0;
        CoinWorth = 1;
        debug_mode = false;
        get_default_name();
    }

    public MainCharacter(int xPosition, int yPosition) throws SlickException {
        XPosition = xPosition;
        YPosition = yPosition;
        speed = 15;
        CoinWorth = 1;
        debug_mode = false;
        get_default_name();
        ss = new SpriteSheet(new Image(SPRITE_SHEET_LOC), 32, 32);
    }

    public int getCoinCount() {
        return CoinCount;
    }

    public void setCoinCount(int coinCount) {
        CoinCount = coinCount;
    }

    public int getCoinWorth() {
        return CoinWorth;
    }

    public void setCoinWorth(int coinWorth) {
        CoinWorth = coinWorth;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getName() {
        return this.Name;
    }

    public void Move(TiledMap map, boolean isRenderOverlay, GameContainer gc) {
        int foregroundLayerIndex = map.getLayerIndex("noCollision");

        if (!gc.getInput().isKeyDown(Input.KEY_RIGHT) && !gc.getInput().isKeyDown(Input.KEY_LEFT)
                && !gc.getInput().isKeyDown(Input.KEY_UP) && !gc.getInput().isKeyDown(Input.KEY_DOWN)) {
            _frameCount = 0;// When nothing is pressed, reset the frameCount to
            // 0, else, move only when _frameCount%x = 0 (x can
            // be changed to change movement speed), decrease to increase speed.
            return;
        }

        if (!isRenderOverlay) {

            if (gc.getInput().isKeyDown(Input.KEY_RIGHT) && _frameCount % speed == 0) {
                // Doesn't go off map
                if (this.XPosition != map.getWidth() - 1) {
                    // Doesn't collide with foreground
                    if (map.getTileId(this.XPosition + 1, this.YPosition, foregroundLayerIndex) != 0) {
                        this.XPosition++;
                        // Process the position and returns frame to be rendered
                        this.currFrame = (this.XPosition%4);
                        this.currRow = 6;
                    }
                }
            }

            if (gc.getInput().isKeyDown(Input.KEY_LEFT) && _frameCount % speed == 0) {
                // Doesn't go off map
                if (this.XPosition != 0) {
                    // Doesn't collide with foreground
                    if (map.getTileId(this.XPosition - 1, this.YPosition, foregroundLayerIndex) != 0) {
                        this.XPosition--;
                        this.currFrame = (this.XPosition%4);
                        this.currRow = 4;
                    }
                }
            }

            if (gc.getInput().isKeyDown(Input.KEY_UP) && _frameCount % speed == 0) {
                // Doesn't go off map
                if (this.YPosition != 0) {
                    // Doesn't collide with foreground
                    if (map.getTileId(this.XPosition, this.YPosition - 1, foregroundLayerIndex) != 0) {
                        this.YPosition--;
                        // Process the position and returns frame to be rendered
                        this.currFrame = (this.YPosition%4);
                        this.currRow = 2;
                    }
                }
            }

            if (gc.getInput().isKeyDown(Input.KEY_DOWN) && _frameCount % speed == 0) {
                if (this.YPosition != map.getHeight() - 1) {
                    // doesn't collide with foreground
                    if (map.getTileId(this.XPosition, this.YPosition + 1, foregroundLayerIndex) != 0) {
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

    public void RenderCharacter(TiledMap map, GameContainer gc) {

        // When the character is on those layers, don't render it
        int overheadLayerIndex1 = map.getLayerIndex("characterOverhead1");
        int overheadLayerIndex2 = map.getLayerIndex("roofsCharacterOverhead");

        if (map.getTileId(this.XPosition, this.YPosition, overheadLayerIndex1) == 0
                && map.getTileId(this.XPosition, this.YPosition, overheadLayerIndex2) == 0) {
        	this.ss.getSprite(this.currFrame, this.currRow + 1).draw(gc.getWidth() / 2 - 4, gc.getHeight() / 2, 32, 32); //lower
        	this.ss.getSprite(this.currFrame, this.currRow).draw(gc.getWidth() / 2 - 4, gc.getHeight() / 2 - 32, 32, 32); //upper
        	// Always
            // render
            // top
            // if
            // lower
            // renders

            return;
        }

        if (map.getTileId(this.XPosition, this.YPosition - 1, overheadLayerIndex1) == 0
                && map.getTileId(this.XPosition, this.YPosition - 1, overheadLayerIndex2) == 0) {
        	this.ss.getSprite(this.currFrame, this.currRow + 1).draw(gc.getWidth() / 2 - 4, gc.getHeight() / 2 - 32, 32, 32); //upper
        }
    }
}
