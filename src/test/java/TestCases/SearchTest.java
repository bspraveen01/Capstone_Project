package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ProductPage;
import pageObjects.SearchPage;

public class SearchTest extends BaseTest {

    SearchPage searchPage;
    ProductPage productPage;

    String productName = "Apple MacBook Pro";

    @Test(priority = 0)
    public void searchAboutProduct() {
  
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);

  
        searchPage.search(productName);

 
        Assert.assertTrue(searchPage.hasSearchResults(), "Search results should be displayed");


        searchPage.openFirstProductDetailsPage();

   
        String actualProductName = productPage.getProductName();
        Assert.assertEquals(actualProductName, productName, "Product name should match the searched product");
    }
    @Test(priority = 1)
    public void userCanSearch() {
        searchPage = new SearchPage(driver);
        searchPage.search(productName);
        searchPage.openFirstProductDetailsPage();

        productPage = new ProductPage(driver);
        String actualName = productPage.getProductName();

        Assert.assertTrue(
                actualName.toLowerCase().contains(productName.toLowerCase()),
                "Expected product: " + productName + " but found: " + actualName
        );
    }

    
}
