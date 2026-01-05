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
    public void validUsernameValidPassword() {
        try {
            loginPage.enterUsername("Admin");
            loginPage.enterPassword("admin123");
            loginPage.clickLoginButton();
            Assert.assertTrue(loginPage.isDashboardDisplayed());
        } catch (Exception e) {
            Assert.fail("Valid login test failed: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void invalidUsernameInvalidPassword() {
        try {
            loginPage.enterUsername("User123");
            loginPage.enterPassword("test123");
            loginPage.clickLoginButton();
            Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        } catch (Exception e) {
            Assert.fail("Invalid login test failed: " + e.getMessage());
        }
    }
    @Test(priority = 3)
    public void validUsernameInvalidPassword() {
        try {
            loginPage.enterUsername("Admin");
            loginPage.enterPassword("wrong123");
            loginPage.clickLoginButton();

            Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        } catch (Exception e) {
            Assert.fail("Valid username + invalid password test failed: " + e.getMessage());
        }
    }
    @Test(priority = 4)
    public void invalidUsernameValidPassword() {
        try {
            loginPage.enterUsername("Admin123");
            loginPage.enterPassword("admin123");
            loginPage.clickLoginButton();
            Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        } catch (Exception e) {
            Assert.fail("Invalid username + valid password test failed: " + e.getMessage());
        }
    }
    @Test(priority = 5)
    public void usernameWithSpaces() {
        try {
            loginPage.enterUsername("  Admin  ");
            loginPage.enterPassword("admin123");
            loginPage.clickLoginButton();
            Assert.assertTrue(
                    loginPage.isErrorMessageDisplayed() ||
                            loginPage.isDashboardDisplayed()
            );
        } catch (Exception e) {
            Assert.fail("Username with spaces test failed: " + e.getMessage());
        }
    }
    @Test(priority = 6)
    public void passwordMaskingValidation() {
        try {
            loginPage.enterPassword("admin123");
            Assert.assertTrue(loginPage.isPasswordMasked());
        } catch (Exception e) {
            Assert.fail("Password masking test failed: " + e.getMessage());
        }
    }

    @Test(priority = 7)
    public void loginButtonEnabledValidation() {
        try {
            loginPage.enterUsername("Admin");
            loginPage.enterPassword("admin123");
            Assert.assertTrue(loginPage.isLoginButtonEnabled());
        } catch (Exception e) {
            Assert.fail("Login button enablement test failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        closeBrowser();
    }

}
