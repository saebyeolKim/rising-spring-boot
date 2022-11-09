package com.example.demo.src.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Hotel {
    private int hotelId;
    private int hotelDetailId;
    private String name;
}
