package mondia.konakart;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import pageObjects.Jcarousel;
import pageObjects.ProductPage;
import pageObjects.TopBar;

public class Utilities {

	public void scrollDown(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)"); // Scroll vertically down by 1000 pixels

	}

	public void jcarouselNext(WebDriver driver) {
		Jcarousel jcarousel = new Jcarousel(driver);
		WebElement jcarouselNextActiveArrow = jcarousel.getJcarouselNextActiveArrow();
		jcarouselNextActiveArrow.click();
	}

	public void jcarouselPerv(WebDriver driver) {
		Jcarousel jcarousel = new Jcarousel(driver);
		WebElement jcarouselPrevActiveArrow = jcarousel.getJcarouselPrevActiveArrow();
		jcarouselPrevActiveArrow.click();
	}

	public List<WebElement> getProductContainer(WebDriver driver) {
		Jcarousel jcarousel = new Jcarousel(driver);
		List<WebElement> productContainer = jcarousel.getProductContainer();
		return productContainer;

	}

	public int getJcarouselItemsCount(WebDriver driver) {
		List<WebElement> productContainer = getProductContainer(driver);
		int productsListLength = productContainer.size();
		return productsListLength - 1;

	}

	public boolean checkIfTheEndOfCarusal(WebDriver driver) {
		Jcarousel jcarousel = new Jcarousel(driver);
		WebElement jcarouselNextInactiveArrow = jcarousel.getJcarouselNextInactiveArrow();
		System.out.println(jcarouselNextInactiveArrow.isDisplayed());
		return true;
	}

	public boolean checkIfTheBeginningOfCarusal(WebDriver driver) {
		Jcarousel jcarousel = new Jcarousel(driver);
		WebElement jcarouselPrevInactiveArrow = jcarousel.getJcarouselPrevInactiveArrow();
		System.out.println(jcarouselPrevInactiveArrow.isDisplayed());
		return true;
	}

	public void moveToLastProduct(WebDriver driver) {
		List<WebElement> productContainer = getProductContainer(driver);
		Actions action = new Actions(driver);
		int productsListLength = getJcarouselItemsCount(driver);
		action.moveToElement(productContainer.get(productsListLength)).perform();
	}

	public void moveToFirstProduct(WebDriver driver) {
		List<WebElement> productContainer = getProductContainer(driver);
		Actions action = new Actions(driver);
		action.moveToElement(productContainer.get(0)).perform();
	}

	public void clickOnFirstProduct(WebDriver driver) {
		List<WebElement> productContainer = getProductContainer(driver);
		WebElement firstProduct = productContainer.get(0);
		Actions action = new Actions(driver);
		action.moveToElement(productContainer.get(0)).perform();
		action.moveToElement(productContainer.get(0)).click().perform();


	}

	public String getProductName(WebDriver driver, int index) {
		Jcarousel jcarousel = new Jcarousel(driver);
		List<WebElement> productNames = jcarousel.getProductsName();
		String productName = productNames.get(index).getText();
		return productName;
	}
	
	public String getProductPrice(WebDriver driver, int index) {
		Jcarousel jcarousel = new Jcarousel(driver);
		List<WebElement> productPrices = jcarousel.getProductsPrice();
		String productPrice = productPrices.get(index).getText();
		return productPrice;
	}
	
	public void selectProductQty(ProductPage productPage , String qty) {
		List<WebElement> allOptions = productPage.getProductQuantityOptions();
		for (int i = 0; i < allOptions.size(); i++) {
			if (allOptions.get(i).getText().equalsIgnoreCase(qty)) {
				allOptions.get(i).click();
				break;
			}
		}
	}
	
	public void moveToMiniCart(WebDriver driver) {
		TopBar topBar = new TopBar(driver);
		Actions Action = new Actions(driver);
		Action.moveToElement(topBar.getMiniCart()).perform();
	}
	
	public void openShoppingCart(WebDriver driver) {
		TopBar topBar = new TopBar(driver);
		WebElement shoppingCartLink = topBar.getMiniCart();
		Actions action = new Actions(driver);
		action.moveToElement(shoppingCartLink).perform();
		shoppingCartLink.click();	
	}
	
	public void createCartAddedProductsSheet() throws IOException {
		DataDriven dataDriven = new DataDriven();
		dataDriven.createWork();
	}
	
	public void addAddedProductToSheet(String name,String price,int rowIndex) throws IOException {
		DataDriven dataDriven = new DataDriven();
		String fileName = "CartItems.xlsx";
		dataDriven.addAddedProductsToSheet(fileName,name, price,rowIndex);
		
	}

}
