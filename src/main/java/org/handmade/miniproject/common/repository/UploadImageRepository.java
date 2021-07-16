package org.handmade.miniproject.common.repository;

import org.handmade.miniproject.common.entity.UploadImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadImageRepository extends JpaRepository<UploadImage,Long> {
}
