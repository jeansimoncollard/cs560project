package Characters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;
import java.util.Random;

public class MainCharacter {

    public int XPosition, YPosition;    // Position of the character.
    public int _frameCount;                // Used to determine how long buttons have been pressed.
    private int CoinCount;        // Number of coins the player has.
    private int CoinWorth;
    private int speed;            // Speed at which the character can move at (in frames per move).
    private String Name;            // Name of the character.

    private ArrayList<ArrayList<Image>> _spriteArray;

    private Image _upperTileImage; // Holds the current top image of the character
    private Image _lowerTileImage; // Holds the current bottom image of the character

    private boolean debug_mode;            // Debug mode variable.

    //////////////////////////////////////////////////////////////////////

    /**
     * Cardinal Directions
     * Down = 0
     * Left = 1
     * Right = 2
     * Up = 3
     *
     * (Note: It's sorted alphabetically for easy memorization)
     */

    private final static int DIRECTION_DOWN = 0;
    private final static int DIRECTION_LEFT = 1;
    private final static int DIRECTION_RIGHT = 2;
    private final static int DIRECTION_UP = 3;
    /////////////////////////////////////////////////////////////////////

    private void get_default_name() {
        Random r = new Random();
        int nrand = r.nextInt(7) + 1;
        if (nrand < 4) {
            Name = "Alice";
        } else {
            Name = "Bob";
        }
    }

    public MainCharacter() throws SlickException {
        this._spriteArray = new ArrayList<>();
        getSpriteSheet();
        _upperTileImage = this._spriteArray.get(DIRECTION_DOWN).get(1);
        _lowerTileImage = this._spriteArray.get(DIRECTION_DOWN).get(0);
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
        this._spriteArray = new ArrayList<>();
        getSpriteSheet();
        _upperTileImage = this._spriteArray.get(DIRECTION_DOWN).get(1);
        _lowerTileImage = this._spriteArray.get(DIRECTION_DOWN).get(0);
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

    /**
     * Gets all the images in the sprite sheet and assigns them to _spriteArray
     * @throws SlickException Handled by the framework
     */
    private void getSpriteSheet() throws SlickException {

        this._spriteArray.add(getImages("Down"));
        this._spriteArray.add(getImages("Left"));
        this._spriteArray.add(getImages("Right"));
        this._spriteArray.add(getImages("Up"));

    }

    /**
     * Gets all the bottom and top pictures in the sprite sheet and returns them in an array.
     * @param folder String - Name of the folder that the PNGs are in
     * @return ArrayList<Image> - Array containing all the images in that file.
     * @throws SlickException Handled by framework
     */
    private ArrayList<Image> getImages(String folder) throws SlickException {
        ArrayList<Image> array = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            array.add(new Image("dependencies/characters/mainCharacterSprites/" + folder + "/" + i + "bot.png"));
            array.add(new Image("dependencies/characters/mainCharacterSprites/" + folder + "/" + i + "top.png"));
        }

        return array;
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
                        int[] frame = getImgPositions(this.XPosition);
                        this._lowerTileImage = this._spriteArray.get(DIRECTION_RIGHT).get(frame[0]);
                        this._upperTileImage = this._spriteArray.get(DIRECTION_RIGHT).get(frame[1]);
                    }
                }
            }

            if (gc.getInput().isKeyDown(Input.KEY_LEFT) && _frameCount % speed == 0) {
                // Doesn't go off map
                if (this.XPosition != 0) {
                    // Doesn't collide with foreground
                    if (map.getTileId(this.XPosition - 1, this.YPosition, foregroundLayerIndex) != 0) {
                        this.XPosition--;
                        // Process the position and returns frame to be rendered
                        int[] frame = getImgPositions(this.XPosition);
                        this._lowerTileImage = this._spriteArray.get(DIRECTION_LEFT).get(frame[0]);
                        this._upperTileImage = this._spriteArray.get(DIRECTION_LEFT).get(frame[1]);
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
                        int[] frame = getImgPositions(this.YPosition);
                        this._lowerTileImage = this._spriteArray.get(DIRECTION_UP).get(frame[0]);
                        this._upperTileImage = this._spriteArray.get(DIRECTION_UP).get(frame[1]);
                    }
                }
            }

            if (gc.getInput().isKeyDown(Input.KEY_DOWN) && _frameCount % speed == 0) {
                if (this.YPosition != map.getHeight() - 1) {
                    // doesn't collide with foreground
                    if (map.getTileId(this.XPosition, this.YPosition + 1, foregroundLayerIndex) != 0) {
                        this.YPosition++;
                        // Process the position and returns frame to be rendered
                        int[] frame = getImgPositions(this.YPosition);
                        this._lowerTileImage = this._spriteArray.get(DIRECTION_DOWN).get(frame[0]);
                        this._upperTileImage = this._spriteArray.get(DIRECTION_DOWN).get(frame[1]);
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
     * Takes the position that is changing and returns which image should be displayed.
     * @param changingPosition int - Either XPosition or YPosition
     * @return int[] - Where index 0 is the bottom image and index 1 is the top image
     */
    private int[] getImgPositions(int changingPosition) {
        int[] arr = new int[2];

        switch(changingPosition % 4) {
            case 0:
                arr[0] = 0;
                arr[1] = 1;
                break;
            case 1:
                arr[0] = 2;
                arr[1] = 3;
                break;
            case 2:
                arr[0] = 4;
                arr[1] = 5;
                break;
            case 3:
                arr[0] = 6;
                arr[1] = 7;
                break;
        }

        return arr;
    }

    public void RenderCharacter(TiledMap map, GameContainer gc) {

        // When the character is on those layers, don't render it
        int overheadLayerIndex1 = map.getLayerIndex("characterOverhead1");
        int overheadLayerIndex2 = map.getLayerIndex("roofsCharacterOverhead");

        if (map.getTileId(this.XPosition, this.YPosition, overheadLayerIndex1) == 0
                && map.getTileId(this.XPosition, this.YPosition, overheadLayerIndex2) == 0) {
            this._lowerTileImage.draw(gc.getWidth() / 2 - 4, gc.getHeight() / 2, 40, 32);
            this._upperTileImage.draw(gc.getWidth() / 2 - 4, gc.getHeight() / 2 - 32, 40, 32); // Always
            // render
            // top
            // if
            // lower
            // renders
            return;
        }

        if (map.getTileId(this.XPosition, this.YPosition - 1, overheadLayerIndex1) == 0
                && map.getTileId(this.XPosition, this.YPosition - 1, overheadLayerIndex2) == 0) {
            this._upperTileImage.draw(gc.getWidth() / 2 - 4, gc.getHeight() / 2 - 32, 40, 32);
        }
    }
}
