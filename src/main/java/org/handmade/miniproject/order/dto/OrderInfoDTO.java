package org.handmade.miniproject.order.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderInfoDTO {

    private Long ono;

    // 주문한 상품의 정보
    private Long pno;       // 검색용 상품 번호
    private String pname;   // 상품 이름
    private int price;      // 상품 가격

    // 주문자의 정보
    private String username;    // 주문자 아이디
    private String mName;   // 주문자 이름
    private String mZipcode;    // 주문자 우편번호
    private String mAddress1;   // 주문자 주소
    private String mAddress2;   // 주문자 상세 주소
    private String mTel1;       // 주문자 연락처 앞자리
    private String mTel2;       // 주문자 연락처 가운데자리
    private String mTel3;       // 주문자 연락처 뒷자리

    // 수령인의 정보
    private String oName;
    private String oZipcode;
    private String oAddress1;
    private String oAddress2;
    private String oTel1;
    private String oTel2;
    private String oTel3;
    private String oRequest;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private boolean del;
    private boolean payment;

}
