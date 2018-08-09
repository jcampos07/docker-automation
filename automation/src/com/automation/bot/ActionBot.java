package com.automation.bot;

import com.automation.tests.config.TestCaseBase;
import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.*;

/**
 *  This class contains all the methods that interact to the web driver, all the page objects call these methods
 *  to perform actions like send keys, findElement, wait for an element, etc.
 *  All the methods are encapsulated in order to be easier the maintenance.
 *  @author Jason Campos on 9/12/2017
 */

public class ActionBot extends TestCaseBase {

    /**
     * Attributes
     */
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private final int TIME_TO_WAIT_IN_SECONDS = 30;
    private final int DEFAULT_TIME_TO_WAIT_IN_SECONDS = 30;

    /**
     * constructor
     * @param driver instance of the web driver
     */
    public ActionBot(WebDriver driver){
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(this.driver, TIME_TO_WAIT_IN_SECONDS);
    }

    /**
     *
     * @return the web driver wait instance
     */
    public WebDriverWait getWaitDriver() {
        return this.webDriverWait;
    }

    /**
     * this method types in any field with the dataproviders received in the method
     * @param element web element to type the info
     * @param data value to enter in the field
     */
    public void type(By element, String data) {
        find(element).clear();
        wait(1000);
        find(element).sendKeys(data);
        wait(1000);
    }

    /**
     * this method find an element in the html
     * @param element web element to type the info
     */
    public WebElement find(By element) {
        if(waitForElementVisible(element)) {
            return driver.findElement(element);
        }
        throw new RuntimeException(String.format("The web element with the following locator was not found: %s", element));
    }

    /**
     * This method clicks on any element in the html
     * @param element webElement to click on
     */
    public void click(By element) {
        try {
            waitForPageToLoad();
            //scrollDown(element);
            WebElement elementToClick = waitUntilElement(ExpectedConditions.visibilityOfElementLocated(element), element);
            elementToClick.click();
            wait(1000);
        }catch (org.openqa.selenium.WebDriverException exception) {
            throw new RuntimeException(String.format("The element cannot be clickable: %s", element), exception);
        }
    }

    /**
     * This method wait for the element in order to perform the following action
     * @param element element to wait
     *  @return boolean
     */

    public boolean waitForElementPresent(final By element,int secondsToWait) {
        boolean isElementPresent = true;
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(this.driver)
                    .withTimeout(secondsToWait, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class);
            wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver d) {
                    d.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                    WebElement we = d.findElement(element);
                    return we;
                }
            });
        } catch (TimeoutException te) {
            isElementPresent = false;
        }
        return isElementPresent;
    }

    /**
     * This method wait that the element is present in order to perform the following action
     * @param element element to wait
     *  @return boolean
     */
    public boolean waitForElementVisible(final By element) {
        boolean isElementPresent = true;
        try {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIME_TO_WAIT_IN_SECONDS);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException te) {
            System.out.println("I entered in visible timeout");
            isElementPresent = false;
        }
        return isElementPresent;
    }

    /**
     * This method waits for X amount of milliseconds for continuing the next instruction
     * @param timeToWaitInMilliseconds overall of milliseconds to wait.
     */
    public void wait(int timeToWaitInMilliseconds) {
        long startTime = System.currentTimeMillis();
        while((System.currentTimeMillis()-startTime)<timeToWaitInMilliseconds)
        {
        }
    }

    /**
     * wait until the element is displayed.
     * @param expectedCondition expected condition to apply in the webdriver
     * @return boolean if the element was found
     */
    public WebElement waitUntilElement(ExpectedCondition<WebElement> expectedCondition, By element) {
        try {
            return getWaitDriver().until(expectedCondition);
        } catch (TimeoutException exception) {
            throw new RuntimeException(String.format("The element is not visible in the expected time before clicking " +
                    "it: %s", element));
        }
    }

    /**
     * This method wait until the page is loaded
     */
    private void waitForPageToLoad() {
        driver.manage().timeouts().pageLoadTimeout(TIME_TO_WAIT_IN_SECONDS, TimeUnit.SECONDS);
    }
}
