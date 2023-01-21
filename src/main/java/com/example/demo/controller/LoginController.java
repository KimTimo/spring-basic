package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("login")
@Controller
public class LoginController {

    @GetMapping
    public String loginPage(@RequestParam(required = false) String errorMsg, Model model){
        model.addAttribute("errorMsg", errorMsg);
        return "login/login";
    }
}
