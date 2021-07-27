package org.handmade.miniproject.member.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "memberInfo")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    //권한이름
    private String authority;

    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private List<MemberInfo> memberInfos;
}
