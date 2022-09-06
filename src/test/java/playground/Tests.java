package playground;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogExporter;
import junit.framework.Assert;

public class Tests {
	public static WebDriver driver;

	public static void switchTab(String parent) {
		Set<String> child = driver.getWindowHandles();
		for (String x : child) {
			if (!parent.equals(x)) {
				driver.switchTo().window(x);
			}
		}
	}

	@Test
	public void sampleTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "D:\\Neel\\tools\\chromeD\\chromedriver.exe");
		driver = new ChromeDriver();

//		maximize browser window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://crio-qkart-frontend-qa.vercel.app/");
		System.out.println(driver.getCurrentUrl());
		driver.findElement(By.linkText("Privacy policy")).click();
		String parent = driver.getWindowHandle();
		switchTab(parent);
		System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'We')]")).isDisplayed());
		driver.switchTo().window(parent);
		System.out.println(driver.getCurrentUrl());
		driver.quit();
	}
}
