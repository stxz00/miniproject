package org.handmade.miniproject.product.entity;

import lombok.*;
import org.handmade.miniproject.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "product")
@Builder
@Getter
public class Qna extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qno;

    //구매자, 판매자
    private String username;

    private String qnacontent;

    private boolean answer;

    private String uuid;

    private boolean del;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

}
