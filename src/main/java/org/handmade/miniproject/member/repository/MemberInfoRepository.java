package org.handmade.miniproject.member.repository;

import org.handmade.miniproject.member.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberInfoRepository extends JpaRepository<MemberInfo, String> {
    //username으로 회원 조회하기
    @Query("select mi from MemberInfo mi where mi.username = :username")
    MemberInfo getByUsername(String username);
}
