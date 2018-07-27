package com.epam.atm.homework5.pf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

public class ComposePopUpPage extends GmailPage {

    @FindBy(xpath = "//div[@class='Am Al editable LW-avf']")
    WebElement popupMessageField;

    @FindBy(xpath = "//textarea[@name='to']")
    WebElement toField;

    @FindBy(xpath = "//input[@name='subjectbox']")
    WebElement subjectField;

    @FindBy(xpath = "//div[@class='Am Al editable LW-avf']")
    WebElement messageField;

    @FindBy(xpath = "//div[text()='Send']")
    WebElement sendBtn;

    @FindBy(xpath = "//img[@class='Ha']")
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
