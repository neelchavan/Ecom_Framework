package playground;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogExporter;

public class Tests {
	public WebDriver driver;
	List<List<String>> expectedTableBody = Arrays.asList(Arrays.asList("6", "6", "40", "9.8"),
			Arrays.asList("7", "7", "41", "10.2"), Arrays.asList("8", "8", "42", "10.6"), Arrays.asList("9", "9", "43", "11"),
			Arrays.asList("10", "10", "44", "11.5"), Arrays.asList("11", "11", "45", "12.2"),
			Arrays.asList("12", "12", "46", "12.6"));

	List<String> tableBodyTxt = new ArrayList<String>();
	List<String> sortedExpList = new ArrayList<String>();

	@Test
	public void sampleTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "D:\\Neel\\tools\\chromeD\\chromedriver.exe");
		driver = new ChromeDriver();

//		maximize browser window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://www.flipkart.com");
		System.out.println(driver.getCurrentUrl());

//		driver.get("https://crio-qkart-frontend-qa.vercel.app/");
//		driver.findElement(By.name("search")).sendKeys("running shoes");
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//button[contains(text(), 'Size chart')]")).click();
//		Thread.sleep(2000);
//		List<WebElement> tableBody = driver.findElements(By.xpath("//table/tbody/tr/td"));
//		for (WebElement x : tableBody) {
//			tableBodyTxt.add(x.getText());
//		}
//
//		// print the expectedTableBody elements
//		for (int i = 0; i < expectedTableBody.size(); i++) {
//			for (int j = 0; j < expectedTableBody.get(i).size(); j++) {
//				// System.out.print(expectedTableBody.get(i).get(j) + " ,");
//				sortedExpList.add(expectedTableBody.get(i).get(j));
//			}
//		}
//
//		System.out.println(expectedTableBody.size());
//		System.out.println(tableBodyTxt.size());
//		// System.out.println(expectedTableBody.toString());
//		System.out.println(tableBodyTxt.toString());
//		System.out.println(sortedExpList.toString());
//		driver.quit();
//		System.out.println("equal or not: " + tableBodyTxt.equals(sortedExpList));
	}
}
