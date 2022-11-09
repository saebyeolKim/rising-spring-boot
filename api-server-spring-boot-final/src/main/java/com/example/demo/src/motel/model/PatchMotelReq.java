package com.example.demo.src.motel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchMotelReq {
    private int motelId;
    private String motelName;
    private String delYn;
}
