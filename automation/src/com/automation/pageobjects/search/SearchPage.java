package com.automation.pageobjects.search;

import com.automation.pageobjects.PageObjectBase;
import org.openqa.selenium.WebDriver;

/**
 * Google search page to look for information
 * @author Jason campos on 8/06/2018
 */

public class SearchPage extends PageObjectBase {

    /**
     * Constructor
     * @param driver web driver
     */
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public String search(String criteria) {
        actionBot.waitForElementVisible(WebElements.GOOGLE_ICON);
        typeCriteria(criteria);
        clickOnGoogleSearchButton();
        return getLink();
    }

    /**
     * Types on criteria textfield
     * @param criteria String to search
     */
    private void typeCriteria(String criteria) {
        actionBot.type(WebElements.SEARCH_TEXTFIELD, criteria);
    }

    /**
     * Click on google search button
     */
    private void clickOnGoogleSearchButton() {
        actionBot.click(WebElements.GOOGLE_ICON);
        actionBot.wait(2000);
        actionBot.click(WebElements.GOOGLE_SEARCH_BUTTON);
    }

    /**
     * Verifies if the docker page is displayed
     * @return boolean if the docker page is displayed.
     */
    private String getLink() {
       return actionBot.find(WebElements.LINK_TEXT).getAttribute("href");
    }
}
