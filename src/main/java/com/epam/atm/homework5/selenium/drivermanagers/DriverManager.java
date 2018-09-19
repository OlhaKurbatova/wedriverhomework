package com.epam.atm.homework5.selenium.drivermanagers;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {

    private WebDriver driver;
    private static final int TIMEOUT = 30;
    private static final int CHROME = 0;
    private static final int FIREFOX = 1;
    private static final int BROWSER = CHROME;
    private static final String URL_HOME = "https://www.google.com/intl/ru/gmail/about/#";

    private static DriverManager instance;

    private DriverManager() {
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            switch (BROWSER) {
                case FIREFOX:
                    driver = new FirefoxDriver();
                    break;
                case CHROME:
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                    ChromeOptions options = new ChromeOptions();
                    // Maximize browser window via options, just an example
                    options.addArguments("start-maximized");
                    driver = new ChromeDriver(options);
                    break;
                default:
                    driver = new FirefoxDriver();
                    break;
            }
            driver.get(URL_HOME);
            driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        }
        return new WebDriverDecorator(driver);
    }

    public void closeDriver() {
        driver.quit();
        driver = null;
    }
}
