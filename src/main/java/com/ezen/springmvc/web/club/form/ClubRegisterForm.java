package com.ezen.springmvc.web.club.form;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ClubRegisterForm {
    private String clubName;
    private String clubLevel;
    private String clubLocation;
    private String clubPhone;
    private MultipartFile clubPhoto;
    private String clubInfo;
}
