package com.influencer.demo.repository;

import com.influencer.demo.entity.BrandAccount;
import com.influencer.demo.entity.InfluencerAccount;
import com.influencer.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * The {@code InfluencerAccountRepository} interface provides methods for interacting with the {@link InfluencerAccount} entity in the database.
 */
@Repository
public interface InfluencerAccountRepository extends JpaRepository<InfluencerAccount, Long> {

    /**
     * JPQL query to retrieve the set of brand accounts associated with a specific influencer account.
     */
    String getBrands = "SELECT b\n" +
            "FROM BrandAccount b\n" +
            "JOIN b.influencerList l\n" +
            "WHERE l = :influencerID";

    /**
     * Retrieves the set of brand accounts associated with a specific influencer account.
     *
     * @param influencerID The unique identifier of the influencer account.
     * @return The set of brand accounts associated with the specified influencer account.
     */
    @Query(value = getBrands)
    Set<BrandAccount> findBrandListByInfluencer(@Param("influencerID") InfluencerAccount influencerID);
}
