package com.epam.atm.homework5.selenium.tools;

import com.epam.atm.homework5.selenium.drivermanagers.DriverManager;
import com.epam.atm.homework5.selenium.drivermanagers.WebDriverDecorator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;

public class Screenshoter {
    private Screenshoter() {
    }

    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";

    public static void takeScreenshot() {
        WebDriver driver = DriverManager.getInstance().getDriver();
        if (driver instanceof WebDriverDecorator) {
            driver = ((WebDriverDecorator) driver).getDriver();
        }
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            File copy = new File(screenshotName + ".png");
            copyFile(screenshot, copy);
            DriverManager.getInstance().getLogger().info("Saved screenshot: " + screenshotName);
        } catch (IOException e) {
            DriverManager.getInstance().getLogger().info("Failed to make screenshot");
        }
    }

    private static void copyFile(File source, File dest) throws IOException {
        try (InputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(dest);) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }
}
