package com.geekbrains.service;

import com.geekbrains.persistence.CategoryRepository;
import com.geekbrains.persistence.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
/*
    @Transactional(readOnly = true)
    public List<Category> findAllWithoutProducts() {
        return categoryRepository.findAllWithoutProducts();
    }

    @Transactional(readOnly = true)
    public Optional<Category> findByIdWithProducts(Long id) {
        return categoryRepository.findByIdWithProducts(id);
    }

 */

    @Transactional
    public void save(Category category) {
        categoryRepository.save(category);
    }
}