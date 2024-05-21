package com.ezen.springmvc.web.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
//
//    // 특정 게시판의 게시글 상세
//    @GetMapping("/{boardId}/article/{articleId}")
//    public String articleList(@PathVariable("boardId") int boardId, @PathVariable("articleId") int articleId) {
//        log.info("게시판 번호: {}, 게시글 번호: {}", boardId, articleId);
//
//        return "/board/articleRead";
//    }
//
//    // 신규 게시글 쓰기 화면
//    @GetMapping("/{boardId}/article/write")
//    public String articleWriteForm(@PathVariable("boardId") int boardId) {
//        log.info("게시판 번호: {}", boardId);
//
//        return "/board/articleWriteForm";
//    }

}









