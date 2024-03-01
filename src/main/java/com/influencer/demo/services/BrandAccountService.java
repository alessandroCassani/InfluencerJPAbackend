package com.influencer.demo.services;

import com.influencer.demo.entity.BrandAccount;
import com.influencer.demo.entity.InfluencerAccount;
import com.influencer.demo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service interface for managing operations related to Brand Accounts.
 */
@Service
public interface BrandAccountService {

    /**
     * Adds a new product for a given Brand Account.
     *
     * @param brandAccountId The ID of the Brand Account.
     * @param newProduct     The product to be added.
     */
    void addProductForBrand(Long brandAccountId, Product newProduct);

    /**
     * Deletes a product for a given Brand Account.
     *
     * @param brandAccountId The ID of the Brand Account.
     * @param productId      The ID of the product to be deleted.
     */
    void deleteProductForBrand(Long brandAccountId, Long productId);

    /**
     * Retrieves the set of products associated with a Brand Account.
     *
     * @param brand The Brand Account for which to retrieve products.
     * @return A set of products associated with the specified Brand Account.
     */
    Set<Product> getBrandProducts(BrandAccount brand);

    /**
     * Adds an Influencer Account for a given Brand Account.
     *
     * @param brandAccountId          The ID of the Brand Account.
     * @param new_InfluencerAccount   The Influencer Account to be added.
     */
    void addInfluencerForBrand(Long brandAccountId, InfluencerAccount new_InfluencerAccount);

    /**
     * Deletes an Influencer Account from a given Brand Account.
     *
     * @param brandAccountId          The ID of the Brand Account.
     * @param old_influencerAccountId The ID of the Influencer Account to be deleted.
     */
    void deleteInfluencerForBrand(Long brandAccountId, Long old_influencerAccountId);

    /**
     * Retrieves a list of all Brand Accounts.
     *
     * @return A list of all Brand Accounts.
     */
    List<BrandAccount> getAllBrand();

    /**
     * Retrieves a Brand Account by its ID.
     *
     * @param brandAccountId The ID of the Brand Account to retrieve.
     * @return An Optional containing the retrieved Brand Account, or empty if not found.
     */
    Optional<BrandAccount> getBrandById(Long brandAccountId);

    /**
     * Retrieves the set of Influencer Accounts associated with a specific Brand Account.
     *
     * @param brandAccount The Brand Account for which to retrieve Influencer Accounts.
     * @return A set of Influencer Accounts associated with the specified Brand Account.
     */
    Set<InfluencerAccount> getInfluencerList(BrandAccount brandAccount);

    /**
     * Finds Brand Accounts based on the maximum price of their associated products.
     *
     * @param maxPrice The maximum price of products.
     * @return A list of Brand Accounts meeting the specified criteria.
     */
    List<BrandAccount> findBrandsByProductPrice(double maxPrice);
}
