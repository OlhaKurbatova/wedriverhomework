package com.epam.atm.homework5.selenium;

import com.epam.atm.homework5.selenium.businessobjects.EmailObject;
import com.epam.atm.homework5.selenium.businessobjects.GmailUser;
import com.epam.atm.homework5.selenium.drivermanagers.DriverManager;
import com.epam.atm.homework5.selenium.steps.LoginSteps;
import com.epam.atm.homework5.selenium.steps.MailsBoxSteps;
import com.epam.atm.homework5.selenium.tools.MyLogger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GmailSendMailFromDraftsTest {

    private WebDriver driver;
    private GmailUser userBObject;
    private EmailObject emailBObject;

    @BeforeClass(description = "Start browser")
    public void startBrowser() {
        driver = DriverManager.getInstance().getDriver();
        userBObject = GmailUser.getDefaultUser();
        emailBObject = EmailObject.getDefaultEmail();
    }

    @Test
    public void sendMailFromDraftsScenarioTest() {
        MyLogger.info("Step 1. Open home page and login");
        new LoginSteps(driver).login(userBObject);

        MyLogger.info("Step 2. Clear sent, bin, drafts");
        MailsBoxSteps mailsBoxSteps = new MailsBoxSteps(driver);
        mailsBoxSteps.clearBinDraftSent();

        MyLogger.info("Step 3. Open compose box, fill in email details and close compose box");
        mailsBoxSteps.composeEmailAndSaveToDrafts(emailBObject);

        MyLogger.info("Step 4. Open drafts and find email by subject " + emailBObject.getSubjectValue() + " and click Send");
        String message = mailsBoxSteps.openDraftsAndGetEmailMessageAndSend(emailBObject);
        Assert.assertEquals(message, emailBObject.getMessageValue(), "'message' not matches");

        MyLogger.info("Step 12. Open drafts and wait until mail is removed from drafts list");
        boolean isMailSent = mailsBoxSteps.isSentEmptyAfterEmailWasSentBySubject(emailBObject);
        MyLogger.info("Step 13. Assert that sent is not empty");
        Assert.assertFalse(!isMailSent, "Sent folder is not empty after sending email");

        MyLogger.info("Step 14. Sign out");
        mailsBoxSteps.signOut();
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        DriverManager.getInstance().closeDriver();
    }
}
