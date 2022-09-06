package utiltyMethods;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WindowHandler {
	public WebDriver driver;

	public WindowHandler(WebDriver driver) {
		this.driver = driver;
	}

	public void handleSingleWindow(String defaultWindow) {
		Set<String> windows = driver.getWindowHandles();
		for (String x : windows) {
			if (!x.equals(defaultWindow)) {
				driver.switchTo().window(x);
			}
		}
	}

	public void getBackToDefaultWindow(String defaultWindow) {
		driver.switchTo().window(defaultWindow);
	}
}
