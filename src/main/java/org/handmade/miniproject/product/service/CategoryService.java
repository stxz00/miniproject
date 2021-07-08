package org.handmade.miniproject.product.service;

import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.product.dto.category.CategoryDTO;
import org.handmade.miniproject.product.dto.category.CategoryListRequestDTO;
import org.handmade.miniproject.product.entity.Category;

public interface CategoryService {

    ListResponseDTO<CategoryDTO> getList(CategoryListRequestDTO categoryListRequestDTO);

    default Category dtoToEntity(CategoryDTO dto){
        return Category.builder()
                .cno(dto.getCno())
                .cname(dto.getCname())
                .cmain(dto.isCmain())
                .uuid(dto.getUuid())
                .del(dto.isDel())
                .build();
    }

    default CategoryDTO entityToDto(Category entity){
        return  CategoryDTO.builder()
                .cno(entity.getCno())
                .cmain(entity.isCmain())
                .cname(entity.getCname())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
    }
}
