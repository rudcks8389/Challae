package com.ezen.springmvc.domain.match.service;

import com.ezen.springmvc.domain.field.dto.FieldDto;
import com.ezen.springmvc.domain.match.dto.ClubMatchDto;
import com.ezen.springmvc.domain.match.dto.CreateDto;
import com.ezen.springmvc.domain.match.dto.MatchBoardDto;
import com.ezen.springmvc.domain.match.mapper.CreateMapper;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CreateServiceImpl implements CreateService {

    private final CreateMapper createMapper;

    @Override
    public List<FieldDto> getFields() {
        return createMapper.findAllField();
    }

    @Override
    public void createMatch(CreateDto createDto) {
        createMapper.createMatch(createDto);
    }

    @Override
    public List<MemberDto> findByClubNum(int clubNum) {
        return createMapper.findByClubNum(clubNum);
    }

<<<<<<< HEAD

    /**
     * 프리셋 삭제하기 / CreateMapper 에서 parameterType 이 2개 필요하므로 Map 에 담아서 인자를 전달
     * @param clubNum
     * @param type
     */
    @Override
    public void deleteMatchBoard(int clubNum, String type) {
        Map<String, Object> params = new HashMap<>();
        params.put("clubNum", clubNum);
        params.put("type", type);
        createMapper.deleteMatchBoard(params);
    }

    @Override
    public void createMatchBoard(MatchBoardDto matchBoardDto) {
        createMapper.createMatchBoard(matchBoardDto);
    }

    /**
     * 프리셋 불러오기 / CreateMapper 에서 parameterType 이 2개 필요하므로 Map 에 담아 인자를 전달
     * @param clubNum
     * @param type
     * @return
     */
    @Override
    public MatchBoardDto loadMatchBoard(int clubNum, String type) {
        Map<String, Object> params = new HashMap<>();
        params.put("clubNum", clubNum);
        params.put("type", type);
        return createMapper.loadMatchBoard(params);
=======
    /**
     * myteam페이지 일정 랜더링
     * @param clubNum 클럽번호
     * @return
     */
    @Override
    public List<ClubMatchDto> getMatch(String clubNum) {
        return createMapper.clubMatchByClubNum(clubNum);
    }

    /**
     * 경기 상세정보 출력
     * @param matchNum 매치번호
     * @return
     */
    @Override
    public CreateDto getMachDetail(String matchNum) {
        return createMapper.clubDetailByMatchNum(matchNum);
>>>>>>> 1f11d145f106574e0d5099fde22f45caab614fcb
    }
}