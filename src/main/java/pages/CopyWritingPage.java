package pages;

import constants.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtilities;

import java.util.List;

public class CopyWritingPage {
    public RemoteWebDriver driver;
    public WaitUtilities waitUtilities;
@FindBy(id="product_or_service")
private WebElement productOrService;
@FindBy(id="main_points")
private WebElement mainPoints;
@FindBy(id="tone_and_style")
private WebElement toneAndStyle;
@FindBy(xpath ="//select[@id='tone_and_style']//option")
private List<WebElement> toneAndStyleOptions;
@FindBy(id="submit-btn")
private WebElement submitButton;
@FindBy(xpath = "(//h2)[2]")
private WebElement Headermsg;
public CopyWritingPage(RemoteWebDriver driver) {
        this.driver = driver;
        this.waitUtilities = new WaitUtilities(driver);
        PageFactory.initElements(driver, this);
    }
    public void enterProductOrService(String product) {
        waitUtilities.fluentWait(productOrService, Constants.timeout, Constants .pollingTime).sendKeys(product);
    }
    public void enterMainPoints(String points) {
    waitUtilities.fluentWait(mainPoints, Constants.timeout, Constants .pollingTime).clear();
        waitUtilities.fluentWait(mainPoints, Constants.timeout, Constants .pollingTime).sendKeys(points);
    }
    public void selectToneAndStyle(String tone) {
        waitUtilities.fluentWait(toneAndStyle, Constants.timeout, Constants.pollingTime).click();
        for (WebElement option : toneAndStyleOptions) {
            if (option.getText().equals(tone)) {
                option.click();
                break;
            }
        }
    }
    public void clickSubmit() {
        waitUtilities.fluentWait(submitButton, Constants.timeout, Constants.pollingTime).click();
    }
    public String getHeaderMsg() {
        return waitUtilities.fluentWait(Headermsg, Constants.timeout, Constants.pollingTime).getText();
    }
    public void fillCopyWritingForm(String product, String points, String tone) {
        enterProductOrService(product);
        enterMainPoints(points);
        selectToneAndStyle(tone);
        clickSubmit();
    }
    public List<WebElement> getToneAndStyleOptions() {
        return waitUtilities.fluentWait(toneAndStyleOptions, Constants.timeout, Constants.pollingTime);
    }
}
