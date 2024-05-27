package com.ezen.springmvc.web.club.controller;

<<<<<<< HEAD
import com.ezen.springmvc.domain.club.dto.ClubDto;
import com.ezen.springmvc.domain.club.service.ClubServiceImpl;
import com.ezen.springmvc.domain.common.dto.UploadFile;
import com.ezen.springmvc.domain.common.service.FileService;
import com.ezen.springmvc.domain.match.dto.FieldDto;
import com.ezen.springmvc.domain.match.service.CreateService;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.web.club.form.ClubRegisterForm;
import com.ezen.springmvc.web.member.form.MemberForm;
=======

import com.ezen.springmvc.domain.club.dto.ClubDto;
import com.ezen.springmvc.domain.club.service.ClubService;
import com.ezen.springmvc.domain.community.dto.CommunityDto;
import com.ezen.springmvc.domain.community.service.CommunityService;

import com.ezen.springmvc.domain.match.service.CreateService;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.member.service.MemberService;
import com.ezen.springmvc.web.club.form.CommunityForm;
import com.ezen.springmvc.web.member.form.MemberForm;
import jakarta.servlet.http.HttpServletRequest;

import com.ezen.springmvc.domain.field.dto.FieldDto;
import com.ezen.springmvc.domain.match.dto.CreateDto;
import com.ezen.springmvc.domain.match.service.CreateService;
import com.ezen.springmvc.domain.member.dto.MemberDto;

import jakarta.servlet.http.HttpSession;
>>>>>>> main
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> main

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
    @Autowired
    private ClubService clubService;
    @Autowired
    private CommunityService communityService;
    @Autowired
    private MemberService memberService;

    @Autowired
    private ClubServiceImpl clubService;

    @Autowired
    private FileService fileService;

    @Value("${upload.clublogo.path}")
    private String clublogoUploadPath;

    // 전체 클럽 목록
    @GetMapping("/list")
    public String clubList(Model model) {
        List<ClubDto> clubs = clubService.clubList();
        model.addAttribute("clubs", clubs);
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
        model.addAttribute("createDto", new CreateDto());

        /* 로그인 상태일 시 클럽 번호로 클럽원 목록 보여주기( 예정 ) */
        List<MemberDto> members = createService.findByClubNum(101);  // 클럽 넘버 받아와야 함
        model.addAttribute("clubMembers", members);

        log.info("101번 클럽 멤버들 : {}", members);

        return "/club/createMatch";
    }

<<<<<<< HEAD
    // 클럽 생성 화면
=======
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
>>>>>>> main
    @GetMapping("/register")
    public String clubRegister(Model model) {
        ClubRegisterForm clubRegisterForm = ClubRegisterForm.builder().build();
        model.addAttribute("clubRegisterForm", clubRegisterForm);
        return "/club/clubregister";
    }

    // 클럽 생성 요청 처리
    @PostMapping("/register")
    public String clubRegisterAction(@ModelAttribute ClubRegisterForm clubRegisterForm, RedirectAttributes redirectAttributes, Model model) {
        log.info("클럽 생성 정보 : {}", clubRegisterForm.toString());

        // 업로드 프로필 사진 저장
        UploadFile uploadFile = fileService.storeFile(clubRegisterForm.getClubPhoto(), clublogoUploadPath);

        // Form Bean -> Dto 변환
        ClubDto clubDto = ClubDto.builder()
                .clubName(clubRegisterForm.getClubName())
                .clubLevel(clubRegisterForm.getClubLevel())
                .clubLocation(clubRegisterForm.getClubLocation())
                .clubPhone(clubRegisterForm.getClubPhone())
                .clubInfo(clubRegisterForm.getClubInfo())
                .clubPhoto(uploadFile.getUploadFileName())
                .clubStoredPhoto(uploadFile.getStoreFileName())
                .build();

        clubService.clubRegister(clubDto);
        redirectAttributes.addFlashAttribute("clubDto", clubDto);
        return "redirect:/club/list";
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
