package com.automation.pageobjects;

import com.automation.bot.ActionBot;
import org.openqa.selenium.WebDriver;

/**
 * This page object is the in charge of initializing the bot in order to be used for the rest of page objects. All the page
 * objects inherit from this class
 * @author Jason Campos on 9/07/2017
 */

public class PageObjectBase {

    /**
     * Attributes
     */
    protected ActionBot actionBot;
    protected WebDriver driver;
    protected static String browserName;

    /**
     * Constructor
     * @param webDriver webDriver instance
     */
    public PageObjectBase(WebDriver webDriver, String browser) {
        this.browserName = browser;
        actionBot = new ActionBot(webDriver,browser);
        this.driver = webDriver;
    }

    /**
     * Constructor
     * @param webDriver webDriver instance
     */
    public PageObjectBase(WebDriver webDriver) {
        actionBot = new ActionBot(webDriver);
        this.driver = webDriver;
    }

    /**
     * This method gets the actual instance of webDriver
     * @return webdriver instance the getDriver method inherited from TestCaseBase class
     */
    public WebDriver getDriverInstance() {
        return this.driver;
    }

    public String getBrowserName() {
        return this.browserName;
    }


}
