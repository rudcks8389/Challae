package com.ezen.springmvc.web.api.controller;


import com.ezen.springmvc.domain.article.service.ArticleService;
import com.ezen.springmvc.domain.club.dto.ClubDto;
import com.ezen.springmvc.domain.club.service.ClubService;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ApiController {


    private static final Logger log = LoggerFactory.getLogger(ApiController.class);
    @Autowired
    private MemberService memberService;

    @Value("${upload.profile.path}")
    private String profileFileUploadPath;

    // 클럽 로고
    @Value("${upload.clublogo.path}")
    private String clublogoUploadPath;

    @Autowired
    private ClubService clubService;

    @Autowired
    private ArticleService articleService;


    @GetMapping("/memberCount")
    public int getMemberCount() {
        return memberService.memberCount();
    }

    @GetMapping("/clubCount")
    public int getClubCount() {
        return clubService.clubCount();
    }

    @GetMapping("/articleCount")
    public int getArticleCount() {
        return articleService.articleCount();
    }

    @GetMapping("/allMember")
    public List<MemberDto> getAllMember() {
        return memberService.viewAllMember();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/deleteMember")
    public ResponseEntity<Void> deleteMember(@RequestParam int memberNum) {
        memberService.deleteMember(memberNum);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pending")
    public List<ClubDto> getPendingClubs() {
        return clubService.findPendingClubs();
    }

    @PostMapping("/approve")
    public void approveClub(@RequestParam int clubNum) {
        clubService.updateStatus(clubNum, "승인");
    }

    @PostMapping("/reject")
    public void rejectClub(@RequestParam int clubNum) {
        clubService.deleteClub(clubNum);
    }




    // 회원 프로필 사진 요청 처리
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

    // 클럽 로고 요청 처리
    @GetMapping("/clublogo/{clubLogoName}")
    @ResponseBody
    public ResponseEntity<Resource> showClublogo(@PathVariable("clubLogoName") String clubLogoName) throws IOException {
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
}
