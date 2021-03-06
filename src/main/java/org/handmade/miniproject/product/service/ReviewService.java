package org.handmade.miniproject.product.service;

import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.product.dto.review.ListReviewDTO;
import org.handmade.miniproject.product.dto.review.ReviewDTO;
import org.handmade.miniproject.product.dto.review.ReviewListRequestDTO;
import org.handmade.miniproject.common.dto.upload.UploadImageDTO;
import org.handmade.miniproject.product.entity.Product;
import org.handmade.miniproject.product.entity.Review;
import org.handmade.miniproject.common.entity.UploadImage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface ReviewService {

    Long register(ReviewDTO dto);

    Long delete(Long rno);

    ReviewDTO read(Long rno);

    Long modify(ReviewDTO dto);

    ListResponseDTO<ListReviewDTO> getList(ReviewListRequestDTO reviewListRequestDTO);


    default Review dtoToEntity(ReviewDTO dto, Product product){

        Set<UploadImage> imageSet = new HashSet<>();
        for (UploadImageDTO uploadImageDTO : dto.getImageList()) {
            UploadImage build = UploadImage.builder()
                    .uuid(uploadImageDTO.getUuid())
                    .fileName(uploadImageDTO.getFileName())
                    .main(uploadImageDTO.isMain())
                    .build();
            imageSet.add(build);
        }

        return Review.builder()
                .rno(dto.getRno())
                .username(dto.getUsername())
                .rcontent(dto.getRcontent())
                .product(product)
                .uploadImages(imageSet)
                .build();
    };

    default ReviewDTO entityToDTO(Review entity){
        List<UploadImageDTO> imageList = entity.getUploadImages()
                .stream()
                .map(uploadImage -> UploadImageDTO.builder()
                        .uuid(uploadImage.getUuid())
                        .fileName(uploadImage.getFileName())
                        .main(uploadImage.isMain())
                        .build())
                .collect(Collectors.toList());

        return ReviewDTO.builder()
                .rno(entity.getRno())
                .username(entity.getUsername())
                .rcontent(entity.getRcontent())
                .imageList(imageList)
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
    };

    // QnA ????????? DTO ??? ??????
    default ListReviewDTO arrToDTO(Object[] arr){
        Review review = (Review) arr[0];
        long reviewCount = (long)arr[1];
        return ListReviewDTO.builder()
                .reviewDTO(entityToDTO(review))
                .reviewCount(reviewCount)
                .build();
    }
}
