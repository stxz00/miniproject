package org.handmade.miniproject.member.service;

import org.handmade.miniproject.member.dto.MemberInfoDTO;
import org.handmade.miniproject.member.entity.MemberInfo;

public interface MemberInfoService {

    String register(MemberInfoDTO memberInfoDTO);

    MemberInfoDTO getMemberInfo(String username);

    String modifyInfo(MemberInfoDTO dto);

    String deleteMemberInfo(String username);

    //회원의 entity를 DTO로 변환
    default MemberInfoDTO entityToDTO(MemberInfo entity) {
        //UploadImage 처리방식 상의 후 image추가

        return MemberInfoDTO.builder()
                .username(entity.getUsername())
                .userPwd(entity.getUserPwd())
                .nickname(entity.getNickname())
                .mname(entity.getMname())
                .mzipcode(entity.getMzipcode())
                .maddress1(entity.getMaddress1())
                .maddress2(entity.getMaddress2())
                .mtel1(entity.getMtel1())
                .mtel2(entity.getMtel2())
                .mtel3(entity.getMtel3())
                .brno(entity.getBrno())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .mdel(entity.isMdel())
                .build();
    }

    //회원의 DTO를 entity로 변환
    default MemberInfo dtoToEntity(MemberInfoDTO dto) {
        //UploadImage 처리방식 상의 후 image추가

        return MemberInfo.builder()
                .username(dto.getUsername())
                .userPwd(dto.getUserPwd())
                .nickname(dto.getNickname())
                .mname(dto.getMname())
                .mzipcode(dto.getMzipcode())
                .maddress1(dto.getMaddress1())
                .maddress2(dto.getMaddress2())
                .mtel1(dto.getMtel1())
                .mtel2(dto.getMtel2())
                .mtel3(dto.getMtel3())
                .brno(dto.getBrno())
                .build();
    }


}
