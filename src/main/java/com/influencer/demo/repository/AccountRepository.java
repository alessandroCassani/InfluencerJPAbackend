package com.influencer.demo.repository;

import com.influencer.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The {@code AccountRepository} interface provides methods for interacting with the {@link Account} entity in the database.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * SQL query to retrieve a list of accounts followed by a given account.
     */
    String getFollowedListQuery = "SELECT a2.* FROM account_followed_list a1 " +
            "JOIN account a2 ON a1.followed_id = a2.account_id " +
            "WHERE a1.account_id = :accountId";

    /**
     * SQL query to count the total number of accounts in the database.
     */
    String countQuery = "SELECT COUNT(*) FROM account";

    /**
     * Retrieves a list of accounts followed by the specified account.
     *
     * @param accountId The unique identifier of the account.
     * @return A list of accounts followed by the specified account.
     */
    @Query(value = getFollowedListQuery, nativeQuery = true)
    List<Account> findByFollowedList(Long accountId);

    /**
     * Counts the total number of accounts in the database.
     *
     * @return The total number of accounts.
     */
    @Query(value = countQuery, nativeQuery = true)
    int countElements();
}
