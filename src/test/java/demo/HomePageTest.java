package demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import resources.Base;

public class HomePageTest extends Base {
	public WebDriver driver;
	HomePage hp;

	private static Logger log = LogManager.getLogger(HomePageTest.class.getName());

	@BeforeClass
	public void openBrowser() {
		driver = startDriver();
		log.info("Browser started successfully");
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
		hp.openLoginModal();
		String profileName = hp.loginToFlipcart();
		System.out.println(profileName);
		Assert.assertEquals(profileName, "Neel");
		log.info("Logged in, user is Neel");
	}

	@Test(priority = 3)
	public void verifyProdCatagories() {
		Assert.assertTrue(hp.verifyProdCatagories());
		log.info("catagories verified ");
	}

	@Test(priority = 4)
	public void verifySearchFunctionality() {
		boolean status = hp.verifySearchFunctionality("roadster");
		Assert.assertTrue(status);
		log.info("search functionality verified");
	}

	@Test(priority = 5)
	public void verifyProductClickableOrNot() {
		boolean status = hp.productsClickableOrNot();
		Assert.assertTrue(status);
		log.info("products clickable or not verified");
	}

	@Test(priority = 6)
	public void productSpecificationPageTest() throws InterruptedException {
		boolean status = hp.productSpecificationPage("APPLE iPhone 13 (Blue, 128 GB)");
		Assert.assertTrue(status);
		log.info("Product specification page verified");
	}

	@AfterMethod
	public void navigateToHomePage() {
		driver.get("https://www.flipkart.com/");
		log.info("NAVIGATED TO HOME PAGE");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
