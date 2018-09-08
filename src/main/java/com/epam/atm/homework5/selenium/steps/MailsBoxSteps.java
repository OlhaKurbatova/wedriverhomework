package com.epam.atm.homework5.selenium.steps;

import com.epam.atm.homework5.selenium.businessobjects.EmailObject;
import com.epam.atm.homework5.selenium.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MailsBoxSteps extends AbstractSteps {

    private GmailPage page;

    public MailsBoxSteps(WebDriver webDriver) {
        super(webDriver);
        page = new InboxPage(webDriver);
    }

    public void clearBinDraftSent() {
        page.
                clickSentLink().clearSent().
                clickDrafts().clearDrafts().
                clickBin().clearBin();
    }

    public void clearSent() {
        SentMailsPage sentMailsPage = page.clickSentLink();
        sentMailsPage.clearSent();
    }

    public void clearDrafts() {
        DraftsMailPage draftsMailPage = page.clickDrafts();
        draftsMailPage.clearDrafts();
    }

    public void clearBin() {
        BinMailPage binMailPage = page.clickBin();
        binMailPage.clearBin();
    }

    public void composeEmailAndSaveToDrafts(EmailObject email) {
        ComposePopUpPage composePopUpPage = page.clickCompose();
        composePopUpPage.
                fillToField(email.getToValue()).
                fillSubjectField(email.getSubjectValue()).
                fillMessageField(email.getMessageValue()).
                clickCloseIcon();
    }

    public void composeEmailAndSend(EmailObject email) {
        ComposePopUpPage composePopUpPage = page.clickCompose();
        composePopUpPage.
                fillToField(email.getToValue()).
                fillSubjectField(email.getSubjectValue()).
                fillMessageField(email.getMessageValue()).
                clickSendFromKeys();
    }

    public String openDraftsAndGetEmailMessageAndSend(EmailObject email) {
        DraftsMailPage draftsMailPage = page.clickDrafts();
        ComposePopUpPage composePopUpPage = draftsMailPage.findMailBySubjectAndClick(email.getSubjectValue());
        String message = composePopUpPage.getPopUpEmailMessageValue();
        composePopUpPage.clickSendFromKeys();
        return message;
    }

    public boolean isSentEmptyAfterEmailWasSentBySubject(EmailObject email) {
        SentMailsPage sentMailsPage = page.clickSentLink();
        sentMailsPage.waitUntilLetterVisibleBySubject(email.getSubjectValue());
        return sentMailsPage.isMailListEmpty();
    }

    public void clearAndCreateSentMail(EmailObject email) {
        SentMailsPage sentMailsPage = page.clickSentLink();
        if (sentMailsPage.isMailListEmpty()) {
            ComposePopUpPage composePopUpPage = sentMailsPage.clickCompose();
            composePopUpPage.
                    fillToField(email.getToValue()).
                    fillSubjectField(email.getSubjectValue()).
                    fillMessageField(email.getMessageValue()).
                    clickSendFromKeys();
            while (sentMailsPage.isMailListEmpty()) {
                sentMailsPage.clickRefresh();
            }
        }
    }

    public int getEmailCountOnSentPage() {
        SentMailsPage sentMailsPage = page.clickSentLink();
        List<WebElement> emails = sentMailsPage.getEmailsList();
        return emails.size();
    }

    public void dragNdropFirstVisibleSentMailToBinAndClickRefresh(){
        SentMailsPage sentMailsPage = page.clickSentLink();
        List<WebElement> emails = sentMailsPage.getEmailsList();
        sentMailsPage.dragAndDropFirstEmailToBinAndConfirm(emails);
        sentMailsPage.clickRefresh();
    }

    public void signOut() {
        page.signOut();
    }
}
