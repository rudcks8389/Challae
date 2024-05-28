package com.ezen.springmvc.web.article.form;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
/**
 * 댓글 작성 폼에 대응하는 Form 클래스
 */
public class CommentForm {
    private String commentAuthor;
    private String commentContent;
    private String memberNum;
    private String articleNum;
}

