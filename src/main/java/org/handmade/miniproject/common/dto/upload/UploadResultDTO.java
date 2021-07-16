package org.handmade.miniproject.common.dto.upload;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {

    private String uuid;

    private String fileName;

}
