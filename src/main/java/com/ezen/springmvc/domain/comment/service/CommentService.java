package com.ezen.springmvc.domain.comment.service;

import com.ezen.springmvc.domain.comment.dto.CommentDto;

import java.util.List;

public interface CommentService {

    // 댓글 생성하기
    public void register(CommentDto commentDto);

    public List<CommentDto> viewComments(int articleNum);

    public int count(int articleNum);

    public void deleteComment(int commentNum,int memberNum);

}
