package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    LoginPage login;

    @BeforeMethod
    public void setup() {
        launchBrowser();
        login = new LoginPage(driver);
    }

    @Test
    public void validLoginTest() {
        login.enterUsername("Admin");
        login.enterPassword("admin123");
        login.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    public void invalidLoginTest() {
        login.enterUsername("Admin");
        login.enterPassword("wrong123");
        login.clickLogin();
        Assert.assertTrue(login.isErrorDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser();
    }
}
