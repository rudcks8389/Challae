package com.ezen.springmvc.web.match.controller;

import com.ezen.springmvc.domain.match.dto.CreateDto;
import com.ezen.springmvc.domain.match.service.CreateService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.PublicKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/club")
@Slf4j
public class MatchController {

    @Autowired
    private CreateService createService;

    // 경기 정보 보기

    /**
     * 선택한 경기의 정보 보기
     * @param matchNum 경기 번호
     */
    @GetMapping("/matchView")
    public String todayMatch(@RequestParam("matchNum") String matchNum, Model model) {
        CreateDto matchDetail = createService.getMachDetail(matchNum);

        /** 출력 형식에 맞는 날짜 형식 변환**/
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd");
        try {
            Date date = originalFormat.parse(matchDetail.getMatchDate());
            String formattedDate = targetFormat.format(date);
            matchDetail.setMatchDateForm(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            log.info("날짜 형식 변경중 오류 발생");
        }


        model.addAttribute("matchDetail", matchDetail);

        return "/match/matchView";
    }


}









