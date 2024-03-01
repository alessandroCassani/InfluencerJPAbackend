package com.influencer.demo.services;

import com.influencer.demo.entity.Product;
import org.springframework.stereotype.Service;
/**
 * Service interface for managing operations related to Products.
 */
public interface ProductService {

    /**
     * Registers a new product.
     *
     * @param product The product to be registered.
     */
    void registerProduct(Product product);

    /**
     * Deletes a product based on its ID.
     *
     * @param productId The ID of the product to be deleted.
     */
    void deleteProduct(Long productId);

    /**
     * Updates the information of an existing product.
     *
     * @param productId The ID of the product to be updated.
     */
    void updateProduct(Long productId);

    /**
     * Retrieves information about a product based on its ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return The retrieved product information.
     */
    Product getProductInfo(Long productId);

    /**
     * Counts the total number of products.
     *
     * @return The total number of products.
     */
    int countElements();
}
