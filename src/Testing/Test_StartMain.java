package Testing;

import org.newdawn.slick.TrueTypeFont;

import StartMain.FontResources;

/**
 * Test functionality of classes in the StartMain package
 * through this object.
 * 
 * @author Greg
 */

public class Test_StartMain extends Test_Abstract {

	@Override
	public boolean run() {
		boolean overall_result = true;
		overall_result = test_fontResources();
		overall_result = test_stringResources();
		overall_result = test_imageResources();
		return overall_result;
	}
	
	/**
	 * Check that font resources can load correctly
	 * and return a TrueTypeFont object that can be
	 * used and is not null.
	 * @return
	 */
	private boolean test_fontResources() {
		boolean test_result = true;
		
		// Don't initialize and check to see that it is null.
		FontResources fontr = FontResources.getInstance();
		TrueTypeFont ttf = fontr.get_ttf();
		if (ttf != null) {
			test_result = false;
		} else {
			System.err.println("ERROR: Font is not null before initialized.");
		}
		
		// Now initialize it to the default.
		fontr.initialize_font();
		ttf = fontr.get_ttf();
		if (ttf == null) {
			test_result = false;
		} else {
			System.err.println("ERROR: Font is null after initialization.");
		}
		
		return test_result;
	}
	
	private boolean test_stringResources() {
		boolean test_result = true;
		return test_result;
	}

	private boolean test_imageResources() {
		boolean test_result = true;
		return test_result;
	}
}
