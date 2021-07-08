package org.handmade.miniproject.product.dto.product;

import lombok.*;
import org.handmade.miniproject.common.dto.ListResponseDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListProductDTO     {

    private ProductDTO productDTO;

    private Long favoriteCount;

    private Long reviewCount;

}
