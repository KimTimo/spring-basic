package com.example.demo.mapper;

import com.example.demo.dto.NoticeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    // 게시판 목록
    List<NoticeDto> noticeList();

    // 게시판 등록
    int regNotice(NoticeDto noticeDto);

}
