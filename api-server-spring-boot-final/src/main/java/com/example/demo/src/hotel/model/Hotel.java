package com.example.demo.src.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Hotel {
    private int hotelId;
    private String hotelName;
    private String hotelTelno;
    private String hotelLocation;
    private String hotelNotify;
    private String hotelEvent;
    private String hotelService;
    private String hotelPolicy;
    private String delYn;
}
