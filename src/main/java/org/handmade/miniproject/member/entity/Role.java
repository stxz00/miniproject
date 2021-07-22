package org.handmade.miniproject.member.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    //권한이름
    private String authority;

    @ManyToMany(mappedBy = "roles")
    private List<MemberInfo> memberInfos;
}
