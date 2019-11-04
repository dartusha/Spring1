package com.geekbrains.persistence;

import com.geekbrains.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
