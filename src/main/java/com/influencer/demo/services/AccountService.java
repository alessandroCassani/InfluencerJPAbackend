package com.influencer.demo.services;

import com.influencer.demo.entity.Account;

import java.util.List;

/**
 * The {@code AccountService} interface defines methods for managing {@link Account} entities in the application.
 */
public interface AccountService {

    /**
     * Registers a new account profile.
     *
     * @param profile The account profile to be registered.
     */
    void registerAccount(Account profile);

    /**
     * Adds a follower to the specified account.
     *
     * @param accountId   The unique identifier of the account.
     * @param followerId  The unique identifier of the follower account.
     */
    void addFollowed(Long accountId, Long followerId);

    /**
     * Deletes a follower from the specified account's followed list.
     *
     * @param accountId   The unique identifier of the account.
     * @param followerId  The unique identifier of the follower account.
     */
    void deleteFollowed(Long accountId, Long followerId);

    /**
     * Retrieves the list of accounts followed by the specified account.
     *
     * @param accountId The unique identifier of the account.
     * @return The list of accounts followed by the specified account.
     */
    List<Account> getFollowedList(Long accountId);

    /**
     * Deletes the specified account profile.
     *
     * @param profileId The unique identifier of the account profile to be deleted.
     */
    void deleteAccount(Long profileId);

    /**
     * Updates the bio information of the specified account profile.
     *
     * @param profileId The unique identifier of the account profile.
     * @param newBio    The new bio information.
     */
    void updateBio(Long profileId, String newBio);

    /**
     * Retrieves information about the specified account profile.
     *
     * @param profileId The unique identifier of the account profile.
     * @return The account information.
     */
    Account getInfoAccount(Long profileId);

    /**
     * Counts the total number of account elements in the system.
     *
     * @return The total number of account elements.
     */
    int countElements();
}
