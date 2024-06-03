package com.ezen.springmvc.domain.comment.service;

import com.ezen.springmvc.domain.article.dto.ArticleDto;
import com.ezen.springmvc.domain.comment.dto.CommentDto;
import com.ezen.springmvc.domain.comment.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;


    // 클럽 생성하기
    @Override
    public void register(CommentDto commentDto) {
        commentMapper.register(commentDto);
    }

    @Override
    public List<CommentDto> viewComments(int articleNum) {
        return commentMapper.viewComments(articleNum);
    }

    @Override
    public int count(int articleNum) {
        return commentMapper.count(articleNum);
    }

    @Override
    public void deleteComment(int commentNum,int memberNum) {
        commentMapper.deleteComment(commentNum,memberNum);
    }


}
