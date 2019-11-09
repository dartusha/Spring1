package com.geekbrains.persistence;

import com.geekbrains.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Transactional
    @Modifying
    @Query("delete from Customer c where c.id = :id")
    void deleteById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Customer c set c.fio=:fio where c.id = :id")
    void update(@Param("id") Long id,@Param("fio") String fio);
}
