package com.epam.atm.homework5.pages;

import com.epam.atm.homework5.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BinMailPage extends MailListPage {

    private static final String XPATH_TEXT_NO_CONVERSATIONS_IN_BIN = "//td[text()='No conversations in Bin.']";

    @FindBy(xpath = "//div[text()='Delete forever']")
    WebElement deleteForeverBtn;

    protected BinMailPage(WebDriver driver) {
        super(driver);
    }

    public boolean isMailListEmpty() {
        return !driver.findElements(By.xpath(XPATH_TEXT_NO_CONVERSATIONS_IN_BIN)).isEmpty();
    }

    public BinMailPage clearBin() {
        if (!isMailListEmpty()) {
            ((BinMailPage) checkAllCheckboxes()).clickDeleteForever();
        }
        return this;
    }

    public BinMailPage clickDeleteForever() {
        ElementActions.waitForVisibleAndClick(driver, deleteForeverBtn);
        return this;
    }
}
