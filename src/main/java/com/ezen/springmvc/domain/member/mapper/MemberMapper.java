package com.ezen.springmvc.domain.member.mapper;

import com.ezen.springmvc.domain.club.dto.SearchDto;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
<<<<<<< HEAD
=======
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;
>>>>>>> 1f11d145f106574e0d5099fde22f45caab614fcb

import java.lang.reflect.Member;
import java.util.List;

/**
 * member 테이블 관련 Mapper
 */
@Mapper
public interface MemberMapper {

    /**
     * 신규 회원가입
     */
    public void create(MemberDto member);

    /**
     * 아이디로 회원정보 얻기
     */
    public MemberDto findById(String id);

    /**
     * 회원정보 수정
     */
    void update(MemberDto memberDto);

    /**
     * 전체 회원 목록 반환
     */
    public List<MemberDto> findByAll();

    /**
     * 아이디와 비밀번호로 로그인하기
     */
    public MemberDto findByIdAndPasswd(@Param("id") String id, @Param("passwd") String passwd);
<<<<<<< HEAD

    /**
     * 이름과 이메일로 아이디 찾기
     */
    public MemberDto findId(@Param("name") String name, @Param("email") String email);
    /**
     * 관리자 모드에서 전체 회원 수 보기
     */
    public int memberCount();

    /**
     * 관리자 모드에서 모든 회원정보 출력
     */
    public List<MemberDto> viewAllMember();

    /**
     * 관리자 모드에서 회원 삭제
     */
    public int deleteMember(int memberNum);

    /**
     * 회원의 비밀번호 찾기
     */
    public MemberDto findMemberByIdNameEmail(@Param("id") String memberId, @Param("name") String name, @Param("email") String email);

    /**
     * 임시 비밀번호 설정
     */
    public void updatePassword(@Param("id") String memberId, @Param("password") String password);

    /**
     * 클럽 생성시 멤버 테이블 수정
     */
    public void updateClub(int clubNum);

    /**
     * 클럽 승인 후 감독여부 수정
     */
    public void updateMemberWithClubInfo(int clubNum);

    /**
     * 클럽 승인 후 Club_President에게 Member테이블 ClubNum부여
     */
    public void updateClubNumByPresident(int clubNum);

=======
    public List<MemberDto> findByAgeRange(@Param("begin") int begin, @Param("end") int end);
    public List<MemberDto> findByNameLike(String name);
    public List<MemberDto> findBySearchType(@Param("type") String type, @Param("value") String value);
    public List<MemberDto> findBySearchAll(String value);
    public List<MemberDto> findBySearchAllOption(MemberSearchCondition searchCondition);
>>>>>>> 1f11d145f106574e0d5099fde22f45caab614fcb


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


