package org.handmade.miniproject.member;

import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.member.dto.MemberInfoDTO;
import org.handmade.miniproject.member.entity.MemberInfo;
import org.handmade.miniproject.member.entity.MemberRole;
import org.handmade.miniproject.member.repository.MemberInfoRepository;
import org.handmade.miniproject.member.service.MemberInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberInfoRepoTests {
    @Autowired
    private MemberInfoRepository memberInfoRepository;
    @Autowired
    private MemberInfoService memberInfoService;

    @Test
    public void testInsert() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            MemberInfo memberInfo = MemberInfo.builder()
                    .username("user"+i+"@aaa.com")
                    .userPwd("asdf1234"+i)
                    .nickname("닉네임"+i)
                    .mname("이름"+i)
                    .mzipcode("13485")
                    .maddress1("주소"+i)
                    .maddress2("상세주소"+i)
                    .mtel1("010")
                    .mtel2("1234")
                    .mtel3("5678")
                    .brno("1234567890")
                    .mdel(false)
                    .build();
            memberInfo.addMemberRole(MemberRole.ADMIN);

            memberInfoRepository.save(memberInfo);

        });

    }

    //임의로 지정된 회원을 탈퇴처리
    @Test
    public void testDrop() {

        Optional<MemberInfo> result = memberInfoRepository.findById("user1@aaa.com");

        result.ifPresent(memberInfo -> {
            memberInfo.changeDel(true);
            memberInfoRepository.save(memberInfo);
        } );

    }

    //회원 찾기 샘플
    @Test
    public void testRead() {

        Optional<MemberInfo> result = memberInfoRepository.findById("user1@aaa.com");
//        log.info("======================================");
//        log.info(result.get());

        MemberInfoDTO dto = memberInfoService.entityToDTO(result.get());
        log.info("======================================");
        log.info(dto);
        log.info("======================================");

    }

}
