package com.geekbrains.controller;

import com.geekbrains.controller.repr.ProductFilter;
import com.geekbrains.controller.repr.ProductRepr;
import com.geekbrains.persistence.entity.Product;
import com.geekbrains.service.CategoryService;
import com.geekbrains.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final ProductService productService;

    private final CategoryService categoryService;

    private static final int recsPerPage = 5;


    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String products(@RequestParam(name = "categoryId", required = false) Long categoryId,
                           @RequestParam(name = "priceFrom", required = false) BigDecimal priceFrom,
                           @RequestParam(name = "priceTo", required = false) BigDecimal priceTo,
                           @RequestParam(name = "currentPage", defaultValue = "0") Integer currentPage,
                           @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                           Model model) {

        ProductFilter productFilter = new ProductFilter(categoryId != null ? categoryId : -1, priceFrom, priceTo,
                currentPage, pageSize);
        productFilter.applyItemCount(productService.countFilterProducts(productFilter).intValue());

        model.addAttribute("products", productService.filterProducts(productFilter));
        model.addAttribute("filter", productFilter);
        model.addAttribute("categories", categoryService.findAllWithoutProducts());

        return "products.html";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createProductFrom(@RequestParam("categoryId") Long categoryId, Model model) {
        model.addAttribute("categories", categoryService.findOneById(categoryId));
        model.addAttribute("product", productService.getEmptyProductReprWithCategory(categoryId));
        return "product";
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String createProductFrom(Model model) {
        model.addAttribute("product", productService.getEmptyProductRepr());
        model.addAttribute("categories", categoryService.findAllWithoutProducts());
        return "product";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editProduct(@RequestParam("id") Long id, Model model) {
        model.addAttribute("categories", categoryService.findAllWithoutProducts());
        model.addAttribute("product", productService.getProductReprById(id)
                .orElseThrow(() -> new IllegalStateException("Product not found")));
        return "product";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute("product") ProductRepr productRepr) {
        productService.save(productRepr);
        return "redirect:/products";
    }
}
