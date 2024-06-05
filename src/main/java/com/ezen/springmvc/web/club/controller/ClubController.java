package com.ezen.springmvc.web.club.controller;

import com.ezen.springmvc.domain.club.dto.ClubDto;
import com.ezen.springmvc.domain.common.dto.UploadFile;
import com.ezen.springmvc.domain.common.service.FileService;

import com.ezen.springmvc.domain.match.dto.MatchBoardDto;
import com.ezen.springmvc.domain.club.dto.SearchDto;
import com.ezen.springmvc.domain.club.service.ClubServiceImpl;

import com.ezen.springmvc.domain.match.dto.ClubMatchDto;
import com.ezen.springmvc.domain.match.service.CreateService;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.web.club.form.ClubRegisterForm;

import com.ezen.springmvc.domain.club.service.ClubService;
import com.ezen.springmvc.domain.community.dto.CommunityDto;
import com.ezen.springmvc.domain.community.service.CommunityService;

import com.ezen.springmvc.domain.member.service.MemberService;
import com.ezen.springmvc.web.club.form.CommunityForm;
import com.ezen.springmvc.web.common.page.Pagination;
import com.ezen.springmvc.web.common.page.ParameterForm;
import jakarta.servlet.http.HttpServletRequest;

import com.ezen.springmvc.domain.field.dto.FieldDto;
import com.ezen.springmvc.domain.match.dto.CreateDto;

import jakarta.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/club")
@Slf4j
public class ClubController {

    // 전략판(캔버스) 저장되는 경로를 변수로 지정
    @Value("${upload.soccerboard.path}")
    private String soccerBoardUploadPath;
    @Value("${upload.presetboard.path}")
    private String soccerPresetBoardUploadPath;
    // 클럽 로고
    @Value("${upload.clublogo.path}")
    private String clublogoUploadPath;
    @Autowired
    private CreateService createService;
    @Autowired
    private ClubService clubService;
    @Autowired
    private CommunityService communityService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private FileService fileService;

    // 클럽 로고 사진 요청 처리
    @GetMapping("/image/{clubLogoFileName}")
    @ResponseBody
    public ResponseEntity<Resource> showImage(@PathVariable("clubLogoFileName") String profileFileName) throws IOException {
        Path path = Paths.get(clublogoUploadPath + "/" + profileFileName);

        if (!Files.exists(path) || profileFileName == null || profileFileName.isEmpty()) {
            path = Paths.get("src/main/resources/static/img/teamlogo.jpg");
        }

        String contentType = Files.probeContentType(path);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        Resource resource = new FileSystemResource(path);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    // 전체 클럽 목록
    @GetMapping("/list")
    public String clubList(Model model) {
        List<ClubDto> clubs = clubService.clubList();
        model.addAttribute("clubs", clubs);
        return "/club/clublist";
    }

    /**
     * 내 팀보기 접속 시 해당하는 클럽정보 출력
     *
     */
    @GetMapping("/myteam")
    public String myteam(@ModelAttribute ParameterForm parameterForm, HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        MemberDto loginMember = (MemberDto) session.getAttribute("loginMember");

        if (loginMember != null) {
            String loginClubNumber = loginMember.getClubNum(); // 세션 객체에서 클럽번호 추출
            if (loginClubNumber == null) {
                return "redirect:/club/list";
            }

            String loginMemberClubStatus = loginMember.getStatus(); // 세션 객체에서 클럽상태 추출
            model.addAttribute("loginMemberStatus", loginMemberClubStatus);

            /** 로그인한 멤버의 클럽정보 출력 **/
            List<ClubDto> clubData = clubService.clubDataService(loginClubNumber);
            model.addAttribute("clubData", clubData);

            /** 로그인한 멤버의 클럽원 목록 출력 **/
            SearchDto searchDto = SearchDto.builder()
                    .limit(parameterForm.getElementSize())
                    .page(parameterForm.getRequestPage())
                    .searchValue(parameterForm.getSearchValue())
                    .build();
            List<MemberDto> clubMember = memberService.getTeamMember(loginClubNumber, searchDto);

            /** 페이지네이션을 위한 멤버목록 행에 대한 갯수 **/
            int memberListCount = memberService.getTeamMemberCount(loginClubNumber, searchDto);
            parameterForm.setRowCount(memberListCount);
            Pagination pagination = new Pagination(parameterForm);

            model.addAttribute("loginMember", loginMember);
            model.addAttribute("clubMember", clubMember);
            model.addAttribute("parameterForm", parameterForm);
            model.addAttribute("pagination", pagination);

            /** 클럽 커뮤니티 (소통공간) 데이터 출력**/
            List<CommunityDto> community = communityService.getCommunityContents(loginClubNumber);
            model.addAttribute("community", community);


            /** CommunityForm 객체를 모델에 추가**/
            CommunityForm communityForm = CommunityForm.builder().build();
            model.addAttribute("communityForm", communityForm);


            return "/club/myteam";

        } else {
            return "redirect:/"; // 로그인을 하지 않았음에도
        }
    }

    /**
     * 소통공간에 입력된 내용을 DB에 저장시키는 부분
     * @param communityForm 커뮤니티 임력 폼
     */
    @PostMapping("/myteam")
    public String inputCommDate(@ModelAttribute CommunityForm communityForm, HttpServletRequest request) {
        HttpSession session = request.getSession();
        MemberDto loginMember = (MemberDto) session.getAttribute("loginMember");
        String clubNum = loginMember.getClubNum();
        String memberNum = loginMember.getMemberNum();

        // 커뮤니티 입력시 저장
        CommunityDto inputData = CommunityDto.builder()
                .commContent(communityForm.getContent())
                .clubNum(clubNum)
                .memberNum(memberNum)
                .build();

        communityService.inputCommunity(inputData);

        return "redirect:/club/myteam";
    }


    /**
     * 클럽원 추방 (클라이언트 쪽에서는 감독한테만 보이게)
     * @param memberNum 회원번호
     */
    @PostMapping("/kick")
    public String deleteMember(@RequestParam("memberNum") String memberNum, RedirectAttributes redirectAttributes) {
        MemberDto memberDto = new MemberDto();
        memberDto.setMemberNum(memberNum);
        try {
            memberService.outClubMember(memberDto);
            redirectAttributes.addFlashAttribute("message", "회원이 성공적으로 추방되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "회원 추방에 실패했습니다.");
        }

        return "redirect:/club/myteam";
    }

    /**
     * 본인이 작성한 댓글 삭제 기능 처리
     *
     * @param commNum 댓글 번호
     */
    @PostMapping("/delete")
    public String deleteComm(@RequestParam("commNum") String commNum, RedirectAttributes redirectAttributes) {
        CommunityDto communityDto = new CommunityDto();
        communityDto.setCommNum(commNum);
        try {
            communityService.deleteCommContent(communityDto);
            redirectAttributes.addFlashAttribute("deleteMessage", "성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("deleteMessage", "삭제에 실패했습니다.");
        }

        return "redirect:/club/myteam";
    }

    /**
     * 유저의 클럽상태에 따른 페이지 안내 (승인, 대기, null...)
     */
    @GetMapping("/status")
    public ResponseEntity<?> checkClubStatus(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 기존 세션을 반환하거나 없으면 null 반환

        // 로그인한 회원 정보 가져오기
        MemberDto loginMember = (MemberDto) session.getAttribute("loginMember");

        if (loginMember.getClubNum() == null) {
            // 클럽에 가입되지 않은 경우
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("클럽에 가입되지 않음");
        }

        String clubStatus = loginMember.getStatus(); // 클럽 상태 가져오기

        if ("승인".equals(clubStatus)) {
            // 클럽 상태가 "승인"인 경우
            return ResponseEntity.ok().body("클럽 상태: 승인");
        } else {
            // 클럽 상태가 "승인"이 아닌 경우
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("클럽 상태: 승인되지 않음");
        }

    }


    // 클럽 상세보기
    @GetMapping("/detail")
    public String clubDetail() {
        return "/club/clubdetail";
    }



    /**
     * 새로운 경기 생성 하기 ( 인록 )
     * @param model
     * @param request
     * @return
     */

    @GetMapping("/create")
    public String create(Model model, HttpServletRequest request) {
        // 세션에서 클럽번호 가져오기
        HttpSession session = request.getSession();
        MemberDto member = (MemberDto) session.getAttribute("loginMember");

        /* 구장 리스트 가져오기 */
        List<FieldDto> fields = createService.getFields();
        model.addAttribute("fields", fields);
        model.addAttribute("createDto", new CreateDto());

        /* 로그인 상태일 시 클럽 번호로 클럽원 목록 보여주기( 예정 ) */
        List<MemberDto> members = createService.findByClubNum(Integer.parseInt(member.getClubNum()));  // 세션에서 받은 클럽 넘버
        model.addAttribute("clubMembers", members);

        return "/club/createMatch";
    }


    /**
     * 단순 경기 일정 생성 ( 인록 )
     * @param createDto
     * @param request
     * @return
     */

    @PostMapping("/create")
//    @ResponseBody
    public String createMatch(@ModelAttribute CreateDto createDto, HttpServletRequest request) {
        // 세션에서 클럽번호 가져오기
        HttpSession session = request.getSession();
        MemberDto member = (MemberDto) session.getAttribute("loginMember");
        Integer clubNum = (Integer) session.getAttribute("clubNum");


        log.info("세션에서 가져온 클럽 번호: {}", member.getClubNum());
        if (member.getClubNum() != null) {
            createDto.setClubNum(Integer.parseInt(member.getClubNum()));
        } else {
            log.warn("클럽 번호가 세션에 존재하지 않습니다.");
        }

//        log.info("dto: {}", createDto); // Dto 데이터 여부 체크
        /* 경기 일정 생성하기 */
        createService.createMatch(createDto);
        return "redirect:/club/create";
    }

    /**
     * 단순 경기 일정 생성 시 캔버스 이미지 등록 ( 인록 )
     * @param canvasImage
     * @return
     */
    @PostMapping("/uploadCanvas")
    @ResponseBody
    public Map<String, String> uploadCanvas(@RequestPart("canvasImage") MultipartFile canvasImage) {
        String fileName = canvasImage.getOriginalFilename();
        String filePath = soccerBoardUploadPath + canvasImage.getOriginalFilename();

        try {
            canvasImage.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.singletonMap("error", "파일 저장 실패");
        }
        return Collections.singletonMap("filePath", fileName);
    }


    /**
     * 프리셋 생성 및 저장하기 ( 인록 )
     * @param presetName
     * @param option
     * @param canvasImage
     * @param request
     * @return
     */
    @PostMapping("/createMatchBoard")
    public ResponseEntity<String> createMatchBoard(
            @RequestParam("presetName") String presetName,
            @RequestParam("option") String option,
            @RequestPart("canvasImage") MultipartFile canvasImage,
            HttpServletRequest request) {

        HttpSession session = request.getSession();
        MemberDto member = (MemberDto) session.getAttribute("loginMember");

        // 파일 저장 경로 설정
        String fileName = canvasImage.getOriginalFilename();
        String filePath = soccerPresetBoardUploadPath + fileName;

        try {
            canvasImage.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("파일 저장 실패");
        }

        // DTO 생성 및 데이터 설정
        MatchBoardDto matchBoardDto = MatchBoardDto.builder()
                .mbName(presetName)
                .mbFile(fileName)
                .clubNum(Integer.parseInt(member.getClubNum()))
                .mbType(option)
                .build();

        // DB에 동일 클럽번호의 동일 프리셋 타입이 있으면 기존에 있던 프리셋 지우기
        createService.deleteMatchBoard(Integer.parseInt(member.getClubNum()), option);
        // 새로운 프리셋 저장
        createService.createMatchBoard(matchBoardDto);

        return ResponseEntity.ok("데이터 저장 성공");
    }

    /**
     * 프리셋 캔버스 불러오기 ( 인록 )
     * @param type
     * @param session
     * @return
     */
    @GetMapping("/loadCanvas")
    public ResponseEntity<?> loadCanvas(@RequestParam String type, HttpSession session) {
        MemberDto member = (MemberDto) session.getAttribute("loginMember");
        int clubNum = Integer.parseInt(member.getClubNum());

        MatchBoardDto matchBoard = createService.loadMatchBoard(clubNum, type);
        if (matchBoard == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "해당 타입의 매치보드를 찾을 수 없습니다."));
        }

        Map<String, String> response = new HashMap<>();
        response.put("presetName", matchBoard.getMbName());
        String imageUrl = matchBoard.getMbFile();
        response.put("filePath", imageUrl);

        return ResponseEntity.ok(response);
    }

    // 새로운 클럽 생성하기

    @GetMapping("/register")
    public String clubRegister(Model model) {
        ClubRegisterForm clubRegisterForm = ClubRegisterForm.builder().build();
        model.addAttribute("clubRegisterForm", clubRegisterForm);
        return "/club/clubregister";
    }
    // 클럽 생성 요청 처리
    @PostMapping("/register")
    public String clubRegisterAction(@ModelAttribute ClubRegisterForm clubRegisterForm, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        // 로그인한 사용자 정보에서 이름 가져오기 => 클럽장
        MemberDto memberDto = (MemberDto) session.getAttribute("loginMember");
        String clubPresident = memberDto.getName();

        // 업로드 로고 사진 저장
        UploadFile uploadFile = fileService.storeFile(clubRegisterForm.getClubPhoto(), clublogoUploadPath);

        // Form Bean -> Dto 변환
        ClubDto clubDto = ClubDto.builder()
                .clubName(clubRegisterForm.getClubName())
                .clubLevel(clubRegisterForm.getClubLevel())
                .clubLocation(clubRegisterForm.getClubLocation())
                .clubPhone(clubRegisterForm.getClubPhone())
                .clubInfo(clubRegisterForm.getClubInfo())
                .clubPhoto(uploadFile.getUploadFileName())
                .clubStoredPhoto(uploadFile.getStoreFileName())
                .clubPresident(clubPresident)
                .build();

        clubService.clubRegister(clubDto);
        redirectAttributes.addFlashAttribute("clubDto", clubDto);

        return "redirect:/club/registersuccess";
    }


    // 클럽생성 완료 페이지
    @GetMapping("/registersuccess")
    public String registersuccess() {
        return "/club/clubregistersuccess";
    }

    // 클럽가입 페이지
    @GetMapping("/join")
    public String clubJoin() {
        return "/club/clubjoin";
    }

}
