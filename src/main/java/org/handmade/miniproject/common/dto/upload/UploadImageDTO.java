package org.handmade.miniproject.common.dto.upload;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UploadImageDTO {

    private String uuid;

    private String fileName;

    private boolean main;
}
