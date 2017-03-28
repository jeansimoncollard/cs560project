package HUD;

import StartMain.StringResources;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import java.awt.Font;

/**
 * Implementation of the Pause Menu.
 * Will show the pause menu when the user presses
 * the ESCAPE button.
 */
public class PauseMenu extends Menu {

    /**
     * String that contains the path to the pause menu overlay
     */
    private static final String _MENU_OVERLAY_PATH = "dependencies/UI_photos/overlay.png";

    /**
     * The Image that is fetched using the path to the overlay
     */
    private Image _menuOverlay;

    /**
     * Decision variable on whether to render the menu or not
     */
    private boolean _renderMenu;

    /**
     * Custom Font on menu to quit
     */
    private TrueTypeFont _ttf;

    /**
     * Default Constructor.
     * Initialises all instance variables.
     *
     * @throws SlickException Throws if issue with reading Image file
     */
    public PauseMenu() throws SlickException {
        super(_MENU_OVERLAY_PATH);
        this._menuOverlay = this.getImage();
        this._renderMenu = false;
    }

    /**
     * Checks to see if user pressed ESCAPE key and
     * renders if pressed.
     * (Calls displayMenu to render)
     *
     * @param gc GameContainer for the game.
     * @return boolean Whether or not the Menu is rendering
     */
    @Override
    public boolean render(GameContainer gc) {
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
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
        g.drawImage(_menuOverlay, (gc.getWidth() / 2) - (_menuOverlay.getWidth() / 2),
                (gc.getHeight() / 2) - (_menuOverlay.getHeight() / 2));

        this.get_ttf("Papyrus", Font.PLAIN, 40).drawString((gc.getWidth() / 2) - (_menuOverlay.getWidth() / 2) + 125,
                (gc.getHeight() / 2) - (_menuOverlay.getHeight() / 2) + 50, StringResources.messages.getString("quitMenu"), Color.black);

        try {
            Image img = new Image("dependencies/UI_photos/exitButton.png");

            Button button = new Button(gc, img, (gc.getWidth() / 2) - (img.getWidth() / 2),
                    (gc.getHeight() / 2) - (img.getHeight() / 2) + 30, 40, gc) {

                public boolean process() {
                    if (_renderMenu) {
                        System.exit(0);
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            };

            button.render(gc, g);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the font/string drawing object.
     * @return The font object.
     */
    private TrueTypeFont get_ttf(String fontName, int fontType, int size){
        if (this._ttf == null) {
            Font font = new java.awt.Font(fontName, fontType, size);
            this._ttf = new TrueTypeFont(font, true);
        }
        return this._ttf;
    }
}
