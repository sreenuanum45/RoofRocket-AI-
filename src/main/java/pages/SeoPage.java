package pages;

import constants.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtilities;

import java.util.List;

public class SeoPage {
    public RemoteWebDriver driver;
    public WaitUtilities waitUtilities;
@FindBy(id="seo_page_topic")
    private WebElement seoPageTopic;
@FindBy(id="seo_main_keyword")
    private WebElement seoMainKeyword;
@FindBy(id="seo_secondary_keywords")
    private WebElement seoSecondaryKeywords;
@FindBy(id="seo_unique_value_prop")
    private WebElement seoUniqueValueProp;
@FindBy(id="seo_tone")
    private WebElement seoTone;
@FindBy(xpath ="//select[@id='seo_tone']//option")
    private List<WebElement> seoToneOptions;
@FindBy(id="seo_meta_title")
    private WebElement seoMetaTitle;
@FindBy(id="seo_meta_description")
    private WebElement seoMetaDescription;
@FindBy(id="submit-btn")
    private WebElement submitButton;
@FindBy(xpath = "(//h2)[2]")
    private WebElement Header;
    public SeoPage(RemoteWebDriver driver) {
        this.driver = driver;
        waitUtilities = new WaitUtilities(driver);
        PageFactory.initElements(driver, this);
    }
    public  void fillSeoForm(String pageTopic, String mainKeyword, String secondaryKeywords, String uniqueValueProp, String tone, String metaTitle, String metaDescription){
        enterSeoPageTopic(pageTopic);
        enterSeoMainKeyword(mainKeyword);
        enterSeoSecondaryKeywords(secondaryKeywords);
        enterSeoUniqueValueProp(uniqueValueProp);
        enterSeoTone(tone);
        enterSeoMetaTitle(metaTitle);
        enterSeoMetaDescription(metaDescription);
       clickSubmitButton();
    }
    public void enterSeoPageTopic(String pageTopic){
        waitUtilities.fluentWait(this.seoPageTopic, Constants.timeout, Constants.pollingTime).sendKeys(pageTopic);
    }
    public void enterSeoMainKeyword(String mainKeyword){
        waitUtilities.fluentWait(this.seoMainKeyword, Constants.timeout, Constants.pollingTime).sendKeys(mainKeyword);
    }
    public void enterSeoSecondaryKeywords(String secondaryKeywords){
        waitUtilities.fluentWait(this.seoSecondaryKeywords, Constants.timeout, Constants.pollingTime).sendKeys(secondaryKeywords);
    }
    public void enterSeoUniqueValueProp(String uniqueValueProp){
        waitUtilities.fluentWait(this.seoUniqueValueProp, Constants.timeout, Constants.pollingTime).sendKeys(uniqueValueProp);
    }
    public void enterSeoTone(String tone){
        waitUtilities.fluentWait(this.seoTone, Constants.timeout, Constants.pollingTime).sendKeys(tone);
    }
    public void enterSeoMetaTitle(String metaTitle){
        waitUtilities.fluentWait(this.seoMetaTitle, Constants.timeout, Constants.pollingTime).sendKeys(metaTitle);
    }
    public void enterSeoMetaDescription(String metaDescription){
        waitUtilities.fluentWait(this.seoMetaDescription, Constants.timeout, Constants.pollingTime).sendKeys(metaDescription);
    }
    public void clickSubmitButton(){
        waitUtilities.fluentWait(this.submitButton, Constants.timeout, Constants.pollingTime).click();
    }
    public List<WebElement> getSeoToneOptions(){
        return waitUtilities.fluentWait(this.seoToneOptions, Constants.timeout, Constants.pollingTime);
    }
    public String getHeader(){
        return waitUtilities.fluentWait(this.Header, Constants.timeout, Constants.pollingTime).getText();
    }

}
