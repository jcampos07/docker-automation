package com.automation.tests.config;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class TestCase extends TestCaseBase {

    /**
     * This method overrides the setUp method from TestCaseBase class
     *
     * @param browserName
     * @param url
     */
    @Parameters({"browserName", "url"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String browserName, String url) {
        super.setUp(browserName);
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
        if (!testResult.isSuccess()) {
            try {
                File file = new File("screenshots");
                if (!file.exists()) {
                    System.out.println("File created " + file);
                    file.mkdir();
                }
                File scrFile = ((TakesScreenshot) getDriverInstance()).getScreenshotAs(OutputType.FILE);
                StringBuilder testFailed = new StringBuilder();
                testFailed.append(this.getClass().getSimpleName()).append(".").append(testResult.getName()).
                        append(".failedTest").append(".png");
                FileUtils.copyFile(scrFile, new File("screenshots/" + testFailed.toString()));
            }catch (IOException ex) {
                throw new RuntimeException("Error taking the screenshot");
            }
        }
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
