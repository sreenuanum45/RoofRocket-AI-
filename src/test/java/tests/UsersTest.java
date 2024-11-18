package tests;

import base.BaseClass;
import com.github.javafaker.Faker;
import constants.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utilities.Utility.WebTableUtility;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsersTest extends BaseClass {
    public RemoteWebDriver driver;
    public LoginPage loginPage;
    public ResearchPage researchPage;
    public Create_AssistantPage create_AssistantPage;
    public GmbPage gmbPage;
    public Faker faker;
    public UserOperation userOperation;
    public Ai_AssistantPage ai_AssistantPage;
    public Yelppage yelppage;
    public SeoPage seoPage;
    public Dfy_VideoPage dfy_VideoPage;
    public CopyWritingPage copyWritingPage;
    public EmailPage emailPage;
    public WebTableUtility webTableUtility;
    public UsersPage usersPage;

    public UsersTest() {
        super();
    }

    @BeforeMethod
    public void setup() throws ElementClickInterceptedException, IOException, InterruptedException {
        driver = initializeBrowserAndOpenApplication(config.getProperty("browserName"));
        loginPage = new LoginPage(driver);
        researchPage = new ResearchPage(driver);
        usersPage = new UsersPage(driver);
        faker = new Faker();
        userOperation = new UserOperation(driver);
        webTableUtility = new WebTableUtility();
        ai_AssistantPage = new Ai_AssistantPage(driver);
        create_AssistantPage = new Create_AssistantPage(driver);
        loginPage.SignIn(config.getProperty("username"), config.getProperty("password"));
    }

    @Test(priority = 1)
    public void VerifyUsersTest() throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickUsersLink();
        Assert.assertEquals(driver.getTitle(), "Users | RoofRocket AI", "navigate to Users successful");
        Assert.assertEquals(usersPage.getHeaderMsg(), "Manage Users");
        int x = webTableUtility.getRowsCount(usersPage.getTable());
        System.out.println("Rows count is " + x);
        List<String> expectedHeaders = Arrays.asList(
                "Full Name",
                "Email Address",
                "Account Status",
                "User Type",
                "Created On",
                "Last Login",
                "Action"
        );
        // Get actual headers from the web table (simulate this for now)
        List<WebElement> actualHeaderElements = webTableUtility.getHeaders(usersPage.getTable());
        // Extract text from actual WebElements into a List
        List<String> actualHeaders = new ArrayList<>();
        for (WebElement header : actualHeaderElements) {
            actualHeaders.add(header.getText());
        }
        // Print both lists for verification
        System.out.println("Expected Headers: " + expectedHeaders);
        System.out.println("Actual Headers: " + actualHeaders);

        // Compare the two lists
        if (expectedHeaders.equals(actualHeaders)) {
            System.out.println("Headers match successfully!");
        } else {
            System.out.println("Headers do not match.");
        }
        webTableUtility.getCellValue(driver, usersPage.getTable(), 1, 1);
        int columnsCountInRow = webTableUtility.getColumnsCountInRow(usersPage.getTable(), 1);
        Assert.assertEquals(columnsCountInRow, 8);
        List<WebElement> cellChilds = webTableUtility.getCellChilds(usersPage.getTable(), 1, 1, By.tagName("a"));
        System.out.println("Cell Childs are " + cellChilds);
        try {
            List<WebElement> h = webTableUtility.getHeaders(usersPage.getTable());
            List<WebElement> r = webTableUtility.getRows(usersPage.getTable());
            // Create a FileWriter
            FileWriter writer = new FileWriter("D:\\Myprojects\\RoofRocketAI\\src\\test\\resources\\testdata\\web_table_data.csv");
            // Write headers to CSV
            for (WebElement header : h) {
                writer.append(header.getText());
                writer.append(",");
            }
            writer.append("\n");
            // Write rows to CSV
            for (WebElement row : r) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                for (WebElement cell : cells) {
                    writer.append(cell.getText());
                    writer.append(",");
                }
                writer.append("\n");
            }
            // Close the writer
            writer.flush();
            writer.close();
            System.out.println("Data has been written to web_table_data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    @Test(priority = 2)
    public void VerifyAddUserTest() throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickUsersLink();
        int usersCount = webTableUtility.getRows(usersPage.getTable()).size();
        usersPage.clickCreateNewUser();
        Assert.assertEquals(driver.getTitle(), "RoofRocket AI", "navigate to Create New User successful");
        System.out.println("Users count is " + usersCount);
        if (usersCount <= 6) {
            userOperation.createNewUser(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), faker.internet().password());
        } else {
            String msg = userOperation.getAlertMessage();
            System.out.println(msg);
        }
    }

    @Test(priority = 3)
    public void VerifyDeleteUserTestWithSingleUserDelete() throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickUsersLink();
        int usersCount = webTableUtility.getRows(usersPage.getTable()).size();
        int deletecount = usersPage.getDeleteButton().size();
        if (usersCount == deletecount) {
            int n = 0;
            for (WebElement ele : usersPage.getDeleteButton()) {
                ele.click();
                usersPage.clickYesButtonToDelete();
                if (usersPage.isOneUserDeletedSuccessMessageDisplayed()) {
                    String msg = usersPage.getOneUserDeletedSuccessMessage();
                    Assert.assertEquals(msg, "One user deleted", "User deleted successfully");
                    n++;
                    break;
                }
            }
            System.out.println("delete count is " + n);
            Assert.assertEquals(n, 1);
        }
    }

    @Test(priority = 4)
    public void VerifyDeleteUserTestWithMultipleUserDelete() throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickUsersLink();
        int usersCount = webTableUtility.getRows(usersPage.getTable()).size();
        int deletecount = usersPage.getDeleteButton().size();
        if (usersCount == deletecount) {
            int n = 0;
            for (WebElement ele : usersPage.getDeleteButton()) {
                ele.click();
                usersPage.clickYesButtonToDelete();
                if (usersPage.isOneUserDeletedSuccessMessageDisplayed()) {
                    String msg = usersPage.getOneUserDeletedSuccessMessage();
                    Assert.assertEquals(msg, "One user deleted", "User deleted successfully");
                    n++;
                }
            }
            System.out.println("delete count is " + n);
            Assert.assertEquals(n, deletecount);
        }
    }

    @Test(priority = 5)
    public void VerifyEditUserTestByOnlyOneUser() throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickUsersLink();
        int usersCount = webTableUtility.getRows(usersPage.getTable()).size();
        int editcount = usersPage.getEditButton().size();
        if (usersCount == editcount) {
            int n = 0;
            for (WebElement ele : usersPage.getEditButton()) {
                try {
                    driver.executeScript("arguments[0].scrollIntoView();", ele);
                    driver.executeScript("arguments[0].click();", ele);
                }catch (StaleElementReferenceException e){
                    fluentWait(ele, Constants.timeout, Constants.pollingTime);
                }
                catch (TimeoutException e) {
                    System.out.println("Element did not become stale within the timeout period. Proceeding with a direct click.");
                }catch (ElementClickInterceptedException e) {
                    System.out.println("Element did not become stale within the timeout period. Proceeding with a direct click.");
                    fluentWait(ele, Constants.timeout, Constants.pollingTime).click();
                }
                Assert.assertEquals(driver.getTitle(), "RoofRocket AI", "navigate to Edit User successful");
                userOperation.clearEmail();
                userOperation.enterEmail(faker.internet().emailAddress());
                userOperation.clearFirstname();
                userOperation.enterFirstName(faker.name().firstName());
                userOperation.clearLastname();
                userOperation.enterLastName(faker.name().lastName());
                n++;
                userOperation.clickSubmitButton();
                if (userOperation.isAccountUpdatedSuccessMessageDisplayed()) {
                    String msg = userOperation.getAccountUpdatedSuccessMessage();
                    //Assert.assertEquals(msg,"The account was updated successfully!","User edited successfully");
                    break;
                } else {
                    String msg = userOperation.getAlertMessage();
                    System.out.println(msg);
                }
            }
            System.out.println("edit count is " + n);
            Assert.assertEquals(n, 1);
        }
    }
    @Test(priority = 6)
    public void VerifyEditUserTestByMultipleUser() throws ElementClickInterceptedException, IOException, InterruptedException {
        researchPage.clickUsersLink();
        int usersCount = webTableUtility.getRows(usersPage.getTable()).size();
        int editcount = usersPage.getEditButtonSize();
        if (usersCount == editcount) {
            int n = 0;
            for (WebElement ele : usersPage.getEditButton()) {
                try {
                    driver.executeScript("arguments[0].scrollIntoView();", ele);
                    driver.executeScript("arguments[0].click();", ele);
                }catch (StaleElementReferenceException e){
                 fluentWait(ele, Constants.timeout, Constants.pollingTime);
                }
                Assert.assertEquals(driver.getTitle(), "RoofRocket AI", "navigate to Edit User successful");
                userOperation.clearEmail();
                userOperation.enterEmail(faker.internet().emailAddress());
                userOperation.clearFirstname();
                userOperation.enterFirstName(faker.name().firstName());
                userOperation.clearLastname();
                userOperation.enterLastName(faker.name().lastName());
                n++;
                userOperation.clickSubmitButton();
                if (userOperation.isAccountUpdatedSuccessMessageDisplayed()) {
                    String msg = userOperation.getAccountUpdatedSuccessMessage();
                    //Assert.assertEquals(msg,"The account was updated successfully!","User edited successfully");
                } else {
                    String msg = userOperation.getAlertMessage();
                    System.out.println(msg);
                }
            }
            System.out.println("edit count is " + n);
            Assert.assertEquals(n, editcount);
        }
    }
}




