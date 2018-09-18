package com.epam.atm.homework5.selenium.pages;

import com.epam.atm.homework5.selenium.tools.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

import static com.epam.atm.homework5.selenium.tools.ElementActions.WAIT_FOR_ELEMENT_TIMEOUT_SECONDS;

public class DraftsMailPage extends MailListPage {
    public static final Pattern DRAFTS_TEXT_PATTERN = Pattern.compile("\\(\\d\\)");
    private static final String XPATH_DRAFTED_SUBJECT = "//span[text()='%s']";

    public static final By DRAFTS_LINK_LOCATOR = By.xpath("//a[@href=\"https://mail.google.com/mail/u/0/#drafts\"]");

    @FindBy(xpath = "//div[text()='Discard drafts']")
    WebElement discardDraftsBtn;

    public boolean isMailListEmpty() {
        ElementActions.waitForVisible(driver, draftsLink);
        return !DRAFTS_TEXT_PATTERN.matcher(draftsLink.getText()).find();
    }

    public DraftsMailPage clickDiscardDrafts() {
        ElementActions.click(driver, discardDraftsBtn);
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(
                ExpectedConditions.not(ExpectedConditions.textMatches(DRAFTS_LINK_LOCATOR, DRAFTS_TEXT_PATTERN)));
        return this;
    }

    public DraftsMailPage clearDrafts() {
        if (!isMailListEmpty()) {
            ((DraftsMailPage) checkAllCheckboxes()).clickDiscardDrafts();
        }
        return this;
    }

    public ComposePopUpPage findMailBySubjectAndClick(String subject) {
        ElementActions.clickByLocator(driver, By.xpath(String.format(XPATH_DRAFTED_SUBJECT, subject)));
        return new ComposePopUpPage();
    }
}
