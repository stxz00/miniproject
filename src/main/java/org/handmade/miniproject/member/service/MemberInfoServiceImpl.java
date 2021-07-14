package org.handmade.miniproject.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.member.dto.MemberInfoDTO;
import org.handmade.miniproject.member.entity.MemberInfo;
import org.handmade.miniproject.member.repository.MemberInfoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberInfoServiceImpl implements MemberInfoService {

    private final MemberInfoRepository memberInfoRepository;

    @Override
    public String register(MemberInfoDTO memberInfoDTO) {
        log.info(memberInfoDTO);

        MemberInfo entity = dtoToEntity(memberInfoDTO);

        log.info("==============================");
        log.info(entity);

        MemberInfo result = memberInfoRepository.save(entity);

        //등록시 가입한 유저의 ID(이메일) 반환
        return result.getUsername();
    }

    //멤버 정보 수정
    @Override
    public MemberInfoDTO modifyInfo(MemberInfoDTO dto) {
//        log.info(dto);
//
//        MemberInfo entity = dtoToEntity(dto);
//        String username = entity.getUsername();
//
//        log.info("==============================");
//        log.info(entity);
//
//        Optional<MemberInfo> searchRes = memberInfoRepository.findById(username);
//
//        searchRes.isPresent(
//
//                );
//
//
//
//
//
//        MemberInfo result = memberInfoRepository.save(entity);

        return null;
    }

    //멤버 정보 출력
    @Override
    public MemberInfoDTO getMemberInfo() {
        return null;
    }



}
