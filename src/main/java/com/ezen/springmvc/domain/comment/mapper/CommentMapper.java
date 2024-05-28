package com.ezen.springmvc.domain.comment.mapper;



import com.ezen.springmvc.domain.comment.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    // 댓글 등록
    public void register(CommentDto comment);

    public List<CommentDto> viewComments(int articleNum);

    public int count(int articleNum);

    public void deleteComment(int commentNum,int memberNum);

}
