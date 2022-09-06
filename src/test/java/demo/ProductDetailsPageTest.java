package demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.ProductDetailsPage;
import pageobjects.SearchPage;
import resources.Base;

public class ProductDetailsPageTest extends Base {
	public WebDriver driver;
	HomePage hp;
	ProductDetailsPage pd;
	SearchPage sp;

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
		hp.loginToFlipcart();
	}

	@Test(priority = 1)
	public void verifyProductImagePresence() {
		sp.SearchProdAndOpenSpecific("roadster");
	}

	@AfterMethod
	public void tearDown() {
		// close browser
		driver.quit();
	}
}
