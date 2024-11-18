package pages;

import constants.Constants;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtilities;

public class LoginPage {
     public RemoteWebDriver driver;
     public WaitUtilities waitUtilities ;
    @FindBy(name="password")
    private WebElement password;
    @FindBy(id="username")
    private WebElement username;
    @FindBy(xpath = "(//input[@type='submit'])[1]")
    private WebElement loginButton;
    @FindBy(linkText = "Forgot your password?")
    private WebElement forgotPasswordLink;
    @FindBy(name="remember")
    private WebElement rememberMeCheckbox;
    @FindBy(id="passwordToggleIcon")
    private WebElement passwordToggleIcon;
    public LoginPage(RemoteWebDriver driver) {
        this.driver = driver;
        waitUtilities= new WaitUtilities(driver);
         PageFactory.initElements(driver, this);
    }
    public void SignIn(String username, String password) {
        waitUtilities.fluentWait(this.username, Constants.timeout, Constants.pollingTime).sendKeys(username);
        waitUtilities.fluentWait(this.password, Constants.timeout, Constants.pollingTime).sendKeys(password);
        waitUtilities.fluentWait(this.rememberMeCheckbox, Constants.timeout, Constants.pollingTime).click();
        driver.executeScript("arguments[0].scrollIntoView();", this.loginButton);
        try {
            waitUtilities.fluentWait(this.loginButton, Constants.timeout, Constants.pollingTime).click();
        }catch (TimeoutException e){
            System.out.println("Element did not become stale within the timeout period. Proceeding with a direct click.");
        }
    }
    public void emailInput(String email) {
        this.username.sendKeys(email);
    }
    public void passwordInput(String password) {
        this.password.sendKeys(password);
    }
    public void clickRememberMeCheckbox() {
        this.rememberMeCheckbox.click();
    }
    public ResearchPage clickSignInButton() {
        loginButton.click();
        return new ResearchPage(driver);
    }
    public void clickForgotPasswordLink() {
        waitUtilities.fluentWait(this.forgotPasswordLink, Constants.timeout, Constants.pollingTime).click();
    }


}


