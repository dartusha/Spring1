package com.geekbrains.persistence;

import com.geekbrains.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select new Category(c.id, c.name, c.description) from Category c")
    List<Category> findAllWithoutProducts();

    @Query("select c from Category c where c.id = :id")
    List<Category> findOneById(@Param("id") Long id);

    @Query("select distinct c " +
            "from Category c " +
            "left join fetch c.products p " +
            "where c.id = :id")
    Optional<Category> findByIdWithProducts(@Param("id") Long id);

    @Query("select c from Category c where c.id = :id")
    Optional<Category> findOne(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("delete from Category c where c.id = :id")
    void deleteById(@Param("id") Long id);

}