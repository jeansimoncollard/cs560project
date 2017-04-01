package HUD;

import Characters.MainCharacter;
import StartMain.StringResources;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.TextField;

import java.awt.Font;

/**
 * Created by Gab on 4/1/2017.
 */
public class NameMenu extends Menu {

    /**
     * Decision variable on whether to render the menu or not
     */
    private boolean _renderMenu;

    /**
     *
     */
    private Image _menuImage;

    /**
     * Path to the Menu Image
     */
    private final static String _PATH_TO_IMG = "dependencies/UI_photos/name_menu.png";

    /**
     * Character to change the name of
     */
    private MainCharacter _character;

    /**
     * Enter button name
     */
    private Button _button;

    /**
     * Text field for entering text
     */
    private TextField _input;

    /**
     * Constructor for Menu. Initializes the
     * path to image field
     */
    public NameMenu(MainCharacter character) throws SlickException {
        super(_PATH_TO_IMG);
        this._renderMenu = true;
        this._character = character;
        this._menuImage = this.getImage();
    }

    @Override
    public boolean render(GameContainer gc) {

        if (_renderMenu) {
            display(gc, gc.getGraphics());
        }

        return _renderMenu;
    }

    @Override
    void display(GameContainer gc, Graphics g) {
        // Draw the menu
        g.drawImage(_menuImage, (gc.getWidth() / 2) - (_menuImage.getWidth() / 2),
                (gc.getHeight() / 2) - (_menuImage.getHeight() / 2));

        // Draw the quit button
        if (_button == null) {
            Image img = null;
            try {
                img = new Image("dependencies/UI_photos/next_button.png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
            this._button = new Button(gc, img, (gc.getWidth() / 2) - (img.getWidth() / 2),
                    (gc.getHeight() / 2) - (img.getHeight() / 2) + 30, 40, gc) {

                public boolean process() {

                    String name = _input.getText();
                    if (name.length() > 0 && name.length() < 25) {
                        character.setName(name);
                        _renderMenu = false;
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
        if (_input == null) {
            TrueTypeFont font = new TrueTypeFont(new Font("Papyrus", Font.PLAIN, 30), true);
            this._input = new TextField(gc, font, (gc.getWidth() / 2), (gc.getHeight() / 2), 50, 10);
            this._input.setText(this._character.getName());
        }
        this._button.render(gc, g);
        this._input.render(gc, g);
    }
}
