package com.example.demo.src.point.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostPointReq {
    private int userIdx;
    private int pointCount;
    private String pointName;
    private String startDt;
    private String endDt;
}
