package org.handmade.miniproject.order.controller;

import com.querydsl.core.types.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.order.dto.ListOrderInfoDTO;
import org.handmade.miniproject.order.dto.OrderInfoDTO;
import org.handmade.miniproject.order.dto.OrderInfoListRequestDTO;
import org.handmade.miniproject.order.service.OrderInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@Log4j2
public class OrderInfoController {

    private final OrderInfoService orderInfoService;

    // 주문 정보 등록
    @PostMapping("/register/{pno}")
    public ResponseEntity<Long> register(@PathVariable long pno/*,Principal principal*/) {

        /* String username = principal.getNama();*/

        String username = "dlgoska00";

        return ResponseEntity.ok(orderInfoService.register(pno,username));

    }

    // 장바구니 리스트 조회
    @GetMapping("/cartlist")
    public ResponseEntity<ListResponseDTO<ListOrderInfoDTO>> cartList(String username/*, int page*/) {
        OrderInfoListRequestDTO requestDTO = new OrderInfoListRequestDTO();
        requestDTO.setUsername(username);
        /*requestDTO.setPage(page);*/
        return ResponseEntity.ok(orderInfoService.getCartList(requestDTO));
    }

    // 리스트 상세 정보 조회, order number로 조회하므로 cartlist, orderlist 양쪽에서 사용 가능
    @GetMapping("/details")
    public ResponseEntity<OrderInfoDTO> getOrderDetail(Long ono) {
        return ResponseEntity.ok(orderInfoService.getListDetail(ono));
    }

    // 장바구니 삭제(=주문 완전 삭제)
    @DeleteMapping("/cartlist/delete")
    public ResponseEntity<Long> deleteCart(Long ono) {
        return ResponseEntity.ok(orderInfoService.deleteCart(ono));
    }

    // 배송지 입력 및 결제 상태로 변경
    @PutMapping("/cartlist/payment")
    public ResponseEntity<Long> payOrder(@RequestBody OrderInfoDTO orderInfoDTO) {
        return ResponseEntity.ok(orderInfoService.payOrder(orderInfoDTO));
    }

    // 주문 내역 리스트 조회
    @GetMapping("/orderlist")
    public ResponseEntity<ListResponseDTO<ListOrderInfoDTO>> orderList(String username/*OrderInfoListRequestDTO orderInfoListRequestDTO*//* Principal principal*/) {
        OrderInfoListRequestDTO orderInfoListRequestDTO = new OrderInfoListRequestDTO();
        orderInfoListRequestDTO.setUsername("dlgoska00"); /*principal.getName()*/
        return ResponseEntity.ok(orderInfoService.getOrderList(orderInfoListRequestDTO));
    }

    // 배송 정보 수정
    @PutMapping("/orderlist/modify")
    public ResponseEntity<OrderInfoDTO> modifyOrder(@RequestBody OrderInfoDTO orderInfoDTO) {
        return ResponseEntity.ok(orderInfoService.modifyOrder(orderInfoDTO));
    }

    // 주문 취소 상태로 변경
    @PutMapping("/orderlist/cancel")
    public ResponseEntity<Long> deleteOrder(Long ono) {
        return ResponseEntity.ok(orderInfoService.deleteOrder(ono));
    }

}
