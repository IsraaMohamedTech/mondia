//Package that contains all project object classes
package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	// Create an Object from WebDriver Interface
	WebDriver driver;

	// Constructor for Home Page
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	By categoriesLink = By.xpath("//a[text()='Products']");

	By gameCategoryLink = By.xpath("//a[text()='Games']//parent::li");

	public WebElement categoriesLink() {
		return driver.findElement(categoriesLink);
	}

	public WebElement gameCategoryLink() {
		return driver.findElement(gameCategoryLink);
	}

}
