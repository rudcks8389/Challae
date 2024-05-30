package com.ezen.springmvc.web.club.controller;

import com.ezen.springmvc.domain.match.dto.ClubMatchDto;
import com.ezen.springmvc.domain.match.service.CreateService;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClubScheduleController {

    @Autowired
    private CreateService createService;
    // myteam 클럽 출력일정
    @GetMapping("/schedules/{clubNum}")
    public ResponseEntity<List<ClubMatchDto>> getClubSchedules(@PathVariable String clubNum, HttpServletRequest request){
        HttpSession session = request.getSession();
        MemberDto loginMember = (MemberDto) session.getAttribute("loginMember");
        List<ClubMatchDto> match = createService.getMatch(loginMember.getClubNum());
        return ResponseEntity.ok(match);
    }
}
