package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TopBar {

	// Create an Object from WebDriver Interface
	WebDriver driver;

	// Constructor for Category Page
	public TopBar(WebDriver driver) {
		this.driver = driver;
	}

	By cartItems = By.id("basket-items");
	
	By miniCart = By.id("shopping-cart");


	public WebElement getCartItems() {
		return driver.findElement(cartItems);
	}
	
	public WebElement getMiniCart() {
		return driver.findElement(miniCart);
	}

}
