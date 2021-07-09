package org.handmade.miniproject.product.service;

import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.product.dto.product.ListProductDTO;
import org.handmade.miniproject.product.dto.product.ProductDTO;
import org.handmade.miniproject.product.dto.product.ProductListRequestDTO;
import org.handmade.miniproject.product.dto.upload.UploadImageDTO;
import org.handmade.miniproject.product.entity.Product;
import org.handmade.miniproject.product.entity.UploadImage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface ProductService {

    ListResponseDTO<ListProductDTO> getList(ProductListRequestDTO productListRequestDTO);

    default ListProductDTO arrToDTO(Object[] arr){
        Product product = (Product) arr[0];
        long favoriteCount = (long)arr[1];
        long reviewCount = (long)arr[2];
        //String uuid = (String)arr[3];
        //String fileName = (String)arr[4];
        return ListProductDTO.builder()
                .productDTO(entityToDTO(product))
                .favoriteCount(favoriteCount)
                .reviewCount(reviewCount)
                //.uuid(uuid)
                //.fileName(fileName)
                .build();
    }

    default ProductDTO entityToDTO(Product entity){

        List<UploadImageDTO> imageList = entity.getUploadImages()
                .stream()
                .map(uploadImage -> UploadImageDTO.builder()
                    .uuid(uploadImage.getUuid())
                    .fileName(uploadImage.getFileName())
                    .main(uploadImage.isMain())
                    .build())
                .collect(Collectors.toList());

        return ProductDTO.builder()
                .pno(entity.getPno())
                .username(entity.getUsername())
                .pcontent(entity.getPcontent())
                .price(entity.getPrice())
                .pname(entity.getPname())
                .pcount(entity.getPcount())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .imageList(imageList)
                .del(entity.isDel())
                .build();
    }

    default Product dtoToEntity(ProductDTO dto){

        Set<UploadImage> imageSet = dto.getImageList().stream().map(uploadImageDTO -> UploadImage.builder()
                    .uuid(uploadImageDTO.getUuid())
                    .fileName(uploadImageDTO.getFileName())
                    .main(uploadImageDTO.isMain())
                    .build())
                .collect(Collectors.toSet());

        return Product.builder()
                .pno(dto.getPno())
                .username(dto.getUsername())
                .pcontent(dto.getPcontent())
                .price(dto.getPrice())
                .pname(dto.getPname())
                .pcount(dto.getPcount())
                .uploadImages(imageSet)
                .del(dto.isDel())
                .build();
    }
}
