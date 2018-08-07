package com.automation.pageobjects.search;

import org.openqa.selenium.By;

/**
 * Web elements the search page will interact with
 * @author Jason Campos on 8/06/2018
 */

public class WebElements {

    public static final By GOOGLE_ICON = By.id("hplogo");
    public static final By SEARCH_TEXTFIELD = By.name("q");
    public static final By GOOGLE_SEARCH_BUTTON = By.name("btnK");
    public static final By LINK_TEXT = By.cssSelector("div.bkWMgd:first-child h3 a");

}
