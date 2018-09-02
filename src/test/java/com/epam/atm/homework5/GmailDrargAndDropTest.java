package com.epam.atm.homework5;

import com.epam.atm.homework5.businessobjects.EmailObject;
import com.epam.atm.homework5.businessobjects.GmailUser;
import com.epam.atm.homework5.drivermanagers.DriverManager;
import com.epam.atm.homework5.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class GmailDrargAndDropTest {

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
    public void dragAndDropTest() {
        logger.info("Step 1. Open home page");
        HomePage homePage = new HomePage(driver);

        logger.info("Step 2. Click login button and fill in login: " + userBObject.getLogin());
        LogInPage logInPage = homePage.clickLogin().fillLoginField(userBObject.getPassword());
        logger.info("Step 3. Click next");
        PasswordPage passwordPage = logInPage.clickNextBtn();
        logger.info("Step 4. Fill in password: " + userBObject.getPassword() + " and click next");
        InboxPage inboxPage = passwordPage.fillPasswordField(userBObject.getPassword()).clickNextBtn();

        logger.info("Step 5. Delete sent emails if not empty");
        SentMailsPage sentMailsPage = inboxPage.clickSentLink();

        logger.info("Step 6. Check whether sent list is empty or not");
        if(sentMailsPage.isMailListEmpty()){
            logger.info("Step 6a. If list is empty, send email and refresh");
            ComposePopUpPage composePopUpPage = sentMailsPage.clickCompose();
            composePopUpPage.
                    fillToField(emailBObject.getToValue()).
                    fillSubjectField(emailBObject.getSubjectValue()).
                    fillMessageField(emailBObject.getMessageValue()).
                    clickSendFromKeys();
            while (sentMailsPage.isMailListEmpty()){
                sentMailsPage.clickRefresh();
            }
        }

        logger.info("Step 7. Drag and drop feirst email to bin link and conform deletion");
        List<WebElement> emails = sentMailsPage.getEmailsList();
        int sizeBeforeDragAndDropDelete = emails.size();
        sentMailsPage.dragAndDropFirstEmailToBinAndConfirm(emails);
        sentMailsPage.clickRefresh();
        List<WebElement> emailsAfterDelete = sentMailsPage.getEmailsList();
        int sizeAfterDragAndDropDelete = emailsAfterDelete.size();

        logger.info("Step 8. Assert that count of emails before and after drag and drop deletion is not equals");
        Assert.assertNotEquals(sizeBeforeDragAndDropDelete, sizeAfterDragAndDropDelete, "EmailObject didn't delete");

        logger.info("Step 14. Sign out");
        sentMailsPage.signOut();
    }

    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        DriverManager.getInstance().closeDriver();
    }
}
