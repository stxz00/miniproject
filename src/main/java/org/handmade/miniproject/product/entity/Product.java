package org.handmade.miniproject.product.entity;

import lombok.*;
import org.handmade.miniproject.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "category") //ManyToOne 에 lazy 로 셋팅하였으므로 category 를 toString 에서 제외.(안그럼 조회할 때 누군지 몰라 오류남)
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    //예정 : 판매자 이메일 ManyToOne
    private String username;

    //상품이름
    private String pname;

    //상품 내용
    private String pcontent;

    //상품 가격
    private int price;

    //예정 : 조회수
    private int pcount;

    //예정 : 파일링크
    private String uuid;

    //삭제 여부
    private boolean del;

    //카테고리 소분류에 속하는 게 product 이므로 Many(product)ToOne(category) 관계가 성립됨
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY) //ManyToOne 의 관계는 eager Loading 이 기본적으로 셋팅되어서 내가 조회한 것만 하도록 Lazy loading 으로 적용
    private Category category;



}
