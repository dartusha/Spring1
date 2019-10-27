package com.geekbrains.controller;

import com.geekbrains.persistence.CategoryRepository;
import com.geekbrains.persistence.ProductRepository;
import com.geekbrains.persistence.entity.Category;
import com.geekbrains.persistence.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("products")
public class ProductController {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private static final int  recsPerPage = 5;

    @Autowired
    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String products(@RequestParam(name = "categoryId", required = false) Long categoryId,
                           @RequestParam(name = "priceOpt", required = false) String priceOpt,
                           @RequestParam(name = "pageId", required = false) Integer pageId,
                           Model model) {
      //  model.addAttribute("categories", categoryRepository.findAll());
        if (categoryId == null || categoryId == -1) {
            model.addAttribute("products", productRepository.findAll());
            model.addAttribute("size",
                    Math.ceil((double)productRepository.findAll().size()/recsPerPage));
        } else {
            model.addAttribute("products", productRepository.getAllByCategory_Id(categoryId));
            model.addAttribute("size",
                    Math.ceil((double)productRepository.getAllByCategory_Id(categoryId).size()/recsPerPage));
        }

        //Постраничная фильтрация
        if (pageId == null){
        }
        else {
            Page<Product> page = productRepository.findAll(PageRequest.of(pageId-1, recsPerPage, Sort.by(Sort.Direction.ASC, "id")));
            model.addAttribute("products", productRepository.findAll(PageRequest.of(pageId-1, recsPerPage, Sort.by(Sort.Direction.ASC, "id"))).getContent());
        }

        //1. Фильтрация по цене
        if (priceOpt==null){

        }
        else {
            if (priceOpt.equals("Minimum price")) {
                model.addAttribute("products", productRepository.findMinPrice());
                model.addAttribute("size",
                        Math.ceil((double)productRepository.findMinPrice().size()/recsPerPage));
            }
            if (priceOpt.equals("Maximum price")) {
                model.addAttribute("products", productRepository.findMaxPrice());
                model.addAttribute("size",
                        Math.ceil((double)productRepository.findMaxPrice().size()/recsPerPage));
            }
            if (priceOpt.equals("Min and Max price")) {
                model.addAttribute("products", productRepository.findMinMaxPrice());
                model.addAttribute("size",
                        Math.ceil((double)productRepository.findMinMaxPrice().size()/recsPerPage));
            }
            if (priceOpt.equals("All")) {
                model.addAttribute("products", productRepository.findAll());
                model.addAttribute("size",
                        Math.ceil((double)productRepository.findAll().size()/recsPerPage));
            }
        }
            return "products";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createProductFrom(@RequestParam("categoryId") Long categoryId, Model model) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("Category not found"));
        Product product = new Product();
        product.setCategory(category);
        model.addAttribute("product", product);
        return "product";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute("product") Product product) {
        product.setCategory(categoryRepository.findById(product.getCategoryId())
                .orElseThrow(() -> new IllegalStateException("Category not found")));
        productRepository.save(product);
        return "redirect:/categories/edit?id=" + product.getCategory().getId();
    }
}