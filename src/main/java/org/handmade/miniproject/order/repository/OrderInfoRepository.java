package org.handmade.miniproject.order.repository;

import org.handmade.miniproject.order.entity.OrderInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {
    // 해당 회원의 주문 현황 조회
    @Query("select o.ono, p.pname, p.price from OrderInfo o " +
            "left join o.product p left join o.memberInfo m " +
            "where o.ono>0 and o.payment = true and m.username =:username " +
            "group by o.ono order by o.ono")
    Page<Object[]> getOrderList(Pageable pageable, @Param("username") String username);

    // 해당 회원의 장바구니 조회
    @Query("select o.ono, p.pname, p.price from OrderInfo o " +
            "left join o.product p left join o.memberInfo m " +
            "where o.ono>0 and o.payment = false and m.username = :username " +
            "group by o.ono order by o.ono")
    Page<Object[]> getCartList(Pageable pageable, @Param("username") String username);

}
