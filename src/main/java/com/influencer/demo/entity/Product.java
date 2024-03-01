package com.influencer.demo.entity;

import jakarta.persistence.*;

/**
 * The {@code Product} class represents an entity for products in the application.
 * Each product has a unique identifier, a name, a price, and is associated with a {@link BrandAccount}.
 */
@Entity
@Table(name = "Product")
public class Product {

    /**
     * The unique identifier for the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long productId;

    /**
     * The name of the product.
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * The price of the product.
     */
    @Column(name = "price")
    private double price;

    /**
     * The brand account associated with the product.
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "brand_id", referencedColumnName = "AccountId")
    private BrandAccount brand;

    /**
     * Constructs a product with the specified name, price, and brand.
     *
     * @param productName The name of the product.
     * @param price        The price of the product.
     * @param brand        The brand account associated with the product.
     */
    public Product(String productName, double price, BrandAccount brand) {
        this.productName = productName;
        this.price = price;
        this.brand = brand;
    }

    /**
     * Default constructor for JPA.
     */
    public Product() {
    }

    /**
     * Retrieves the unique identifier of the product.
     *
     * @return The product's unique identifier.
     */
    public long getId() {
        return productId;
    }

    /**
     * Retrieves the name of the product.
     *
     * @return The product's name.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return The product's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the name of the product.
     *
     * @param name The new name for the product.
     */
    public void setProductName(String name) {
        productName = name;
    }

    /**
     * Sets the brand account associated with the product.
     *
     * @param brandAccount The brand account associated with the product.
     */
    public void setBrand(BrandAccount brandAccount) {
        this.brand = brandAccount;
    }

    /**
     * Sets the price of the product.
     *
     * @param price The new price for the product.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns a string representation of the product.
     *
     * @return A string containing the product's unique identifier, name, and price.
     */
    @Override
    public String toString() {
        return "productId: " + productId + " name: " + productName + " price: " + price;
    }
}
