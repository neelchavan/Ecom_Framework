package pageobjects;

import java.util.List;

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
	By productPrice = By.xpath("//div[@class='_25b18c']/div[@class='_30jeq3 _16Jk6d']");
	By specificationText = By.cssSelector("._5pFuey");
	By specificationTable = By.xpath("//tbody");
	By specificationTypes = By.cssSelector(".flxcaE");
	By productRatings = By.cssSelector("._3_L3jD");
	By wishlistOption = By.cssSelector("._2hVSre");
	By buyNowBtn = By.xpath("//button[text()='BUY NOW']");
	By addToCartBtn = By.xpath("//button[text()='ADD TO CART']");

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

	// verify if product price is displayed or not
	public boolean productPricePresence() {
		boolean status = false;
		WebElement price = driver.findElement(productPrice);
		if (price.isDisplayed()) {
			System.out.println("Price: " + price.getText());
			status = true;
		} else {
			return false;
		}
		return status;
	}

	// verify if product specification is displayed correctly
	public boolean productSpecificationPresence() {
		boolean status = false;
		String text = driver.findElement(specificationText).getText();
		List<WebElement> features = driver.findElements(specificationTable);
		List<WebElement> featureTypes = driver.findElements(specificationTypes);
		status = text.equals("Specifications");
		for (WebElement x : features) {
			if (x.isDisplayed()) {
				status = true;
			}
		}
		for (WebElement x : featureTypes) {
			if (x.isDisplayed()) {
				status = true;
			}
		}
		return status;
	}

	// verify if product ratings are displayed correctly
	public boolean prouctRatingsPresence() {
		boolean status = false;
		WebElement rating = driver.findElement(productRatings);
		if (rating.isDisplayed()) {
			status = true;
		}
		return status;
	}

	// verify if wish list option is displayed and eneabled
	public boolean wishListOptionPresence() {
		boolean status = false;
		WebElement wishListButton = driver.findElement(wishlistOption);
		if (wishListButton.isDisplayed() && wishListButton.isEnabled()) {
			status = true;
		}
		return status;
	}

	// verify if 'Add to cart' and 'Buy now' buttons are displayed and enabled.
	public boolean addToCartAndBuyNow() {
		boolean status = false;
		WebElement addToCartButton = driver.findElement(addToCartBtn);
		WebElement buyNowButton = driver.findElement(buyNowBtn);
		if ((addToCartButton.isDisplayed() && addToCartButton.isEnabled())
				&& (buyNowButton.isDisplayed() && buyNowButton.isEnabled())) {
			status = true;
		}
		return status;
	}
}
