package pages;

import constants.Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtilities;

import java.util.List;
import java.util.Random;

public class Yelppage {
    public RemoteWebDriver driver;
    public WaitUtilities waitUtilities;
    @FindBy(id = "yelp_niche")
    private WebElement yelpNiche;
    @FindBy(id = "yelp_location")
    private WebElement yelpLocation;
    @FindBy(id = "yelp_special")
    private WebElement yelpSpecial;
    @FindBy(id = "yelp_years")
    private WebElement yelpYears;
    @FindBy(id = "yelp_favorites")
    private WebElement yelpFavorites;
    @FindBy(id = "yelp_tone")
    private WebElement yelpTone;
    @FindBy(xpath = "//select[@id='yelp_tone']//option")
    private List<WebElement> yelpToneOptions;
    @FindBy(xpath = "//input[@type='button']")
    private WebElement submitButton;

    public Yelppage(RemoteWebDriver driver) {
        this.driver = driver;
        waitUtilities = new WaitUtilities(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillYelpForm(String niche, String location, String special, String years, String achievements, String tone) {
        enterYelpNiche(niche);
        enterYelpLocation(location);
        enterYelpSpecial(special);
        enterYelpYears(years);
        enterYelpAchievements(achievements);
        selectYelpTone(tone);
        clickSubmitButton();

    }

    public void enterYelpNiche(String niche) {
        waitUtilities.fluentWait(this.yelpNiche, Constants.timeout, Constants.pollingTime).sendKeys(niche);
    }

    public void enterYelpLocation(String location) {
        waitUtilities.fluentWait(this.yelpLocation, Constants.timeout, Constants.pollingTime).sendKeys(location);
    }

    public void enterYelpSpecial(String special) {
        waitUtilities.fluentWait(this.yelpSpecial, Constants.timeout, Constants.pollingTime).sendKeys(special);
    }

    public void enterYelpYears(String years) {
        waitUtilities.fluentWait(this.yelpYears, Constants.timeout, Constants.pollingTime).sendKeys(years);
    }

    public void enterYelpAchievements(String achievements) {
        waitUtilities.fluentWait(this.yelpFavorites, Constants.timeout, Constants.pollingTime).sendKeys(achievements);
    }

    public void selectYelpTone(String tone) {
        waitUtilities.fluentWait(this.yelpTone, Constants.timeout, Constants.pollingTime).click();
        for (WebElement option : yelpToneOptions) {
            if (option.getText().equals(tone)) {
                option.click();
                break;
            }
        }

    }
    public void clickSubmitButton() {
        waitUtilities.fluentWait(this.submitButton, Constants.timeout, Constants.pollingTime).click();
    }
    public List<WebElement> yelpToneOptions() {
        return waitUtilities.fluentWait(this.yelpToneOptions, Constants.timeout, Constants.pollingTime);
    }
    public String getRandomOption(List<WebElement> x) {
        List<WebElement> options = x;
        Random rand = new Random();
        int randomIndex = rand.nextInt(options.size());
        return options.get(randomIndex).getText();
    }

}
