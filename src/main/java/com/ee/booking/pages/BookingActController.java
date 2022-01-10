package com.ee.booking.pages;

import com.ee.booking.context.Booking;
import com.google.common.collect.Iterables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.ee.booking.DriverFactory.getDriver;

public class BookingActController {

    WebDriver driver = getDriver();
    public static Booking customerData = new Booking("", "Tony", "Harris", "15", "true", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MMMM-dd")), LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MMMM-dd")));

    public void completeReservationForm(String firstName, String lastName, String totalPrice, String depositPaid, String checkIn, String checkOut) {
        driver.findElement(By.id("firstname")).sendKeys(firstName);
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.id("totalprice")).sendKeys(totalPrice);
        {
            WebElement dropdown = driver.findElement(By.id("depositpaid"));
            dropdown.findElement(By.xpath("//option[. = '" + depositPaid + "']")).click();
        }
        String strCheckIn[] = checkIn.split("-");
        selectDate(strCheckIn[0], strCheckIn[1], strCheckIn[2], "checkin");
        String strCheckout[] = checkOut.split("-");
        selectDate(strCheckout[0], strCheckout[1], strCheckout[2], "checkout");
        driver.findElement(By.cssSelector(".row:nth-child(1) > .col-md-1:nth-child(7) > input")).click();
    }

    public void selectDate(String year, String month, String day, String searchElementId) {

        driver.findElement(By.id(searchElementId)).click();
        while (true) {
            String datePickerYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
            String datePickerMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
            if (datePickerYear.equalsIgnoreCase(year) && datePickerMonth.equalsIgnoreCase(month)) {
                break;
            } else {
                driver.findElement(By.xpath("//span[text()='Next']")).click();
            }
        }
        driver.findElement(By.linkText(day.replaceFirst("^0+(?!$)", ""))).click();
    }

    public BookingActController createReservation() {
        completeReservationForm(customerData.getFirstname(),
                customerData.getSurname(),
                customerData.getPrice(),
                customerData.getDeposit(),
                customerData.getCheckIn(),
                customerData.getCheckOut());
        return this;
    }

    public BookingActController verifyReservation() {
        driver.findElement(By.xpath("//div/p[text()='" + customerData.getFirstname() + "']")).isDisplayed();
        return this;
    }

    public BookingActController deleteReservation() {
        driver.findElement(By.xpath("//div/p[text()='" + customerData.getFirstname() + "']/parent::div//following-sibling::div/input")).click();
        return this;
    }

    public String getRecordId(String firstName) {
        return driver.findElement(By.xpath("//div/p[text()='" + firstName + "']/parent::div/parent::div")).getAttribute("id");
    }

    public BookingActController delete() {
        //TODO: initialize records with created id then delete
        String lastId = Iterables.getLast(getLastRowId());
        driver.findElement(By.xpath("//div[@id='bookings']/div[@id='" + lastId + "']//input")).click();
        return this;
    }

    public BookingActController verify() {
        //TODO: Update customer id by newly created record id
        boolean t = getBookingList()
                .stream().anyMatch(s -> s.getId().contains(customerData.getId()));
        return this;
    }

    public List<String> getLastRowId() {
        List<String> recordId = new ArrayList<>();
        List<WebElement> tableRows = driver.findElements(By.xpath("//div[@id='bookings']/div[@id]"));
        if (tableRows.size() > 0) {
            for (int i = 1; i <= tableRows.size(); i++) {
                List<WebElement> tableData = driver.findElements(By.xpath("//div[@id='bookings']/div/div/p"));
                recordId.add(driver.findElement(By.xpath("//div[@id='bookings']/div[" + (i + 1) + "]")).getAttribute("id"));
            }
        }
        return recordId;
    }

    public List<Booking> getBookingList() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='bookings']/div")));
        Booking booking;
        List<Booking> rows = new ArrayList<>();

        List<WebElement> tableRows = driver.findElements(By.xpath("//div[@id='bookings']/div[@id]"));
        if (tableRows.size() > 0) {
            for (int i = 1; i <= tableRows.size(); i++) {
                List<WebElement> tableData = driver.findElements(By.xpath("//div[@id='bookings']/div/div/p"));
                String id = driver.findElement(By.xpath("//div[@id='bookings']/div[" + (i + 1) + "]")).getAttribute("id");

                booking = new Booking(
                        id,
                        tableData.get(0).getText(),
                        tableData.get(1).getText(),
                        tableData.get(2).getText(),
                        tableData.get(3).getText(),
                        tableData.get(4).getText(),
                        tableData.get(5).getText());
                rows.add(booking);
            }
        }
        return rows;
    }

    public List<String> getSearchResult() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='bookings']/div")));

        List<String> rows = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        List<WebElement> tableRows = driver.findElements(By.xpath("//div[@id='bookings']/div[@id]"));
        if (tableRows.size() > 0) {
            for (int i = 1; i <= tableRows.size(); i++) {
                List<WebElement> tableData = driver.findElements(By.xpath("//div[@id='bookings']/div/div/p"));
                for (int j = 0; j < tableData.size(); j++) {
                    sb.append(tableData.get(j).getText());
                    sb.append(",");
                }
            }
            rows.add(sb.toString());
        }
        return rows;
    }
}
