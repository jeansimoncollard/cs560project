package HUD;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;


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
    private static final String _MENU_OVERLAY_LOAD3 = "dependencies/UI_photos/mainmenu_loading3.png";
    private static final String _MENU_OVERLAY_LOAD2 = "dependencies/UI_photos/mainmenu_loading2.png";
    private static final String _MENU_OVERLAY_LOAD1 = "dependencies/UI_photos/mainmenu_loading.png";
    private static final String _MENU_OVERLAY_PRESSSPACE = "dependencies/UI_photos/mainmenu_pressspace.png";

    /**
     * The Image that is fetched using the path to the overlay
     */
    private ArrayList<Image> _menuOverlays;
    private Image _menuOverlayComplete;
    private Image currentMenu;
    private int numLoads;
    private boolean loading;

    /**
     * Decision variable on whether to render the menu or not
     */
    private boolean _renderMenu;

    /**
     * Default Constructor.
     * Initializes all instance variables.
     *
     * @throws SlickException Throws if issue with reading Image file
     */
    public MainMenu() throws SlickException {
        super(_MENU_OVERLAY_LOAD3);
    	this._menuOverlays = new ArrayList<Image>();
    	
        this._menuOverlays.add(this.getImage());
        this.setImagePath(_MENU_OVERLAY_LOAD1);
        this._menuOverlays.add(this.getImage());
        this.setImagePath(_MENU_OVERLAY_LOAD2);
        this._menuOverlays.add(this.getImage());
        this.setImagePath(_MENU_OVERLAY_PRESSSPACE);
        this._menuOverlayComplete = this.getImage();
        
        this._renderMenu = true;
        this.numLoads = 0;
        this.loading = true;
    }
    
    public void setLoading(boolean status) { 
    	this.loading = status;
    	if (!this.loading) {
    		this.currentMenu = this._menuOverlayComplete;
    		LoadingList.setDeferredLoading(false);
    	}
    }
    
    public boolean getLoading() {
    	return this.loading;
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
    	if (!this.loading){
	        if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
	            _renderMenu = !_renderMenu;
	        }
	
	        if (_renderMenu) {
	            display(gc, gc.getGraphics());
	        }
    	} else {
    		this.currentMenu = this._menuOverlays.get(this.numLoads%3);
    		this.numLoads++;
    		System.out.println(numLoads);
            display(gc, gc.getGraphics());
    	}
    	try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
        g.drawImage(this.currentMenu, 0, 0, gc.getWidth(), gc.getHeight(), 0, 0, 1920, 1080);
    }
}