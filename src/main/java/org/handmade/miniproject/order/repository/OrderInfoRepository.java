package org.handmade.miniproject.order.repository;

import org.handmade.miniproject.order.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {
    // 1개의 주문에 대한 정보 조회

    // 해당 회원의 총 주문 정보 조회
}
