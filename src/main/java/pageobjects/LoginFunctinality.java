package pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginFunctinality {
	public WebDriver driver;
	LoginFunctinality lf;
	String validMail = "neelchavan418@gmail.com";
	String validPass = "engineer1000";

	public LoginFunctinality(WebDriver driver) {
		this.driver = driver;
	}

	By loginPopUp = By.xpath("//div[@class='_3oBhRa col col-2-5 _4H6HH5']");
	By emailField = By.cssSelector("input[class='_2IX_2- VJZDxU'][type='text']");
	By passwrdField = By.cssSelector("input[class='_2IX_2- _3mctLh VJZDxU'][type='password']");
	By login = By.partialLinkText("Login");
	By logout = By.xpath("//div[@class='_3vhnxf'][contains(text(), 'Logout')]");
	By submit = By.cssSelector("button[class='_2KpZ6l _2HKlqd _3AWRsL']");
	By neel = By.xpath("//div[contains(text(), 'Neel')]");
	By mobile = By.xpath("//div[contains(text(),'Mobiles')]");
	By errMsg = By.cssSelector("span[class='_2YULOR'] span");

	public void loginMethod(String usrname, String pass) {
		driver.findElement(emailField).sendKeys(usrname);
		driver.findElement(passwrdField).sendKeys(pass);
		driver.findElement(submit).click();
	}

	// valid mail and valid password
	public String validMailValidpass() {
		String profileName = null;
		// the line below is for explicit wait.
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		lf = new LoginFunctinality(driver);
		lf.loginMethod(validMail, validPass);
		wait.until(ExpectedConditions.visibilityOfElementLocated(neel));
		profileName = driver.findElement(neel).getText();
		return profileName;
	}

	// valid mail and invalid passowrd
	public String validMailInvalidPass() {
		String errorMsg = null;
		lf = new LoginFunctinality(driver);
		lf.loginMethod(validMail, "123");
		errorMsg = driver.findElement(errMsg).getText();
		return errorMsg;
	}

	// invalid mail and valid password
	public String invalidMailValidPass() {
		String errorMsg = null;
		lf = new LoginFunctinality(driver);
		lf.loginMethod("isdf", validPass);
		errorMsg = driver.findElement(errMsg).getText();
		return errorMsg;
	}

	// empty mail and empty pass
	public String emptyMailEmptyPass() {
		String errorMsg = null;
		lf = new LoginFunctinality(driver);
		lf.loginMethod("", "");
		errorMsg = driver.findElement(errMsg).getText();
		return errorMsg;
	}

	// empty mail valid pass
	public String emptyMailValidpass() {
		String errorMsg = null;
		lf = new LoginFunctinality(driver);
		lf.loginMethod("", validPass);
		errorMsg = driver.findElement(errMsg).getText();
		return errorMsg;
	}

	// valid mail and empty pass
	public String validMailEmptyPass() {
		String errorMsg = null;
		lf = new LoginFunctinality(driver);
		lf.loginMethod(validMail, "");
		errorMsg = driver.findElement(errMsg).getText();
		return errorMsg;
	}
}
