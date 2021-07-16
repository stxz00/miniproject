package org.handmade.miniproject.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.order.dto.ListOrderInfoDTO;
import org.handmade.miniproject.order.dto.OrderInfoDTO;
import org.handmade.miniproject.order.repository.OrderInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderInfoServiceImpl implements OrderInfoService {

    private final OrderInfoRepository orderInfoRepository;

    @Override
    public Long register(OrderInfoDTO orderInfoDTO) {
//        log.info(orderDTO);
//
//        Order entity = dtoToEntity(orderDTO);
//




        return null;
    }

    @Override
    public List<ListOrderInfoDTO> getOrderList() {
        return null;
    }

    @Override
    public OrderInfoDTO getOrder(Long ono) {
        return null;
    }

    @Override
    public OrderInfoDTO modifyOrder(Long ono) {
        return null;
    }

    @Override
    public Long deleteOrder(Long ono) {
        return null;
    }
}
