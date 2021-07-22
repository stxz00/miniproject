package org.handmade.miniproject.member.entity;

import lombok.*;
import org.handmade.miniproject.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="MemberInfo")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberInfo extends BaseEntity {
    @Id
    //아이디(이메일)
    private String username;

    //패스워드
    private String password;

    //이름
    private String mname;

    //닉네임
    private String nickname;

    //우편번호
    private String mzipcode;

    //주소
    private String maddress1;

    //상세 주소
    private String maddress2;

    //전화번호 앞자리
    private String mtel1;

    //전화번호 중간자리
    private String mtel2;

    //전화번호 끝자리
    private String mtel3;

    //사업자 번호
    private String brno;

    //탈퇴 여부
    private boolean enabled;

// TODO: 2021-07-13  UploadImage 처리 어떻게 할건지 확인 후 하기 선언 수정
//    @Builder.Default
//    private Set<UploadImage> uploadImages = new HashSet<>();
//
//    public void addImage(UploadImage image) { uploadImages.add(image) }

    //회원 정보 수정을 위한 메소드
    public void changeUserPwd(String userPwd) { this.password = userPwd;  }
    public void changeMname(String mname) { this.mname = mname; }
    public void changeNickname(String nickname) {   this.nickname = nickname;   }
    public void changeMzipcode(String mzipcode) {   this.mzipcode = mzipcode;   }
    public void changeMaddress1(String maddress1) { this.maddress1 = maddress1; }
    public void changeMaddress2(String maddress2) { this.maddress2 = maddress2; }
    public void changeMtel1(String mtel1) { this.mtel1 = mtel1; }
    public void changeMtel2(String mtel2) { this.mtel2 = mtel2; }
    public void changeMtel3(String mtel3) { this.mtel3 = mtel3; }
    public void changeBrno(String brno) {   this.brno = brno;   }
    public void changeDel(boolean enabled){
        this.enabled = enabled;
    }

    @ManyToMany
    @JoinTable(
            name = "memberRole",
            joinColumns= @JoinColumn(name = "member_username"),
            inverseJoinColumns = @JoinColumn(name = "role_num"))
    private List<Role> roles = new ArrayList<Role>();

   /* @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> memberRoleSet = new HashSet<>();

    public void addMemberRole(MemberRole role){
        memberRoleSet.add(role);
    }*/
}
