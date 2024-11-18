package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtilities;

public class Forgot_PasswordPage {
    public RemoteWebDriver driver;
    public WaitUtilities waitUtilities ;
    @FindBy(id="username")
    private WebElement username;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public Forgot_PasswordPage(RemoteWebDriver driver) {
        this.driver = driver;
        waitUtilities= new WaitUtilities(driver);
        PageFactory.initElements(driver, this);
    }
}
