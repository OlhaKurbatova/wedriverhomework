package com.epam.atm.homework5.selenium.pages;

import com.epam.atm.homework5.selenium.tools.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SentMailsPage extends MailListPage {

    private static final String CLASS_NAME_EMPTY = "TC";
    private static final String XPATH_DELETE_SENT = "//div[@title='Delete']/div";

    private static final String XPATH_SENT_SUBJECT = "//span[text()='%s']";
    public static final String XPATH_MAIL = "//tr[@class='zA yO']";

    @FindBy(xpath = "//button[@name='ok']")
    WebElement confirmDeleteBtn;

    @FindBy(xpath = "//div[@class='G-Ni J-J5-Ji']/div[@aria-label='Refresh']/div[@class='asa']")
    WebElement refreshBtn;

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

    public List<WebElement> getEmailsList() {
        return driver.findElements(By.xpath(XPATH_MAIL));
    }

    public SentMailsPage dragAndDropFirstEmailToBinAndConfirm(List<WebElement> mails) {
        for (WebElement email : mails) {
            if (email.isDisplayed()) {
                ElementActions.dragAndDrop(driver, email, binLink);
                clickConfirmDelete();
                return this;
            }
        }
        return this;
    }

    public SentMailsPage clickRefresh() {
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='G-Ni J-J5-Ji']/div[@title='Refresh']/div[@class='asa']"));
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                ElementActions.click(driver, element);
                return this;
            }
        }
        return this;
    }

    public SentMailsPage clickConfirmDelete() {
        ElementActions.click(driver, confirmDeleteBtn);
        return this;
    }

    public SentMailsPage waitForEmptyMessage() {
        new WebDriverWait(driver, ElementActions.WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className(CLASS_NAME_EMPTY)));
        return this;
    }

    public SentMailsPage clearSent() {
        if (!isMailListEmpty()) {
            ((SentMailsPage) checkAllCheckboxes()).clickDelete().clickConfirmDelete().waitForEmptyMessage();
        }
        return this;
    }

}
