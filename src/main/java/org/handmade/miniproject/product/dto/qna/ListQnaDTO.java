package org.handmade.miniproject.product.dto.qna;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListQnaDTO {

    private QnaDTO qnaDto;

    private Long qnaCount;

}
