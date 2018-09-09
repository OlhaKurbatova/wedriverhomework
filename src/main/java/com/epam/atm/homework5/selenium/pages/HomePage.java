package com.epam.atm.homework5.selenium.pages;

import com.epam.atm.homework5.selenium.tools.ElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//a[@class='gmail-nav__nav-link gmail-nav__nav-link__sign-in']")
    WebElement loginButton;

    public HomePage(WebDriver driver) {
        super();
    }

    public LogInPage clickLogin() {
        ElementActions.click(driver, loginButton);
        return new LogInPage();
    }
}
