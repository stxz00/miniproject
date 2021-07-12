package org.handmade.miniproject.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.product.dto.category.CategoryDTO;
import org.handmade.miniproject.product.dto.product.ListProductDTO;
import org.handmade.miniproject.product.dto.product.ProductDTO;
import org.handmade.miniproject.product.dto.product.ProductListRequestDTO;
import org.handmade.miniproject.product.service.CategoryService;
import org.handmade.miniproject.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    //상품 등록과 함께 디렉터리에 업로드한 이미지 파일 연결 및 DB 저장
    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody ProductDTO productDTO, CategoryDTO categoryDTO){
        log.info(productDTO);
        log.info(categoryDTO);
        return ResponseEntity.ok(productService.register(productDTO));
    }

    @GetMapping("/list")
    public ResponseEntity<ListResponseDTO<ListProductDTO>> list(ProductListRequestDTO requestDTO){
        log.info("------------------------------");
        return ResponseEntity.ok(productService.getList(requestDTO));
    }

}
