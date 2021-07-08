package org.handmade.miniproject.product.dto.category;

import lombok.*;
import org.handmade.miniproject.common.dto.ListRequestDTO;

@Data
public class CategoryListRequestDTO extends ListRequestDTO {

    private String type;
}
