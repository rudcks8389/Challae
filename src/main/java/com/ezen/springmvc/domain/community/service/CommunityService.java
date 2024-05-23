package com.ezen.springmvc.domain.community.service;

import com.ezen.springmvc.domain.community.dto.CommunityDto;

import java.util.List;

public interface CommunityService {
    public List<CommunityDto> getContents();
}
