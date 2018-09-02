package com.epam.atm.homework5;

import com.epam.atm.homework5.businessobjects.EmailObject;
import com.epam.atm.homework5.businessobjects.GmailUser;
import com.epam.atm.homework5.drivermanagers.DriverManager;
import com.epam.atm.homework5.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GmailSendMailFromDraftsTest {

    private static final Logger logger = LogManager.getLogger();

    private WebDriver driver;
    private GmailUser userBObject;
    private EmailObject emailBObject;

    @BeforeClass(description = "Start browser")
    public void startBrowser() {
        driver = DriverManager.getInstance().getDriver();
        userBObject = new GmailUser();
        emailBObject = new EmailObject();
    }

    @Test
    public void sendMailFromDraftsScenarioTest() {
        logger.info("Step 1. Open home page");
        HomePage homePage = new HomePage(driver);

        logger.info("Step 2. Click login button and fill in login: " + userBObject.getLogin());
        LogInPage logInPage = homePage.clickLogin().fillLoginField(userBObject.getLogin());
        logger.info("Step 3. Click next");
        PasswordPage passwordPage = logInPage.clickNextBtn();
        logger.info("Step 4. Fill in password: " + userBObject.getPassword() + " and click next");
        InboxPage inboxPage = passwordPage.fillPasswordField(userBObject.getPassword()).clickNextBtn();

        logger.info("Step 5. Delete sent emails if not empty");
        SentMailsPage sentMailsPage = inboxPage.clickSentLink();
        sentMailsPage.clearSent();

        logger.info("Step 6. Delete drafts emails if not empty");
        DraftsMailPage draftsMailPage = sentMailsPage.clickDrafts();
        draftsMailPage.clearDrafts();

        logger.info("Step 7. Clear bin if not empty");
        BinMailPage binMailPage = draftsMailPage.clickBin();
        binMailPage.clearBin();

        logger.info("Step 8. Open compose box, fill in email details and close compose box");
        ComposePopUpPage composePopUpPage = binMailPage.clickCompose();
        composePopUpPage.
                fillToField(emailBObject.getToValue()).
                fillSubjectField(emailBObject.getSubjectValue()).
                fillMessageField(emailBObject.getMessageValue()).
                clickCloseIcon();

        logger.info("Step 9. Open drafts and find email by subject " + emailBObject.getSubjectValue());
        draftsMailPage = composePopUpPage.clickDrafts();
        composePopUpPage = draftsMailPage.findMailBySubjectAndClick(emailBObject.getSubjectValue());
        logger.info("Step 10. Assert that message email matches with constant");
        Assert.assertEquals(composePopUpPage.getPopUpEmailMessageValue(), emailBObject.getMessageValue(), "'message' not matches");
        logger.info("Step 11. Click send");
        composePopUpPage.clickSend();

        logger.info("Step 12. Open drafts and wait until mail is removed from drafts list");
        sentMailsPage = draftsMailPage.clickSentLink();
        sentMailsPage.waitUntilLetterVisibleBySubject(emailBObject.getSubjectValue());
        logger.info("Step 13. Assert that drafts is empty");
        Assert.assertTrue(sentMailsPage.isMailListEmpty(), "Sent folder is empty after sending email");

        logger.info("Step 14. Sign out");
        sentMailsPage.signOut();
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        DriverManager.getInstance().closeDriver();
    }
}
