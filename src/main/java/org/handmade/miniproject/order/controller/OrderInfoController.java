package org.handmade.miniproject.order.controller;

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
    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody OrderInfoDTO orderInfoDTO) {

        log.info(orderInfoDTO);

        return ResponseEntity.ok(orderInfoService.register(orderInfoDTO));

    }

    // 장바구니 리스트 조회
    @GetMapping("/cartlist?user={username}")
    public ResponseEntity<ListResponseDTO<ListOrderInfoDTO>> cartList(OrderInfoListRequestDTO orderInfoListReqeustDTO, Principal principal) {
        // username을 받아옴, db가 오고가는 서버는 localhost:8080 이고 화면으로 출력하는 것은 locathost:3000이라
        // 여기서 username을 받아줘야함
        orderInfoListReqeustDTO.setUsername(principal.getName());
        return ResponseEntity.ok(orderInfoService.getCartList(orderInfoListReqeustDTO));
    }

    // 리스트 상세 정보 조회, order number로 조회하므로 cartlist, orderlist 양쪽에서 사용 가능
    @GetMapping("/details?ono={ono}")
    public ResponseEntity<OrderInfoDTO> getOrderDetail(@PathVariable Long ono) {
        return ResponseEntity.ok(orderInfoService.getListDetail(ono));
    }

    // 장바구니 삭제(=주문 완전 삭제)
    @DeleteMapping("/cartlist/delete?ono={ono}")
    public ResponseEntity<Long> deleteCart(@PathVariable Long ono) {
        return ResponseEntity.ok(orderInfoService.deleteCart(ono));
    }

    // 배송지 입력 및 결제 상태로 변경
    @PutMapping("/cartlist/payment?ono={ono}")
    public ResponseEntity<Long> payOrder(@RequestBody OrderInfoDTO orderInfoDTO) {
        return ResponseEntity.ok(orderInfoService.payOrder(orderInfoDTO));
    }

    // 주문 내역 리스트 조회
    @GetMapping("/orderlist?user={username}")
    public ResponseEntity<ListResponseDTO<ListOrderInfoDTO>> orderList(OrderInfoListRequestDTO orderInfoListRequestDTO, Principal principal) {
        orderInfoListRequestDTO.setUsername(principal.getName());
        return ResponseEntity.ok(orderInfoService.getOrderList(orderInfoListRequestDTO));
    }

    // 배송 정보 수정
    @PutMapping("/orderlist/modify?ono={ono}")
    public ResponseEntity<OrderInfoDTO> modifyOrder(@RequestBody OrderInfoDTO orderInfoDTO) {
        return ResponseEntity.ok(orderInfoService.modifyOrder(orderInfoDTO));
    }

    // 주문 취소 상태로 변경
    @PutMapping("/orderlist/cancel?ono={ono}")
    public ResponseEntity<Long> deleteOrder(@PathVariable Long ono) {
        return ResponseEntity.ok(orderInfoService.deleteOrder(ono));
    }

}
