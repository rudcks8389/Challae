package com.ezen.springmvc.web.member.controller;

import com.ezen.springmvc.domain.common.dto.UploadFile;
import com.ezen.springmvc.domain.common.service.FileService;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.member.service.MemberService;
import com.ezen.springmvc.web.article.form.ArticleForm;
import com.ezen.springmvc.web.member.form.FindIdForm;
import com.ezen.springmvc.web.member.form.LoginForm;
import com.ezen.springmvc.web.member.form.MemberForm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    @Value("${upload.profile.path}")
    private String profileFileUploadPath;

    @Autowired
    private FileService fileService;

    @Autowired
    private MemberService memberService;

    // 회원 가입 화면
    @GetMapping("/signup")
    public String signUpForm(Model model) {
        MemberForm memberForm = MemberForm.builder().build();
        model.addAttribute("memberForm", memberForm);
        return "/member/signUpForm";
    }

    // 회원 가입 요청 처리
    @PostMapping("/signup")
    public String signUpAction(@ModelAttribute MemberForm memberForm, RedirectAttributes redirectAttributes, Model model) {
        log.info("회원 정보 : {}", memberForm.toString());

        // 업로드 프로필 사진 저장
        UploadFile uploadFile = fileService.storeFile(memberForm.getProfileImage(), profileFileUploadPath);
        // Form  -> Dto 변환
        MemberDto memberDto = MemberDto.builder()
                .id(memberForm.getId())
                .name(memberForm.getName())
                .email(memberForm.getEmail())
                .phone(memberForm.getPhone())
                .profileImage(uploadFile.getUploadFileName())
                .storedProfile(uploadFile.getStoreFileName())
                .passwd(memberForm.getPasswd())
                .build();
        memberService.register(memberDto);
        redirectAttributes.addFlashAttribute("memberDto", memberDto);
        return "redirect:/member/signup/result";
    }


    // 회원 가입 결과 화면 요청 처리
    @GetMapping("/signup/result")
    public String signUpResult() {
        return "/member/signUpResult";
    }

    // 회원 프로필 사진 요청 처리
    @GetMapping("/image/{profileFileName}")
    @ResponseBody
    public ResponseEntity<Resource> showImage(@PathVariable("profileFileName") String profileFileName) throws IOException {
        Path path = Paths.get(profileFileUploadPath + "/" + profileFileName);
        String contentType = Files.probeContentType(path);
        Resource resource = new FileSystemResource(path);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }



    // 회원 로그인 화면 요청 처리
    @GetMapping("/signin")
    public String signInForm(@ModelAttribute LoginForm loginForm, HttpServletRequest request) {
        // 쿠키에서 saveId 값 읽기
        String rememberedLoginId = null;
        boolean rememberMeChecked = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("saveId".equals(cookie.getName())) {
                    rememberedLoginId = cookie.getValue();
                    rememberMeChecked = true;
                    break;
                }
            }
        }
        // 로그인 폼 객체에 쿠키에서 읽은 아이디와 rememberMe 설정
        loginForm.setLoginId(rememberedLoginId);
        loginForm.setRememberLoginId(rememberMeChecked);

        return "/member/signInForm"; // 로그인 페이지 템플릿
    }

    // 회원 로그인 요청 처리
    @PostMapping("/signin")
    public String signInAction(@ModelAttribute LoginForm loginForm, HttpServletRequest request, HttpServletResponse response) {
        log.info("로그인 정보 : {}", loginForm.toString());
        MemberDto loginMember = memberService.isMember(loginForm.getLoginId(), loginForm.getLoginPasswd());
        // 회원 아닌 경우
        if (loginMember == null) {
            return "/member/signInForm";
        }
        // 회원인 경우
        // 아이디 저장 체크 시
        if(loginForm.isRememberLoginId()){
            Cookie saveIdCookie = new Cookie("saveId", loginMember.getId());
            saveIdCookie.setMaxAge(60*60*24*100); // 100일저장
            saveIdCookie.setPath("/");
            response.addCookie(saveIdCookie);
        }else {
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("saveId")){
                        cookie.setPath("/");
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        }
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", loginMember);
        log.info("@@Session created with loginMember@@: {}", session.getAttribute("loginMember"));
        return "redirect:/";
}

    // 회원 로그아웃 요청 처리
    @GetMapping("/signout")
    public String signInAction(HttpServletRequest request) {
        // 세션이 있으면 기존 세션 반환, 없으면 생성하지 않고 null 반환
        HttpSession session =  request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    //회원 정보 수정
    @PostMapping("/update")
    public String updateMemberInfo(
            @RequestParam(value = "newEmail", required = false) String newEmail,
            @RequestParam(value = "password", required = false) String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        MemberDto loginMember = (MemberDto) session.getAttribute("loginMember");
        if (newEmail != null && !newEmail.isEmpty()) {
            loginMember.setEmail(newEmail);
        }

        if (password != null && !password.isEmpty()) {
            loginMember.setPasswd(password);
        }
        memberService.editMember(loginMember);

        // 수정이 완료된 후에는 인덱스 페이지로 리다이렉트
        return "redirect:/";
    }

    // 회원정보로 아이디 찾기
    @GetMapping("/find")
    public String findIdForm(Model model) {
        FindIdForm findIdForm = FindIdForm.builder().build();
        model.addAttribute("findIdForm",findIdForm);

        return "/member/findIdForm";
    }

    @PostMapping("/find")
    public String findIdFormAction(@ModelAttribute FindIdForm findIdForm, RedirectAttributes redirectAttributes) {
        MemberDto foundMember = memberService.findId(findIdForm.getName(), findIdForm.getEmail());
        log.info("@@찾은 아이디 : {}", foundMember);
        if (foundMember == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "해당 정보로 등록된 아이디를 찾을 수 없습니다.");
        } else {
            redirectAttributes.addFlashAttribute("foundMember", foundMember);
        }
        return "redirect:/member/find/result";
    }

    @GetMapping("/find/result")
    public String findIdResult(Model model) {
        MemberDto foundMember = (MemberDto) model.asMap().get("foundMember");
        model.addAttribute("foundMember", foundMember);
        return "/member/findIdResult";
    }




}





