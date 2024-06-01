package com.ezen.springmvc.domain.article.mapper;

import com.ezen.springmvc.domain.article.dto.ArticleDto;
import com.ezen.springmvc.domain.common.dto.SearchDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Repository
@Mapper
public interface ArticleMapper {
    public void create(ArticleDto article);

    void update(ArticleDto articleDto);

    public List<ArticleDto> findByAll(SearchDto searchDto);

    public ArticleDto articleView(int articleNum);

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


