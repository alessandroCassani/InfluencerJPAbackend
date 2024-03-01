package com.influencer.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * The {@code InfluencerAccount} class represents an entity for influencer accounts in the application.
 * It extends the {@link Account} class, inheriting common properties and behaviors for accounts.
 */
@Entity
public class InfluencerAccount extends Account {

    /**
     * The set of brand accounts collaborating with the influencer.
     */
    @ManyToMany
    @JoinTable(
            name = "Brand_Influencer",
            joinColumns = @JoinColumn(name = "influencer_account_id"),
            inverseJoinColumns = @JoinColumn(name = "brand_account_id")
    )
    private Set<BrandAccount> brandCollaborations = new HashSet<>();

    /**
     * The set of products sponsored by the influencer.
     */
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "influencer_sponsored_products",
            joinColumns = @JoinColumn(name = "influencer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> productSponsored = new HashSet<>();

    /**
     * Constructs an influencer account with the specified username and biography.
     *
     * @param userName The username for the influencer account.
     * @param bio      The biography or description of the influencer account.
     */
    public InfluencerAccount(String userName, String bio) {
        super(userName, bio);
    }

    /**
     * Default constructor for JPA.
     */
    public InfluencerAccount() {
        super();
    }

    /**
     * Adds a brand account to the set of brand collaborations and updates the relationship.
     *
     * @param brandAccount The brand account to be added.
     */
    public void addCollaboration(BrandAccount brandAccount) {
        brandCollaborations.add(brandAccount);
        brandAccount.getInfluencerList().add(this);
    }

    /**
     * Removes a brand account from the set of brand collaborations and updates the relationship.
     *
     * @param brandAccount The brand account to be removed.
     */
    public void removeCollaboration(BrandAccount brandAccount) {
        brandCollaborations.remove(brandAccount);
        brandAccount.getInfluencerList().remove(this);
    }

    /**
     * Retrieves the set of brand accounts collaborating with the influencer.
     *
     * @return The set of collaborating brand accounts.
     */
    public Set<BrandAccount> getBrandCollaborations() {
        return brandCollaborations;
    }

    /**
     * Retrieves the set of products sponsored by the influencer.
     *
     * @return The set of sponsored products.
     */
    public Set<Product> getSponsoredProducts() {
        return productSponsored;
    }

    /**
     * Sets the set of brand accounts collaborating with the influencer.
     *
     * @param brandCollaborations The set of collaborating brand accounts.
     */
    public void setBrandCollaborations(Set<BrandAccount> brandCollaborations) {
        this.brandCollaborations = brandCollaborations;
    }

    /**
     * Adds a product to the set of sponsored products.
     *
     * @param product The product to be added.
     */
    public void addProductSponsoredList(Product product) {
        productSponsored.add(product);
    }

    /**
     * Removes a product from the set of sponsored products.
     *
     * @param product The product to be removed.
     */
    public void removeProductSponsored(Product product) {
        productSponsored.remove(product);
    }

    /**
     * Retrieves the set of sponsored products.
     *
     * @return The set of sponsored products.
     */
    public Set<Product> getProductsSponsoredList() {
        return productSponsored;
    }

    /**
     * Sets the set of sponsored products.
     *
     * @param productSponsored The set of sponsored products.
     */
    public void setProductSponsored(List<Product> productSponsored) {
        this.productSponsored.addAll(productSponsored);
    }
}
