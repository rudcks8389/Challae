package com.ezen.springmvc.domain.article.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ArticleDto {
    private String articleNum;
    private String articleTitle;
    private String articleContent;
    private String articleDate;
    private String articleAuthor;
    private String memberNum;
}
