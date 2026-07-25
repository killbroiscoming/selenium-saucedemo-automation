package base;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.BooleanSupplier;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    protected void click(By locator) {
        // 1. Wait until the element is fully interactive
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

        try {
            element.click(); // Attempt a standard native Selenium click
        } catch (Exception e) {
            // 2. Fallback: If intercepted or blocked by rendering lag, trigger via JavaScript
            System.out.println("Native click dropped or blocked. Executing JavaScript click fallback for: " + locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            // Create a fast, local 1-second wait just for checking states
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
            return shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException e) {
            return false; // Returns false in 1 second instead of 10!
        }
    }

    protected boolean exists(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    protected void waitForVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForInvisible(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitForTextToBe(By locator, String expectedText) {
        wait.until(ExpectedConditions.textToBe(locator, expectedText));
    }

    protected void waitForUrlContains(String text) {
        wait.until(ExpectedConditions.urlContains(text));
    }

    protected void waitUntil(BooleanSupplier condition) {
        wait.until(driver -> condition.getAsBoolean());
    }

}



