package org.handmade.miniproject.product.service;

import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.member.entity.MemberInfo;
import org.handmade.miniproject.product.dto.product.ListProductDTO;
import org.handmade.miniproject.product.dto.product.ProductDTO;
import org.handmade.miniproject.product.dto.product.ProductListRequestDTO;
import org.handmade.miniproject.common.dto.upload.UploadImageDTO;
import org.handmade.miniproject.product.entity.Category;
import org.handmade.miniproject.product.entity.Product;
import org.handmade.miniproject.common.entity.UploadImage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface ProductService {

    ListResponseDTO<ListProductDTO> getList(ProductListRequestDTO productListRequestDTO);

    ListResponseDTO<ListProductDTO> getSellerProductList(ProductListRequestDTO productListRequestDTO);

    Long register(ProductDTO productDTO);

    Long delete(Long pno);

    ProductDTO read(Long pno);

    String modify(ProductDTO productDTO);

    Long modifyDel(Long pno);

    String nickname(Long pno);

    // 상품리스트 DTO 로 변환
    default ListProductDTO arrToDTO(Object[] arr){
        Product product = (Product) arr[0];
        long favoriteCount = (long)arr[1];
        long reviewCount = (long)arr[2];
        return ListProductDTO.builder()
                .productDTO(entityToDTO(product))
                .favoriteCount(favoriteCount)
                .reviewCount(reviewCount)
                .build();
    }

    //상품 엔티티를 DTO 로 변환
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

    //상품 dto 를 엔티티로 변환. 카테고리는 dto 의 getCno 및 findById 를 통해 가져오기
   default Product dtoToEntity(ProductDTO dto, Category category, MemberInfo memberInfo){
        Set<UploadImage> imageSet = new HashSet<>();
        if(dto.getImageList() != null) {
            for (UploadImageDTO uploadImageDTO : dto.getImageList()) {
                UploadImage build = UploadImage.builder()
                        .uuid(uploadImageDTO.getUuid())
                        .fileName(uploadImageDTO.getFileName())
                        .main(uploadImageDTO.isMain())
                        .build();
                imageSet.add(build);
            }
        }

        return Product.builder()
                .pno(dto.getPno())
                .pcontent(dto.getPcontent())
                .price(dto.getPrice())
                .pname(dto.getPname())
                .pcount(dto.getPcount())
                .uploadImages(imageSet)
                .del(dto.isDel())
                .memberInfo(memberInfo)
                .category(category)
                .build();
    }
}
