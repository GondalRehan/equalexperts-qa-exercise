package com.ee.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static com.ee.booking.DriverFactory.getDriver;

public class BookingVerifyController {
    WebDriver driver = getDriver();

    public boolean isRecordPresent() {
        return isElementExists(By.xpath("//div/p[text()='" + BookingActController.customerData.getFirstname() + "']"));
    }

    public boolean isElementExists(By by) {
        boolean isExists = true;
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            isExists = false;
        }
        return isExists;
    }

    //TODO: Update with ID after sorting out record update with created customer id
    public boolean isRecordExist() {
        return driver.findElement(By.xpath("//div[@id='bookings']/div[@id='" + BookingActController.customerData.getId() + "']")).isDisplayed();
    }

}
