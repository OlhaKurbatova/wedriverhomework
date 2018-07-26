package com.epam.atm.homework5.pf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

public class DraftsMailPage extends MailListPage {
    private static final String XPATH_DISCARD_DRAFTS = "//div[text()='Discard drafts']";
    private static final String XPATH_DRAFTS_LINK = "//a[@href=\"https://mail.google.com/mail/u/0/#drafts\"]";
    private static final String DRAFTS_COUNT_REGEX = "\\(\\d\\)";
    public static final Pattern DRAFTS_TEXT_PATTERN = Pattern.compile(DRAFTS_COUNT_REGEX);
    private static final String XPATH_DRAFTED_SUBJECT = "//span[text()='%s']";

    public static final By DRAFTS_LINK_LOCATOR = By.xpath(XPATH_DRAFTS_LINK);

    @FindBy(xpath = XPATH_DISCARD_DRAFTS)
    WebElement discardDraftsBtn;

    protected DraftsMailPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEmpty() {
        waitForElementVisible(draftsLink);
        return !DRAFTS_TEXT_PATTERN.matcher(draftsLink.getText()).find();
    }

    public DraftsMailPage clickDiscardDrafts() {
        waitForElementVisible(discardDraftsBtn);
        discardDraftsBtn.click();
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(
                ExpectedConditions.not(ExpectedConditions.textMatches(DRAFTS_LINK_LOCATOR, DRAFTS_TEXT_PATTERN)));
        return this;
    }

    public DraftsMailPage clearDrafts() {
        if (!isEmpty()) {
            ((DraftsMailPage) checkAllCheckboxes()).clickDiscardDrafts();
        }
        return this;
    }

    public ComposePopUpPage findMailBySubjectAndClick(String subject) {
        By mailSubjectLocator = By.xpath(String.format(XPATH_DRAFTED_SUBJECT, subject));
        waitForElementByLocatorVisible(mailSubjectLocator);
        driver.findElement(mailSubjectLocator).click();
        return new ComposePopUpPage(driver);
    }
}
