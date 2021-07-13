package org.handmade.miniproject.member.entity;

import lombok.*;
import org.handmade.miniproject.common.entity.BaseEntity;
import org.handmade.miniproject.product.entity.UploadImage;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member extends BaseEntity {
    @Id
    //아이디(이메일)
    private String username;

    //패스워드
    private String userPwd;

    //이름
    private String mname;

    //닉네임
    private String nickname;

    //우편번호
    private int mzipcode;

    //주소
    private String maddress1;

    //상세 주소
    private String maddress2;

    //전화번호 앞자리
    private String tel1;

    //전화번호 중간자리
    private String tel2;

    //전화번호 끝자리
    private String tel3;

    //사업자 번호
    private int brno;

    //가입 일자
    private LocalDateTime regDate;

    //회원 정보 수정 일자
    private LocalDateTime modDate;

    //탈퇴 여부
    private boolean del;

// TODO: 2021-07-13  UploadImage 처리 어떻게 할건지 확인 후 하기 선언 수정
//    @Builder.Default
//    private Set<UploadImage> uploadImages = new HashSet<>();
//
//    public void addImage(UploadImage image) { uploadImages.add(image) }

    public void changeDel(boolean del){
        this.del = del;
    }

}
