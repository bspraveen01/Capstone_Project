package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Wishlist extends BasePage {

    public Wishlist(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "button.remove-btn")
    private WebElement buttonRemove;

    @FindBy(css = "div.no-data")
    private WebElement emptyWishlist;

    @FindBy(css = "div.page-title")
    private WebElement pageTitleWishlist;

    // Action: remove product
    public void removeProductFromWishList() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonRemove)).click();
    }

    // Getter for empty wishlist message
    public String getEmptyWishlistMessage() {
        return wait.until(ExpectedConditions.visibilityOf(emptyWishlist)).getText();
    }

    // Getter for wishlist page title
    public String getPageTitleWishlist() {
        return wait.until(ExpectedConditions.visibilityOf(pageTitleWishlist)).getText();
    }
}
