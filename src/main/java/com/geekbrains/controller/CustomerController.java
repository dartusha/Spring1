package com.geekbrains.controller;

import com.geekbrains.persistence.CustomerRepository;
import com.geekbrains.persistence.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String allCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customers";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String createCategoryFrom(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String editForm(@RequestParam("id") Long id, Model model) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Customer not found"));
        model.addAttribute("customer", customer);
        return "customer";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveForm(@ModelAttribute("customer") Customer customer) {
        customerRepository.save(customer);
        return "redirect:/admin/customers";
    }

}
