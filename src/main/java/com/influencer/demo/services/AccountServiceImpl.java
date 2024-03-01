package com.influencer.demo.services;

import com.influencer.demo.entity.Account;
import com.influencer.demo.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The {@code AccountServiceImpl} class implements the {@link AccountService} interface and provides
 * the implementation for managing {@link Account} entities in the application.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Registers a new account profile.
     *
     * @param profile The account profile to be registered.
     */
    @Override
    @Transactional
    public void registerAccount(final Account profile) {
        accountRepository.save(profile);
    }

    /**
     * Deletes the specified account profile.
     *
     * @param profileId The unique identifier of the account profile to be deleted.
     */
    @Override
    @Transactional
    public void deleteAccount(final Long profileId) {
        accountRepository.deleteById(profileId);
    }

    /**
     * Updates the bio information of the specified account profile.
     *
     * @param profileId The unique identifier of the account profile.
     * @param newBio    The new bio information.
     */
    @Override
    @Transactional
    public void updateBio(final Long profileId, String newBio) {
        Optional<Account> optionalProfile = accountRepository.findById(profileId);
        if (optionalProfile.isPresent()) {
            Account existingAccount = optionalProfile.get();
            existingAccount.setBio(newBio);
            accountRepository.save(existingAccount);
        } else
            throw new EntityNotFoundException("Account with ID " + profileId + " not found");
    }

    /**
     * Retrieves information about the specified account profile.
     *
     * @param profileId The unique identifier of the account profile.
     * @return The account information.
     */
    @Override
    @Transactional
    public Account getInfoAccount(final Long profileId) {
        Optional<Account> accountOptional = accountRepository.findById(profileId);

        if (accountOptional.isPresent()) {
            return accountOptional.get();
        } else {
            throw new EntityNotFoundException("Account with ID " + profileId + " not found");
        }
    }

    /**
     * Adds a follower to the specified account.
     *
     * @param accountId   The unique identifier of the account.
     * @param followedId  The unique identifier of the follower account.
     */
    @Override
    @Transactional
    public void addFollowed(final Long accountId, final Long followedId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        Optional<Account> followerOptional = accountRepository.findById(followedId);

        if (accountOptional.isPresent() && followerOptional.isPresent()) {
            Account account = accountOptional.get();
            Account followed = followerOptional.get();

            if (!account.getFollowedAccounts().contains(followed)) {
                account.addFollowed(followed);
                accountRepository.save(account);
            } else {
                throw new IllegalArgumentException("The specified account is already following the specified follower.");
            }
        } else {
            throw new IllegalArgumentException("Either the account or the follower does not exist.");
        }
    }

    /**
     * Deletes a follower from the specified account's followed list.
     *
     * @param accountId   The unique identifier of the account.
     * @param followedId  The unique identifier of the follower account.
     */
    @Override
    @Transactional
    public void deleteFollowed(final Long accountId, final Long followedId) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        Optional<Account> followedOptional = accountRepository.findById(followedId);

        if (accountOptional.isPresent() && followedOptional.isPresent()) {
            Account account = accountOptional.get();
            Account accountFollowed = followedOptional.get();

            if (account.getFollowedAccounts().contains(accountFollowed)) {
                account.getFollowedAccounts().remove(accountFollowed);
                accountRepository.save(account);
            } else {
                throw new IllegalStateException("The specified account is not following the specified follower.");
            }
        } else {
            throw new EntityNotFoundException("Either the account or the follower does not exist.");
        }
    }

    /**
     * Retrieves the list of accounts followed by the specified account.
     *
     * @param accountId The unique identifier of the account.
     * @return The list of accounts followed by the specified account.
     */
    @Override
    @Transactional
    public List<Account> getFollowedList(final Long accountId) {
        return accountRepository.findByFollowedList(accountId);
    }

    /**
     * Counts the total number of account elements in the system.
     *
     * @return The total number of account elements.
     */
    @Override
    public int countElements() {
        return accountRepository.countElements();
    }
}
