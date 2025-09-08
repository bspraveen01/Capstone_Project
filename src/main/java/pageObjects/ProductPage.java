package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {

    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(css = "div.product-name h1")
    private WebElement productTitle;

    @FindBy(css = "button.button-2.add-to-wishlist-button")
    private WebElement addToWishlistButton;

    @FindBy(css = "button.button-1.add-to-cart-button")
    private WebElement addToCartBtn;
    
    @FindBy(css = "p.content") // Success message after adding to cart
    private WebElement successMessage;
    
    @FindBy(xpath = "//button[contains(text(), 'Add to compare list')]")
    private WebElement addToCompareBtn;

    @FindBy(css = "div.bar-notification.success")
    private WebElement successMessage1; // Optional duplicate locator

    @FindBy(css = "span.close") // Close button on success bar
    private WebElement closeSuccessMessage;


    public void waitForSuccessMessageToDisappear() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(8));
            shortWait.until(ExpectedConditions.invisibilityOf(successMessage));
            System.out.println("✅ Success message disappeared automatically.");
        } catch (Exception e) {
            System.out.println("⚠️ Success message did not disappear within timeout. Trying manual close...");
            closeSuccessMessageIfVisible();
            try { Thread.sleep(1000); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
        }
    }

    public void closeSuccessMessageIfVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(closeSuccessMessage));
            closeButton.click();
            System.out.println("✅ Closed success message manually.");
        } catch (Exception e) {
            System.out.println("ℹ️ No close button found or not clickable.");
        }
    }

    // Get product name with wait
    public String getProductName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(productTitle)).getText();
    }

    // Add product to wishlist with wait
    public void addProductToWishList() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addToWishlistButton)).click();
    }

    // Add to cart with success wait
    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        // Wait for success message to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            org.openqa.selenium.By.cssSelector("p.content")));
        System.out.println("Product added to cart — success message appeared.");
    }

    public void addToCompareList() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        System.out.println("Current Page: " + driver.getTitle() + " | URL: " + driver.getCurrentUrl());

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.cssSelector(".product-name, h1")));

            WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.xpath("//button[contains(text(), 'Add to compare list')]")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);
            btn = wait.until(ExpectedConditions.elementToBeClickable(btn));

            try {
                btn.click();
                System.out.println("Clicked 'Add to compare list' via normal click.");
            } catch (Exception e) {
                System.out.println("Normal click failed, trying JS click...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                System.out.println("Clicked via JavaScript.");
            }
        } catch (Exception e) {
            System.err.println("FAILED to add product to compare list.");
            System.err.println("Page Source Snippet: " + driver.getPageSource().substring(0, 500));
            throw new RuntimeException("Add to compare button not found or not clickable", e);
        }
    }

    public boolean isAddedToCompareSuccessfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOf(successMessage));
            return successMessage.isDisplayed();
        } catch (Exception e) {
            System.out.println("No success message found — may not be implemented on this site.");
            return false;
        }
    }


}