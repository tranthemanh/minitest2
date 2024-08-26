package com.manhcode.repository;

import com.manhcode.model.Category;
import com.manhcode.repository.dto.ICategoryPostsNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
    @Query(nativeQuery = true, value = "SELECT category.name, count(posts.id) as number FROM category LEFT JOIN posts ON category_id = category.id GROUP BY category.name")
    List<ICategoryPostsNumber> getCategoryPostsNumber();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "CALL sp_delete_category(:id)")
    void deleteCategoryById(@Param("id") Long id);
}
