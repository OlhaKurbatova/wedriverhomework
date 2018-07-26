package com.epam.atm.homework5.pf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public abstract class MailListPage extends GmailPage {
    private static final String XPATH_CHECKBOX = "//div[@role='checkbox']";

    protected MailListPage(WebDriver driver) {
        super(driver);
    }

    public abstract boolean isEmpty();

    public MailListPage checkAllCheckboxes() {
        List<WebElement> checkboxes = driver.findElements(By.xpath(XPATH_CHECKBOX));
        for (WebElement checkbox : checkboxes) {
            checkbox.click();
        }
        return this;
    }

}
