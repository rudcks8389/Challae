package com.ezen.springmvc.domain.record_fc.service;

import com.ezen.springmvc.domain.record_fc.dto.RecordFcDto;

import java.util.List;

public interface RecordFcService {

    // 클럽 상세정보 페이지 최근 활동 ( 클럽전적 리스트 )
    public List<RecordFcDto> clubRecList(String clubNum);
}
