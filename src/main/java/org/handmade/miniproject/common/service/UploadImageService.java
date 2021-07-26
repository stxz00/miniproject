package org.handmade.miniproject.common.service;

import org.handmade.miniproject.common.dto.upload.UploadImageDTO;
import org.handmade.miniproject.common.entity.UploadImage;

public interface UploadImageService {

    default UploadImage dtoToEntity(UploadImageDTO uploadImageDTO){
        System.out.println("sssssssssssssssssssss"+uploadImageDTO);

        return UploadImage.builder()
                .uuid(uploadImageDTO.getUuid())
                .fileName(uploadImageDTO.getFileName())
                .main(uploadImageDTO.isMain())
                .build();
    }

}
