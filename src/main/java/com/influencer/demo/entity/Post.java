package com.influencer.demo.entity;

import jakarta.persistence.*;

/**
 * The {@code Post} class represents an entity for posts in the application.
 * Each post is associated with an {@link Account} and contains a description.
 */
@Entity
@Table(name = "Post")
public class Post {

    /**
     * The unique identifier for the post.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private long postId;

    /**
     * The description or content of the post.
     */
    @Column(name = "description")
    private String description;

    /**
     * The account associated with the post.
     */
    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    /**
     * Constructs a post with the specified description.
     *
     * @param description The content or description of the post.
     */
    public Post(String description) {
        this.description = description;
    }

    /**
     * Default constructor for JPA.
     */
    protected Post() {}

    /**
     * Retrieves the unique identifier of the post.
     *
     * @return The post's unique identifier.
     */
    public long getPostId() {
        return postId;
    }

    /**
     * Retrieves the description or content of the post.
     *
     * @return The post's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the unique identifier for the post.
     *
     * @param id The new unique identifier for the post.
     */
    public void setPostId(long id) {
        this.postId = id;
    }

    /**
     * Sets the description or content of the post.
     *
     * @param desc The new description for the post.
     */
    public void setDescription(String desc) {
        this.description = desc;
    }

    /**
     * Returns a string representation of the post.
     *
     * @return A string containing the post's unique identifier and description.
     */
    @Override
    public String toString() {
        return "postId: " + postId + " description: " + description;
    }

    /**
     * Sets the account associated with the post.
     *
     * @param accountId The account associated with the post.
     */
    public void setAccountPost(Account accountId) {
        this.account = accountId;
    }

    /**
     * Retrieves the account associated with the post.
     *
     * @return The account associated with the post.
     */
    public Account getAccount() {
        return account;
    }
}
