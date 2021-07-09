package org.handmade.miniproject.product.dto.category;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CategoryDTO {

    private Long cno;

    private String cname;

    private boolean cmain;

    private boolean del;

    private String uuid;

    //카테고리를 날짜별로 조회하지 않아 regDate, modDate 삭제

}
