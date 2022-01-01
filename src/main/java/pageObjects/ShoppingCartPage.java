package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingCartPage {
	// Create an Object from WebDriver Interface
	WebDriver driver;

	// Constructor for ShoppingCart Page
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
	}

	By productName = By.className("text-link");

	By totalCost = By.xpath("//td[@class='cost-overview-amounts right'][1]");

	public List<WebElement> getProductsName() {
		return driver.findElements(productName);
	}

	public List<WebElement> getTotalCost() {
		return driver.findElements(totalCost);
	}

}
