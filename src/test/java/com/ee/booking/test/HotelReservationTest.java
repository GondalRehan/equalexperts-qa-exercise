package com.ee.booking.test;

import com.ee.booking.pages.HotelBookingFormPage;
import org.testng.annotations.Test;

import static com.ee.booking.pages.HotelBookingFormPage.getBookingPage;
import static org.testng.Assert.assertTrue;

public class HotelReservationTest extends BaseTest {

    @Test
    public void makeConfirmAndEraseBooking() {
        HotelBookingFormPage bookingPage = getBookingPage();
        bookingPage.act()
                .createReservation()
                .verifyReservation()
                .deleteReservation();
        assertTrue(bookingPage.verify().isRecordPresent());
    }
}
