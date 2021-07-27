package org.handmade.miniproject.order.service;

import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.member.entity.MemberInfo;
import org.handmade.miniproject.order.dto.ListOrderInfoDTO;
import org.handmade.miniproject.order.dto.OrderInfoDTO;
import org.handmade.miniproject.order.dto.OrderInfoListRequestDTO;
import org.handmade.miniproject.order.entity.OrderInfo;
import org.handmade.miniproject.product.entity.Product;

public interface OrderInfoService {
    Long register(Long pno, String username);
    ListResponseDTO<ListOrderInfoDTO> getCartList(OrderInfoListRequestDTO orderInfoListRequestDTO);
    OrderInfoDTO getListDetail(Long ono);       // 장바구니 및 주문현황 리스트에서 선택한 주문 조회
    Long deleteCart(Long ono);
    Long payOrder(OrderInfoDTO orderInfoDTO);
    ListResponseDTO<ListOrderInfoDTO> getOrderList(OrderInfoListRequestDTO orderInfoListRequestDTO);
    OrderInfoDTO modifyOrder(OrderInfoDTO orderInfoDTO);
    Long deleteOrder(Long ono);

    // 주문 리스트를 DTO로 변환
    default ListOrderInfoDTO arrToDTO(Object[] arr) {

        return ListOrderInfoDTO.builder()
                .ono((Long) arr[0])
                .pname((String) arr[1])
                .price((int) arr[2])
                .oName((String)arr[3])
                .oAddress1((String)arr[4])
                .oAddress2((String)arr[5])
                .build();

    }

    // 주문 DTO를 Entity로 변환
    default OrderInfoDTO entityToDTO(OrderInfo entity) {
        return OrderInfoDTO.builder()
                .ono(entity.getOno())
                .pname(entity.getProduct().getPname())
                .price(entity.getProduct().getPrice())
                .oName(entity.getOName())
                .oZipcode(entity.getOZipcode())
                .oAddress1(entity.getOAddress1())
                .oAddress2(entity.getOAddress2())
                .oTel1(entity.getOTel1())
                .oTel2(entity.getOTel2())
                .oTel3(entity.getOTel3())
                .oRequest(entity.getORequest())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .del(entity.isDel())
                .payment(entity.isPayment())
                .build();
    }

    // 주문 Entity를 DTO로 변환
    default OrderInfo dtoToEntity(OrderInfoDTO dto, Product product, MemberInfo memberInfo) {
        return OrderInfo.builder()
                .ono(dto.getOno())
                .oName(dto.getOName())
                .oZipcode(dto.getOZipcode())
                .oAddress1(dto.getOAddress1())
                .oAddress2(dto.getOAddress2())
                .oTel1(dto.getOTel1())
                .oTel2(dto.getOTel2())
                .oTel3(dto.getOTel3())
                .oRequest(dto.getORequest())
                .del(dto.isDel())
                .payment(dto.isPayment())
                .product(product)
                .memberInfo(memberInfo)
                .build();
    }

}
