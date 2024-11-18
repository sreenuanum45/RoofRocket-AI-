package pages;

import constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtilities;

public class ResearchPage {
    public RemoteWebDriver driver;
    public WaitUtilities waitUtilities ;
@FindBy(xpath = "//a[@role='button']")
private WebElement profileButton;
@FindBy(xpath = "//li[contains(@class,'nav-item dropdown') or @class='nav-item']")
private WebElement navigationbar;
    @FindBy(linkText = "Serve")
    private WebElement serveLink;
@FindBy(linkText = "Dashboard")
private WebElement dashboardLink;
@FindBy(linkText = "GMB Description")
private WebElement gmbDescriptionLink;
    @FindBy(linkText = "Yelp Description")
    private WebElement yelpDescriptionLink;
    @FindBy(linkText = "SEO Titles & Headers")
    private WebElement seoTitlesAndHeadersLink;
    @FindBy(linkText = "DFY Video Script")
    private WebElement dfyVideoScriptLink;
    @FindBy(linkText = "Ad Copywriting")
    private WebElement adCopywritingLink;
    @FindBy(linkText = "Email Writing")
    private WebElement emailWritingLink;
    @FindBy(linkText = "Facebook Post")
    private WebElement facebookPostLink;
    @FindBy(linkText = "Instagram Stories Script")
    private WebElement instagramStoriesScriptLink;
    @FindBy(linkText = "Twitter Post")
    private WebElement twitterPostLink;
    @FindBy(linkText = "Review Reply")
    private WebElement reviewReplyLink;
    @FindBy(linkText = "Restaurant Menu Creation")
    private WebElement restaurantMenuCreationLink;
    @FindBy(linkText = "SEO Description")
    private WebElement seoDescriptionLink;
    @FindBy(linkText = "Blog Content Creation")
    private WebElement blogContentCreationLink;
@FindBy(xpath = "//span[text()=' Roofer AI Assitant']//parent::a")
private WebElement rooferAiAssitantLink;
@FindBy(xpath = "//span[text()=' Users']//parent::a")
private WebElement usersLink;
    public ResearchPage(RemoteWebDriver driver) {
        this.driver = driver;
        waitUtilities= new WaitUtilities(driver);
        PageFactory.initElements(driver, this);
    }
    public boolean isLoginSuccessful() {
        return waitUtilities.fluentWait(profileButton, Constants.timeout, Constants.pollingTime).isDisplayed();
    }
    public void clickDashboardLink() {
        waitUtilities.fluentWait(dashboardLink, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickServeLink() {
        driver.executeScript("arguments[0].scrollIntoView();", serveLink);
        waitUtilities.fluentWait(serveLink, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickGmbDescriptionLink() {
        driver.executeScript("arguments[0].scrollIntoView();", gmbDescriptionLink);
        waitUtilities.fluentWait(gmbDescriptionLink, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickYelpDescriptionLink() {
        driver.executeScript("arguments[0].scrollIntoView();", yelpDescriptionLink);
        waitUtilities.fluentWait(yelpDescriptionLink, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickSeoTitlesAndHeadersLink() {
        driver.executeScript("arguments[0].scrollIntoView();", seoTitlesAndHeadersLink);
        waitUtilities.fluentWait(seoTitlesAndHeadersLink, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickDfyVideoScriptLink() {
        driver.executeScript("arguments[0].scrollIntoView();", dfyVideoScriptLink);
        waitUtilities.fluentWait(dfyVideoScriptLink, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickAdCopywritingLink() {
        driver.executeScript("arguments[0].scrollIntoView();", adCopywritingLink);
        waitUtilities.fluentWait(adCopywritingLink, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickEmailWritingLink() {
        driver.executeScript("arguments[0].scrollIntoView();", emailWritingLink);
        waitUtilities.fluentWait(emailWritingLink, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickFacebookPostLink() {
        driver.executeScript("arguments[0].scrollIntoView();", facebookPostLink);
        waitUtilities.fluentWait(facebookPostLink, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickInstagramStoriesScriptLink() {
        driver.executeScript("arguments[0].scrollIntoView();", instagramStoriesScriptLink);
        waitUtilities.fluentWait(instagramStoriesScriptLink, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickTwitterPostLink() {
        driver.executeScript("arguments[0].scrollIntoView();", twitterPostLink);
        waitUtilities.fluentWait(twitterPostLink, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickReviewReplyLink() {
        driver.executeScript("arguments[0].scrollIntoView();", reviewReplyLink);
        waitUtilities.fluentWait(reviewReplyLink, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickRestaurantMenuCreationLink() {
        driver.executeScript("arguments[0].scrollIntoView();", restaurantMenuCreationLink);
        waitUtilities.fluentWait(restaurantMenuCreationLink, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickSeoDescriptionLink() {
        driver.executeScript("arguments[0].scrollIntoView();", seoDescriptionLink);
        waitUtilities.fluentWait(seoDescriptionLink, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickBlogContentCreationLink() {
        driver.executeScript("arguments[0].scrollIntoView();", blogContentCreationLink);
        waitUtilities.fluentWait(blogContentCreationLink, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickRooferAiAssitantLink() {
        waitUtilities.fluentWait(rooferAiAssitantLink, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickUsersLink() {
        waitUtilities.fluentWait(usersLink, Constants.timeout, Constants.pollingTime).click();
    }
}
