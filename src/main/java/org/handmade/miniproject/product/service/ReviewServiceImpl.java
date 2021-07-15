package org.handmade.miniproject.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.product.dto.qna.QnaDTO;
import org.handmade.miniproject.product.dto.review.ReviewDTO;
import org.handmade.miniproject.product.entity.Product;
import org.handmade.miniproject.product.entity.Review;
import org.handmade.miniproject.product.repository.ProductRepository;
import org.handmade.miniproject.product.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    private final ProductRepository productRepository;

    private final UploadImageService uploadImageService;

    @Override
    public Long register(ReviewDTO dto) {
        log.info(dto);

        //Entity 로 변경
        Review entity = dtoToEntity(dto,productRepository.findById(dto.getPno()).get());

        log.info("========================================================");
        log.info(entity);

        //상품 등록(저장)
        Review result = reviewRepository.save(entity);

        return result.getRno();
    }

    @Override
    public Long delete(Long rno) {
        reviewRepository.deleteById(rno);
        return rno;
    }

    @Override
    public ReviewDTO read(Long rno) {
        return entityToDTO(reviewRepository.findById(rno).get());
    }

    @Override
    public Long modify(ReviewDTO dto) {
        Optional<Review> result = reviewRepository.findById(dto.getPno());

        if(result.isPresent()){
            Review entity = result.get();
            entity.changeRcontent(dto.getRcontent());
            entity.changeuploadImages(dto.getImageList()
                    .stream()
                    .map(uploadImageDTO -> uploadImageService.dtoToEntity(uploadImageDTO))
                    .collect(Collectors.toSet()));
            reviewRepository.save(entity);
            return dto.getRno();
        }
        return null;
    }
}
