package pages;

import constants.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtilities;

import java.util.List;

public class EmailPage {
    RemoteWebDriver driver;
    public WaitUtilities waitUtilities;
    @FindBy(id="email_subject_line")
    public WebElement emailSubjectLine;
    @FindBy(id="main_points")
    public WebElement mainPoints;
    @FindBy(id="tone_and_style")
    public WebElement toneAndStyle;
    @FindBy(xpath = "(//select[@id='tone_and_style'])//option")
    public List<WebElement> toneAndStyleOptions;
    @FindBy(id="submit-btn")
    public WebElement submitButton;
    @FindBy(xpath = "(//h2)[2]")
    public WebElement Headermsg;
    public EmailPage(RemoteWebDriver driver){
        this.driver= this.driver;
        waitUtilities=new WaitUtilities(this.driver);
        PageFactory.initElements(this.driver,this);
    }
    public void enterEmailSubjectLine(String subjectLine){
        waitUtilities.fluentWait(emailSubjectLine, Constants.timeout, Constants.pollingTime).sendKeys(subjectLine);
    }
    public void enterMainPoints(String points){
        waitUtilities.fluentWait(mainPoints, Constants.timeout, Constants.pollingTime).clear();
        waitUtilities.fluentWait(mainPoints, Constants.timeout, Constants.pollingTime).sendKeys(points);
    }
    public void selectToneAndStyle(String tone){
        waitUtilities.fluentWait(toneAndStyle, Constants.timeout, Constants.pollingTime).click();
        for(WebElement option: toneAndStyleOptions){
            if(option.getText().equals(tone)){
                option.click();
                break;
            }
        }
    }
    public void clickSubmit(){
        waitUtilities.fluentWait(submitButton, Constants.timeout, Constants.pollingTime).click();
    }
    public String getHeaderMsg(){
        return waitUtilities.fluentWait(Headermsg, Constants.timeout, Constants.pollingTime).getText();
    }
    public List<WebElement> getToneAndStyleOptions(){
        return waitUtilities.fluentWait(toneAndStyleOptions, Constants.timeout, Constants.pollingTime);
    }
    public void fillEmailForm(String subjectLine, String points, String tone){
        enterEmailSubjectLine(subjectLine);
        enterMainPoints(points);
        selectToneAndStyle(tone);
        clickSubmit();
    }
}
