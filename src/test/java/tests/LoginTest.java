package tests;

import base.BaseClass;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ResearchPage;

import java.io.IOException;

public class LoginTest extends BaseClass {
    public RemoteWebDriver driver;
    public LoginPage loginPage;
    public ResearchPage researchPage;

    public LoginTest() {
        super();
    }

    @BeforeMethod
    public void setup() throws ElementClickInterceptedException, IOException, InterruptedException {
        driver = initializeBrowserAndOpenApplication(config.getProperty("browserName"));
        loginPage = new LoginPage(driver);
    }
    @Test(priority = 1)
    public void loginWithValidDetailsTest() throws ElementClickInterceptedException, IOException, InterruptedException {
        loginPage.SignIn(config.getProperty("username"), config.getProperty("password"));
        Assert.assertEquals(driver.getTitle(), "Research | RoofRocket AI", "Login successful");
        researchPage = new ResearchPage(driver);
        Assert.assertTrue(researchPage.isLoginSuccessful());
    }

    @Test(priority = 2)
    public void loginWithInvalidDetailsTest() throws ElementClickInterceptedException, IOException, InterruptedException {
        loginPage.SignIn(config.getProperty("invalidusername"), config.getProperty("invalidpassword"));
        Assert.assertEquals(driver.getTitle(), "Login | RoofRocket AI", "Login failed");
    }

    @Test(priority = 3)
    public void loginWithEmptyDetailsTest() throws ElementClickInterceptedException, IOException, InterruptedException {
        loginPage.SignIn(config.getProperty("emptyusername"), config.getProperty("emptypassword"));
        Assert.assertEquals(driver.getTitle(), "Login | RoofRocket AI", "Login failed");
    }

    @Test(priority = 4)
    public void forgotPasswordTest() throws ElementClickInterceptedException, IOException, InterruptedException {

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }////li[contains(@class,'nav-item dropdown') or @class='nav-item']
}