package com.epam.atm.homework5.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {
    private static final String GOOGLE_URL = "https://www.google.com/intl/ru/gmail/about/#";

    @FindBy(xpath = "//a[@class='gmail-nav__nav-link gmail-nav__nav-link__sign-in']")
    WebElement loginButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(GOOGLE_URL);
        return this;
    }

    public LogInPage clickLogin() {
        waitForElementVisible(loginButton);
        loginButton.click();
        return new LogInPage(driver);
    }
}
