package com.ezen.springmvc.domain.comment.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CommentDto {
    private String commentNum;
    private String commentContent;
    private String commentDate;
    private String memberNum;
    private String commentAuthor;
    private String articleNum;
}
