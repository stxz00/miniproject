package org.handmade.miniproject.product.dto.category;

import lombok.*;
import org.handmade.miniproject.common.dto.upload.UploadImageDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CategoryDTO {

    private Long cno;

    private String cname;

    private boolean cmain;

    private boolean del;

    private List<UploadImageDTO> imageList;

}
