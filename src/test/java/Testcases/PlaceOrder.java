package Testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import mondia.konakart.Common;
import mondia.konakart.DataDriven;
import mondia.konakart.Utilities;
import pageObjects.CategoryPage;
import pageObjects.HomePage;
import pageObjects.Jcarousel;
import pageObjects.ProductPage;
import pageObjects.ShoppingCartPage;
import pageObjects.TopBar;
import pageObjects.MiniCartPage;

public class PlaceOrder extends Common {

	WebDriver driver;
	int rowIndex = 0;

	@BeforeSuite
	public void setUp() {

		try {
			driver = initializeDriver();
			String websiteUrl = prop.getProperty("websiteUrl");
			driver.get(websiteUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority = 1)
	public void selectCategory() throws IOException {
		HomePage homePage = new HomePage(driver);
		CategoryPage categoryPage = new CategoryPage(driver);
		
		Actions Action = new Actions(driver);
		Action.moveToElement(homePage.categoriesLink()).click().perform();
		
		homePage.gameCategoryLink().click();
		Action.moveToElement(homePage.gameCategoryLink()).click().perform();
		
		String breadcrumbItemText = categoryPage.getBreadcrumbItem().getText();
		assertEquals("Games", breadcrumbItemText,
				"Navigate to Game categories page is faild");
	}
	

	@Test(priority = 2)
	public void productPrice() throws IOException {
		CategoryPage categoryPage = new CategoryPage(driver);
		Utilities utilities = new Utilities();

		String xl = "ProductPrices.xlsx";
		String Sheet = "Sheet1";
		DataDriven dataDriven = new DataDriven();
		int rowCount = dataDriven.getRowCount(xl, Sheet);

		for (int i = 1; i <= rowCount; i++) {
			String from = dataDriven.getCellValue(xl, Sheet, i, 0);
			String to = dataDriven.getCellValue(xl, Sheet, i, 1);

			for (int j = 0; j < utilities.getProductContainer(driver).size(); j++) {
				Double price = Double.valueOf(utilities.getProductPrice(driver, i).replace("$", ""));
				Assert.assertTrue(price >Double.valueOf(from) && price < Double.valueOf(to), " There are products price not between 39 and 80.");
				

			}
		}

	}

	@Test(priority = 3)
	public void addLastProductToCart() throws InterruptedException, IOException {
		Utilities utilities = new Utilities();
		Jcarousel jcarousel = new Jcarousel(driver);
		Actions action = new Actions(driver);
		int lastProductIndex = utilities.getJcarouselItemsCount(driver);

		utilities.scrollDown(driver);
		Thread.sleep(3000);
		utilities.jcarouselNext(driver);
		Thread.sleep(3000);
		utilities.checkIfTheEndOfCarusal(driver);
		Thread.sleep(3000);
		utilities.moveToLastProduct(driver);
		action.moveToElement(jcarousel.getAddToCartButton().get(lastProductIndex)).click().perform();

		String productName = utilities.getProductName(driver, lastProductIndex);
		String productPrice = utilities.getProductPrice(driver, lastProductIndex).replace("$", "");
		utilities.createCartAddedProductsSheet();
		utilities.addAddedProductToSheet(productName, productPrice, ++rowIndex);
	}

	@Test(priority = 4)
	public void verifyLastItemAdded() throws InterruptedException {
		Thread.sleep(3000);
		TopBar topBar = new TopBar(driver);
		WebElement cartItems = topBar.getCartItems();
		String cartItemsCount = cartItems.getAttribute("innerHTML").replaceAll("[^a-zA-Z0-9]", "");
		assertEquals(cartItemsCount, "1",
				"Expected only one product with 1 quantity. Actual more than product/s quanity");

	}

	@Test(priority = 5)
	public void checkFirstProductName() throws InterruptedException {
		ProductPage productPage = new ProductPage(driver);
		Utilities utilities = new Utilities();
		utilities.jcarouselPerv(driver);
		Thread.sleep(3000);
		utilities.checkIfTheBeginningOfCarusal(driver);
		String productNameInCategoryPage = utilities.getProductName(driver, 0);
		utilities.clickOnFirstProduct(driver);
		String productNameInProductPage = productPage.getProductName().getAttribute("innerHTML");
		assertEquals(productNameInProductPage, productNameInCategoryPage,
				"The game name is not the same as the one you clicked");

	}

	@Test(priority = 6)
	public void verifyProductScreenshots() {
		ProductPage productPage = new ProductPage(driver);
		int productGallerySize = productPage.getProductGallery().size();
		int expetedProductGallerySize = 4;
		assertEquals(productGallerySize, expetedProductGallerySize, "The game has more than 4 screenshots");
	}

	@Test(priority = 7)
	public void addToCart() throws InterruptedException {
		Utilities utilities = new Utilities();
		ProductPage productPage = new ProductPage(driver);

		utilities.selectProductQty(productPage, "2");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", productPage.getAddToCart());
	}

	@Test(priority = 8)
	public void verifyItemAdded() throws InterruptedException, IOException {
		Thread.sleep(3000);
		Utilities utilities = new Utilities();
		ProductPage productPage = new ProductPage(driver);
		MiniCartPage miniCartPage = new MiniCartPage(driver);

		String detailPageItemName = productPage.getProductName().getText();
		String detailPageItemPrice = productPage.getProductPrice().getAttribute("innerHTML").replace("$", "");

		utilities.moveToMiniCart(driver);

		List<WebElement> shoppingCartItemsTitle = miniCartPage.getMiniCartItemsTitle();
		String cartPageItemName = shoppingCartItemsTitle.get(0).getText();

		assertEquals(cartPageItemName, detailPageItemName, "The product has not been added");
		utilities.addAddedProductToSheet(detailPageItemName, detailPageItemPrice, ++rowIndex);

	}

	@Test(priority = 10)
	public void verifyCartItems() throws IOException {
		Utilities utilities = new Utilities();
		utilities.openShoppingCart(driver);
		ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
		String totalCost = shoppingCartPage.getTotalCost().get(0).getText().replace("$", "");
		List<WebElement> productsInCart = shoppingCartPage.getProductsName();

		String xl = "CartItems.xlsx";
		String Sheet = "Cart Products";
		DataDriven dataDriven = new DataDriven();
		int rowCount = dataDriven.getRowCount(xl, Sheet);
		Double totalCostInSheet = 0.0;
		for (int i = 1; i <= rowCount; i++) {
			String productName = dataDriven.getCellValue(xl, Sheet, i, 0);
			String productPrice = dataDriven.getCellValue(xl, Sheet, i, 1);
			totalCostInSheet += Double.parseDouble(productPrice);

			for (int j = 0; j < productsInCart.size(); j++) {
				String productNameInCart = productsInCart.get(j).getText();
				if (productNameInCart.equalsIgnoreCase(productName)) {
					Assert.assertTrue(true);
				} else
					Assert.assertTrue(true);
			}
		}
		assertEquals(totalCost, String.valueOf(totalCostInSheet), "Subtotal is not the total price of the added items");

	}
	@AfterSuite
	public void close_browser() throws Throwable {
		driver.close();
	}

}
