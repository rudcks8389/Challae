package com.ezen.springmvc.domain.article.service;

import com.ezen.springmvc.domain.article.dto.ArticleDto;

import java.util.List;

/**
 * 회원 관련 비즈니스 로직 처리 및 트랜잭션 관리
 */
public interface ArticleService {
	public void register(ArticleDto articleDto);
	public List<ArticleDto> getarticles();
	public ArticleDto articleView(int articleNum);
	public void editArticle(ArticleDto article);
	public void deleteArticle(int articleNum,int memberNum);
}
