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
    private String articleAuthor;
    private String title;
    private String content;
    private String memberNum;
}

