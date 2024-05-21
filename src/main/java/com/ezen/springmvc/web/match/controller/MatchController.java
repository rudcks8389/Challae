package com.ezen.springmvc.web.match.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/match")
@Slf4j
public class MatchController {

    // 경기 정보 보기
    @GetMapping("/view")
    public String todayMatch() {
        return "/match/matchView";
    }


}









