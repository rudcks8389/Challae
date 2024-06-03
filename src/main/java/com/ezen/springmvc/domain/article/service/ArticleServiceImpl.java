package com.ezen.springmvc.domain.article.service;

import com.ezen.springmvc.domain.article.dto.ArticleDto;
import com.ezen.springmvc.domain.article.mapper.ArticleMapper;
import com.ezen.springmvc.domain.common.dto.SearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ArticleService 구현체 */
@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {
	
	private final ArticleMapper articleMapper;


	/**
	 * 게시판 게시글 작성
	 */
	@Override
	@Transactional
	public void register(ArticleDto memberDto) {
		articleMapper.create(memberDto);
	}


	/**
	 * 게시판 목록 반환
	 */
	@Override
	public List<ArticleDto> getarticles(SearchDto searchDto) {
		return articleMapper.findByAll(searchDto);
	}

	/**
	 * 특정 게시판의 내용 반환 (자세히 보기)
	 */
	@Override
	public ArticleDto articleView(int articleNum) {
		return articleMapper.articleView(articleNum);
	}

	/**
	 * 게시판의 본인이 작성한 게시글 삭제하기
	 */
	@Override
	public void deleteArticle(int articleNum,int memberNum) {
		articleMapper.deleteArticle(articleNum,memberNum);
	}

	/**
	 * 게시글 전체 갯수 반환
	 */
	@Override
	public int countBySearchCondition(SearchDto searchDto) {
		return articleMapper.countBySearchCondition(searchDto);
	}

	/**
	 * 관리자 모드에서 게시글 전체 갯수 반환
	 */
	@Override
	public int articleCount() {
		return articleMapper.articleCount();
	}
}
