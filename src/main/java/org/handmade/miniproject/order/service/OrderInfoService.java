package org.handmade.miniproject.order.service;

import org.handmade.miniproject.member.entity.MemberInfo;
import org.handmade.miniproject.order.dto.ListOrderInfoDTO;
import org.handmade.miniproject.order.dto.OrderInfoDTO;
import org.handmade.miniproject.order.entity.OrderInfo;
import org.handmade.miniproject.product.entity.Product;

import java.util.List;

public interface OrderInfoService {
    Long register(OrderInfoDTO orderInfoDTO);
    List<ListOrderInfoDTO> getOrderList();
    OrderInfoDTO getOrder(Long ono);
    OrderInfoDTO modifyOrder(Long ono);
    Long deleteOrder(Long ono);

    // TODO: 2021-07-16 배열에서 dto 묶음으로 변경하는 함수 확인
    // 주문 리스트를 DTO로 변환
    default ListOrderInfoDTO arrToDTO(Object[] arr) {
        Long ono = (Long) arr[0];
        String pname = (String) arr[2];
        int price = (int) arr[3];
        return ListOrderInfoDTO.builder()
                .ono(ono)
                .pname(pname)
                .price(price)
                .build();
    }

    // 주문 DTO를 Entity로 변환
    default OrderInfoDTO entityToDTO(OrderInfo entity) {
        return OrderInfoDTO.builder()
                .ono(entity.getOno())
                .pname(entity.getProduct().getPname())
                .price(entity.getProduct().getPrice())
                .oUsername(entity.getOUsername())
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
                .oUsername(dto.getOUsername())
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
