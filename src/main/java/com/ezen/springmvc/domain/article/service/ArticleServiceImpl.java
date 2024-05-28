package com.ezen.springmvc.domain.article.service;

import com.ezen.springmvc.domain.article.dto.ArticleDto;
import com.ezen.springmvc.domain.article.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {
	
	private final ArticleMapper articleMapper;
	
	@Override
	@Transactional
	public void register(ArticleDto memberDto) {
		articleMapper.create(memberDto);
	}

	@Override
	public List<ArticleDto> getarticles() {
		return articleMapper.findByAll();
	}

	@Override
	public ArticleDto articleView(int articleNum) {
		return articleMapper.articleView(articleNum);
	}

	@Override
	@Transactional
	public void editArticle(ArticleDto memberDto) {
		articleMapper.update(memberDto);
	}

	@Override
	public void deleteArticle(int articleNum,int memberNum) {
		articleMapper.deleteArticle(articleNum,memberNum);
	}


}
