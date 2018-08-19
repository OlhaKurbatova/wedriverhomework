package com.epam.atm.homework5;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {

    private WebDriver driver;
    private final static int TIMEOUT = 30;
    private static final int CHROME = 0;
    private static final int FIREFOX = 1;
    private final static int BROWSER = CHROME;
    private static final String URL = "https://www.google.com/intl/ru/gmail/about/#";

    private static DriverManager INSTANCE;

    private DriverManager() {
    }

    public static DriverManager getInstance(){
        if(INSTANCE == null){
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
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
            }
            driver.get(URL);
            driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        }
        return driver;
    }

    public void closeDriver() {
        driver.quit();
        driver = null;
    }
}
