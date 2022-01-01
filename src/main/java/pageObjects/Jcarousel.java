package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Jcarousel {

	// Create an Object from WebDriver Interface
	WebDriver driver;

	// Constructor for Category Page
	public Jcarousel(WebDriver driver) {
		this.driver = driver;
	}

	By jcarouselNextActiveArrow = By.cssSelector("a[class='jcarousel-control-next jcarousel-border-next next-items']");

	By jcarouselNextInactiveArrow = By
			.cssSelector("a[class='jcarousel-control-next jcarousel-border-next next-items-inactive']");

	By jcarouselPrevActiveArrow = By.cssSelector("a[class='jcarousel-control-prev jcarousel-border-prev prev-items']");

	By jcarouselPrevInactiveArrow = By
			.cssSelector("a[class='jcarousel-control-prev jcarousel-border-prev prev-items-inactive']");
	
	By productContainer = By.xpath("//div[@class='item']");
	
	By addToCart = By.xpath("//a[text()='Add to Cart']");
	
	By productNames = By.className("item-title");
	
	By productPrices = By.className("price");


	public WebElement getJcarouselNextActiveArrow() {
		return driver.findElement(jcarouselNextActiveArrow);

	}

	public WebElement getJcarouselNextInactiveArrow() {
		return driver.findElement(jcarouselNextInactiveArrow);
	}

	public WebElement getJcarouselPrevActiveArrow() {
		return driver.findElement(jcarouselPrevActiveArrow);
	}

	public WebElement getJcarouselPrevInactiveArrow() {
		return driver.findElement(jcarouselPrevInactiveArrow);
	}
	
	public List<WebElement> getProductContainer() {
		return driver.findElements(productContainer);
	}
	
	public List<WebElement> getAddToCartButton() {
		return driver.findElements(addToCart);
	}
	
	public List<WebElement> getProductsName() {
		return driver.findElements(productNames);
	}
	
	public List<WebElement> getProductsPrice() {
		return driver.findElements(productPrices);

	}

}
