package com.epam.atm.homework5;

import com.epam.atm.homework5.pf.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GmailTestPo {

    private static final String USER_LOGIN = "tyled6@gmail.com";
    private static final String USER_PASSWORD = "qwerty23";

    private static final String TO = "ok130493@gmail.com";
    private static final String SUBJECT = "hello message";
    private static final String MESSAGE = "hi, how?";

    private WebDriver driver;

    @BeforeClass(description = "Start browser")
    public void startBrowser() {
        //alternative way to set environmental variable at runtime
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        // Maximize browser window via options, just an example
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);

        // setting standard timeout
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void sendMailFromDraftsScenarioTest() {
        HomePage homePage = new HomePage(driver);
        homePage.open();

        LogInPage logInPage = homePage.clickLogin();
        logInPage.fillLoginField(USER_LOGIN);
        PasswordPage passwordPage = logInPage.clickNextBtn();
        InboxPage inboxPage = passwordPage.fillPasswordField(USER_PASSWORD).clickNextBtn();

        SentMailsPage sentMailsPage = inboxPage.clickSentLink();
        sentMailsPage.clearSent();

        DraftsMailPage draftsMailPage = sentMailsPage.clickDrafts();
        draftsMailPage.clearDrafts();

        BinMailPage binMailPage = draftsMailPage.clickBin();
        binMailPage.clearBin();

        ComposePopUpPage composePopUpPage = binMailPage.clickCompose();
        composePopUpPage.fillToField(TO);
        composePopUpPage.fillSubjectField(SUBJECT);
        composePopUpPage.fillMessageField(MESSAGE);
        composePopUpPage.clickCloseIcon();

        draftsMailPage = composePopUpPage.clickDrafts();
        composePopUpPage = draftsMailPage.findMailBySubjectAndClick(SUBJECT);
        Assert.assertEquals(composePopUpPage.getPopUpEmailMessageValue(), MESSAGE, "'message' not matches");
        composePopUpPage.clickSend();

        sentMailsPage = draftsMailPage.clickSentLink();
        sentMailsPage.waitUntilLetterVisibleBySubject(SUBJECT);
        Assert.assertTrue(!sentMailsPage.isEmpty(), "Sent folder is empty after sending email");

        sentMailsPage.signOut();
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        driver.quit();
    }
}
