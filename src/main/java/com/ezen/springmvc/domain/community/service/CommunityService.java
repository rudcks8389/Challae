package com.ezen.springmvc.domain.community.service;

import com.ezen.springmvc.domain.community.dto.CommunityDto;

import java.util.List;

public interface CommunityService {

    /**
     * 커뮤니티 입력 서비스 (데이터 저장)
     * @param communityDto 커뮤니티Dto
     */
    public void inputCommunity(CommunityDto communityDto);

    /**
     * 커뮤니티 데이터 출력 서비스 (데이터 조회)
     * @param clubNum 클럽번호
     * @return
     */
    public List<CommunityDto> getCommunityContents(String clubNum);

    /**
     * 커뮤니티 데이터 삭제 서비스 (데이터 삭제)
     * @param communityDto 커뮤니티Dto
     */
    public void deleteCommContent(CommunityDto communityDto);
}
