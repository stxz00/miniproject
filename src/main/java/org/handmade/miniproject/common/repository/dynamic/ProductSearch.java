package org.handmade.miniproject.common.repository.dynamic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductSearch {
    //상품 검색별 페이지 생성
    Page<Object[]> getSearchList(String type, String keyword, Pageable pageable);

    //구매자별 찜 리스트 생성 keyword = username(String)
    Page<Object[]> getFavoriteList(String keyword, Pageable pageable);

    //상품별 Qna 리스트 생성 keyword = 상품번호(Long)
    Page<Object[]> getQnaList(String keyword, Pageable pageable);

    Page<Object[]> getReviewList(String keyword, Pageable pageable);
}
