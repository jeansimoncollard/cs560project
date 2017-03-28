package HUD;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

abstract class Menu {

    /**
     * Path to the image of the menu
     */
    private String pathToImage;

    /**
     * Constructor for Menu. Initializes the
     * path to image field
     * @param pathToImage String that holds path to image
     */
    Menu(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    /**
     * Returns the Image of which the path was supplied in the constructor
     * @return Image supplied in constructor
     * @throws SlickException Throws if issue with reading Image file
     */
    Image getImage() throws SlickException {
        return new Image(this.pathToImage);
    }
    
    /**
     * Sets the path to the image that is needed.
     * @param pathToImage
     */
    void setImagePath(String pathToImage){
    	this.pathToImage = pathToImage;
    }

    String getImagePath() {
        return this.pathToImage;
    }

    /**
     * Contains the logic of when to display Menu
     * @param gc GameContainer of application
     * @return boolean Whether or not the Menu is rendering
     */
    abstract boolean render(GameContainer gc);

    /**
     * Contains the logic of how to display Menu
     * @param gc GameContainer of application
     * @param g Graphics of application
     */
    abstract void display(GameContainer gc, Graphics g);
}
