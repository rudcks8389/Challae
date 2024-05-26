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
 * 회원 가입 폼에 대응하는 Form 클래스
 */
public class ArticleForm {
    private String id;
    private String name;
    private String email;
    private String phone;
    private MultipartFile profileImage;
    private String passwd;
    private String rePasswd;
}

