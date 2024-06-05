package com.ezen.springmvc.web.club.form;


import lombok.*;

/**
 * 커뮤니티 (소통공간) 입력을 위한 form
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CommunityForm {
    private String content;
}
