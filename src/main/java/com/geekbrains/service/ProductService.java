package com.geekbrains.service;

import com.geekbrains.controller.repr.ProductFilter;
import com.geekbrains.controller.repr.ProductRepr;
import com.geekbrains.persistence.CategoryRepository;
import com.geekbrains.persistence.ProductRepository;
import com.geekbrains.persistence.entity.Category;
import com.geekbrains.persistence.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    public Optional<ProductRepr> getProductReprById(Long id) {
        return productRepository.getProductReprById(id);
    }

    @Transactional(readOnly = true)
    public ProductRepr getEmptyProductReprWithCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("Category not found"));
        ProductRepr productRepr = new ProductRepr();
        productRepr.setCategoryId(category.getId());
        productRepr.setCategoryName(category.getName());
        return productRepr;
    }

    @Transactional(readOnly = true)
    public ProductRepr getEmptyProductRepr() {
        ProductRepr productRepr = new ProductRepr();
        return productRepr;
    }



    @Transactional(readOnly = true)
    public List<ProductRepr> filterProducts(ProductFilter filter) {
        return productRepository.filterProducts(filter.getCategoryId(),
                filter.getPriceFrom(), filter.getPriceTo(), PageRequest.of(filter.getCurrentPage(), filter.getPageSize()));
    }

    @Transactional(readOnly = true)
    public Long countFilterProducts(ProductFilter filter) {
        return productRepository.countFilterProducts(filter.getCategoryId(),
                filter.getPriceFrom(), filter.getPriceTo());
    }

    @Transactional
    public void save(ProductRepr productRepr) {
        Product product = new Product();
        System.out.println("!!!"+productRepr.getName());
        System.out.println("!!!"+productRepr.getId());
        product.setId(productRepr.getId());
        product.setName(productRepr.getName());
        product.setPrice(productRepr.getPrice());
        product.setDescription(productRepr.getDescription());
        product.setCategory(categoryRepository.findById(productRepr.getCategoryId())
                .orElseThrow(() -> new IllegalStateException("Category not found")));
        productRepository.save(product);
    }
}