package org.handmade.miniproject.product.service;

import org.handmade.miniproject.product.dto.category.CategoryDTO;
import org.handmade.miniproject.product.dto.category.ListCategoryDTO;
import org.handmade.miniproject.product.entity.Category;

import java.util.List;

public interface CategoryService {

    List<ListCategoryDTO> getAllList();
    List<ListCategoryDTO> getMainList();

    //받아온 배열을 dto로 변환
    default ListCategoryDTO arrToDTO(Object[] arr) {

        return ListCategoryDTO.builder()
                .cno((Long)arr[0])
                .cname((String)arr[1])
                .del((boolean)arr[2])
                .uuid((String)arr[3])
                .build();
    }

    default Category dtoToEntity(CategoryDTO dto){
        return Category.builder()
                .cno(dto.getCno())
                .cname(dto.getCname())
                .uuid(dto.getUuid())
                .del(dto.isDel())
                .build();
    }

    default CategoryDTO entityToDto(Category entity){
        return  CategoryDTO.builder()
                .cno(entity.getCno())
                .cmain(entity.isCmain())
                .cname(entity.getCname())
                .build();
    }


}
