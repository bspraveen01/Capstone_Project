package TestCases;

import pageObjects.CartValidationPage;
import pageObjects.ProductPage;
import pageObjects.SearchPage;
import Utilities.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CardValidationTest extends BaseTest {

	@Test(priority = 1)
	public void testClearCompareList() {
	   
	    addProductsBySearch("MacBook", "Samsung");

	    System.out.println(" Navigating to Compare Page...");
	    driver.get(Constants.COMPARE_PAGE_URL);
	    CartValidationPage comparePage = new CartValidationPage(driver);

	    Assert.assertFalse(comparePage.isCompareTableEmpty(),
	            " Compare table should NOT be empty before clearing");

	    comparePage.clearCompareTable();

	    Assert.assertTrue(comparePage.isCompareTableEmpty(),
	            " Compare table should be empty after clearing");

	    System.out.println(" PASSED: Compare list cleared and verified empty.");
	}

	@Test(priority = 2)
	public void testAddAndDisplayProductsInCompare() {
	    addProductsBySearch("MacBook", "Samsung");

	    System.out.println("Navigating to Compare Page...");
	    driver.get(Constants.COMPARE_PAGE_URL);
	    CartValidationPage comparePage = new CartValidationPage(driver);

	    Assert.assertTrue(comparePage.firstProductName.isDisplayed(), "❌ First product should be displayed");
	    Assert.assertTrue(comparePage.secondProductName.isDisplayed(), "❌ Second product should be displayed");

	    comparePage.compareProducts();
	    System.out.println("PASSED: Products added and displayed in compare table.");
	}

	@Test(priority = 3)
	public void testCompareProductsDisplay() {
	    addProductsBySearch("HP Envy", "HTC One");

	    System.out.println("Navigating to Compare Page...");
	    driver.get(Constants.COMPARE_PAGE_URL);
	    CartValidationPage comparePage = new CartValidationPage(driver);

	    Assert.assertTrue(comparePage.firstProductName.isDisplayed(), "❌ First product missing");
	    Assert.assertTrue(comparePage.secondProductName.isDisplayed(), "❌ Second product missing");

	    System.out.println("PASSED: Compare products display validated.");
	}

	private void addProductsBySearch(String firstProductName, String secondProductName) {
	    try {
	   
	        System.out.println(" Searching and adding: " + firstProductName);
	        driver.get(Constants.BASE_URL); // go to home
	        SearchPage searchPage1 = new SearchPage(driver);
	        searchPage1.search(firstProductName);
	        searchPage1.clickFirstResult();
	        ProductPage productPage1 = new ProductPage(driver);
	        productPage1.addToCompareList();

	  
	        System.out.println(" Searching and adding: " + secondProductName);
	        driver.get(Constants.BASE_URL);
	        SearchPage searchPage2 = new SearchPage(driver);
	        searchPage2.search(secondProductName);
	        searchPage2.clickFirstResult();
	        ProductPage productPage2 = new ProductPage(driver);
	        productPage2.addToCompareList();

	    } catch (Exception e) {
	        System.err.println("❌ Failed to add products: " + e.getMessage());
	        throw e;
	    }
	}


	

}
