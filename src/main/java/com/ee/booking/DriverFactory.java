package com.ee.booking;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DriverFactory {
    private static WebDriver driver;
    private static WebDriverWait wait;

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();

            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }
        return driver;
    }

    public static WebDriverWait getWebDriverWait() {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), 5);
        }
        return wait;
    }
}
