package org.handmade.miniproject.product;

import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.product.entity.Category;
import org.handmade.miniproject.product.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class CategoryRepoTests {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testInsert(){
        //1~10이 될때까지 (10개 데이터) 카테고리 인서트
        IntStream.rangeClosed(1,50).forEach(i -> {
            Category category = Category.builder()
                    .cname("과자")
                    .cmain(false)
                    .del(false)
                    .build();
            categoryRepository.save(category); //실제 DB에 저장
        });

    }

    @Test //카테고리 조회
    public void testRead(){
        //Optional 조회할 때 쓰는 기능
        Optional<Category> result = categoryRepository.findById(2L);
        //result.isPresent() 결과값이 나왔을 경우 true 없으면 false
       if(result.isPresent()){
           log.info(result.get().toString());
       }
    }

    @Test
    public void testUpdate(){
        Optional<Category> result = categoryRepository.findById(2L);
        if(result.isPresent()){
            Category category = result.get();
            category.changeCname("변경된카테고리2");
            categoryRepository.save(category);
        }
    }

    @Test
    public void testDelete(){
        categoryRepository.deleteById(210L);
    }

    @Test
    public void testSearch(){
        Pageable pageable = PageRequest.of(0,10,Sort.by("cno").descending());
        log.info("=========================");
        Page<Category> result = categoryRepository.findAll(pageable);
        log.info("=========================");
        log.info(result);
        result.getContent().forEach(category -> log.info(category.toString()));
    }


    @Test
    public void testAllList() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("cno").descending());

        Page<Object[]> result = categoryRepository.getAllList(pageable);

        result.getContent().forEach(arr -> System.out.println(Arrays.toString(arr)));

    }

    @Test
    public void testMainList() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("cno").descending());

        Page<Object[]> result = categoryRepository.getMainList(pageable);

        result.getContent().forEach(arr -> System.out.println(Arrays.toString(arr)));

    }

}
