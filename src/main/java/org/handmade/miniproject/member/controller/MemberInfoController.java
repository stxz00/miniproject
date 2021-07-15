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
    @PostMapping("")
    public ResponseEntity<String> register(@RequestBody MemberInfoDTO dto) {

        log.info(dto);

        String username = memberInfoService.register(dto);

        return ResponseEntity.ok(username);

    }

    @GetMapping("/modify")
    public ResponseEntity<String> getMemberInfo(@RequestBody MemberInfoDTO dto) {
        log.info(dto);

        String username = memberInfoService.modifyInfo(dto);

        return ResponseEntity.ok(username);
    }


}
