package com.ezen.springmvc.web.field.controller;

import com.ezen.springmvc.domain.field.dto.FieldDto;
import com.ezen.springmvc.domain.field.service.FieldService;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.reservation.dto.ReservationDto;
import com.ezen.springmvc.domain.reservation.mapper.ReservationMapper;
import com.ezen.springmvc.domain.reservation.service.ReservationService;
import com.ezen.springmvc.web.field.form.ReservationForm;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/field")
@Slf4j
public class FieldController {
    private   FieldService fieldService;
    private   ReservationService reservationService;
    private ReservationMapper reservationMapper;

    @Autowired
    public FieldController(FieldService fieldService, ReservationService reservationService, ReservationMapper reservationMapper) {
        this.fieldService = fieldService;
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

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



    /** 새로운 예약 API **/
    @GetMapping("/saveReservation")
    public @ResponseBody ReservationDto insertReservation(Model model, ReservationForm reservationForm, HttpSession session) {
        MemberDto loginMember = (MemberDto) session.getAttribute("loginMember");
        if (loginMember == null) {
            // 로그인되지 않은 경우에 대한 처리
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        log.info("reservationForm: {}", reservationForm);


        ReservationDto reservationDto = ReservationDto.builder()
                .resDate(reservationForm.getResResDate())
                .resPrice(reservationForm.getResPrice())
                .memberNum(Integer.parseInt(loginMember.getMemberNum()))
                .resTime(reservationForm.getResTime())
                .resMemo(reservationForm.getResMemo())
                .build();
        reservationService.reserveField(reservationDto);
        return reservationDto;
    }
    //구장 예약 정보 출력
    @GetMapping("/payfinish/{resNum}")
    public String showpayfinish(@PathVariable("resNum") int resNum, Model model, HttpSession session) {
        MemberDto memberDto = (MemberDto) session.getAttribute("loginMember");
        int memberNum = Integer.parseInt(memberDto.getMemberNum()); // 세션에서 로그인된 회원의 멤버 넘버만 가져옴

        MemberDto memberInfo = reservationService.findByMemberNum(memberNum);

        ReservationDto reservationDto = reservationService.findByReservationNum(resNum);
        model.addAttribute("reservation", reservationDto);
        model.addAttribute("memberinfo", memberInfo);
        log.info("reservationDto: {}", reservationDto);


        return "/reservation/payfinish";
    }



}









