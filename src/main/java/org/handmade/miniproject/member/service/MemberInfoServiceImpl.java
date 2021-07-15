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
    private final MemberInfoService memberInfoService;

    //회원 등록
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

    //회원 정보 출력
    @Override
    public MemberInfoDTO getMemberInfo(String username) {

        Optional<MemberInfo> result = memberInfoRepository.findById(username);

        if(result.isPresent()){
            return memberInfoService.entityToDTO(result);
        }

        return null;
    }

    //회원 정보 수정
    @Override
    public String modifyInfo(MemberInfoDTO dto) {

        //dto를 entity로 변환
        MemberInfo entity = memberInfoService.dtoToEntity(dto);
        //DB에서 해당 아이디와 일치하는 레코드 검색
        Optional<MemberInfo> resEntity = memberInfoRepository.findById(entity.getUsername());

        //검색한 결과가 존재할 경우 받아온 정보를 덮어씌워 저장
        resEntity.ifPresent( memberInfo -> {
            memberInfo.changeUserPwd(entity.getUserPwd());
            memberInfo.changeNickname(entity.getNickname());
            memberInfo.changeMname(entity.getMname());
            memberInfo.changeMzipcode(entity.getMzipcode());
            memberInfo.changeMaddress1(entity.getMaddress1());
            memberInfo.changeMaddress2(entity.getMaddress2());
            memberInfo.changeMtel1(entity.getMtel1());
            memberInfo.changeMtel2(entity.getMtel2());
            memberInfo.changeMtel3(entity.getMtel3());
            memberInfo.changeBrno(entity.getBrno());

            MemberInfo result = memberInfoRepository.save(memberInfo);

            return result.getUsername();

        });

        return null;
    }

    //회원 정보 삭제
    @Override
    public String deleteMemberInfo(String username) {

        Optional<MemberInfo> member = memberInfoRepository.findById(username);

        member.ifPresent(memberInfo -> {

            memberInfo.changeDel(true);
            MemberInfo result = memberInfoRepository.save(memberInfo);

            return result.getUsername();

        });

        return null;
    }

}
