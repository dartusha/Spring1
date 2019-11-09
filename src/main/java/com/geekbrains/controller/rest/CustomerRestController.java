package com.geekbrains.controller.rest;

import com.geekbrains.controller.CustomerController;
import com.geekbrains.persistence.entity.Customer;
import com.geekbrains.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/customers")
@RestController
public class CustomerRestController {

    private CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService= customerService;
    }

    @GetMapping(value = "")
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Customer getCustomer(@PathVariable("id") Long id) {
        return customerService.findOneNyId(id)
              .orElseThrow(ResourceNotFoundException::new);
    }
    @PostMapping(path = "", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = "application/json" )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addCustomer(@RequestBody Customer customer) {
        Long tst = customerService.addNew(customer);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("id") Long id) {
        customerService.delete(id);
    }

    @PatchMapping(path = "/{id}"
            , consumes = {MediaType.APPLICATION_JSON_VALUE}
            , produces = "application/json"
            )
    public Customer updateCustomer(@RequestBody Customer customer) {
        customer = customerService.update(customer);
        return  customer;
    }

}
