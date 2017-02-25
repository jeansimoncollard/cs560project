package Characters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class MainCharacter {

	public int XPosition, YPosition;
	public Image Image;

	public int _frameCount;

	public MainCharacter() {
		XPosition = 0;
		YPosition = 0;
		_frameCount = 0;
	}

	public MainCharacter(int xPosition, int yPosition, String imagePath) throws SlickException {
		XPosition = xPosition;
		YPosition = yPosition;
		Image = new Image(imagePath);
	}

	public void Move(TiledMap map, boolean isRenderOverlay, GameContainer gc) {
		int foregroundLayerIndex = map.getLayerIndex("noCollision");

		if (!gc.getInput().isKeyDown(Input.KEY_RIGHT) && !gc.getInput().isKeyDown(Input.KEY_LEFT)
				&& !gc.getInput().isKeyDown(Input.KEY_UP) && !gc.getInput().isKeyDown(Input.KEY_DOWN)) {
			_frameCount = 0;// When nothing is pressed, reset the frameCount to
							// 0, else, move only when _frameCount%30 = 0 (30 can
							// be changed to change movement speed)
			return;
		}

		if (!isRenderOverlay) {
			
			if (gc.getInput().isKeyDown(Input.KEY_RIGHT) && _frameCount % 15 == 0) {
				// Doesn't go off map
				if (this.XPosition != map.getWidth() - 1) {
					// Doesn't collide with foreground
					if (map.getTileId(this.XPosition + 1, this.YPosition, foregroundLayerIndex) != 0) {
						this.XPosition++;
					}
				}	
			}

			if (gc.getInput().isKeyDown(Input.KEY_LEFT) && _frameCount % 15 == 0) {
				// Doesn't go off map
				if (this.XPosition != 0) {
					// Doesn't collide with foreground
					if (map.getTileId(this.XPosition - 1, this.YPosition, foregroundLayerIndex) != 0) {
						this.XPosition--;
					}
				}
			}

			if (gc.getInput().isKeyDown(Input.KEY_UP) && _frameCount % 15 == 0) {
				// Doesn't go off map
				if (this.YPosition != 0) {
					// Doesn't collide with foreground
					if (map.getTileId(this.XPosition, this.YPosition - 1, foregroundLayerIndex) != 0) {
						this.YPosition--;
					}
				}
			}

			if (gc.getInput().isKeyDown(Input.KEY_DOWN) && _frameCount % 15 == 0) {
				if (this.YPosition != map.getHeight() - 1) {
					// doesn't collide with foreground
					if (map.getTileId(this.XPosition, this.YPosition + 1, foregroundLayerIndex) != 0) {
						this.YPosition++;
					}
				}
			}
			_frameCount++;
		}
	}

}
