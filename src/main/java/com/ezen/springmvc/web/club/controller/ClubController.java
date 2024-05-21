package com.ezen.springmvc.web.club.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/club")
@Slf4j
public class ClubController {

    // 전체 클럽 목록
    @GetMapping("/list")
    public String clubList() {
        return "/club/clublist";
    }

    // 내 클럽 정보 보기
    @GetMapping("/myteam")
    public String myteam() {
        return "/club/myteam";
    }

    // 클럽 상세보기
    @GetMapping("/detail")
    public String clubDetail() {
        return "/club/clubdetail";
    }

    // 새로운 경기 생성하기
    @GetMapping("/create")
    public String create() {
        return "/club/createMatch";
    }

    // 새로운 클럽 생성하기
    @GetMapping("/register")
    public String clubRegister() {
        return "/club/clubregister";
    }

    // 클럽생성 완료 페이지
    @GetMapping("/registersuccess")
    public String registersuccess() {
        return "/club/clubregistersuccess";
    }

    // 클럽가입 페이지
    @GetMapping("/join")
    public String clubJoin() {
        return "/club/clubjoin";
    }





}









