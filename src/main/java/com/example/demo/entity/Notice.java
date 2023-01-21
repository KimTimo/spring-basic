package com.example.demo.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("notice")
public class Notice {

    private int noticeId;
    private int memNo;
    private String noticeTitle;
    private String noticeContent;
    private String noticeCreateDate;
    private String noticeModifyDate;
    private int noticeStatus;
}
