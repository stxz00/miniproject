package org.handmade.miniproject.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.product.dto.category.CategoryDTO;
import org.handmade.miniproject.product.dto.product.ListProductDTO;
import org.handmade.miniproject.product.dto.product.ProductDTO;
import org.handmade.miniproject.product.dto.product.ProductListRequestDTO;
import org.handmade.miniproject.product.dto.qna.QnaListRequestDTO;
import org.handmade.miniproject.product.service.CategoryService;
import org.handmade.miniproject.product.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    //상품 등록과 함께 디렉터리에 업로드한 이미지 파일 연결 및 DB 저장
    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody ProductDTO productDTO){
        log.info(productDTO);
        return ResponseEntity.ok(productService.register(productDTO));
    }

    //상품 통합검색 및 검색 조건별 검색
    @GetMapping("/list")
    public ResponseEntity<ListResponseDTO<ListProductDTO>> list(int page, String cname, String keyword, String type){
        log.info("------------------------------");
        System.out.println("cname= "+ cname.equals("undefined"));
        System.out.println(page);
        ProductListRequestDTO requestDTO = new ProductListRequestDTO();
        requestDTO.setType(type);
        requestDTO.setKeyword(keyword);
        requestDTO.setPage(page);
        requestDTO.setCname(cname);
        log.info("dssdsddsfuhsdufsdjfdsjfidfds");
        return ResponseEntity.ok(productService.getList(requestDTO));
    }

    /*//상품 통합검색 및 검색 조건별 검색
    @GetMapping("/list/pages/{page}")
    public ResponseEntity<ListResponseDTO<ListProductDTO>> sss (@PathVariable  int page){
        log.info("------------------------------");
        ProductListRequestDTO requestDTO = new ProductListRequestDTO();
        requestDTO.setPage(page);
        log.info(requestDTO);
        return ResponseEntity.ok(productService.getList(requestDTO));
    }*/

    //상품 한 개 조회
    @GetMapping("/{pno}")
    public ResponseEntity<ProductDTO> read(@PathVariable Long pno){
        //log.info(pno);
        return ResponseEntity.ok(productService.read(pno));
    }

    //상품 한 개 삭제(관리자)
    @DeleteMapping("/{pno}")
    public ResponseEntity<Long> delete(@PathVariable Long pno){
        log.info(pno);
        return ResponseEntity.ok(productService.delete(pno));
    }

    //상품 수정 및 삭제 del
    @PutMapping("/{pno}")
    public ResponseEntity<Long> modify(@PathVariable Long pno, @RequestBody ProductDTO productDTO){
        productDTO.setPno(pno);
        productService.modify(productDTO);
        return ResponseEntity.ok(productDTO.getPno());
    }


}
