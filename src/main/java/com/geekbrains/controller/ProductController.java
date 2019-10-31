package com.geekbrains.controller;

import com.geekbrains.persistence.CategoryRepository;
import com.geekbrains.persistence.ProductRepository;
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

import java.math.BigDecimal;


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
                           @RequestParam(name = "pageId", required = false) Integer pageId,
                           @RequestParam(name = "priceMin", required = false) BigDecimal priceMin,
                           @RequestParam(name = "priceMax", required = false) BigDecimal priceMax,
                           Model model) {
        model.addAttribute("categories", categoryRepository.findAll());

        if (categoryId == null || categoryId == -1) {
            model.addAttribute("products", productRepository.findAll());
            model.addAttribute("size",
                    (int)Math.ceil((double)productRepository.findAll().size()/recsPerPage));
        } else {
            model.addAttribute("products", productRepository.getAllByCategory_Id(categoryId));
            model.addAttribute("size",
                    (int)Math.ceil((double)productRepository.getAllByCategory_Id(categoryId).size()/recsPerPage));
        }

        if (priceMin == null){
            priceMin=new BigDecimal(0);
        }
        if (priceMax == null){
            priceMax=new BigDecimal(1000000000);
        }

        model.addAttribute("products", productRepository.findMinMaxDiap(priceMin,priceMax));
        model.addAttribute("size",
                (int)Math.ceil((double)productRepository.findMinMaxDiap(priceMin,priceMax).size()/recsPerPage));
        //1. Фильтрация по цене
        if (pageId==null){

        }

        //2. Постраничная фильтрация

        else {
            Page<Product> page = productRepository.findAll(PageRequest.of(pageId-1, recsPerPage, Sort.by(Sort.Direction.ASC, "id")));
            model.addAttribute("products", productRepository.findAll(PageRequest.of(pageId-1, recsPerPage, Sort.by(Sort.Direction.ASC, "id"))).getContent());
        }

            return "products";
    }


    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createProductFrom(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
       // Category category = categoryRepository.findById(categoryId)
      //          .orElseThrow(() -> new IllegalStateException("Category not found"));
        Product product = new Product();
      //  product.setCategory(category);
        model.addAttribute("product", product);
        return "product";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createProduct(@RequestParam(name = "categoryId", required = false) Long categoryId,
            @ModelAttribute("product") Product product) {
        product.setCategory(categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("Category not found")));
        productRepository.save(product);
        return "redirect:/products";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editForm(@RequestParam("id") Long id,
                           @RequestParam(name = "categoryId", required = false) Long categoryId,
                           Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product not found"));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("product", product);
        product.setCategory(categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("Category not found")));

        model.addAttribute("action", "edit");
        return "product";
    }
/*
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String editForm(
            @RequestParam(name = "categoryId", required = false) Long categoryId,
            @ModelAttribute("product") Product product) {
        product.setCategory(categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("Category not found")));
        productRepository.save(product);
        return "redirect:/products";
    }
    */

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveForm(
            @ModelAttribute("product") Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editForm(
            @ModelAttribute("product") Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

}