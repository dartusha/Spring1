package com.geekbrains.controller;

import com.geekbrains.controller.repr.CustomerRepr;
import com.geekbrains.persistence.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.geekbrains.controller.CustomerController;
import com.geekbrains.service.CustomerService;

import javax.validation.Valid;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final CustomerService customerService;

    @Autowired
    public LoginController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("customer", new CustomerRepr());
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute("customer") @Valid CustomerRepr customerRepr,
                                  BindingResult result) {
        logger.info("New user {}", customerRepr);

        if (result.hasErrors()) {
            return "register";
        }
        if (!customerRepr.getPassword().equals(customerRepr.getMatchingPassword())) {
            result.rejectValue("password", "", "Password not matching");
            return "register";
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(customerRepr.getPassword());

        Customer customer=new Customer(customerRepr.getFio(),customerRepr.getLogin(),hashedPassword);

        customerService.save(customer);
        return "redirect:/login";
    }
}