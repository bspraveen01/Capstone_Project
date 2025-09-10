package pageObjects;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {




	public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@class='ico-register']")
    WebElement registeroption;

    @FindBy(xpath = "//input[@id='gender-male']")
    WebElement gender;

    @FindBy(xpath = "//input[@id='FirstName']")
    WebElement firstname;

    @FindBy(xpath = "//input[@id='LastName']")
    WebElement lastname;

    @FindBy(xpath = "//input[@id='Email']")
    WebElement email;

    @FindBy(xpath = "//input[@id='Company']")
    WebElement companyname;

    @FindBy(xpath = "//input[@id='Newsletter']")
    WebElement newsletterbox;

    @FindBy(xpath = "//input[@id='Password']")
    WebElement password;

    @FindBy(xpath = "//input[@id='ConfirmPassword']")
    WebElement confirmpassword;

    @FindBy(xpath = "//button[@id='register-button']")
    WebElement registerButton;  
    
    @FindBy(xpath = "//div[@class='result']")
    WebElement successMsg;
    
    @FindBy(xpath="//li[normalize-space()='The specified email already exists']")
    WebElement existingUser;
    
    @FindBy(xpath="//a[normalize-space()='Continue']")
    WebElement continueclick;


    public void clickRegisterOption() {
        wait.until(ExpectedConditions.elementToBeClickable(registeroption)).click();
    }

    public void setGender() {
        wait.until(ExpectedConditions.elementToBeClickable(gender)).click();
    }

    public void setFirstName(String fname) {
        wait.until(ExpectedConditions.visibilityOf(firstname));
        firstname.clear();
        firstname.sendKeys(fname);
    }

    public void setLastName(String lname) {
        wait.until(ExpectedConditions.visibilityOf(lastname));
        lastname.clear();
        lastname.sendKeys(lname);
    }

    public void setEmail(String emailId) {
        wait.until(ExpectedConditions.visibilityOf(email));
        email.clear();
        email.sendKeys(emailId);
    }

    public void setCompany(String companyName) {
        wait.until(ExpectedConditions.visibilityOf(companyname));
        companyname.clear();
        companyname.sendKeys(companyName);
    }

    public void clickNewsbox() {
        wait.until(ExpectedConditions.elementToBeClickable(newsletterbox)).click();
    }

    public void setPassword(String pwd) {
        wait.until(ExpectedConditions.visibilityOf(password));
        password.clear();
        password.sendKeys(pwd);
    }

    public void setConfirmPassword(String conPassword) {
        wait.until(ExpectedConditions.visibilityOf(confirmpassword));
        confirmpassword.clear();
        confirmpassword.sendKeys(conPassword);
    }

    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

//    public String getSuccessMessage() {
//        try {
//            wait.until(ExpectedConditions.visibilityOf(successMsg));
//            return successMsg.getText();
//        } catch (Exception e) {
//            return ""; // Return empty if element not found
//        }
//    }

    public String getExitingerrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(existingUser));
            return existingUser.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickContinueAfterRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(continueclick)).click();
    }

   

    // Fills the registration form but does not submit
    public void registrationForm(String fname, String lname, String emailId, String pwd) {
        clickRegisterOption();
        setGender();
        setFirstName(fname);
        setLastName(lname);
        setEmail(emailId);
        setPassword(pwd);
        setConfirmPassword(pwd);
    }

    // Completes full registration (fills + submits)
    public void register(String fname, String lname, String emailId, String pwd) {
        registrationForm(fname, lname, emailId, pwd);
        clickRegisterButton();
    }
    
    public String getSuccessMessage() {
        return successMsg.getText();
    }
}
