package com.ezen.springmvc.web.member.form;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LoginForm {
    private String loginId;
    private String loginPasswd;
    private boolean rememberLoginId;
}
