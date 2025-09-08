package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "termsofservice")
    WebElement termsCheckbox;

    @FindBy(name = "checkout")
    WebElement checkoutButton;

    @FindBy(id = "Email")
    WebElement emailField;

    @FindBy(id = "Password")
    WebElement passwordField;

    @FindBy(xpath = "//button[normalize-space()='Log in']")
    WebElement loginButton;

    @FindBy(id = "BillingNewAddress_CountryId")
    WebElement countryDropdown;

    @FindBy(id = "BillingNewAddress_StateProvinceId")
    WebElement stateDropdown;

    @FindBy(id = "BillingNewAddress_City")
    WebElement cityField;

    @FindBy(id = "BillingNewAddress_Address1")
    WebElement addressField;

    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    WebElement zipCodeField;

    @FindBy(id = "BillingNewAddress_PhoneNumber")
    WebElement phoneField;

    @FindBy(xpath = "//button[@onclick='if (!window.__cfRLUnblockHandlers) return false; Billing.save()']")
    WebElement billingContinueBtn;

    @FindBy(xpath="//input[@id='shippingoption_1']") // Ground shipping
    WebElement ShippingRadio;
    
 
    @FindBy(xpath = "//button[@class='button-1 shipping-method-next-step-button']")
    WebElement shippingContinueBtn;

    @FindBy(id = "paymentmethod_0") // Cash on Delivery
    WebElement cashOnDeliveryRadio;

    @FindBy(xpath = "//button[@class='button-1 payment-method-next-step-button']")
    WebElement paymentMethodContinueBtn;
    

    @FindBy(xpath = "//button[@class='button-1 payment-info-next-step-button']")
    WebElement paymentInfoContinueBtn;


    @FindBy(xpath = "//button[@class='button-1 confirm-order-next-step-button']")
    WebElement confirmOrderBtn;

    @FindBy(xpath = "//strong[normalize-space()='Your order has been successfully processed!']")
    WebElement successMessage;

    // Extra shipping address handling
    @FindBy(xpath = "//input[@id='ShipToSameAddress']")
    WebElement shippingCheckbox;

    @FindBy(xpath = "//button[@onclick='if (!window.__cfRLUnblockHandlers) return false; Billing.save()']")
    WebElement shippingContinue;


    public void agreeAndCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(termsCheckbox)).click();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void loginIfRedirected(String email, String password) {
        try {
            WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Email")));
            if (emailInput.isDisplayed()) {
                emailInput.clear();
                emailInput.sendKeys(email);

                passwordField.clear();
                passwordField.sendKeys(password);

                loginButton.click();
                System.out.println("Logged in during checkout.");
            }
        } catch (Exception e) {
            System.out.println("No login required — already logged in.");
        }
    }

  
    public void fillBillingAddress(String country, String state, String city,
                                   String address, String zip, String phone) {
        try {
            if (wait.until(ExpectedConditions.visibilityOf(countryDropdown)).isDisplayed()) {
                Select countrySelect = new Select(countryDropdown);
                countrySelect.selectByVisibleText(country);

                if (state != null && !state.trim().isEmpty()) {
                    Select stateSelect = new Select(stateDropdown);
                    stateSelect.selectByVisibleText(state);
                }

                cityField.clear();
                cityField.sendKeys(city);

                addressField.clear();
                addressField.sendKeys(address);

                zipCodeField.clear();
                zipCodeField.sendKeys(zip);

                phoneField.clear();
                phoneField.sendKeys(phone);

                billingContinueBtn.click();
                System.out.println("Filled new billing address.");
            }
        } catch (Exception e) {
            System.out.println(" Billing address already exists, clicking Continue.");
            wait.until(ExpectedConditions.elementToBeClickable(billingContinueBtn)).click();
        }
    }


    public void continueButton() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(billingContinueBtn)).click();
        Thread.sleep(2000);
    }
    
    public void selectBoxShipping() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(shippingCheckbox)).click();
        Thread.sleep(2000);
    }

    public void shippingradio() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(ShippingRadio)).click();
    	Thread.sleep(2000);
    }
    
    public void shippingBtn() throws InterruptedException {
    	wait.until(ExpectedConditions.elementToBeClickable(shippingContinueBtn)).click();
    	Thread.sleep(2000);
    }
    public void continueShipping() throws InterruptedException {
    	
        wait.until(ExpectedConditions.elementToBeClickable(shippingContinue)).click();
        Thread.sleep(2000);
    }

    public void selectRadioPaymentMethod() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(cashOnDeliveryRadio)).click();
        Thread.sleep(2000);

    }
    public void selectPaymentContinue() throws InterruptedException {
         wait.until(ExpectedConditions.elementToBeClickable(paymentMethodContinueBtn)).click();
         Thread.sleep(2000);
   }
 
    public void confirmPaymentInfo() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(paymentInfoContinueBtn)).click();
        Thread.sleep(2000);
    }

    public void confirmOrder() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderBtn)).click();
        Thread.sleep(2000);
    }


    public boolean isOrderSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successMessage));
            return successMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public boolean isCheckoutPageDisplayed() {
        try {
           
            wait.until(ExpectedConditions.visibilityOf(termsCheckbox));
            return termsCheckbox.isDisplayed() && checkoutButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public boolean isShippingMethodSelected() {
        try {
           
            wait.until(ExpectedConditions.visibilityOf(shippingCheckbox));
            return shippingCheckbox.isSelected() || ShippingRadio.isSelected();
        } catch (Exception e) {
            return false;
        }
    }

	

}
