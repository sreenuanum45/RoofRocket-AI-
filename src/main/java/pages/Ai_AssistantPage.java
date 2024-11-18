package pages;

import constants.Constants;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtilities;

public class Ai_AssistantPage {
    public RemoteWebDriver driver;
    public WaitUtilities waitUtilities;
    @FindBy(linkText = "+")
    private WebElement addChat;
    @FindBy(linkText = "Create Roofer AI Assistant")
    private WebElement createRooferAiAssistant;
    public Ai_AssistantPage(RemoteWebDriver driver) {
        this.driver = driver;
        this.waitUtilities = new WaitUtilities(driver);
        PageFactory.initElements(driver, this);
    }
    public void clickAddChat(){
        waitUtilities.fluentWait(addChat, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickCreateRooferAiAssistant(){
        try {
            waitUtilities.fluentWait(createRooferAiAssistant, Constants.timeout, Constants.pollingTime).click();
        }catch (TimeoutException e){
            System.out.println("Element did not become stale within the timeout period. Proceeding with a direct click.");
        }
        }
    public boolean isCreateRooferAiAssistantDisplayed(){
        return waitUtilities.fluentWait(createRooferAiAssistant, Constants.timeout, Constants.pollingTime).isDisplayed();
    }
    public boolean isAddChatDisplayed(){
        return waitUtilities.fluentWait(addChat, Constants.timeout, Constants.pollingTime).isDisplayed();
    }

}
