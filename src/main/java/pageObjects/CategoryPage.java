package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoryPage {

	// Create an Object from WebDriver Interface
	WebDriver driver;

	// Constructor for Category Page
	public CategoryPage(WebDriver driver) {
		this.driver = driver;
	}

	By breadcrumbItem = By.xpath("//span[@class='breadcrumb-item']//a[text()='Games']");

	public WebElement getBreadcrumbItem() {
		return driver.findElement(breadcrumbItem);
	}

	// Store css selector for prices on an Object of By Class
//	By prices = By.cssSelector("li div[class='item'] div[class='price']");
//	By productContainer = By.xpath("//div[@class='item']");
//	By addToCart = By.xpath("//a[text()='Add to Cart']");
//	By jcarouselNextActiveArrow = By.cssSelector("a[class='jcarousel-control-next jcarousel-border-next next-items']");
//	By jcarouselNextInactiveArrow = By
//			.cssSelector("a[class='jcarousel-control-next jcarousel-border-next next-items-inactive']");
//	By jcarouselPrevActiveArrow = By.cssSelector("a[class='jcarousel-control-prev jcarousel-border-prev prev-items']");
//	By jcarouselPrevInactiveArrow = By
//			.cssSelector("a[class='jcarousel-control-prev jcarousel-border-prev prev-items-inactive']");
//	By cartItems = By.xpath("//span[@id = 'basket-items']");
//	By productNames = By.className("item-title");
//
//	public List<WebElement> gategoriesLink() {
//		return driver.findElements(prices);
//	}
//
//	public List<WebElement> getProductContainer() {
//		return driver.findElements(productContainer);
//
//	}
//
//	public List<WebElement> getAddToCartButton() {
//		return driver.findElements(addToCart);
//
//	}
//
//	public WebElement getJcarouselNextActiveArrow() {
//		return driver.findElement(jcarouselNextActiveArrow);
//	}
//
//	public WebElement getJcarouselNextInactiveArrow() {
//		return driver.findElement(jcarouselNextInactiveArrow);
//	}
//
//	public WebElement getCartItems() {
//		return driver.findElement(cartItems);
//	}
//
//	public List<WebElement> getProductsName() {
//		return driver.findElements(productNames);
//
//	}
//
//	public WebElement getJcarouselPrevActiveArrow() {
//		return driver.findElement(jcarouselPrevActiveArrow);
//	}
//	
//	public WebElement getJcarouselPrevInactiveArrow() {
//		return driver.findElement(jcarouselPrevInactiveArrow);
//	}

}
