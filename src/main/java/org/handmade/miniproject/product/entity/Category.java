package org.handmade.miniproject.product.entity;

import lombok.*;
import org.handmade.miniproject.common.entity.BaseEntity;
import org.handmade.miniproject.common.entity.UploadImage;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    private boolean del;

    //카테고리 이미지를 끌어오기 위해 생성

    //★★★★★★★★★★★★★★★OneToOne 의 관계★★★★★★★★★★★★★★★★★★★★★★★★
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UploadImage> categoryImages = new HashSet<>();

    public void changeCname(String cname) {
        this.cname = cname;
    }
    //★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
}
