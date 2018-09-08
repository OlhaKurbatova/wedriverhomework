package com.epam.atm.homework5.selenium;

import com.epam.atm.homework5.selenium.businessobjects.EmailObject;
import com.epam.atm.homework5.selenium.businessobjects.GmailUser;
import com.epam.atm.homework5.selenium.drivermanagers.DriverManager;
import com.epam.atm.homework5.selenium.steps.LoginSteps;
import com.epam.atm.homework5.selenium.steps.MailsBoxSteps;
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
        logger.info("Step 1. Open home page and login");
        new LoginSteps(driver).login(userBObject);

        logger.info("Step 2. Clear sent, bin, drafts");
        MailsBoxSteps mailsBoxSteps = new MailsBoxSteps(driver);
        mailsBoxSteps.clearBinDraftSent();

        logger.info("Step 3. Open compose box, fill in email details and close compose box");
        mailsBoxSteps.composeEmailAndSaveToDrafts(emailBObject);

        logger.info("Step 4. Open drafts and find email by subject " + emailBObject.getSubjectValue() + " and click Send");
        String message = mailsBoxSteps.openDraftsAndGetEmailMessageAndSend(emailBObject);
        Assert.assertEquals(message, emailBObject.getMessageValue(), "'message' not matches");

        logger.info("Step 12. Open drafts and wait until mail is removed from drafts list");
        Boolean isMailSent = mailsBoxSteps.isSentEmptyAfterEmailWasSentBySubject(emailBObject);
        logger.info("Step 13. Assert that sent is not empty");
        Assert.assertFalse(!isMailSent, "Sent folder is not empty after sending email");

        logger.info("Step 14. Sign out");
        mailsBoxSteps.signOut();
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        DriverManager.getInstance().closeDriver();
    }
}
