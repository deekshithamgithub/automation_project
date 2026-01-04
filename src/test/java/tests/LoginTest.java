package tests;

import base.BaseTest;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    
    @BeforeMethod
    public void setUp() {
        launchBrowser();
        loginPage = new LoginPage(driver);
    }
    
    @Test(priority = 1)
    public void validUsernameValidPassword() throws InterruptedException {
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isDashboardDisplayed());
    }

    @Test(priority = 2)
    public void invalidUsernameInvalidPassword() {
        loginPage.enterUsername("User123");
        loginPage.enterPassword("test123");
        loginPage.clickLoginButton();
        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message should be shown for invalid credentials"
        );
    }
    
    @Test(priority = 3)
    public void validUsernameInvalidPassword() {
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("wrong123");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }
    
    @Test(priority = 4)
    public void invalidUsernameValidPassword() {
        loginPage.enterUsername("Admin123");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(priority = 5)
    public void usernameWithSpaces() {
        loginPage.enterUsername("  Admin  ");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();
        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed()
                        || driver.getCurrentUrl().contains("dashboard")
        );
    }
    
    @Test(priority = 6)
    public void passwordMaskedValidation() {
        loginPage.enterPassword("admin123");
        Assert.assertTrue(
                loginPage.isPasswordMasked(),
                "Password should be masked"
        );
    }
    
    @Test(priority = 7)
    public void loginButtonEnabledValidation() {
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        Assert.assertTrue(
                loginPage.isLoginButtonEnabled(),
                "Login button should be enabled"
        );
    }
    
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        closeBrowser();
    }

}
