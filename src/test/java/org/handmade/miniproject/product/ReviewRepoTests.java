package org.handmade.miniproject.product;

import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.product.entity.Product;
import org.handmade.miniproject.product.entity.Review;
import org.handmade.miniproject.product.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ReviewRepoTests {

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    public void testInsert(){
        Product product = Product.builder().pno(1L).build();
        IntStream.rangeClosed(1,10).forEach(i -> {
            Review review = Review.builder()
                    .rcontent("댓글"+i)
                    .username("사용자"+i)
                    .product(product)
                    .build();

            reviewRepository.save(review);
        });
    }
}
