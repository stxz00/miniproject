package org.handmade.miniproject.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListResponseDTO<D> { //공용이니 제네릭으로 어느 DTO 가 오든 사용 가능

    private ListRequestDTO listRequestDTO;

    private List<D> dtoList;

    private PageMaker pageMaker;

    private int page,start,end;



}
