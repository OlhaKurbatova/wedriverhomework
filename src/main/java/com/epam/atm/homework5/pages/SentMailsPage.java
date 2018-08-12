package com.epam.atm.homework5.pages;

import com.epam.atm.homework5.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SentMailsPage extends MailListPage {

    private static final String CLASS_NAME_EMPTY = "TC";
    private static final String XPATH_DELETE_SENT = "//div[@title='Delete']/div";

    private static final String XPATH_SENT_SUBJECT = "//span[text()='%s']";

    @FindBy(xpath = "//button[@name='ok']")
    WebElement confirmDeleteBtn;

    protected SentMailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isMailListEmpty() {
        return !driver.findElements(By.className(CLASS_NAME_EMPTY)).isEmpty();
    }

    public SentMailsPage waitUntilLetterVisibleBySubject(String subject) {
        ElementActions.waitForVisibleByLocator(driver, By.xpath(String.format(XPATH_SENT_SUBJECT, subject)));
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
        ElementActions.waitForVisibleAndClick(driver, confirmDeleteBtn);
        new WebDriverWait(driver, ElementActions.WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className(CLASS_NAME_EMPTY)));
        return this;
    }

    public SentMailsPage clearSent() {
        if (!isMailListEmpty()) {
            ((SentMailsPage) checkAllCheckboxes()).clickDelete().clickConfirmDelete();
        }
        return this;
    }

}
