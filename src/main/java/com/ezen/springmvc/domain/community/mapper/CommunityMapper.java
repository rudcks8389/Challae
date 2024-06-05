package com.ezen.springmvc.domain.community.mapper;

import com.ezen.springmvc.domain.community.dto.CommunityDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityMapper {
    /**
     *  커뮤니티(소통공간) 입력 시 데이터 생성
     * @param communityDto 커뮤니티 Dto
     */
    public void communityCreate(CommunityDto communityDto);

    /**
     * 클럽번호에 따른 클럽소통공간 구성
     * @param clubNum 클럽번호
     */
    public List<CommunityDto> getClubCommunity(String clubNum);

    /**
     * 커뮤니티(소통공간)데이터 삭제
     * @param communityDto 커뮤니티 Dto
     */
    public void ClubCommDelete (CommunityDto communityDto);
}


