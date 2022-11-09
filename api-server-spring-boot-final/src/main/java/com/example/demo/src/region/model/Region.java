package com.example.demo.src.region.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Region {
    private int regionId;
    private String nationRegion;
    private String regionName;
    private String regionDetail;
    private String delYn;
}
