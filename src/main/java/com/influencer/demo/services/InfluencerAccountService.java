package com.influencer.demo.services;

import com.influencer.demo.entity.BrandAccount;
import com.influencer.demo.entity.InfluencerAccount;
import com.influencer.demo.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;
/**
 * Service interface for managing operations related to Influencer Accounts.
 */
public interface InfluencerAccountService {

    /**
     * Adds a sponsored product for a given Influencer Account.
     *
     * @param influencerId The ID of the Influencer Account.
     * @param product      The product to be sponsored.
     */
    void addProductSponsored(Long influencerId, Product product);

    /**
     * Deletes a sponsored product for a given Influencer Account.
     *
     * @param influencerId The ID of the Influencer Account.
     * @param productId    The ID of the product to be deleted.
     */
    void deleteProductSponsored(Long influencerId, Long productId);

    /**
     * Retrieves the set of sponsored products associated with an Influencer Account.
     *
     * @param influencerId The ID of the Influencer Account for which to retrieve sponsored products.
     * @return A set of sponsored products associated with the specified Influencer Account.
     */
    Set<Product> getProductSponsored(Long influencerId);

    /**
     * Adds a Brand Account collaboration for a given Influencer Account.
     *
     * @param influencerAccount The ID of the Influencer Account.
     * @param new_brandAccount   The Brand Account to be added as a collaboration.
     */
    void addBrandCollaboration(Long influencerAccount, BrandAccount new_brandAccount);

    /**
     * Deletes a Brand Account collaboration from a given Influencer Account.
     *
     * @param influencerAccountId The ID of the Influencer Account.
     * @param old_brandAccountId   The ID of the Brand Account collaboration to be deleted.
     */
    void deleteBrandCollaboration(Long influencerAccountId, Long old_brandAccountId);

    /**
     * Retrieves a list of all Influencer Accounts.
     *
     * @return A list of all Influencer Accounts.
     */
    List<InfluencerAccount> getAllInfluencer();

    /**
     * Retrieves an Influencer Account by its ID.
     *
     * @param influencerAccountId The ID of the Influencer Account to retrieve.
     * @return An Optional containing the retrieved Influencer Account, or empty if not found.
     */
    Optional<InfluencerAccount> getInfuencerById(Long influencerAccountId);

    /**
     * Retrieves the set of Brand Accounts associated with a specific Influencer Account.
     *
     * @param influencerAccount The Influencer Account for which to retrieve Brand Accounts.
     * @return A set of Brand Accounts associated with the specified Influencer Account.
     */
    Set<BrandAccount> getBrandAccountList(InfluencerAccount influencerAccount);
}
