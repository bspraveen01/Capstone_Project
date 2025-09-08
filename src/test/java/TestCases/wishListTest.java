package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Wishlist;
import pageObjects.ProductPage;
import pageObjects.SearchPage;

public class wishListTest extends BaseTest {

    private SearchPage searchPage;
    private ProductPage productPage;
    private Wishlist wishListPage;

    String productName = "Apple MacBook Pro";

    @Test(priority = 1)
    public void searchAboutProduct() {
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);

        searchPage.search(productName);
        searchPage.openFirstProductDetailsPage();

        Assert.assertEquals(productPage.getProductName(), productName, 
            "Product name should match searched product");
    }

    @Test(priority = 2)
    public void userCanAddProductToWishList() {
        productPage = new ProductPage(driver);

  
        productPage.addProductToWishList();

        driver.navigate().to("https://demo.nopcommerce.com/wishlist");

        wishListPage = new Wishlist(driver);


        Assert.assertTrue(wishListPage.getPageTitleWishlist().contains("Wishlist"), 
            "Page title should be Wishlist");
    }

    @Test(priority = 3)
    public void userCanRemoveProductFromWishList() {
        wishListPage = new Wishlist(driver);

        wishListPage.removeProductFromWishList();


        Assert.assertTrue(wishListPage.getEmptyWishlistMessage().contains("empty"),
            "Wishlist should be empty after removing product");
    }
}
