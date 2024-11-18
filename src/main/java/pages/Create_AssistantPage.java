package pages;

import constants.Constants;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtilities;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

public class Create_AssistantPage {
    public RemoteWebDriver driver;
    public WaitUtilities waitUtilities;
    @FindBy(id="businessName")
    private WebElement businessName;
    @FindBy(id="phoneNumber")
    private WebElement phoneNumber;
    @FindBy(id="emailAddress")
    private WebElement emailAddress;
    @FindBy(xpath = "(//button[text()='Save & Next'])[1]")
    private WebElement saveAndNextButton;
    @FindBy(xpath = "//h1[text()='Add Business Details:']")
    private WebElement addBusinessDetails;
    @FindBy(className ="steps")
    private WebElement steps;
    @FindBy(id="botName")
    private WebElement botName;
    @FindBy(id="description")
    private WebElement description;
    @FindBy(xpath = "(//input[@type='text'])[3]")
    private WebElement AddKnowledgeFromWebsite;
@FindBy(linkText = "Get Knowledge")
    private WebElement getKnowledge;
@FindBy(linkText = "CHOOSE FILE")
    private WebElement uploadFile;
@FindBy(id="click_file")
    private WebElement clickFile;
@FindBy(xpath="(//input[@name='starters[]'])[1]")
private WebElement ConversationStarters;
@FindBy(xpath ="(//input[@name='starters[]'])[2]")
private WebElement ConversationStarters1;
@FindBy(xpath = "//button[text()='+']")
private WebElement addConversationStarters;
@FindBy(id="instructions")
private WebElement instructions;
@FindBy(id="prevStep2")
private WebElement prevStep2;
@FindBy(xpath = "(//button[text()='Save & Next'])[2]")
private WebElement saveAndNextButton2;
@FindBy(xpath = "//a[normalize-space()='generate Roofer AI Assistant for me']")
private WebElement generateRooferAIAssistant;
@FindBy(id="botRoleInput")
private WebElement botRole;
@FindBy(xpath = "(//button[text()='Submit'])[1]")
private WebElement submitButton;
@FindBy(xpath = "(//div//button[@class='btn-close'])[1]")
private WebElement closeButton;
    @FindBy(xpath = "//div[@id='alertDiv']//span")
    private WebElement alertMsg;
    @FindBy(xpath = "(//img[@class='remove'])[3]")
    public WebElement removeConversationStarters;
    @FindBy(xpath = "(//img[@class='remove'])[4]")
    public WebElement removeConversationStarters1;

    public Create_AssistantPage(RemoteWebDriver driver) {
        this.driver = driver;
        this.waitUtilities = new WaitUtilities(driver);
        PageFactory.initElements(driver, this);
    }
    //step1
    public void createAssistantForm(String businessName, String phoneNumber, String emailAddress)throws InterruptedException{
        enterBusinessName(businessName);
        enterPhoneNumber(phoneNumber);
        enterEmailAddress(emailAddress);
        clickSaveAndNextButton();
    }
    public void clickSaveAndNextButton()throws InterruptedException{
        try {
            waitUtilities.fluentWaitForStaleness(this.saveAndNextButton, Constants.timeout, Constants.pollingTime);

        }
        catch (TimeoutException e)
        {
System.out.println("Element did not become stale within the timeout period. Proceeding with a direct click.");
        }
            waitUtilities.fluentWait(this.saveAndNextButton, Constants.timeout, Constants.pollingTime).click();

      }

    public void enterBusinessName(String businessName){
       waitUtilities.fluentWait(this.businessName, Constants.timeout, Constants.pollingTime).sendKeys(businessName);
    }
    public void enterPhoneNumber(String phoneNumber){
        waitUtilities.fluentWait(this.phoneNumber, Constants.timeout, Constants.pollingTime).sendKeys(phoneNumber);
    }
    public void enterEmailAddress(String emailAddress){
            waitUtilities.fluentWait(this.emailAddress, Constants.timeout, Constants.pollingTime).sendKeys(emailAddress);
    }
    public boolean isAddBusinessDetailsDisplayed(){
        return waitUtilities.fluentWait(this.addBusinessDetails, Constants.timeout, Constants.pollingTime).isDisplayed();
    }
    public boolean isStepsDisplayed(){
        return waitUtilities.fluentWait(this.steps, Constants.timeout, Constants.pollingTime).isDisplayed();
    }
    public String getAlertMsg() {
        if (waitUtilities.fluentWait(this.alertMsg, Constants.timeout, Constants.pollingTime).isDisplayed()) {
            return waitUtilities.fluentWait(this.alertMsg, Constants.timeout, Constants.pollingTime).getText();
        } else {
            return null;
        }

    }
    //step2
    public void enterBotName(String botName) {
        waitUtilities.fluentWait(this.botName, Constants.timeout, Constants.pollingTime).sendKeys(botName);
    }
    public void enterDescription(String description) {
        waitUtilities.fluentWait(this.description, Constants.timeout, Constants.pollingTime).sendKeys(description);
    }
    public void AddKnowledgeFromWebsite(String AddKnowledgeFromWebsite) {
        driver.executeScript("arguments[0].scrollIntoView();", this.AddKnowledgeFromWebsite);
        try {
            waitUtilities.waitForLoaderToDisappear(this.AddKnowledgeFromWebsite, Constants.timeout);
            waitUtilities.fluentWait(this.AddKnowledgeFromWebsite, Constants.timeout, Constants.pollingTime).sendKeys(AddKnowledgeFromWebsite);
        }catch (TimeoutException e) {
            System.out.println("Element did not become stale within the timeout period. Proceeding with a direct click.");
        }
    }
    public void clickGetKnowledge() {
        waitUtilities.fluentWait(this.getKnowledge, Constants.timeout, Constants.pollingTime).click();
    }
    public void uploadFile(String filePath) throws AWTException, ElementNotInteractableException, InterruptedException {
        // Scroll the element into view
        driver.executeScript("arguments[0].scrollIntoView();", this.uploadFile);

// Wait and click on the upload button
        try {
         /*   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.timeout));
            wait.until(ExpectedConditions.elementToBeClickable(this.uploadFile)).click();*/
            waitUtilities.fluentWait(this.uploadFile, Constants.timeout, Constants.pollingTime).click();
            System.out.println("Clicked on the upload button");
        } catch (Exception e) {
            System.err.println("Error clicking the upload button: " + e.getMessage());
            return;
        }

// Verify clipboard content
        try {
            StringSelection filePathSelection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePathSelection, null);
            System.out.println("File path copied to clipboard: " + filePath);

            // Debug clipboard content
            String clipboardContent = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
            System.out.println("Clipboard content: " + clipboardContent);
            if (!clipboardContent.equals(filePath)) {
                System.err.println("Clipboard content mismatch! Check the file path.");
                return;
            }
        } catch (Exception e) {
            System.err.println("Error setting clipboard content: " + e.getMessage());
            return;
        }

// Handle file upload dialog using Robot
        try {
            Robot robot = new Robot();

            // Ensure file dialog is in focus
            Thread.sleep(2000); // Wait for the dialog to appear

            // Paste the file path using Ctrl+V
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            System.out.println("Pasted file path using Robot");

            // Add delay for the file path to appear
            Thread.sleep(1000);

            // Press Enter to upload the file
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            System.out.println("Pressed Enter key using Robot");
        } catch (Exception e) {
            System.err.println("Error interacting with the file dialog: " + e.getMessage());
        }
    }
    public String gettextmsgOfFileSize() {
       return waitUtilities.fluentWait(this.clickFile, Constants.timeout, Constants.pollingTime).getText();
    }
    public void clickAddConversationStarters() {
        driver.executeScript("arguments[0].scrollIntoView();", this.addConversationStarters);
      //  waitUtilities.waitForLoaderToDisappear(this.addConversationStarters, Constants.timeout);
        waitUtilities.fluentWait(this.addConversationStarters, Constants.timeout, Constants.pollingTime).click();
    }
    public void enterConversationStarters(String conversationStarters) {
        waitUtilities.fluentWait(this.ConversationStarters, Constants.timeout, Constants.pollingTime).sendKeys(conversationStarters);
    }

    public void enterInstructions(String instructions) {
        waitUtilities.fluentWait(this.instructions, Constants.timeout, Constants.pollingTime).sendKeys(instructions);
    }
    public void clickPrevStep2() {
        waitUtilities.fluentWait(this.prevStep2, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickSaveAndNextButton2() {
        waitUtilities.fluentWait(this.saveAndNextButton2, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickGenerateRooferAIAssistant() {
        driver.executeScript("arguments[0].scrollIntoView();", this.generateRooferAIAssistant);
        try {
            waitUtilities.waitForLoaderToDisappear(this.generateRooferAIAssistant, Constants.timeout);
        }
        catch (TimeoutException e) {
            System.out.println("Element did not become stale within the timeout period. Proceeding with a direct click.");
        }
        waitUtilities.fluentWait(this.generateRooferAIAssistant, Constants.timeout, Constants.pollingTime).click();
    }
    public void enterBotRole(String botRole) {
        waitUtilities.fluentWait(this.botRole, Constants.timeout, Constants.pollingTime).sendKeys(botRole);
    }
    public void clickSubmitButton() {
        waitUtilities.fluentWait(this.submitButton, Constants.timeout, Constants.pollingTime).click();
    }
    public void clickCloseButton() {
        waitUtilities.fluentWait(this.closeButton, Constants.timeout, Constants.pollingTime).click();
    }
    public void enterConversationStarters1(String conversationStarters) {
        waitUtilities.fluentWait(this.ConversationStarters1, Constants.timeout, Constants.pollingTime).sendKeys(conversationStarters);
    }
    public void clickOnremovingConversationStarters() {
        waitUtilities.fluentWait(this.removeConversationStarters1, Constants.timeout, Constants.pollingTime).click();
        waitUtilities.fluentWait(this.removeConversationStarters, Constants.timeout, Constants.pollingTime).click();
    }

    public void fillTheConfigureRooferAIAssistant(String botName, String description, String AddKnowledgeFromWebsite, String conversationStarters, String instructions, String botRole) throws AWTException, ElementNotInteractableException, InterruptedException {
        enterBotName(botName);
        enterDescription(description);
        AddKnowledgeFromWebsite(AddKnowledgeFromWebsite);
        clickGetKnowledge();
        enterConversationStarters(conversationStarters);
        enterConversationStarters1(conversationStarters);
        enterInstructions(instructions);
        clickGenerateRooferAIAssistant();
        enterBotRole(botRole);
        clickCloseButton();
        clickAddConversationStarters();
        clickOnremovingConversationStarters();
        uploadFile(new File("D:\\Myprojects\\RoofRocketAI\\src\\test\\resources\\testdata\\Anumandlasreenu80 (2) (2).pdf").getAbsolutePath());
        Thread.sleep(5000);

    }

}
