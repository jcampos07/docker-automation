package com.automation.tests.search;

import com.automation.pageobjects.search.SearchPage;
import com.automation.tests.config.TestCase;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


/**
 * This class is a test to verify a search on google page
 *  @author Jason-Campos on 8/06/2018
 */

public class SearchTest extends TestCase {

    @Parameters({"secondCriteria"})
    @Test(groups = {"docker-automation"}, description = "Search in google for docker")
    public void searchDocker(String information) {
        SearchPage searchPage = new SearchPage(getDriverInstance());
        Assert.assertEquals(searchPage.search(information),"https://www.docker.com/", "Docker link was not " +
                "displayed in the list of results");
    }

    @Parameters({"firstCriteria"})
    @Test(groups = {"docker-automation"}, description = "Search in google for selenium")
    public void searchSelenium(String information) {
        SearchPage searchPage = new SearchPage(getDriverInstance());
        Assert.assertEquals(searchPage.search(information), "https://www.seleniumhq.org/", "Selenium link was" +
                " not displayed in the list of results");
    }

    @Parameters({"testNG"})
    @Test(groups = {"docker-automation"}, description = "Search in google for testNG")
    public void searchTestNg(String information) {
        SearchPage searchPage = new SearchPage(getDriverInstance());
        Assert.assertEquals(searchPage.search(information), "https://test.org/", "TestNG link was" +
                " not displayed in the list of results");
    }
}
