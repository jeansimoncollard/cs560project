package HUD;


import org.newdawn.slick.*;

public class HelpMenu extends Menu {

    /**
     * String that contains the path to the pause menu overlay
     */
    private static final String _HELP_MENU_PATH = "dependencies/UI_photos/help.png";

    /**
     * Decision variable on whether to render the menu or not
     */
    private boolean _renderMenu;

    /**
     * The Image that is fetched using the path to the overlay
     */
    private Image _helpMenuOverlay;

    public HelpMenu() throws SlickException {
        super(_HELP_MENU_PATH);
        _renderMenu = false;
        _helpMenuOverlay = this.getImage();
    }

    /**
     * Checks to see if user pressed H key and
     * renders if pressed.
     * (Calls displayMenu to render)
     *
     * @param gc GameContainer for the game.
     * @return boolean Whether or not the Menu is rendering
     */
    @Override
    public boolean render(GameContainer gc) {
        if (gc.getInput().isKeyPressed(Input.KEY_H)) {
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
        g.drawImage(_helpMenuOverlay, (gc.getWidth() / 2) - (_helpMenuOverlay.getWidth() / 2),
                (gc.getHeight() / 2) - (_helpMenuOverlay.getHeight() / 2));
    }
}
