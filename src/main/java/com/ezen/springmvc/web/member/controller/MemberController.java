package com.ezen.springmvc.web.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {

    // 회원 가입 화면
    @GetMapping("/signup")
    public String signUpForm() {
        return "/member/signUpForm";
    }

    // 회원 로그인 화면
    @GetMapping("/signin")
    public String signInForm() {
        return "/member/signInForm";
    }
}





