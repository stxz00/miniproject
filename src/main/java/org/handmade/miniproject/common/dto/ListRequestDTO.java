package org.handmade.miniproject.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListRequestDTO {

    @Builder.Default //따로 값을 주지 않으면 아래 값 지정
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String keyword;

    //page 와 size 가 보안이 위험하기 때문에 세터 설정.
    public void setPage(int page){
        this.page = page <= 0 ? 1 : page ;
    }
    public void setSize(int size){
        this.size = size < 10 ? 10 : size ;
    }

    @JsonIgnore //JSON 으로 반환할때 무시해주는것
    public Pageable getPageable() {
        return PageRequest.of(this.page-1,this.size);
    }
}
