package TestCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;

public class SmokeTestAll extends BaseTest {

    HomePage home;

    @Test(priority = 1)
    public void validateHomePageLoaded() {
        home = new HomePage(driver);
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains("nopCommerce demo store"), "Home page did not load correctly!");
    }

    @Test(priority = 2)
    public void validateHomePageTitle() {
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains("nopCommerce demo store"), "Title mismatch");
    }

    @Test(priority = 3)
    public void validateHomePageURL() {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, properties.getProperty("baseURL"), "URL mismatch");
    }

    @Test(priority = 4)
    public void validateLogoDisplayed() {
        home = new HomePage(driver);
        Assert.assertTrue(home.islogoDisplayed(), "Logo not visible on Home Page");
    }

    @Test(priority = 5)
    public void validateRegisterButtonClickable() {
        home = new HomePage(driver);
        Assert.assertTrue(home.isLoginDisplayed(), "Register/Login button not visible");
        home.regstr();
        driver.navigate().back();
    }

    @Test(priority = 6)
    public void validateLoginButtonClickable() {
        home = new HomePage(driver);
        home.login();
        driver.navigate().back();
    }

    @Test(priority = 7)
    public void validateScrollToContactUs() {
        home = new HomePage(driver);
        home.openContactUsPage();
        Assert.assertTrue(home.contactlink.isDisplayed(), "Contact Us link not visible after scroll");
    }

    @Test(priority = 8)
    public void validateTopMenuHover() {
        home = new HomePage(driver);
        home.hoverOnComputers();
        home.hoverOnElectronics();
        home.hoverOnApparel();
        home.hoverOnDigitalDownloads();
        home.hoverOnBooks();
    }

    @Test(priority = 9)
    public void validateCurrencyDropdown() {
        home = new HomePage(driver);
        home.selectCurrency();
    }

    @Test(priority = 10)
    public void validateCartIconClickable() {
        home = new HomePage(driver);
        Assert.assertTrue(home.isCartClickable(), "Cart icon not clickable");
    }
    
    public void validateWishlistIcon() {
    	home = new HomePage(driver);
    	Assert.assertTrue(home.iswishlistClickable(), "wishlist is not clickable");
    }
}
