package com.influencer.demo.services;

import com.influencer.demo.entity.BrandAccount;
import com.influencer.demo.entity.InfluencerAccount;
import com.influencer.demo.entity.Product;
import com.influencer.demo.repository.BrandAccountRepository;
import com.influencer.demo.repository.InfluencerAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class BrandAccountImpl implements BrandAccountService{
    @Autowired
    private BrandAccountRepository brandAccountRepository;

    @Autowired
    private InfluencerAccountRepository influencerAccountRepository;

    @Override
    @Transactional
    public void addProductForBrand(Long brandAccountId, Product newProduct) {
        Optional<BrandAccount> brandAccountOptional = brandAccountRepository.findById(brandAccountId);

        if (brandAccountOptional.isPresent()) {
            BrandAccount brandAccount = brandAccountOptional.get();
            brandAccount.addProduct(newProduct);
            brandAccountRepository.save(brandAccount);
        } else {
            throw new EntityNotFoundException("BrandAccount with ID " + brandAccountId + " not found");
        }
    }

    @Override
    @Transactional
    public void deleteProductForBrand(Long brandAccountId, Long productId) {
        Optional<BrandAccount> brandAccountOptional = brandAccountRepository.findById(brandAccountId);

        if (brandAccountOptional.isPresent()) {
            BrandAccount brandAccount = brandAccountOptional.get();

            Optional<Product> productOptional = brandAccount.getBrandProducts().stream()
                    .filter(product -> Objects.equals(product.getId(), productId))
                    .findFirst();

            productOptional.ifPresent(brandAccount::removeProduct);

            brandAccountRepository.save(brandAccount);
        } else {
            throw new EntityNotFoundException("BrandAccount with ID " + brandAccountId + " not found");
        }
    }

    @Override
    @Transactional
    public Set<Product> getBrandProducts(BrandAccount brand){
        return brandAccountRepository.findProductsByBrandAccount(brand);
    }

    /**
     * Added an Influencer Account from a Brand Account
     * This method adds the association between a Brand Account and an Influencer Account,
     * updating the database.
     * @param brandAccountId The ID of the Brand Account
     * @param new_InfluencerAccount The ID of the Influencer Account to be added from the brand account
     * @throws EntityNotFoundException If the Brand Account is not found in the database.
     */
    @Override
    @Transactional
    public void addInfluencerForBrand(Long brandAccountId, InfluencerAccount new_InfluencerAccount) {
        Optional<BrandAccount> brandAccountOptional = brandAccountRepository.findById(brandAccountId);

        if(brandAccountOptional.isPresent()){
            BrandAccount brandAccount  = brandAccountOptional.get();
            brandAccount.addInfluencer(new_InfluencerAccount);
            brandAccountRepository.save(brandAccount);
            //influencerAccountRepository.save(new_InfluencerAccount); -> ?
        }else{
            throw new EntityNotFoundException("BrandAccount with ID " + brandAccountId + " not found");
        }
    }

    /**
     * Remove an Influencer Account from a Brand Account
     * This method removes the association between a Brand Account and an Influencer Account,
     * updating the database.
     * @param brandAccountId The ID of the Brand Account
     * @param old_influencerAccountId The ID of the Influencer Account to be removed from the brand account
     * @throws EntityNotFoundException If the Influencer Account or Brand Account is not found in the database
     */
    @Override
    @Transactional
    public void deleteInfluencerForBrand(Long brandAccountId, Long old_influencerAccountId) {
        Optional<BrandAccount> brandAccountOptional = brandAccountRepository.findById(brandAccountId);
        Optional<InfluencerAccount> influencerAccountOpional = influencerAccountRepository.findById(old_influencerAccountId);

        if (brandAccountOptional.isPresent() || influencerAccountOpional.isPresent()){
            BrandAccount brandAccount = brandAccountOptional.get();
            InfluencerAccount influencerAccount = influencerAccountOpional.get();

            brandAccount.removeInfluencer(influencerAccount);

            brandAccountRepository.save(brandAccount);
            //influencerAccountRepository.save(influencerAccount); -> ?
        }else {
            throw new EntityNotFoundException("InfluencerAccount or BrandAccount not found");
        }
    }

    /**
     * This method returns all Brand Accounts in the database
     * @return A list of Brand Accounts
     */
    @Override
    @Transactional
    public List<BrandAccount> getAllBrand() {
        return brandAccountRepository.findAll();
    }

    /**
     * This method returns a Brand Account based on its ID
     * @param brandAccountId The ID of the Brand Account to retrieve
     * @return An Optional containing the retrieved brand account, or empty if not found.
     */
    @Override
    @Transactional
    public Optional<BrandAccount> getBrandById(Long brandAccountId) {
        return brandAccountRepository.findById(brandAccountId);
    }

    /**
     * This method returns the list of Influencer Accounts that are associated with a specific brand
     * @param brandAccount The Brand Account for which to retrieve influencer accounts
     * @return A list of Influencers Accounts associated with the specified brand account
     */
    @Override
    @Transactional
    public Set<InfluencerAccount> getInfluencerList(BrandAccount brandAccount) {
        return brandAccountRepository.findInfluencerListByBrand(brandAccount);
    }

    @Override
    @Transactional
    public List<BrandAccount> findBrandsByProductPrice(double maxPrice) {
        return brandAccountRepository.findBrandsByProductPrice(maxPrice);
    }
}