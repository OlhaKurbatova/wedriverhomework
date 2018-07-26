package com.epam.atm.homework5.pf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

public class ComposePopUpPage extends GmailPage {
    private static final String XPATH_TO_FIELD = "//textarea[@name='to']";
    private static final String XPATH_SUBJECT_FIELD = "//input[@name='subjectbox']";
    private static final String XPATH_MESSAGE_FIELD = "//div[@class='Am Al editable LW-avf']";
    private static final String XPATH_SEND_BTN = "//div[text()='Send']";
    private static final String XPATH_CLOSE_ICON = "//img[@class='Ha']";
    private static final String XPATH_MESSAGE_POPUP_FIELD = "//div[@class='Am Al editable LW-avf']";

    @FindBy(xpath = XPATH_MESSAGE_POPUP_FIELD)
    WebElement popupMessageField;

    @FindBy(xpath = XPATH_TO_FIELD)
    WebElement toField;

    @FindBy(xpath = XPATH_SUBJECT_FIELD)
    WebElement subjectField;

    @FindBy(xpath = XPATH_MESSAGE_FIELD)
    WebElement messageField;

    @FindBy(xpath = XPATH_SEND_BTN)
    WebElement sendBtn;

    @FindBy(xpath = XPATH_CLOSE_ICON)
    WebElement closeIcon;


    protected ComposePopUpPage(WebDriver driver) {
        super(driver);
    }

    public ComposePopUpPage fillToField(String to) {
        waitForElementVisible(toField);
        toField.sendKeys(to);
        return this;
    }

    public ComposePopUpPage fillSubjectField(String subject) {
        waitForElementVisible(subjectField);
        subjectField.sendKeys(subject);
        return this;
    }

    public ComposePopUpPage fillMessageField(String message) {
        waitForElementVisible(messageField);
        messageField.sendKeys(message);
        return this;
    }

    public void clickSend() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendBtn.click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.not(ExpectedConditions.textMatches(By.xpath(XPATH_DRAFTS_LINK), Pattern.compile("\\(\\d\\)"))));
    }

    public void clickCloseIcon() {
        waitForElementVisible(closeIcon);
        closeIcon.click();
    }

    public String getPopUpEmailMessageValue() {
        waitForElementVisible(popupMessageField);
        return popupMessageField.getText();
    }
}
