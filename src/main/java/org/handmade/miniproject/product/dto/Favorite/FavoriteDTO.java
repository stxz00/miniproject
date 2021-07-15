package org.handmade.miniproject.product.dto.Favorite;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FavoriteDTO {

    private Long fno;

    private String username;

    private Long pno;
}
