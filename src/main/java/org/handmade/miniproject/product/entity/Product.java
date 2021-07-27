package org.handmade.miniproject.product.entity;

import lombok.*;
import org.handmade.miniproject.common.entity.BaseEntity;
import org.handmade.miniproject.common.entity.UploadImage;
import org.handmade.miniproject.member.entity.MemberInfo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private MemberInfo memberInfo;

    //상품이름
    private String pname;

    //상품 내용
    private String pcontent;

    //상품 가격
    private int price;

    //예정 : 조회수
    private int pcount;

    //삭제 여부
    private boolean del;

    //카테고리 소분류에 속하는 게 product 이므로 Many(product)ToOne(category) 관계가 성립됨
    @JoinColumn
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY) //ManyToOne 의 관계는 eager Loading 이 기본적으로 셋팅되어서 내가 조회한 것만 하도록 Lazy loading 으로 적용
    private Category category;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UploadImage> uploadImages = new HashSet<>();

    public void addImage(UploadImage image){
        uploadImages.add(image);
    }

    public void changePname(String pname){
        this.pname = pname;
    }

    public void changePcontent(String pcontent){
        this.pcontent = pcontent;
    }

    public void changePrice(int price){
        this.price = price;
    }

    public void changeDel(boolean del){
        this.del = del;
    }

    public void changeCategory(Category category){
        this.category = category;
    }

    public void changeuploadImages(Set<UploadImage> uploadImages){
        System.out.println("기존ㅑㅑㅑㅑㅑㅑㅑㅑㅑ"+this.uploadImages);
        this.uploadImages.clear();
        /*this.uploadImages = uploadImages;*/
        System.out.println("야ㅑㅑㅑㅑㅑㅑㅑㅑㅑ"+uploadImages);

        this.uploadImages.addAll(uploadImages);

        /*uploadImages.stream().map(uploadImage -> );*/


        System.out.println("뭐가 된거지?"+uploadImages);
    }

    public void addImages(Set<UploadImage> uploadImages){
        uploadImages.addAll(this.uploadImages);
        this.uploadImages.addAll(uploadImages);
    }



}
