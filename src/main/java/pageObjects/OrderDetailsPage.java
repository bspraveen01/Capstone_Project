package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderDetailsPage extends BasePage{

    public OrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a.button-2.print-order-button")
    private WebElement printOrderButton;

    @FindBy(xpath = "//a[@class='button-2 pdf-invoice-button']")
    private WebElement pdfInvoiceButton;
    
    @FindBy(css = "a[href*='orderdetails']")
    WebElement orderDetailsLink;

    public void printOrderDetails() {
        wait.until(ExpectedConditions.elementToBeClickable(printOrderButton)).click();
    }

    public void downloadPDFInvoice() {
        wait.until(ExpectedConditions.elementToBeClickable(pdfInvoiceButton)).click();
    }

    public void openOrderDetails() {
        wait.until(ExpectedConditions.visibilityOf(orderDetailsLink));
        orderDetailsLink.click();
    }

}
