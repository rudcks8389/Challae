package com.ezen.springmvc.domain.match.service;

import com.ezen.springmvc.domain.field.dto.FieldDto;
import com.ezen.springmvc.domain.match.dto.CreateDto;
import com.ezen.springmvc.domain.match.mapper.CreateMapper;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<CreateDto> getMatch(int clubNum) {
        return createMapper.findClubMatch(clubNum);
    }
}