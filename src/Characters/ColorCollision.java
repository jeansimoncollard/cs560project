package Characters;

import java.awt.Point;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * This class is used to determine how much a certain character intersects
 * another image or tile. It modifies a character's image depending on the
 * current tile to collide with. 
 * 
 * It does this by using a point list of tuple indices (or x-y pairs) that
 * represent the characters pixels that are not transparent. This set is
 * then used to compare the characters points with those on the object to
 * intersect with. Each pixel that is transparent in the colliding image,
 * is left alone in the original, but those that are not a cleared. This
 * leaves a new character image that can be rendered.
 *  
 * @author Greg
 *
 */
public class ColorCollision {
	private MainCharacter character;
	private Image originalUp;
	private Image originalDown;
	private ArrayList<Point> charPointListUp;
	private ArrayList<Point> charPointListDown;
	private Color collisionColour;
	
	public ColorCollision() {
		this.character = null;
		this.collisionColour = new Color(0, 0, 0, 0);
	}
	
	public ColorCollision(MainCharacter mc){
		this.character = mc;
		//this.originalUp = mc.get_upperTileImage();
		//this.originalDown = mc.get_lowerTileImage();
		this.charPointListUp = new ArrayList<Point>();
		this.charPointListDown = new ArrayList<Point>();
		this.collisionColour = new Color(0, 0, 0, 0);
		
		// Add pixels to point lists, and by default all tiles must be the
		// same size.
		for (int i = 0; i < this.originalUp.getHeight(); i++) {
			for (int j = 0; j < this.originalUp.getHeight(); j++) {
				
				if (!this.originalDown.getColor(i, j).equals(this.collisionColour)){
					charPointListDown.add(new Point(i, j));
				}
				else if (!this.originalUp.getColor(i, j).equals(this.collisionColour)){
					charPointListUp.add(new Point(i, j));
				}
			}
		}
	}
	
	public void collide(Image collideWith) {
		Image modded = this.originalUp.copy();
		if (collideWith == null) {
			return;
		}
		
		for (Point p : charPointListUp) {
			if (!collideWith.getColor(p.x, p.y).equals(this.collisionColour)) {
				Graphics g;
				try {
					g = modded.getGraphics();
					g.setColor(this.collisionColour);
					g.fillRect(p.x, p.y, 1, 1);
					g.flush();//
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		}
		//this.character.set_upperTileImage(modded);
		
		modded = this.originalDown.copy();
		for (Point p : charPointListDown) {
			if (!collideWith.getColor(p.x, p.y).equals(this.collisionColour)) {
				Graphics g;
				try {
					g = modded.getGraphics();
					g.setColor(this.collisionColour);
					g.fillRect(p.x, p.y, 1, 1);
					g.flush();//
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		}
		//this.character.set_lowerTileImage(modded);
	}
}
