package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailsPage {
	public WebDriver driver;

	public ProductDetailsPage(WebDriver driver) {
		this.driver = driver;
	}

	// locators
	By productName = By.cssSelector(".B_NuCI");
	By productImage = By.xpath("//div[@class='_3kidJX']//div[@class='_312yBx SFzpgZ']/img");

	// verify if product image is displayed or not
	public boolean productImagePresence() {
		boolean status = false;
		WebElement prodImage = driver.findElement(productImage);
		if (prodImage.isDisplayed()) {
			status = true;
		} else {
			return false;
		}
		return status;
	}

}
