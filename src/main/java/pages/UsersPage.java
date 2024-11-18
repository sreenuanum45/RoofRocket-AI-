package pages;

import constants.Constants;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtilities;

import java.util.List;

public class UsersPage {
    RemoteWebDriver driver;
    WaitUtilities waitUtilities;
    @FindBy(xpath = "//a[normalize-space()='Create New User']")
    public WebElement createNewUser;
    @FindBy(xpath = "//table")
    public WebElement table;
    @FindBy(xpath = "//div[@class='page-title-box']//h2")
    public WebElement headermsg;
    @FindBy(xpath = "(//td[8]/a)[position() mod 3 = 0]")
    private List<WebElement> deleteButton;
    @FindBy(xpath = "//button[text()='Yes, delete it!']")
    public WebElement yesButtonToDelete;
    @FindBy(xpath = "//button[text()='No, cancel!']")
    public WebElement noButtonToDelete;
    @FindBy(xpath = "//strong[text()='One user deleted']")
    public WebElement oneUserDeletedSuccessMessage;
    @FindBy(xpath = "//td[@class='action_link']//a[2]")
    public List<WebElement> editButton;
    @FindBy(xpath = "//td[@class='action_link']//a[1]")
    private List<WebElement> resetUser;

    public UsersPage(RemoteWebDriver driver) {
        this.driver = driver;
        waitUtilities = new WaitUtilities(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickCreateNewUser() {

        waitUtilities.fluentWait(createNewUser, Constants.timeout, Constants.pollingTime);
        waitUtilities.fluentWait(createNewUser, Constants.timeout, Constants.pollingTime).click();
    }

    public WebElement getTable() {
        try {
            driver.executeScript("arguments[0].scrollIntoView();", table);
            return waitUtilities.fluentWait(table, Constants.timeout, Constants.pollingTime);
        } catch (TimeoutException e) {
            System.out.println("Element did not become stale within the timeout period. Proceeding with a direct click.");
        }
        return table;
    }

    public String getHeaderMsg() {
        return waitUtilities.fluentWait(headermsg, Constants.timeout, Constants.pollingTime).getText();
    }

    public List<WebElement> getDeleteButton() {
        return waitUtilities.fluentWait(deleteButton, Constants.timeout, Constants.pollingTime);


    }

    public void clickYesButtonToDelete() {
        waitUtilities.fluentWait(yesButtonToDelete, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickNoButtonToDelete() {
        waitUtilities.fluentWait(noButtonToDelete, Constants.timeout, Constants.pollingTime).click();
    }
    public boolean isOneUserDeletedSuccessMessageDisplayed() {
        return waitUtilities.fluentWait(oneUserDeletedSuccessMessage, Constants.timeout, Constants.pollingTime).isDisplayed();
    }
    public String getOneUserDeletedSuccessMessage() {
        return waitUtilities.fluentWait(oneUserDeletedSuccessMessage, Constants.timeout, Constants.pollingTime).getText();
    }
    public List<WebElement> getEditButton() {
        try{
            waitUtilities.fluentWaitForStalenessOfList(editButton, Constants.timeout, Constants.pollingTime);
        }catch (TimeoutException e){
            System.out.println("Element did not become stale within the timeout period. Proceeding with a direct click.");
        }

        return waitUtilities.fluentWait(editButton, Constants.timeout, Constants.pollingTime);
    }
    public List<WebElement> getResetUser() {
        return waitUtilities.fluentWait(resetUser, Constants.timeout, Constants.pollingTime);
    }
    public int getResetUserSize() {
        return waitUtilities.fluentWait(resetUser, Constants.timeout, Constants.pollingTime).size();
    }
    public int getEditButtonSize() {
        return waitUtilities.fluentWait(editButton, Constants.timeout, Constants.pollingTime).size();
    }
}
