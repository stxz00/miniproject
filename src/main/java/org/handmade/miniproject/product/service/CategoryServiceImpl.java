package org.handmade.miniproject.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.product.dto.category.CategoryDTO;
import org.handmade.miniproject.product.dto.category.CategoryListRequestDTO;
import org.handmade.miniproject.product.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;


    @Override
    public ListResponseDTO<CategoryDTO> getList(CategoryListRequestDTO categoryListRequestDTO) {




        return null;
    }
}
