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
import StartMain.StringResources;

/**
 * Implementation of the Shop Menu. Press the 'S' key
 * to render. It will display a multi-page view of a store
 * that has clickable buttons that the user can click to 
 * buy the item.
 */
public class ShopMenu  extends Menu {
	private MainCharacter character;

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
    
    //Blank buttons
    private static final String _MENU_OVERLAY_BLANK = "dependencies/UI_photos/button_orig.png";
    private static final String _MENU_OVERLAY_BLANKC = "dependencies/UI_photos/button_orig_click.png";
    private static final String _MENU_OVERLAY_BLANKO = "dependencies/UI_photos/button_orig_mouseover.png";
    
    /**
     * The Image that is fetched using the path to the overlay
     * and the pages of the shop. Each inner array is one page.
     */
    private Image _menuOverlay;
    private ArrayList<ArrayList<Button>> shopPages;
    private ArrayList<ArrayList<String>> shopPages_names;
    private ArrayList<ArrayList<Integer>> shopPages_cost;
    private int currentPage;
    private int prevPage;
    
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
    
    private Image blank_img;
    private Image blank_imgc;
    private Image blank_imgo;

    /**
     * Decision variable on whether to render the menu or not.
     */
    private boolean _renderMenu;
    
    /**
     * Tells whether or not an error string should be drawing.
     */
    private boolean _drawingShopError;		// Display shop string (does not necessarily need to be an error).
    private String _drawingShopMsg;			// String to display
    private int _stringFrames;				// Number of times string was rendered
    private int _maxFrames;					// Max number of times to render it.
    private Font _font;						// Font resources for displaying the text.
    private TrueTypeFont _ttf;
    private TrueTypeFont _ttf_pages;
    
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
        
        this.currentPage = 0;
        this.prevPage = 0;

        // Initialize to the max number of pages.
        this.shopPages = new ArrayList<ArrayList<Button>>();
        this.shopPages_names = new ArrayList<ArrayList<String>>();
        this.shopPages_cost = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < maxPages; i++){
        	this.shopPages.add(new ArrayList<Button>());
        	this.shopPages_names.add(new ArrayList<String>());
            this.shopPages_cost.add(new ArrayList<Integer>());
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
		
		blank_img = new Image(_MENU_OVERLAY_BLANK);
		blank_imgc = new Image(_MENU_OVERLAY_BLANKC);
		blank_imgo = new Image(_MENU_OVERLAY_BLANKO);
    }
    
    /**
     * Set the current character.
     * @param character
     */
    public void set_character(MainCharacter character){
    	this.character = character;
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
     * Get the current page number.
     * @return
     */
    public int getCurrentPage() {
		return currentPage;
	}
    
    /**
     * Get the maximum number of pages.
     * @return
     */
    public int getMaximumPages() {
    	return this.maxPages;
    }
    
    /**
     * Set the current page number.
     * @param currentPage
     */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 * Get the current shop pages cost list.
	 */
	public ArrayList<ArrayList<Integer>> getShopCosts() {
		return this.shopPages_cost;
	}
	
	/**
	 * Get the current shop pages cost list.
	 */
	public void setShopCosts(ArrayList<ArrayList<Integer>> costs) {
		this.shopPages_cost = costs;
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
		return this._ttf;
    }

	/**
     * Get the font/string drawing object.
     * @return The font object.
     */
    private TrueTypeFont get_ttf_pages(String fontName, int fontType, int size){
		if (this._ttf_pages == null) {
			this._font = new Font(fontName, fontType, size);
			this._ttf_pages = new TrueTypeFont(_font, true);
		}
		return this._ttf_pages;
    }
    
    // Max number of pages defined here.
    private int maxPages = 3;
    
    /**
     * This function adds all the buttons to the current GameContainer
     * for the shop menu. It sets up all the available pages of the array list.
     * @param gc
     * @param maxFrameTime
     */
    public void add_buttons(GameContainer gc, int maxFrameTime) {	
    	/**
    	 * Add buttons by loading them into a variable, giving them a process
    	 * and then setting their mouse over and mouse click images.
    	 */
    	
		/**
		 * Load change page down button.
		 */
		chpd_button = new Button(gc, chpd_img, 0, 0, maxFrameTime, this) {
			@Override
			boolean process() {
				// Prevent moving into negative bounds.
				if (currentPage > 0) {
					this.shop.setCurrentPage(this.shop.getCurrentPage()-1);
				}
				// Pause before continuing
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return true;
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
				// Prevent moving out of bounds when changing pages.
				if (currentPage < this.shop.getMaximumPages()-1) {
					this.shop.setCurrentPage(this.shop.getCurrentPage()+1);
				}
				// Pause before continuing
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return true;
			}
		};
		chpu_button.setMouseDownImage(chpu_imgc);
		chpu_button.setMouseOverImage(chpu_imgo);
		
		/**
		 * Load speed button.
		 */
		spd_button = new Button(gc, spd_img, spd_imgc, spd_imgo, 0, 
								0, maxFrameTime, this, this.character) {
			@Override
			public boolean process() {
				// If the character has enough coins, buy the item.
				System.out.println("Clicked speed button, spending 50 coins.");
				if (this.character.getCoinCount() >= 50) {
					// If the speed is not at the max yet.
					if (this.character.getSpeed() > 1) {
						// Take the coings and increase speed.
						this.character.setSpeed(this.character.getSpeed()-1);
						this.character.setCoinCount(this.character.getCoinCount() - 50);
						// Draw a notification.
						if (!this.shop.get_drawing()) {
							this.shop.set_drawing(true);
							this.shop.set_stringFrames(0);
							this.shop.set_drawingMsg(StringResources.messages.getString("speedUp"));
						}
					} else { // Speed is at the max, display an error message.
						if (!this.shop.get_drawing()) {
							this.shop.set_drawing(true);
							this.shop.set_stringFrames(0);
							this.shop.set_drawingMsg(StringResources.messages.getString("speedMax"));
						}
					}							
				} else {
					// If were drawing increment the number of time so far (outside here),
					// otherwise start.
					if (!this.shop.get_drawing()) {
						this.shop.set_drawing(true);
						this.shop.set_stringFrames(0);
						this.shop.set_drawingMsg(StringResources.messages.getString("missingCoins") + this.character.getCoinCount());
					}
				}
				// Sleep to prevent a click being recognized as two clicks.
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return true;
			}
		};
		spd_button.setMouseDownImage(spd_imgc);
		spd_button.setMouseOverImage(spd_imgo);
		
		/**
		 * Load questionable sale 1 button.
		 */
		q1_button = new Button(gc, q1_img, q1_imgc, q1_imgo, 0, 
							   0, maxFrameTime, this, this.character) {
			@Override
			public boolean process() {
				// If the character has enough coins, buy the item.
				System.out.println("Clicked Q1 button, find something for this one.");
				if (this.character.getCoinCount() >= 150) {
					// Take the coins and display a weird message.
					// This button has no effect.
					System.out.println("Taking 150 coins.");
					if (!this.shop.get_drawing()) {
						this.shop.set_drawing(true);
						this.shop.set_stringFrames(0);
						this.shop.set_drawingMsg("? ? ?: " + this.character.getCoinCount());
					}
					this.character.setCoinCount(this.character.getCoinCount() - 150);
				} else {
					// If were drawing increment the number of time so far (outside here),
					// otherwise start.
					if (!this.shop.get_drawing()) {
						this.shop.set_drawing(true);
						this.shop.set_stringFrames(0);
						this.shop.set_drawingMsg(StringResources.messages.getString("missingCoins") + this.character.getCoinCount());
					}
				}
				// Sleep to prevent a click being recognized as two clicks.
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return true;
			}
		};
		q1_button.setMouseDownImage(q1_imgc);
		q1_button.setMouseOverImage(q1_imgo);
		
		/**
		 * Load questionable sale 2 button.
		 */
		q2_button = new Button(gc, q2_img, q2_imgc, q2_imgo, 0, 
							   0, maxFrameTime, this, this.character) {					
			@Override
			boolean process() {
				// If the character has enough coins, buy the item.
				System.out.println("Clicked Q2 button, end game easter-egg.");
				if (this.character.getCoinCount() >= 200) {
					// Take the coins.
					System.out.println("Taking 200 coins.");
					this.character.setCoinCount(this.character.getCoinCount() - 200);
					
					// End the game.
					System.exit(0);
				} else {
					// If were drawing increment the number of time so far (outside here),
					// otherwise start.
					if (!this.shop.get_drawing()) {
						this.shop.set_drawing(true);
						this.shop.set_stringFrames(0);
						this.shop.set_drawingMsg(StringResources.messages.getString("missingCoins") + this.character.getCoinCount());
					}
				}
				
				// Sleep to prevent a click being recognized as two clicks.
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
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
		
		// Fill the rest of the pages with empty buttons.
		Button tmp_button = null;
		for (int i = 1; i < maxPages; i++){	// For each page
			for (int j = 0; j < 3; j++){	// For each line
				// Create the empty buttons.
				tmp_button = new Button(gc, blank_img, blank_imgc, blank_imgo, 0, 
									    0, maxFrameTime, this) {
					@Override
					boolean process() {
						System.out.println("Blank button process.");
						return true;
					}
				};
				// Set their images.
				tmp_button.setMouseDownImage(blank_imgc);
				tmp_button.setMouseOverImage(blank_imgo);
				
				// Add the button to the current page
				this.shopPages.get(i).add(tmp_button);
			}
		}
		
		/**
		 * Everything that follows here makes use of dynamic button creation.
		 * In other words, we use a blank button picture but modify the process,
		 * and the strings and costs that are shown.
		 * 
		 * This makes it simpler because this way we don't have to continuously use some
		 * drawing tool to make the buttons, we just build them on the fly.
		 */
		
		/**
		 * Load a button for increasing coin worth.
		 */
		this.shopPages.get(1).set(0, new Button(gc, blank_img, blank_imgc, blank_imgo, 0,
												0, maxFrameTime, this, this.character) {
			private int num_buys = 1;  // Used to increase item cost.
			
			@Override
			boolean process() {
				// If the character has enough coins, buy the item.
				if (this.character.getCoinCount() >= this.num_buys*20) {
					// Increase the coin worth, take the coins, increase cost.
					System.out.println("Increasing coin worth.");
					
					this.character.setCoinWorth(this.character.getCoinWorth() + 1);
					this.character.setCoinCount(this.character.getCoinCount()-(num_buys*20));
					
					num_buys++;
					this.shop.getShopCosts().get(1).set(0, num_buys*20);
					
					// Draw a notification string.
					if (!this.shop.get_drawing()) {
						this.shop.set_drawing(true);
						this.shop.set_stringFrames(0);
						this.shop.set_drawingMsg(StringResources.messages.getString("coinWorthUp"));
					}
				} else { 
					// Otherwise, the character doesn't have enough money.
					// Ensure that the correct cost is displayed.
					this.shop.getShopCosts().get(1).set(0, num_buys*20);
					
					// Don't tell the user that they don't have enough coins
					// if another string is being drawn.
					if (!this.shop.get_drawing()) {
						this.shop.set_drawing(true);
						this.shop.set_stringFrames(0);
						this.shop.set_drawingMsg(StringResources.messages.getString("missingCoins") + this.character.getCoinCount());
					}
				}
				
				// Sleep to prevent a click being recognized as two clicks.
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				return false;
			}
		});
		// Set the images for over and cliked.
		this.shopPages.get(1).get(0).setMouseDownImage(blank_imgc);
		this.shopPages.get(1).get(0).setMouseOverImage(blank_imgo);
		
		// Fill the last numbers and names with blanks and invalid numbers.
		for (int i = 0; i < maxPages; i++){
			for (int j = 0; j < 3; j++){
				this.shopPages_cost.get(i).add(-1);
				this.shopPages_names.get(i).add(" ");
			}
		}
		
		// Initialize the coin worths button cost and name.
		this.shopPages_cost.get(1).set(0, 20);
		this.shopPages_names.get(1).set(0, StringResources.messages.getString("coinWorthUpMsg"));
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
    	// Render is S is pressed.
        if (gc.getInput().isKeyPressed(Input.KEY_S)) {
            _renderMenu = !_renderMenu;
        }
        
        // Max time to display.
        int maxFrameTime = 40;

        // If we are rendering, add the buttons if
        // there are none and show the shop.
        if (_renderMenu) {        	
        	if (this.shopPages.get(currentPage).isEmpty()) {
				add_buttons(gc, maxFrameTime);
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
    	// Get the upper-left corner (minX, minY) to draw the shop at.
    	Point corner = new Point((gc.getWidth() / 2) - (_menuOverlay.getWidth() / 2),
                (gc.getHeight() / 2) - (_menuOverlay.getHeight() / 2));
    	
    	// Draw the shop.
        g.drawImage(_menuOverlay, corner.x, corner.y);
        
        
        // Set the locations of the buttons.
        // TODO: Make a better way to calculate it.
        this.shopPages.get(currentPage).get(0).setLocation(corner.x+90, corner.y+130);
        this.shopPages.get(currentPage).get(1).setLocation(corner.x+90, corner.y+185);
        this.shopPages.get(currentPage).get(2).setLocation(corner.x+90, corner.y+240);
        
        // Set the location of the arrow up and 
        // down buttons for changing pages.
        chpd_button.setLocation(corner.x+120, corner.y+300);
        chpu_button.setLocation(corner.x+170, corner.y+300);
        
        // If we've changed pages, disable input for the 
        // previous page and enable input for the current
        // page.
        if (prevPage != currentPage) {
        	this.shopPages.get(prevPage).get(0).setAcceptingInput(false);
        	this.shopPages.get(prevPage).get(1).setAcceptingInput(false);
        	this.shopPages.get(prevPage).get(2).setAcceptingInput(false);
        	
        	this.shopPages.get(currentPage).get(0).setAcceptingInput(true);
        	this.shopPages.get(currentPage).get(1).setAcceptingInput(true);
        	this.shopPages.get(currentPage).get(2).setAcceptingInput(true);
        	prevPage = currentPage;
        }
        
        // Draw the buttons.
        this.shopPages.get(currentPage).get(0).render(gc, g);
        this.shopPages.get(currentPage).get(1).render(gc, g);
        this.shopPages.get(currentPage).get(2).render(gc, g);
        
        // If they are not blank buttons, draw the names and costs
        // of the buttons to draw. First draw the name and then draw the cost.
        if (this.shopPages_cost.get(currentPage).get(0) != -1) {
            this.get_ttf_pages("Papyrus", Font.PLAIN, 25).drawString(corner.x+100, corner.y+135, 
            			 this.shopPages_names.get(currentPage).get(0), Color.black);
            this.get_ttf_pages("Papyrus", Font.PLAIN, 25).drawString(corner.x+350, corner.y+135, 
       			 Integer.toString(this.shopPages_cost.get(currentPage).get(0)), Color.black);
        }
        if (this.shopPages_cost.get(currentPage).get(1) != -1) {
            this.get_ttf_pages("Papyrus", Font.PLAIN, 25).drawString(corner.x+100, corner.y+190, 
            			 this.shopPages_names.get(currentPage).get(1), Color.black);
            this.get_ttf_pages("Papyrus", Font.PLAIN, 25).drawString(corner.x+350, corner.y+190, 
       			 Integer.toString(this.shopPages_cost.get(currentPage).get(1)), Color.black);
        }
        if (this.shopPages_cost.get(currentPage).get(2) != -1) {
            this.get_ttf_pages("Papyrus", Font.PLAIN, 25).drawString(corner.x+100, corner.y+245, 
            			 this.shopPages_names.get(currentPage).get(2), Color.black);
            this.get_ttf_pages("Papyrus", Font.PLAIN, 25).drawString(corner.x+350, corner.y+245, 
       			 Integer.toString(this.shopPages_cost.get(currentPage).get(2)), Color.black);
        }
        
        // Render the arrow buttons.
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

