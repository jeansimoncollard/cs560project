package HUD;


import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import StartMain.FontResources;
import StartMain.StringResources;

/**
 * The TextBox class can be used to display a set
 * of lines across multiple pages. Set the text, then the view,
 * and the text box will be displayed. If the text is too big,
 * either in height or width, you will be notified.
 * 
 * This class is used by NPCs that need to communicate or speak to
 * the Main Character.
 * 
 * @author Gregory
 *
 */

public class TextBox extends Menu {

    /**
     * String that contains the path to the pause menu overlay
     */
    private static final String _TEXT_BOX_PATH = "dependencies/UI_photos/coins_stats_bg.png";
    
    private static ArrayList<ArrayList<String>> textPages;
    private int currentPage;
    private int prevPage;
    private int currentPageHeight;
    private int currentPageWidthMax;
    private static String overHead;
    private TrueTypeFont ttf;

    /**
     * Decision variable on whether to render the menu or not
     */
    private static boolean _renderMenu;

    /**
     * The Image that is fetched using the path to the overlay
     */
    private Image _textBoxBacking;

    public TextBox() throws SlickException {
        super(_TEXT_BOX_PATH);
        _renderMenu = false;
        _textBoxBacking = this.getImage();
        this.currentPage = 0;
        this.prevPage = 0;
        this.currentPageHeight = 0;
        this.currentPageWidthMax = 0;
        this.overHead = null;
        this.ttf = null;
    }
    
    /**
     * Sets the textbox to display.
     */
    public static void setView(boolean renderView) {
    	_renderMenu = renderView;
    }
    
    /**
     * Sets the text pages to be displayed.
     */
    public static void setText(ArrayList<ArrayList<String>> tps) {
    	textPages = tps;
    }
    
    /**
     * Sets the overhead text, if any, for the textbox. 
     */
    public static void setOverheadText(String s) {
    	overHead = s;
    }
    
    /**
     * Checks to see if user pressed space key and
     * stops rendering if pressed.
     * (Calls display to render)
     *
     * @param gc GameContainer for the game.
     * @return boolean Whether or not the Menu is rendering
     */
    @Override
    public boolean render(GameContainer gc) {
    	
        if (_renderMenu) {
        	if (gc.getInput().isKeyPressed(Input.KEY_SPACE)){
        		
        		if(currentPage<textPages.size()-1){
        			currentPage++;
        		}else{
        			currentPage=0;
        			_renderMenu = false;
        		}
        		
        		
        	}
            display(gc, gc.getGraphics());
        }

        return _renderMenu;
    }

    /**
     * Renders the text box using the GameContainer and Graphics
     * singletons used in the game.
     *
     * @param gc GameContainer
     * @param g  Graphics
     */
    @Override
    void display(GameContainer gc, Graphics g) {    	
    	
    	// Create these for simpler code.
    	float maxX = (float)(0.50*(gc.getWidth()));
		float maxY = (float)(0.30*(gc.getHeight()));
		float minX = (float)(gc.getWidth()/2 - maxX/2);
		float minY = (float)(gc.getHeight() - maxY);
		
		// Draw the background.
		g.drawImage(this._textBoxBacking, minX, minY, minX+maxX, minY+maxY, 0, 0, 312, 192);
		
		// Currently there is a max of 4 lines with about 30 characters.
		// Displayed at a font size of 20.
		if (this.ttf == null) {
			this.ttf = FontResources.getInstance().GetFontWithSize(25);
		}
		
		// Get the current widths and heights and check if they will fit.
		this.currentPageHeight = this.getTotalPageHeight(textPages.get(currentPage));
		this.currentPageWidthMax = this.getMaxPageWidth(textPages.get(currentPage));
		if (currentPageHeight > maxY) {
			System.err.println("Page height greater than allowable range.");
		}
		else if (currentPageWidthMax > maxX) {
			System.err.println("Page width greater than allowable range.");
		}
		
		// Print strings from the top of the text box down to the bottom,
		// centered to the middle.
		int height = this.ttf.getHeight(textPages.get(currentPage).get(0));
		for (int i = 0; i < textPages.get(currentPage).size(); i++) {
			int width = this.ttf.getWidth(textPages.get(currentPage).get(i));
			this.ttf.drawString(gc.getWidth()/2 - width/2, (float)(minY + ((i) * height) + (maxY*0.07)),
								textPages.get(currentPage).get(i), Color.black);
		}
		
		// Print the overhead text.
		int heightSpace = 0;
		if (overHead != null) {
			heightSpace = FontResources.getInstance().get_ttf().getHeight(this.overHead);
			FontResources.getInstance().get_ttf().drawString((float)(minX + (maxX*0.05)), 
					   							   (float)(minY - heightSpace - (maxY*0.01)), 
					   							   overHead, Color.black);
		}
		
		
		// Draw a space to let the user know they can press it
		// to get out.
		String space = StringResources.messages.getString("space");
		int width = this.ttf.getWidth(space);
		heightSpace = this.ttf.getHeight(space);
		this.ttf.drawString((float)(minX+maxX-width-(maxX*0.05)), (float)(minY+maxY-heightSpace-(maxY*0.07)), 
				             space, Color.black);
    }
    
    /**
     * This function can be used to determine the total
     * height of a given page to be displayed.
     * @param page
     * @return
     */
    private int getTotalPageHeight(ArrayList<String> page) {
    	int pageHeight = 0;
    	for (String s : page) {
    		pageHeight = pageHeight + this.ttf.getHeight(s);
    	}
    	return pageHeight;
    }
    
    /**
     * This function can be used to determine the maximum
     * width needed from a single page.
     * @param page
     * @return
     */
    private int getMaxPageWidth(ArrayList<String> page) {
		int max = 0;
		for (String s : page) {
			int width = this.ttf.getWidth(s);
			if (max < width) {
				max = width;
			}
		}
		return max;
    }
}
