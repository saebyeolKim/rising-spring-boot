package com.example.demo.src.notice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetNoticeRes {
    private String notifyTitle;
    private String notifyContent;
    private String date;
}
