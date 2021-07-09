package org.handmade.miniproject.product.dto.qna;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class QnaDTO {

    private Long qno;

    //구매자, 판매자
    private String username;

    private String qnacontent;

    private boolean answer;

    private String uuid;

    private boolean del;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
