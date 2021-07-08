package org.handmade.miniproject.product.repository;

import org.handmade.miniproject.product.entity.Product;
import org.handmade.miniproject.product.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    Page<Review> getByProduct(Product product, Pageable pageable);
}
