package com.ezen.springmvc.domain.record_fc.service;

import com.ezen.springmvc.domain.record_fc.dto.RecordFcDto;
import com.ezen.springmvc.domain.record_fc.mapper.RecordFcMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RecordFcServiceImpl implements RecordFcService {

    private final RecordFcMapper recordFcMapper;


    // 클럽 상세정보 페이지 최근 활동 ( 클럽전적 리스트 )
    @Override
    public List<RecordFcDto> clubRecList(String clubNum) {
        return recordFcMapper.clubRecList(clubNum);
    }
}
