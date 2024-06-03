package com.ezen.springmvc.web.api.controller;
import com.ezen.springmvc.domain.article.service.ArticleService;
import com.ezen.springmvc.domain.club.dto.ClubDto;
import com.ezen.springmvc.domain.club.service.ClubService;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.member.service.MemberService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private MemberService memberService;

    /** 회원 프로필사진 업로드 경로 **/
    @Value("${upload.profile.path}")
    private String profileFileUploadPath;

    /** 메일을 보내는 사람의 주소 **/
    @Value("${spring.mail.username}")
    private String from;

    /** 클럽 프로필사진 업로드 경로 **/
    @Value("${upload.clublogo.path}")
    private String clublogoUploadPath;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ClubService clubService;

    @Autowired
    private ArticleService articleService;

    /** 전체 회원 수 반환 API **/
    @GetMapping("/memberCount")
    public int getMemberCount() {
        return memberService.memberCount();
    }

    /** 전체 클럽 수 반환 API **/
    @GetMapping("/clubCount")
    public int getClubCount() {
        return clubService.clubCount();
    }

    /** 전체 게시판 수 반환 API **/
    @GetMapping("/articleCount")
    public int getArticleCount() {
        return articleService.articleCount();
    }

    /** 모든 회원 목록 반환 API **/
    @GetMapping("/allMember")
    public List<MemberDto> getAllMember() {
        return memberService.viewAllMember();
    }

    /** 회원 강제 탈퇴 API **/
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/deleteMember")
    public ResponseEntity<Void> deleteMember(@RequestParam int memberNum) {
        memberService.deleteMember(memberNum);
        return ResponseEntity.ok().build();
    }

    /** 클럽 생성을 기다리는 클럽 목록 API **/
    @GetMapping("/pending")
    public List<ClubDto> getPendingClubs() {
        return clubService.findPendingClubs();
    }

    /** 클럽 생성 승인 API **/
    @PostMapping("/approve")
    public void approveClub(@RequestParam int clubNum) {
        clubService.updateStatus(clubNum, "승인");

        String presidentEmail = clubService.findClubById(clubNum);

        // 클럽 승인 이메일 발송
        String subject = "[찰래 홈페이지] 클럽 승인 안내";
        String body = "요청하신 클럽이 성공적으로 승인되었습니다.";

        try {
            sendEmail(presidentEmail, subject, body);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /** 클럽 생성 거절 API **/
    @PostMapping("/reject")
    public void rejectClub(@RequestParam int clubNum) {
        String presidentEmail = clubService.findClubById(clubNum);
        clubService.deleteClub(clubNum);
        // 클럽 거절 이메일 발송
        String subject = "[찰래 홈페이지] 클럽 거절 안내";
        String body = "죄송합니다.요청하신 클럽이 거절되었습니다.";

        try {
            sendEmail(presidentEmail, subject, body);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /** 회원 프로필사진 업로드 **/
    @GetMapping("/image/{profileFileName}")
    @ResponseBody
    public ResponseEntity<Resource> showImage(@PathVariable("profileFileName") String profileFileName) throws IOException {
        // 프로필 이미지 경로 설정
        Path imagePath = Paths.get(profileFileUploadPath, profileFileName);
        if (!Files.exists(imagePath) || profileFileName == null || profileFileName.isEmpty()) {
            imagePath = Paths.get("src/main/resources/static/img/challae.png");
        }

        // 이미지 타입 확인
        String contentType = Files.probeContentType(imagePath);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        Resource resource = new FileSystemResource(imagePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    /** 클럽 로고 업로드 **/
    @GetMapping("/clublogo/{clubLogoName}")
    @ResponseBody
    public ResponseEntity<Resource> showClublogo(@PathVariable("clubLogoName") String clubLogoName) throws IOException {
        // 클럽 로고 이미지 경로 설정
        Path imagePath = Paths.get(clublogoUploadPath, clubLogoName);

        if (!Files.exists(imagePath) || clubLogoName == null || clubLogoName.isEmpty()) {
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

    /** 이메일 발송 메서드 **/
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
