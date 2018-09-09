package com.epam.atm.homework5.selenium.pages;

import com.epam.atm.homework5.selenium.tools.ElementActions;
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

    protected PasswordPage() {
        super();
    }


    public PasswordPage fillPasswordField(String password) {
        ElementActions.type(driver, passwordFieldInput, password);
        return this;
    }

    public InboxPage clickNextBtn() {
        ElementActions.click(driver, passwordNextButton);
        return new InboxPage();
    }
}
