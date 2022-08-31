package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
	public WebDriver driver;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
	}

	// locators
	By searchBox = By.cssSelector("._3704LK");
	By filtersText = By.xpath("//span[contains(text(), 'Filters')]");
	By filterOptions = By.xpath("//section[@class='_2aDURW']/div/div");

	public boolean verifyFilterOptions() {
		boolean status = false;
		String text = driver.findElement(filtersText).getText();
		status = text.equals("Filters");
		List<WebElement> filterOption = driver.findElements(filterOptions);

		// Check if all the options are displayed and enabled
		for (WebElement x : filterOption) {
			if (x.isDisplayed() && x.isEnabled()) {
				status = true;
			} else {
				return false;
			}
		}
		return status;
	}
}
