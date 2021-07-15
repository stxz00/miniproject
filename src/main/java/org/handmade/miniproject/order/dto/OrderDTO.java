package org.handmade.miniproject.order.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {

    private Long ono;

    private Long pno;

    private String oUsername;

    private String oZipcode;

    private String oAddress1;

    private String oAddress2;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private boolean del;

}
