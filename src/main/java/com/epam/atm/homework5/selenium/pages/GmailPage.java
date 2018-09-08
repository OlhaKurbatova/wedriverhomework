package com.epam.atm.homework5.selenium.pages;

import com.epam.atm.homework5.selenium.tools.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

import static com.epam.atm.homework5.selenium.tools.ElementActions.WAIT_FOR_ELEMENT_TIMEOUT_SECONDS;

public abstract class GmailPage extends AbstractPage {

    protected static final String XPATH_DRAFTS_LINK = "//a[@href=\"https://mail.google.com/mail/u/0/#drafts\"]";
    public static final By SELECTED_SENT_DRAFT_LOCATOR = By.xpath("//div[@class='aim ain']/div/div/div[2]/span/a");
    private static final String LINK_TEXT_SENT_MAIL = "Sent Mail";
    public static final By XPATH_DRAFTS_SHIELD = By.xpath("//div[@class='Kj-JD-Jh']");

    @FindBy(xpath = "//div[@class='gb_Pc gb_ib gb_Rg gb_R']")
    WebElement accountIcon;

    @FindBy(xpath = "//a[@class='gb_za gb_4f gb_ag gb_Pe gb_Fb']")
    WebElement logOutBtn;

    @FindBy(linkText = "Sent Mail")
    WebElement sentLink;

    @FindBy(xpath = "//a[@href=\"https://mail.google.com/mail/u/0/#drafts\"]")
    WebElement draftsLink;

    @FindBy(xpath = "//a[@title='Bin']")
    WebElement binLink;

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji T-I-KE L3']")
    WebElement composeBtn;

    protected GmailPage(WebDriver driver) {
        super(driver);
    }

    public PasswordPage signOut() {
        ElementActions.click(driver, accountIcon);
        ElementActions.click(driver, logOutBtn);
        return new PasswordPage(driver);
    }

    public SentMailsPage clickSentLink() {
        ElementActions.click(driver, sentLink);
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(
                ExpectedConditions.textToBe(SELECTED_SENT_DRAFT_LOCATOR, LINK_TEXT_SENT_MAIL));
        return new SentMailsPage(driver);
    }

    public DraftsMailPage clickDrafts() {
        List<WebElement> webElements = driver.findElements(XPATH_DRAFTS_SHIELD);
        if (!webElements.isEmpty()) {
            new WebDriverWait(driver, ElementActions.WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(
                    ExpectedConditions.invisibilityOfElementLocated(XPATH_DRAFTS_SHIELD));
        }

        ElementActions.click(driver, draftsLink);
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(
                ExpectedConditions.textMatches(SELECTED_SENT_DRAFT_LOCATOR, Pattern.compile("Drafts")));
        return new DraftsMailPage(driver);
    }

    public BinMailPage clickBin() {
        ElementActions.click(driver, binLink);
        return new BinMailPage(driver);
    }

    public ComposePopUpPage clickCompose() {
        ElementActions.click(driver, composeBtn);
        return new ComposePopUpPage(driver);
    }
}
