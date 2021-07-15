package org.handmade.miniproject.order.service;

import org.handmade.miniproject.order.dto.ListOrderDTO;
import org.handmade.miniproject.order.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    Long register(OrderDTO orderDTO);
    List<ListOrderDTO> getOrderList();
    OrderDTO getOrder(Long ono);
    OrderDTO modifyOrder(Long ono);
    Long deleteOrder(Long ono);

    //arrToDTO
    //dtoToEntity
    //entityToDTO

}
