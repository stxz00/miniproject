package org.handmade.miniproject.member.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class MemberRefreshToken {

    @Id
    private String username;

    private String refreshStr;

    private long expireDate;
}
