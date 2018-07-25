package com.epam.atm.homework5.pf;

import org.openqa.selenium.WebDriver;

public class InboxPage extends MailListPage {
    protected InboxPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEmpty() {
        return false;
    }
}
