package com.ezen.springmvc.web.article.controller;

import com.ezen.springmvc.domain.article.dto.ArticleDto;
import com.ezen.springmvc.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
@Slf4j
@RequiredArgsConstructor
public class ArticleController {

    @Autowired
    private final ArticleService articleService;

    // 게시글 목록
    @GetMapping("/list")
    public String articleList(Model model) {
        List<ArticleDto> articleList = articleService.getarticles();
        model.addAttribute("articleList", articleList);
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









