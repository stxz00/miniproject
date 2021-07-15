package org.handmade.miniproject.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.member.dto.MemberInfoDTO;
import org.handmade.miniproject.member.service.MemberInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberInfoController {

    private MemberInfoService memberInfoService;

    //회원가입
    @PostMapping("/signIn")
    public ResponseEntity<String> register(@RequestBody MemberInfoDTO dto) {

        log.info(dto);

        String username = memberInfoService.register(dto);

        return ResponseEntity.ok(username);

    }

    //회원 정보 수정
    @PostMapping("/modify")
    public ResponseEntity<String> modifyMemberInfo(@RequestBody MemberInfoDTO dto) {
        log.info(dto);

        String username = memberInfoService.modifyInfo(dto);

        return ResponseEntity.ok(username);
    }

    //회원 정보 조회
    @GetMapping("/{username}")
    public ResponseEntity<MemberInfoDTO> getMemberInfo(@RequestBody String username){

        MemberInfoDTO memberInfoDTO = memberInfoService.getMemberInfo(username);

        return ResponseEntity.ok(memberInfoDTO);
    }

    //회원 탈퇴(del 변경)
    @PutMapping("/{username}")
    public ResponseEntity<String> delMemberInfo(@RequestBody String username) {

        String deleteRes = memberInfoService.deleteMemberInfo(username);

        return ResponseEntity.ok(deleteRes);
    }


}
