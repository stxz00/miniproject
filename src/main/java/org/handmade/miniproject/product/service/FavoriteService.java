package org.handmade.miniproject.product.service;

import org.handmade.miniproject.common.dto.ListRequestDTO;
import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.product.dto.Favorite.FavoriteDTO;
import org.handmade.miniproject.product.dto.Favorite.FavoriteListRequestDTO;
import org.handmade.miniproject.product.dto.Favorite.ListFavoriteDTO;
import org.handmade.miniproject.product.dto.product.ListProductDTO;
import org.handmade.miniproject.product.entity.Favorite;
import org.handmade.miniproject.product.entity.Product;

public interface FavoriteService {

    Long register(FavoriteDTO dto);
    Long delete(Long fno);
    ListResponseDTO<ListFavoriteDTO> getList(FavoriteListRequestDTO listRequestDTO);

    default FavoriteDTO entityToDTO(Favorite entity){
        return FavoriteDTO.builder()
                .fno(entity.getFno())
                .username(entity.getUsername())
                .build();
    }

    default Favorite dtoToEntity(FavoriteDTO dto, Product product){
        return Favorite.builder()
                .fno(dto.getFno())
                .username(dto.getUsername())
                .product(product)
                .build();
    }

    // 상품리스트 DTO 로 변환
    default ListFavoriteDTO arrToDTO(Object[] arr){
        Favorite favorite = (Favorite) arr[0];
        long favoriteCount = (long)arr[1];
        return ListFavoriteDTO.builder()
                .favoriteDTO(entityToDTO(favorite))
                .favoriteCount(favoriteCount)
                .build();
    }

}
