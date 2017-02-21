import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;
import org.lwjgl.input.Mouse;

import Characters.MainCharacter;

public class Main extends BasicGame {

    private TiledMap _map;
    private MainCharacter _mainCharacter;
    private Image _img;
    private boolean _renderOverlay = false;

    private final String MAP_PATH = "dependencies/map/bishopsmap.tmx";
    private final String CHARACTER_IMAGE_PATH = "dependencies/characters/maincharacter.png";
    private final String OVERLAY_PATH = "dependencies/UI_photos/overlay.png";


    public Main(String gamename) {
        super(gamename);
    }

    @Override
    // This function is called once at the beginning when we start the game
    public void init(GameContainer gc) throws SlickException {
        _map = new TiledMap(MAP_PATH);
        _mainCharacter = new MainCharacter(0, 0, CHARACTER_IMAGE_PATH);
        _img = new Image(OVERLAY_PATH);
        //Set minimum interval between update() calls
        gc.setMinimumLogicUpdateInterval(150);



    }

    @Override
    // Update is called just before render
    public void update(GameContainer gc, int i) throws SlickException {

        int foregroundLayerIndex = _map.getLayerIndex("Foreground");

        if (!_renderOverlay) {
            if (gc.getInput().isKeyDown(Input.KEY_RIGHT)) {
                // Doesn't go off map
                if (_mainCharacter.XPosition != _map.getWidth() - 1) {
                    // Doesn't collide with foreground
                    if (_map.getTileId(_mainCharacter.XPosition + 1, _mainCharacter.YPosition,
                            foregroundLayerIndex) == 0) {
                        _mainCharacter.XPosition++;
                    }
                }
            }

            if (gc.getInput().isKeyDown(Input.KEY_LEFT)) {
                // Doesn't go off map
                if (_mainCharacter.XPosition != 0) {
                    // Doesn't collide with foreground
                    if (_map.getTileId(_mainCharacter.XPosition - 1, _mainCharacter.YPosition,
                            foregroundLayerIndex) == 0) {
                        _mainCharacter.XPosition--;
                    }
                }
            }

            if (gc.getInput().isKeyDown(Input.KEY_UP)) {
                // Doesn't go off map
                if (_mainCharacter.YPosition != 0) {
                    // Doesn't collide with foreground
                    if (_map.getTileId(_mainCharacter.XPosition, _mainCharacter.YPosition - 1,
                            foregroundLayerIndex) == 0) {
                        _mainCharacter.YPosition--;
                    }
                }
            }

            if (gc.getInput().isKeyDown(Input.KEY_DOWN)) {
                if (_mainCharacter.YPosition != _map.getHeight() - 1) {
                    // doesn't collide with foreground
                    if (_map.getTileId(_mainCharacter.XPosition, _mainCharacter.YPosition + 1,
                            foregroundLayerIndex) == 0) {
                        _mainCharacter.YPosition++;
                    }
                }
            }
        }

        if (gc.getInput().isMousePressed(0) &&
                Mouse.getX() < gc.getWidth() &&
                Mouse.getY() < 136 &&
                Mouse.getY() > 0 &&
                Mouse.getX() > gc.getWidth() - 130) {
            _renderOverlay = !_renderOverlay;
        }

    }

    @Override
    // This function is called for every FPS
    public void render(GameContainer gc, Graphics g) throws SlickException {
        // Move the map to simulate that the character moves, but the character
        // actually stays static in middle of screen {
        _map.render(0, 0, _mainCharacter.XPosition - gc.getWidth() / (2 * 32),
                _mainCharacter.YPosition - gc.getHeight() / (2 * 32), gc.getWidth(), gc.getHeight()

        );

        //Fill a square at middle of screen that represents character
        _mainCharacter.Image.draw(gc.getWidth() / 2 - 4, gc.getHeight() / 2 - 32, 40, 64);

        g.drawImage(new Image("dependencies/UI_photos/wood-plank.jpg"), gc.getWidth() - 130, gc.getHeight() - 135);

        if (_renderOverlay) {
            g.drawImage(_img, (gc.getWidth() / 2) - (_img.getWidth() / 2), (gc.getHeight() / 2) - (_img.getHeight() / 2));
        }
    }

    public static void main(String[] args) throws SlickException {

		AppGameContainer appgc;
		appgc = new AppGameContainer(new Main("Treasure Trail"));
		appgc.setDisplayMode(1280, 768, false);
		appgc.start();
	}
}
