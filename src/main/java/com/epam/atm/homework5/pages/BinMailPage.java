package com.epam.atm.homework5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BinMailPage extends MailListPage {

    private static final String LINK_TEXT_NO_CONVERSATIONS_IN_BIN = "No conversations in Bin.";

    @FindBy(xpath = "//div[text()='Delete forever']")
    WebElement deleteForeverBtn;

    protected BinMailPage(WebDriver driver) {
        super(driver);
    }

    public boolean isMailListEmpty() {
        try {
            driver.findElement(By.linkText(LINK_TEXT_NO_CONVERSATIONS_IN_BIN));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public BinMailPage clearBin() {
        if (!isMailListEmpty()) {
            ((BinMailPage) checkAllCheckboxes()).clickDeleteForever();
        }
        return this;
    }

    public BinMailPage clickDeleteForever() {
        waitForElementVisible(deleteForeverBtn);
        deleteForeverBtn.click();
        return this;
    }
}
