package org.handmade.miniproject.member;

import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.member.entity.Member;
import org.handmade.miniproject.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberRepoTests {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testInsert() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .username("user"+i+"@aaa.com")
                    .userPwd("asdf1234"+i)
                    .nickname("닉네임"+i)
                    .mzipcode(13485)
                    .maddress1("주소"+i)
                    .maddress2("상세주소"+i)
                    .tel1("010")
                    .tel2("1234")
                    .tel3("5678")
                    .brno(i)
                    .del(false)
                    .build();

            memberRepository.save(member);

        });

    }

    //임의로 지정된 회원의 회원 정보 수정
    @Test
    public void testModify() {
        // TODO: 2021-07-14 사용자가 입력한 수정정보를 반영하는 기능 작성
        // DB의 내용을 고스란히 화면으로 뿌려주고 전체를 다시 저장
    }

    //임의로 지정된 회원을 탈퇴처리
    @Test
    public void testDrop() {

        Optional<Member> result = memberRepository.findById("user1@aaa.com");

        result.ifPresent( member -> {
            member.changeDel(true);
            memberRepository.save(member);
        } );
    }

    // TODO: 2021-07-14 유저별 검색 어떻게 할건지 상의 후 추가

}
