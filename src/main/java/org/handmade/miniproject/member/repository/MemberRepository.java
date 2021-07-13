package org.handmade.miniproject.member.repository;

import org.handmade.miniproject.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, String> {
    // TODO: 2021-07-13 작가별 검색을 넣을건지 상의해서 MemberSearch 및 페이징 추가
}
