package com.automation.tests.config;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.MalformedURLException;


public class TestCase extends TestCaseBase {

    /**
     * This method overrides the setUp method from TestCaseBase class
     *
     * @param browserName
     * @param url
     */
    @Parameters({"browserName", "url", "executionMode", "hubUrl"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String browserName, String url, String executionMode, String hubUrl) throws MalformedURLException {
        super.setUp(browserName, executionMode, hubUrl);
        driver.get(url);
    }

    /**
     * This method overrides the quitBrowser method inherited from TestCaseBase class
     *
     * @param testResult testng variable
     * @param method     testng variable
     */
    @AfterMethod(alwaysRun = true)
    public void quitBrowser(ITestResult testResult, Method method) {
        super.quitBrowser();
    }

    /**
     * This method gets the actual instance of webDriver
     *
     * @return web driver instance the getDriver method inherited from TestCaseBase class
     */
    public WebDriver getDriverInstance() {
        return super.getDriver();
    }

    /**
     * This method gets the actual browser name
     *
     * @return String with the browser name
     */
    public String getBrowserName() {
        return super.getBrowserName();
    }

}
