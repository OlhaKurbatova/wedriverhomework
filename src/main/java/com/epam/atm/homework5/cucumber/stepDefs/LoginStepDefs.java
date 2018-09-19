package com.epam.atm.homework5.cucumber.stepDefs;

import com.epam.atm.homework5.selenium.drivermanagers.DriverManager;
import com.epam.atm.homework5.selenium.pages.HomePage;
import com.epam.atm.homework5.selenium.pages.LogInPage;
import com.epam.atm.homework5.selenium.pages.PasswordPage;
import cucumber.api.java.en.Given;
import io.cucumber.datatable.DataTable;

public class LoginStepDefs {

    @Given("^I log in with (?:creds|credentials):$")
    public void iLogInWithCreds(DataTable creds) {
        String email = (String) creds.asMap(String.class, String.class).get("email");
        String password = (String) creds.asMap(String.class, String.class).get("password");

        HomePage homePage = new HomePage();

        LogInPage logInPage = homePage.clickLogin().fillLoginField(email);
        PasswordPage passwordPage = logInPage.clickNextBtn();
        passwordPage.fillPasswordField(password).clickNextBtn();
    }
}
