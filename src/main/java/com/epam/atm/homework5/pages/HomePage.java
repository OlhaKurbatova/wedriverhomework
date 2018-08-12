package com.epam.atm.homework5.pages;

import com.epam.atm.homework5.ElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//a[@class='gmail-nav__nav-link gmail-nav__nav-link__sign-in']")
    WebElement loginButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LogInPage clickLogin() {
        ElementActions.waitForVisibleAndClick(driver, loginButton);
        return new LogInPage(driver);
    }
}
