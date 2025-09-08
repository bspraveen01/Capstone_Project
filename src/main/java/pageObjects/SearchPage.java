package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(id = "small-searchterms")
    private WebElement searchBox;

    @FindBy(xpath = "(//button[@type='submit'])[1]")
    private WebElement searchBtn;

    @FindBy(css = "div.product-item h2.product-title a")
    private List<WebElement> productLinks;

    @FindBy(xpath = "//ul[@id='ui-id-1']//li")
    private List<WebElement> autoSuggestList;
    
    @FindBy(css = ".product-title a")
    WebElement firstSearchResult;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void search(String productName) {
        wait.until(ExpectedConditions.visibilityOf(searchBox)).clear();
        searchBox.sendKeys(productName);
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
    }

    // NEW: Click product by exact name match
    public void openProductDetailsPageByName(String expectedProductName) {
        wait.until(ExpectedConditions.visibilityOfAllElements(productLinks));

        for (WebElement productLink : productLinks) {
            String productName = productLink.getText().trim();
            if (productName.contains(expectedProductName)) {
                System.out.println("Found and clicking product: " + productName);
                productLink.click();
                return;
            }
        }

        throw new RuntimeException("Product not found in search results: " + expectedProductName);
    }

    // Keep old method for backward compatibility (optional)
    public void openFirstProductDetailsPage() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productLinks));
        if (!productLinks.isEmpty()) {
            System.out.println("Opening first product (may not be expected one): " + productLinks.get(0).getText());
            productLinks.get(0).click();
        } else {
            throw new RuntimeException("No products found in search results!");
        }
    }

    public boolean hasSearchResults() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productLinks));
        return !productLinks.isEmpty();
    }

    public void searchByAutoSuggestion(String searchText) {
        wait.until(ExpectedConditions.visibilityOf(searchBox)).clear();
        searchBox.sendKeys(searchText.substring(0, 3));

        wait.until(ExpectedConditions.visibilityOfAllElements(autoSuggestList));

        boolean clicked = false;
        for (WebElement suggestion : autoSuggestList) {
            String suggestionText = suggestion.getText().toLowerCase();
            if (suggestionText.contains(searchText.toLowerCase())) {
                wait.until(ExpectedConditions.elementToBeClickable(suggestion)).click();
                clicked = true;
                break;
            }
        }

        if (!clicked) {
            throw new RuntimeException(" No auto-suggestion matched for: " + searchText);
        }
    }

	public void clickFirstResult() {
		// TODO Auto-generated method stub
		firstSearchResult.click();
	}
}