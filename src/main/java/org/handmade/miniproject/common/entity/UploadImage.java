package org.handmade.miniproject.common.entity;

import lombok.*;
import org.handmade.miniproject.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class UploadImage extends BaseEntity {

    @Id
    private String uuid;

    private String fileName;

    private boolean main;


}
