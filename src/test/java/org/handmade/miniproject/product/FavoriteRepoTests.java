package org.handmade.miniproject.product;

import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.product.entity.Favorite;
import org.handmade.miniproject.product.entity.Product;
import org.handmade.miniproject.product.repository.FavoriteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class FavoriteRepoTests {

    @Autowired
    FavoriteRepository favoriteRepository;

    @Test
    public void testInsert(){
        Product product = Product.builder().pno(1L).build();
        Favorite favorite = Favorite.builder()
                .username("구매자")
                .product(product)
                .del(false)
                .build();
        favoriteRepository.save(favorite);
    }

    @Test
    public void testIntStream(){

        IntStream.rangeClosed(1,100).forEach(i -> {
            long bno = (int)(Math.random()*60)+1;
            Product product = Product.builder().pno(bno).build();
            Favorite favorite = Favorite.builder()
                    .username("구매자"+i)
                    .product(product)
                    .del(false)
                    .build();
            favoriteRepository.save(favorite);
        });

    }

}
