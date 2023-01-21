package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    // 회원가입 페이지
    @GetMapping("join-user")
    public String joinUser(){
        return "user/join-user";
    }

    // 회원가입 완료처리
    @PostMapping("join-userOk")
    public String joinUserOk(UserDto userDto){
        userService.joinUser(userDto);
        return "redirect:/notice-list";
    }

    @GetMapping("user-info")
    public ModelAndView userInfo(ModelAndView mv, @RequestParam(value = "memNo", required = false) int memNo){

        UserDto userInfo = userService.userInfo(memNo);
        log.info("memNo => " + memNo);

        mv.addObject("userInfo", userInfo);
        mv.setViewName("user/user-info");

        return mv;
    }


    // ID 중복체크
    @ResponseBody
    @GetMapping("user-idCheck")
    public int idCheck(@RequestParam(value = "memId", required = false) String memId){
        return userService.idCheck(memId);
    }

}
