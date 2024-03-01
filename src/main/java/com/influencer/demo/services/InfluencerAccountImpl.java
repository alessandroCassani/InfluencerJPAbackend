package com.influencer.demo.services;

import com.influencer.demo.entity.BrandAccount;
import com.influencer.demo.entity.InfluencerAccount;
import com.influencer.demo.entity.Product;
import com.influencer.demo.repository.BrandAccountRepository;
import com.influencer.demo.repository.InfluencerAccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class InfluencerAccountImpl implements InfluencerAccountService {
    @Autowired
    private InfluencerAccountRepository influencerAccountRepository;

    @Autowired
    private BrandAccountRepository brandAccountRepository;

    /**
     * Adds a sponsored product to the specified Influencer account.
     *
     * @param influencerId The ID of the Influencer account.
     * @param product      The product to be added as sponsored.
     * @throws IllegalArgumentException if the product already exists in the sponsored products list.
     */
    @Override
    @Transactional
    public void addProductSponsored(final Long influencerId, final Product product) {
        InfluencerAccount influencerAccount = getInfluencerAccountById(influencerId);

        if(!influencerAccount.getSponsoredProducts().contains(product)){
            influencerAccount.addProductSponsoredList(product);
            influencerAccountRepository.save(influencerAccount);
        } else {
            throw new IllegalArgumentException("The product passed by argument already exist in memory.");
        }
    }

    /**
     * Deletes a sponsored product from the specified Influencer account.
     *
     * @param influencerId The ID of the Influencer account.
     * @param productId    The ID of the product to be deleted.
     */
    @Override
    @Transactional
    public void deleteProductSponsored(final Long influencerId,final Long productId) {
        InfluencerAccount influencerAccount = getInfluencerAccountById(influencerId);

        influencerAccount.getProductsSponsoredList()
                .stream()
                .filter(product -> Objects.equals(product.getId(), productId))
                .findFirst()
                .ifPresent(influencerAccount::removeProductSponsored);

        influencerAccountRepository.save(influencerAccount);
    }

    /**
     * Retrieves the set of sponsored products for the specified Influencer account.
     *
     * @param influencerId The ID of the Influencer account.
     * @return A set of sponsored products associated with the Influencer account.
     */
    @Override
    @Transactional
    public Set<Product> getProductSponsored(final Long influencerId) {
        InfluencerAccount influencerAccount = getInfluencerAccountById(influencerId);
        return influencerAccount.getProductsSponsoredList();
    }

    /**
     * Added a Brand Account from an Influencer Account
     * This method adds the association between an Influencer Account and a Brand Account,
     * updating the database.
     * @param influencerAccountId The ID of the Influencer Account
     * @param new_brandAccount The ID of the Brand Account to be added from the influencer account
     * @throws EntityNotFoundException If the Influencer Account is not found in the database
     */
    @Override
    @Transactional
    public void addBrandCollaboration(Long influencerAccountId, BrandAccount new_brandAccount) {
        Optional<InfluencerAccount> influencerAccountOptional = influencerAccountRepository.findById(influencerAccountId);

        if(influencerAccountOptional.isPresent()){
            InfluencerAccount influencerAccount = influencerAccountOptional.get();
            if (!influencerAccount.getBrandCollaborations().equals(new_brandAccount)) {
                influencerAccount.addCollaboration(new_brandAccount);
                influencerAccountRepository.save(influencerAccount);
            }else {
                throw new IllegalArgumentException("The relationship between BrandAccount and InfluencerAccount is already in the database");
            }
        }else{
            throw new EntityNotFoundException("InfluencerAccount with ID " + influencerAccountId + " not found");
        }
    }

    /**
     * Removed a Brand Account from an Influencer Account
     * This method removes the association between an Influencer Account and a Brand Account,
     * updating the database.
     * @param influencerAccountId The ID of the Influencer Account
     * @param old_brandAccountId The ID of the Brand Account to be removed from the influencer account
     * @throws EntityNotFoundException If the Influencer Account or Brand Account is not found in the database
     */
    @Override
    @Transactional
    public void deleteBrandCollaboration(Long influencerAccountId, Long old_brandAccountId) {
        Optional<InfluencerAccount> influencerAccountOptional = influencerAccountRepository.findById(influencerAccountId);
        Optional<BrandAccount> brandAccountOptional = brandAccountRepository.findById(old_brandAccountId);

        if(influencerAccountOptional.isPresent() && brandAccountOptional.isPresent()){
            InfluencerAccount influencerAccount = influencerAccountOptional.get();
            BrandAccount brandAccount = brandAccountOptional.get();

            influencerAccount.removeCollaboration(brandAccount);

            influencerAccountRepository.save(influencerAccount);
        }else {
            throw new EntityNotFoundException("InfluencerAccount or BrandAccount not found");
        }

    }

    /**
     * This method returns all Influencer Accounts in the database
     * @return A list of Influencer Accounts
     */
    @Override
    @Transactional
    public List<InfluencerAccount> getAllInfluencer() {
        return influencerAccountRepository.findAll();
    }

    /**
     * This method returns an Influencer Account based on its ID
     * @param influencerAccountId The ID of the Influencer Account to retrieve
     * @return An Optional containing the retrieved influencer account, or empty if not found
     */
    @Override
    @Transactional
    public Optional<InfluencerAccount> getInfuencerById(Long influencerAccountId) {
        return influencerAccountRepository.findById(influencerAccountId);
    }

    /**
     * This method returns the list of Brand Accounts that are associated with a specific influencer
     * @param influencerAccount The Influencer Account for which to retrieve brand accounts
     * @return A list of Brand Accounts associated with the specified influencer account
     */
    @Override
    @Transactional
    public Set<BrandAccount> getBrandAccountList(InfluencerAccount influencerAccount) {
        return influencerAccountRepository.findBrandListByInfluencer(influencerAccount);
    }

    private InfluencerAccount getInfluencerAccountById(final Long influencerId) {
        return influencerAccountRepository.findById(influencerId)
                .orElseThrow(() -> new EntityNotFoundException("Influencer account with ID " + influencerId + " not found"));
    }

}