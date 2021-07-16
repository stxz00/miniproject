package org.handmade.miniproject.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListOrderInfoDTO {
    // TODO: 2021-07-16 한 사람이 여러 개의 물건을 주문할 수 있으므로 화면에 띄울 요소 확인 후 작성

    private Long ono;
    private String pname;
    private int price;

}
