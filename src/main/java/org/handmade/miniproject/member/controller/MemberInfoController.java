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
    @PostMapping("/join")
    public ResponseEntity<String> register(@RequestBody MemberInfoDTO dto) {

        log.info(dto);

        String userName = memberInfoService.register(dto);

        return ResponseEntity.ok(userName);

    }

    @GetMapping("/modify")
    public ResponseEntity<MemberInfoDTO> getMemberInfo(@RequestBody MemberInfoDTO dto) {
        log.info(dto);

        MemberInfoDTO memberInfoDTO = memberInfoService.modifyInfo(dto);

        return ResponseEntity.ok(memberInfoDTO);
    }

    @GetMapping("/modifyResult")
    public ResponseEntity<MemberInfoDTO> getModifyInfo() {
        return ResponseEntity.ok(memberInfoService.getMemberInfo());
    }


}
