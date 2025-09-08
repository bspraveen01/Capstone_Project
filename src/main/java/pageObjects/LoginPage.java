package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Login form fields
    @FindBy(xpath = "//input[@id='Email']")
    WebElement email;

    @FindBy(xpath = "//input[@id='Password']")
    WebElement password;

    @FindBy(xpath = "//button[normalize-space()='Log in']")
    WebElement loginButton;

    // Actions
    public void setEmail(String emailId) {
        wait.until(ExpectedConditions.visibilityOf(email));
        email.clear();
        email.sendKeys(emailId);
    }

    public void setPassword(String pwd) {
        wait.until(ExpectedConditions.visibilityOf(password));
        password.clear();
        password.sendKeys(pwd);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

	public void loginForm(String mail, String pass) {
		
		    email.sendKeys(mail);
		    password.sendKeys(pass);
		    loginButton.click();
	

		
	}
}
