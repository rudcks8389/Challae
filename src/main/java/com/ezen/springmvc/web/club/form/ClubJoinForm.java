package com.ezen.springmvc.web.club.form;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ClubJoinForm {
    private String memberAddress;
    private String memberLevel;
    private String memberInfo;
}
