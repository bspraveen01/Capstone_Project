package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
		
	}
 @FindBy(xpath = "//a[@class='ico-register']")
 public WebElement Rstrbtn;
 
 @FindBy(xpath = "//a[@class='ico-login']")
public WebElement Loginbtn;
 
 @FindBy(xpath="//a[@class='ico-account']")
public WebElement ActBtn;
 
 @FindBy(xpath="//a[@class='ico-logout']")

public WebElement Logoutbtn;
 
 @FindBy(xpath="//Select[@id='customerCurrency']")
 public WebElement currencyDl;
 
 @FindBy(xpath = "//img[@alt='nopCommerce demo store']")
public  WebElement logo;
 
@FindBy(xpath="//span[@class='wishlist-label']")
public WebElement wishlist;
 
 @FindBy(xpath="//a[normalize-space()='Contact us']")
public WebElement contactlink;
 
 @FindBy(xpath="//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']")
public  WebElement computersOpt;
 
 @FindBy(xpath="//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']")
public WebElement ElectronicsOpt;
 
 @FindBy(xpath="//ul[@class='top-menu notmobile']//a[normalize-space()='Apparel']")
public WebElement ApparelOpt;
 
 @FindBy(xpath="//ul[@class='top-menu notmobile']//a[normalize-space()='Digital downloads']")
public WebElement digitalDownloads;
 
 @FindBy(xpath="//ul[@class='top-menu notmobile']//a[normalize-space()='Books']")
 public WebElement BooksOption;
 
 @FindBy(id = "small-searchterms")
public WebElement searchBox;

 @FindBy(xpath = "//input[@value='Search']")
 public WebElement searchButton;
 
 @FindBy(xpath = "//span[@class='cart-label']")
 public WebElement cartIcon;

 
 public void regstr() {
	 wait.until(ExpectedConditions.elementToBeClickable(Rstrbtn)).click();
 }
 
 public void login() {
	 wait.until(ExpectedConditions.elementToBeClickable(Loginbtn)).click();
 }
 
 public void myAccount() {
	 wait.until(ExpectedConditions.elementToBeClickable(ActBtn)).click();
 }
 
 public void selectCurrency() {
	 Select s = new Select(currencyDl);
	 s.selectByVisibleText("Euro");
 }
 
 public void hoverOnComputers() {
     action.moveToElement(computersOpt).perform();
 }

 public void hoverOnElectronics() {
     action.moveToElement(ElectronicsOpt).perform();
 }

 public void hoverOnApparel() {
     action.moveToElement(ApparelOpt).perform();
 }

 public void hoverOnDigitalDownloads() {
     action.moveToElement(digitalDownloads).perform();
 }

 public void hoverOnBooks() {
     action.moveToElement(BooksOption).click().build().perform();
 }
 public void openContactUsPage() {
	    // Scroll down directly to the Contact Us link before clicking
	 JavascriptExecutor jse = (JavascriptExecutor) driver;
	 jse.executeScript("arguments[0].scrollIntoView(true);", contactlink);

	}

 public void logout() {
     try {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         wait.until(ExpectedConditions.elementToBeClickable(Logoutbtn));
         Logoutbtn.click();
     } catch (Exception e) {
         System.out.println("⚠️ Logout link not found – maybe user is not logged in.");
     }
 }

 public boolean isLoginDisplayed() {
     try {
         return Loginbtn.isDisplayed();
     } catch (Exception e) {
         return false;
     }
 }

 public boolean isLogoutDisplayed() {
     try {
         return Logoutbtn.isDisplayed();
     } catch (Exception e) {
         return false;
     }
 }
 
 public boolean islogoDisplayed() {
	 try {
		 return logo.isDisplayed();
	 }catch (Exception e) {
         return false;
     }
 }
 public boolean isCartClickable() {
	    try {
	        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

 public void searchProduct(String productName) {
	    wait.until(ExpectedConditions.visibilityOf(searchBox)).clear();
	    searchBox.sendKeys(productName);
	    searchBox.sendKeys(Keys.ENTER);
	}

 public void searchClick() {
	    wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
	}

 private By productSearchResult(String productName) {
     return By.xpath("//h2[@class='product-title']/a[contains(text(),'" + productName + "')]");
 }

 public boolean isProductSearchResultDisplayed(String productName) {
     try {
         WebElement product = driver.findElement(productSearchResult(productName));
         return product.isDisplayed();
     } catch (Exception e) {
         return false;
     }
 }

 public boolean iswishlistClickable() {
	 try {
	        wait.until(ExpectedConditions.elementToBeClickable(wishlist));
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
 }

 
 
}

