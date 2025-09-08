package TestCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;  
    public Logger logger;
    public Properties properties;
   public WebDriverWait wait ;

    @BeforeClass
    @Parameters({"browserName"})
    public void setUp(@Optional("chrome") String browser) throws IOException {

        // Fix: use "resources" not "resource"
        File configFile = new File("./src/test/resources/Config.properties");
        properties = new Properties();

        if (configFile.exists()) {
            FileReader fis = new FileReader(configFile);
            properties.load(fis);
        } else {
            System.out.println("Config.properties not found, using default values.");
           // properties.setProperty("baseURL", "https://demo.nopcommerce.com/");
        }

        logger = LogManager.getLogger(this.getClass());

        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser: " + browser);
        }
       

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Use config value
        driver.get(properties.getProperty("baseURL"));
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        // Quit driver
        if (driver != null) {
            driver.quit();
        }

    }

	public WebDriver getDriver() {
		// TODO Auto-generated method stub
		return driver;
	}
	
	

}
