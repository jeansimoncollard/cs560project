package StartMain;

import java.util.Locale;
import java.util.ResourceBundle;

public class StringResources {
	public static ResourceBundle messages;
	private static String language;
	private static String country;
	private static Locale currentLocale;
	
	/**
	 * Get the current language in use.
	 * @return
	 */
	public static String get_language() {
		return language;
	}
	
	/**
	 * Set the language to a new one.
	 * @param language
	 */
	public static void set_language(String language) {
		StringResources.language = language;
	}
	
	/**
	 * Get the current country.
	 * @return
	 */
	public static String get_country() {
		return country;
	}
	
	/**
	 * Set the current country.
	 * @param country
	 */
	public static void set_country(String country) {
		StringResources.country = country;
	}
	
	/**
	 * Initialize the language so that the ResourceBundle messages
	 * could be used to generate the required strings.
	 */
	public static void initialize_language(){
        currentLocale = new Locale(language, country);
        messages = ResourceBundle.getBundle("StartMain.MessagesBundle", currentLocale);
	}
}
