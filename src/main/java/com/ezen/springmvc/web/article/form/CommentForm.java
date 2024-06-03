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
    private String commentAuthor;      /** 댓글 작성자 아이디 */
    private String commentContent;     /** 댓글 내용 */
    private String memberNum;          /** 댓글 작성자의 정보를 얻기위한 회원번호 */
    private String articleNum;         /** 댓글이 달린 게시글의 번호 */
}

