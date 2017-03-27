package StartMain;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

/**
 * A singleton that holds the font resources.
 * 
 * @author Greg
 */
public class FontResources {
	private static FontResources fontr = new FontResources();
	private Font ft;
	private TrueTypeFont ttf;
	private String fname;
	private int ftype;
	private int fsize;
	
	public void set_font(Font ft) {
		this.ft = ft;
	}
	
	public Font get_font() {
		return this.ft;
	}
	
	public void set_ttf(TrueTypeFont ttf) {
		this.ttf = ttf;
	}
	
	public TrueTypeFont get_ttf() {
		return this.ttf;
	}
	
	public Font getFt() {
		return ft;
	}

	public void setFt(Font ft) {
		this.ft = ft;
	}

	public TrueTypeFont getTtf() {
		return ttf;
	}

	public void setTtf(TrueTypeFont ttf) {
		this.ttf = ttf;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public int getFtype() {
		return ftype;
	}

	public void setFtype(int ftype) {
		this.ftype = ftype;
	}

	public int getFsize() {
		return fsize;
	}

	public void setFsize(int fsize) {
		this.fsize = fsize;
	}

	public void initialize_font() {
		if (this.ttf ==  null) {
			this.ft = new Font(this.fname, this.ftype, this.fsize);
			this.ttf = new TrueTypeFont(this.ft, true);
		}
	}
	
	public TrueTypeFont initialize_font(String fname, int ftype, int fsize) {
		Font font_tmp = new Font(fname, ftype, fsize);
		return new TrueTypeFont(font_tmp, true);
	}
	
	// Prevent any calls to a constructor.
	private FontResources(){
		this.ttf = null;
		this.fname = "Papyrus";
		this.ftype = Font.PLAIN;
		this.fsize = 40;
	}
	
	public static FontResources getInstance() {
		return fontr;
	}
}
