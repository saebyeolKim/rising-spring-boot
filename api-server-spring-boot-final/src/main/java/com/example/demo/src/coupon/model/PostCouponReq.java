package com.example.demo.src.coupon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostCouponReq {
    private int couponType;
    private String couponName;
    private String startDt;
    private String endDt;
    private int discountPrice;
    private String productUrl;
    private int publishCount;
    private int limitPrice;
    private String useStartDt;
    private String useEndDt;
    private int usePrice;
    private String useComment;
}
