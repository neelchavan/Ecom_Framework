package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
	public WebDriver driver;
	HomePage hp;
	ProductDetailsPage pd;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
	}

	// locators
	By searchBox = By.cssSelector("._3704LK");
	By filtersText = By.xpath("//span[contains(text(), 'Filters')]");
	By sortByText = By.cssSelector("._5THWM1 span");
	By filterOptions = By.xpath("//section[@class='_2aDURW']/div/div");
	By sortingOptions = By.xpath("//div[@class='_5THWM1']/div");
	By searchResults = By.cssSelector("._10Ermr");
	By allNavLink = By.xpath("//nav[@class='yFHi8N']/a");
	By resultsEqulasTwoFour = By.cssSelector("._2kHMtA");
	By resultMoreThnTwoFour = By.xpath("//div[@class='_13oc-S']/div");

	// verify if filter options are available and enabled
	public boolean verifyFilterOptions() {
		boolean status = false;
		String text = driver.findElement(filtersText).getText();
		boolean filterTestExitst = text.equals("Filters");
		List<WebElement> filterOption = driver.findElements(filterOptions);
		for (WebElement x : filterOption) {
			if (x.isDisplayed() && x.isEnabled()) {
				status = true;
			} else {
				return false;
			}
		}
		if (filterTestExitst && status) {
			status = true;
		}
		return status;
	}

	// verify if sorting options are available and enabled
	public boolean verifySortingOptions() {
		boolean status = false;
		boolean sortingTestExist = driver.findElement(sortByText).isDisplayed();
		List<WebElement> sortingOption = driver.findElements(sortingOptions);
		for (WebElement x : sortingOption) {
			if (x.isDisplayed() && x.isEnabled()) {
				status = true;
			} else {
				return false;
			}
		}
		if (sortingTestExist && status) {
			status = true;
		}
		return status;
	}

	// how much products displayed on one page
	public String productsOnOnePage() {
		String searchResult = driver.findElement(searchResults).getText();
		String numOfProdOnOnePage = searchResult.split("– ")[1].split("of")[0].trim();
		System.out.println("There are " + numOfProdOnOnePage + " on one page.");
		return numOfProdOnOnePage;
	}

	// verify if all the navigation options are displayed and available
	public boolean navigationOptions() {
		boolean status = false;
		List<WebElement> navLinks = driver.findElements(allNavLink);
		for (WebElement x : navLinks) {
			if (x.isDisplayed() && x.isEnabled()) {
				status = true;
			} else {
				System.out.println(x.getText());
				return false;
			}
		}
		return status;
	}

	public void SearchProdAndOpenSpecific(String search) {
		hp = new HomePage(driver);
		pd = new ProductDetailsPage(driver);
		hp.searchForProduct(search);
		pd.mouseOver();
		String searchResult = driver.findElement(searchResults).getText();
		String numOfProdOnOnePage = searchResult.split("– ")[1].split("of")[0].trim();
		if (numOfProdOnOnePage.equals("24")) {
			driver.findElement(resultsEqulasTwoFour).click();
		} else {
			driver.findElement(resultMoreThnTwoFour).click();
		}
	}
}
