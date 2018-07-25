package com.epam.atm.homework5.pf;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SentMailsPage extends MailListPage {

    private static final String CLASS_NAME_EMPTY = "TC";
    private static final String XPATH_DELETE_SENT = "//div[@title='Delete']/div";
    private static final String XPATH_CONFIRM_DELETE_DIALOG_BTN_OK = "//button[@name='ok']";
    private static final String XPATH_SENT_SUBJECT = "//span[text()='%s']";

    @FindBy(xpath = XPATH_CONFIRM_DELETE_DIALOG_BTN_OK)
    WebElement confirmDeleteBtn;

    protected SentMailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEmpty() {
        try {
            driver.findElement(By.className(CLASS_NAME_EMPTY));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public SentMailsPage waitUntilLetterVisibleBySubject(String subject){
        By mailSubjectLocator = By.xpath(String.format(XPATH_SENT_SUBJECT, subject));
        waitForElementByLocatorVisible(mailSubjectLocator);
        driver.findElement(mailSubjectLocator).click();
        return this;
    }

    public SentMailsPage clickDelete() {
        for (WebElement delete : driver.findElements(By.xpath(XPATH_DELETE_SENT))) {
            if (delete.isDisplayed()) {
                delete.click();
            }
        }
        return this;
    }

    public SentMailsPage clickConfirmDelete() {
        waitForElementVisible(confirmDeleteBtn);
        confirmDeleteBtn.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className(CLASS_NAME_EMPTY)));
        return this;
    }

    public SentMailsPage clearSent(){
        if (!isEmpty()) {
            ((SentMailsPage) checkAllCheckboxes()).clickDelete().clickConfirmDelete();
        }
        return this;
    }

}
