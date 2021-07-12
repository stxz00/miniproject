package org.handmade.miniproject.product.service;

import lombok.Builder;
import org.handmade.miniproject.product.dto.upload.UploadImageDTO;
import org.handmade.miniproject.product.entity.UploadImage;

public interface UploadImageService {

    default UploadImage dtoToEntity(UploadImageDTO uploadImageDTO){
        return UploadImage.builder()
                .uuid(uploadImageDTO.getUuid())
                .fileName(uploadImageDTO.getFileName())
                .main(uploadImageDTO.isMain())
                .build();
    }


}
