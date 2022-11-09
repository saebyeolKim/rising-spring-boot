package com.example.demo.src.motel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Motel {
    private int motelId;
    private String motelName;
    private String motelTelno;
    private String motelLocation;
    private String motelNotify;
    private String motelEvent;
    private String motelService;
    private String motelPolicy;
    private String delYn;
}
