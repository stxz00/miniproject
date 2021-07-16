package org.handmade.miniproject.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.product.dto.category.CategoryDTO;
import org.handmade.miniproject.product.dto.category.ListCategoryDTO;
import org.handmade.miniproject.product.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/allClist")
    public ResponseEntity<List<ListCategoryDTO>> getAllList(){
        return ResponseEntity.ok(categoryService.getAllList());
    }

    @GetMapping("/mainClist")
    public ResponseEntity<List<ListCategoryDTO>> getMainList(){
        return ResponseEntity.ok(categoryService.getMainList());
    }

    @PostMapping("/")
    public ResponseEntity<Long> register(@RequestBody CategoryDTO dto){
        return ResponseEntity.ok(categoryService.register(dto));
    }


}
