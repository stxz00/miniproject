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

    //구매자 ManyToOne
    private String username;

    //찜 해제 여부
    private boolean del;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
