package com.example.demo.src.heart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetHeartsRes {
    private String userId;
    private String startDt;
    private String endDt;
    private String adults;
    private String children;
    private String name;
    private String location;
    private String star;
    private String image;
}
