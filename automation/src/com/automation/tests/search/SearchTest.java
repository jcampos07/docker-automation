package com.automation.tests.search;

import com.automation.pageobjects.search.SearchPage;
import com.automation.tests.config.TestCase;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


/**
 * This class is a test to verify the search functionality, it is a search test since most of the scenarios need to be
 * logged
 *  @author Jason-Campos on 8/06/2018
 */

public class SearchTest extends TestCase {

    @Parameters({"information"})
    @Test(groups = {"docker"}, description = "Search in google")
    public void searchDocker(String information) {
        SearchPage searchPage = new SearchPage(getDriverInstance());
        Assert.assertEquals(searchPage.search(information),"https://www.docker.com/", "Docker link was not " +
                "displayed");
    }

    @Parameters({"information"})
    @Test(groups = {"selenium"}, description = "Search in google")
    public void searchSelenium(String information) {
        SearchPage searchPage = new SearchPage(getDriverInstance());
        Assert.assertEquals(searchPage.search(information), "https://www.seleniumhq.org/", "Selenium link was" +
                " not displayed");
    }
}
