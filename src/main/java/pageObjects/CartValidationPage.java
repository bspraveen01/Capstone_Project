package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartValidationPage extends BasePage {

  
    public CartValidationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@class='clear-list']")
    private WebElement clearListLink;

    @FindBy(xpath = "//table[@class='compare-products-table']")
    private WebElement compareTable;

    @FindBy(xpath = "//div[@class='no-data']")
    private WebElement noData;

    @FindBy(linkText = "Apple MacBook Pro 13-inch")
    public WebElement firstProductName;

    @FindBy(xpath = "//table[@class='compare-products-table']//tr[3]/td[2]/a")
    public WebElement secondProductName;

    @FindBy(xpath = "//table[@class='compare-products-table']//tr")
    public List<WebElement> allRows;

  
    public void clearCompareTable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement clearListBtn = wait.until(ExpectedConditions.elementToBeClickable(clearListLink));
        clearListBtn.click();
    }
    public void compareProducts() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(compareTable));

        System.out.println("Total Rows in Compare Table: " + allRows.size());

        for (WebElement row : allRows) {
            System.out.println("Row Text: " + row.getText());

            List<WebElement> colsInRow = row.findElements(org.openqa.selenium.By.tagName("td"));
            for (WebElement col : colsInRow) {
                System.out.print(col.getText() + "\t");
            }
            System.out.println(); 
        }
    }

    public boolean isCompareTableEmpty() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(noData));
            return noData.isDisplayed();
        } catch (org.openqa.selenium.TimeoutException e) {
            
            return false;
        }
    }
}