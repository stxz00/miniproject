package org.handmade.miniproject.product.dto.product;

import lombok.*;
import org.handmade.miniproject.common.dto.upload.UploadImageDTO;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProductDTO {

    private Long pno;

    private String username;

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

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private List<UploadImageDTO> imageList;

    private Long cno;

    private String cname;


}
