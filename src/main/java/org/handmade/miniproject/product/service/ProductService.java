package org.handmade.miniproject.product.service;

import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.product.dto.product.ListProductDTO;
import org.handmade.miniproject.product.dto.product.ProductDTO;
import org.handmade.miniproject.product.dto.product.ProductListRequestDTO;
import org.handmade.miniproject.product.entity.Product;

public interface ProductService {

    ListResponseDTO<ListProductDTO> getList(ProductListRequestDTO productListRequestDTO);

    default ListProductDTO arrToDTO(Object[] arr){
        Product product = (Product) arr[0];
        long favoriteCount = (long)arr[1];
        long reviewCount = (long)arr[2];
        return ListProductDTO.builder()
                .productDTO(entityToDTO(product))
                .favoriteCount(favoriteCount)
                .reviewCount(favoriteCount)
                .build();
    }

    default ProductDTO entityToDTO(Product entity){
        return ProductDTO.builder()
                .pno(entity.getPno())
                .username(entity.getUsername())
                .pcontent(entity.getPcontent())
                .price(entity.getPrice())
                .pname(entity.getPname())
                .pcount(entity.getPcount())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .uuid(entity.getUuid())
                .del(entity.isDel())
                .build();
    }
    
    default Product dtoToEntity(ProductDTO dto){
        return Product.builder()
                .pno(dto.getPno())
                .username(dto.getUsername())
                .pcontent(dto.getPcontent())
                .price(dto.getPrice())
                .pname(dto.getPname())
                .pcount(dto.getPcount())
                .uuid(dto.getUuid())
                .del(dto.isDel())
                .build();
    }
}
