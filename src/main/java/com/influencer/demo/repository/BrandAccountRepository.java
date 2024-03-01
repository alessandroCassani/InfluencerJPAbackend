package com.influencer.demo.repository;

import com.influencer.demo.entity.BrandAccount;
import com.influencer.demo.entity.InfluencerAccount;
import com.influencer.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * The {@code BrandAccountRepository} interface provides methods for interacting with the {@link BrandAccount} entity in the database.
 */
public interface BrandAccountRepository extends JpaRepository<BrandAccount, Long> {

    /**
     * JPQL query to retrieve products associated with a specific brand account.
     */
    String queryGetProducts = "SELECT p FROM Product p WHERE p.brand = :brandAccount";

    /**
     * JPQL query to retrieve influencers associated with a specific brand account.
     */
    String getInfluencers = "SELECT i\n" +
            "FROM InfluencerAccount i\n" +
            "JOIN i.brandCollaborations b\n" +
            "WHERE b = :brandID";

    /**
     * JPQL query to retrieve brands with products having a price lower than a specified maximum price.
     */
    String queryGetBrandWithProductsPrice = "SELECT DISTINCT b FROM BrandAccount b JOIN b.brandProducts p WHERE p.price < :maxPrice";

    /**
     * Retrieves a set of products associated with a specific brand account.
     *
     * @param brandAccount The brand account for which products are retrieved.
     * @return A set of products associated with the specified brand account.
     */
    @Query(queryGetProducts)
    Set<Product> findProductsByBrandAccount(@Param("brandAccount") BrandAccount brandAccount);

    /**
     * Retrieves a set of influencers associated with a specific brand account.
     *
     * @param brandID The unique identifier of the brand account.
     * @return A set of influencers associated with the specified brand account.
     */
    @Query(value = getInfluencers)
    Set<InfluencerAccount> findInfluencerListByBrand(@Param("brandID") BrandAccount brandID);

    /**
     * Retrieves a list of brand accounts with products having a price lower than the specified maximum price.
     *
     * @param maxPrice The maximum price for the products.
     * @return A list of brand accounts with qualifying products.
     */
    @Query(value = queryGetBrandWithProductsPrice)
    List<BrandAccount> findBrandsByProductPrice(@Param("maxPrice") double maxPrice);
}
