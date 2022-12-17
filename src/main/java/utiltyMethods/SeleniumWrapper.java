package utiltyMethods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWrapper {
	static WebDriverWait wait;

	public static boolean click(WebElement ele, WebDriver driver) {
		boolean present = false;
		try {
			JavascriptExecutor je = (JavascriptExecutor) driver;

			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(ele));

			je.executeScript("arguments[0].scrollIntoView(true);", ele);

			if (ele.isEnabled()) {
				ele.click();
				present = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			present = false;
		}
		return present;
	}

	public static void sendKeys(WebElement ele, String str) {
		ele.clear();
		ele.sendKeys(str);
	}

	public static boolean navigate(WebDriver driver, String url) {
		if (!driver.getCurrentUrl().equals(url)) {
			driver.get(url);
			if (driver.getCurrentUrl().equals(url)) {
				return true;
			}
		}
		return false;
	}

	public static WebElement findElementWithRetry(WebDriver driver, By by, int retryCount) {
		WebElement element = null;
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			// TODO: handle exception
			for (int i = 0; i < retryCount; i++) {
				element = driver.findElement(by);
				if (element.isDisplayed())
					return element;
			}
		}
		return element;
	}

	// click using JavascriptExecutor
	public void jsClick(WebElement ele, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
	}

	// Type Text in a Text Box without using sendKeys() method.
	public void jsSendKeys(WebElement element, String textToEnter, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '" + textToEnter + "')", element);
	}
}
