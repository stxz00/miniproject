package org.handmade.miniproject.common.dto;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//현재 페이지 번호
//사이즈
//전체 개수
@Data
public class PageMaker {
    private int page;
    private int size;
    private int totalCount;
    private List<Integer> pageList;
    private boolean prev, next; //이전, 다음 페이지 존재 유무
    private int start, end;
    private int totalPage;

    public PageMaker(int page,int size, int totalCount){
        this.page = page;
        this.size = size;
        this.totalCount = totalCount;

        totalPage = (int)(Math.ceil(totalCount/(double)size));
        //temp end page
        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;

        start = tempEnd - 9;

        prev = start > 1;

        end = totalPage > tempEnd ? tempEnd: totalPage;

        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

    }

    public void makePageList(Pageable pageable){

        this.page = pageable.getPageNumber() + 1; // 0부터 시작하므로 1을 추가
        this.size = pageable.getPageSize();

        //temp end page
        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;

        start = tempEnd - 9;

        prev = start > 1;

        end = totalPage > tempEnd ? tempEnd: totalPage;

        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

    }

}
