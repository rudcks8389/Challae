package com.ezen.springmvc.web.club.form;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ClubJoinForm {
    // DB로 로그인 상태의 정보를 가져옴
    private Long clubNum;
    private String memberStoredProfile;
    private String memberName;
    private String memberPhone;
    private String memberEmail;

    private String memberLevel;
    private String memberInfo;
}
