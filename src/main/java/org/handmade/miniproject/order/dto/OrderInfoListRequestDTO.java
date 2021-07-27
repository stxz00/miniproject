package org.handmade.miniproject.order.dto;

import lombok.Data;
import org.handmade.miniproject.common.dto.ListRequestDTO;

@Data
public class OrderInfoListRequestDTO extends ListRequestDTO {

    private String username;
}
