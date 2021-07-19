package org.handmade.miniproject.member.repository;

import org.handmade.miniproject.member.entity.MemberInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.reflect.Member;
import java.util.Optional;

public interface MemberInfoRepository extends JpaRepository<MemberInfo, String> {
    //username으로 회원 조회하기
    @Query("select mi from MemberInfo mi where mi.username = :username")
    MemberInfo getByUsername(String username);

    @EntityGraph(attributePaths = "memberRoleSet", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from MemberInfo m where m.username = :username ")
    Optional<MemberInfo> findByEmail(String username);
}
