package com.ezen.springmvc.web.member.controller;

import com.ezen.springmvc.domain.common.dto.UploadFile;
import com.ezen.springmvc.domain.common.service.FileService;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.member.mapper.MemberMapper;
import com.ezen.springmvc.domain.member.service.MemberService;
import com.ezen.springmvc.web.article.form.ArticleForm;
import com.ezen.springmvc.web.member.form.FindIdForm;
import com.ezen.springmvc.web.member.form.FindPasswdForm;
import com.ezen.springmvc.web.member.form.LoginForm;
import com.ezen.springmvc.web.member.form.MemberForm;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    /** 회원 프로필 사진 저장 경로  **/
    @Value("${upload.profile.path}")
    private String profileFileUploadPath;
    /** 임시비밀번호 발송 이메일 **/
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    private final MemberMapper memberMapper;

    @Autowired
    private FileService fileService;

    @Autowired
    private MemberService memberService;

    /** 신규 회원가입 요청 **/
    @GetMapping("/signup")
    public String signUpForm(Model model) {
        MemberForm memberForm = MemberForm.builder().build();
        model.addAttribute("memberForm", memberForm);
        return "/member/signUpForm";
    }

    /** 신규 회원가입 요청 처리 **/
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


    /** 신규 회원가입 결과 요청 **/
    @GetMapping("/signup/result")
    public String signUpResult() {


        return "/member/signUpResult";
    }

    /** 회원 프로필 사진 API **/
    @GetMapping("/image/{profileFileName}")
    @ResponseBody
    public ResponseEntity<Resource> showImage(@PathVariable("profileFileName") String profileFileName) throws IOException {
        Path imagePath = Paths.get(profileFileUploadPath, profileFileName);
        if (!Files.exists(imagePath) || profileFileName == null || profileFileName.isEmpty()) {
            imagePath = Paths.get("src/main/resources/static/img/challae.png");
        }

        String contentType = Files.probeContentType(imagePath);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        Resource resource = new FileSystemResource(imagePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }



    /** 로그인 화면 요청 처리 API **/
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

    /** 로그인 화면 요청 처리 API **/
    @PostMapping("/signin")
    public String signInAction(@ModelAttribute LoginForm loginForm, HttpServletRequest request, HttpServletResponse response,Model model) {
        MemberDto loginMember = memberService.isMember(loginForm.getLoginId(), loginForm.getLoginPasswd());

        // 회원 아닌 경우
        if (loginMember == null) {
            model.addAttribute("loginError", "아이디 또는 비밀번호를 확인해주세요");
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

            return "redirect:/";
}

    /** 로그아웃 요청 처리 API **/
    @GetMapping("/signout")
    public String signInAction(HttpServletRequest request) {
        // 세션이 있으면 기존 세션 반환, 없으면 생성하지 않고 null 반환
        HttpSession session =  request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    /** 회원 정보 수정 처리  **/
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

    /** 회원 정보로 아이디 찾기 처리 API **/
    @GetMapping("/find")
    public String findIdForm(Model model) {
        FindIdForm findIdForm = FindIdForm.builder().build();
        model.addAttribute("findIdForm",findIdForm);

        return "/member/findIdForm";
    }

    /** 회원 정보로 아이디 찾기 처리 API **/
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

    /** 회원 정보로 아이디 찾기 결과 처리 API **/
    @GetMapping("/find/result")
    public String findIdResult(Model model) {
        MemberDto foundMember = (MemberDto) model.asMap().get("foundMember");
        model.addAttribute("foundMember", foundMember);
        return "/member/findIdResult";
    }

    /** 회원 정보로 비밀번호 찾기 처리 API **/
    @GetMapping("/findPasswd")
    public String findPasswdForm(Model model) {
        FindPasswdForm findPasswdForm = new FindPasswdForm();
        model.addAttribute("findPasswdForm", findPasswdForm);
        return "/member/findPasswdForm";
    }

    /** 회원 정보로 비밀번호 찾기 처리 API **/
    @PostMapping("/findPasswd")
    public String findPasswdFormAction(@ModelAttribute FindPasswdForm findPasswdForm, RedirectAttributes redirectAttributes) {
        // 입력된 정보를 바탕으로 회원 정보 조회
        MemberDto foundMember = memberService.findMemberByIdNameEmail(findPasswdForm.getId(), findPasswdForm.getName(), findPasswdForm.getEmail());

        // 해당 멤버가 존재하는지 확인하고 처리
        if (foundMember != null) {
            try {
                // 임시 비밀번호 생성
                UUID uuid = UUID.randomUUID();
                String tempPasswd = uuid.toString().substring(0, 6);

                // 회원 정보에 임시 비밀번호 업데이트
                foundMember.setPasswd(tempPasswd);
                memberMapper.updatePassword(foundMember.getId(), tempPasswd);

                // 회원에게 임시 비밀번호 이메일 전송
                String userEmail = foundMember.getEmail(); // 회원 이메일 주소
                String subject = "[찰래 홈페이지] 임시비밀번호 안내";
                String body = "회원님의 임시 비밀번호는 " + tempPasswd + "입니다.";
                sendEmail(userEmail, subject, body);

                // 성공 메시지 추가
                redirectAttributes.addFlashAttribute("successMessage", "임시 비밀번호가 이메일로 발송되었습니다.");
            } catch (Exception e) {
                // 오류 메시지 추가
                redirectAttributes.addFlashAttribute("errorMessage", "임시 비밀번호 발송 중 오류가 발생했습니다.");
            }
        } else {
            // 회원이 존재하지 않을 경우 오류 메시지 추가
            redirectAttributes.addFlashAttribute("errorMessage", "일치하는 회원 정보가 없습니다.");
        }
        // 비밀번호 찾기 결과 페이지로 리다이렉트
        return "redirect:/member/findPasswd/result";
    }

    /** 회원 정보로 비밀번호 찾기 결과 처리 API **/
    @GetMapping("/findPasswd/result")
    public String findPasswdFormResult() {
        return "/member/findPasswdResult";
    }


    /** 아이디 중복체크 처리 API **/
    @GetMapping("/idcheck/{id}")
    public @ResponseBody Map<String, Object> idDupCheckAction(@PathVariable("id") String inputId) {

        Map<String, Object> map = new HashMap<>();
        map.put("result", true);
        map.put("message", "사용 가능한 아이디입니다.");

        MemberDto memberDto = memberService.getMember(inputId);
        if (memberDto != null) {
            map.put("result", false);
            map.put("message", "이미 사용중인 아이디입니다.");
        }
        return map;
    }

    /** 이메일 발송 처리 API **/
    private void sendEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(body);
        javaMailSender.send(message);
    }



}





