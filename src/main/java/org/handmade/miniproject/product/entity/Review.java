package org.handmade.miniproject.product.entity;

import lombok.*;
import org.handmade.miniproject.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "product")
@Getter
@Builder
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    //ManyToOne
    private String username;

    private String rcontent;

    private String uuid;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

}
