package com.example.demo.src.point.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetPointRes {
    public String pointName;
    public int pointCount;
    public int possiblePoints;
    public int disappearPoints;
}
