package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {

	// Create an Object from WebDriver Interface
	WebDriver driver;

	// Constructor for Product Page
	public ProductPage(WebDriver driver) {
			this.driver = driver;
		}

	// Store css selector for prices on an Object of By Class
	By productName = By.id("page-title");
	
	By productGallery = By.cssSelector("div[id='gallery_nav'] a");
	
	By prodcutQuantityDropDown = By.id("prodQuantityId");
	
	By productButtons = By.cssSelector("div[class='product-buttons']");
	
	By addToCart = By.cssSelector("a[class='add-to-cart-button-big button small-rounded-corners']");
	
	By prodcutQuantityOptions = By.xpath("//select[@name='prodQuantity']//option");
	
	By productPrice = By.xpath("//span[@class='product-price-current']");

	public WebElement getProductName() {
		return driver.findElement(productName);
	}
	
	public List<WebElement> getProductGallery(){
		return driver.findElements(productGallery);
	}

	public WebElement getProductQuantity() {
		return driver.findElement(prodcutQuantityDropDown);
	}
	
	public WebElement getAddToCart() {
		return driver.findElement(addToCart);
	}
	
	public WebElement getProductButtons() {
		return driver.findElement(productButtons);
	}
	
	public List<WebElement> getProductQuantityOptions(){
		return driver.findElements(prodcutQuantityOptions);
	}
	
	public WebElement getProductPrice() {
		return driver.findElement(productPrice);
	}
}
