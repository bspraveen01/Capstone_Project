package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import utils.Constants;

public class CheckoutTest extends BaseTest {

    HomePage homePage;
    SearchPage searchPage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @Test(priority = 1)
    public void testCheckoutProcess() {
        try {
           
            driver.get(Constants.BASE_URL);
            homePage = new HomePage(driver);

            searchPage = new SearchPage(driver);
            searchPage.search("MacBook");
            searchPage.clickFirstResult();


            productPage = new ProductPage(driver);
            productPage.addToCart();
      
            cartPage = new CartPage(driver);
            cartPage.closeNotificationIfPresent();
            cartPage.clickCartIcon();

            checkoutPage = new CheckoutPage(driver);
            Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed(), "Checkout page did not load correctly.");
            checkoutPage.agreeAndCheckout();

            checkoutPage.loginIfRedirected(Constants.USER_EMAIL, Constants.USER_PASSWORD);

            checkoutPage.fillBillingAddress(
                "India",
                "Telangana",
                "Hyderabad",
                "Madhapur",
                "500081",
                "9876543210"
            );

          
            checkoutPage.continueButton();

            if (!checkoutPage.isShippingMethodSelected()) {
                checkoutPage.selectBoxShipping();
            }
            checkoutPage.continueShipping();
            Assert.assertTrue(checkoutPage.isShippingMethodSelected(), "Shipping method not selected.");

            checkoutPage.shippingradio();
            checkoutPage.shippingBtn();

            checkoutPage.selectRadioPaymentMethod();
            checkoutPage.selectPaymentContinue();

            checkoutPage.confirmPaymentInfo();
            checkoutPage.confirmOrder();

        
            Assert.assertTrue(
                checkoutPage.isOrderSuccessMessageDisplayed(),
                "Checkout test failed: Success message not displayed"
            );
            System.out.println(" PASSED: Checkout flow completed successfully.");

        } catch (Exception e) {
            Assert.fail(" Checkout test failed: " + e.getMessage());
        }
    }
}
