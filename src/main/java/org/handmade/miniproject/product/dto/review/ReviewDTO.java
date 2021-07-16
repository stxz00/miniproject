package org.handmade.miniproject.product.dto.review;

import lombok.*;
import org.handmade.miniproject.common.dto.upload.UploadImageDTO;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ReviewDTO {

    private Long rno;

    private String username;

    private String rcontent;

    private List<UploadImageDTO> imageList;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private Long pno;
}
