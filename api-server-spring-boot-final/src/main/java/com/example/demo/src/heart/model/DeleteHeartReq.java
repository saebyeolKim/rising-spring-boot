package com.example.demo.src.heart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeleteHeartReq {
    private int userIdx;
    private int accommodationId;
}
