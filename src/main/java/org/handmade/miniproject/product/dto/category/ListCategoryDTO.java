package org.handmade.miniproject.product.dto.category;

import lombok.*;
import org.handmade.miniproject.common.dto.ListResponseDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListCategoryDTO {

    //카테고리 번호
    private Long cno;

    //카테고리 이름
    private String cname;

    //카테고리 삭제 여부
    private boolean del;

    //카테고리 이미지의 uuid
    private String uuid;

//  productCount 삭제(카테고리와 상품 리스트 페이지를 구분)
//  cmain삭제(카테고리 이미지는 1개만 사용)

}
