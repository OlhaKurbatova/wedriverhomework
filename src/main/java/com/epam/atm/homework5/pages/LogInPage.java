package com.epam.atm.homework5.pages;

import com.epam.atm.homework5.ElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends AbstractPage {
    private static final String ID_LOG_IN_FIELD = "identifierId";
    private static final String CSS_LOGIN_NEXT_BTN = "#identifierNext";
    @FindBy(id = ID_LOG_IN_FIELD)
    WebElement loginFieldInput;
    @FindBy(css = CSS_LOGIN_NEXT_BTN)
    WebElement loginNextButton;


    protected LogInPage(WebDriver driver) {
        super(driver);
    }

    public LogInPage fillLoginField(String user) {
        ElementActions.type(driver, loginFieldInput, user);
        return this;
    }
    public PasswordPage clickNextBtn() {
        ElementActions.click(driver, loginNextButton);
        return new PasswordPage(driver);
    }
}
