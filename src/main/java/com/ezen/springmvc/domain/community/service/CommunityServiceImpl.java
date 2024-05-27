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
    // 출력
    @Override
    public List<CommunityDto> getCommunityContents(String clubNum) {
        return communityMapper.getClubCommunity(clubNum);
    }

    // 입력
    @Override
    public void inputCommunity(CommunityDto communityDto) {
        communityMapper.communityCreate(communityDto);
    }

}
