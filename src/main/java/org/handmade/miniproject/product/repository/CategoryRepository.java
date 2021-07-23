package org.handmade.miniproject.product.repository;

import org.handmade.miniproject.product.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    //모든 카테고리를 호출
    @Query("select c.cno, c.cname, ci.uuid, ci.fileName " +
            "from Category c left join c.categoryImages ci " +
            "where c.cno > 0 group by c")
    Page<Object[]> getAllList(Pageable pageable);

    //카테고리의 대분류를 호출
    @Query("select c.cno, c.cname, ci.uuid, ci.fileName " +
            "from Category c left join c.categoryImages ci " +
            "where ci.main = true and c.cno > 0 group by c")
    Page<Object[]> getMainList(Pageable pageable);

    @Query("select c  from Category c where c.cname= :cname")
    Category findByCategory(String cname);

}
