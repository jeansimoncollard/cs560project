package Characters;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class MainCharacter {

	public int XPosition, YPosition;
	public int _frameCount;
	public int CoinCount;

	private final String CHARACTER_IMAGE_UPPERTILE_PATH = "dependencies/characters/maincharacterUpperTile.png";
	private final String CHARACTER_IMAGE_LOWERTILE_PATH = "dependencies/characters/maincharacterLowerTile.png";

	private Image _upperTileImage;
	private Image _lowerTileImage;

	public MainCharacter() {
		XPosition = 0;
		YPosition = 0;
		_frameCount = 0;
		CoinCount = 0;
	}

	public MainCharacter(int xPosition, int yPosition) throws SlickException {
		XPosition = xPosition;
		YPosition = yPosition;
		_upperTileImage = new Image(CHARACTER_IMAGE_UPPERTILE_PATH);
		_lowerTileImage = new Image(CHARACTER_IMAGE_LOWERTILE_PATH);
	}

	public void Move(TiledMap map, boolean isRenderOverlay, GameContainer gc) {
		int foregroundLayerIndex = map.getLayerIndex("noCollision");

		if (!gc.getInput().isKeyDown(Input.KEY_RIGHT) && !gc.getInput().isKeyDown(Input.KEY_LEFT)
				&& !gc.getInput().isKeyDown(Input.KEY_UP) && !gc.getInput().isKeyDown(Input.KEY_DOWN)) {
			_frameCount = 0;// When nothing is pressed, reset the frameCount to
							// 0, else, move only when _frameCount%x = 0 (x can
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

	public void RenderCharacter(TiledMap map, GameContainer gc) {

		// When the character is on those layers, don't render it
		int overheadLayerIndex1 = map.getLayerIndex("characterOverhead1");
		int overheadLayerIndex2 = map.getLayerIndex("roofsCharacterOverhead");

		if (map.getTileId(this.XPosition, this.YPosition, overheadLayerIndex1) == 0
				&& map.getTileId(this.XPosition, this.YPosition, overheadLayerIndex2) == 0) {
			this._lowerTileImage.draw(gc.getWidth() / 2 - 4, gc.getHeight() / 2, 40, 32);
			this._upperTileImage.draw(gc.getWidth() / 2 - 4, gc.getHeight() / 2 - 32, 40, 32); // Always
																								// render
																								// top
																								// if
																								// lower
																								// renders
			return;
		}

		if (map.getTileId(this.XPosition, this.YPosition - 1, overheadLayerIndex1) == 0
				&& map.getTileId(this.XPosition, this.YPosition - 1, overheadLayerIndex2) == 0) {
			this._upperTileImage.draw(gc.getWidth() / 2 - 4, gc.getHeight() / 2 - 32, 40, 32);
		}
	}
}
