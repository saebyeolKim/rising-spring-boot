package com.example.demo.src.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchHotelReq {
    private int hotelId;
    private String hotelName;
    private String delYn;
}
