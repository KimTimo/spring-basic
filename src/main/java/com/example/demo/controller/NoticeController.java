package com.example.demo.controller;

import com.example.demo.dto.NoticeDto;
import com.example.demo.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class NoticeController {

    private final NoticeService noticeService;

    // 글 목록
    @GetMapping("/notice-list")
    public ModelAndView noticeList(ModelAndView mv){
        List<NoticeDto> noticeList = noticeService.noticeList();

        mv.addObject("noticeList", noticeList);
        mv.setViewName("notice/notice-list");
        return mv;
    }

    @GetMapping("register-notice")
    public String regNotice(){
        return "notice/register-notice";
    }

    @PostMapping("register-noticeOk")
    public String regNoticeOk(NoticeDto noticeDto){
        noticeService.regNotice(noticeDto);
        return "redirect:/notice-list";
    }

}
