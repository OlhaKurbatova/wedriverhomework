package com.epam.atm.homework5.cucumber.stepDefs;


import com.epam.atm.homework5.selenium.drivermanagers.DriverManager;
import cucumber.api.java.After;

public class ScenarioHooks {
    @After
    public void afterScenario() {
        DriverManager.getInstance().closeDriver();
    }
}
