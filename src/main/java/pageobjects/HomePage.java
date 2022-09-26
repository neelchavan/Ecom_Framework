package pageobjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePage {

	private static Logger log = LogManager.getLogger(HomePage.class.getName());

	public WebDriver driver;
	public static String flipEmail;
	public static String flipPassword;
	HomePage hp;
	ProductDetailsPage pd;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	// locators
	By loginPopUp = By.xpath("//div[@class='_3oBhRa col col-2-5 _4H6HH5']");
	By emailField = By.cssSelector("input[class='_2IX_2- VJZDxU'][type='text']");
	By passwrdField = By.cssSelector("input[class='_2IX_2- _3mctLh VJZDxU'][type='password']");
	By login = By.partialLinkText("Login");
	By logout = By.xpath("//div[@class='_3vhnxf'][contains(text(), 'Logout')]");
	By submit = By.cssSelector("button[class='_2KpZ6l _2HKlqd _3AWRsL']");
	By neel = By.xpath("//div[contains(text(), 'Neel')]");
	By errMsg = By.cssSelector("span[class='_2YULOR'] span");
	By catagories = By.cssSelector("._37M3Pb .xtXmba");
	By searchBox = By.cssSelector("._3704LK");
	By searchResults = By.cssSelector("._10Ermr");
	By homePageProducts = By.cssSelector("._6WQwDJ");
	By searchedProductResult = By.cssSelector("._4rR01T");
	By specificationPageProduct = By.cssSelector(".B_NuCI");
	By closeLoginModal = By.cssSelector("._2QfC02 button");

	public void closeLoginmodal() {
		driver.findElement(closeLoginModal).click();
	}

	public String verifyHomePage() {
		String homePageUrl = driver.getCurrentUrl();
		return homePageUrl;
	}

	public void loginMethod(String usrname, String pass) {
		driver.findElement(emailField).sendKeys(usrname);
		driver.findElement(passwrdField).sendKeys(pass);
		driver.findElement(submit).click();
	}

	public String loginToFlipcart() {
		String profileName = null;
		// the line below is for explicit wait.
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		hp = new HomePage(driver);
		hp.loginMethod(flipEmail, flipPassword);
		wait.until(ExpectedConditions.visibilityOfElementLocated(neel));
		profileName = driver.findElement(neel).getText();
		return profileName;
	}

	public boolean verifyProdCatagories() {
		boolean status = false;
//		List<String> expectedCatagoris = Arrays.asList("Top Offers", "Grocery", "Mobiles", "Fashion", "Electronics", "Home",
//				"Appliances", "Travel", "Beauty, Toys & More, Electric 2-Wheelers");
		// List<String> displayedCatagories = new ArrayList<String>();
		List<WebElement> catagorieList = driver.findElements(catagories);
		for (WebElement x : catagorieList) {
			// System.out.println(x.getText());
			if (x.isDisplayed()) {
				status = true;
			} else {
				return false;
			}
		}
		return status;
	}

	public boolean verifySearchFunctionality(String searchValue) {
		boolean status = false;
		boolean ifExist = driver.findElement(searchBox).isDisplayed();
		WebElement search = driver.findElement(searchBox);
		search.sendKeys(searchValue);
		search.sendKeys(Keys.ENTER);
		String searchResult = driver.findElement(searchResults).getText();
		String actualText = searchResult.split("for ")[1];
		status = actualText.contains(searchValue) && ifExist;
		return status;
	}

	public boolean productsClickableOrNot() {
		boolean status = false;
		List<WebElement> products = driver.findElements(homePageProducts);
		for (WebElement x : products) {
			if (x.isEnabled()) {
				status = true;
			} else {
				log.error("This product is not clickable: " + x.findElement(By.cssSelector("._3LU4EM")).getText());
				System.out.println("This product is not clickable: " + x.findElement(By.cssSelector("._3LU4EM")).getText());
				return false;
			}
		}
		return status;
	}

	public boolean productSpecificationPage(String searchProduct) throws InterruptedException {
		pd = new ProductDetailsPage(driver);
		boolean status = false;
		String parent = driver.getWindowHandle();
		WebElement search = driver.findElement(searchBox);
		search.sendKeys(searchProduct);
		search.sendKeys(Keys.ENTER);
		pd.mouseOver();
		List<WebElement> products = driver.findElements(searchedProductResult);
		for (WebElement x : products) {
			if (x.getText().equals(searchProduct)) {
				// System.out.println(x.getText());
				x.click();
				break;
			}
		}

		Set<String> allWindows = driver.getWindowHandles();
		System.out.println(allWindows.size());
		for (String x : allWindows) {
			if (!parent.equals(x)) {
				driver.switchTo().window(x);
				status = driver.findElement(specificationPageProduct).isDisplayed();
				break;
			}
		}
		driver.switchTo().window(parent);
		return status;
	}

	public void searchForProduct(String search) {
		WebElement searchProduct = driver.findElement(searchBox);
		searchProduct.sendKeys(search);
		searchProduct.sendKeys(Keys.ENTER);
	}

	public void logOut() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// To perform mouse and keyboard actions
		WebElement ele = driver.findElement(neel);
		Actions actions = new Actions(driver);
		actions.moveToElement(ele).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(logout));
		driver.findElement(logout).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(login));
		// Assert.assertTrue(driver.findElement(loginPopUp).isDisplayed());
	}

	public void openLoginModal() {
		driver.findElement(login).click();
	}

}
