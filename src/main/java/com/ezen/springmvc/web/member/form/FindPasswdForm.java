package com.ezen.springmvc.web.member.form;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
/**
 * 비밀번호 찾기 폼에 대응하는 Form 클래스
 */
public class FindPasswdForm {
    private String id;       /** 회원의 아이디  **/
    private String name;     /** 회원의 이름  **/
    private String email;    /** 회원의 이메일  **/
}

