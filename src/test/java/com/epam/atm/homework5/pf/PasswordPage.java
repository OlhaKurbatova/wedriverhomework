package com.epam.atm.homework5.pf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PasswordPage extends AbstractPagePF {

    @FindBy(xpath = "//input[@type=\"password\"]")
    WebElement passwordFieldInput;
    @FindBy(id = "passwordNext")

    WebElement passwordNextButton;

    protected PasswordPage(WebDriver driver) {
        super(driver);
    }


    public PasswordPage fillPasswordField(String password) {
        waitForElementVisible(passwordFieldInput);
        passwordFieldInput.sendKeys(password);
        return this;
    }

    public InboxPage clickNextBtn() {
        waitForElementVisible(passwordNextButton);
        passwordNextButton.click();
        return new InboxPage(driver);
    }
}
