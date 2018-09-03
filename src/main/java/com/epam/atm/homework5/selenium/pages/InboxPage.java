package com.epam.atm.homework5.selenium.pages;

import org.openqa.selenium.WebDriver;

public class InboxPage extends MailListPage {
    protected InboxPage(WebDriver driver) {
        super(driver);
    }

    public boolean isMailListEmpty() {
        throw new UnsupportedOperationException();
    }
}
