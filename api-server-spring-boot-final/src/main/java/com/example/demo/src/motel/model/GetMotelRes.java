package com.example.demo.src.motel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetMotelRes {
    private int regionId;
    private int accommodationId;
    private int motelId;
    private String motelName;
    private String motelTelno;
    private String motelLocation;
    private String motelNotify;
    private String motelEvent;
    private String motelCouponEvent;
    private String motelService;
    private String motelPolicy;
    private String motelStar;
    private String motelApplyingCoupon;
    private String motelImage;
    private String motelCeoName;
    private String motelBusinessName;
    private String motelBusinessEmail;
    private String motelBusinessTelno;
    private String motelBusinessRegistrationNo;
    private String delYn;
    private String createDt;
    private String updateDt;
}
