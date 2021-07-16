package org.handmade.miniproject.product.repository;

import org.handmade.miniproject.product.entity.Product;
import org.handmade.miniproject.product.entity.Qna;
import org.handmade.miniproject.common.repository.dynamic.ProductSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Qna,Long>, ProductSearch {
    Page<Qna> getByProduct(Product product, Pageable pageable);

}
