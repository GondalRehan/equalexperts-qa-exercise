package com.ee.booking.test;

import com.ee.booking.utils.ReadConfig;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.ee.booking.DriverFactory.getDriver;


public class BaseTest {

    ReadConfig readconfig = new ReadConfig();
    public String baseURL = readconfig.getApplicationURL();
    public String browser = readconfig.getBrowser();
    WebDriver driver;

    @BeforeClass
    public void setup() {
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", readconfig.getBrowser());
            driver = getDriver();
        }
    }

    @BeforeMethod
    public void goToHome() {
        driver.get(baseURL);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
