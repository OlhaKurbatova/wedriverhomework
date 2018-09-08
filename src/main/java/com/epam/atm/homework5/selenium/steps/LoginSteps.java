package com.epam.atm.homework5.selenium.steps;

import com.epam.atm.homework5.selenium.businessobjects.GmailUser;
import com.epam.atm.homework5.selenium.pages.HomePage;
import com.epam.atm.homework5.selenium.pages.InboxPage;
import com.epam.atm.homework5.selenium.pages.LogInPage;
import com.epam.atm.homework5.selenium.pages.PasswordPage;
import org.openqa.selenium.WebDriver;

public class LoginSteps extends AbstractSteps {

    public LoginSteps(WebDriver driver) {
        super(driver);
    }

    public void login(GmailUser user) {
        HomePage homePage = new HomePage(driver);

        LogInPage logInPage = homePage.clickLogin().fillLoginField(user.getLogin());
        PasswordPage passwordPage = logInPage.clickNextBtn();
        passwordPage.fillPasswordField(user.getPassword()).clickNextBtn();
    }
}
