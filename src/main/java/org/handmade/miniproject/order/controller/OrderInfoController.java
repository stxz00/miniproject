package org.handmade.miniproject.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.order.service.OrderInfoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@Log4j2
public class OrderInfoController {

    private final OrderInfoService orderInfoService;

    // 주문 정보 등록

    // 장바구니 리스트 조회

    // 장바구니 리스트 상세 정보 조회

    // 장바구니 리스트 상세 정보 수정

    // 장바구니 삭제(=주문 완전 삭제)

    // 결제 상태로 변경

    // 주문 내역 리스트 조회

    // 주문 내역 리스트 상세 조회

    // 주문 취소 상태로 변경

}
