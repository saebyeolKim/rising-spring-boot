package com.example.demo.src.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetHotelRes {
    private int regionId;
    private int accommodationId;
    private int id;
    private String name;
    private String tel;
    private String location;
    private String notify;
    private String event;
    private String couponEvent;
    private String Service;
    private String policy;
    private String Star;
    private String applyingCoupon;
    private String image;
    private String ceoName;
    private String BusinessName;
    private String businessLocation;
    private String businessEmail;
    private String businessTelno;
    private String businessRegistrationNo;
    private String delYn;
    private String createDt;
    private String updateDt;
}
