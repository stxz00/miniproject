package org.handmade.miniproject.product.dto.category;

import lombok.*;
import org.handmade.miniproject.common.dto.ListResponseDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListCategoryDTO {

    private CategoryDTO categoryDTO;

    private Long productCount;

}
