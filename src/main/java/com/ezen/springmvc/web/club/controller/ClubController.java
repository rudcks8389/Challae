package com.ezen.springmvc.web.club.controller;

import com.ezen.springmvc.domain.club.dto.ClubDto;
import com.ezen.springmvc.domain.club.service.ClubServiceImpl;
import com.ezen.springmvc.domain.common.dto.UploadFile;
import com.ezen.springmvc.domain.common.service.FileService;
import com.ezen.springmvc.domain.match.dto.FieldDto;
import com.ezen.springmvc.domain.match.service.CreateService;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.web.club.form.ClubRegisterForm;
import com.ezen.springmvc.web.member.form.MemberForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/club")
@Slf4j
public class ClubController {

    @Autowired
    private CreateService createService;

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
    public String create(Model model) {

        /* 구장 리스트 가져오기 */
        List<FieldDto> fields = createService.getFields();
        model.addAttribute("fields", fields);

        return "/club/createMatch";
    }

    // 클럽 생성 화면
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









