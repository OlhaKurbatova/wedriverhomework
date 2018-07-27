package com.epam.atm.homework5.pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends AbstractPagePF {

    @FindBy(id = "identifierId")
    WebElement loginFieldInput;
    @FindBy(css = "#identifierNext")
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
