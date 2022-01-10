package com.ee.booking.pages;

public class HotelBookingFormPage {

    private BookingActController act;
    private BookingVerifyController verify;

    public BookingActController act(){ return act;}
    public BookingVerifyController verify(){ return verify;}

    private HotelBookingFormPage(){}

     public HotelBookingFormPage(BookingActController act, BookingVerifyController verify){
         this.act = act;
         this.verify = verify;
     }

    public static HotelBookingFormPage getBookingPage(){
        return new HotelBookingFormPage(new BookingActController(), new BookingVerifyController());
    }
}
