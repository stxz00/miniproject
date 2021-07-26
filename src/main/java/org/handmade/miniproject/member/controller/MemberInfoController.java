package org.handmade.miniproject.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.member.dto.MemberInfoDTO;
import org.handmade.miniproject.member.service.MemberInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@CrossOrigin(origins = "*")
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberInfoController {

    private final MemberInfoService memberInfoService;

    //판매자 회원가입
    @PostMapping("/customer")
    public ResponseEntity<String> customerRegister(@RequestBody MemberInfoDTO dto) {

        log.info(dto);

        String username = memberInfoService.customerRegister(dto);

        return ResponseEntity.ok(username);

    }


    //판매자 회원가입
    @PostMapping("/seller")
    public ResponseEntity<String> sellerRegister(@RequestBody MemberInfoDTO dto) {

        log.info(dto);

        String username = memberInfoService.sellerRegister(dto);

        return ResponseEntity.ok(username);

    }

    //관리자 회원가입
    @PostMapping("/admin")
    public ResponseEntity<String> adminRegister(@RequestBody MemberInfoDTO dto) {

        log.info(dto);

        String username = memberInfoService.adminRegister(dto);

        return ResponseEntity.ok(username);

    }

    //회원 정보 수정
    @PutMapping("/modify")
    public ResponseEntity<String> modifyMemberInfo(@RequestBody MemberInfoDTO dto) {
        log.info(dto);

        String username = memberInfoService.modifyInfo(dto);

        return ResponseEntity.ok(username);
    }


    //회원 정보 조회
    @GetMapping("/info")
    public ResponseEntity<MemberInfoDTO> getMemberInfo(String username){

        MemberInfoDTO memberInfoDTO = memberInfoService.getMemberInfo(username);

        return ResponseEntity.ok(memberInfoDTO);
    }

    //회원 탈퇴(del 변경)
    @PutMapping("/delete")
    public ResponseEntity<String> delMemberInfo(String username) {

        String deleteRes = memberInfoService.deleteMemberInfo(username);

        return ResponseEntity.ok(deleteRes);
    }


}
