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
public class Favorite extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    //구매자 ManyToOne
    private String username;
    
    //찜 해제 여부
    private boolean del;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

}
