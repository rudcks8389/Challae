package com.ezen.springmvc.web.club.form;

import lombok.*;

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
    private String clubPhoto;
    private String clubInfo;
}
