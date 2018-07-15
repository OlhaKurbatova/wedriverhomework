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

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class GmailTest {

    private static final String PATH_TO_WEBDRIVER = "d:\\Projects\\chromedriver.exe";
    private static final String GOOGLE_URL = "https://www.google.com/intl/ru/gmail/about/#";
    private static final String USER_LOGIN = "tyled6@gmail.com";
    private static final String USER_PASSWORD = "qwerty23";
    private static final String TO = "ok130493@gmail.com";
    private static final String SUBJECT = "hello message";
    private static final String MESSAGE = "hi, how?";

    private static final String DRAFTS_COUNT_REGEX = "\\(\\d\\)";

    private static final String CSS_LOGIN_NEXT_BTN = "#identifierNext";

    private static final String ID_LOG_IN = "identifierId";
    private static final String ID_PASSWORD_NEXT = "passwordNext";

    private static final String LINK_TEXT_SENT_MAIL = "Sent Mail";
    private static final String LINK_TEXT_NO_CONVERSATIONS_IN_BIN = "No conversations in Bin.";

    private static final String CLASS_NAME_EMPTY = "TC";

    private static final String XPATH_MESSAGE_FIELD = "//div[@class='Am Al editable LW-avf']";
    private static final String XPATH_CLOSE_ICON = "//img[@class='Ha']";
    private static final String XPATH_PASSWORD = "//input[@type=\"password\"]";
    private static final String XPATH_SIGNIN = "//a[@class='gmail-nav__nav-link gmail-nav__nav-link__sign-in']";
    private static final String XPATH_SENT_EMAIL_SUBJECT = "//span[text()='" + SUBJECT + "']";
    private static final String XPATH_POPUP_TO_EMAIL = "//span[@email='" + TO + "']";
    private static final String XPATH_COMPOSE_BTN = "//div[@class='T-I J-J5-Ji T-I-KE L3']";
    private static final String XPATH_TO_FIELD = "//textarea[@name='to']";
    private static final String XPATH_SUBJECT_FIELD = "//input[@name='subjectbox']";
    private static final String XPATH_DRAFTS_LINK = "//a[@href=\"https://mail.google.com/mail/u/0/#drafts\"]";
    private static final String XPATH_DRAFTED_SUBJECT = "//span[text()='hello message']";
    private static final String XPATH_MESSAGE_POPUP_FIELD = "//div[@class='Am Al editable LW-avf']";
    private static final String XPATH_SEND_BTN = "//div[text()='Send']";
    private static final String XPATH_SENT_LINK = "//a[@title=\"Sent Mail\"]";
    private static final String XPATH_ACCOUNT_ICON = "//span[@class='gb_8a gbii']";
    private static final String XPATH_LOG_OUT = "//a[@class='gb_za gb_Zf gb_6f gb_Ke gb_Eb']";
    private static final String XPATH_CHECKBOX = "//div[@role='checkbox']";
    private static final String XPATH_DELETE_SENT = "//div[@title='Delete']/div";
    private static final String XPATH_CONFIRM_DELETE_DIALOG_BTN_OK = "//button[@name='ok']";
    private static final String XPATH_DISCARD_DRAFTS = "//div[text()='Discard drafts']";
    private static final String XPATH_BIN = "//a[@title='Bin']";
    private static final String XPATH_DELETE_FOREVER = "//div[text()='Delete forever']";
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
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id(ID_LOG_IN)));
        driver.findElement(By.id(ID_LOG_IN)).sendKeys(USER_LOGIN);
        driver.findElement(By.cssSelector(CSS_LOGIN_NEXT_BTN)).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_PASSWORD)));
        driver.findElement(By.xpath(XPATH_PASSWORD)).sendKeys(USER_PASSWORD);
        driver.findElement(By.id(ID_PASSWORD_NEXT)).click();
        //clear draft/send/bin before writing a letter
        //clean Sent folder
        //check if folder is not empty
        driver.findElement(By.linkText(LINK_TEXT_SENT_MAIL)).click();

        try {
            driver.findElement(By.className(CLASS_NAME_EMPTY));
        } catch (NoSuchElementException e) {
            List<WebElement> checkboxes = driver.findElements(By.xpath(XPATH_CHECKBOX));
            for (WebElement checkbox : checkboxes) {
                checkbox.click();
            }

            for (WebElement delete : driver.findElements(By.xpath(XPATH_DELETE_SENT))) {
                if (delete.isDisplayed()) {
                    delete.click();
                }
            }

            driver.findElement(By.xpath(XPATH_CONFIRM_DELETE_DIALOG_BTN_OK)).click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className(CLASS_NAME_EMPTY)));
        }

        //clean Drafts folder
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_DRAFTS_LINK)));
        WebElement drafts = driver.findElement(By.xpath(XPATH_DRAFTS_LINK));
        if (Pattern.compile(DRAFTS_COUNT_REGEX).matcher(drafts.getText()).find()) {
            drafts.click();

            List<WebElement> checkboxes = driver.findElements(By.xpath(XPATH_CHECKBOX));
            for (WebElement checkbox : checkboxes) {
                checkbox.click();
            }
            //By.linkText and By.partialLinkText throws are failing to find desired element or some reason
            driver.findElement(By.xpath(XPATH_DISCARD_DRAFTS)).click();
            new WebDriverWait(driver, 30).until(ExpectedConditions.not(ExpectedConditions.textMatches(By.xpath(XPATH_DRAFTS_LINK), Pattern.compile(DRAFTS_COUNT_REGEX))));
        }

        //Clear bin folder
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_BIN)));
        driver.findElement(By.xpath(XPATH_BIN)).click();

        try {
            driver.findElement(By.linkText(LINK_TEXT_NO_CONVERSATIONS_IN_BIN));
        } catch (NoSuchElementException e) {
            List<WebElement> checkboxes = driver.findElements(By.xpath(XPATH_CHECKBOX));
            if (checkboxes.size() > 0) {
                for (WebElement checkbox : checkboxes) {
                    checkbox.click();
                }
                //By.linkText and By.partialLinkText throws are failing to find desired element or some reason
                driver.findElement(By.xpath(XPATH_DELETE_FOREVER)).click();
            }
        }

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
        new WebDriverWait(driver, 30).until(ExpectedConditions.not(ExpectedConditions.textMatches(By.xpath(XPATH_DRAFTS_LINK), Pattern.compile("\\(\\d\\)"))));
        driver.findElement(By.xpath(XPATH_SENT_LINK)).click();
        WebElement messageSent = driver.findElement(By.xpath(XPATH_SENT_EMAIL_SUBJECT));
        Assert.assertEquals(messageSent.getText(), SUBJECT, "SUBJECT not matches");
        driver.findElement(By.xpath(XPATH_ACCOUNT_ICON)).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_LOG_OUT)));
        driver.findElement(By.xpath(XPATH_LOG_OUT)).click();
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        driver.quit();
    }
}

