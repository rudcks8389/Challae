package com.ezen.springmvc.domain.member.mapper;

import com.ezen.springmvc.domain.club.dto.SearchDto;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.member.dto.MemberSearchCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Member;
import java.util.List;

//@Repository
@Mapper
public interface MemberMapper {
    public void create(MemberDto member);

    public MemberDto findById(String id);

    void update(MemberDto memberDto);

    public List<MemberDto> findByAll();

    public MemberDto findByIdAndPasswd(@Param("id") String id, @Param("passwd") String passwd);
    public List<MemberDto> findByAgeRange(@Param("begin") int begin, @Param("end") int end);
    public List<MemberDto> findByNameLike(String name);
    // 검색 타입별 회원 검색
    public List<MemberDto> findBySearchType(@Param("type") String type, @Param("value") String value);
    // 통합 검색
    public List<MemberDto> findBySearchAll(String value);
    // 통합 검색
    public List<MemberDto> findBySearchAllOption(MemberSearchCondition searchCondition);


    /**
     * myTeam을 위한 memberMapper
     * @param clubNum
     * @return
     */
    // 내 팀보기에서 쓸 멤버 클럽번호에 따른 팀원목록 출력
     public List<MemberDto> myTeamList(@Param("clubNum") String clubNum, @Param("searchDto")SearchDto searchDto); // xml로 인자 두개 이상을 받아올 때 @param으로 지정

    /** 팀원 목록 카운팅 (for pagination)**/
    public int countMyTeamList(@Param("clubNum") String clubNum,@Param("searchDto") SearchDto searchDto);

    // 팀원 목록에서 감독이 팀원 삭제하는 기능
    public void ClubMemberDelete (MemberDto memberDto);
}


