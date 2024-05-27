package com.ezen.springmvc.domain.article.mapper;

import com.ezen.springmvc.domain.article.dto.ArticleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Repository
@Mapper
public interface ArticleMapper {
    public void create(ArticleDto article);

    void update(ArticleDto articleDto);

    public List<ArticleDto> findByAll();

    public ArticleDto articleView(@Param("id") String id);

}


