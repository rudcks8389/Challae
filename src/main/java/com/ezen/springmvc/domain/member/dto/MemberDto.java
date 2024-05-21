package com.ezen.springmvc.domain.member.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MemberDto {
    private String id;
    private String name;
    private String email;
    private MultipartFile profileImage;
    private String passwd;
    private String rePasswd;
    private String regdate;
}
