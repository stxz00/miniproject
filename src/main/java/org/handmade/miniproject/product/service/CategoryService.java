package org.handmade.miniproject.product.service;

import org.handmade.miniproject.product.dto.category.CategoryDTO;
import org.handmade.miniproject.product.dto.category.ListCategoryDTO;
import org.handmade.miniproject.common.dto.upload.UploadImageDTO;
import org.handmade.miniproject.product.entity.Category;
import org.handmade.miniproject.common.entity.UploadImage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface CategoryService {

    Long register(CategoryDTO dto);
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

        Set<UploadImage> imageSet = new HashSet<>();
        for (UploadImageDTO uploadImageDTO : dto.getImageList()) {
            UploadImage build = UploadImage.builder()
                    .uuid(uploadImageDTO.getUuid())
                    .fileName(uploadImageDTO.getFileName())
                    .main(uploadImageDTO.isMain())
                    .build();
            imageSet.add(build);
        }

        return Category.builder()
                .cno(dto.getCno())
                .cname(dto.getCname())
                .categoryImages(imageSet)
                .cmain(dto.isCmain())
                .del(dto.isDel())
                .build();
    }

    default CategoryDTO entityToDto(Category entity){
        List<UploadImageDTO> imageList = entity.getCategoryImages()
                .stream()
                .map(uploadImage -> UploadImageDTO.builder()
                        .uuid(uploadImage.getUuid())
                        .fileName(uploadImage.getFileName())
                        .main(uploadImage.isMain())
                        .build())
                .collect(Collectors.toList());

        return  CategoryDTO.builder()
                .cno(entity.getCno())
                .cmain(entity.isCmain())
                .cname(entity.getCname())
                .imageList(imageList)
                .del(entity.isDel())
                .build();
    }


}
