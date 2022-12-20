package com.example.demo.src.heart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostHeartReq {
    public int userIdx;
    public int accommodationId;
    public String startDt;
    public String endDt;
    public String adults;
    public String children;
}
