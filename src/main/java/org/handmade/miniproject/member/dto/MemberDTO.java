package org.handmade.miniproject.member.dto;

import lombok.*;
import org.handmade.miniproject.product.dto.upload.UploadImageDTO;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDTO {

    //아이디(이메일)
    private String username;

    //패스워드
    private String userPwd;

    //닉네임
    private String nickname;

    //이름
    private String mname;

    //우편번호
    private int mzipcode;

    //주소
    private String maddress1;

    //상세 주소
    private String maddress2;

    //전화번호 앞자리, 0으로 시작해서 String 처리
    private String tel1;

    //전화번호 중간자리
    private String tel2;

    //전화번호 끝자리
    private String tel3;

    //사업자 번호
    private int brno;

// TODO: 2021-07-13 프로필 사진은 한 장? 여러 장? 확인 후 하기 선언 수정
//    //프로필 사진
//    private List<UploadImageDTO> imageList;

    //가입 일자
    private LocalDateTime regDate;

    //회원 정보 수정 일자
    private LocalDateTime modDate;

    //탈퇴 여부
    private boolean del;

}
