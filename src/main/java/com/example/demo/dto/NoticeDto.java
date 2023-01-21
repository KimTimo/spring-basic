package com.example.demo.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("noticeDto")
public class NoticeDto {

    private int noticeId;
    private int memNo;
    private String noticeTitle;
    private String noticeContent;
    private String noticeCreateDate;
    private String noticeModifyDate;
    /*
    noticeStatus
    * 1 : 활성화
    * 2 : 비활성화
    * 9 : 삭제
    * */
    private int noticeStatus;
    private String memUsername;
}
