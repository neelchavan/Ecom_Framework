package demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginFunctinality;
import resources.Base;

public class LoginFunctionalityTest extends Base {
	public WebDriver driver;
	LoginFunctinality lp;
	HomePage hp;

	private static Logger log = LogManager.getLogger(LoginFunctionalityTest.class.getName());

	@BeforeClass
	public void startBrowser() {
		driver = startDriver();
		log.info("Browser started successfully");
		log.info("Launched flipcart.com");
		lp = new LoginFunctinality(driver);
		hp = new HomePage(driver);
	}

	@Test(priority = 1)
	public void loginWithValidCreds() {
		log.info("Logging in with valid credentials");
		hp.openLoginModal();
		String user = lp.validMailValidpass();
		Assert.assertEquals(user, "Neel");
		hp.logOut();
		log.info("Login in succes, username is neel ");
	}

	@Test(priority = 2)
	public void loginWithValidMailInvalidPass() {
		log.info("providing valid email and invalid password");
		hp.openLoginModal();
		String errMsg = lp.validMailInvalidPass();
		hp.closeLoginmodal();
		Assert.assertEquals(errMsg, "Your username or password is incorrect");
		log.info("got correct error message");
	}

	@Test(priority = 3)
	public void loginWithInvalidMailValidPass() {
		log.info("providing invalid email and valid password");
		hp.openLoginModal();
		String errMsg = lp.invalidMailValidPass();
		hp.closeLoginmodal();
		Assert.assertEquals(errMsg, "Please enter valid Email ID/Mobile number");
		log.info("got correct error message");
	}

	@Test(priority = 4)
	public void loginWithEmptyMailEmptyPass() {
		log.info("providing empty email and empty password");
		hp.openLoginModal();
		String errMsg = lp.emptyMailEmptyPass();
		hp.closeLoginmodal();
		Assert.assertEquals(errMsg, "Please enter valid Email ID/Mobile number");
		log.info("got correct error message");
	}

	@Test(priority = 5)
	public void loginWithEmptyMailValidPass() {
		log.info("providing empty email and valid password");
		hp.openLoginModal();
		String errMsg = lp.emptyMailValidpass();
		hp.closeLoginmodal();
		Assert.assertEquals(errMsg, "Please enter valid Email ID/Mobile number");
		log.info("got correct error message");
	}

	@Test(priority = 6)
	public void loginWithValidMailEmptyPass() {
		log.info("providing valid email and empty password");
		hp.openLoginModal();
		String errMsg = lp.validMailEmptyPass();
		hp.closeLoginmodal();
		Assert.assertEquals(errMsg, "Please enter Password");
		log.info("got correct error message");
	}

	@AfterMethod
	public void navigateToHomePage() {
		// hp.logOut();
		// driver.get("https://www.flipkart.com/");
		log.info("NAVIGATED TO HOME PAGE");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
