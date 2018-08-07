package com.automation.tests.config;

import com.automation.utils.Consts;
import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.awt.*;
import java.util.concurrent.TimeUnit;
/**
 * This Class set up the browser in order to execute the tests.
 * @author Jason Campos on 9/07/17.
 */

public class TestCaseBase {
    protected WebDriver driver;
    private final int TIMEOUT = 10;
    protected String browserName;

    /**
     * This method returns the driver to be used.
     * @return driver to be used in the proofs
     */
    public WebDriver getDriver() {
        return this.driver;
    }

    /**
     * This method returns the browser we are using.
     * @return String browser name
     */
    public String getBrowserName() {
        return this.browserName;
    }

    /**
     * this method configure the browser for the tests
     * @param browserName the browser in which the test will run
     */
    public void setUp(String browserName) {
        setLocalEnvironment(browserName);
    }

    /**
     * Set the local browser to run the test in one instance
     * @param browserName String with the browser to use
     */
    public void setLocalEnvironment(String browserName) {
        if (browserName.equalsIgnoreCase(Consts.CHROME_BROWSER) || browserName.equalsIgnoreCase(Consts.CHROME_HEADLESS_BROWSER)) {
            //set path to chromedriver You may need to download it from http://code.google.com/p/selenium/wiki/ChromeDriver
            ChromeDriverManager.chromedriver().setup();
            //create chrome instance
            ChromeOptions options = new ChromeOptions();
            if (browserName.equalsIgnoreCase(Consts.CHROME_HEADLESS_BROWSER)) {
                options.addArguments("headless");
                this.browserName = Consts.CHROME_HEADLESS_BROWSER;
                options.addArguments("no-sandbox");
                options.addArguments("window-size=1920,1080");
            } else {
                this.browserName = Consts.CHROME_BROWSER;
            }
            options.setCapability(ChromeOptions.CAPABILITY, options);
            driver = new ChromeDriver(options);
            if (!browserName.equalsIgnoreCase(Consts.CHROME_HEADLESS_BROWSER)) {
                maximizeChromeScreen(driver);
            }
        } else if (browserName.contains(Consts.FIREFOX_BROWSER)){
            FirefoxDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if (browserName.equalsIgnoreCase(Consts.FIREFOX_HEADLESS_BROWSER)) {
                options.addArguments("-headless");
            }
            driver = new FirefoxDriver(options);
            this.browserName = Consts.FIREFOX_BROWSER;
        } else if (browserName.equalsIgnoreCase(Consts.IE_BROWSER_NAME)){
            //InternetExplorerDriverManager.getInstance().setup(Architecture.x32);
            //create IE instance
            driver = new InternetExplorerDriver();
            this.browserName = Consts.IE_BROWSER_NAME;
        } 
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
    }

    /**
     *this method is the in charge of quit the browser after the test runs
     */
    protected void quitBrowser() {
        driver.quit();
    }

    /**
     * this method maximaze the chrome driver
     * @param driver driver to use
     */
    private void maximizeChromeScreen(WebDriver driver) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int Width = (int) toolkit.getScreenSize().getWidth();
        int Height = (int)toolkit.getScreenSize().getHeight();
        driver.manage().window().setSize(new Dimension(Width,Height));
    }
}
