package org.handmade.miniproject.member.repository;

import org.handmade.miniproject.member.entity.MemberRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRefreshTokenRepository extends JpaRepository<MemberRefreshToken, Long> {

    Optional<MemberRefreshToken> findFirstByRefreshStr(String refreshStr);
}
