package com.example.demo.src.region.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchRegionReq {
    private int regionId;
    private String regionName;
}
