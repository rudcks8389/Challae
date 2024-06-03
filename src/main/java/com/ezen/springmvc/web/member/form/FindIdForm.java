package com.ezen.springmvc.web.member.form;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
/**
 * 아이디 찾기 폼에 대응하는 Form 클래스
 */
public class FindIdForm {
    private String name;  /** 회원의 이름  **/
    private String email; /** 회원의 이메일 **/
}

