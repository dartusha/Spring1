package com.geekbrains.persistence;

import com.geekbrains.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getAllByCategory_Id(Long categoryId);
    List<Product> getAllByPriceEquals(BigDecimal price);

    @Query("select p from Product p where p.price=(select min(p1.price+0) from Product p1)")
    List<Product> findMinPrice();

    @Query("select p from Product p where p.price=(select max(p1.price+0) from Product p1)")
    List<Product> findMaxPrice();

    @Query("select p from Product p where p.price in (select max(p1.price+0) from Product p1)" +
            " or p.price in (select min(p2.price+0) from Product p2)")
    List<Product> findMinMaxPrice();

    @Query("select p from Product p where p.price between ?1 and ?2")
    List<Product> findMinMaxDiap(BigDecimal priceMin, BigDecimal priceMax);


}