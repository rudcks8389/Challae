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
 * 회원 가입 폼에 대응하는 Form 클래스
 */
public class MemberForm {
    private String id;              /** 회원의 아이디  **/
    private String name;            /** 회원의 이름  **/
    private String email;           /** 회원의 이메일  **/
    private String phone;           /** 회원의 핸드폰번호  **/
    private MultipartFile profileImage;  /** 회원의 프로필 이미지  **/
    private String passwd;               /** 회원의 비밀번호  **/
    private String rePasswd;             /** 회원의 비밀번호 확인  **/
}

