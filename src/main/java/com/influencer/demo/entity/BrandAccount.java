package com.influencer.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The {@code BrandAccount} class represents an entity for brand accounts in the application.
 * It extends the {@link Account} class, inheriting common properties and behaviors for accounts.
 */
@Entity
public class BrandAccount extends Account {

    /**
     * The set of influencer accounts collaborating with the brand.
     */
    @ManyToMany
    @JoinTable(
            name = "Brand_Influencer",
            joinColumns = @JoinColumn(name = "brand_account_id"),
            inverseJoinColumns = @JoinColumn(name = "influencer_account_id")
    )
    private Set<InfluencerAccount> influencerList = new HashSet<>();

    /**
     * The set of products associated with the brand.
     */
    @OneToMany(mappedBy = "brand", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Product> brandProducts = new HashSet<>();

    /**
     * Constructs a brand account with the specified username and biography.
     *
     * @param userName The username for the brand account.
     * @param bio      The biography or description of the brand account.
     */
    public BrandAccount(String userName, String bio) {
        super(userName, bio);
    }

    /**
     * Default constructor for JPA.
     */
    public BrandAccount() {
        super();
    }

    /**
     * Adds a product to the set of brand products and establishes the relationship.
     *
     * @param product The product to be added.
     */
    public void addProduct(Product product) {
        brandProducts.add(product);
        product.setBrand(this);
    }

    /**
     * Removes a product from the set of brand products and updates the relationship.
     *
     * @param product The product to be removed.
     */
    public void removeProduct(Product product) {
        brandProducts.remove(product);
        product.setBrand(null);
    }

    /**
     * Adds an influencer to the set of collaborating influencers and updates the relationship.
     *
     * @param influencerAccount The influencer account to be added.
     */
    public void addInfluencer(InfluencerAccount influencerAccount) {
        influencerList.add(influencerAccount);
        influencerAccount.getBrandCollaborations().add(this);
    }

    /**
     * Removes an influencer from the set of collaborating influencers and updates the relationship.
     *
     * @param influencerAccount The influencer account to be removed.
     */
    public void removeInfluencer(InfluencerAccount influencerAccount) {
        influencerList.remove(influencerAccount);
        influencerAccount.getBrandCollaborations().remove(this);
    }

    /**
     * Retrieves the set of influencer accounts collaborating with the brand.
     *
     * @return The set of collaborating influencer accounts.
     */
    public Set<InfluencerAccount> getInfluencerList() {
        return influencerList;
    }

    /**
     * Retrieves the set of products associated with the brand.
     *
     * @return The set of brand products.
     */
    public Set<Product> getBrandProducts() {
        return brandProducts;
    }

    /**
     * Sets the set of influencer accounts collaborating with the brand.
     *
     * @param influencerList The set of collaborating influencer accounts.
     */
    public void setInfluencerList(Set<InfluencerAccount> influencerList) {
        this.influencerList = influencerList;
    }

    /**
     * Sets the set of products associated with the brand.
     *
     * @param brandProducts The set of brand products.
     */
    public void setBrandProducts(Set<Product> brandProducts) {
        this.brandProducts = new HashSet<>(brandProducts);
    }
}
