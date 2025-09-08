package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.ProductPage;  // Renamed from ProductDetailsPage
import pageObjects.SearchPage;


public class CartTest extends BaseTest {

    private SearchPage searchPage;
    private ProductPage productPage; // Updated class name to match your structure
    private CartPage cartPage;

    private final String PRODUCT_NAME = "Apple MacBook Pro";
    private final String QUANTITY = "4";

    @Test(priority = 1)
    public void userCanSearchAndOpenProduct() {
        searchPage = new SearchPage(driver);
        searchPage.search(PRODUCT_NAME); // Updated method name to lowercase (convention)

        Assert.assertTrue(searchPage.hasSearchResults(), "No search results for: " + PRODUCT_NAME);

        searchPage.openFirstProductDetailsPage();
        productPage = new ProductPage(driver);

    }

    @Test(priority = 2)
    public void userCanAddProductToCart() {
        productPage.addToCart();

        driver.navigate().to("https://demo.nopcommerce.com/cart");
        cartPage = new CartPage(driver);

        String productNameInCart = cartPage.getProductNameFromCart();
        Assert.assertTrue(productNameInCart.contains("MacBook"),
            "Product not found in cart. Found: " + productNameInCart);
    }

    @Test(priority = 3)
    public void userCanUpdateProductQuantity() {
        cartPage.updateProductQuantityCart(QUANTITY);

        String totalPrice = cartPage.getTotalPrice();
        System.out.println("Cart Total after update: " + totalPrice);
        Assert.assertNotNull(totalPrice, "Total price is null!");
        Assert.assertFalse(totalPrice.trim().isEmpty(), "Total price is empty!");

   
    }

    @Test(priority = 4)
    public void userCanRemoveProductFromCart() {
        cartPage.removeProductFromCart();

        // Wait for cart to update and show empty message
        String emptyMessage = cartPage.getEmptyCartMessage();
        System.out.println("Empty Cart Message: " + emptyMessage);

 
        Assert.assertTrue(emptyMessage.contains("empty"),
            "Cart is not empty Message: " + emptyMessage);
    }
}