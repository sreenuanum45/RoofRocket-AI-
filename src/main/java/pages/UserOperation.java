package pages;

import constants.Constants;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtilities;

import java.util.List;

public class UserOperation {
    RemoteWebDriver driver;
    WaitUtilities waitUtilities;
@FindBy(name="firstname")
private WebElement firstName;
@FindBy(name="lastname")
private WebElement lastName;
@FindBy(name="email")
private WebElement email;
@FindBy(name="password")
private WebElement password;
@FindBy(name = "access[]")
private WebElement CheckBox;
@FindBy(xpath = "(//button[text()='Submit'])[1]")
private WebElement submitButton;
@FindBy(xpath = "//div[@class='modal-alerts']//div[@class='alert alert-danger'][last()]")
private WebElement alertMessage;
@FindBy(xpath="//div[@class='alert alert-success']")
private WebElement AccountUpdatedSuccessMessage;



    public UserOperation(RemoteWebDriver driver) {
        this.driver = driver;
        waitUtilities = new WaitUtilities(driver);
        PageFactory.initElements(driver, this);
    }
    public void createNewUser(String firstName, String lastName, String email, String password) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPassword(password);
        clickCheckBox();
        clickSubmitButton();
    }
    public void enterFirstName(String firstName) {
        waitUtilities.fluentWait(this.firstName, Constants.timeout, Constants.pollingTime).sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        try {
            waitUtilities.fluentWait(this.lastName, Constants.timeout, Constants.pollingTime).sendKeys(lastName);
        } catch (TimeoutException e) {
            System.out.println("Element did not become stale within the timeout period. Proceeding with a direct click.");
        }
    }
        public void enterEmail (String email){
            try {
                waitUtilities.fluentWait(this.email, Constants.timeout, Constants.pollingTime).sendKeys(email);
            } catch (TimeoutException e) {
                System.out.println("Element did not become stale within the timeout period. Proceeding with a direct click.");
            }
        }

    public void enterPassword(String password) {
        waitUtilities.fluentWait(this.password, Constants.timeout, Constants.pollingTime).sendKeys(password);
    }
    public void clickSubmitButton() {
        waitUtilities.fluentWait(this.submitButton, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickCheckBox() {
        waitUtilities.fluentWait(this.CheckBox, Constants.timeout, Constants.pollingTime).click();
    }
    public String getAlertMessage() {
        return waitUtilities.fluentWait(this.alertMessage, Constants.timeout, Constants.pollingTime).getText();
    }
    public String getAccountUpdatedSuccessMessage() {
        return waitUtilities.fluentWait(this.AccountUpdatedSuccessMessage, Constants.timeout, Constants.pollingTime).getText();
    }
    public boolean isAccountUpdatedSuccessMessageDisplayed() {
        try{
        return waitUtilities.fluentWait(this.AccountUpdatedSuccessMessage, Constants.timeout, Constants.pollingTime).isDisplayed();
    }catch (TimeoutException e){
        System.out.println("Element did not become stale within the timeout period. Proceeding with a direct click.");
        return false;
    }
        }
        public  void clearFirstname(){
            waitUtilities.fluentWait(this.firstName, Constants.timeout, Constants.pollingTime).clear();
        }
        public void clearLastname(){
        try{
            waitUtilities.fluentWait(this.lastName, Constants.timeout, Constants.pollingTime).clear();
        }catch (TimeoutException e){
            System.out.println("Element did not become stale within the timeout period. Proceeding with a direct click.");
        }
        }
        public void clearEmail(){
            waitUtilities.fluentWait(this.email, Constants.timeout, Constants.pollingTime).clear();
        }
        public void clearPassword(){
            waitUtilities.fluentWait(this.password, Constants.timeout, Constants.pollingTime).clear();
        }


}
