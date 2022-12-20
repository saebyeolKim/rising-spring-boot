package com.example.demo.src.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostHotelReq {
    private int regionId;
    private String name;
    private String tel;
    private String location;
    private String notify;
    private String event;
    private String couponEvent;
    private String service;
    private String policy;
    private String star;
    private String applyingCoupon;
    private String image;
    private String ceoName;
    private String businessName;
    private String businessLocation;
    private String businessEmail;
    private String businessTelno;
    private String businessRegistrationNo;
}
