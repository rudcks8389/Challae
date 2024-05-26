package com.ezen.springmvc.web.article.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

    // 게시글 목록
    @GetMapping("/list")
    public String articleList() {

        return "/board/board";
    }

    // 특정 게시판의 게시글 상세
    @GetMapping("/view")
    public String articleView() {

        return "/board/articleview";
    }

    // 신규 게시글 쓰기 화면
    @GetMapping("/write")
    public String articleWriteForm() {

        return "/board/articlewriteForm";
    }

}









