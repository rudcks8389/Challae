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

    @Override
    public List<CommunityDto> getCommunityContents(String clubNum) {
        return communityMapper.getClubCommunity(clubNum);
    }

}
