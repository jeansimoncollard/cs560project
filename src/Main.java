import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame {

	public Main(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {

	}

	public static void main(String[] args) throws SlickException {

			AppGameContainer appgc;
			appgc = new AppGameContainer(new Main("Treasure Trail"));
			appgc.setDisplayMode(1280, 768, false);
			appgc.start();

		
	}
}
