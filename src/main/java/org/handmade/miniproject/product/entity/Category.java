package org.handmade.miniproject.product.entity;

import lombok.*;
import org.handmade.miniproject.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "category")
@Getter
@ToString
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoIncrement primary key
    private Long cno;

    //카테고리명
    private String cname;

    //대분류 여부
    private boolean cmain;

    //예정 : 파일링크
    private String uuid;

    private boolean del;



    public void changeCname(String cname) {
        this.cname = cname;
    }

}
