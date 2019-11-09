package com.geekbrains.service;

import com.geekbrains.persistence.CategoryRepository;
import com.geekbrains.persistence.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<Category> findAllWithoutProducts() {
        return categoryRepository.findAllWithoutProducts();
    }

    @Transactional(readOnly = true)
    public List<Category> findOneById(Long id) {
        return categoryRepository.findOneById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Category> findByIdWithProducts(Long id) {
        return categoryRepository.findByIdWithProducts(id);
    }

    @Transactional
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public Optional<Category> findOne(Long id) {
        return categoryRepository.findOne(id);
    }

    @Transactional
    @Modifying
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}