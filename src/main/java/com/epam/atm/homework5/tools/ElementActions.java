package com.epam.atm.homework5.tools;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.epam.atm.homework5.tools.JSHighlighter.highlightElement;
import static com.epam.atm.homework5.tools.JSHighlighter.unHighlightElement;
import static com.epam.atm.homework5.tools.Screenshoter.takeScreenshot;

public class ElementActions {

    public static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 10;

    public static void waitForVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForVisibleByLocator(WebDriver driver, By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void clickByLocator(WebDriver driver, By locator) {
        click(driver, driver.findElement(locator));
    }

    public static void click(WebDriver driver, final WebElement element) {
        waitForVisible(driver, element);
        System.out.println("Clicking element '" + element.getText());
        highlightElement(element, driver);
        takeScreenshot();
        unHighlightElement(element, driver);
        element.click();
    }

    public static void type(WebDriver driver, final WebElement element, String text) {
        waitForVisible(driver, element);
        element.clear();
        highlightElement(element, driver);
        System.out.println("Typing text '" + text + "' to input form '" + element.getAttribute("name"));
        element.sendKeys(text);
        takeScreenshot();
        unHighlightElement(element, driver);
    }

    public static void dragAndDrop(WebDriver driver, WebElement element, WebElement target) {
        waitForVisible(driver, element);
        waitForVisible(driver, target);
        takeScreenshot();
        System.out.println("Dragging element '" + element.getText() + "to '" + target.getText());
        (new Actions(driver)).dragAndDrop(element, target).perform();
    }
}
