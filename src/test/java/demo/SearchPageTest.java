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

public class SearchPageTest extends Base {
	public WebDriver driver;
	HomePage hp;
	SearchPage sp;
	ProductDetailsPage pd;

	private static Logger log = LogManager.getLogger(HomePageTest.class.getName());

	@BeforeMethod
	public void openBrowser() {
		driver = startDriver();
		log.info("Browser started successfully");
		driver.get("https://www.flipkart.com/");
		log.info("Launched flipcart.com");
		hp = new HomePage(driver);
		sp = new SearchPage(driver);
		pd = new ProductDetailsPage(driver);
		hp.closeLoginmodal();
	}

	@Test(priority = 1)
	public void verifyFilterOptionsTest() {
		hp.searchForProduct("hp laptops");
		pd.mouseOver();
		boolean status = sp.verifyFilterOptions();
		Assert.assertTrue(status);
		log.info("Verfied filter options successfully");
	}

	@Test(priority = 2)
	public void verifySortingOptionsTest() {
		hp.searchForProduct("hp laptops");
		pd.mouseOver();
		boolean status = sp.verifySortingOptions();
		Assert.assertTrue(status);
		log.info("Verfied sorting options successfully");
	}

	@Test(priority = 3)
	public void numOfProductsOnOnePage() {
		hp.searchForProduct("roadster");
		pd.mouseOver();
		String numOfProd = sp.productsOnOnePage();
		log.info("There are " + numOfProd + " on one page.");
	}

	@Test(priority = 4)
	public void navigationOptionsTest() {
		hp.searchForProduct("nike shoes");
		pd.mouseOver();
		boolean status = sp.navigationOptions();
		Assert.assertTrue(status);
		log.info("Verified nav button on search page successfully");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		log.info("Closed the current browser window");
	}
}
