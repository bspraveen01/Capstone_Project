package TestCases;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterDataProvider extends BaseTest {

    private HomePage homePage;
    private RegisterPage registrationPage;
    private LoginPage loginPage;

    
    @DataProvider(name = "TestData")
    public static Object[][] userData() {
        return new Object[][] {
            {"Sri", "Sra", "praveenburri@gmail.com", "1987665"}
        };
    }

    @DataProvider(name = "LoginData")
    public static Object[][] loginData() {
        return new Object[][] {
            {"praveenburri@gmail.com", "1987665"}
        };
    }

    @Test(priority = 1, dataProvider = "TestData")
    public void ValidRegistration(String fname, String lname, String email, String pass) {
        homePage = new HomePage(driver);
        homePage.regstr();

        registrationPage = new RegisterPage(driver);
        registrationPage.setGender();
        registrationPage.setFirstName(fname);
        registrationPage.setLastName(lname);
        registrationPage.setEmail(email);
        registrationPage.setCompany("Wipro");
        registrationPage.clickNewsbox();
        registrationPage.setPassword(pass);
        registrationPage.setConfirmPassword(pass);
        registrationPage.clickRegisterButton();

        // Verify registration success or existing user
        String successMsg = registrationPage.getSuccessMessage();
        String existingError = registrationPage.getExitingerrorMessage();

        Assert.assertTrue(
            successMsg.contains("Your registration completed") || existingError.contains("already exists"),
            "Registration failed - neither success nor existing user message displayed!"
        );
    }
    @Test(priority = 2, dependsOnMethods = "ValidRegistration")
    public void RegisteredUserCanLogout() {
        homePage = new HomePage(driver);
        homePage.logout();
        Assert.assertTrue(homePage.isLoginDisplayed(), "User should be logged out but Login link is not displayed!");
    }

    @Test(priority = 2, dependsOnMethods = "RegisteredUserCanLogout", dataProvider = "LoginData")
    public void RegisteredUserCanLogin(String email, String pass) {
        homePage = new HomePage(driver);
     
        if (homePage.isLogoutDisplayed()) {
            homePage.logout();
        }
        Assert.assertTrue(homePage.isLoginDisplayed(), "Login link should be visible after logout!");

        homePage.login(); 
        loginPage = new LoginPage(driver);
        loginPage.setEmail(email);
        loginPage.setPassword(pass);
        loginPage.clickLoginButton();

        Assert.assertTrue(homePage.isLogoutDisplayed(), "Logout link is not displayed after login!");
    }

    
}
