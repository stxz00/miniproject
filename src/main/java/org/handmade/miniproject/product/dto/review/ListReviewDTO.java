package org.handmade.miniproject.product.dto.review;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ListReviewDTO {

    private ReviewDTO reviewDTO;

    private Long reviewCount;
}
