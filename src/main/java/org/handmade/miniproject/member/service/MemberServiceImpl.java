package org.handmade.miniproject.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.member.dto.MemberDTO;
import org.handmade.miniproject.member.entity.Member;
import org.handmade.miniproject.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public String register(MemberDTO memberDTO) {
        log.info(memberDTO);

        Member entity = dtoToEntity(memberDTO);

        log.info("==================================================================");
        log.info(entity);

        Member result = memberRepository.save(entity);

        //등록시 가입한 유저의 ID(이메일) 반환
        return result.getUsername();
    }

}
