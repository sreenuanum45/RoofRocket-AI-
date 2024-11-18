package pages;

import com.github.javafaker.Faker;
import constants.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import utilities.WaitUtilities;

import java.util.List;
import java.util.Random;

public class GmbPage {
    public RemoteWebDriver driver;
    public WaitUtilities waitUtilities ;
    @FindBy(id="serve-dropdown")
    private WebElement serveDropdown;
    @FindBy(xpath = "//ul[@id='serve-dropdown']//li")
    private List<WebElement> childElements;
@FindBy(id="gmb_niche")
private WebElement gmbNiche;
@FindBy(id="gmb_location")
private WebElement gmbLocation;
@FindBy(id="gmb_special")
private WebElement gmbSpecial;
@FindBy(id="gmb_years")
private WebElement gmbYears;
@FindBy(id="gmb_achievements")
private WebElement gmbAchievements;
@FindBy(id="gmb_tone")
private WebElement gmbTone;
@FindBy(xpath = "//select[@id='gmb_tone']//option")
private List<WebElement> gmbToneOptions;
@FindBy(xpath = "//input[@type='button']")
private WebElement submitButton;
public GmbPage(RemoteWebDriver driver) {
        this.driver = driver;
        waitUtilities= new WaitUtilities(driver);
        PageFactory.initElements(driver, this);
    }
    public void printChildElements() {
        for (WebElement childElement : childElements) {
            System.out.println(childElement.getText());
        }
    }
    public void enterGmbNiche(String niche) {
        waitUtilities.fluentWait(this.gmbNiche, Constants.timeout, Constants.pollingTime).sendKeys(niche);
    }
    public void enterGmbLocation(String location) {
        waitUtilities.fluentWait(this.gmbLocation, Constants.timeout, Constants.pollingTime).sendKeys(location);
    }
    public void enterGmbSpecial(String special) {
        waitUtilities.fluentWait(this.gmbSpecial, Constants.timeout, Constants.pollingTime).sendKeys(special);
    }
    public void enterGmbYears(String years) {
        waitUtilities.fluentWait(this.gmbYears, Constants.timeout, Constants.pollingTime).sendKeys(years);
    }
    public void enterGmbAchievements(String achievements) {
        waitUtilities.fluentWait(this.gmbAchievements, Constants.timeout, Constants.pollingTime).sendKeys(achievements);
    }
    public void selectGmbTone(String tone) {
        waitUtilities.fluentWait(this.gmbTone, Constants.timeout, Constants.pollingTime).click();
        for (WebElement option : gmbToneOptions) {
            if (option.getText().equals(tone)) {
                option.click();
                break;
            }
        }
    }
    public void clickSubmitButton() {
        waitUtilities.fluentWait(this.submitButton, Constants.timeout, Constants.pollingTime).click();
    }

    public void fillGmbForm(String niche, String location, String special, String years, String achievements, String tone) {
        enterGmbNiche(niche);
        enterGmbLocation(location);
        enterGmbSpecial(special);
        enterGmbYears(years);
        enterGmbAchievements(achievements);
        selectGmbTone(tone);
        clickSubmitButton();
    }
    public List<WebElement> gmbToneOptions() {
      return waitUtilities.fluentWait(this.gmbToneOptions, Constants.timeout, Constants.pollingTime);
    }


}
