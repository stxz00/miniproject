package org.handmade.miniproject.product.repository.dynamic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductSearch {
    //상품 검색별 페이지 생성
    Page<Object[]> getSearchList(String type, String keyword, Pageable pageable);
}
