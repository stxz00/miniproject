package org.handmade.miniproject.product.dto.product;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductDTO {

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

    private LocalDateTime regDate;

    private LocalDateTime modDate;





}
