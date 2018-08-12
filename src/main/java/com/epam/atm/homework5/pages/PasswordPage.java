package com.epam.atm.homework5.pages;

import com.epam.atm.homework5.ElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PasswordPage extends AbstractPage {
    private static final String XPATH_PASSWORD = "//input[@type=\"password\"]";
    private static final String ID_PASSWORD_NEXT = "passwordNext";
    @FindBy(xpath = XPATH_PASSWORD)
    WebElement passwordFieldInput;
    @FindBy(id = ID_PASSWORD_NEXT)
    WebElement passwordNextButton;

    protected PasswordPage(WebDriver driver) {
        super(driver);
    }


    public PasswordPage fillPasswordField(String password) {
        ElementActions.waitForVisibleAndType(driver, passwordFieldInput, password);
        return this;
    }

    public InboxPage clickNextBtn() {
        ElementActions.waitForVisibleAndClick(driver, passwordNextButton);
        return new InboxPage(driver);
    }
}
