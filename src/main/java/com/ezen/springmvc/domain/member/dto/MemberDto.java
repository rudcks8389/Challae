package com.ezen.springmvc.domain.member.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
/**
 * 회원 DTO클래스
 */
public class MemberDto {
    private String memberNum;      /** 회원 식별번호 */
    private String id;             /** 회원 아이디 */
    private String name;           /** 회원 이름 */
    private String email;          /** 회원 이메일 */
    private String profileImage;   /** 회원 프로필이미지 */
    private String storedProfile;  /** 회원 저장용 프로필 이미지 */
    private String dir;            /** 회원의 감독여부 */
    private String clubNum;        /** 회원이 가입된 클럽의 번호 */  
    private String passwd;         /** 회원 비밀번호 */
    private String phone;          /** 회원의 전화번호 */
    private String regdate;        /** 회원의 가입일자 */
    private String clubName;       /** 회원이 가입된 클럽의 이름 */
}
