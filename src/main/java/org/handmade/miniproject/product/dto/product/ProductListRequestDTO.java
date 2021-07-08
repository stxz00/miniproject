package org.handmade.miniproject.product.dto.product;

import lombok.Data;
import org.handmade.miniproject.common.dto.ListRequestDTO;

@Data
public class ProductListRequestDTO extends ListRequestDTO {

    private String type;


}
