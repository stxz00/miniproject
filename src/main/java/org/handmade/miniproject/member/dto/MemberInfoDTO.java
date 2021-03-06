package org.handmade.miniproject.member.dto;

import lombok.*;
import org.handmade.miniproject.member.entity.Role;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberInfoDTO {

    //아이디(이메일)
    private String username;

    //패스워드
    private String password;

    //닉네임
    private String nickname;

    //이름
    private String mname;

    //우편번호
    private String mzipcode;

    //주소
    private String maddress1;

    //상세 주소
    private String maddress2;

    //전화번호 앞자리, 0으로 시작해서 String 처리
    private String mtel1;

    //전화번호 중간자리
    private String mtel2;

    //전화번호 끝자리
    private String mtel3;

    //사업자 번호
    private String brno;

    //가입 일자
    private LocalDateTime regDate;

    //회원 정보 수정 일자
    private LocalDateTime modDate;

    //탈퇴 여부
    private boolean enabled;

    private List<Role> roles = new ArrayList<Role>();

}
