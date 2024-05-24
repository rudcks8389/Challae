package com.ezen.springmvc.web.club.controller;

import com.ezen.springmvc.domain.community.dto.CommunityDto;
import com.ezen.springmvc.domain.community.service.CommunityService;
import com.ezen.springmvc.domain.match.dto.FieldDto;
import com.ezen.springmvc.domain.match.service.CreateService;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/club")
@Slf4j
public class ClubController {

    @Autowired
    private CreateService createService;
    @Autowired
    private CommunityService communityService;
    @Autowired
    private MemberService memberService;

    // 전체 클럽 목록
    @GetMapping("/list")
    public String clubList() {
        return "/club/clublist";
    }

    // 내 클럽 정보 보기
    @GetMapping("/myteam")
    public String myteam(Model model) {

        // 팀별 팀원목록 출력
        List<MemberDto> teamMember = memberService.getTeamMember("102");
        model.addAttribute("teamMember",teamMember);


        // 커뮤니티 내용데이터 출력
        List<CommunityDto> community = communityService.getContents();
        model.addAttribute("community", community);

        return "/club/myteam";
    }

    // 클럽 상세보기
    @GetMapping("/detail")
    public String clubDetail() {
        return "/club/clubdetail";
    }

    // 새로운 경기 생성하기
    @GetMapping("/create")
    public String create(Model model) {

        /* 구장 리스트 가져오기 */
        List<FieldDto> fields = createService.getFields();
        model.addAttribute("fields", fields);

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









