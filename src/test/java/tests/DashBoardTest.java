package tests;

import base.BaseClass;
import org.openqa.selenium.ElementClickInterceptedException;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;
import pages.ResearchPage;

import java.io.IOException;


public class DashBoardTest extends BaseClass {
    public RemoteWebDriver driver;
    public LoginPage loginPage;
    public ResearchPage researchPage;
    public DashBoardPage dashBoardPage;
    public DashBoardTest() {
        super();
    }
    @BeforeMethod
    public void setup() throws ElementClickInterceptedException, IOException, InterruptedException {
        driver = initializeBrowserAndOpenApplication(config.getProperty("browserName"));
        loginPage = new LoginPage(driver);
        loginPage.SignIn(config.getProperty("username"), config.getProperty("password"));
        Assert.assertEquals(driver.getTitle(), config.getProperty("ResearchPageTitle"), "Login successful");
        researchPage = new ResearchPage(driver);
        dashBoardPage = new DashBoardPage(driver);
    }
    @Test(priority = 1)
    public void VerifyDashboardTest() throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickDashboardLink();
        Assert.assertEquals(driver.getTitle(), config.getProperty("DashboardPageTitle"), " navigate to Dashboard successful");
     dashBoardPage.verifyDashboardElements();

    }
}
