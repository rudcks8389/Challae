package com.ezen.springmvc.domain.article.service;

import com.ezen.springmvc.domain.article.dto.ArticleDto;
import com.ezen.springmvc.domain.common.dto.SearchDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 회원 관련 비즈니스 로직 처리 및 트랜잭션 관리
 */
public interface ArticleService {
	/**
	 * 게시판 게시글 작성
	 */
	public void register(ArticleDto articleDto);
	/**
	 * 게시판의 목록 반환
	 */
	public List<ArticleDto> getarticles(SearchDto searchDto);
	/**
	 * 게시판의 특정 게시글 보기
	 */
	public ArticleDto articleView(int articleNum);
	public void editArticle(ArticleDto article);
	/**
	 * 게시판의 본인이 작성한 게시글 삭제하기
	 */
	public void deleteArticle(int articleNum,int memberNum);
	/**
	 * 게시글 전체 갯수 반환
	 */
	public int countBySearchCondition(@Param("searchDto") SearchDto searchDto);
}
