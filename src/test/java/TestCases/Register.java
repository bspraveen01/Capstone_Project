package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;

public class Register extends BaseTest {

    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;

    Faker fakeData = new Faker();
    String firstName = fakeData.name().firstName();
    String lastName = fakeData.name().lastName();
    String email = fakeData.internet().emailAddress();
    String password = fakeData.number().digits(10);

    @Test(priority = 1)
    public void ValidRegistration() {
        homePage = new HomePage(driver);
        homePage.regstr();

        registerPage = new RegisterPage(driver);
        registerPage.setGender();
        registerPage.setFirstName(firstName);
        registerPage.setLastName(lastName);
        registerPage.setEmail(email);
        registerPage.setCompany("Wipro");
        registerPage.clickNewsbox();
        registerPage.setPassword(password);
        registerPage.setConfirmPassword(password);
        registerPage.clickRegisterButton();

        Assert.assertTrue(registerPage.getSuccessMessage().contains("Your registration completed"),
                "Registration failed - Success message not displayed!");
    }

    @Test(priority = 2, dependsOnMethods = "ValidRegistration")
    public void RegisteredUserCanLogout() {
       
        homePage.logout();
        Assert.assertTrue(homePage.isLoginDisplayed(), "Login link should be visible after logout!");
    }

    @Test(priority = 3, dependsOnMethods = "RegisteredUserCanLogout")
    public void RegisteredUserCanLogin() {
        homePage.login();  

        loginPage = new LoginPage(driver);
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();   

        Assert.assertTrue(homePage.isLogoutDisplayed(), "Logout link is not displayed after login!");
    }

   
}

