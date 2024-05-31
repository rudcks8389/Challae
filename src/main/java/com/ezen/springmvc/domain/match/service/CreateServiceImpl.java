package com.ezen.springmvc.domain.match.service;

import com.ezen.springmvc.domain.field.dto.FieldDto;
import com.ezen.springmvc.domain.match.dto.CreateDto;
import com.ezen.springmvc.domain.match.dto.MatchBoardDto;
import com.ezen.springmvc.domain.match.mapper.CreateMapper;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CreateServiceImpl implements CreateService {

    private final CreateMapper createMapper;

    @Override
    public List<FieldDto> getFields() {
        return createMapper.findAllField();
    }

    @Override
    public void createMatch(CreateDto createDto) {
        createMapper.createMatch(createDto);
    }

    @Override
    public List<MemberDto> findByClubNum(int clubNum) {
        return createMapper.findByClubNum(clubNum);
    }

    @Override
    public void deleteMatchBoard(int clubNum, String type) {
        Map<String, Object> params = new HashMap<>();
        params.put("clubNum", clubNum);
        params.put("type", type);
        createMapper.deleteMatchBoard(params);
    }

    @Override
    public void createMatchBoard(MatchBoardDto matchBoardDto) {
        createMapper.createMatchBoard(matchBoardDto);
    }

    @Override
    public MatchBoardDto loadMatchBoard(int clubNum, String type) {
        Map<String, Object> params = new HashMap<>();
        params.put("clubNum", clubNum);
        params.put("type", type);
        return createMapper.loadMatchBoard(params);
    }
}