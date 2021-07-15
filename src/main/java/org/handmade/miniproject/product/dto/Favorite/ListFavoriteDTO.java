package org.handmade.miniproject.product.dto.Favorite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListFavoriteDTO {

    private FavoriteDTO favoriteDTO;

    private Long favoriteCount;
}
