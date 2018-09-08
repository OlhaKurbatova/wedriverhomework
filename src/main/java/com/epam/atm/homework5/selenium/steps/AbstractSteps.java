package com.epam.atm.homework5.selenium.steps;

import org.openqa.selenium.WebDriver;

public abstract class AbstractSteps {

    protected WebDriver driver;

    public AbstractSteps(WebDriver webDriver) {
        this.driver = webDriver;
    }
}
