package com.epam.atm.homework5.selenium;

import com.epam.atm.homework5.selenium.businessobjects.EmailObject;
import com.epam.atm.homework5.selenium.businessobjects.GmailUser;
import com.epam.atm.homework5.selenium.drivermanagers.DriverManager;
import com.epam.atm.homework5.selenium.steps.LoginSteps;
import com.epam.atm.homework5.selenium.steps.MailsBoxSteps;
import com.epam.atm.homework5.selenium.tools.MyLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GmailDrargAndDropTest {

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
    public void dragAndDropTest() {
        MyLogger.info("Step 1. Open home page and login");
        new LoginSteps(driver).login(userBObject);
        MailsBoxSteps mailsBoxSteps = new MailsBoxSteps(driver);
        MyLogger.info("Step 2. Clear sent and send new letter");
        mailsBoxSteps.clearAndCreateSentMail(emailBObject);
        int sizeBeforeDragAndDropDelete = mailsBoxSteps.getEmailCountOnSentPage();
        MyLogger.info("Step 3. Drag and drop sent email to bin and click refresh");
        mailsBoxSteps.dragNdropFirstVisibleSentMailToBinAndClickRefresh();
        int sizeAfterDragAndDropDelete = mailsBoxSteps.getEmailCountOnSentPage();

        MyLogger.info("Step 4. Assert that count of emails before and after drag and drop deletion is different");
        Assert.assertNotEquals(sizeBeforeDragAndDropDelete, sizeAfterDragAndDropDelete, "email didn't deleted");

        MyLogger.info("Step 5. Sign out");
        mailsBoxSteps.signOut();
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        DriverManager.getInstance().closeDriver();
    }
}
