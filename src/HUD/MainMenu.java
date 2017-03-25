package HUD;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Implementation of the Main menu.
 * It will continue the game loop once space is pressed.
 * 
 * @author Greg
 */
public class MainMenu  extends Menu {

    /**
     * String that contains the path to the pause menu overlay
     */
    private static final String _MENU_OVERLAY_PATH = "dependencies/UI_photos/mainmenu.png";

    /**
     * The Image that is fetched using the path to the overlay
     */
    private Image _menuOverlay;

    /**
     * Decision variable on whether to render the menu or not
     */
    private boolean _renderMenu;

    /**
     * Default Constructor.
     * Initialises all instance variables.
     *
     * @throws SlickException Throws if issue with reading Image file
     */
    public MainMenu() throws SlickException {
        super(_MENU_OVERLAY_PATH);
        this._menuOverlay = this.getImage();
        this._renderMenu = true;
    }

    /**
     * Checks to see if user pressed SCAPE key and
     * cancels rendering if pressed.
     * (Calls displayMenu to render)
     *
     * @param gc GameContainer for the game.
     * @return boolean Whether or not the Menu is rendering
     */
    @Override
    public boolean render(GameContainer gc) {
        if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
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
    public void display(GameContainer gc, Graphics g) {
        g.drawImage(_menuOverlay, 0, 0, gc.getWidth(), gc.getHeight(), 0, 0, 1920, 1080);
    }
}
