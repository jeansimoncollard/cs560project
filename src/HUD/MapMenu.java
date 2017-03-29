package HUD;

import org.newdawn.slick.*;

/**
 * Created by Gab on 3/29/2017.
 */
public class MapMenu extends Menu {

    /**
     * Decision variable on whether to render the menu or not
     */
    private boolean _renderMenu;

    /**
     * Path to the Map Image
     */
    private final static String _PATH_TO_IMG =  "dependencies/UI_photos/bmap.png";

    /**
     *
     */
    private Image _mapImage;

    /**
     * Constructor for Menu. Initializes the
     * path to image field
     *
     */
    public MapMenu() throws SlickException {
        super(_PATH_TO_IMG);
        this._renderMenu = false;
        this._mapImage = this.getImage();
    }

    /**
     * Checks to see if user pressed M key and
     * renders if pressed.
     * (Calls display() to render)
     *
     * @param gc GameContainer for the game.
     * @return boolean Whether or not the Menu is rendering
     */
    @Override
    public boolean render(GameContainer gc) {
        if (gc.getInput().isKeyPressed(Input.KEY_M)) {
            _renderMenu = !_renderMenu;
        }

        if (_renderMenu) {
            display(gc, gc.getGraphics());
        }

        return _renderMenu;
    }

    /**
     * Renders menu using the GameContainer and Graphics
     * singletons used in the game.
     *
     * @param gc GameContainer
     * @param g  Graphics
     */
    @Override
    void display(GameContainer gc, Graphics g) {
        g.drawImage(_mapImage, (gc.getWidth() / 2) - (_mapImage.getWidth() / 2),
                (gc.getHeight() / 2) - (_mapImage.getHeight() / 2));
    }
}
