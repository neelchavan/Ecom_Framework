package demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.ProductDetailsPage;
import pageobjects.SearchPage;
import resources.Base;
import utiltyMethods.WindowHandler;

public class ProductDetailsPageTest extends Base {
	public WebDriver driver;
	HomePage hp;
	ProductDetailsPage pd;
	SearchPage sp;
	WindowHandler wh;

	private static Logger log = LogManager.getLogger(ProductDetailsPageTest.class.getName());

	@BeforeMethod
	public void openBrowser() {
		driver = startDriver();
		log.info("Browser started successfully");
		driver.get("https://www.flipkart.com/");
		log.info("Launched flipcart.com");
		hp = new HomePage(driver);
		pd = new ProductDetailsPage(driver);
		sp = new SearchPage(driver);
		wh = new WindowHandler(driver);
		hp.loginToFlipcart();
	}

	@Test(priority = 1)
	public void verifyProductImagePresence() {
		boolean status = false;
		String parentWindow = driver.getWindowHandle();
		sp.SearchProdAndOpenSpecific("roadster");
		wh.handleSingleWindow(parentWindow);
		status = pd.productImagePresence();
		wh.getBackToDefaultWindow(parentWindow);
		Assert.assertTrue(status);
		log.info("Verified that product image is displayed correctly.");
	}

	@Test(priority = 2)
	public void productPricePresence() {
		boolean status = false;
		String parentWindow = driver.getWindowHandle();
		sp.SearchProdAndOpenSpecific("roadster");
		wh.handleSingleWindow(parentWindow);
		status = pd.productPricePresence();
		wh.getBackToDefaultWindow(parentWindow);
		Assert.assertTrue(status);
		log.info("Verified that product price details is displayed correctly.");
	}

	@Test(priority = 3)
	public void productSpecificationPresence() {
		boolean status = false;
		String parentWindow = driver.getWindowHandle();
		sp.SearchProdAndOpenSpecific("realme");
		wh.handleSingleWindow(parentWindow);
		status = pd.productSpecificationPresence();
		wh.getBackToDefaultWindow(parentWindow);
		Assert.assertTrue(status);
		log.info("Verified that product specification details is displayed correctly.");
	}

	@Test(priority = 4)
	public void prouctRatingsPresence() {
		boolean status = false;
		String parentWindow = driver.getWindowHandle();
		sp.SearchProdAndOpenSpecific("nokia");
		wh.handleSingleWindow(parentWindow);
		status = pd.prouctRatingsPresence();
		wh.getBackToDefaultWindow(parentWindow);
		Assert.assertTrue(status);
		log.info("Verified that product ratings details is displayed correctly.");
	}

	@Test(priority = 5)
	public void wishListOptionPresence() {
		boolean status = false;
		String parentWindow = driver.getWindowHandle();
		sp.SearchProdAndOpenSpecific("garam masala");
		wh.handleSingleWindow(parentWindow);
		status = pd.wishListOptionPresence();
		wh.getBackToDefaultWindow(parentWindow);
		Assert.assertTrue(status);
		log.info("Verified that wishlist option is displayed and enabled correctly.");
	}

	@Test(priority = 6)
	public void addToCartAndBuyNowButtons() {
		boolean status = false;
		String parentWindow = driver.getWindowHandle();
		sp.SearchProdAndOpenSpecific("durex");
		wh.handleSingleWindow(parentWindow);
		status = pd.addToCartAndBuyNow();
		wh.getBackToDefaultWindow(parentWindow);
		Assert.assertTrue(status);
		log.info("Verified that wishlist 'Add to cart' and 'Buy now' buttons is displayed and enabled correctly.");
	}

	@AfterMethod
	public void tearDown() {
		// logout and close browser
		hp.logOut();
		driver.quit();
	}
}
