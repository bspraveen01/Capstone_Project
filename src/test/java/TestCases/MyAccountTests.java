package TestCases;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import pageObjects.MyAccountPage;
import com.github.javafaker.Faker;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyAccountTests extends BaseTest {

    private HomePage homePage;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private MyAccountPage myAccountPage;
    private Faker fakeData = new Faker();


    private String firstName = fakeData.name().firstName();
    private String lastName = fakeData.name().lastName();
    private String email = fakeData.internet().emailAddress();
    private String password = fakeData.number().digits(10);
    private String newPassword = fakeData.number().digits(10);

    @Test(priority = 1)
    public void validRegistration() {
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
                " Registration failed - Success message not displayed!");
    }

    @Test(priority = 2, dependsOnMethods = "validRegistration")
    public void registeredUserCanLogout() {
        homePage.logout();
        Assert.assertTrue(homePage.isLoginDisplayed(), " Login link should be visible after logout!");
    }

    @Test(priority = 3, dependsOnMethods = "registeredUserCanLogout")
    public void registeredUserCanLogin() {
        homePage.login(); // Click Login link from HomePage

        loginPage = new LoginPage(driver);
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        Assert.assertTrue(homePage.isLogoutDisplayed(), " Logout link is not displayed after login!");
    }

    @Test(priority = 4, dependsOnMethods = "registeredUserCanLogin")
    public void loggedInUserCanChangePassword() {
        homePage.myAccount(); 

        myAccountPage = new MyAccountPage(driver);
        myAccountPage.openChangePasswordPage();
        myAccountPage.changePassword(password, newPassword);

        Assert.assertTrue(myAccountPage.getSuccessfulChangeMessage().contains("was changed"),
                "Password change not successful");
    }

    @Test(priority = 5, dependsOnMethods = "loggedInUserCanChangePassword")
    public void closeNotificationBar() {
        myAccountPage.closeNotification();
    }

    @Test(priority = 6)
    public void registeredUserCanLogoutAfterPasswordChange() {
        homePage = new HomePage(driver); 
        myAccountPage = new MyAccountPage(driver); 

        try { Thread.sleep(1000); } catch (Exception e) {}

        homePage.logout();

   
        wait.until(ExpectedConditions.visibilityOf(homePage.Loginbtn));
        Assert.assertTrue(homePage.isLoginDisplayed(), "Logout failed after password change");
    }

    @Test(priority = 7)
    public void userCanLoginWithNewPassword() {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);

        homePage.login(); 

        loginPage.setEmail(email);
        loginPage.setPassword(newPassword);
        loginPage.clickLoginButton();


        wait.until(ExpectedConditions.visibilityOf(homePage.Logoutbtn));
        Assert.assertTrue(homePage.isLogoutDisplayed(), " Login with new password failed");
    }


}
