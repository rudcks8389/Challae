package com.ezen.springmvc.domain.article.mapper;

import com.ezen.springmvc.domain.article.dto.ArticleDto;
import com.ezen.springmvc.domain.common.dto.SearchDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * article 테이블 관련 Mapper
 */
@Mapper
public interface ArticleMapper {
    /**
     * 새로운 게시판 생성하기
     */
    public void create(ArticleDto article);

    /**
     * 게시판의 모든 글 목록 반환
     */
    public List<ArticleDto> findByAll(SearchDto searchDto);

    /**
     * 특정 게시판의 내용 반환 (자세히 보기)
     */
    public ArticleDto articleView(int articleNum);

    /**
     * 본인이 작성한 게시글에 대한 삭제
     */
    public void deleteArticle(int articleNum,int memberNum);

    /**
     * 특정 게시판의 검색 조건에 따른 게시글 전체 갯수 반환
     */
    public int countBySearchCondition(@Param("searchDto") SearchDto searchDto);

    /**
     * 관리자 모드에서 게시글 전체 갯수
     */
    public int articleCount();

}


