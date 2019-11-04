package com.geekbrains.persistence;

import com.geekbrains.controller.repr.ProductRepr;
import com.geekbrains.persistence.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    List<Product> getAllByCategory_Id(Long categoryId, Pageable pageable);

    @Query("select new com.geekbrains.controller.repr.ProductRepr(p.id, p.name, p.description, p.price, p.category.id, p.category.name) " +
            "from Product p " +
            "where p.id = :id")
    Optional<ProductRepr> getProductReprById(@Param("id") Long id);

    @Query("select new  com.geekbrains.controller.repr.ProductRepr(p.id, p.name, p.description, p.price, p.category.id, p.category.name) " +
            "from Product p " +
            "where (:categoryId = -1L or p.category.id = :categoryId) and " +
            "(:priceFrom is null or p.price >= :priceFrom) and " +
            "(:priceTo is null or p.price <= :priceTo)")
    List<ProductRepr> filterProducts(@Param("categoryId") Long categoryId,
                                     @Param("priceFrom") BigDecimal priceFrom,
                                     @Param("priceTo") BigDecimal priceTo, Pageable pageable
    );

    @Query("select count(p)" +
            "from Product p " +
            "left join Category c on p.category.id = c.id " +
            "where (:categoryId = -1L or c.id = :categoryId) and " +
            "(:priceFrom is null or p.price >= :priceFrom) and " +
            "(:priceTo is null or p.price <= :priceTo)")
    Long countFilterProducts(@Param("categoryId") Long categoryId,
                             @Param("priceFrom") BigDecimal priceFrom,
                             @Param("priceTo") BigDecimal priceTo);

}