package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    
	protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions action;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10s default wait
		action = new Actions(driver);
		PageFactory.initElements( driver, this);
	}
	
}
