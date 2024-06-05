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
    public List<MemberDto> findBySearchType(@Param("type") String type, @Param("value") String value);
    public List<MemberDto> findBySearchAll(String value);
    public List<MemberDto> findBySearchAllOption(MemberSearchCondition searchCondition);


    /**
     * myTeam 페이지 클럽원 조회, 검색
     * @param clubNum 클럽번호
     * @return
     */
     public List<MemberDto> myTeamList(@Param("clubNum") String clubNum, @Param("searchDto")SearchDto searchDto); // xml로 인자 두개 이상을 받아올 때 @param으로 지정

    /**
     * 팀원 목록 카운팅 (for pagination)
     * @param clubNum 클럽번호
     * @param searchDto 검색 Dto
     * @return
     */
    public int countMyTeamList(@Param("clubNum") String clubNum,@Param("searchDto") SearchDto searchDto);

    /**
     * 팀원 강퇴
     * @param memberDto 회원 Dto
     */
    public void ClubMemberDelete (MemberDto memberDto);
}


