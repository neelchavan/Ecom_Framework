package demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import resources.Base;

public class HomePageTest extends Base {
	public WebDriver driver;
	HomePage hp;

	private static Logger log = LogManager.getLogger(HomePageTest.class.getName());

	@BeforeMethod
	public void openBrowser() {
		driver = startDriver();
		log.info("Browser started successfully");
		driver.get("https://www.flipkart.com/");
		log.info("Launched flipcart.com");
		hp = new HomePage(driver);
	}

	@Test(priority = 1)
	public void verifyFlipcartHomePage() {
		String url = hp.verifyHomePage();
		System.out.println(url);
		Assert.assertEquals(url, "https://www.flipkart.com/");

		log.info("On the home page");
	}

	@Test(priority = 2)
	public void verifyUserName() {
		log.info("Logging in to flipcart");
		String profileName = hp.loginToFlipcart();
		Assert.assertEquals(profileName, "Neel");
		log.info("Logged in, user is Neel");
	}

	@Test(priority = 3)
	public void verifyProdCatagories() {
		hp.closeLoginmodal();
		Assert.assertTrue(hp.verifyProdCatagories());
		log.info("catagories verified ");
	}

	@Test(priority = 4)
	public void verifySearchFunctionality() {
		hp.closeLoginmodal();
		boolean status = hp.verifySearchFunctionality("roadster");
		Assert.assertTrue(status);
		log.info("search functionality verified");
	}

	@Test(priority = 5)
	public void verifyProductClickableOrNot() {
		hp.closeLoginmodal();
		boolean status = hp.productsClickableOrNot();
		Assert.assertTrue(status);
		log.info("products clickable or not verified");
	}

	@Test(priority = 6)
	public void productSpecificationPageTest() throws InterruptedException {
		hp.closeLoginmodal();
		boolean status = hp.productSpecificationPage("APPLE iPhone 13 (Blue, 128 GB)");
		Assert.assertTrue(status);
		log.info("Product specification page verified");
	}

	@Test(priority = 7)
	public void logout() throws InterruptedException {
		hp.closeLoginmodal();
		log.info("Logged in, user is Neel");
		log.info("Logged out");
	}

	@AfterMethod
	public void tearDown() {
		// hp.logOut();
		driver.quit();
		log.info("Closed the current browser window");
	}
}
