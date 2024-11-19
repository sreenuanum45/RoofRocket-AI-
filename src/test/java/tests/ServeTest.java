package tests;

import base.BaseClass;
import com.github.javafaker.Faker;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class ServeTest extends BaseClass {
    public RemoteWebDriver driver;
    public LoginPage loginPage;
    public ResearchPage researchPage;
    public GmbPage gmbPage;
    public Faker faker;
    public Yelppage yelppage;
    public SeoPage seoPage;
    public Dfy_VideoPage dfy_VideoPage;
public CopyWritingPage copyWritingPage;
public EmailPage emailPage;
    public ServeTest() {
        super();
    }

    @BeforeMethod
    public void setup() throws ElementClickInterceptedException, IOException, InterruptedException {
        driver = initializeBrowserAndOpenApplication(config.getProperty("browserName"));
        loginPage = new LoginPage(driver);
        researchPage = new ResearchPage(driver);
        gmbPage = new GmbPage(driver);
        yelppage = new Yelppage(driver);
        seoPage = new SeoPage(driver);
        dfy_VideoPage = new Dfy_VideoPage(driver);
        copyWritingPage = new CopyWritingPage(driver);
   emailPage = new EmailPage(driver);
        faker = new Faker();
        loginPage.SignIn(config.getProperty("username"), config.getProperty("password"));
        researchPage.clickServeLink();

    }
//GMB
    @Test(priority = 1, dataProvider = "gmbData")
    public void VerifyGMBDescriptionTest(String industry, String city, String catchPhrase, String date, String achievement, String category) throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickGmbDescriptionLink();
        Assert.assertNotNull(industry);
        Assert.assertNotNull(city);
        Assert.assertNotNull(catchPhrase);
        Assert.assertNotNull(date);
        Assert.assertNotNull(achievement);
        Assert.assertNotNull(category);
        gmbPage.fillGmbForm(industry, city, catchPhrase, date, achievement, category);
    }
    @Test(priority = 2)
    public void VerifyGMBDescriptionTestWithoutData() throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickGmbDescriptionLink();
        gmbPage.fillGmbForm("","","","","","Professional");
    }
    //Yelp
   @Test(priority = 3)
    public void VerifyYelpDescriptionTest() throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickYelpDescriptionLink();
        String tone = getRandomOption( yelppage.yelpToneOptions());
        yelppage.fillYelpForm(faker.company().industry(), faker.address().city(), faker.company().catchPhrase(), faker.date().birthday().toString(), faker.name().fullName() + " achieved recognition for " + faker.lorem().sentence(), tone);
    }
    @Test(priority = 4)
    public void VerifyYelpDescriptionTestWithoutData() throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickYelpDescriptionLink();
        yelppage.fillYelpForm("","","","","","Informal");
    }
    //
    @Test(priority = 5)
    public void VerifySEOTitlesAndHeadersTest() throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickSeoTitlesAndHeadersLink();
        Assert.assertEquals(seoPage.getHeader(),"SEO Titles & Headers");
        String tone = getRandomOption( seoPage.getSeoToneOptions());
        String companyName = faker.company().name();
        String industry = faker.company().industry();
        String product = faker.commerce().productName();
        String benefit = faker.company().catchPhrase();
        // Generate components for a meta title
        String adjective = faker.commerce().material();
        String metaTitle = product + " - " + adjective;
        seoPage.fillSeoForm(faker.book().title(),faker.lorem().word(),faker.lorem().word(),companyName + " provides exceptional " + product + " for " + industry + ". Our unique value proposition: " + benefit + ".",tone,metaTitle,faker.lorem().sentence(15, 20));
    }
    @Test(priority = 6)
    public void VerifySEOTitlesAndHeadersTestWithoutData() throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickSeoTitlesAndHeadersLink();
        Assert.assertEquals(seoPage.getHeader(),"SEO Titles & Headers");
        seoPage.fillSeoForm("","","","","Enticing","","");
    }
    //DFY Video Script
    @Test(priority = 7)
    public void VerifyDFYVideoScriptTest() throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickDfyVideoScriptLink();
        String tone=getRandomOption( dfy_VideoPage.getToneAndStyleOptions());
dfy_VideoPage.fillDfyVideoForm(faker.company().catchPhrase(),faker.company().catchPhrase(),tone);
        Assert.assertEquals(dfy_VideoPage.getHeaderMsg(),"DFY Video Script","Successfully able to navigate to DFY Video Script page");
    }
    @Test(priority = 8)
    public void VerifyDFYVideoScriptTestWithoutData() throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickDfyVideoScriptLink();
        String tone=getRandomOption( dfy_VideoPage.getToneAndStyleOptions());
        dfy_VideoPage.fillDfyVideoForm("","",tone);
        Assert.assertEquals(dfy_VideoPage.getHeaderMsg(),"DFY Video Script","Successfully able to navigate to DFY Video Script page");
    }

//Ad Copywriting
    @Test(priority = 9)
    public void VerifyAdCopywritingTest() throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickAdCopywritingLink();
        String tone=getRandomOption( copyWritingPage.getToneAndStyleOptions());
        copyWritingPage.fillCopyWritingForm(faker.company().catchPhrase(),faker.company().catchPhrase(),tone);
Assert.assertEquals(copyWritingPage.getHeaderMsg(),"Ad Copywriting","Successfully able to navigate to Ad Copywriting page");
    }
    @Test(priority = 10)
    public void VerifyAdCopywritingTestWithoutData() throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickAdCopywritingLink();
        String tone=getRandomOption( copyWritingPage.getToneAndStyleOptions());
        copyWritingPage.fillCopyWritingForm("","",tone);
        Assert.assertEquals(copyWritingPage.getHeaderMsg(),"Ad Copywriting","Successfully able to navigate to Ad Copywriting page");
    }
    //Email Writing
    @Test(priority = 11/*,retryAnalyzer = utilities.RetryAnalyzer.class*/)
    public void  VerifyEmailWritingTest() throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickEmailWritingLink();
        String tone=getRandomOption( emailPage.getToneAndStyleOptions());
        emailPage.fillEmailForm(faker.company().catchPhrase(),faker.company().catchPhrase(),tone);
        Assert.assertEquals(emailPage.getHeaderMsg(),"Email Writing","Successfully able to navigate to Email Writing page");
        System.out.println(driver.getTitle());
    }
    @Test(priority = 12/*,retryAnalyzer = utilities.RetryAnalyzer.class*/)
    public void  VerifyEmailWritingTestWithoutData() throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickEmailWritingLink();
        String tone=getRandomOption( emailPage.getToneAndStyleOptions());
        emailPage.fillEmailForm("","",tone);
        Assert.assertEquals(emailPage.getHeaderMsg(),"Email Writing","Successfully able to navigate to Email Writing page");
    }
    @DataProvider(name = "gmbData")
    public Object[][] getGMBData() {
Faker faker = new Faker();
        Object[][] data = new Object[][]{
                { faker.company().industry(), faker.address().city(), faker.company().catchPhrase(), faker.date().birthday().toString(), faker.name().fullName() + " achieved recognition for " + faker.lorem().sentence(), "Professional" },
                { faker.company().industry(), faker.address().city(), faker.company().catchPhrase(), faker.date().birthday().toString(), faker.name().fullName() + " achieved recognition for " + faker.lorem().sentence(), "Energetic" },
                { faker.company().industry(), faker.address().city(), faker.company().catchPhrase(), faker.date().birthday().toString(), faker.name().fullName() + " achieved recognition for " + faker.lorem().sentence(), "Sophisticated" }
        };
        return data;
    }
    @DataProvider(name = "yelpData")
    public Object[][] getYelpData() {
        faker = new Faker();
        Object[][] data = new Object[][]{
                { faker.company().industry(), faker.address().city(), faker.company().catchPhrase(), faker.date().birthday().toString(), faker.name().fullName() + " achieved recognition for " + faker.lorem().sentence(), "Professional" },
                { faker.company().industry(), faker.address().city(), faker.company().catchPhrase(), faker.date().birthday().toString(), faker.name().fullName() + " achieved recognition for " + faker.lorem().sentence(), "Energetic" },
                { faker.company().industry(), faker.address().city(), faker.company().catchPhrase(), faker.date().birthday().toString(), faker.name().fullName() + " achieved recognition for " + faker.lorem().sentence(), "Sophisticated" }
        };
        return data;
    }
    public String getRandomOption(List<WebElement> x) {
        List<WebElement> options = x;
        Random rand = new Random();
        int randomIndex = rand.nextInt(options.size());
        return options.get(randomIndex).getText();
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
