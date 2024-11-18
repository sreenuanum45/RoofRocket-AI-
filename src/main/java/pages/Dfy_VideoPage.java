package pages;

import constants.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtilities;

import java.util.List;

public class Dfy_VideoPage {
    public RemoteWebDriver driver;
    public WaitUtilities waitUtilities;
    @FindBy(id = "dfy_niche")
    private WebElement dfyNiche;
    @FindBy(id = "main_points")
    private WebElement mainPoints;
    @FindBy(id = "tone_and_style")
    private WebElement toneAndStyle;
    @FindBy(xpath = "(//h2)[2]")
    private WebElement Headermsg;
    @FindBy(id="tone_and_style")
    private WebElement toneAndStyleDropdown;
    @FindBy(xpath = "//select[@id='tone_and_style']//option")
    private List<WebElement> toneAndStyleOptions;
    @FindBy(id = "submit-btn")
    private WebElement submitButton;
    public Dfy_VideoPage(RemoteWebDriver driver) {
        this.driver = driver;
        this.waitUtilities = new WaitUtilities(driver);
        PageFactory.initElements(driver, this);
    }
    public void enterDfyNiche(String niche) {
        waitUtilities.fluentWait(dfyNiche, Constants.timeout, Constants.pollingTime).sendKeys(niche);
    }
    public void enterMainPoints(String points) {
        waitUtilities.fluentWait(mainPoints, Constants.timeout, Constants.pollingTime).clear();
        waitUtilities.fluentWait(mainPoints, Constants.timeout, Constants.pollingTime).sendKeys(points);
    }
    public void enterToneAndStyle(String tone) {
        waitUtilities.fluentWait(toneAndStyle, Constants.timeout, Constants.pollingTime).sendKeys(tone);
    }
    public void clickSubmit() {
        waitUtilities.fluentWait(submitButton, Constants.timeout, Constants.pollingTime).click();
    }
    public String getHeaderMsg() {
        return waitUtilities.fluentWait(Headermsg, Constants.timeout, Constants.pollingTime).getText();
    }
    public void selectToneAndStyle(String tone) {

        waitUtilities.fluentWait(toneAndStyleDropdown, Constants.timeout, Constants.pollingTime).click();
        for (WebElement option : toneAndStyleOptions) {
            if (option.getText().equals(tone)) {
                option.click();
                break;
            }
        }
    }
    public List<WebElement> getToneAndStyleOptions() {

        return waitUtilities.fluentWait(toneAndStyleOptions, Constants.timeout, Constants.pollingTime);
    }
    public void fillDfyVideoForm(String niche, String points, String tone) {
        enterDfyNiche(niche);
        enterMainPoints(points);
        selectToneAndStyle(tone);
        clickSubmit();
    }


}
