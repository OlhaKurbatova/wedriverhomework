package com.epam.atm.homework5.selenium.tools;

import com.epam.atm.homework5.selenium.drivermanagers.WebDriverDecorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSHighlighter {
    private JSHighlighter() {
    }

    public static void highlightElement(WebElement element, WebDriver driver) {
        if (driver instanceof WebDriverDecorator) {
            driver = ((WebDriverDecorator) driver).getDriver();
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid green'", element);
    }

    public static void unHighlightElement(WebElement element, WebDriver driver) {
        if (driver instanceof WebDriverDecorator) {
            driver = ((WebDriverDecorator) driver).getDriver();
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", element);
    }

}
