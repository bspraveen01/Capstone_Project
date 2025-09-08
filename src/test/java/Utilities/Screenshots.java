package Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Screenshots {
	WebDriver driver;
	public static String takeScreenshot(WebDriver driver, String screenshotName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
        Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/screenshots/"));
        Files.copy(src.toPath(), Paths.get(path));
        System.out.println("Screenshot saved at: " + path);
        return path;
    }
	
}
