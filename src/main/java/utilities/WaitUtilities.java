package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class WaitUtilities {
    private RemoteWebDriver driver;

    public WaitUtilities(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement fluentWait(WebElement element, int timeout, int pollingTime) {
        FluentWait<RemoteWebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(Exception.class);

        return wait.until(new Function<RemoteWebDriver, WebElement>() {
            @Override
            public WebElement apply(RemoteWebDriver driver) {
                return element.isDisplayed() ? element : null;
            }
        });
    }
    public void waitForLoaderToDisappear(WebElement loader, int timeout)
    {
        FluentWait<RemoteWebDriver> wait = new FluentWait<>(driver) .withTimeout(Duration.ofSeconds(timeout)) .pollingEvery(Duration.ofMillis(500)) .ignoring(Exception.class);
        try{wait.until(ExpectedConditions.invisibilityOf(loader));
        }catch(Exception e){
            System.out.println("Element did not become stale within the timeout period. Proceeding with a direct click.");
        }
    }
    public void fluentWaitForStaleness(WebElement element, int timeout, int pollingTime)
    {
        FluentWait<RemoteWebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.stalenessOf(element));
    }
    public void fluentWaitForStalenessOfList(List<WebElement> elements, int timeout, int pollingTime)
    {
        FluentWait<RemoteWebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(Exception.class);
        for (WebElement element : elements)
        {
            wait.until(ExpectedConditions.stalenessOf(element));
        }
    }

    public WebElement fluentWaitForElement(WebElement element, int timeout, int pollingTime) {
        FluentWait<RemoteWebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(Exception.class);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> fluentWait(List<WebElement> elements, int timeout, int pollingTime) {
        FluentWait<RemoteWebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(Exception.class);

        return wait.until(new Function<RemoteWebDriver, List<WebElement>>() {
            @Override
            public List<WebElement> apply(RemoteWebDriver driver) {
                for (WebElement element : elements) {
                    if (!element.isDisplayed()) {
                        return null;
                    }
                }
                return elements;
            }
        });


    }



    }
