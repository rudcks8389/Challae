package com.ezen.springmvc.domain.match.service;

import com.ezen.springmvc.domain.field.dto.FieldDto;
import com.ezen.springmvc.domain.match.dto.CreateDto;
import com.ezen.springmvc.domain.match.dto.MatchBoardDto;
import com.ezen.springmvc.domain.member.dto.MemberDto;

import java.util.List;

public interface CreateService {

    public List<FieldDto> getFields();
    public void createMatch(CreateDto createDto);

    public List<MemberDto> findByClubNum(int clubNum);

    public void deleteMatchBoard(int clubNum, String type);

    public void createMatchBoard(MatchBoardDto matchBoardDto);

    public MatchBoardDto loadMatchBoard(int clubNum, String type); // clubNum 만 서버에서 받아 쓰기.

}
