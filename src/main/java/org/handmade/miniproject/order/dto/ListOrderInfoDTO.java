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
    private Long pno;
    private String pname;
    private int price;
    private String username;
    private String mName;
    private String oName;
    private String oAddress1;
    private String oAddress2;
    private String mAddress1;
    private String mAddress2;
    private String mTel1;
    private String mTel2;
    private String mTel3;
    private String oZipcode;
    private String oTel1;
    private String oTel2;
    private String oTel3;
    private String oRequest;

}
