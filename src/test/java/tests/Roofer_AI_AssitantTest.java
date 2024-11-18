package tests;

import base.BaseClass;
import com.github.javafaker.Faker;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.awt.*;
import java.io.IOException;

public class Roofer_AI_AssitantTest extends BaseClass {
    public RemoteWebDriver driver;
    public LoginPage loginPage;
    public ResearchPage researchPage;
    public Create_AssistantPage create_AssistantPage;
    public GmbPage gmbPage;
    public Faker faker;
    public Ai_AssistantPage ai_AssistantPage;
    public Yelppage yelppage;
    public SeoPage seoPage;
    public Dfy_VideoPage dfy_VideoPage;
    public CopyWritingPage copyWritingPage;
    public EmailPage emailPage;
    public Roofer_AI_AssitantTest() {
        super();
    }
    @BeforeMethod
    public void setup() throws ElementClickInterceptedException, IOException, InterruptedException {
        driver = initializeBrowserAndOpenApplication(config.getProperty("browserName"));
        loginPage = new LoginPage(driver);
        researchPage = new ResearchPage(driver);
        faker = new Faker();
        ai_AssistantPage = new Ai_AssistantPage(driver);
    create_AssistantPage = new Create_AssistantPage(driver);
        loginPage.SignIn(config.getProperty("username"), config.getProperty("password"));
        researchPage.clickRooferAiAssitantLink();
    }
    @Test(priority = 1,retryAnalyzer = utilities.RetryAnalyzer.class)
    public void VerifyRooferAiAssitantTest() throws ElementClickInterceptedException, IOException, InterruptedException, AWTException {
        if(ai_AssistantPage.isCreateRooferAiAssistantDisplayed()){
            ai_AssistantPage.clickCreateRooferAiAssistant();
        } else if (ai_AssistantPage.isAddChatDisplayed()) {
            ai_AssistantPage.clickAddChat();
        }
        create_AssistantPage.createAssistantForm(faker.company().name(),generateRandomPhoneNumber(10), faker.internet().emailAddress());
        Assert.assertEquals(create_AssistantPage.getAlertMsg(), "Settings saved successfully!");
        Assert.assertTrue(create_AssistantPage.isAddBusinessDetailsDisplayed());
        Assert.assertTrue(create_AssistantPage. isStepsDisplayed());
        create_AssistantPage. fillTheConfigureRooferAIAssistant(faker.name().fullName(),faker.lorem().sentence(),faker.internet().url(),faker.lorem().sentence(),faker.lorem().sentence(),faker.lorem().sentence());
  //  Assert.assertEquals(create_AssistantPage.getAlertMsg(), "Settings saved successfully!");
   /*     System.out.println(create_AssistantPage.getAlertMsg());
        System.out.println(create_AssistantPage.getAlertMsg());*/
    }
    @Test(priority = 2,retryAnalyzer = utilities.RetryAnalyzer.class)
    public void VerifyRooferAiAssitantTestWithOutDataValidatingErrorMsg() throws ElementClickInterceptedException, IOException, InterruptedException {
        if(ai_AssistantPage.isCreateRooferAiAssistantDisplayed()){
            ai_AssistantPage.clickCreateRooferAiAssistant();
        } else if (ai_AssistantPage.isAddChatDisplayed()) {
            ai_AssistantPage.clickAddChat();
        }
        Assert.assertEquals(driver.getTitle(),"Create AI Assistant | RoofRocket AI");
        create_AssistantPage.clickSaveAndNextButton();
        Assert.assertEquals(create_AssistantPage.getAlertMsg(), "Please enter business name");
        create_AssistantPage.enterBusinessName(faker.company().name());
        create_AssistantPage.clickSaveAndNextButton();
        //Assert.assertEquals(create_AssistantPage.getAlertMsg(), "Please enter a valid email address");
       // create_AssistantPage.enterEmailAddress(faker.internet().emailAddress());
      //  create_AssistantPage.clickSaveAndNextButton();
       /* Assert.assertEquals(create_AssistantPage.getAlertMsg(), "Please enter a valid phone number");
  create_AssistantPage.enterPhoneNumber(generateRandomPhoneNumber(10));
        create_AssistantPage.clickSaveAndNextButton();
        Assert.assertEquals(create_AssistantPage.getAlertMsg(), "Settings saved successfully!");
        //create_AssistantPage.createAssistantForm("", "", "");
        Assert.assertTrue(create_AssistantPage.isAddBusinessDetailsDisplayed());
        Assert.assertTrue(create_AssistantPage. isStepsDisplayed());*/
    }
    @AfterMethod

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        else {
            System.out.println("Driver is null");
        }
    }

    public String generateRandomPhoneNumber(int length) {
        String phoneNumber = "";
        for (int i = 0; i < length; i++) {
            phoneNumber += (int) (Math.random() * 10);
        }
        return phoneNumber;
    }
}
