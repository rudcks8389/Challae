package com.ezen.springmvc.domain.community.service;

import com.ezen.springmvc.domain.community.dto.CommunityDto;

import java.util.List;

public interface CommunityService {
    // 클럽에 따른 출력
    public List<CommunityDto> getCommunityContents(String clubNum);
    // 접속한 멤버가 입력한 데이터 들어오게
    public void inputCommunity(CommunityDto communityDto);

    public void deleteCommContent(CommunityDto communityDto);
}
