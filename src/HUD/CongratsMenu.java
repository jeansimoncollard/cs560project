package HUD;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import NPC.Layachi;
import StartMain.ImageResources;
import StartMain.StringResources;

/**
 * Created by Gab on 3/29/2017.
 */
public class CongratsMenu extends Menu {
	// These variables are needed to determine
	// when this menu should be shown.
	private Layachi npc_layachi;
	private int frameCount = 0;
	private int maxFrameCount = 400;
	private Image CONGRATS_SCREEN = null;

    /**
     * Decision variable on whether to render the menu or not
     */
    private boolean _renderMenu;

    /**
     * Constructor for Menu. Initializes the
     * path to image field
     *
     */
    public CongratsMenu() throws SlickException {
    	super("");
        this._renderMenu = false;
    }
    
    public void setLayachi(Layachi l) {
    	if (this.npc_layachi == null) {
    		this.npc_layachi = l;
    	}
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
        if (this.npc_layachi.getCompleteText() && _renderMenu == false) {
            _renderMenu = true;
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
    	if (this.CONGRATS_SCREEN == null) {
    		if (StringResources.get_language().equals("fr")) {
    			this.CONGRATS_SCREEN = ImageResources.CONGRATS_SCREEN_FR;
    		} else {
    			this.CONGRATS_SCREEN = ImageResources.CONGRATS_SCREEN;
    		}
    	}
    	
    	if (this.frameCount < this.maxFrameCount) {
			this.CONGRATS_SCREEN.draw(gc.getWidth()/2-this.CONGRATS_SCREEN.getWidth()/2, 
					0, this.CONGRATS_SCREEN.getWidth(), gc.getHeight());
			this.frameCount++;
    	} else {
    		this.frameCount = 0;
    		this.npc_layachi.setCompleteText();
    		this._renderMenu = false;
    	}
    }
}
