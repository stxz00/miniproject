package org.handmade.miniproject.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.handmade.miniproject.common.entity.BaseEntity;
import org.handmade.miniproject.product.entity.Product;

import javax.persistence.*;

@Entity
@Table(name="Order")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ono;              // 주문 번호

    private String oUsername;           // 받는 사람 이름

    private String oZipcode;            // 받는 사람 우편번호

    private String oAddress1;           // 받는 사람 주소

    private String oAddress2;           // 받는 사람 상세 주소

    private boolean del;                // 주문 취소 여부

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;            // 상품 정보를 가져오기 위해 join

    // 배송 정보 수정용
    public void changeOUsername(String oUsername) {  this.oUsername = oUsername;    }
    public void changeOZipcode(String oZipcode) {   this.oZipcode = oZipcode;   }
    public void changeOAddress1(String oAddress1) { this.oAddress1 = oAddress1; }
    public void changeOAddress2(String oAddress2) { this.oAddress2 = oAddress2; }
    public void changeDel(boolean del) {    this.del = del; }

}
