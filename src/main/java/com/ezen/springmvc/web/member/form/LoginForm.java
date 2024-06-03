package com.ezen.springmvc.web.member.form;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
/**
 * 로그인 폼에 대응하는 Form 클래스
 */
public class LoginForm {
    private String loginId;             /** 로그인 아이디  **/
    private String loginPasswd;        /** 회원의 비밀번호  **/
    private boolean rememberLoginId;  /** 회원의 아이디 기억하기  **/
}
