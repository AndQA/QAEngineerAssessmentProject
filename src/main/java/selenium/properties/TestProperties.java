package selenium.properties;

public final class TestProperties {

	private TestProperties() {
		// Utility class — no instances allowed
	}

	/**
	 * Reads browser type from environment.properties
	 * Example:
	 * browser=CHROME
	 */
	public static Browser getBrowser() {
		String browserName = EnvironmentManager.envConf.browser().toUpperCase();

		try {
			return Browser.valueOf(browserName);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(
					"Invalid browser value in environment.properties: " + browserName, e
			);
		}
	}

	/**
	 * Reads headless flag from environment.properties
	 * Example:
	 * headless=true
	 */
	public static boolean isHeadless() {
		return EnvironmentManager.envConf.headless();
	}
}
