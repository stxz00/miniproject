package org.handmade.miniproject.product.repository;

import org.handmade.miniproject.product.entity.Favorite;
import org.handmade.miniproject.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    Page<Favorite> getByProduct(Product product, Pageable pageable);
}
