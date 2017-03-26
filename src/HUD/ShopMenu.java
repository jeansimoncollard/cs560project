package HUD;

import java.awt.Point;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import Characters.MainCharacter;

/**
 * Implementation of the Shop Menu.
 * Will show the pause menu when the user presses
 * the S button.
 */
public class ShopMenu  extends Menu {

    /**
     * Strings that contain the paths to the shop menu and it's buttons. 
     */
    private static final String _MENU_OVERLAY_BG = "dependencies/UI_photos/shop_menu_bg.png";
    
    private static final String _MENU_OVERLAY_SPD = "dependencies/UI_photos/speed_button_50.png";
    private static final String _MENU_OVERLAY_Q1 = "dependencies/UI_photos/questions_button1_150.png";
    private static final String _MENU_OVERLAY_Q2 = "dependencies/UI_photos/questions_button2_200.png";
    
    private static final String _MENU_OVERLAY_SPDO = "dependencies/UI_photos/speed_button_50_mouseover.png";
    private static final String _MENU_OVERLAY_Q1O = "dependencies/UI_photos/questions_button1_150_mouseover.png";
    private static final String _MENU_OVERLAY_Q2O = "dependencies/UI_photos/questions_button2_200_mouseover.png";
    
    private static final String _MENU_OVERLAY_SPDC = "dependencies/UI_photos/speed_button_50_click.png";
    private static final String _MENU_OVERLAY_Q1C = "dependencies/UI_photos/questions_button1_150_click.png";
    private static final String _MENU_OVERLAY_Q2C = "dependencies/UI_photos/questions_button2_200_click.png";
    
    /**
     * The Image that is fetched using the path to the overlay
     * and the pages of the shop. Each inner array is one page.
     */
    private Image _menuOverlay;
    private ArrayList<ArrayList<Button>> shopPages;
    private int currentPage;
    private int maxPages = 1;
    
    private Image spd_img;
    private Button spd_button;
    private Image q1_img;
    private Button q1_button;
    private Image q2_img;
    private Button q2_button;
    
    private Image spd_imgo;
    private Image q1_imgo;
    private Image q2_imgo;
    
    private Image spd_imgc;
    private Image q1_imgc;
    private Image q2_imgc;

    /**
     * Decision variable on whether to render the menu or not
     */
    private boolean _renderMenu;

    /**
     * Default Constructor.
     * Initializes all instance variables, shop pages, and button images.
     *
     * @throws SlickException Throws if issue with reading Image file
     */
    public ShopMenu() throws SlickException {
        super(_MENU_OVERLAY_BG);
        this._menuOverlay = this.getImage();
        this._renderMenu = false;
        
        this.currentPage = 0;
        this.shopPages = new ArrayList<ArrayList<Button>>();
        for (int i = 0; i < maxPages; i++){
        	this.shopPages.add(new ArrayList<Button>());
        }
        
		spd_img = new Image(_MENU_OVERLAY_SPD);
		q1_img = new Image(_MENU_OVERLAY_Q1);
		q2_img = new Image(_MENU_OVERLAY_Q2);
		
		spd_imgo = new Image(_MENU_OVERLAY_SPDO);
		q1_imgo = new Image(_MENU_OVERLAY_Q1O);
		q2_imgo = new Image(_MENU_OVERLAY_Q2O);
		
		spd_imgc = new Image(_MENU_OVERLAY_SPDC);
		q1_imgc = new Image(_MENU_OVERLAY_Q1C);
		q2_imgc = new Image(_MENU_OVERLAY_Q2C);
    }

    /**
     * Checks to see if user pressed S key and
     * renders if pressed. It also initializes all the buttons when needed.
     * (Calls displayMenu to render)
     *
     * @param gc GameContainer for the game.
     * @return boolean Whether or not the Menu is rendering
     */
    @Override
    public boolean render(GameContainer gc) {
        if (gc.getInput().isKeyPressed(Input.KEY_S)) {
            _renderMenu = !_renderMenu;
        }

        if (_renderMenu) {
        	if (this.shopPages.get(currentPage).isEmpty()) {
				spd_button = new Button(gc, spd_img, 0, 0) {
					@Override
					public boolean process() {
						System.out.println("Clicked speed button, spending 50 coins.");
						if (MainCharacter.CoinCount >= 50) {
							if (MainCharacter.speed > 1) {
								MainCharacter.speed--;
								MainCharacter.CoinCount = MainCharacter.CoinCount - 50;
							} else {
								System.out.println("Speed is already at the max.");
							}							
						} else {
							System.out.println("Not enough coins.");
						}
						return true;
					}
				};
				spd_button.setMouseDownImage(spd_imgc);
				spd_button.setMouseOverImage(spd_imgo);
				
				q1_button = new Button(gc, q1_img, 0, 0) {
					@Override
					public boolean process() {
						System.out.println("Clicked Q1 button, find something for this one.");
						if (MainCharacter.CoinCount >= 150) {
							System.out.println("Taking 150 coins.");
							MainCharacter.CoinCount = MainCharacter.CoinCount - 150;
						} else {
							System.out.println("Not enough coins");
						}
						return true;
					}
				};
				q1_button.setMouseDownImage(q1_imgc);
				q1_button.setMouseOverImage(q1_imgo);
				
				q2_button = new Button(gc, q2_img, 0, 0) {
					@Override
					boolean process() {
						System.out.println("Clicked Q2 button, end game easter-egg.");
						if (MainCharacter.CoinCount >= 200) {
							System.out.println("Taking 200 coins.");
							MainCharacter.CoinCount = MainCharacter.CoinCount - 200;
							
							// End the game.
							System.exit(0);
						} else {
							System.out.println("Not enough coins");
						}
						
						return true;
					}
				};
				q2_button.setMouseDownImage(q2_imgc);
				q2_button.setMouseOverImage(q2_imgo);
				
				this.shopPages.get(currentPage).add(spd_button);
				this.shopPages.get(currentPage).add(q1_button);
				this.shopPages.get(currentPage).add(q2_button);
        	}
        	
            display(gc, gc.getGraphics());
        }

        return _renderMenu;
    }

    /**
     * Renders menu using the GameContainer and Graphics
     * singletons used in the game. Also renders the buttons.
     *
     * @param gc GameContainer
     * @param g  Graphics
     */
    @Override
    public void display(GameContainer gc, Graphics g) {
    	Point corner = new Point((gc.getWidth() / 2) - (_menuOverlay.getWidth() / 2),
                (gc.getHeight() / 2) - (_menuOverlay.getHeight() / 2));
    	
        g.drawImage(_menuOverlay, corner.x, corner.y);
        
        spd_button.setLocation(corner.x+90, corner.y+130);
        q1_button.setLocation(corner.x+90, corner.y+185);
        q2_button.setLocation(corner.x+90, corner.y+240);
        
        spd_button.render(gc, g);
        q1_button.render(gc, g);
        q2_button.render(gc, g);
    }
}

