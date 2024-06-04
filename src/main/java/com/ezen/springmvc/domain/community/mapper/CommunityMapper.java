package com.ezen.springmvc.domain.community.mapper;

import com.ezen.springmvc.domain.community.dto.CommunityDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityMapper {
    //내용입력
    public void communityCreate(CommunityDto communityDto);
    //내용 출력
    public List<CommunityDto> getClubCommunity(String clubNum);
    //내용 삭제
    public void ClubCommDelete (CommunityDto communityDto);
}


