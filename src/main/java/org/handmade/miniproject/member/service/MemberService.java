package org.handmade.miniproject.member.service;

import org.handmade.miniproject.member.dto.MemberDTO;
import org.handmade.miniproject.member.entity.Member;

public interface MemberService {

    // TODO: 2021-07-13  작가별 검색 넣을건지 확인해서 getMemberList추가

    String register(MemberDTO memberDTO);

    default MemberDTO entityToDTO(Member entity) {
        //UploadImage 처리방식 상의 후 image추가

        return MemberDTO.builder()
                .username(entity.getUsername())
                .userPwd(entity.getUserPwd())
                .nickname(entity.getNickname())
                .mname(entity.getMname())
                .mzipcode(entity.getMzipcode())
                .maddress1(entity.getMaddress1())
                .maddress2(entity.getMaddress2())
                .tel1(entity.getTel1())
                .tel2(entity.getTel2())
                .tel3(entity.getTel3())
                .brno(entity.getBrno())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .del(entity.isDel())
                .build();
    }

    default Member dtoToEntity(MemberDTO dto) {
        //UploadImage 처리방식 상의 후 image추가

        return Member.builder()
                .username(dto.getUsername())
                .userPwd(dto.getUserPwd())
                .nickname(dto.getNickname())
                .mname(dto.getMname())
                .mzipcode(dto.getMzipcode())
                .maddress1(dto.getMaddress1())
                .maddress2(dto.getMaddress2())
                .tel1(dto.getTel1())
                .tel2(dto.getTel2())
                .tel3(dto.getTel3())
                .brno(dto.getBrno())
                .regDate(dto.getRegDate())
                .modDate(dto.getModDate())
                .del(dto.isDel())
                .build();
    }

}
