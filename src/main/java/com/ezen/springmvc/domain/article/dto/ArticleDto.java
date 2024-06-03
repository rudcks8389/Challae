package com.ezen.springmvc.domain.article.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
/**
 * 게시글 DTO클래스
 */
public class ArticleDto {
    private String articleNum;      /** 게시글 식별번호 */
    private String articleTitle;    /** 게시글 제목 */
    private String articleContent;  /** 게시글 내용 */
    private String articleDate;     /** 게시글 등록일자 */
    private String articleAuthor;   /** 게시글 작성자 아이디 */
    private String memberNum;       /** 게시글 작성자의 정보를 얻기위한 회원번호 */
}
