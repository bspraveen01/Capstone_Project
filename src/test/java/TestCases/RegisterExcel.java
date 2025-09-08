package TestCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.ExcelReader;
import pageObjects.HomePage;
import pageObjects.RegisterPage;

public class RegisterExcel extends BaseTest {

    HomePage homePage;
    RegisterPage reg;

    @DataProvider(name = "registerData")
    public Object[][] getRegisterData() throws Exception {
        String filePath = System.getProperty("user.dir") + "src/test/resources/userrData.xlsx";
        String sheetName = "Sheet1";
        ExcelReader.setExcelFile(filePath, sheetName); 
        return ExcelReader.getData(); 
    }

    @Test(dataProvider = "registerData")
    public void testUserRegistration(String firstName, String lastName, String email, String password) {
        homePage = new HomePage(driver);
        reg = new RegisterPage(driver);

      
        homePage.regstr();
        reg.register(firstName, lastName, email, password);

        // Assertion
        String actualMsg = reg.getSuccessMessage();
        Assert.assertEquals(actualMsg, "Your registration completed", "❌ Registration failed");
    }
}
