package pages;

import constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.WaitUtilities;

import java.util.Arrays;
import java.util.List;

public class DashBoardPage {
    RemoteWebDriver driver;
    public WaitUtilities waitUtilities ;
    @FindBy(xpath = "//div[contains(@class,'overview')]//div[contains(@class,'sm_cl_1')]")
    private List<WebElement> dashboardItems;

    public DashBoardPage(RemoteWebDriver driver) {
        this.driver = driver;
        waitUtilities= new WaitUtilities(driver);
        PageFactory.initElements(driver, this);
    }
    public void verifyDashboardElements() {
        // List of expected values
        List<String> expectedValues = Arrays.asList(
                "Google leads",
                "Yelp leads",
                "Foursquare leads",
                "Saved leads",
                "Reports generated",
                "Email sent",
                "Reports Seen By Porspect",
                "Potential Value of Collected Leads"
        );
        // Find elements on the dashboard
        List<WebElement> elements =waitUtilities.fluentWait(dashboardItems, Constants.timeout, Constants.pollingTime);
        // Assert the number of elements matches the expected values count
        Assert.assertEquals(elements.size(), expectedValues.size(), "Element count mismatch"
        );
        // Iterate through elements and compare with expected values
        for (int i = 0; i < elements.size(); i++) {
            WebElement element = elements.get(i);
            // Assert the element is displayed
            Assert.assertTrue(
                    element.isDisplayed(),
                    "Element is not displayed: " + element.getText()
            );
            // Fetch and trim the actual value
            String actualValue = element.getText().trim();
            String expectedValue = expectedValues.get(i);
            // Assert the actual value matches the expected value
            Assert.assertEquals(
                    actualValue,
                    expectedValue,
                    "Value mismatch at index " + i
            );
            // Print the verification result
            System.out.println("Verified: " + actualValue);
        }
    }

}
