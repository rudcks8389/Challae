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
    private String memberNum;
    private String id;
    private String name;
    private String email;
    private String profileImage;
    private String storedProfile;
    private String dir;
    private String backNumber;
    private String clubNum;
    private String passwd;
    private String phone;
    private String regdate;
    private String clubName;
    private String status;
}
