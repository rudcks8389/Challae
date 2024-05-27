package com.ezen.springmvc.web.club.controller;

import com.ezen.springmvc.domain.match.dto.CreateDto;

import com.ezen.springmvc.domain.match.service.CreateService;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/club")
@Slf4j
public class ClubController {

    // 전략판(캔버스) 저장되는 경로를 변수로 지정
    @Value("${upload.soccerboard.path}")
    private String soccerBoardUploadPath;


    @Autowired
    private CreateService createService;

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


    @PostMapping("/create")
//    @ResponseBody
    public String createMatch(@ModelAttribute CreateDto createDto, HttpSession session) {
        // 세션에서 클럽번호 가져오기
        Integer clubNum = (Integer) session.getAttribute("clubnum");

        if (clubNum != null) {
            createDto.setClubNum(clubNum);
        } else {
            log.warn("클럽 번호가 세션에 존재하지 않습니다.");
            // 클럽 번호가 없을 경우의 로직
        }


        log.info("dto: {}", createDto); // Dto 데이터 여부 체크
        /* 경기 일정 생성하기 */
        createService.createMatch(createDto);
        return "redirect:/club/create";
    }

    @PostMapping("/uploadCanvas")
    @ResponseBody
    public Map<String, String> uploadCanvas(@RequestPart("canvasImage") MultipartFile canvasImage) {
        String fileName = canvasImage.getOriginalFilename();
        String filePath = soccerBoardUploadPath + canvasImage.getOriginalFilename();

        log.info("경로포함된 업로드 파일 : " + filePath);
        log.info("DB에 업로드 파일 : " + fileName);

        try {
            canvasImage.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.singletonMap("error", "파일 저장 실패");
        }
        return Collections.singletonMap("filePath", fileName);
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









