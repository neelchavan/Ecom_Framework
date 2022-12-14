package resources;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.HomePage;

public class Base {
	private WebDriver driver;
	HomePage hp;

	public static String browserName = null;

	public WebDriver startDriver() {
		// Calling the static method named getProperties() from PropertiesFile to set
		// the BrwoserName
		PropertiesFile.getProperties();

		if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equals("chrome")) {
			// to remove unwanted chrome browser warnings use
			// "webdriver.chrome.silentOutput".
			System.setProperty("webdriver.chrome.silentOutput", "true");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			driver = new ChromeDriver(options);
		}

		// maximize browser window
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));

		// launch flipcart
		driver.get("https://www.flipkart.com/");

		// close login modal
		hp = new HomePage(driver);
		hp.closeLoginmodal();
		return driver;
	}

	public String getScreenshotPath(String methodName, WebDriver driver) throws IOException {
		String timestamp = String.valueOf(java.time.LocalDateTime.now()).replace(':', '-');
		// this step stores screenshot into virtual location
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// move the scrShot from virtual location and store it into desired folder.
		String destination = System.getProperty("user.dir") + "\\reports\\" + "At_" + methodName + "_" + timestamp
				+ "_.png";
		// copy file at destination
		FileUtils.copyFile(source, new File(destination));
		return destination;

	}
}
