package com.epam.atm.homework5.cucumber.stepDefs;

import com.epam.atm.homework5.selenium.pages.*;
import com.epam.atm.homework5.selenium.tools.ElementActions;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.testng.Assert;

public class MailBoxStepDefs {
    @When("^I clear sent$")
    public void clearSent() {
        SentMailsPage sentMailsPage = new InboxPage().clickSentLink();
        sentMailsPage.clearSent();
    }

    @When("^I clear drafts")
    public void clearDrafts() {
        DraftsMailPage draftsMailPage = new InboxPage().clickDrafts();
        draftsMailPage.clearDrafts();
    }

    @When("^I clear bin$")
    public void clearBin() {
        BinMailPage binMailPage = new InboxPage().clickBin();
        binMailPage.clearBin();
    }

    @And("^I compose email with fields: and save it to drafts$")
    public void iComposeEmailWithFieldsAndSaveItToDrafts(DataTable emailFieldsTable) {
        String to = (String) emailFieldsTable.asMap(String.class, String.class).get("to");
        String subject = (String) emailFieldsTable.asMap(String.class, String.class).get("subject");
        String message = (String) emailFieldsTable.asMap(String.class, String.class).get("message");

        ComposePopUpPage composePopUpPage = GmailPage.getGmailPage().clickCompose();
        composePopUpPage.
                fillToField(to).
                fillSubjectField(subject).
                fillMessageField(message).
                clickCloseIcon();
    }

    @And("^I compose email with fields: and send$")
    public void iComposeEmailWithFieldsAndSend(DataTable emailFieldsTable) {
        String to = (String) emailFieldsTable.asMap(String.class, String.class).get("to");
        String subject = (String) emailFieldsTable.asMap(String.class, String.class).get("subject");
        String message = (String) emailFieldsTable.asMap(String.class, String.class).get("message");

        ComposePopUpPage composePopUpPage = GmailPage.getGmailPage().clickCompose();
        composePopUpPage.
                fillToField(to).
                fillSubjectField(subject).
                fillMessageField(message).
                clickSendFromKeys();
    }

    @Then("^I open drafts$")
    public void iOpenDrafts() {
        GmailPage.getGmailPage().clickDrafts();
    }

    @And("^I open email by ([\\w ]+)$")
    public void iOpenEmailBySubjectSubject(String subject) {
        GmailPage.getGmailPage().clickDrafts().findMailBySubjectAndClick(subject);
    }

    @Then("^I check message is ([\\w ]+)$")
    public void iCheckMessageIsMessage(String message) {
        ComposePopUpPage composePopUpPage = new ComposePopUpPage();
        String messageFromMail = composePopUpPage.getPopUpEmailMessageValue();

        Assert.assertEquals(messageFromMail, message, "'message' not matches");
    }
}
