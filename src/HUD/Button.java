package HUD;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

import Characters.MainCharacter;

/**
 * This is a button class that can be used to easily implement a button
 * represented by an image and with a process that runs whenever it is
 * clicked.
 * 
 * @author Greg
 */
abstract class Button extends MouseOverArea{
	protected int maxFrameTime;
	protected GameContainer gc;
	protected ShopMenu shop;
	protected MainCharacter character;
	protected Image im;
	protected Image imc;
	protected Image imo;
	
	/**
	 * Initialize.
	 * @param container    GameContainer.
	 * @param im		   Image to use.
	 * @param x    		   Position in x.
	 * @param y 		   Position in y.
	 */	
	public Button(GUIContext container, Image im, int x, int y, int maxFrameTime, GameContainer gc) {
        super(container, im, x, y);
        this.maxFrameTime = maxFrameTime;
        this.gc = gc;
        this.imc = null;
        this.imo = null;
    }
	
	public Button(GUIContext container, Image im, int x, int y, int maxFrameTime, ShopMenu sm) {
        super(container, im, x, y);
        this.maxFrameTime = maxFrameTime;
        this.shop = sm;
        this.im = im;
        this.imc = null;
        this.imo = null;
    }
	
	public Button(GUIContext container, Image im, Image imc, Image imo, int x, int y, int maxFrameTime, ShopMenu sm) {
        super(container, im, x, y);
        this.maxFrameTime = maxFrameTime;
        this.shop = sm;
        this.im  = im;
        this.imc = imc;
        this.imo = imo;
    }
	
	public Button(GUIContext container, Image im, Image imc, Image imo, int x, int y, int maxFrameTime, ShopMenu sm,
				  MainCharacter character) {
        super(container, im, x, y);
        this.maxFrameTime = maxFrameTime;
        this.shop = sm;
        this.im  = im;
        this.imc = imc;
        this.imo = imo;
        this.character = character; 
    }
	
	/**
	 * A button can implement this to run something when
	 * it is clicked.
	 * @return True if it passes, False if it fails.
	 */
	abstract boolean process();

	/**
	 * If the button is clicked, run it's associated process.
	 */
    @Override
    public void mousePressed(int button, int mx, int my) {
        super.mousePressed(button, mx, my);
        if (this.isMouseOver()) {
        	process();
        }
    }
}
