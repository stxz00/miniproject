package org.handmade.miniproject.product.dto.product;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListProductDTO {

    private ProductDTO productDTO;

    private Long favoriteCount;

    private Long reviewCount;
/*
    private String uuid;

    private String fileName;

    public String getLink(){
        return uuid+"_"+fileName;
    }
    public String getThumb(){
        return "s_"+getLink();
    }
*/
}
