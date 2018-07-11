package com.epam.atm.homework5;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GmailTest {

    private static final String PATH_TO_WEBDRIVER = "d:\\Projects\\chromedriver.exe";
    private static final String GOOGLE_URL = "https://www.google.com/intl/ru/gmail/about/#";
    private static final String USER_LOGIN = "tyled6@gmail.com";
    private static final String USER_PASSWORD = "qwerty23";
    private static final String TO = "ok130493@gmail.com";
    private static final String SUBJECT = "hello message";
    private static final String MESSAGE = "hi, how?";
    private static final String CSS_LOGIN_NEXT_BTN = "#identifierNext";
    private static final String XPATH_MESSAGE_FIELD = "//div[@class='Am Al editable LW-avf']";
    private static final String XPATH_CLOSE_ICON = "//img[@class='Ha']";
    private static final String XPATH_PASSWORD = "//input[@type=\"password\"]";
    private static final String XPATH_LOG_IN = "//*[@id=\"identifierId\"]";
    private static final String XPATH_SIGNIN = "//a[@class='gmail-nav__nav-link gmail-nav__nav-link__sign-in']";
    private static final String XPATH_SENT_EMAIL_SUBJECT = "//span[text()='" + SUBJECT + "']";
    private static final String XPATH_POPUP_TO_EMAIL = "//span[@email='" + TO + "']";
    private static final String XPATH_PASSWORD_NEXT = "//*[@id=\"passwordNext\"]";
    private static final String XPATH_COMPOSE_BTN = "//div[@class='T-I J-J5-Ji T-I-KE L3']";
    private static final String XPATH_TO_FIELD = "//textarea[@name='to']";
    private static final String XPATH_SUBJECT_FIELD = "//input[@name='subjectbox']";
    private static final String XPATH_DRAFTS_LINK = "//a[@href=\"https://mail.google.com/mail/u/0/#drafts\"]";
    private static final String XPATH_DRAFTED_SUBJECT = "//span[text()='hello message']";
    private static final String XPATH_MESSAGE_POPUP_FIELD = "//div[@class='Am Al editable LW-avf']";
    private static final String XPATH_SEND_BTN = "//div[text()='Send']";
    private static final String XPATH_EMPTY_DRAFT_MESSAGE_TEXT = "//td[@class=\'TC\']";
    private static final String XPATH_SENT_LINK = "//a[@title=\"Sent Mail\"]";
    private static final String PATH_ACCOUNT_ICON = "//span[@class='gb_8a gbii']";
    private static final String XPATH_LOG_OUT = "//a[@class='gb_za gb_Zf gb_6f gb_Ke gb_Eb']";
    private WebDriver driver;
    private WebElement login;

    @BeforeClass(description = "Start browser")
    public void startBrowser() {
        //alternative way to set environmental variable at runtime
        System.setProperty("webdriver.chrome.driver", PATH_TO_WEBDRIVER);
        ChromeOptions options = new ChromeOptions();
        // Maximize browser window via options, just an example
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);

        // Open web page for moving through demo steps
        driver.get(GOOGLE_URL);

        // setting standard timeout
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void sendMailFromDraftsScenarioTest() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_SIGNIN)));
        login = driver.findElement(By.xpath(XPATH_SIGNIN));

        login.click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_LOG_IN)));
        driver.findElement(By.xpath(XPATH_LOG_IN)).sendKeys(USER_LOGIN);
        driver.findElement(By.cssSelector(CSS_LOGIN_NEXT_BTN)).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_PASSWORD)));
        driver.findElement(By.xpath(XPATH_PASSWORD)).sendKeys(USER_PASSWORD);
        driver.findElement(By.xpath(XPATH_PASSWORD_NEXT)).click();

        //write a letter
        driver.findElement(By.xpath(XPATH_COMPOSE_BTN)).click();
        //fill field to
        driver.findElement(By.xpath(XPATH_TO_FIELD)).sendKeys(TO);
        driver.findElement(By.xpath(XPATH_SUBJECT_FIELD)).sendKeys(SUBJECT);

        driver.findElement(By.xpath(XPATH_MESSAGE_FIELD)).sendKeys(MESSAGE);
        driver.findElement(By.xpath(XPATH_CLOSE_ICON)).click();
        driver.findElement(By.xpath(XPATH_DRAFTS_LINK)).click();
        driver.findElement(By.xpath(XPATH_DRAFTED_SUBJECT)).click();
        //проверка того что имейл получателя правильный
        driver.findElement(By.xpath(XPATH_POPUP_TO_EMAIL));
        //проверка тела письма
        WebElement message = driver.findElement(By.xpath(XPATH_MESSAGE_POPUP_FIELD));
        Assert.assertEquals(message.getText(), MESSAGE, "'message' not matches");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath(XPATH_SEND_BTN)).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_EMPTY_DRAFT_MESSAGE_TEXT)));
        driver.findElement(By.xpath(XPATH_SENT_LINK)).click();
        WebElement messageSent = driver.findElement(By.xpath(XPATH_SENT_EMAIL_SUBJECT));
        Assert.assertEquals(messageSent.getText(), SUBJECT, "SUBJECT not matches");
        driver.findElement(By.xpath(PATH_ACCOUNT_ICON)).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_LOG_OUT)));
        driver.findElement(By.xpath(XPATH_LOG_OUT)).click();
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        driver.quit();
        System.out.println("Browser was successfully quited.");
    }
}

