package com.ezen.springmvc.web.article.form;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
/**
 * 게시글 작성 폼에 대응하는 Form 클래스
 */
public class ArticleForm {
    private String articleAuthor;  /** 게시글 작성자 아이디 */
    private String title;          /** 게시글 제목 */
    private String content;        /** 게시글 내용 */
    private String memberNum;      /** 게시글 작성자의 정보를 얻기위한 회원번호 */
}


