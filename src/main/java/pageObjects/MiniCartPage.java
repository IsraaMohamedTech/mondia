package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MiniCartPage {
	// Create an Object from WebDriver Interface
	WebDriver driver;

	// Constructor for MiniCart Page
	public MiniCartPage(WebDriver driver) {
		this.driver = driver;
	}

	By miniCart = By.id("shopping-cart");
	
	By miniCartItemsTitle = By.cssSelector("[id='shopping-cart'] [class='shopping-cart-item-title']");

	public WebElement getMiniCart() {
		return driver.findElement(miniCart);
	}
	
	public List<WebElement> getMiniCartItemsTitle() {
		return driver.findElements(miniCartItemsTitle);
	}

}
