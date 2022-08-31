package demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageobjects.HomePage;
import pageobjects.SearchPage;
import resources.Base;

public class SearchPageTest extends Base {
	public WebDriver driver;
	HomePage hp;
	SearchPage sp;

	private static Logger log = LogManager.getLogger(HomePageTest.class.getName());

	@BeforeMethod
	public void openBrowser() {
		driver = startDriver();
		log.info("Browser started successfully");
		driver.get("https://www.flipkart.com/");
		log.info("Launched flipcart.com");
		hp = new HomePage(driver);
		hp.loginToFlipcart();
		sp = new SearchPage(driver);
	}

	@Test(priority = 1)
	public void verifyFilterOptionsTest() {
		hp.searchForProduct("roadster");
		boolean status = sp.verifyFilterOptions();
		Assert.assertTrue(status);
		log.info("Verfied filter options successfully");
	}

	@AfterMethod
	public void tearDown() {
		hp.logOut();
		driver.quit();
		log.info("Closed the current browser window");
	}
}
