package com.ezen.springmvc.web.article.controller;

import com.ezen.springmvc.domain.article.dto.ArticleDto;
import com.ezen.springmvc.domain.article.service.ArticleService;
import com.ezen.springmvc.domain.comment.dto.CommentDto;
import com.ezen.springmvc.domain.comment.service.CommentService;
import com.ezen.springmvc.domain.common.dto.SearchDto;
import com.ezen.springmvc.domain.common.dto.UploadFile;
import com.ezen.springmvc.domain.field.dto.FieldDto;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.web.article.form.ArticleForm;
import com.ezen.springmvc.web.article.form.CommentForm;
import com.ezen.springmvc.web.common.page.Pagination;
import com.ezen.springmvc.web.common.page.ParameterForm;
import com.ezen.springmvc.web.member.form.MemberForm;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
@Slf4j
@RequiredArgsConstructor
public class ArticleController {

    @Autowired
    private final ArticleService articleService;

    @Autowired
    private final CommentService commentService;

    /** 전체 게시판 목록 반환 API **/
    @GetMapping("/list")
    public String articleList(@ModelAttribute ParameterForm parameterForm, Model model) {
        SearchDto searchDto = SearchDto.builder()
                .limit(parameterForm.getElementSize())
                .page(parameterForm.getRequestPage())
                .searchValue(parameterForm.getSearchValue())
                .build();

        List<ArticleDto> articleList = articleService.getarticles(searchDto);

        Map<Integer, Integer> commentCounts = new HashMap<>();
        for (ArticleDto article : articleList) {
            int articleNum = Integer.parseInt(article.getArticleNum());
            int commentCount = commentService.count(articleNum);
            commentCounts.put(articleNum, commentCount);
        }

        int totalArticles = articleService.countBySearchCondition(searchDto);
        parameterForm.setRowCount(totalArticles);

        Pagination pagination = new Pagination(parameterForm);

        model.addAttribute("articleList", articleList);
        model.addAttribute("commentCounts", commentCounts);
        model.addAttribute("parameterForm", parameterForm);
        model.addAttribute("pagination", pagination);
        return "/board/board";
    }

    /** 게시판 상세보기 API **/
    @GetMapping("/view")
    public String articleView(@RequestParam("articleNum") int articleNum, Model model) {
        ArticleDto articledetail = articleService.articleView(articleNum);
        List<CommentDto> comments = (List<CommentDto>) commentService.viewComments(articleNum);
        model.addAttribute("articledetail", articledetail);
        model.addAttribute("comments", comments);
        return "/board/articleview";
    }

    /** 본인이 작성한 게시판 삭제 API **/
    @PostMapping("/deleteArticle")
    public String deleteArticle(HttpSession session, @RequestParam("articleNum") int articleNum) {
        // 로그인한 사용자 정보에서 작성자 ID 가져오기
        MemberDto member = (MemberDto) session.getAttribute("loginMember");
        int memberNum = Integer.parseInt(member.getMemberNum());

        // 게시글 삭제 서비스 호출
        articleService.deleteArticle(articleNum,memberNum);

        // 삭제 후 리스트 페이지로 리다이렉트
        return "redirect:/board/list";
    }

    /** 댓글을 작성하는 API **/
    @PostMapping("/comment")
    public String registerCommentAction(@ModelAttribute CommentForm commentForm, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        // 로그인한 사용자 정보에서 작성자 ID 가져오기
        MemberDto member = (MemberDto) session.getAttribute("loginMember");
        String author = member.getId();
        String memberNum = member.getMemberNum();

        // Form -> Dto 변환
        CommentDto commentDto = CommentDto.builder()
                .commentAuthor(author)
                .memberNum(memberNum)
                .commentContent(commentForm.getCommentContent())
                .articleNum(commentForm.getArticleNum())
                .build();
        commentService.register(commentDto);
        redirectAttributes.addFlashAttribute("commentDto", commentDto);

        return "redirect:/board/list";
    }


    /** 본인이 작성한 댓글 삭제 API **/
    @PostMapping("/delete")
    public String deleteComment(HttpSession session, @RequestParam("commentNum") int commentNum) {
        // 로그인한 사용자 정보에서 작성자 ID 가져오기
        MemberDto member = (MemberDto) session.getAttribute("loginMember");
        int memberNum = Integer.parseInt(member.getMemberNum());

        commentService.deleteComment(commentNum, memberNum);
        return "redirect:/board/list";
    }

    /** 새로운 게시판 작성 API **/
    @GetMapping("/write")
    public String articleWriteForm(Model model) {
        ArticleForm articleForm = ArticleForm.builder().build();
        model.addAttribute("articleForm", articleForm);
        return "/board/articlewriteForm";
    }

    /** 새로운 게시판 작성 API **/
    @PostMapping("/write")
    public String signUpAction(@ModelAttribute ArticleForm articleForm, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        // 로그인한 사용자 정보에서 작성자 ID 가져오기
        MemberDto member = (MemberDto) session.getAttribute("loginMember");
        String author = member.getId();
        String memberNum = member.getMemberNum();

        // Form -> Dto 변환
        ArticleDto articleDto = ArticleDto.builder()
                .articleAuthor(author)
                .memberNum(memberNum)
                .articleTitle(articleForm.getTitle())
                .articleContent(articleForm.getContent())
                .build();
        articleService.register(articleDto);
        redirectAttributes.addFlashAttribute("articleDto", articleDto);

        return "redirect:/board/list";
    }

}









