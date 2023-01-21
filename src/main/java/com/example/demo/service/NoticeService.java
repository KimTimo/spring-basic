package com.example.demo.service;

import com.example.demo.dto.NoticeDto;
import com.example.demo.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeMapper noticeMapper;

    public List<NoticeDto> noticeList(){
        return noticeMapper.noticeList();
    }

    public int regNotice(NoticeDto noticeDto){
        return noticeMapper.regNotice(noticeDto);
    }
}
