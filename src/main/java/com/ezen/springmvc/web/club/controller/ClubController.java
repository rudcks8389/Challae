package com.ezen.springmvc.web.club.controller;

import com.ezen.springmvc.domain.club.dto.ClubDto;
import com.ezen.springmvc.domain.club.service.ClubService;
import com.ezen.springmvc.domain.community.dto.CommunityDto;
import com.ezen.springmvc.domain.community.service.CommunityService;
import com.ezen.springmvc.domain.match.dto.FieldDto;
import com.ezen.springmvc.domain.match.service.CreateService;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.member.service.MemberService;
import com.ezen.springmvc.web.club.form.CommunityForm;
import com.ezen.springmvc.web.member.form.MemberForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/club")
@Slf4j
public class ClubController {

    @Autowired
    private CreateService createService;
    @Autowired
    private ClubService clubService;
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
    public String myteam(CommunityDto communityDto, HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        MemberDto loginMember = (MemberDto)session.getAttribute("loginMember");
        log.info("로그인한 객체");

        if (loginMember != null){
        String loginClubNumber = loginMember.getClubNum(); // 세션 객체에서 클럽번호 추출

        if (loginClubNumber == null){
            return "redirect:/club/list";
        }
        // 로그인한 멤버의 클럽정보 출력
            List<ClubDto> clubData = clubService.clubDataService(loginClubNumber);
            model.addAttribute("clubData", clubData);

        // 로그인한 멤버의 팀원목록 출력
        List<MemberDto> clubMember = memberService.getTeamMember(loginClubNumber);
        model.addAttribute("clubMember",clubMember);

        // 커뮤니티 내용데이터 출력 (단순 DB출력만 되어있음)
        List<CommunityDto> community = communityService.getCommunityContents(loginClubNumber);
        model.addAttribute("community", community);

            // CommunityForm 객체를 모델에 추가
            CommunityForm communityForm = CommunityForm.builder().build();
             model.addAttribute("communityForm", communityForm);



        return "/club/myteam";

        }else {
            return "redirect:/"; // 로그인을 하지 않았음에도
        }
    }
    @PostMapping("/myteam")
    public String inputCommDate(@ModelAttribute CommunityForm communityForm, HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        MemberDto loginMember = (MemberDto)session.getAttribute("loginMember");
        String clubNum = loginMember.getClubNum();
        String memberNum = loginMember.getMemberNum();

        // 커뮤니티 입력시 저장
       CommunityDto inputData = CommunityDto.builder()
                .commContent(communityForm.getContent())
                .clubNum(clubNum)
                .memberNum(memberNum)
                .build();

        communityService.inputCommunity(inputData);

        return "redirect:/club/myteam";

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









