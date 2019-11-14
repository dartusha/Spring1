package com.geekbrains.service;

import com.geekbrains.controller.repr.CustomerRepr;
import com.geekbrains.persistence.CustomerRepository;
import com.geekbrains.persistence.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository= customerRepository;
    }

    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Customer> findOneNyId(Long id)  {
    return customerRepository.findById(id);
    }

    @Transactional
    @Modifying
     public Long addNew(Customer customer){
        customerRepository.save(customer);
        return customer.getId();
    }


    @Transactional
    @Modifying
    public Customer save(Customer customer){
        customerRepository.save(customer);
        return customer;
    }

    @Transactional
    @Modifying
    public Customer update(Customer customer){
        customerRepository.update(customer.getId(), customer.getFio());
        return customer;
    }


    @Transactional
    @Modifying
    public void delete(Long id){
        customerRepository.deleteById(id);
    }

}
