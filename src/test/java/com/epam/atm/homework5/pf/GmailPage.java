package com.epam.atm.homework5.pf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

public abstract class GmailPage extends AbstractPagePF {

    protected static final String XPATH_DRAFTS_LINK = "//a[@href=\"https://mail.google.com/mail/u/0/#drafts\"]";
    private static final String LINK_TEXT_SENT_MAIL = "Sent Mail";
    private static final String XPATH_BIN = "//a[@title='Bin']";
    private static final String XPATH_COMPOSE_BTN = "//div[@class='T-I J-J5-Ji T-I-KE L3']";
    private static final String XPATH_ACCOUNT_ICON = "//span[@class='gb_8a gbii']";
    private static final String XPATH_LOG_OUT = "//a[@class='gb_za gb_Zf gb_6f gb_Ke gb_Eb']";
    public static final By SELECTED_SENT_DRAFT_LOCATOR = By.xpath("//div[@class='aim ain']/div/div/div[2]/span/a");

    @FindBy(xpath = XPATH_ACCOUNT_ICON)
    WebElement accountIcon;

    @FindBy(xpath = XPATH_LOG_OUT)
    WebElement logOutBtn;

    @FindBy(linkText = LINK_TEXT_SENT_MAIL)
    WebElement sentLink;

    @FindBy(xpath = XPATH_DRAFTS_LINK)
    WebElement draftsLink;

    @FindBy(xpath = XPATH_BIN)
    WebElement binLink;

    @FindBy(xpath = XPATH_COMPOSE_BTN)
    WebElement composeBtn;

    protected GmailPage(WebDriver driver) {
        super(driver);
    }

    public PasswordPage signOut() {
        waitForElementVisible(accountIcon);
        accountIcon.click();
        waitForElementVisible(logOutBtn);
        logOutBtn.click();
        return new PasswordPage(driver);
    }

    public SentMailsPage clickSentLink() {
        waitForElementVisible(sentLink);
        sentLink.click();
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(
                ExpectedConditions.textToBe(SELECTED_SENT_DRAFT_LOCATOR, LINK_TEXT_SENT_MAIL));
        return new SentMailsPage(driver);
    }

    public DraftsMailPage clickDrafts() {
        waitForElementVisible(draftsLink);
        draftsLink.click();
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(
                ExpectedConditions.textMatches(SELECTED_SENT_DRAFT_LOCATOR, Pattern.compile("Drafts")));
        return new DraftsMailPage(driver);
    }

    public BinMailPage clickBin() {
        waitForElementVisible(binLink);
        binLink.click();
        return new BinMailPage(driver);
    }

    public ComposePopUpPage clickCompose() {
        waitForElementVisible(composeBtn);
        composeBtn.click();
        return new ComposePopUpPage(driver);
    }
}
