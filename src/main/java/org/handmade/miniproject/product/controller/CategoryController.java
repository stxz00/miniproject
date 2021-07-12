package org.handmade.miniproject.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.product.dto.category.CategoryDTO;
import org.handmade.miniproject.product.dto.category.ListCategoryDTO;
import org.handmade.miniproject.product.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
