package com.influencer.demo.services;

import com.influencer.demo.entity.Product;
import com.influencer.demo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the ProductService interface for managing Product entities.
 */
@Service
public class ProductImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Registers a new product.
     *
     * @param product The product to be registered.
     */
    @Override
    @Transactional
    public void registerProduct(Product product) {
        productRepository.save(product);
    }

    /**
     * Deletes a product based on its ID.
     *
     * @param productId The ID of the product to be deleted.
     */
    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    /**
     * Updates the information of an existing product.
     *
     * @param productId The ID of the product to be updated.
     */
    @Override
    @Transactional
    public void updateProduct(Long productId) {
        double newPrice = 100.5;
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setPrice(newPrice);
            productRepository.save(product);
        } else {
            throw new EntityNotFoundException("Product with ID " + productId + " not found");
        }
    }

    /**
     * Retrieves information about a product based on its ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return The retrieved product information.
     * @throws EntityNotFoundException If the product with the given ID is not found.
     */
    @Override
    @Transactional
    public Product getProductInfo(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new EntityNotFoundException("Product with ID " + productId + " not found");
        }
    }

    /**
     * Counts the total number of products.
     *
     * @return The total number of products.
     */
    @Override
    public int countElements() {
        return productRepository.countElements();
    }
}
