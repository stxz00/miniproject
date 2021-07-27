package org.handmade.miniproject.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.product.dto.product.ListProductDTO;
import org.handmade.miniproject.product.dto.product.ProductDTO;
import org.handmade.miniproject.product.dto.product.ProductListRequestDTO;
import org.handmade.miniproject.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@CrossOrigin(origins = "*")
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/sellers/product")
public class SellerProductController {

    private final ProductService productService;

    //상품 등록과 함께 디렉터리에 업로드한 이미지 파일 연결 및 DB 저장
    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody ProductDTO productDTO/*, Principal principal*/){
        //productDTO.setUsername(principal.getName());
        productDTO.setUsername("dlgoska00");
        log.info(productDTO);
        return ResponseEntity.ok(productService.register(productDTO));
    }

    //상품 통합검색 및 검색 조건별 검색
    @GetMapping("/list")
    public ResponseEntity<ListResponseDTO<ListProductDTO>> list(/*Principal principal*/){
        log.info("------------------------------");


        ProductListRequestDTO requestDTO = new ProductListRequestDTO();
        requestDTO.setType("w");
        requestDTO.setKeyword("dlgoska00");
        requestDTO.setPage(1);
        log.info("dssdsddsfuhsdufsdjfdsjfidfds");
        return ResponseEntity.ok(productService.getSellerProductList(requestDTO));
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
        productDTO.setUsername("dlgoska00");
        productService.modify(productDTO);
        return ResponseEntity.ok(productDTO.getPno());
    }

    @PutMapping("/del/{pno}")
    public ResponseEntity<Long> del(@PathVariable Long pno){
        return ResponseEntity.ok(productService.modifyDel(pno));
    }

}
