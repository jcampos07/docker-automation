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

    /**
     * Constructor
     * @param webDriver webDriver instance
     */
    public PageObjectBase(WebDriver webDriver) {
        actionBot = new ActionBot(webDriver);
        this.driver = webDriver;
    }
}
