package playground;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CaptureScrnShotStandAloneTest {
	public WebDriver driver;

	@Test
	public void testScrnCaptFunctionality() throws IOException {
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "D:\\Neel\\tools\\chromeD\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.get("https://www.geeksforgeeks.org/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// getScreenshotPath(driver);
		int linkCountByTagname = driver.findElements(By.tagName("a")).size();
		int linkCountByXpath = driver.findElements(By.xpath("//a")).size();
		System.out.println(linkCountByTagname);
		System.out.println(linkCountByXpath);
		driver.close();
	}

	public static void getScreenshotPath(WebDriver driver) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// this step stores screenshot into virtual location
//		File source = ts.getScreenshotAs(OutputType.FILE);
		// move the scrShot from virtual location and store it into desired folder.
		String destination = "D:\\Neel\\eclipse-workspace\\SeleFramework/reports/scrnCaptStdnAlone.png";
		// copy file at destination
		FileUtils.copyFile(source, new File(destination));
		// return destination;

	}
	
	@Test
	public void str() {
		String a = null;
		a = "neel";
		a = "chavan";
		System.out.println(a);
		
	}
}
