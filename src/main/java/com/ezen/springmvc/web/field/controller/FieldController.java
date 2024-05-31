package com.ezen.springmvc.web.field.controller;

import com.ezen.springmvc.domain.field.dto.FieldDto;
import com.ezen.springmvc.domain.field.service.FieldService;
import com.ezen.springmvc.domain.reservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;
import java.util.List;

@Controller
@RequestMapping("/field")
@Slf4j
public class FieldController {
    private   FieldService fieldService;
    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @Autowired
    private ReservationService reservationService;

    // 전체 구장 목록
    @GetMapping("/list")
    public String fieldList(Model model ) {
        List<FieldDto> fieldList = fieldService.findByAll(); // FieldService에서 구장 목록을 가져옴
        model.addAttribute("fieldList", fieldList);
        return "/field/fieldlist";
    }

    // 구장 상세보기 화면
    @GetMapping("/view")
    public String fieldView(@RequestParam("fieldNum") int fieldNum, Model model) {
        FieldDto fieldDetail = fieldService.findByFieldNum(fieldNum);
        log.info("fieldDetail: {}", fieldDetail);
        model.addAttribute("fieldDetail", fieldDetail);
        return "/field/fieldView";
    }


    // 구장 예약 화면
    @GetMapping("/reservation")
    public String reservation(@RequestParam("fieldNum") int fieldNum , Model model) {
        FieldDto fieldDetail2 = fieldService.findByFieldNum2(fieldNum);
        model.addAttribute("fieldDetail2", fieldDetail2);


        return "/reservation/reservation";
    }



}









