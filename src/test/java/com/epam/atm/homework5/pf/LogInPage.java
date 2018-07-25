package com.epam.atm.homework5.pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends AbstractPagePF {
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
        loginFieldInput.sendKeys(user);
        return this;
    }
    public PasswordPage clickNextBtn() {
        waitForElementVisible(loginNextButton);
        loginNextButton.click();
        return new PasswordPage(driver);
    }
}
