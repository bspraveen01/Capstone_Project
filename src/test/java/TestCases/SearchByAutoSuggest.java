package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ProductPage;
import pageObjects.SearchPage;

public class SearchByAutoSuggest extends BaseTest {

    SearchPage searchPage;
    ProductPage productPage;

  
    String searchText = "iphone";
    String expectedProductName = "Apple iPhone 16 128GB";

    @Test(priority = 0)
    public void searchWithAutoSuggest() throws InterruptedException {
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);

   
        searchPage.searchByAutoSuggestion(searchText);
         Thread.sleep(2000);

        String actualProductName = productPage.getProductName();


        Assert.assertEquals(
            actualProductName,
            expectedProductName,
            " Product name should match the auto-suggested product"
        );
    }

    @Test(priority = 1, dependsOnMethods = "searchWithAutoSuggest")
    public void userCanSearchWithAutoSuggest() {
        try {
            searchPage = new SearchPage(driver);

            searchPage.searchByAutoSuggestion(expectedProductName);
            
            Thread.sleep(2000);

            String actualTitle = driver.getTitle().toLowerCase();

            Assert.assertTrue(
                actualTitle.contains("iphone") || actualTitle.contains("apple"),
                "Product page title does not match expected. Found: " + actualTitle
            );

            System.out.println(" Auto-suggest search worked: " + actualTitle);

        } catch (Exception e) {
            Assert.fail(" Test failed due to exception: " + e.getMessage());
        }
    }
}
