package org.handmade.miniproject.product;

import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.member.entity.MemberInfo;
import org.handmade.miniproject.product.entity.Category;
import org.handmade.miniproject.product.entity.Product;
import org.handmade.miniproject.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ProductRepoTests {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testInsert(){
        //카테고리의 1번에 참조하여 product 를 insert 할 예정
        Category category = Category.builder().cno(1L).build();
        MemberInfo memberInfo = MemberInfo.builder().username("user100@aaa.com").build();

        Product product = Product.builder()
                .pname("상품명")
                .pcontent("상품내용")
                .price(20000)
                .memberInfo(memberInfo)
                .del(false)
                .category(category) //카테고리 cno 1번을 참조
                .build();
        productRepository.save(product);
    }

    @Test
    public void testIntStream(){
        IntStream.rangeClosed(1,100).forEach(i ->{
            long cno = (int)(Math.random()*50)+1;
            log.info("======================");
            log.info("cno: "+cno+", i: "+i);
            Category category = Category.builder().cno(cno).build();
            MemberInfo memberInfo = MemberInfo.builder().username("user100@aaa.com").build();

            Product product = Product.builder()
                    .pname("상품명"+i)
                    .pcontent("상품내용"+i)
                    .price(20000)
                    .memberInfo(memberInfo)
                    .del(false)
                    .category(category) //카테고리 cno 1번을 참조
                    .build();

            productRepository.save(product);

        });
    }

    @Test
    public void testPage(){
        Category category = Category.builder().cno(1L).build();
        Pageable pageable = PageRequest.of(0,10);
        Page<Product> result = productRepository.getByCategory(category, pageable);

        result.getContent().forEach(product ->  log.info(product.toString()));
    }




}
