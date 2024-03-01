package com.influencer.demo.repository;

import com.influencer.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * The {@code ProductRepository} interface provides methods for interacting with the {@link Product} entity in the database.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * SQL query to count the total number of products in the database.
     */
    String count = "SELECT COUNT(*)\n" +
            "FROM product";

    /**
     * Counts the total number of products in the database.
     *
     * @return The total number of products.
     */
    @Query(value = count, nativeQuery = true)
    int countElements();

}
