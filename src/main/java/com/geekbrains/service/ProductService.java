package com.geekbrains.service;

import com.geekbrains.controller.repr.ProductRepr;
import com.geekbrains.persistence.CategoryRepository;
import com.geekbrains.persistence.ProductRepository;
import com.geekbrains.persistence.entity.Category;
import com.geekbrains.persistence.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Product> getAllByCategory_Id(Long categoryId) {
        return productRepository.getAllByCategory_Id(categoryId);
    }
/*
    @Transactional(readOnly = true)
    public Optional<ProductRepr> getProductReprById(Long id) {
        return productRepository.getProductReprById(id);
    }

 */

    @Transactional(readOnly = true)
    public ProductRepr getEmptyProductReprWithCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("Category not found"));
        ProductRepr productRepr = new ProductRepr();
        productRepr.setCategoryId(category.getId());
        productRepr.setCategoryName(category.getName());
        return productRepr;
    }

    @Transactional
    public void save(ProductRepr productRepr) {
        Product product = new Product();
        product.setId(productRepr.getId());
        product.setName(productRepr.getName());
        product.setPrice(productRepr.getPrice());
        product.setDescription(productRepr.getDescription());
        product.setCategory(categoryRepository.findById(productRepr.getCategoryId())
                .orElseThrow(() -> new IllegalStateException("Category not found")));
        productRepository.save(product);
    }
}