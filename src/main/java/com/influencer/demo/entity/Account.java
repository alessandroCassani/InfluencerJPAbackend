package com.influencer.demo.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The {@code Account} class represents a base entity for user accounts in the application.
 * It is an abstract class that provides common properties and behaviors for different types of accounts.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "Account")
public abstract class Account {

    /**
     * The unique identifier for the account.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountId")
    private long accountId;

    /**
     * The username associated with the account.
     */
    @Column(name = "username")
    private String userName;

    /**
     * The biography or description of the account.
     */
    @Column(name = "bio")
    private String bio;

    /**
     * The set of accounts that the current account is following.
     */
    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "account_followed_list",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id"))
    private Set<Account> followedList = new HashSet<>();

    /**
     * The list of posts associated with the account.
     */
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    /**
     * Constructs an account with the specified username and biography.
     *
     * @param userName The username for the account.
     * @param bio      The biography or description of the account.
     */
    public Account(String userName, String bio) {
        this.userName = userName;
        this.bio = bio;
    }

    /**
     * Default constructor for JPA.
     */
    public Account() {
    }

    /**
     * Performs necessary actions before removing the account, such as updating the followedList of other accounts.
     */
    @PreRemove
    private void preRemove() {
        for (Account followedAccount : followedList) {
            followedAccount.getFollowedList().remove(this);
        }
    }

    /**
     * Retrieves the unique identifier of the account.
     *
     * @return The account's unique identifier.
     */
    public long getId() {
        return accountId;
    }

    /**
     * Retrieves the username associated with the account.
     *
     * @return The account's username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Retrieves the biography or description of the account.
     *
     * @return The account's biography.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Retrieves the set of accounts that the current account is following.
     *
     * @return The set of followed accounts.
     */
    public Set<Account> getFollowedList() {
        return followedList;
    }

    /**
     * Retrieves the list of posts associated with the account.
     *
     * @return The list of posts.
     */
    public List<Post> getPostList() {
        return postList;
    }

    /**
     * Sets the username for the account.
     *
     * @param username The new username for the account.
     */
    public void setUserName(String username) {
        this.userName = username;
    }

    /**
     * Sets the biography or description of the account.
     *
     * @param bio The new biography for the account.
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Adds a new account to the set of followed accounts.
     *
     * @param follower The account to be followed.
     */
    public void addFollowed(Account follower) {
        followedList.add(follower);
    }

    /**
     * Retrieves the set of accounts that the current account is following.
     *
     * @return The set of followed accounts.
     */
    public Set<Account> getFollowedAccounts() {
        return followedList;
    }

    /**
     * Returns a string representation of the account.
     *
     * @return A string containing the account's unique identifier, username, and biography.
     */
    @Override
    public String toString() {
        return "accountId: " + accountId + " username: " + userName + " bio " + bio;
    }
}
