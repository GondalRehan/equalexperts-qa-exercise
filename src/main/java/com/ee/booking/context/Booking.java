package com.ee.booking.context;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter

@Data
public class Booking {
    private String id = "";
    private String firstname;
    private String surname;
    private String price;
    private String deposit;
    private String checkIn;
    private String checkOut;

}
