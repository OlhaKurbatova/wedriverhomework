package com.epam.atm.homework5.selenide;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class MavenRepositorySearchTest {

    @AfterClass
    public static void logout() {
        closeWebDriver();
    }

    @Test
    public void testMavenSearch() {
        open("https://mvnrepository.com/");
        $(By.id("query")).should(visible).setValue("core");
        $(By.xpath("//input[@value='Search']")).should(visible).click();
        $$(By.className("im")).shouldHave(CollectionCondition.sizeGreaterThanOrEqual(3));
    }
}
