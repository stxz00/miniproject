package org.handmade.miniproject.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.product.dto.category.CategoryDTO;
import org.handmade.miniproject.product.dto.category.CategoryListRequestDTO;
import org.handmade.miniproject.product.dto.category.ListCategoryDTO;
import org.handmade.miniproject.product.entity.Category;
import org.handmade.miniproject.product.entity.Qna;
import org.handmade.miniproject.product.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;


    @Override
    public Long register(CategoryDTO dto) {
        Category entity = dtoToEntity(dto);
        categoryRepository.save(entity);
        return entity.getCno();
    }

    @Override
    public List<ListCategoryDTO> getAllList() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("cno"));

        Page<Object[]> result = categoryRepository.getAllList(pageable);

        return result.getContent().stream().map(arr->arrToDTO(arr)).collect(Collectors.toList());
    }

    @Override
    public List<ListCategoryDTO> getMainList() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("cno"));

        Page<Object[]> result = categoryRepository.getMainList(pageable);
        return result.getContent().stream().map(arr->arrToDTO(arr)).collect(Collectors.toList());
    }
}
