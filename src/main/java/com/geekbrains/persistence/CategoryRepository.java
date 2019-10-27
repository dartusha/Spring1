package com.geekbrains.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.geekbrains.persistence.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}