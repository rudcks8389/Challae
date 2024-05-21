package com.ezen.springmvc.web.field.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/field")
@Slf4j
public class FieldController {

    // 전체 구장 목록
    @GetMapping("/list")
    public String fieldList() {
        return "/field/fieldlist";
    }

    // 구장 상세보기 화면
    @GetMapping("/view")
    public String fieldView() {
        return "/field/fieldView";
    }

    // 구장 예약 화면
    @GetMapping("/reservation")
    public String reservation() {
        return "/reservation/reservation";
    }



}









