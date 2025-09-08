package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Change password")
    WebElement changePasswordLink;

    @FindBy(id = "OldPassword")
    WebElement oldPasswordField;

    @FindBy(id = "NewPassword")
    WebElement newPasswordField;

    @FindBy(id = "ConfirmNewPassword")
    WebElement confirmPasswordField;

    @FindBy(xpath = "(//button[@type='submit'])[2]")
    WebElement changePasswordButton;

    @FindBy(xpath = "//p[@class='content']")
    WebElement successfulChangeMessage;

    @FindBy(xpath = "//*[@id='bar-notification']/div/span")
    WebElement notificationCloseBtn;


    public void openChangePasswordPage() {
        wait.until(ExpectedConditions.elementToBeClickable(changePasswordLink)).click();
    }

    public void changePassword(String oldPassword, String newPassword) {
        wait.until(ExpectedConditions.visibilityOf(oldPasswordField)).clear();
        oldPasswordField.sendKeys(oldPassword);

        wait.until(ExpectedConditions.visibilityOf(newPasswordField)).clear();
        newPasswordField.sendKeys(newPassword);

        wait.until(ExpectedConditions.visibilityOf(confirmPasswordField)).clear();
        confirmPasswordField.sendKeys(newPassword);

        wait.until(ExpectedConditions.elementToBeClickable(changePasswordButton)).click();
    }

    public String getSuccessfulChangeMessage() {
        wait.until(ExpectedConditions.visibilityOf(successfulChangeMessage));
        return successfulChangeMessage.getText();
    }

    public void closeNotification() {
        wait.until(ExpectedConditions.elementToBeClickable(notificationCloseBtn)).click();
    }
}
