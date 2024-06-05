package com.ezen.springmvc.domain.community.service;

import com.ezen.springmvc.domain.community.dto.CommunityDto;
import com.ezen.springmvc.domain.community.mapper.CommunityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommunityServiceImpl implements CommunityService{

    private final CommunityMapper communityMapper;

    /**
     * 커뮤니티 입력 서비스 (데이터 저장)
     * @param clubNum 클럽번호
     */
    @Override
    public List<CommunityDto> getCommunityContents(String clubNum) {
        return communityMapper.getClubCommunity(clubNum);
    }

    /**
     * 커뮤니티 데이터 출력 서비스 (데이터 조회)
     * @param communityDto 커뮤니티Dto
     */
    @Override
    public void inputCommunity(CommunityDto communityDto) {
        communityMapper.communityCreate(communityDto);
    }

    /**
     * 커뮤니티 데이터 삭제 서비스 (데이터 삭제)
     * @param communityDto 커뮤니티Dto
     */
    @Override
    public void deleteCommContent(CommunityDto communityDto) {
        communityMapper.ClubCommDelete(communityDto);
    }

}
