package com.epam.atm.homework5.selenium.pages;

import com.epam.atm.homework5.selenium.tools.ElementActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

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


    public ComposePopUpPage() {
        super();
    }

    public ComposePopUpPage fillToField(String to) {
        ElementActions.type(driver, toField, to);
        return this;
    }

    public ComposePopUpPage fillSubjectField(String subject) {
        ElementActions.type(driver, subjectField, subject);
        return this;
    }

    public ComposePopUpPage fillMessageField(String message) {
        ElementActions.type(driver, messageField, message);
        return this;
    }

    public void clickSendFromKeys(){
        new Actions(driver).sendKeys(Keys.CONTROL, Keys.ENTER).build().perform();
    }

    public void clickCloseIcon() {
        ElementActions.click(driver, closeIcon);
    }

    public String getPopUpEmailMessageValue() {
        ElementActions.waitForVisible(driver, popupMessageField);
        return popupMessageField.getText();
    }
}
