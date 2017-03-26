package HUD;

import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import Characters.MainCharacter;

/**
 * Implementation of the Shop Menu.
 * Will show the pause menu when the user presses
 * the S button.
 */
public class ShopMenu  extends Menu {

    /**
     * Strings that contain the paths to the shop menu and it's buttons.
     * *o for mouse over, and *c for click. 
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
    
    private static final String _MENU_OVERLAY_CHPD = "dependencies/UI_photos/arrow_left.png";
    private static final String _MENU_OVERLAY_CHPU = "dependencies/UI_photos/arrow_right.png";
    
    private static final String _MENU_OVERLAY_CHPDO = "dependencies/UI_photos/arrow_left_mouseover.png";
    private static final String _MENU_OVERLAY_CHPUO = "dependencies/UI_photos/arrow_right_mouseover.png";
    
    private static final String _MENU_OVERLAY_CHPDC = "dependencies/UI_photos/arrow_left_click.png";
    private static final String _MENU_OVERLAY_CHPUC = "dependencies/UI_photos/arrow_right_click.png";
    
    /**
     * The Image that is fetched using the path to the overlay
     * and the pages of the shop. Each inner array is one page.
     */
    private Image _menuOverlay;
    private ArrayList<ArrayList<Button>> shopPages;
    private int currentPage;
    private int maxPages = 1;
    
    /**
     * Button images, *o for mouseover and *c for click.
     */
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
    
    private Image chpd_img;
    private Image chpu_img;
    private Button chpd_button;
    private Button chpu_button;
    
    private Image chpd_imgo;
    private Image chpu_imgo;
    
    private Image chpd_imgc;
    private Image chpu_imgc;

    /**
     * Decision variable on whether to render the menu or not.
     */
    private boolean _renderMenu;
    
    /**
     * Tells whether or not an error string should be drawing.
     */
    private boolean _drawingShopError;
    private String _drawingShopMsg;
    private int _stringFrames;
    private int _maxFrames;
    private Font _font;
    private TrueTypeFont _ttf;
    
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
        this._drawingShopError = false;
        this._drawingShopMsg = "";
        this._font = null;
        this._ttf = null;
        this._stringFrames = 0;
        this._maxFrames = 40;
        
        // Initialize to the max number of pages.
        this.currentPage = 0;
        this.shopPages = new ArrayList<ArrayList<Button>>();
        for (int i = 0; i < maxPages; i++){
        	this.shopPages.add(new ArrayList<Button>());
        }
        
        /**
         * Load images for all buttons and their different states.
         */
		spd_img = new Image(_MENU_OVERLAY_SPD);
		q1_img = new Image(_MENU_OVERLAY_Q1);
		q2_img = new Image(_MENU_OVERLAY_Q2);
		
		spd_imgo = new Image(_MENU_OVERLAY_SPDO);
		q1_imgo = new Image(_MENU_OVERLAY_Q1O);
		q2_imgo = new Image(_MENU_OVERLAY_Q2O);
		
		spd_imgc = new Image(_MENU_OVERLAY_SPDC);
		q1_imgc = new Image(_MENU_OVERLAY_Q1C);
		q2_imgc = new Image(_MENU_OVERLAY_Q2C);
		
		chpd_img = new Image(_MENU_OVERLAY_CHPD);
		chpu_img = new Image(_MENU_OVERLAY_CHPU);
		
		chpd_imgo = new Image(_MENU_OVERLAY_CHPDO);
		chpu_imgo = new Image(_MENU_OVERLAY_CHPUO);

		chpd_imgc = new Image(_MENU_OVERLAY_CHPDC);
		chpu_imgc = new Image(_MENU_OVERLAY_CHPUC);
    }
    
    /**
     * Setter for drawing an error string.
     * @param drawing
     */
    public void set_drawing(boolean drawing){
    	this._drawingShopError = drawing;
    }
    
    /**
     * Getter for whether or not a string is being drawn.
     * @return
     */
    public boolean get_drawing(){
    	return this._drawingShopError;
    }
    
    /**
     * Setter for the string to be drawn.
     * @param drawing
     */
    public void set_drawingMsg(String drawing){
    	this._drawingShopMsg = drawing;
    }
    
    /**
     * Getter for the string to be drawn.
     * @return
     */
    public String get_drawingMsg(){
    	return this._drawingShopMsg;
    }
    
    /**
     * Setter for drawing an error string.
     * @param drawing
     */
    public void set_stringFrames(int sf){
    	this._stringFrames = sf;
    }
    
    /**
     * Getter for whether or not a string is being drawn.
     * @return Number of frames to draw for.
     */
    public int get_stringFrames(){
    	return this._stringFrames;
    }
    
    /**
     * Get the font/string drawing object.
     * @return The font object.
     */
    private TrueTypeFont get_ttf(String fontName, int fontType, int size){
		if (this._ttf == null) {
			this._font = new Font(fontName, fontType, size);
			this._ttf = new TrueTypeFont(_font, true);
		}
		return _ttf;
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
        
        int maxFrameTime = 40;

        if (_renderMenu) {
        	if (this.shopPages.get(currentPage).isEmpty()) {
        		/**
        		 * Load change page down button.
        		 */
        		chpd_button = new Button(gc, chpd_img, 0, 0, maxFrameTime, this) {
					@Override
					boolean process() {
						if (!this.shop.get_drawing()) {
							this.shop.set_drawing(true);
							this.shop.set_stringFrames(0);
							this.shop.set_drawingMsg("This button hasn't been implemented yet.");
						}
						return false;
					}
        		};
				chpd_button.setMouseDownImage(chpd_imgc);
				chpd_button.setMouseOverImage(chpd_imgo);
        		
				/**
				 * Load change page up button.
				 */
        		chpu_button = new Button(gc, chpu_img, 0, 0, maxFrameTime, this) {
					@Override
					boolean process() {
						if (!this.shop.get_drawing()) {
							this.shop.set_drawing(true);
							this.shop.set_stringFrames(0);
							this.shop.set_drawingMsg("This button hasn't been implemented yet.");
						}
						return false;
					}
        		};
				chpu_button.setMouseDownImage(chpu_imgc);
				chpu_button.setMouseOverImage(chpu_imgo);
        		
				/**
				 * Load speed button.
				 */
				spd_button = new Button(gc, spd_img, 0, 0, maxFrameTime, this) {
					@Override
					public boolean process() {
						System.out.println("Clicked speed button, spending 50 coins.");
						if (MainCharacter.CoinCount >= 50) {
							if (MainCharacter.speed > 1) {
								MainCharacter.speed--;
								MainCharacter.CoinCount = MainCharacter.CoinCount - 50;
							} else {
								if (!this.shop.get_drawing()) {
									this.shop.set_drawing(true);
									this.shop.set_stringFrames(0);
									this.shop.set_drawingMsg("Speed is already at the max.");
								}
							}							
						} else {
							if (!this.shop.get_drawing()) {
								this.shop.set_drawing(true);
								this.shop.set_stringFrames(0);
								this.shop.set_drawingMsg("Not enough coins to buy this: " + MainCharacter.CoinCount);
							}
						}
						return true;
					}
				};
				spd_button.setMouseDownImage(spd_imgc);
				spd_button.setMouseOverImage(spd_imgo);
				
				/**
				 * Load questionable sale 1 button.
				 */
				q1_button = new Button(gc, q1_img, 0, 0, maxFrameTime, this) {
					@Override
					public boolean process() {
						System.out.println("Clicked Q1 button, find something for this one.");
						if (MainCharacter.CoinCount >= 150) {
							System.out.println("Taking 150 coins.");
							MainCharacter.CoinCount = MainCharacter.CoinCount - 150;
						} else {
							if (!this.shop.get_drawing()) {
								this.shop.set_drawing(true);
								this.shop.set_stringFrames(0);
								this.shop.set_drawingMsg("Not enough coins to buy this: " + MainCharacter.CoinCount);
							}
						}
						return true;
					}
				};
				q1_button.setMouseDownImage(q1_imgc);
				q1_button.setMouseOverImage(q1_imgo);
				
				/**
				 * Load questionable sale 2 button.
				 */
				q2_button = new Button(gc, q2_img, 0, 0, maxFrameTime, this) {					
					@Override
					boolean process() {
						System.out.println("Clicked Q2 button, end game easter-egg.");
						if (MainCharacter.CoinCount >= 200) {
							System.out.println("Taking 200 coins.");
							MainCharacter.CoinCount = MainCharacter.CoinCount - 200;
							
							// End the game.
							System.exit(0);
						} else {
							// If were drawing increment the number of time so far,
							// otherwise start.
							if (!this.shop.get_drawing()) {
								this.shop.set_drawing(true);
								this.shop.set_stringFrames(0);
								this.shop.set_drawingMsg("Not enough coins to buy this: " + MainCharacter.CoinCount);
							}
						}
						
						return true;
					}
				};
				q2_button.setMouseDownImage(q2_imgc);
				q2_button.setMouseOverImage(q2_imgo);
				
				// Add these buttons to their appropriate pages.
				this.shopPages.get(0).add(spd_button);
				this.shopPages.get(0).add(q1_button);
				this.shopPages.get(0).add(q2_button);
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
        
        
        // TODO: Make a better way to calculate it.
        spd_button.setLocation(corner.x+90, corner.y+130);
        q1_button.setLocation(corner.x+90, corner.y+185);
        q2_button.setLocation(corner.x+90, corner.y+240);
        
        chpd_button.setLocation(corner.x+120, corner.y+300);
        chpu_button.setLocation(corner.x+170, corner.y+300);
        
        spd_button.render(gc, g);
        q1_button.render(gc, g);
        q2_button.render(gc, g);
        chpd_button.render(gc, g);
        chpu_button.render(gc, g);

		
		// If we've started drawing, draw the string.
		// Otherwise, turn rendering off.
		if (this._drawingShopError && this._stringFrames <= this._maxFrames) {
			this._stringFrames++;
			this.get_ttf("Papyrus", Font.PLAIN, 40).drawString(gc.getWidth()/2-(gc.getWidth()/5),
								gc.getHeight()/2-gc.getHeight()/4-50, this._drawingShopMsg, Color.black);
		} else {
			this._stringFrames = 0;
			this._drawingShopError = false;
		}
    }
}

