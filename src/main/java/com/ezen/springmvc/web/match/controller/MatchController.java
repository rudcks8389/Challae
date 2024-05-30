package com.ezen.springmvc.web.match.controller;

import com.ezen.springmvc.domain.match.dto.CreateDto;
import com.ezen.springmvc.domain.match.service.CreateService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.PublicKey;

@Controller
@RequestMapping("/match")
@Slf4j
public class MatchController {

    @Autowired
    private CreateService createService;

    // 경기 정보 보기
    @GetMapping("/view")
    public String todayMatch() {


        return "/match/matchView";
    }


}









