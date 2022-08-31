package demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.LoginFunctinality;
import resources.Base;

public class LoginFunctionalityTest extends Base {
	public WebDriver driver;
	LoginFunctinality lp;

	private static Logger log = LogManager.getLogger(LoginFunctionalityTest.class.getName());

	@BeforeMethod
	public void startBrowser() {
		driver = startDriver();
		log.info("Browser started successfully");
		driver.get("https://www.flipkart.com/");
		log.info("Launched flipcart.com");
		lp = new LoginFunctinality(driver);
	}

	@Test(priority = 1)
	public void loginWithValidCreds() {
		log.info("Logging in with valid credentials");
		String user = lp.validMailValidpass();
		Assert.assertEquals(user, "Neel");
		log.info("Login in succes, username is neel ");
	}

	@Test(priority = 2)
	public void loginWithValidMailInvalidPass() {
		log.info("providing valid email and invalid password");
		String errMsg = lp.validMailInvalidPass();
		Assert.assertEquals(errMsg, "Your username or password is incorrect");
		log.info("got correct error message");
	}

	@Test(priority = 3)
	public void loginWithInvalidMailValidPass() {
		log.info("providing invalid email and valid password");
		String errMsg = lp.invalidMailValidPass();
		Assert.assertEquals(errMsg, "Please enter valid Email ID/Mobile number");
		log.info("got correct error message");
	}

	@Test(priority = 4)
	public void loginWithEmptyMailEmptyPass() {
		log.info("providing empty email and empty password");
		String errMsg = lp.emptyMailEmptyPass();
		Assert.assertEquals(errMsg, "Please enter valid Email ID/Mobile number");
		log.info("got correct error message");
	}

	@Test(priority = 5)
	public void loginWithEmptyMailValidPass() {
		log.info("providing empty email and valid password");
		String errMsg = lp.emptyMailValidpass();
		Assert.assertEquals(errMsg, "Please enter valid Email ID/Mobile number");
		log.info("got correct error message");
	}

	@Test(priority = 6)
	public void loginWithValidMailEmptyPass() {
		log.info("providing valid email and empty password");
		String errMsg = lp.validMailEmptyPass();
		Assert.assertEquals(errMsg, "Please enter Password");
		log.info("got correct error message");
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
		log.info("Closed the current browser window");
	}
}
