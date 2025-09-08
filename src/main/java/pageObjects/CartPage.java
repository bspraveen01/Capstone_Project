package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

public class CartPage extends BasePage{
  

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "button.remove-btn")
    private WebElement removeButton;

    @FindBy(id = "updatecart")
    private WebElement updateCartButton;

    @FindBy(css = "input.qty-input")
    private WebElement quantityInput;


    @FindBy(css = "span.product-subtotal")
    private WebElement total;

    @FindBy(css = "a.product-name")
    private WebElement productNameResult;

    @FindBy(css = "div.no-data")
    private WebElement emptyCartMessage;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "termsofservice")
    private WebElement agreeCheckbox;

    @FindBy(css = "div.page-title")
	public WebElement pageTitleCheckout;
    
    @FindBy(xpath = "//span[@class='cart-label']")
    public WebElement cartIcon;

    @FindBy(xpath = "//span[@title='Close']")
    WebElement notificationCloseBtn;

    
    public void closeNotificationIfPresent() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(notificationCloseBtn)).click();
            System.out.println("Closed notification popup.");
        } catch (Exception e) {
            System.out.println("No popup found.");
        }
    }

    public void clickCartIcon() {
    	wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    public void removeProductFromCart() {
        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
    }

    // --- Getters ---
    public String getProductNameFromCart() {
        return wait.until(ExpectedConditions.visibilityOf(productNameResult)).getText();
    }

    public String getTotalPrice() {
        return wait.until(ExpectedConditions.visibilityOf(total)).getText();
    }

    public String getEmptyCartMessage() {
        return wait.until(ExpectedConditions.visibilityOf(emptyCartMessage)).getText();
    }
   
    public void updateProductQuantityCart(String quantityProduct) {
        WebElement qtyField = wait.until(ExpectedConditions.visibilityOf(quantityInput));

        qtyField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        qtyField.sendKeys(quantityProduct);

        WebElement updateBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("updatecart")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", updateBtn);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(updateBtn)).click();
        } catch (Exception e) {
         
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", updateBtn);
        }

        try {
            wait.until(ExpectedConditions.stalenessOf(updateBtn));
        } catch (Exception ignored) {
         
            try { Thread.sleep(1000); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
        }
    }
    public void openCheckoutPage() {
        WebElement agreeCheckbox = wait.until(ExpectedConditions.elementToBeClickable(this.agreeCheckbox));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", agreeCheckbox);
        agreeCheckbox.click();

        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(this.checkoutButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkoutBtn);

        try {
            checkoutBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutBtn);
        }
    }
    public void addFirstProductToCart() {
        try {
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[text()='Add to cart'])[1]")));
            addButton.click();
            System.out.println(" Product added to cart — success message appeared.");
        } catch (Exception e) {
            throw new RuntimeException(" Failed to add product to cart: " + e.getMessage());
        }
    }

    public boolean isProductInCart(String productName) {
        try {
       
            wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();

            List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.cssSelector("a.product-name")));

            for (WebElement product : products) {
                if (product.getText().equalsIgnoreCase(productName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }



}
