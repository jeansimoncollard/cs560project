package HUD;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import StartMain.StringResources;

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
    private static final String _MENU_OVERLAY_QUIT = "dependencies/UI_photos/exitButton.png";
    private static final String _MENU_OVERLAY_QUITO = "dependencies/UI_photos/exitButton_mouseOver.png";
    
    private Image quit;
    private Image quito;

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
        this.quit = new Image(_MENU_OVERLAY_QUIT);
        this.quito = new Image(_MENU_OVERLAY_QUITO);
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
    private Button quit_btn = null;
    @Override
    public void display(GameContainer gc, Graphics g) {
        g.drawImage(_menuOverlay, (gc.getWidth() / 2) - (_menuOverlay.getWidth() / 2),
                (gc.getHeight() / 2) - (_menuOverlay.getHeight() / 2));

        this.get_ttf("Papyrus", Font.PLAIN, 40).drawString((gc.getWidth() / 2) - (_menuOverlay.getWidth() / 2) + 125,
                (gc.getHeight() / 2) - (_menuOverlay.getHeight() / 2) + 50, StringResources.messages.getString("quitMenu"), Color.black);
        
        if (quit_btn == null) {
            this.quit_btn = new Button(gc, this.quit, (gc.getWidth() / 2) - (this.quit.getWidth() / 2),
                    (gc.getHeight() / 2) - (this.quit.getHeight() / 2) + 30, 40, gc) {

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
            //Still needs to be worked on.
            //this.quit_btn.setMouseOverImage(this.quito);
        }
    	
        this.quit_btn.render(gc, g);
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
