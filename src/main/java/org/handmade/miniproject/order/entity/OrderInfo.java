package org.handmade.miniproject.order.entity;

import lombok.*;
import org.handmade.miniproject.common.entity.BaseEntity;
import org.handmade.miniproject.member.entity.MemberInfo;
import org.handmade.miniproject.product.entity.Product;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name="orderInfo")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"product", "MemberInfo"})
public class OrderInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ono;              // 주문 번호

    @ColumnDefault("'N'")
    private String oName;           // 받는 사람 이름

    @ColumnDefault("'N'")
    private String oZipcode;            // 받는 사람 우편번호

    @ColumnDefault("'N'")
    private String oAddress1;           // 받는 사람 주소

    @ColumnDefault("'N'")
    private String oAddress2;           // 받는 사람 상세 주소

    @ColumnDefault("'N'")
    private String oTel1;               // 받는 사람 전화번호 앞자리

    @ColumnDefault("'N'")
    private String oTel2;               // 받는 사람 전화번호 가운데자리

    @ColumnDefault("'N'")
    private String oTel3;               // 받는 사람 전화번호 뒷자리

    @ColumnDefault("'N'")
    private String oRequest;            // 배송 메모

    private boolean del;                // 주문 취소 여부
    private boolean payment;            // 결제 여부
                                        // true면 결제된 주문으로 주문 현황 페이지에,
                                        // false면 미결제 주문으로 장바구니 페이지에 뿌려짐

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;            // 상품 정보를 가져오기 위해 join

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberInfo memberInfo;      // 주문자의 정보를 가져오기 위해 join

    // 주문 정보 수정용
    public void changeOName(String oName) {  this.oName = oName;    }
    public void changeOZipcode(String oZipcode) {   this.oZipcode = oZipcode;   }
    public void changeOAddress1(String oAddress1) { this.oAddress1 = oAddress1; }
    public void changeOAddress2(String oAddress2) { this.oAddress2 = oAddress2; }
    public void changeOTel1(String oTel1) { this.oTel1 = oTel1; }
    public void changeOTel2(String oTel2) { this.oTel2 = oTel2; }
    public void changeOTel3(String oTel3) { this.oTel3 = oTel3; }
    public void changeORequest(String oRequest) { this.oRequest = oRequest; }
    public void changeDel(boolean del) {    this.del = del; }
    public void changePayment(boolean payment) {    this.payment = payment; }
}
